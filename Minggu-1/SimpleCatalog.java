public class SimpleCatalog {
    public static void main(String[] args) {
        String productName = "Mechanical Keyboard";
        double price = 450000.0;
        int stock = 8;
        if (stock > 0) {
            displayProduct(productName, price, stock);
        }
    }
    public static void displayProduct(String name, double price, int stock) {
        System.out.println("Product: " + name + " | Price: Rp" + price + " | 
Stock: " + stock);
    }
}