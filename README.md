🍔 Fast Food Manager
Command-line program for fast food ordering using Java. Includes SQL database support for transactions and uses object serialization.

📖 Introduction 
Order processing is implemented using terminal. Customers are able to add one item at a time into the order, change the basket and save the order in the SQL database.

Features:

Update your Order: Update your order and instantly get the total cost of your order.Database: Make transactions in SQL using JDBC

Serialization: Serialise the current state of your order into binary format (BLOB) so that you can restore your order later.

🏗️ Class Structure 
Main : Implements the menu and the database functionality (checkout, getLastID).

Product: Abstract class for all items on the menu.

Burger, Drink, French Fries: Products with specifications.

Database Connection: Makes a connection to SQL server that you have setup above.

🚀 Run 
Prepare Data: Set up the fastfood_data table by moving the database to your sql directory.

Compile: javac *.java

Execute: java Main

🖥️ User Interaction Example 
Customer Name: Enter the customer's name.

Choice: Choose an option from the product list (numbers from 1 to 6).Change: Enter the number (7) to view and delete any items from your order basket.


Done: Hit the number (8) to confirm the order for the purchased products.
