package ra.ra.entity;

import ra.IShop;
import ra.run.ShopManagement;

import java.util.List;
import java.util.Scanner;

public class Categories implements IShop {
    int catalogIndex = 0;
    private int catalogId;
    private String catalogName;
    private Boolean satus;

    public Categories() {
    }

    public Categories(int catalogId, String catalogName, Boolean satus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.satus = satus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public Boolean getSatus() {
        return satus;
    }

    public void setSatus(Boolean satus) {
        this.satus = satus;
    }

    public String toString() {
        return "Mã danh mục: " + this.catalogId + " - Tên danh mục: " + this.catalogName + " - Trạng thái danh mục: " + this.satus + " \n";
    }
    public int inputCatalogId(Scanner sc, List<Categories> listCategories) {
        System.out.println("Nhập vào mã danh muc:");
        do {
            this.catalogId = Integer.parseInt(sc.nextLine());
            if (catalogId > 0) {
                boolean isExist = false;
                for (int i = 0; i < listCategories.size(); i++) {
                    if (listCategories.get(i).getCatalogId()==catalogId) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Mã danh muc đã tồn tại, vui lòng nhập lại");
                } else {
                    return catalogId;
                }
            } else {
                System.err.println("Mã danh muc la so nguyen , vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputCatalogName(Scanner sc,  List<Categories> listCategories) {
        System.out.println("Nhập vào tên danh mục:");
        do {
            this.catalogName = sc.nextLine();

            boolean isExist = false;//Chưa tồn tại
            for (int i = 0; i < listCategories.size(); i++) {
                if (listCategories.get(i).getCatalogName().equals(catalogName)) {
                    isExist = true;//Bị trùng
                    break;
                }
            }
            if (isExist) {
                System.err.println("Tên danh mục đã tồn tại, vui lòng nhập lại");
            } else {
                return catalogName;
            }

        } while (true);
    }
    public boolean inputCatalogStatus(Scanner sc) {
        do {
            System.out.println("Nhập vào trạng thái danh mục:");
            String status = sc.nextLine();
            if (status.equals("true") || status.equals("false")) {
                this.satus = Boolean.parseBoolean(status);
                return this.satus;
            } else {
                System.err.println("Trạng thái danh mục chỉ nhận true hoặc false, vui lòng nhập lại");
            }
        } while (true);
    }
    @Override
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập số danh muc cần nhập thông tin:");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            System.out.printf("Danh muc thứ %d:\n", (ShopManagement.listProducts.size() + 1));
            this.catalogId = inputCatalogId(sc, ShopManagement.listCategories);
            this.catalogName = inputCatalogName(sc, ShopManagement.listCategories);
            this.satus = inputCatalogStatus(sc);
            Categories categorie = new Categories(this.catalogId, this.catalogName, this.satus);
            ShopManagement.listCategories.add(categorie);
        }
    }

    @Override
    public void displayData() {
        ShopManagement.listCategories.forEach(System.out::println);
    }
    public int checkIdCatalog(List<Categories> listCategories, Scanner scanner) {
        boolean isExit = false;
        do {
            System.out.print("Nhập mã danh mục:");
            int id = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < listCategories.size(); i++) {
                if (id == listCategories.get(i).getCatalogId()) {
                    return i;
                }
            }
            if (!isExit) {
                System.out.println("Mã danh mục không tồn tại! Mời nhập lại!");
            }
        } while (true);
    }
    public void updateCatalog(Scanner sc, List<Categories> listCategories) {
        int updateIndex = checkIdCatalog(listCategories, sc);
        if (updateIndex >= 0) {
            listCategories.get(updateIndex).setCatalogName(inputCatalogName(sc, listCategories));
            System.out.print("Cập nhật thành công!");
        }
    }
    public void deleteCatalog(Scanner sc, List<Categories> listCategories,List<Product> listProduct) {
        int deleteIndex = checkIdCatalog(listCategories, sc);
        if (deleteIndex >= 0) {
            boolean isCheck = false;
            for (int i = 0; i < listProduct.size(); i++) {
                if (listProduct.get(i).getCatalogId() != listCategories.get(deleteIndex).getCatalogId()){
                    isCheck = true;
                    listCategories.remove(deleteIndex);
                    System.out.print("Xóa thành công!");
                    break;
                }
            }
            if (!isCheck){
                System.out.print("Danh mục đang chứa sản phẩm nên không xóa được !");
            }
        }

    }
}
