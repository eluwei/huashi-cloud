package com.huashi.cloud.common.repository;

import com.huashi.cloud.common.domain.BaseDomain;
import com.huashi.cloud.common.exception.FieldAccessException;
import com.huashi.cloud.common.exception.result.FieldAccessExceptionResult;
import com.huashi.cloud.common.page.PageBean;
import com.huashi.cloud.common.qiniu.QiniuStorage;
import com.huashi.cloud.common.result.ResultState;
import com.huashi.cloud.common.utils.ConvertUtil;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liangkun on 2017/12/7.
 */
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T,ID> implements BaseRepository<T,ID> {

    private final EntityManager entityManager;

    private QiniuStorage qiniuUtil;

    //父类没有不带参数的构造方法，这里手动构造父类
    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager, QiniuStorage qiniuUtil) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
        this.qiniuUtil = qiniuUtil;
    }



    //通过EntityManager来完成查询
    @Override
    public  List<Map<String, Object>> listBySQL(String sql) {
        return entityManager.createNativeQuery(sql).getResultList();
    }

    @Override
    public <T> T find(Class<T> clasz, Object id) throws FieldAccessException {
        BaseDomain baseDomain = BaseDomain.getBaseDomain(clasz);
        List<T> results = findAll(clasz, baseDomain.getId() + "=?", new Object[]{id});
        if (results.size() == 0){
            return null;
        }
        else{
            return results.get(0);
        }
    }


    @Override
    public <T> T findFirst(Class<T> clasz) throws FieldAccessException {
        return findFirst(clasz, null, null, null, 0, 0);
    }

    @Override
    public <T> T findFirst(Class<T> clasz, String conditions, Object[] args) throws FieldAccessException {
        return findFirst(clasz, conditions, args, null, 0, 0);
    }

    @Override
    public <T> T findFirst(Class<T> clasz, String conditions, Object[] args, String order)  throws FieldAccessException{
        return findFirst(clasz, conditions, args, order, 0, 0);
    }

    @Override
    public <T> T findFirst(Class<T> clasz, String conditions, Object[] args, String order, int limit)  throws FieldAccessException{
        return findFirst(clasz, conditions, args, order, limit, 0);
    }

    @Override
    public <T> T findFirst(Class<T> clasz, String conditions, Object[] args, String order, int limit, int offset)  throws FieldAccessException{
        List<T> results = findAll(clasz, conditions, args, order, limit, offset);
        if (results.size() == 0){
            return null;
        }
        else{
            return results.get(0);
        }
    }

    @Override
    public <T> List<T> findAll(Class<T> clasz) throws FieldAccessException {
        return findAll(clasz, null, null, null, 0, 0);
    }

    @Override
    public <T> List<T> findAll(Class<T> clasz, String conditions) throws FieldAccessException {
        return findAll(clasz, conditions, null, null, 0, 0);
    }

    @Override
    public <T> List<T> findAll(Class<T> clasz, String conditions, Object[] args) throws FieldAccessException {
        return findAll(clasz, conditions, args, null, 0, 0);
    }

    @Override
    public <T> List<T> findAll(Class<T> clasz, String conditions, Object[] args, String order) throws FieldAccessException {
        return findAll(clasz, conditions, args, order, 0, 0);
    }

    @Override
    public <T> List<T> findAll(Class<T> clasz, String conditions, Object[] args, String order, int limit)  throws FieldAccessException{
        return findAll(clasz, conditions, args, order, limit, 0);
    }

    @Override
    public <T> List<T> findAll(Class<T> clasz, String conditions, Object[] args, String order, int limit, int offset) throws FieldAccessException {
        BaseDomain baseDomain = BaseDomain.getBaseDomain(clasz);
        String sql = "select * from " + baseDomain.getTable();
        if (StringUtils.isNotBlank(conditions)){
            sql += " where " + conditions;
        }
        return findBySql(clasz, sql, args, order, limit, offset);
    }

    @Override
    public <T> List<T> findBySql(Class<T> clasz, String sql) throws FieldAccessException {
        return findBySql(clasz, sql, null, null, 0, 0);
    }

    @Override
    public <T> List<T> findBySql(Class<T> clasz, String sql, Object[] args)  throws FieldAccessException{
        return findBySql(clasz, sql, args, null, 0, 0);
    }

    @Override
    public <T> List<T> findBySql(Class<T> clasz, String sql, Object[] args, String order) throws FieldAccessException {
        return findBySql(clasz, sql, args, order, 0, 0);
    }

    @Override
    public <T> List<T> findBySql(Class<T> clasz, String sql, Object[] args, String order, int limit)  throws FieldAccessException{
        return findBySql(clasz, sql, args, order,limit, 0);
    }

    @Override
    public <T> List<T> findBySql(Class<T> clasz, String sql, Object[] args, String order, int limit, int offset) throws FieldAccessException {
       if(StringUtils.isNotBlank(order))
            sql += " order by " + order;
        return select(clasz, sql, args, limit, offset);

    }

    @Override
    public <T> List<T> findBySql(Class<T> clasz, String sql, Object[] args, String order, PageBean pageBean) throws FieldAccessException {
        String sqlLower = sql.toLowerCase();
        String countSql ="select count(*) as COUNT " +sql.substring(sqlLower.indexOf("from"));
        Object result = executeScalar(countSql, args);
        if (result == null){
            pageBean.setTotalCount(0);
        }else {
            pageBean.setTotalCount(Integer.parseInt(result.toString()));
        }

        pageBean.setVal(findBySql(clasz, sql,args, order, pageBean.getRows(), pageBean.getSkip()));
        return (List<T>) pageBean.getVal();
    }

    @Override
    public <T> PageBean find(Class<T> clasz, String order, PageBean pageBean) throws FieldAccessException {
        return find(clasz, null, order, null, pageBean);
    }

    @Override
    public <T> PageBean find(Class<T> clasz, String conditions, Object[] args, PageBean pageBean) throws FieldAccessException {
        return find(clasz, conditions, null, args, pageBean);
    }

    @Override
    public <T> PageBean find(Class<T> clasz, String conditions, String order, Object[] args, PageBean pageBean) throws FieldAccessException {
        int total = count(clasz, conditions, args);
        pageBean.setTotalCount(total);
        pageBean.setVal(findAll(clasz, conditions, args, order, pageBean.getRows(), pageBean.getSkip()));
        return pageBean;
    }

    @Override
    public List<Map<String, Object>> getResultMap(String sql, Object[] args, String order, int limit, int offset)  throws FieldAccessException{
        if(StringUtils.isNotBlank(order))
            sql += " order by " + order;
        return select(sql, args, limit, offset);
    }

    @Override
    public List<Map<String, Object>> getResultMap(String sql, Object[] args, String order, PageBean pageBean)  throws FieldAccessException{
        String sqlLower = sql.toLowerCase();
        String countSql ="select count(*) as COUNT " +sql.substring(sqlLower.indexOf("from"));
        Object result = executeScalar(countSql, args);
        pageBean.setTotalCount(Integer.parseInt(result.toString()));
        pageBean.setVal(getResultMap(sql, args, order, pageBean.getRows(), pageBean.getSkip()));
        return (List<Map<String, Object>>) pageBean.getVal();
    }

    @Override
    public List<Map<String, Object>> getResultMap(String sql, Object[] args, String order, PageBean pageBean, Boolean searchPrev)  throws FieldAccessException{
        String sqlLower = sql.toLowerCase();
        String countSql ="select count(*) as COUNT " +sql.substring(sqlLower.indexOf("from"));
        Object result = executeScalar(countSql, args);
        Integer totalCount = Integer.parseInt(result.toString());
        Integer pageSize = pageBean.getPageSize();
        Integer totalPageCount = totalCount/pageSize  + ((totalCount % pageSize == 0) ? 0 : 1);
        if (searchPrev && totalPageCount>0 && pageBean.getCurrentPage() > totalPageCount){
            pageBean.setCurrentPage(totalPageCount);
        }
        pageBean.setTotalCount(Integer.parseInt(result.toString()));
        pageBean.setVal(getResultMap(sql, args, order, pageBean.getRows(), pageBean.getSkip()));
        return (List<Map<String, Object>>) pageBean.getVal();
    }



    @Override
    public int count(Class<?> c, String conditions, Object[] args) throws FieldAccessException {
        BaseDomain baseDomain = BaseDomain.getBaseDomain(c);
        String sql = "select count(*) from " + baseDomain.getTable();
        if (StringUtils.isNotBlank(conditions)){
            sql += " where " + conditions;
        }
        Object result = executeScalar(sql, args);

        return Integer.parseInt(result.toString());
    }



    public <T> List<T> select(Class<T> clasz, String sql, Object[] args, int limit, int offset)  throws FieldAccessException{
        BaseDomain baseDomain = BaseDomain.getBaseDomain(clasz);
        List<T> data = new ArrayList<T>();
        List<Map<String,Object>> items = select(sql, args, limit, offset);
        for(Map<String,Object> item: items){
            T obj = null;
            try {
                obj = clasz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                String err = "new instance " + clasz.getSimpleName() +  " error.";
                throw new FieldAccessException(err, new FieldAccessExceptionResult(ResultState.NEWINSTANCE_ERROR.name(), err));
            } 
            if (baseDomain.getId() != null){
                Object value = item.get(baseDomain.getId().toLowerCase());
                value = ConvertUtil.castFromObject(value, baseDomain.getIdType());
                baseDomain.setFieldValue(clasz, baseDomain.getId(), obj, value);
            }
            for(BaseDomain.ColumnField field: baseDomain.getColumnFields()){
                Object value = item.get(field.getName());
                if(value == null) //驼峰转下滑线
                    value = item.get(com.huashi.cloud.common.utils.StringUtils.humpToLine2(field.getName()));
                if(value != null && field.getName().indexOf("Url") >= 0)
                    value = qiniuUtil.getUrl(value.toString());
                value = ConvertUtil.castFromObject(value, field.getType());
                baseDomain.setFieldValue(clasz, field.getName(), obj, value);
            }
            data.add(obj);
        }
        return data;
    }

    public List<Map<String,Object>> select(String sql, Object[] args, int limit, int offset){
        Object[] sqlParts = buildSql(sql, args, limit, offset);
        sql = sqlParts[0].toString();
        args = (Object[])sqlParts[1];
        Query query = entityManager.createNativeQuery(sql);
        if(args != null) {
            for (int i = 0; i < args.length; i++) {
                query.setParameter(i + 1, args[i]);
            }
        }
        //返回map集合
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }

    @Override
    public Object executeScalar(String sql, Object[] args) {
        Object[] sqlParts = buildSql(sql, args, 0, 0);
        sql = sqlParts[0].toString();
        args = (Object[])sqlParts[1];
        Query query = entityManager.createNativeQuery(sql);
        if(args != null) {
            for (int i = 0; i < args.length; i++) {
                query.setParameter(i + 1, args[i]);
            }
        }
        return query.getSingleResult();
    }


    /**
     * 构建SQL语句，处理掉NULL值参数
     * @param sql 原始SQL语句
     * @param args 原始参数
     * @return 三个元素数组，分别是SQL是语句、参数、显示SQL语句
     */
    private Object[] buildSql(String sql, Object[] args, int limit, int offset){
        Object[] result = new Object[3];
        if (args == null){
            result[0] = sql;
            result[1] = args;
            result[2] = sql;
            return result;
        }

        String newSql = "";
        String showSql = "";
        List<Object> tmpArgs = new ArrayList<Object>();

        String[] ss = (sql + " ").split("\\?");
        for(int i=0; i<ss.length-1; i++){
            Object arg = args[i];
            if (arg == null) {
                newSql += ss[i] + "null";
                showSql += ss[i] + "null";
            } else {
                newSql += ss[i] + "?";
                tmpArgs.add(arg);
                if (arg instanceof String) {
                    showSql += ss[i] + "'" + arg + "'";
                } else {
                    showSql += ss[i] + arg.toString();
                }
            }
        }
        newSql += ss[ss.length - 1];
        showSql += ss[ss.length - 1];

        if(offset > 0)
            newSql += "offset " + offset;

        if(limit > 0)
            newSql += "limit " + limit;

        result[0] = newSql;
        result[1] = tmpArgs.toArray();
        result[2] = showSql;

        return result;
    }




}
