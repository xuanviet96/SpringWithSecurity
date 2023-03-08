package integrationtest;

import com.example.springwithsecurity.model.request.CreateUserRequest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.BaseAPIUtil;

import java.util.Properties;

public class UserIntegrationTest extends BaseIntegrationTest {
    private String registerRoute;
    private String changePasswordRoute;

    @BeforeClass
    public void setup() {
        registerRoute = properties.getProperty("registerRoute");
        changePasswordRoute = properties.getProperty("changePasswordRoute");
        testCSVFile = "usertest.csv";
    }

    @Test(dataProvider = "getCSVDataForRegistration")
    public void sendRegistrationRequest(String testCaseId) {
        String url = host + registerRoute;
        Properties prop = loadCSVData(testCaseId);

        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setEmail(prop.getProperty("userName"));
        createUserRequest.setPassword(prop.getProperty("password"));
        createUserRequest.setFullName(prop.getProperty("fullname"));
        createUserRequest.setPhone(prop.getProperty("phone"));
        createUserRequest.setAddress(prop.getProperty("address"));

        if (testCaseId.equalsIgnoreCase("sendRegistrationSuccessRequest")) {
            BaseAPIUtil.sendPostRequest(url, "", createUserRequest, 200);
        } else {
            BaseAPIUtil.sendPostRequest(url, "", createUserRequest, 400);
        }
    }
        @DataProvider(name = "getCSVDataForRegistration")
        public Object[][] getCSVDataForRegistration () {
            return new Object[][]{{"sendRegistrationSuccessRequest"}, {"sendRegistrationDuplicateEmailRequest"}};
        }

        @DataProvider(name = "getCSVDataForJwtToken")
        public Object[][] getCSVDataForJwtToken () {
            return new Object[][]{{"getCSVDataForJwtTokenSuccess"}, {"getCSVDataForJwtTokenFail"}};
        }

        @DataProvider(name = "getCSVDataForChangePassword")
        public Object[][] getCSVDataForChangePassword () {
            return new Object[][]{{"getCSVDataForChangePasswordSuccess"}, {"getCSVDataForChangePasswordFail"}};
        }
    }
