package endpoints.classes;

import java.util.Random;

public class RandomMobileNumberGenerator {

    public static String generateRandomMobileNumber() {
        int length = 10;
        StringBuilder mobileNumber = new StringBuilder();
        mobileNumber.append((1 + new Random().nextInt(9)));

        for (int i = 1; i < length; i++) {
            mobileNumber.append(new Random().nextInt(10));
        }

        return mobileNumber.toString();
    }
}
