public class BinarySearch {
    public static void main(String[] args) {
        Product[] products = {
                new Product(1, "Laptop", "Electronics"),
                new Product(2, "Phone", "Electronics"),
                new Product(3, "Shoes", "Fashion"),
                new Product(4, "Watch", "Accessories"),
                new Product(5, "Tablet", "Electronics")
        };

        Product result = binary_search(products, 2);

        if(result != null)
            System.out.println(result);
        else
            System.out.println("Product Not Found");
    }

    private static Product binary_search(Product[] products, int id) {
        int l = 0, r= products.length-1;
        while(l<=r){
            int mid = (r-l)/2 + l;

            if(products[mid].productId == id) return products[mid];
            else  if(products[mid].productId < id) l = mid+1;
            else r = mid-1;
        }
        return null;
    }
}
