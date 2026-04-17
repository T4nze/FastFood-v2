import java.sql.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

private byte[] serializeObject(ArrayList<Object> cart) {

    try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
         ObjectOutputStream oos = new ObjectOutputStream(baos)) {
        oos.writeObject(cart);
        return baos.toByteArray();
    }

    catch (IOException e) {
        throw new RuntimeException(e);
    }

}

public void checkout(String customerName, double orderID, ArrayList<Object> cart, double total) {

    String sql = "INSERT INTO fastfood_data (customerName, orderID, _order, cost, BIN) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, customerName);
        pstmt.setDouble(2, orderID);

        // Readable data
        StringJoiner sj = new StringJoiner(" | ");
        for (Object o : cart) {
            sj.add(o.toString());
        }
        pstmt.setString(3, sj.toString());

        // Total Cost
        pstmt.setDouble(4, total);

        // Serialization of the array
        byte[] binaryData = serializeObject(cart);
        pstmt.setBytes(5, binaryData);

        pstmt.executeUpdate();
        System.out.println("Checkout complete! Database updated.");

    }

    catch (Exception e) {
        e.printStackTrace();
    }

}

public int getLastID() {
    String sql = "SELECT MAX(orderID) FROM fastfood_data";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        if (rs.next()) {
            return rs.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}

void main() throws IOException {

    Burger cheeseBurger = new Burger("Cheeseburger", 4.5, "Cheddar, Patty, Pickles, Lettuce, Tomato, Sauce");
    Burger hamBurger = new Burger("Hamburger", 5, "Onion, Patty, Pickles, Lettuce, Tomato, Sauce");

    Drink cocaCola = new Drink("Coca-Cola", 2, "Classic");
    Drink cocaColaLarge = new Drink("Coca-Cola", 3, "Large");

    Fries fries = new Fries("Fries", 3, "Classic");
    Fries loadedFries = new Fries("Loaded Fries", 4, "Large with Cheese");

    ArrayList<Object> cart = new ArrayList<>();
    double totalCost = 0;
    double id = getLastID() + 1;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    boolean bit = true;
    boolean bit2 = true;
    boolean wantToExit = false;

    while (bit2 == true) {

        String name = null;
        IO.println("\nName: ");
        name = br.readLine();

        while (bit == true) {

            IO.println("\n---------MENU---------");
            IO.println("1. Cheeseburger: $4.5");
            IO.println("2. Hamburger: $5");
            IO.println("3. CocaCola: $2");
            IO.println("4. CocaCola Large: $3");
            IO.println("5. Fries: $3");
            IO.println("6. Loaded Fries: $4");
            IO.println("7. Remove Item");
            IO.println("8. Done");
            IO.println("9. Exit");
            IO.println("\nEnter a value to add to cart: ");
            String choice = br.readLine();

            switch (choice) {
                case "1":
                    cart.add(cheeseBurger);
                    IO.println("\nItem added to cart!\n");
                    break;

                case "2":
                    cart.add(hamBurger);
                    IO.println("\nItem added to cart!\n");
                    break;

                case "3":
                    cart.add(cocaCola);
                    IO.println("\nItem added to cart!\n");
                    break;

                case "4":
                    cart.add(cocaColaLarge);
                    IO.println("\nItem added to cart!\n");
                    break;

                case "5":
                    cart.add(fries);
                    IO.println("\nItem added to cart!\n");
                    break;

                case "6":
                    cart.add(loadedFries);
                    IO.println("\nItem added to cart!\n");
                    break;

                case "7":
                    IO.println("\n---------CART---------");
                    for (int i = 0; i < cart.size(); i++) {
                        Object item = cart.get(i);
                        int displayIndex = i + 1;

                        if (item instanceof Burger) {
                            Burger b = (Burger) item;
                            IO.println(displayIndex + " - " + b.getName() + " $" + b.getPrice());
                        }

                        if (item instanceof Drink) {
                            Drink s = (Drink) item;
                            IO.println(displayIndex + " - " + s.getName() + " $" + s.getPrice());
                        }

                        if (item instanceof Fries) {
                            Fries f = (Fries) item;
                            IO.println(displayIndex + " - " + f.getName() + " $" + f.getPrice());
                        }
                    }

                    IO.println("Select a number to delete: ");
                    String delete = br.readLine();
                    int del = Integer.parseInt(delete);

                    if (del > 0 && del <= cart.size()) {
                        // Subtract 1 to get the correct 0-based index
                        Object removed = cart.remove(del - 1);
                        System.out.println("Successfully removed " + removed.toString());
                    }
                    break;

                case "8":
                    bit = false;
                    break;

                case "9":
                    bit2 = false;
                    bit = false;
                    wantToExit = true;
                    break;

                default:
                    IO.println("Invalid input!");
                    break;
            }

        }

        if (!wantToExit) {

            for (Object item : cart) {
                if (item instanceof Burger) totalCost += ((Burger) item).getPrice();
                else if (item instanceof Drink) totalCost += ((Drink) item).getPrice();
                else if (item instanceof Fries) totalCost += ((Fries) item).getPrice();
            }

            checkout(name, id, cart, totalCost);

            id++;
            bit = true;
            totalCost = 0;
            cart.clear();

        }

    }

}