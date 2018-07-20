package com.huashi.cloud.customer.service;

import com.huashi.cloud.common.page.PageBean;
import com.huashi.cloud.common.qiniu.QiniuStorage;
import com.huashi.cloud.common.redis.RedisStorage;
import com.huashi.cloud.common.utils.EncryptUtils;
import com.huashi.cloud.common.utils.StringUtils;
import com.huashi.cloud.common.utils.UUIDGenerator;
import com.huashi.cloud.customer.admin.domain.Admin;
import com.huashi.cloud.customer.app.domain.Channel;
import com.huashi.cloud.customer.common.domain.Goods;
import com.huashi.cloud.customer.repository.CloudAdminGoodsRepository;
import com.huashi.cloud.customer.repository.CloudAdminUserRepository;
import com.huashi.cloud.customer.repository.CloudChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class AdminService {

    @Autowired
    protected CloudAdminUserRepository cloudAdminUserRepository;


    @Autowired
    protected CloudChannelRepository cloudChannelRepository;

    @Autowired
    protected CloudAdminGoodsRepository cloudAdminGoodsRepository;

    @Autowired
    private QiniuStorage qiniuUtil;


    @Autowired
    protected RedisStorage redisStorage;

    /**
     * admin 用户登录
     * @return
     */
    public Object userLogin(String userName, String password, String ip)throws Exception {
        Admin admin = cloudAdminUserRepository.findAdminByUserName(userName);
        String passwordMd5 = EncryptUtils.encodePassword(password, admin.getPasswordSalt());
        boolean loginBoolean = passwordMd5.equals(admin.getPassword());
        if(loginBoolean){
            // 更新登录信息
            admin.setLastLoginTime(new Date());
            admin.setLastLoginIp(ip);
            cloudAdminUserRepository.save(admin);
        }
        return handLoginInfo(admin, true);
    }

    /**
     * 缓存用户的session
     * @param userId
     * @param sessionId
     */
    private void addUserSessionInRedis(int userId, String sessionId){
        redisStorage.removePattern("huashi_admin_sessionid_" + userId + "_*");// 删除所有之前登录的账号缓存
        redisStorage.set("huashi_admin_sessionid_" + sessionId, userId, 15 * 24 * 60 * 60L);// 设置新登录的账号缓存
    }

    /**
     * 登录逻辑
     *
     * @param admin
     * @param autoLogin
     * @return
     * @throws Exception
     * @throws Exception
     */
    private Map<String, Object> handLoginInfo(Admin admin, Boolean autoLogin) throws Exception {
        //封装登录信息
        Map<String, Object> userInfo = new HashMap<String, Object>();
        String sessionId =  admin.getId() + "_" + UUIDGenerator.uuid();
        userInfo.put("sessionId", sessionId);
        userInfo.put("userId", admin.getId());
        userInfo.put("userName", admin.getUserName());
        userInfo.put("avatar", admin.getAvatar());
        userInfo.put("admin_role_id", admin.getAdminRoleId());
        //记录redis
        addUserSessionInRedis(admin.getId(), sessionId);
        return userInfo;
    }

    /**
     * 查询 小程序首页渠道分类 分页查询
     * @return
     */
    public Object listChannel(String name, PageBean pageBean) {
        if(StringUtils.isNotEmpty(name)) {
            return cloudAdminUserRepository.find(Channel.class, "name like ?", new Object[]{"%" + name + "%"}, pageBean);
        }else {
            return cloudAdminUserRepository.find(Channel.class, null, null, pageBean);
        }
    }


    /**
     * 管理后台 获取商品列表
     * @return
     */
    public Object getGoodsList(String name, PageBean pageBean) {
        if(StringUtils.isNotEmpty(name)) {
            return cloudAdminGoodsRepository.find(Goods.class, "name like ?", new Object[]{"%" + name + "%"}, pageBean);
        }else {
            return cloudAdminGoodsRepository.find(Goods.class, null, null, pageBean);
        }
    }


    /**
     * 管理后台 根据id获取商品详情
     * @param id
     * @return
     * @throws Exception
     */
    public Goods getGoodsInfo(Integer id){

        return cloudAdminGoodsRepository.find(Goods.class, id);
    }

    /**
     * 管理后台 获取首页分类详情
     * @param id
     * @return
     * @throws Exception
     */
    public Channel getChannelInfo(Integer id){
        return cloudAdminUserRepository.find(Channel.class, id);
    }

    /**
     * 保存 首页分类 当有图片过来时 进行 图片上传
     * @param channel
     * @param file
     * @return
     */
    public Channel storeOrupdateChannel(Channel channel, MultipartFile file)throws Exception{
        if(file == null) {
            String nativeQiniuKey = channel.getIconUrl().substring(channel.getIconUrl().indexOf("@") + 1, channel.getIconUrl().indexOf("?"));
            channel.setIconUrl(nativeQiniuKey); //前端传过来的路径是真实的路径，需要处理
            return cloudChannelRepository.save(channel);
        }
        Channel nativeChannel = getChannelInfo(channel.getId() == null ? 0 : channel.getId());
        if(nativeChannel == null) {
            channel.setIconUrl(qiniuUtil.uploadImage(file.getBytes(), "channel", "forUse")); //新增图片
        }else {
            String nativeQiniuKey = nativeChannel.getIconUrl().substring(nativeChannel.getIconUrl().indexOf("@") + 1, nativeChannel.getIconUrl().indexOf("?"));
            channel.setIconUrl(qiniuUtil.uploadImage(file.getBytes(), nativeQiniuKey,true)); //更新图片
        }
        return cloudChannelRepository.save(channel);
    }

    /**
     * 保存 首页分类 根据Id进行删除
     * @param id
     * @return
     */
    public Integer onlineChannelById(Integer id, Integer online) throws Exception{
        Channel channel = getChannelInfo(id);
        channel.setOnline(online);
        return storeOrupdateChannel(channel, null).getId();
    }


}
