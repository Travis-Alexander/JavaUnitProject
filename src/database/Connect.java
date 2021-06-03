package database;

import com.company.Equipment;
import com.company.StreamEntry;
import com.company.Time;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;


public class Connect {

    static String dburl = "jdbc:sqlite:src/database/database.db";
    static Connection conn;

    private static void connect() throws SQLException {
        try {
            if (conn == null){
                conn = DriverManager.getConnection(dburl);
                System.out.println("Connection to SQLite has been established.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public static void addStreamEntry(StreamEntry entry) throws SQLException {
        connect();
        var statement = conn.createStatement();
        statement.executeUpdate(
                "INSERT INTO StreamEntry (" +
                        "Day, Time, Earnings" + ") " +
                        "VALUES ('" + entry.getStreamDay().replace("'", "''") +
                        "', '" + entry.getStreamTime().getFormattedTime().replace("'", "''") +
                        "', '" + entry.getEarnings() +
                        "') "
        );
    }

    public static ArrayList<StreamEntry> getStreamEntry() throws SQLException {
        connect();
        var statement = conn.createStatement();
        var data = statement.executeQuery(
                "SELECT * FROM StreamEntry"
        );

        ArrayList<StreamEntry> entries = new ArrayList<>();
        while (data.next()) {
            entries.add(new StreamEntry(
                    data.getString("Day").replace("''", "'"),
                    Time.parseTime(data.getString("Time")).getFormattedTime(),
                    data.getDouble("Earnings")
            ));
        }

        return entries;
    }

    public static void addEquipment(Equipment equipment) throws SQLException {
        connect();
        var statement = conn.createStatement();
        statement.executeUpdate(
                "INSERT INTO Equipment (" +
                        "Name, Cost" + ") " +
                        "VALUES ('" + equipment.getDesiredItem().toLowerCase().replace("'", "''") +
                        "', " + equipment.getDesiredItemCost() +
                        ") "
        );

    }

    public static void deleteEquipment(String equipmentName) throws SQLException {
        connect();
        var statement = conn.createStatement();
        statement.executeUpdate(
                "DELETE FROM Equipment WHERE name = '" + equipmentName.toLowerCase() + "'"
        );
    }

    public static ArrayList<Equipment> getEquipment() throws SQLException{
        connect();
        var statement = conn.createStatement();
        var data = statement.executeQuery(
                "SELECT * FROM Equipment"
        );
        ArrayList<Equipment> items = new ArrayList<>();
        while (data.next()) {
            items.add(new Equipment(
                    data.getString("Name"),
                    data.getDouble(("Cost"))
            ));
        }
        return items;
    }

    public static void updateDeduction(double amount) throws SQLException {
        connect();
        var statement = conn.createStatement();
        statement.executeUpdate(
                "UPDATE Savings SET amount = " + amount
        );
    }

    public static double getDeductions() throws SQLException {
        connect();
        var statement = conn.createStatement();
        var data = statement.executeQuery(
                "SELECT * FROM Savings"
        );

        double deductions = data.getDouble("amount");
        return deductions;
    }
}