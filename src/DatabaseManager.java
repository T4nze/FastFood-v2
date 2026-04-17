import java.io.*;
import java.sql.*;

public class DatabaseManager {

    // Helper method to turn any Object into a byte array
    private byte[] serializeObject(Object obj) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(obj);
            return baos.toByteArray();
        }
    }

    public void saveOrderWithBinary(String customer, double orderId, Object item, double price) {
        String sql = "INSERT INTO fastfood_data (customerName, orderID, _order, cost, BIN) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, customer);
            pstmt.setDouble(2, orderId);
            pstmt.setString(3, item.toString()); // Readable version
            pstmt.setDouble(4, price);

            // Serialize the object and set it as binary data
            byte[] binaryData = serializeObject(item);
            pstmt.setBytes(5, binaryData);

            pstmt.executeUpdate();
            System.out.println("Object successfully serialized and saved to BIN column!");

        }

        catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}