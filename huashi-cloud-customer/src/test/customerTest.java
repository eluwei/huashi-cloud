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

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationHuaShiCloudCustomer.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
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
}
