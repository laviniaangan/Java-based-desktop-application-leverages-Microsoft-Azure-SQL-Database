import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Gestionează Datele");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Tab pentru Clienti
        tabbedPane.addTab("Clienti", createClientTab());

        // Tab pentru ProdusAlimentar
        tabbedPane.addTab("Produse Alimentare", createProdusAlimentarTab());

        // Tab pentru Producatori
        tabbedPane.addTab("Producatori", createProducatoriTab());

        // Tab pentru ClientiProduse
        tabbedPane.addTab("Clienti - Produse", createClientiProduseTab());

        // Tab pentru Producatori
        tabbedPane.addTab("Produse - Producatori", createProduseProducatoriTab());

        add(tabbedPane);
    }

    private JPanel createClientTab() {
        JPanel panel = new JPanel(new BorderLayout());

        // Table
        JTable table = ClientOperations.viewClientsInTable();
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Form pentru adăugare/modificare
        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        JTextField txtClientID = new JTextField();
        JTextField txtNume = new JTextField();
        JTextField txtPrenume = new JTextField();
        JTextField txtAdresa = new JTextField();

        formPanel.add(new JLabel("ID Client:"));
        formPanel.add(txtClientID);
        formPanel.add(new JLabel("Nume:"));
        formPanel.add(txtNume);
        formPanel.add(new JLabel("Prenume:"));
        formPanel.add(txtPrenume);
        formPanel.add(new JLabel("Adresa:"));
        formPanel.add(txtAdresa);

        panel.add(formPanel, BorderLayout.NORTH);

        // Butoane
        JPanel buttonPanel = new JPanel();
        JButton btnView = new JButton("Vizualizează");
        JButton btnAdd = new JButton("Adaugă");
        JButton btnUpdate = new JButton("Modifică");
        JButton btnDelete = new JButton("Șterge");

        buttonPanel.add(btnView);
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Acțiuni butoane
        btnView.addActionListener(e -> table.setModel(ClientOperations.viewClientsInTable().getModel()));

        btnAdd.addActionListener(e -> {
            String nume = txtNume.getText();
            String prenume = txtPrenume.getText();
            String adresa = txtAdresa.getText();
            ClientOperations.addClient(nume, prenume, adresa);
            table.setModel(ClientOperations.viewClientsInTable().getModel());
        });

        btnUpdate.addActionListener(e -> {
            int clientID = Integer.parseInt(txtClientID.getText());
            String nume = txtNume.getText();
            String prenume = txtPrenume.getText();
            String adresa = txtAdresa.getText();
            ClientOperations.updateClient(clientID, nume, prenume, adresa);
            table.setModel(ClientOperations.viewClientsInTable().getModel());
        });

        btnDelete.addActionListener(e -> {
            int clientID = Integer.parseInt(txtClientID.getText());
            ClientOperations.deleteClient(clientID);
            table.setModel(ClientOperations.viewClientsInTable().getModel());
        });

        return panel;
    }

    private JPanel createProdusAlimentarTab() {
        JPanel panel = new JPanel(new BorderLayout());

        JTable table = ProductOperations.viewProductsInTable();
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        JTextField txtProdusID = new JTextField();
        JTextField txtDenumire = new JTextField();
        JTextField txtDataProducere = new JTextField();
        JTextField txtDataExpirare = new JTextField();

        formPanel.add(new JLabel("ID Produs:"));
        formPanel.add(txtProdusID);
        formPanel.add(new JLabel("Denumire:"));
        formPanel.add(txtDenumire);
        formPanel.add(new JLabel("Data Producere:"));
        formPanel.add(txtDataProducere);
        formPanel.add(new JLabel("Data Expirare:"));
        formPanel.add(txtDataExpirare);

        panel.add(formPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton btnView = new JButton("Vizualizează");
        JButton btnAdd = new JButton("Adaugă");
        JButton btnUpdate = new JButton("Modifică");
        JButton btnDelete = new JButton("Șterge");

        buttonPanel.add(btnView);
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        btnView.addActionListener(e -> table.setModel(ProductOperations.viewProductsInTable().getModel()));

        btnAdd.addActionListener(e -> {
            String denumire = txtDenumire.getText();
            String dataProducere = txtDataProducere.getText();
            String dataExpirare = txtDataExpirare.getText();
            ProductOperations.addProduct(denumire, dataProducere, dataExpirare);
            table.setModel(ProductOperations.viewProductsInTable().getModel());
        });

        btnUpdate.addActionListener(e -> {
            int produsID = Integer.parseInt(txtProdusID.getText());
            String denumire = txtDenumire.getText();
            String dataProducere = txtDataProducere.getText();
            String dataExpirare = txtDataExpirare.getText();
            ProductOperations.updateProduct(produsID, denumire, dataProducere, dataExpirare);
            table.setModel(ProductOperations.viewProductsInTable().getModel());
        });

        btnDelete.addActionListener(e -> {
            int produsID = Integer.parseInt(txtProdusID.getText());
            ProductOperations.deleteProduct(produsID);
            table.setModel(ProductOperations.viewProductsInTable().getModel());
        });

        return panel;
    }

private JPanel createProducatoriTab() {
        JPanel panel = new JPanel(new BorderLayout());

        JTable table = ProducerOperations.viewProducersInTable();
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        JTextField txtProducerID = new JTextField();
        JTextField txtName = new JTextField();
        JTextField txtCountry = new JTextField();
        JTextField txtAddress = new JTextField();

        formPanel.add(new JLabel("ID Producător:"));
        formPanel.add(txtProducerID);
        formPanel.add(new JLabel("Nume:"));
        formPanel.add(txtName);
        formPanel.add(new JLabel("Țara de origine:"));
        formPanel.add(txtCountry);
        formPanel.add(new JLabel("Adresă:"));
        formPanel.add(txtAddress);

        panel.add(formPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton btnView = new JButton("Vizualizează");
        JButton btnAdd = new JButton("Adaugă");
        JButton btnUpdate = new JButton("Modifică");
        JButton btnDelete = new JButton("Șterge");

        buttonPanel.add(btnView);
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        btnView.addActionListener(e -> table.setModel(ProducerOperations.viewProducersInTable().getModel()));

        btnAdd.addActionListener(e -> {
            String name = txtName.getText();
            String country = txtCountry.getText();
            String address = txtAddress.getText();
            ProducerOperations.addProducer(name, country, address);
            table.setModel(ProducerOperations.viewProducersInTable().getModel());
        });

        btnUpdate.addActionListener(e -> {
            int producerID = Integer.parseInt(txtProducerID.getText());
            String name = txtName.getText();
            String country = txtCountry.getText();
            String address = txtAddress.getText();
            ProducerOperations.updateProducer(producerID, name, country, address);
            table.setModel(ProducerOperations.viewProducersInTable().getModel());
        });

        btnDelete.addActionListener(e -> {
            int producerID = Integer.parseInt(txtProducerID.getText());
            ProducerOperations.deleteProducer(producerID);
            table.setModel(ProducerOperations.viewProducersInTable().getModel());
        });

        return panel;
    }

    private JPanel createClientiProduseTab() {
        JPanel panel = new JPanel(new BorderLayout());

        JTable table = ClientProductOperations.viewClientProductsInTable();
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(2, 2));
        JTextField txtClientID = new JTextField();
        JTextField txtProdusID = new JTextField();

        formPanel.add(new JLabel("ID Client:"));
        formPanel.add(txtClientID);
        formPanel.add(new JLabel("ID Produs:"));
        formPanel.add(txtProdusID);

        panel.add(formPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton btnView = new JButton("Vizualizează");
        JButton btnAdd = new JButton("Adaugă");
        JButton btnDelete = new JButton("Șterge");

        buttonPanel.add(btnView);
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnDelete);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        btnView.addActionListener(e -> table.setModel(ClientProductOperations.viewClientProductsInTable().getModel()));

        btnAdd.addActionListener(e -> {
            int clientID = Integer.parseInt(txtClientID.getText());
            int produsID = Integer.parseInt(txtProdusID.getText());
            ClientProductOperations.addClientProduct(clientID, produsID);
            table.setModel(ClientProductOperations.viewClientProductsInTable().getModel());
        });

        btnDelete.addActionListener(e -> {
            int clientID = Integer.parseInt(txtClientID.getText());
            int produsID = Integer.parseInt(txtProdusID.getText());
            ClientProductOperations.deleteClientProduct(clientID, produsID);
            table.setModel(ClientProductOperations.viewClientProductsInTable().getModel());
        });

        return panel;
    }

    private JPanel createProduseProducatoriTab() {
        JPanel panel = new JPanel(new BorderLayout());

        JTable table = ProductProducerOperations.viewProductProducersInTable();
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(2, 2));
        JTextField txtProdusID = new JTextField();
        JTextField txtProducerID = new JTextField();

        formPanel.add(new JLabel("ID Produs:"));
        formPanel.add(txtProdusID);
        formPanel.add(new JLabel("ID Producător:"));
        formPanel.add(txtProducerID);

        panel.add(formPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton btnView = new JButton("Vizualizează");
        JButton btnAdd = new JButton("Adaugă");
        JButton btnDelete = new JButton("Șterge");

        buttonPanel.add(btnView);
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnDelete);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        btnView.addActionListener(e -> table.setModel(ProductProducerOperations.viewProductProducersInTable().getModel()));

        btnAdd.addActionListener(e -> {
            int produsID = Integer.parseInt(txtProdusID.getText());
            int producerID = Integer.parseInt(txtProducerID.getText());
            ProductProducerOperations.addProductProducer(produsID, producerID);
            table.setModel(ProductProducerOperations.viewProductProducersInTable().getModel());
        });

        btnDelete.addActionListener(e -> {
            int produsID = Integer.parseInt(txtProdusID.getText());
            int producerID = Integer.parseInt(txtProducerID.getText());
            ProductProducerOperations.deleteProductProducer(produsID, producerID);
            table.setModel(ProductProducerOperations.viewProductProducersInTable().getModel());
        });

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}

