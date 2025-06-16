package model;

public class KhStokoCusVO {
    private int recNo;
    private String categoryCus;
    private String subCategoryCus;
    private int priceCus;
    private int stockCus;
    private String expDate;



    public KhStokoCusVO() {
        super();
    }

    public KhStokoCusVO(int recNo, String categoryCus, String subCategoryCus, int priceCus, int stockCus,
            String expDate) {
        this.recNo = recNo;
        this.categoryCus = categoryCus;
        this.subCategoryCus = subCategoryCus;
        this.priceCus = priceCus;
        this.stockCus = stockCus;
        this.expDate = expDate;
    }

    public int getRecNo() {
        return recNo;
    }

    public void setRecNo(int recNo) {
        this.recNo = recNo;
    }

    public String getCategoryCus() {
        return categoryCus;
    }

    public void setCategoryCus(String categoryCus) {
        this.categoryCus = categoryCus;
    }

    public String getSubCategoryCus() {
        return subCategoryCus;
    }

    public void setSubCategoryCus(String subCategoryCus) {
        this.subCategoryCus = subCategoryCus;
    }

    public int getPriceCus() {
        return priceCus;
    }

    public void setPriceCus(int priceCus) {
        this.priceCus = priceCus;
    }

    public int getStockCus() {
        return stockCus;
    }

    public void setStockCus(int stockCus) {
        this.stockCus = stockCus;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    @Override
    public String toString() {
        return "[영수증 번호: " + recNo + ", 대분류: " + categoryCus + ", 소분류: " + subCategoryCus
                + ", 가격: " + priceCus + ", 구매개수: " + stockCus + ", 구매일자: " + expDate + "]";
    }

}
