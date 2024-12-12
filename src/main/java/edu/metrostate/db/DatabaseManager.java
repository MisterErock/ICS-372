package edu.metrostate.db;

import edu.metrostate.model.Appliance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private List<Appliance> appliances;

    public DatabaseManager() {
        this.createTables();
        this.appliances = new ArrayList<>();
    }



    private void createTables() {
        String sql = "CREATE TABLE IF NOT EXISTS appliances (" +
                "id TEXT PRIMARY KEY," +
                "type TEXT NOT NULL," +
                "model TEXT NOT NULL," +
                "purchase_date TEXT NOT NULL," +
                "last_service_date TEXT NOT NULL," +
                "next_service_date TEXT NOT NULL," +
                "status TEXT NOT NULL)";

        try (
                // Ensure the database file is created
                Connection conn = DatabaseConfig.getConnection();
                Statement stmt = conn.createStatement();
        ) {
            System.out.println("Database file location: " + new java.io.File(DatabaseConfig.databaseName).getAbsolutePath());  // Log the absolute path
            stmt.execute(sql);
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    public void saveAppliance(Appliance appliance) {
        String sql = "INSERT INTO appliances (id, type, model, purchase_date, last_service_date, next_service_date, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection conn = DatabaseConfig.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, appliance.getApplianceId());
            pstmt.setString(2, appliance.getApplianceType());
            pstmt.setString(3, appliance.getModel());
            pstmt.setString(4, DATE_FORMAT.format(appliance.getPurchaseDate()));
            pstmt.setString(5, DATE_FORMAT.format(appliance.getLastServiceDate()));
            pstmt.setString(6, DATE_FORMAT.format(appliance.getNextServiceDueDate()));
            pstmt.setString(7, appliance.getStatus());
            pstmt.executeUpdate();
            System.out.println("Appliance saved successfully");
        } catch (SQLException e) {
            System.out.println("Error saving appliance: " + e.getMessage());
        }

    }

    public List<Appliance> loadAppliances() {
        List<Appliance> appliances = new ArrayList();
        String sql = "SELECT * FROM appliances";

        try (
                Connection conn = DatabaseConfig.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            while(rs.next()) {
                try {
                    Appliance appliance = new Appliance(rs.getString("type"), rs.getString("model"), DATE_FORMAT.parse(rs.getString("purchase_date")));
                    appliance.setApplianceId(rs.getString("id"));
                    appliance.setStatus(rs.getString("status"));
                    appliance.setLastServiceDate(DATE_FORMAT.parse(rs.getString("last_service_date")));
                    appliance.setNextServiceDueDate(DATE_FORMAT.parse(rs.getString("next_service_date")));
                    appliances.add(appliance);
                } catch (Exception e) {
                    System.out.println("Error parsing appliance data: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error loading appliances: " + e.getMessage());
        }

        return appliances;
    }

    public void updateAppliance(Appliance updatedAppliance) {
        if (this.appliances == null) {
            throw new IllegalStateException("Appliances list is not initialized.");
        }
        for (int i = 0; i < this.appliances.size(); i++) {
            Appliance appliance = this.appliances.get(i);
            if (appliance.getApplianceId().equals(updatedAppliance.getApplianceId())) {
                this.appliances.set(i, updatedAppliance);
                System.out.println("Appliance updated successfully.");
                return;
            }
        }
        throw new RuntimeException("Appliance with ID " + updatedAppliance.getApplianceId() + " not found.");
    }

    // Method to add appliances (for initial data population)
    public void addAppliance(Appliance appliance) {
        if (this.appliances == null) {
            this.appliances = new ArrayList<>();
        }
        this.appliances.add(appliance);
    }



}

