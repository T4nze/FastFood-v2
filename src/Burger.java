import java.io.Serializable;

public class Burger extends Product implements Serializable {

    private static final long serialVersionUID = 1L;
    private String ingredients;

    public Burger(String name, double price, String ingredients) {
        super(name, price);
        this.ingredients = ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void burgerStatus() {
        IO.println("Name: " + name);
        IO.println("Price: " + price);
        IO.println("Ingredients: " + ingredients);
    }

    @Override
    public String toString() {
        return name + " (" + ingredients + ")" + " - $" + price;
    }

}
