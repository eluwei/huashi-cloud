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


    @Test
    public void getparamsUniqueMap() throws Exception {
        Map<String, Object> params = new HashMap<>();
        Object result = testRestTemplate.getForObject("/customer/base/list", Object.class, params);
        System.out.println(result);
    }
}
