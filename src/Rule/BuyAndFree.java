package Rule;

import util.CheckoutItem;
import util.Data;

import java.util.HashMap;

public class BuyAndFree extends PricingRule{

    String SKU;
    int amount;
    int freeAmount;

    public BuyAndFree(String SKU, int amount, int freeAmount){
        this.SKU = SKU;
        this.amount = amount;
        this.freeAmount = freeAmount;
    }

    public void process(HashMap<String, CheckoutItem> checkoutitems){
        CheckoutItem item = checkoutitems.get(SKU);
        for (int i = item.originalCount; i >= amount; i-=amount){
            item.pricedCount = Math.max(0,item.pricedCount - freeAmount);

        }

    }

    @Override
    public String toString() {
        return "BuyAndFree{" +
                "SKU='" + SKU + '\'' +
                ", amount=" + amount +
                ", freeAmount=" + freeAmount +
                '}';
    }
}
