package utils;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils extends Validations {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    @BeforeClass
    public static void createConnection() {
        String url;
        String username;
        String password;
        switch (ConfigReader.getProperty("environment")) {
            case "dev":
                url = ConfigReader.getProperty("devUrl");
                username = ConfigReader.getProperty("dbUsername");
                password = ConfigReader.getProperty("dbPassword");

                try {
                    connection = DriverManager.getConnection(url, username, password);
                    if (connection != null) {
                        DatabaseMetaData dm = (DatabaseMetaData) connection.getMetaData();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "preprod":
                url = ConfigReader.getProperty("preprodUrl");
                username = ConfigReader.getProperty("dbUsername");
                password = ConfigReader.getProperty("dbPassword");

                try {
                    connection = DriverManager.getConnection(url, username, password);
                    if (connection != null) {
                        DatabaseMetaData dm = (DatabaseMetaData) connection.getMetaData();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "uat":
                url = ConfigReader.getProperty("uatUrl");
                username = ConfigReader.getProperty("dbUsername");
                password = ConfigReader.getProperty("dbPassword");

                try {
                    connection = DriverManager.getConnection(url, username, password);
                    if (connection != null) {
                        DatabaseMetaData dm = (DatabaseMetaData) connection.getMetaData();
                    }
                } catch (SQLException e) {
                    System.out.println("connection is null!!");
                    e.printStackTrace();
                }
                break;
        }
    }

    public static void createConnection_SMS() throws SQLException {
        String url;
        String username;
        String password;
        switch (ConfigReader.getProperty("environment")) {
            case "dev":
                url = ConfigReader.getProperty("devSmsUrl");
                username = ConfigReader.getProperty("dbUsername");
                password = ConfigReader.getProperty("dbPassword");

                try {
                    connection = DriverManager.getConnection(url, username, password);
                    if (connection != null) {
                        DatabaseMetaData dm = (DatabaseMetaData) connection.getMetaData();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "preprod":
            case "uat":

                url = ConfigReader.getProperty("preprodSmsUrl");
                username = ConfigReader.getProperty("dbUsername");
                password = ConfigReader.getProperty("dbPassword");

                try {
                    connection = DriverManager.getConnection(url, username, password);
                    if (connection != null) {
                        DatabaseMetaData dm = (DatabaseMetaData) connection.getMetaData();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;


        }
    }

    public static void executeQuery(String query) {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //    used to close the connectivity
    @AfterSuite
    public static void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Statement getStatement() {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }


    //Use this to get the ResutSet object
    public static ResultSet getResultset() {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    // This method returns the number fo row in a table in the database
    public static int getRowCount() throws Exception {
        resultSet.last();
        int rowCount = resultSet.getRow();
        return rowCount;
    }

    /*
     * @return returns a single cell value. If the results in multiple rows and/or
     *         columns of data, only first column of the first row will be returned.
     *         The rest of the data will be ignored


     */

    public static Object getCellValue(String query) {
        return getQueryResultList(query).get(0).get(0);
    }
    /*
     * @return returns a list of Strings which represent a row of data. If the query
     *         results in multiple rows and/or columns of data, only first row will
     *         be returned. The rest of the data will be ignored


     */

    public static List<Object> getRowList(String query, int column) {

        return getQueryResultList(query).get(column);

    }
    /*
     * @return returns a map which represent a row of data where key is the column
     *         name. If the query results in multiple rows and/or columns of data,
     *         only first row will be returned. The rest of the data will be ignored


     */

    public static Map<String, Object> getRowMap(String query) {

        return getQueryResultMap(query).get(0);
    }
    /*
     * @return returns query result in a list of lists where outer list represents
     *         collection of rows and inner lists represent a single row


     */


    public static List<List<Object>> getQueryResultList(String query) {
        executeQuery(query);
        List<List<Object>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                List<Object> row = new ArrayList<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    row.add(resultSet.getObject(i));
                }
                rowList.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowList;
    }
    /*
     * @return list of values of a single column from the result set


     */

    public static List<String> getColumnData(String query, String column) {

        executeQuery(query);
        List<String> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                rowList.add(resultSet.getString(column));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowList;
    }

    /*
     * @return returns query result in a list of maps where the list represents
     *         collection of rows and a map represents represent a single row with
     *         key being the column name


     */


    public static List<Map<String, Object>> getQueryResultMap(String query) {
        executeQuery(query);
        List<Map<String, Object>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                Map<String, Object> colNameValueMap = new HashMap<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    colNameValueMap.put(rsmd.getColumnName(i), resultSet.getObject(i));
                }
                rowList.add(colNameValueMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowList;
    }
    /*
     * @return List of columns returned in result set


     */


    public static List<String> getColumnNames(String query) {
        executeQuery(query);
        List<String> columns = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columns.add(rsmd.getColumnName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columns;
    }

    public static void getConnection() throws SQLException, ClassNotFoundException {
        DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://10.36.0.224;database=ESSNET;encrypt=true;trustServerCertificate=true;";
        Connection conn = DriverManager.getConnection(dbURL);
        if (conn != null) {
            System.out.println("Connection Established successfully");
        }
    }

}



