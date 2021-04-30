package Rule;

import util.CheckoutItem;
import util.Data;

import java.util.HashMap;

public class FreeBundle extends PricingRule{

    String SKU;
    int amount;
    String giftedSKU;
    int giftAmount;

    /*
        OriginalCount
        MarkedPrice
        SellingPrice
        PricedCount     <-- this attribute will be affected
        ObtainedCount

     */

    public FreeBundle(String SKU, int amount, String giftedSKU, int giftAmount){
        this.SKU = SKU;
        this.giftAmount =giftAmount;
        this.amount = amount;
        this.giftedSKU = giftedSKU;
    }

    public void process(HashMap<String, CheckoutItem> checkoutitems){
        CheckoutItem item = checkoutitems.get(SKU);
        for (int i = item.originalCount; i >= amount; i-=amount){

            // Step 1. Chcek if the giftedSKU exist, if not, create the checkoutItem and put it into HashMap
            CheckoutItem giftedItem ;
            if(!checkoutitems.containsKey(giftedSKU)){
                //giftedItem = new CheckoutItem(Data.items.get(giftedSKU),0);
                //checkoutitems.put(giftedSKU,giftedItem);
            } else {
                giftedItem = checkoutitems.get(giftedSKU);
                giftedItem.pricedCount -= 1;
            }

            // Step 2. If exist or just created, then increase the ObtainedCount by giftAmount
            // giftedItem.obtainedCount += giftAmount;

        }

    }

    @Override
    public String toString() {
        return "FreeBundle{" +
                "SKU='" + SKU + '\'' +
                ", amount=" + amount +
                ", giftedSKU='" + giftedSKU + '\'' +
                ", giftAmount=" + giftAmount +
                '}';
    }
}

