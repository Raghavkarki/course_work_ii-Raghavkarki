package coursework2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionC {
    private String URl = "jdbc:mysql://localhost:3306";
    private String Database = "coursework2";
    private String username = "root";
    private String password = "";

    public Statement connection() {
        Connection con;
        Statement stmt = null;
        try {
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursework2", "root", "");
        	 con = DriverManager.getConnection(this.URl+"/"+this.Database,this.username, this.password);
        	stmt = con.createStatement();
            System.out.println("Connected");
            ;
        } catch (SQLException e1) {

            e1.printStackTrace();
        }
        return stmt;

    }

}
