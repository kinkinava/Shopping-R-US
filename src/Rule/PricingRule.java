package Rule;


import util.CheckoutItem;

import java.util.HashMap;

public abstract class PricingRule {

/*
    Type of Pricing Rule:

    Step 1: Search for the [SKU] to see if there is promotion on the product -->
    Step 2: If there is promotion, check if the cart fulfill the requirement according to different type

    Some possible Type are:

    Type 1: Free Bundle
        [ Just need to add the product into the final cart] // Removed since the expected output from the description is different from that
        OR
        [ If the product already exist in the final cart, simply minus the price of the gifted product]

    Type 2: Price for each item drop! If you buy more then 4
        [
    Type 3: Buy 3 items, pay 2 only
        [ Just need to minus the price of 1 product]
        [ e.g. if (carts.get(item) > 3) {...} ]
        [ Similiar with Type 1: If the product already exist in the final cart, simply minus the price of the gifted product]
    Type 4: ..... Can add many pricing rule in the future



 */

    public abstract void process(HashMap<String, CheckoutItem> checkoutItems);

}
