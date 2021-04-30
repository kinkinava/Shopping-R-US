package util;

public class CheckoutItem extends Item{

    public final int originalCount;
    public final double markedPrice;
    public double sellingPrice;
    public int pricedCount;
    public int obtainedCount;

    public CheckoutItem(Item item, int count){
        super(item.SKU, item.name, item.price);
        originalCount = count;
        markedPrice = item.price;
        sellingPrice = item.price;
        pricedCount = count;
        obtainedCount = count;

    }

    @Override
    public String toString() {
        return "SKU: "+SKU+"{" +
                "originalCount=" + originalCount +
                ", markedPrice=" + markedPrice +
                ", sellingPrice=" + sellingPrice +
                ", pricedCount=" + pricedCount +
                ", obtainedCount=" + obtainedCount +
                "}\n";
    }
}
