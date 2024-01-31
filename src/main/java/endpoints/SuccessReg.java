package endpoints;

import org.json.simple.JSONObject;
import utils.utility;

public class SuccessReg {

    public static JSONObject successfulRegistration() {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        databaseConnector.fetchDataFromDatabase();

        String jsonFilePath = "src/test/resources/POST_cusRegSuccess.json";

        String email = databaseConnector.getEmail();
        String mobileNo = databaseConnector.getMobileNo();
        String deviceId = databaseConnector.getDeviceId();

        JSONObject requestObjectCom = new JSONObject();

        try {
            // Use the jsonReader method from the JsonReader utility class
            JSONObject entireJsonObjectComplete = new utility().jsonReader(jsonFilePath);

            requestObjectCom = (JSONObject) entireJsonObjectComplete.get("request");

            requestObjectCom.put("email", email);
            requestObjectCom.put("mobileNo", mobileNo);
            requestObjectCom.put("deviceId", deviceId);


            System.out.println(entireJsonObjectComplete);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestObjectCom;
    }
}