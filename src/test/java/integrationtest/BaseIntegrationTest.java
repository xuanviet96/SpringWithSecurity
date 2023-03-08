package integrationtest;

import com.example.springwithsecurity.entity.User;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.testng.annotations.BeforeClass;
import util.TestUtil;

import java.io.IOException;
import java.util.Properties;

public class BaseIntegrationTest {
    protected User testUser = new User();
    protected String token;
    protected String host;
    protected String loginRoute;
    protected String userGetUserRoute;
    protected String testCSVFile;
    protected Properties properties;

    @BeforeClass
    public void defaultSetup() throws IOException {
        properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/test.properties"));
        testUser.setEmail("test@email.com");
        testUser.setPassword("thanhtu123");
        host = properties.getProperty("host");
        loginRoute = properties.getProperty("loginRoute");
        userGetUserRoute = properties.getProperty("userGetUserRoute");

    }
    public Properties loadCSVData(String testCaseId) {
        return TestUtil.getCSVData(testCSVFile, testCaseId);
    }
}
