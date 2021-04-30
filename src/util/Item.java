package util;

public class Item {

    String SKU;
    String name;
    double price;

    public Item(String SKU, String name, double price){
        this.SKU = SKU;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString(){
        return String.format("SKU: %s, Name: %s, Price: %.2f", SKU,name,price);
    }
}
