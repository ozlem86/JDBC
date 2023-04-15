import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Trabzon1461.");
        Statement statement = connection.createStatement();

        //1.Example: Find the company and number_of_employees whose number_of_employees is the second highest from the companies table

        //1. Way: offset ile
        String sql1 = "SELECT company, number_of_employees FROM companies1 ORDER BY number_of_employees DESC OFFSET 1 ROW LIMIT 1";
        // OFFSET 1 ROW LIMIT 1" yerine fetch next 1 row only de kullanilabilir ayni sey
        ResultSet rs1 = statement.executeQuery(sql1);

        while (rs1.next()) {
            System.out.println(rs1.getString("company") + "--" + rs1.getInt("number_of_employees"));
        }

        //2. Way:Subquery kullanarak
        String sql2 = "  SELECT company, number_of_employees\n" +
                "  FROM companies1\n" +
                "  WHERE number_of_employees = (SELECT MAX(number_of_employees)\n" +
                "FROM companies1\n" +
                "WHERE number_of_employees < (SELECT MAX(number_of_employees)\n" +
                "FROM companies1))";

        //maximumdan kucuk olan maximum degeri ver dedik. bu da en buyuk ikinci degerdir.

        ResultSet rs2 = statement.executeQuery(sql2);
        while (rs2.next()) {
            System.out.println(rs2.getString(1) + "--" + rs2.getInt(2));
        }

        //2.Example: Find the company names and number of employees whose number of employees is less than the average number of employees
        String sql3 = "SELECT company, number_of_employees FROM companies1 WHERE number_of_employees < (SELECT AVG(number_of_employees) FROM companies1)";

        ResultSet rs3 = statement.executeQuery(sql3);
        while (rs3.next()) {
            System.out.println(rs3.getString(1) + "--" + rs3.getInt(2));
        }

        connection.close();
        statement.close();
        rs2.close();
        rs1.close();
        rs3.close();
    }
}

