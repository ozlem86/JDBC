public class Runner {
    public static void main(String[] args) {


        //1. Step: Create the connection
        //2. Step: Create Statement
        //3. Step: Execute Query
        //4. Step: Close Connection and Statement


        JdbcUtils.connectToDatabase("localhost","postgres","postgres","Trabzon1461.");

        JdbcUtils.createStatement();//2. Step: Create Statement


        //3. Step: Execute Query
        JdbcUtils.execute("CREATE TABLE workers( worker_id INT, worker_name VARCHAR(30), worker_salary SMALLINT)");
        JdbcUtils.dropTable("workers");

        JdbcUtils.createTable("School","classes VARCHAR()20", "teacher_name VARCHAR(20)","id INT");

        JdbcUtils.closeConnectionAndStatement(); //4. Step: Close Connection and Statement

    }
}

