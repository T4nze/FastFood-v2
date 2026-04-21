import java.io.Serializable;

public class Drink extends Product implements Serializable {

    private static final long serialVersionUID = 1L;
    private String size;

    public Drink(String name, double price, String size) {
        super(name, price);
        this.size = size;
    }

    public void setSize(String soda) {
        this.size = size;
    }

    public String getSize(){
        return size;
    }

    public void sodaStatus(){
        IO.println("Name: " + name);
        IO.println("Price: " + price);
        IO.println("Size: " + size);
    }

    @Override
    public String toString() {
        return name + " (" + size + ")"+ " - $" + price;
    }

}