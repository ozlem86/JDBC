import java.sql.*;

public class Query06 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Trabzon1461.");

        //Statement st= con.createStatement();

       PreparedStatement ps =  con.prepareStatement("insert into countries values (?, ?, ?)");
       ps.setString(1,"KKK");
       ps.setString(2,"Awagahhh");
       ps.setInt(3,54);
       ps.executeUpdate();
       ps.close();


    }
}
