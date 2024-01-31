package endpoints;

import org.json.simple.JSONObject;
import utils.utility;

public class VerifyOTP {
    public static JSONObject successfulOtpVerify() {

        DatabaseConnector databaseConnector = new DatabaseConnector();
        databaseConnector.fetchDataFromDatabase();

        String jsonFilePath = "src/test/resources/POST_verifyOtpCode.json";

        String otp = databaseConnector.getOtp();
        String mobileNo = databaseConnector.getMobileNo();
        String deviceId = databaseConnector.getDeviceId();

        JSONObject requestObject = null;

        try {
            // Use the jsonReader method from the JsonReader utility class
            JSONObject entireJsonObject = new utility().jsonReader(jsonFilePath);

            requestObject = (JSONObject) entireJsonObject.get("request");
            requestObject.put("otp", otp);
            requestObject.put("mobileNo", mobileNo);
            requestObject.put("deviceId", deviceId);

            System.out.println(entireJsonObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestObject;
    }
}
