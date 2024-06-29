package Classes;

import org.sqlite.SQLiteConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class DButils {
        public static Connection c;

        public static void setConnection() {
                try{
                        Class.forName("org.sqlite.JDBC");
                        c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\nanci\\IdeaProjects\\amazon\\database.db");
                }catch (Exception e) {
                        e.printStackTrace();
                }
        }

        public static void closeConnection() {
                try{
                        c.close();
                }catch(Exception e) {
                        e.printStackTrace();
                }
        }


}
