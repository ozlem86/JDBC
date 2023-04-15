import java.sql.*;

public class CallableStatement01 {

    // JDBC relational database uzerinde baglanti ve veri manipulasyonu icin kullanilir.
    /*
    Javada methodlarin return type olsa da olmasada method olarak adlandirilir
    SQL de ise data return ediyorsa "function", etmiyorsa "procedure" olarak adlandirilir

    CallableStatement ile function cagirmayi parametrelendirecegiz.

     */


    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Trabzon1461.");
        Statement statement = connection.createStatement();

        //1.Example: Create a function which uses 2 parameters and return the sum of the parameters
        //1st Step: Type code to create function--function kodunu yaz
        String sql1 = "CREATE OR REPLACE FUNCTION additionF(x NUMERIC, y NUMERIC) RETURNS NUMERIC LANGUAGE plpgsql AS $$ BEGIN RETURN x+y; END $$";
        //create or replace function, returns, language plpgsql, as $$ begin return  end $$--->
        // bunlar textin degismezleridir.

        //2nd Step: Execute the function--functioni calistir
        statement.execute(sql1);

        //3rd Step: Prepare the Callable Statement--fonctioni cagir
        CallableStatement cs1 = connection.prepareCall("{? = call additionF(?,?)}");
        //ilk parametre return type

        //4th Step:Use registerOutParameter() method for result container and use setInt(), setString ... for parameters
        //return icin registerOutParameter() methodunu, parametlereler icin ise
        //set() methodlarini uygula.
        cs1.registerOutParameter(1, Types.NUMERIC);
        cs1.setInt(2, 9);
        cs1.setInt(3, 4);

        //5th Step: Execute() callable statement--execute methodu ile CallableStatementi calistir
        cs1.execute();

        //6th Step: Print the result--sonucu cagirmak icin return data type a gore
        System.out.println(cs1.getObject(1));
        //sadece 1 sutun gelecegi icin 1 yazdik




        //2.Example: Create a function which calculates the volume of cone
        //koninin hacmini hesaplayan bir function

        String sql2 = "CREATE OR REPLACE FUNCTION volumeOfCone(r NUMERIC, h NUMERIC) RETURNS NUMERIC LANGUAGE plpgsql AS $$ BEGIN RETURN r*r*h*3.14/3; END $$";
        statement.execute(sql2);
        CallableStatement cs2 = connection.prepareCall("{? = call volumeOfCone(?,?)}");
        cs2.registerOutParameter(1, Types.NUMERIC);
        cs2.setInt(2, 2);
        cs2.setInt(3, 6);
        cs2.execute();
        System.out.println(cs2.getObject(1));
        System.out.printf("%.2f", cs2.getObject(1));

        connection.close();
        statement.close();
    }
}
