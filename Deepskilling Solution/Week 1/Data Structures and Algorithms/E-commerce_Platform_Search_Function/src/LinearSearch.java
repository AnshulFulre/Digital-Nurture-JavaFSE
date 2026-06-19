public class LinearSearch {
    public static void main(String[] args) {
        Product[] products = {
                new Product(1, "Laptop", "Electronics"),
                new Product(4, "Phone", "Electronics"),
                new Product(2, "Shoes", "Fashion"),
                new Product(3, "Watch", "Accessories")
        };

        Product result = linear_search(products, 2);

        if(result != null)
            System.out.println(result);
        else
            System.out.println("Product Not Found");
    }

    private static Product linear_search(Product[] products, int id) {
        for(Product p : products){
            if (p.productId == id) return p;
        }

        return null;
    }
}