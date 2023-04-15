import java.sql.*;

public class Query01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");

        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Trabzon1461.");

        Statement stm= con.createStatement();
        String a = "select country_name from countries where region_id =1";

      ResultSet r1= stm.executeQuery(a);

      while(r1.next()){
          System.out.println(r1.getString(1));

          String b=" select country_id, country_name from countries where region_id >2 ";

      }

        String b=" select country_id, country_name from countries where region_id >2 ";

      ResultSet r4= stm.executeQuery(b);

      while(r4.next()){
          System.out.println(r4.getString(1)
                  +" "+r4.getString(2));

      }

      String c ="select * from companies where number_of_employees= (select min(number_of_employees) from companies)";

      ResultSet r3= stm.executeQuery(c);
      while(r3.next()){
          System.out.println(r3.getObject(1)+" "+ r3.getObject(2)+" "+r3.getObject(3));
      }
        con.close();
        stm.close();



    }





    }

