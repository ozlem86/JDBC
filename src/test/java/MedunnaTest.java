import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedunnaTest {
    @Test
    public void roomTest() throws SQLException {
        JdbcUtils.connectToDatabase("medunna.com","medunna_db","medunna_user",
                "medunna_pass_987");
        Statement statement= JdbcUtils.createStatement();
        String sql="select created_by from room";
        ResultSet resulSet= statement.executeQuery(sql);

        List<String> list= new ArrayList<>();

        while(resulSet.next()){
            list.add(resulSet.getString(1));
        }
        Assert.assertTrue("Nonexisting data",list.contains("john_doe"));

        JdbcUtils.closeConnectionAndStatement();


    }

}
