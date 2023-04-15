import java.sql.*;

public class Query03 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        Class.forName("org.postgresql.Driver");

        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Trabzon1461.");

        Statement st= con.createStatement();
        //String sql01= "create table worker(worker_id int, worker_name varchar(20),worker_salary int)";
         //st.execute(sql01);

        //String sql02=" alter table worker add worker_address varchar (50)";
        //st.execute(sql02);

        String sql03= "drop table worker";
        st.execute(sql03);
        con.close();
        st.close();






    }
}
