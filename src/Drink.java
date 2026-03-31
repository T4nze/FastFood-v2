public class Drink extends Product {

    private String size;

    public Drink(String name, double price, String size) {
        super(name, price);
        this.size = size;
    }

    public void setSoda(String soda) {
        this.size = size;
    }

    public String getSoda(){
        return size;
    }

    public void sodaStatus(){
        IO.println("Name: " + name);
        IO.println("Price: " + price);
        IO.println("Size: " + size);
    }

}