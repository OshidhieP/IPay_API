package endpoints;

import java.sql.*;
import java.util.Properties;

public class DatabaseConnector {

    private static String mobileNo;
    private static String deviceId;
    private static String otp;
    private static String email;
    public String getMobileNo() {
        return mobileNo;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getOtp() {
        return otp;
    }

    public String getEmail() {
        return email;
    }

    public void fetchDataFromDatabase() {
        // Load the database configuration from the properties file
        Properties properties = new Properties();
        try {
            properties.load(DatabaseConnector.class.getClassLoader().getResourceAsStream("db-config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // Database connection parameters
        String dbMainUrl = properties.getProperty("dbMainUrl");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        // SSL-related properties
        String requireSSL = properties.getProperty("requireSSL");
        String useSSL = properties.getProperty("useSSL");
        String clientCertificateKeyStoreUrl = properties.getProperty("clientCertificateKeyStoreUrl");
        String clientCertificateKeyStorePassword = properties.getProperty("clientCertificateKeyStorePassword");
        String trustCertificateKeyStoreUrl = properties.getProperty("trustCertificateKeyStoreUrl");

        // Create a Properties object for additional connection properties
        Properties connectionProperties = new Properties();
        connectionProperties.setProperty("user", user);
        connectionProperties.setProperty("password", password);
        connectionProperties.setProperty("requireSSL", requireSSL);
        connectionProperties.setProperty("useSSL", useSSL);
        connectionProperties.setProperty("clientCertificateKeyStoreUrl", clientCertificateKeyStoreUrl);
        connectionProperties.setProperty("clientCertificateKeyStorePassword", clientCertificateKeyStorePassword);
        connectionProperties.setProperty("trustCertificateKeyStoreUrl", trustCertificateKeyStoreUrl);

        // Load the MySQL JDBC driver (not required for JDBC 4.0+)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // SQL query
        String sqlQuery = "SELECT mobile_no, otp, device_id, email " +
                "FROM user_registration_otp " +
                "ORDER BY created_date DESC " +
                "LIMIT 1;";

        try (
                Connection connection = DriverManager.getConnection(dbMainUrl, connectionProperties);
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            // Process the result set
            if (resultSet.next()) {
                // Retrieve values from the result set
                mobileNo = resultSet.getString("mobile_no");
                otp = resultSet.getString("otp");
                deviceId = resultSet.getString("device_id");
                email = resultSet.getString("email");

                // Check if OTP retrieval is successful
                if (otp != null && !otp.isEmpty()) {
//                    System.out.println("Mobile Number: " + mobileNo);
//                    System.out.println("OTP: " + otp);
//                    System.out.println("Device ID: " + deviceId);
                } else {
                    System.out.println("No OTP found for verification.");
                }
            } else {
                System.out.println("No records found in the result set.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
