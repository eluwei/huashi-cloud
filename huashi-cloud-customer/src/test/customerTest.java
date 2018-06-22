import com.huashi.cloud.common.qiniu.QiniuStorage;
import com.huashi.cloud.common.utils.FileUtil;
import com.huashi.cloud.customer.ApplicationHuaShiCloudCustomer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ApplicationHuaShiCloudCustomer.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@EnableAutoConfiguration
public class customerTest {


    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private QiniuStorage qiniuUtil;


    @Test
    public void getparamsUniqueMap() throws Exception {
        Map<String, Object> params = new HashMap<>();
        Object result = testRestTemplate.getForObject("/customer/base/list", Object.class, params);
        System.out.println(result);
    }

    @Test
    public void uplodPicture() throws Exception {
        //测试上传图片
        byte[] buff = FileUtil.getBytesByFile("C:/Users/Administrator/Desktop/花食.jpg");
        String key = qiniuUtil.uploadImage(buff, "brand" , "huashi");
        System.out.println("key = " + key);


        //测试下载图片
        String url = qiniuUtil.getUrl(key);
        System.out.println("url = " + url);
    }

    public static void main1(String[] args) {
        FileChannel fc = null;
        try {
            //创建文件管道
            fc = FileChannel.open(Paths.get("C:\\Users\\Administrator\\Desktop\\huashi-service.sh"), StandardOpenOption.READ);
            //创建buffer
            ByteBuffer buffer = ByteBuffer.allocate(12);
            int len = 0;
            byte[] buf = new byte[12];
            //只要能够从缓冲区中读取数据
            while((len=fc.read(buffer))>0) {
                //重置缓冲区
                buffer.flip();
                //从缓冲区读取到字节数组中
                buffer.get(buf,0,len);
                //输出字节数组的值
                System.out.println(new String(buf,0,len));
                //重置缓冲区
                buffer.clear();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fc!=null) fc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        String test = "http://pa0033s0h.bkt.clouddn.com/@/channel/forUse/5188689684274d5ca6aab26b56955a9d.png?e=1529638733&token=aTLmh_-BkzEZuRP12TJWY4hIDE";
        System.out.println(test.substring(test.indexOf("@") + 1, test.indexOf("?")));
    }

}
