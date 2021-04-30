import Rule.BuyAndFree;
import Rule.FreeBundle;
import Rule.PriceDrop;
import Rule.PricingRule;
import util.Checkout;
import util.Data;
import util.Item;

import java.util.HashMap;
import java.util.Scanner;

public class Shopping {


    public static void main(String[] args) {

        // Some Data Preparation

        Data.items.put("ipd", new Item("ipd","Super iPad", 549.99));
        Data.items.put("mbp", new Item("mbp","MacBook Pro", 1399.99));
        Data.items.put("atv", new Item("atv","Apple TV", 109.50));
        Data.items.put("vga", new Item("vga","VGA adapter", 30.00));

        Item ipd = Data.items.get("ipd");
        Item mbp = Data.items.get("mbp");
        Item atv = Data.items.get("atv");
        Item vga = Data.items.get("vga");

        HashMap<Item, PricingRule[]> pricingrules = new HashMap<Item, PricingRule[]>();
        Data.items.forEach((sku,item) -> {
            pricingrules.put(item, new PricingRule[]{});
        });


        pricingrules.put(mbp, new PricingRule[]{
                new FreeBundle("mbp", 1, "vga", 1)
        });

        pricingrules.put(ipd, new PricingRule[]{
                new PriceDrop("ipd", 5, 499.99)
        });

        pricingrules.put(atv, new PricingRule[]{
                new BuyAndFree("atv", 3,  1)
        });

        Checkout checkout = new Checkout(pricingrules);

        System.out.print("SKUs Scanned: ");
        Scanner scanner = new Scanner(System.in);
        String[] row = scanner.nextLine().split(",");
        for (String data : row){
            checkout.scan(Data.items.get(data.replaceAll("\\s+","")));
        }



        checkout.cartSummary();

        checkout.checkout();

    }
}
