import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Query05 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Trabzon1461.");

        Statement st = con.createStatement();
        //String sql01="insert into countries values ('SA', 'Arabistan', 7)";
        //st.executeUpdate(sql01);

        // String [] veri ={"insert into countries values ('TA', 'Tarabistan', 27)",
        //"insert into countries values ('GA', 'Garabistan', 17)",
        //"insert into countries values ('KA', 'Karabistan', 37)"};
        //int count=0;
        //for(String w: veri){
        //  count+= st.executeUpdate(w);
        //}
        String[] veri = {"insert into countries values ('TAI', 'ITarabistan', 20)",
                "insert into countries values ('GAI', 'IGarabistan', 10)",
                "insert into countries values ('KAI', 'IKarabistan', 30)"};

        for (String w :veri) {
            st.addBatch(w);
        }
        st.executeBatch();
        con.close();
        st.close();
    }
}

