import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ClientProductOperations {

    // Vizualizare relații Client-Produs cu datele asociate
    public static JTable viewClientProductsInTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nume Client");
        model.addColumn("Denumire Produs");

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Interogare pentru a extrage datele din tabelele asociate
            String query = "SELECT c.Nume AS NumeClient, p.Denumire AS DenumireProdus " +
                           "FROM Clienti_ProdusAlimentar cp " +
                           "JOIN Clienti c ON cp.ClientID = c.ClientID " +
                           "JOIN ProdusAlimentar p ON cp.ProdusID = p.ProdusID";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("NumeClient"),
                        rs.getString("DenumireProdus")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JTable(model);
    }

    // Adăugare relație
    public static void addClientProduct(int ClientID, int ProdusID) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO Clienti_ProdusAlimentar (ClientID, ProdusID) VALUES (?, ?)")) {

            pstmt.setInt(1, ClientID);
            pstmt.setInt(2, ProdusID);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Relație Client-Produs adăugată cu succes!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Ștergere relație
    public static void deleteClientProduct(int ClientID, int ProdusID) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "DELETE FROM Clienti_ProdusAlimentar WHERE ClientID = ? AND ProdusID = ?")) {

            pstmt.setInt(1, ClientID);
            pstmt.setInt(2, ProdusID);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Relație Client-Produs ștearsă cu succes!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
