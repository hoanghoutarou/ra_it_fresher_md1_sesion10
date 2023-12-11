package ra.ra.entity;

import ra.IShop;
import ra.run.ShopManagement;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Product implements IShop {
    private String productid,productName,title;
    private int catalogId;
    private Float price;
    private Boolean status;

    public Product() {
    }

    public Product(String productid, String productName, String title, int catalogId, Float price, Boolean status) {
        this.productid = productid;
        this.productName = productName;
        this.title = title;
        this.catalogId = catalogId;
        this.price = price;
        this.status = status;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    public String inputProductId(Scanner sc, List<Product> listProduct) {
        do {
            System.out.print("Nhập mã sản phẩm:");
            this.productid = sc.nextLine();
            if (this.productid.length() == 5) {
                if (this.productid.charAt(0) == 'P') {
                    boolean isDuplication = false;
                    for (int i = 0; i < listProduct.size(); i++) {
                        if (this.productid.equals(listProduct.get(i).getProductid())) {
                            isDuplication = true;
                            break;
                        }
                    }
                    if (isDuplication) {
                        System.out.println("Mã sản phẩm bị trùng mời nhập lại.");
                    } else {
                        return this.productid;
                    }
                }
            }
        } while (true);
    }

    public String inputProductName(Scanner sc, List<Product> listProduct) {
        do {
            System.out.print("Nhập tên sản phẩm:");
            this.productName = sc.nextLine();
            boolean isDuplication = false;
            for (int i = 0; i < listProduct.size(); i++) {
                if (this.productName.equals(listProduct.get(i).getProductName())) {
                    isDuplication = true;
                    break;
                }
            }
            if (isDuplication) {
                System.out.println("Tên sản phẩm bị trùng mời nhập lại");
            } else {
                return this.productName;
            }
        } while (true);
    }
    public float inputPrice(Scanner sc) {
        do {
            System.out.print("Nhập giá sản phẩm:");
            this.price = Float.parseFloat(sc.nextLine());
            if (this.price > 0) {
                return this.price;
            } else {
                System.out.println("Giá sản phẩm phải lớn hơn 0");
            }
        } while (true);
    }

    public String inputTitle(Scanner sc) {
        System.out.print("Nhập tiêu đề sản phẩm:");
        this.title = sc.nextLine();
        return this.title;
    }
    public int inputCatalogId(Scanner sc, List<Categories> listCategories) {
        Categories categories = new Categories();
        System.out.println("Thông tin các danh mục:");
        categories.displayData();
        do {
            System.out.print("Nhập mã danh mục mà sản phẩm thuộc về:");
            this.catalogId = Integer.parseInt(sc.nextLine());
            boolean isExit = false;
            for (int i = 0; i < listCategories.size(); i++) {
                if (this.catalogId == listCategories.get(i).getCatalogId()) {
                    isExit = true;
                    break;
                }
            }
            if (isExit) {
                return this.catalogId;
            } else {
                System.out.println("Mã danh mục không tồn tại moi nhập lại!");
            }
        } while (true);
    }

    public boolean inputProductStatus(Scanner sc) {
        System.out.println("Nhập vào trạng thái danh mục:");
        do {
            String status = sc.nextLine();
            if (status.equals("true") || status.equals("false")) {
                return Boolean.parseBoolean(status);
            } else {
                System.err.println("Trạng thái san pham chỉ nhận true hoặc false, vui lòng nhập lại");
            }
        } while (true);
    }
    @Override
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập số sản phẩm cần nhập thông tin:");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            System.out.printf("Sản phẩm thứ %d:\n", (ShopManagement.listProducts.size() + 1));
            this.productid = inputProductId(sc, ShopManagement.listProducts);
            this.productName = inputProductName(sc, ShopManagement.listProducts);
            this.price = inputPrice(sc);
            this.title = inputTitle(sc);
            this.catalogId = inputCatalogId(sc, ShopManagement.listCategories);
            this.status = inputProductStatus(sc);
            Product product = new Product(this.productid,this.productName,this.title,this.catalogId,this.price,this.status);
            ShopManagement.listProducts.add(product);
        }
    }

    @Override
    public void displayData() {
        ShopManagement.listProducts.forEach(System.out::println);
    }
    public int checkIdProduct(List<Product> listProduct, Scanner sc) {
        boolean isExit = false;
        do {
            System.out.print("Nhập mã sản phẩm:");
            String id = sc.nextLine();
            for (int i = 0; i < listProduct.size(); i++) {
                if (id.equals(listProduct.get(i).getProductid())) {
                    return i;
                }
            }
            if (!isExit) {
                System.out.println("Mã sản phẩm không tồn tại mời nhập lại!");
            }
        } while (true);
    }

    public void updateProduct(Scanner sc, List<Product> listProduct) {
        int updateIndex = checkIdProduct(listProduct, sc);
        if (updateIndex >= 0) {
            listProduct.get(updateIndex).setPrice(inputPrice(sc));
            System.out.print("Cập nhật thành công!");
        }
    }
    public void deleteProduct(Scanner sc, List<Product> listProduct) {
        int deleteIndex = checkIdProduct(listProduct, sc);
        if (deleteIndex >= 0) {
            listProduct.remove(deleteIndex);
            System.out.print("Xóa thành công!");
        }
    }
    public void sortProductByPrice(List<Product> listProduct) {
        listProduct.sort((o1, o2) -> (int) (o1.price - o2.price));
        System.out.println("Sắp xếp thành công");
        ShopManagement.listProducts.forEach(System.out::println);
    }

    public void sortProductByName(List<Product> listProduct) {
        listProduct.sort(Comparator.comparing(o -> o.productName));
        System.out.println("Sắp xếp thành công! Ấn 2 kiểm tra kết quả!");
        ShopManagement.listProducts.forEach(System.out::println);
    }

    public void lookForProductByName(List<Product> listProduct, Scanner sc) {
        System.out.print("Nhập tên sản phẩm tìm kiếm:");
        String name = sc.nextLine();
        boolean isCheck = false;
        for (int i = 0; i < listProduct.size(); i++) {
            if (listProduct.get(i).getProductName().contains(name)) {
                isCheck = true;
                System.out.println(listProduct.get(i).toString());
                break;
            }
        }
        if (!isCheck) {
            System.out.print("Tên sản phẩm không tồn tại");
        }
    }

    public void statisticsByCategories(List<Categories> listCategories, List<Product> listProduct) {
        System.out.println("Thống kê số lượng sản phẩm theo danh mục sản phẩm:");
        for (int i = 0; i < listCategories.size(); i++) {
            int quantity = countProduct(listCategories.get(i).getCatalogId(), listProduct);
            String name = listCategories.get(i).getCatalogName();
            System.out.printf("Tên danh mục %s: %d sản phẩm\n", name, quantity);
        }
    }

    public int countProduct(int idCatalog, List<Product> listProduct) {
        int count = 0;
        for (int i = 0; i < listProduct.size(); i++) {
            if (listProduct.get(i).getCatalogId() == idCatalog) {
                count++;
            }
        }
        return count;
    }

}
