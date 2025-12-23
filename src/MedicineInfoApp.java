import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.util.*;

public class MedicineInfoApp {

    JFrame frame = new JFrame("💊 MedSearch Pro");
    JTextField searchField = new JTextField();
    JTextArea infoArea = new JTextArea();
    JButton searchButton = new JButton("Search");
    
    Color bgColor = new Color(24, 26, 31);
    Color cardColor = new Color(36, 40, 47);
    Color accentColor = new Color(0, 150, 136);
    Color textColor = new Color(230, 230, 230);

    HashMap<String, String> medicineData = new HashMap<>();

    public MedicineInfoApp() {
        loadDatabase("medicine_dataset.csv");

        frame.setSize(600, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(bgColor);
        frame.setLayout(new BorderLayout());

        // --- TOP SECTION (Header + Search) ---
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(bgColor);
        topPanel.setBorder(new EmptyBorder(20, 25, 10, 25));

        // Title
        JLabel title = new JLabel("Medicine Database");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Search Bar (Now smaller)
        JPanel searchContainer = new JPanel(new BorderLayout(10, 0));
        searchContainer.setBackground(bgColor);
        searchContainer.setMaximumSize(new Dimension(2000, 45)); // Limits the height
        searchContainer.setAlignmentX(Component.LEFT_ALIGNMENT);

        searchField.setBackground(cardColor);
        searchField.setForeground(Color.WHITE);
        searchField.setCaretColor(Color.WHITE);
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        // Reduced padding from 12 to 8 to make it shorter
        searchField.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(60, 60, 60), 1),
            new EmptyBorder(8, 10, 8, 10) 
        ));

        searchButton.setBackground(accentColor);
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("SansSerif", Font.BOLD, 13));
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchButton.setBorder(new EmptyBorder(0, 20, 0, 20));

        searchContainer.add(searchField, BorderLayout.CENTER);
        searchContainer.add(searchButton, BorderLayout.EAST);

        topPanel.add(title);
        topPanel.add(Box.createVerticalStrut(15)); // Space between title and search
        topPanel.add(searchContainer);

        // --- CENTER SECTION (Results) ---
        infoArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        infoArea.setBackground(cardColor);
        infoArea.setForeground(textColor);
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setMargin(new Insets(15, 15, 15, 15));
        
        JScrollPane scroll = new JScrollPane(infoArea);
        scroll.setBorder(new EmptyBorder(10, 25, 25, 25)); // Margin around the result box
        scroll.getViewport().setBackground(bgColor);

        // --- ASSEMBLE ---
        frame.add(topPanel, BorderLayout.NORTH); // Top stays small
        frame.add(scroll, BorderLayout.CENTER); // Results fill the rest

        // Search Logic
        ActionListener performSearch = e -> {
            String input = searchField.getText().toLowerCase().trim();
            if (medicineData.containsKey(input)) {
                infoArea.setText(medicineData.get(input));
                infoArea.setCaretPosition(0);
            } else {
                infoArea.setText("❌ No medicine found.\n\nTry: Acetocillin, Metovir, or Dextrophen");
            }
        };

        searchButton.addActionListener(performSearch);
        searchField.addActionListener(performSearch);

        frame.setVisible(true);
    }

    private void loadDatabase(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine(); 
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (data.length >= 6) {
                    String name = data[0].trim();
                    String key = name.toLowerCase();
                    String formattedInfo = 
                        "• NAME: " + name.toUpperCase() + "\n" +
                        "  Category:     " + data[1] + "\n" +
                        "  Form:         " + data[2] + " (" + data[3] + ")\n" +
                        "  Manufacturer: " + data[4].replace("\"", "") + "\n" +
                        "  Indication:   " + data[5] + "\n\n";
                    medicineData.put(key, medicineData.getOrDefault(key, "") + formattedInfo);
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load CSV file.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MedicineInfoApp::new);
    }
}