import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUtils {
    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;

    //1. Step: Create the connection
    public static Connection connectToDatabase(String hostName, String databaseName, String username, String password ) {

        try {//To handle the SQL exception we use try-catch block. "throws" keyword does not handle the exception.
            connection = DriverManager.getConnection("jdbc:postgresql://"+hostName+":5432/"+databaseName, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(connection !=null){
            System.out.println("Connection Success");
        } else{
            System.out.println("Connection Fail");
        }
        return connection;
    }

    //2. Step: Create Statement
    public static Statement createStatement() {

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return statement;
    }

    //3. Step: Execute Query--> query calistir
    public static boolean execute(String query) {
        boolean isExecute;
        try {
            isExecute = statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isExecute;
    }

    //executeQuery Methodu

    public  static  ResultSet executeQuery (String query){
        try{
            resultSet=statement.executeQuery(query);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return  resultSet;
    }

    //4. Step: Close Connection and Statement
    public static void closeConnectionAndStatement() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try{
            if(connection.isClosed() && statement.isClosed()){
                System.out.println("Connection and statement closed!");
            }else {
                System.out.println("Connection and statement NOT closed!");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    //Method to Drop The Table
    public static void dropTable(String tableName) {

        try {
            statement.execute("DROP TABLE " + tableName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    // table olusturan method

    public static void createTable(String tableName, String... columnName_dataType){

        StringBuilder columnName_dataValue =new StringBuilder();

        for(String w: columnName_dataType){

            columnName_dataValue.append(w).append(",");

        }

        columnName_dataValue.deleteCharAt(columnName_dataValue.length()-1);


        try {
            statement.execute("CREATE TABLE "+tableName+"("+columnName_dataValue+") ");
            System.out.println("Table "+tableName+" successfully created!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    //The method to put column data into a list(sutun degerlerini List icerisine alan metod)
    public static List<Object> getColumnList(String tableName, String columnName) {
        List<Object> list = new ArrayList<>();

                     //select     id            from      students
        String sql = "SELECT " + columnName + " FROM " + tableName;


        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                list.add(resultSet.getObject(1));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    // ExecuteUpdate methodu
    public static int executeUpdate(String query){
        int guncellenenSatirSayisi;
        try {
            guncellenenSatirSayisi=statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return guncellenenSatirSayisi;
    }


}

