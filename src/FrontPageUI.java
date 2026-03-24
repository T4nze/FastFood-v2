import javax.swing.*;

public class FrontPageUI extends JFrame {

    private JPanel panel1;
    private JTextField textField1;

    public FrontPageUI() {
        setTitle("Jakaroo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {

        new FrontPageUI();

    }

}
