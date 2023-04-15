import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CountriesTest {
     /*
        Given
          User connects to the database
        When
          User sends the query to get the region ids from "countries" table
        Then
          Verify that the number of region ids greater than 1 is 17.
        And
          User closes the connection
       */

    @Test
            public void countryTest() throws SQLException {

        //User connects to the database
        JdbcUtils.connectToDatabase("localhost","postgres","postgres","Trabzon1461.");
         JdbcUtils.createStatement();
        Statement statement= JdbcUtils.createStatement();

        //User sends the query to get the region ids from "countries" table
        String sql1 = "SELECT region_id FROM countries";



        ResultSet resultSet = statement.executeQuery(sql1);




        List<Integer> list = new ArrayList<>();
        while (resultSet.next()){
            list.add(resultSet.getInt(1));
        }

        System.out.println("list = " + list);

        //Verify that the number of region ids greater than 1 is 17.
        //birinci yol
        int counter = 0;
        for(int w:list){
            if(w>1){
                counter++;
            }

        }

        //ikinci yol

        /*List<Integer> idsGraterThan1 = new ArrayList<>();
        for(int w:list){
            if(w>1){
                idsGraterThan1.add(w);
            }
        }
        System.out.println("idsGraterThan1 = " + idsGraterThan1);

         */

        System.out.println("counter = " + counter);

        assertEquals(17,counter);

        JdbcUtils.closeConnectionAndStatement();

    }
}