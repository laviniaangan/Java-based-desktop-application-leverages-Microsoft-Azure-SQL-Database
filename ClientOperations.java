import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ClientOperations {

    // Vizualizare date în JTable
    public static JTable viewClientsInTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ClientID");
        model.addColumn("Nume");
        model.addColumn("Prenume");
        model.addColumn("Adresa");

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            String query = "SELECT * FROM Clienti";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("ClientID"),
                        rs.getString("Nume"),
                        rs.getString("Prenume"),
                        rs.getString("Adresa")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JTable(model);
    }

    // Adăugare client
    public static void addClient(String nume, String prenume, String adresa) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO Clienti (Nume, Prenume, Adresa) VALUES (?, ?, ?)")) {

            pstmt.setString(1, nume);
            pstmt.setString(2, prenume);
            pstmt.setString(3, adresa);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Client adăugat cu succes!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Modificare client
    public static void updateClient(int clientID, String nume, String prenume, String adresa) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "UPDATE Clienti SET Nume = ?, Prenume = ?, Adresa = ? WHERE ClientID = ?")) {

            pstmt.setString(1, nume);
            pstmt.setString(2, prenume);
            pstmt.setString(3, adresa);
            pstmt.setInt(4, clientID);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Client modificat cu succes!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Ștergere client
    public static void deleteClient(int clientID) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "DELETE FROM Clienti WHERE ClientID = ?")) {

            pstmt.setInt(1, clientID);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Client șters cu succes!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
