package endpoints;

public class Routes {

    public static String base_url = "https://staging.ipay.lk/apis/v3";

    public static String post_tokenEndpoint_url = "https://staging.ipay.lk/apis/token";

    public static String post_VerifyOtp_url = base_url+"/customer/registrationNew/verifyOtp";

    public static String post_CusRegistrationInit_url = base_url+"/customer/registrationNew/init";

    public static String post_regComplete_url = base_url+"/customer/registrationNew/complete";
}
