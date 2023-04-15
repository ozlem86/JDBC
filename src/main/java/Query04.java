import javax.xml.transform.Result;
import java.sql.*;

public class Query04 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Trabzon1461.");

        Statement st= con.createStatement();

       ResultSet rs= st.executeQuery("select*from countries" );
       ResultSetMetaData rmsd = rs.getMetaData();
        System.out.println(rmsd.getColumnName(1));
        System.out.println(rmsd.getTableName(2));
        con.close();
        st.close();





    }
}
