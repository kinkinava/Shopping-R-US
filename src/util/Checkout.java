package util;

import Rule.PricingRule;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class Checkout {

    HashMap<Item, Integer> cart = new HashMap<Item, Integer>() ;
    //HashMap<Item, Integer> processedCart;

    HashMap<Item, PricingRule[]> pricingrule;
    int expectedPrice;

    public Checkout(HashMap<Item, PricingRule[]> pricingrule){
        this.pricingrule = pricingrule;
    }

    /*
        Append the item into shopping cart
     */
    public void scan(Item item){
        System.out.println("Scanning: "+item);
            if (cart.containsKey(item) ){
                cart.put(item, cart.get(item) + 1);
            } else {
                cart.put(item, 1);
            }

    }

    /*
        Output what's inside the cart
     */
    public void cartSummary(){
        System.out.println("Cart Info");
        System.out.println("======================");
        for (Item item: cart.keySet()) {
            String key = item.toString();
            String value = cart.get(item).toString();
            System.out.println(key + " | Amount: " + value);
        }
        System.out.println("======================\n");

    }

    /*
        Return the Original Price of the items in the shopping cart
     */
    double originalPrice(){
        AtomicReference<Double> total = new AtomicReference<>((double) 0);
        cart.forEach((Item,count)-> {
            total.updateAndGet(v -> new Double((double) (v + Item.price * count)));
        });
        return total.get();
    }


    /*
        Calculate the expected price for the customer.
     */

    public void checkout(){
        System.out.println("Processing Final Payment");
        System.out.println("======================\n");
        HashMap<String,CheckoutItem> checkoutItems = new HashMap<String,CheckoutItem>();

        // Init a HashMap for item to be checkout
        cart.forEach((item,count) -> {
            checkoutItems.put(item.SKU,new CheckoutItem(item,count));

        });


        // Apply corrsponding pricing rule for each item
        cart.forEach((item,count) -> {
            if (pricingrule.containsKey(item)){
                PricingRule[] rules = pricingrule.get(item);
                for (PricingRule rule: rules){
                    System.out.println("[Processing Pricing Rule] "+rule);
                    rule.process(checkoutItems);
                    System.out.println("[Items] "+checkoutItems);
                }
            }
        });

        AtomicReference<Double> expectedPrice = new AtomicReference<>((double) 0);

        // Generate Receipt for customer
        System.out.println(String.format("Receipt"));
        System.out.println("======================\n");
        System.out.println(String.format("%21s \t Price \t Amount \t Average$ \t Obtained ",""));
        checkoutItems.forEach((sku,item) -> {
            System.out.println(String.format("%20s \t $%.2f \t %d \t %10.2f   \t %d"
                    ,item.name, item.markedPrice, item.originalCount, item.sellingPrice * item.pricedCount/item.obtainedCount, item.obtainedCount));

            expectedPrice.updateAndGet(v -> new Double((double) (v + item.sellingPrice * item.pricedCount)));

        });
        System.out.println(String.format("Total Expected: $%s ",expectedPrice));
    }


 }

