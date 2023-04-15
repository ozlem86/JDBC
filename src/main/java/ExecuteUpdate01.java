import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Trabzon1461.");
        Statement statement = connection.createStatement();

        //1.Example: Update the number of employees to 16000
        // if the number of employees is less than the average number of employees
        String sql1 = "UPDATE companies1 SET number_of_employees = 16000 WHERE number_of_employees < (SELECT AVG(number_of_employees) FROM companies1)";
        int numOfRowsUpdated = statement.executeUpdate(sql1);//executeUpdate() method returns number of records updated.
        //kac tane satir uppdate edildi onun sayisini verir-->uppdate edilen satir sayisi
        System.out.println("numOfRowsUpdated = " + numOfRowsUpdated);

        String sql2 = "SELECT * FROM companies1";
        ResultSet resultSet = statement.executeQuery(sql2);

        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getInt(3));
        }

        connection.close();
        statement.close();
        resultSet.close();
    }
}
