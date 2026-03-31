public class Fries extends Product {

    private String size;

    public Fries(String name, double price, String size) {
        super(name, price);
        this.size = size;
    }

    public void setSize(String size){
        this.size = size;
    }

    public String getSize(){
        return size;
    }

    public void friesStatus(){
        IO.println("Name: " + name);
        IO.println("Price: " + price);
        IO.println("Size: " + size);
    }

}
