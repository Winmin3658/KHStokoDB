package model;

public class KhStokoVO {
    private int stockNo;
    private String category;
    private String subCategory;
    private int price;
    private int stock;
    private String expDate;

    public KhStokoVO() {
        super();
    }

    public KhStokoVO(int stockNo, String category, String subCategory, int price, int stock, String expDate) {
        this.stockNo = stockNo;
        this.category = category;
        this.subCategory = subCategory;
        this.price = price;
        this.stock = stock;
        this.expDate = expDate;
    }

    public int getStockNo() {
        return stockNo;
    }

    public void setStockNo(int stockNo) {
        this.stockNo = stockNo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    @Override
    public String toString() {
        return "[품목 번호:" + stockNo + ", 대분류: " + category + ", 소분류: " + subCategory + ", 가격: "
                + price + ", 개수: " + stock + ", 유통기한: " + expDate + "]";
    }

}
