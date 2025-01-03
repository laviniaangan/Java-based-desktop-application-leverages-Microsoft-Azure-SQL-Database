import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductOperations {

    // Vizualizare produse
    public static JTable viewProductsInTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ProdusID");
        model.addColumn("Denumire");
        model.addColumn("Data Producere");
        model.addColumn("Data Expirare");

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            String query = "SELECT * FROM ProdusAlimentar";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("ProdusID"),
                        rs.getString("Denumire"),
                        rs.getDate("DataProducere"),
                        rs.getDate("DataExpirare")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JTable(model);
    }

    // Adăugare produs
    public static void addProduct(String denumire, String dataProducere, String dataExpirare) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO ProdusAlimentar (Denumire, DataProducere, DataExpirare) VALUES (?, ?, ?)")) {

            pstmt.setString(1, denumire);
            pstmt.setDate(2, java.sql.Date.valueOf(dataProducere));
            pstmt.setDate(3, java.sql.Date.valueOf(dataExpirare));
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produs adăugat cu succes!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Modificare produs
    public static void updateProduct(int produsID, String denumire, String dataProducere, String dataExpirare) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "UPDATE ProdusAlimentar SET Denumire = ?, DataProducere = ?, DataExpirare = ? WHERE ProdusID = ?")) {

            pstmt.setString(1, denumire);
            pstmt.setDate(2, java.sql.Date.valueOf(dataProducere));
            pstmt.setDate(3, java.sql.Date.valueOf(dataExpirare));
            pstmt.setInt(4, produsID);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produs modificat cu succes!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Ștergere produs
    public static void deleteProduct(int produsID) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "DELETE FROM ProdusAlimentar WHERE ProdusID = ?")) {

            pstmt.setInt(1, produsID);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produs șters cu succes!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
