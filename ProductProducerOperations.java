import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductProducerOperations {

    // Vizualizare relații Producător-Produs
    public static JTable viewProductProducersInTable() {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Nume Producător");
    model.addColumn("Denumire Produs");

    try (Connection conn = DatabaseConnection.getConnection();
         Statement stmt = conn.createStatement()) {

        String query = "SELECT p.Denumire AS NumeProducator, pr.Denumire AS NumeProdus " +
                       "FROM ProdusAlimentar_Producatori pp " +
                       "INNER JOIN Producatori p ON pp.ProducatorID = p.ProducatorID " +
                       "INNER JOIN ProdusAlimentar pr ON pp.ProdusID = pr.ProdusID";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("NumeProducator"),
                rs.getString("NumeProdus")
            });
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return new JTable(model);
}

    // Adăugare relație
    public static void addProductProducer(int ProducatorID, int ProdusID) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO ProdusAlimentar_Producatori (ProducatorID, ProdusID) VALUES (?, ?)")) {

            pstmt.setInt(1, ProducatorID);
            pstmt.setInt(2, ProdusID);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Relație Producător-Produs adăugată cu succes!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Ștergere relație
    public static void deleteProductProducer(int ProducatorID, int ProdusID) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "DELETE FROM ProdusAlimentar_Producatori WHERE ProducatorID = ? AND ProdusID = ?")) {

            pstmt.setInt(1, ProducatorID);
            pstmt.setInt(2, ProdusID);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Relație Producător-Produs ștearsă cu succes!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
