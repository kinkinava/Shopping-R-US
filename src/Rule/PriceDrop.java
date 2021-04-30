package Rule;

import util.CheckoutItem;

import java.util.HashMap;

public class PriceDrop extends PricingRule{

    String SKU;
    int amount;
    double newPrice;

    public PriceDrop(String SKU, int amount, double newPrice){
        this.SKU = SKU;
        this.amount = amount;
        this.newPrice = newPrice;
    }



    @Override
    public void process(HashMap<String, CheckoutItem> checkoutitems){
        CheckoutItem item = checkoutitems.get(SKU);
        if (item.originalCount >= amount){
            item.sellingPrice = newPrice;
        }
    }

    @Override
    public String toString() {
        return "PriceDrop{" +
                "SKU='" + SKU + '\'' +
                ", amount=" + amount +
                ", newPrice=" + newPrice +
                '}';
    }
}
