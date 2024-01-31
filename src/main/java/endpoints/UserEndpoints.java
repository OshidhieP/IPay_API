package endpoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import java.io.File;

public class UserEndpoints {

    public static Response createNewUser(String accessToken) {

        File payload = new File("src/test/resources/POST_cusRegistration.json");

        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .post(Routes.post_CusRegistrationInit_url);
    }

    public static Response verifyToken(String accessToken) {

        JSONObject payload = VerifyOTP.successfulOtpVerify();

        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .post(Routes.post_VerifyOtp_url);
    }

    public static Response cusRegisterSuccess(String accessToken){

        JSONObject payload = SuccessReg.successfulRegistration();

        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .post(Routes.post_regComplete_url);
    }
}
