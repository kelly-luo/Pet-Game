package Database;

import Pet.Pet;
import World.Owner;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KellyL (17985065)
 */
public class VirtualPetDatabase {

    private static final String URL = "jdbc:derby://localhost:1527/VirtualPet; create=true";  //url of the embedded DB host
    private static final String USER_NAME = "virtualpet";  // your DB username
    private static final String PASSWORD = "virtualpetpass";   // your DB password
    
    private static Statement statement = null;
    private Connection conn;
    private static VirtualPetDatabase database;
    
    private VirtualPetDatabase() {
        try {
            this.conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            this.createTables();
        } catch (SQLException ex) {
            Logger.getLogger(VirtualPetDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static synchronized VirtualPetDatabase getDatabase() {
        try {
            if (database == null) {
                database = new VirtualPetDatabase();
                System.out.println("   VIRTUAL PET DATABASE CONNECTED....");
                
            }else if(database.getConnection().isClosed()){
                database = new VirtualPetDatabase();
                System.out.println("   VIRTUAL PET DATABASE CONNECTED....");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VirtualPetDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return database;
    }
    
    @Override //Override the Object clone method to prevent cloning
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void closeDatabase() {
        if (database != null) {
            try {
                System.out.println("Virtual Pet Database safely closed!");
                database.statement.close();
                database.getConnection().close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public Connection getConnection(){
        return this.conn;
    }
    
    public void createTables() {
        this.createOwnerTable();
        this.createPetTable();
    }

    public void createOwnerTable(){
        try {
            this.statement = getConnection().createStatement();
            if (this.checkExistedTable("Owner") == false) {
                this.statement.addBatch("CREATE TABLE Owner (Owner_Name VARCHAR(15),Owner_Gender CHAR(1),Pet_Name VARCHAR(15))");
                this.statement.addBatch("INSERT INTO Owner VALUES ('Akansha','F','Maya'),\n" + "('Kelly', 'F','Cherry')");
                this.statement.executeBatch();
            } else
                System.out.println("Owner Table is loaded.");

        } catch (SQLException ex) {
            Logger.getLogger(VirtualPetDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createPetTable() {
        try {
            if(this.checkExistedTable("Pet") == false){
                this.statement.addBatch("CREATE TABLE Pet (Pet_Name VARCHAR(15),Pet_Age INT,Pet_Happiness INT,Pet_Hungry INT,Pet_Fatigue INT)");
                this.statement.addBatch("INSERT INTO Pet values ('Maya', 1, 10, 5, 20)");
                this.statement.addBatch("INSERT INTO Pet values ('Cherry', 0, 10, 7, 20)");
                this.statement.executeBatch();
            }else
                System.out.println("Pet Table is loaded.");
        } catch (SQLException ex) {
            Logger.getLogger(VirtualPetDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    // if table exists then drop the table
    public boolean checkExistedTable(String name) {
        boolean exists = false;
        try {
            DatabaseMetaData dbmd = getConnection().getMetaData();
            String[] types = {"TABLE"};
            ResultSet rs = dbmd.getTables(null, null, null, types);
            Statement statement = getConnection().createStatement();
            while (rs.next()) {
                String table_name = rs.getString("TABLE_NAME");
                if (table_name.equalsIgnoreCase(name)) {
                    exists = true;
                }
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(VirtualPetDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exists;

    }
    
    public void addToTable(String ownerName, String gender, String dogName){
        try {
            System.out.println("Added owner and pet information into database");
            this.statement.executeUpdate("INSERT INTO Owner VALUES('"+ ownerName + "','"+ gender + "','" + dogName +"')");
            this.statement.executeUpdate("INSERT INTO Pet VALUES('"+ dogName + "',"+ 0 + "," + 0 +"," + 0 + "," + 0 + ")");
        } catch (SQLException ex) {
            Logger.getLogger(VirtualPetDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
    
}
