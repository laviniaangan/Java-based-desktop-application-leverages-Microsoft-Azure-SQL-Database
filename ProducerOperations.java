import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProducerOperations {

    // Vizualizare producători
    public static JTable viewProducersInTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ProducatorID");
        model.addColumn("Denumire");
        model.addColumn("Tara Origine");
        model.addColumn("Adresa");

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            String query = "SELECT * FROM Producatori";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("ProducatorID"),
                        rs.getString("Denumire"),
                        rs.getString("TaraOrigine"),
                        rs.getString("Adresa")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JTable(model);
    }

    // Adăugare producător
    public static void addProducer(String denumire, String taraOrigine, String adresa) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO Producatori (Denumire, TaraOrigine, Adresa) VALUES (?, ?, ?)")) {

            pstmt.setString(1, denumire);
            pstmt.setString(2, taraOrigine);
            pstmt.setString(3, adresa);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Producător adăugat cu succes!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Modificare producător
    public static void updateProducer(int producatorID, String denumire, String taraOrigine, String adresa) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "UPDATE Producatori SET Denumire = ?, TaraOrigine = ?, Adresa = ? WHERE ProducatorID = ?")) {

            pstmt.setString(1, denumire);
            pstmt.setString(2, taraOrigine);
            pstmt.setString(3, adresa);
            pstmt.setInt(4, producatorID);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Producător modificat cu succes!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Ștergere producător
    public static void deleteProducer(int producatorID) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "DELETE FROM Producatori WHERE ProducatorID = ?")) {

            pstmt.setInt(1, producatorID);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Producător șters cu succes!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
