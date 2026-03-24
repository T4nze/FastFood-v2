import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrontPageUI extends JFrame {

    private double totalBill = 0.0;
    private JLabel statusLabel;

    public FrontPageUI() {
        // Window Setup
        setTitle("Jakaroo Fast Food");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JLabel header = new JLabel("Welcome to Jakaroo!", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        add(header, BorderLayout.NORTH);

        // Menu Panel (The 3 Foods)
        JPanel menuPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton burgerBtn = createFoodButton("Classic Burger", 5.99);
        JButton friesBtn = createFoodButton("Large Fries", 2.50);
        JButton sodaBtn = createFoodButton("Cold Soda", 1.50);

        menuPanel.add(burgerBtn);
        menuPanel.add(friesBtn);
        menuPanel.add(sodaBtn);

        add(menuPanel, BorderLayout.CENTER);

        // Footer / Total Display
        statusLabel = new JLabel("Total: $0.00", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        add(statusLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Helper method to create buttons and handle clicks
    private JButton createFoodButton(String name, double price) {
        JButton button = new JButton(name + " - $" + price);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalBill += price;
                statusLabel.setText(String.format("Added %s! Total: $%.2f", name, totalBill));
            }
        });

        return button;
    }

    public static void main(String[] args) {
        // Running on the Event Dispatch Thread (Best practice for Swing)
        SwingUtilities.invokeLater(() -> new FrontPageUI());
    }
}
