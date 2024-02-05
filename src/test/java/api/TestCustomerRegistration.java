package api;

import endpoints.*;
import endpoints.classes.TokenRequest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCustomerRegistration {
    @Test(priority = 1)
    public void testCreateUser() throws IOException {
            String accessToken = TokenRequest.requestToken();

            Response postResponse = UserEndpoints.createNewUser(accessToken);
            ResponseAssert.assertThat(postResponse)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseObject.message", "OTP is sent to your phone. Please enter the code to continue the registration.")
                    .assertAll();

            Response otpVerify = UserEndpoints.verifyToken(accessToken);
            ResponseAssert.assertThat(otpVerify)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseObject.message", "Your account verified successfully.")
                    .assertAll();

            Response success = UserEndpoints.cusRegisterSuccess(accessToken);
            ResponseAssert.assertThat(success)
                    .statusCodeIs(200)
                    .hasKeyWithValue("responseObject.status", "A")
                    .assertAll();
    }

    @Test(priority = 2)
        public void testCreateUserInvalid() throws IOException {

            String accessToken = TokenRequest.requestToken();

            Response invalidEmail = UserEndpoints.emptyEmailInit(accessToken);
            ResponseAssert.assertThat(invalidEmail)
                    .statusCodeIs(200)
                    .hasKey("responseObject.errorMessage")
                    .hasErrorMessage("Input validation failed [Invalid email address]")
                    .assertAll();
    }

}

