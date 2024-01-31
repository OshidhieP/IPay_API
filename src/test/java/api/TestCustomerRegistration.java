package api;

import endpoints.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCustomerRegistration {
    @Test(priority = 1)
    public void testCreateUser() {
        try {
            String accessToken = TokenRequest.requestToken();

            Response postResponse = UserEndpoints.createNewUser(accessToken);
            ResponseAssert.assertThat(postResponse)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseObject.message", "OTP is sent to your phone. Please enter the code to continue the registration.");

            Response otpVerify = UserEndpoints.verifyToken(accessToken);
            ResponseAssert.assertThat(otpVerify)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseObject.message", "Your account verified successfully.");


            Response success = UserEndpoints.cusRegisterSuccess(accessToken);
            ResponseAssert.assertThat(success)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseObject.status", "A");

//            Response invalidLang = UserEndpoints.cusRegisterSuccess(accessToken);
//            ResponseAssert.assertThat(invalidLang)
//                    .statusCodeIs(200)
//                    .hasErrorMessage("Preferred language is invalid.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}