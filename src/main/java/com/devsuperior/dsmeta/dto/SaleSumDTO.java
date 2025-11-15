package com.devsuperior.dsmeta.dto;

public class SaleSumDTO {

    private String sellerName;
    private Double saleAmount;

    public SaleSumDTO(String sellerName, Double saleAmount) {
        this.sellerName = sellerName;
        this.saleAmount = saleAmount;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Double getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(Double saleAmount) {
        this.saleAmount = saleAmount;
    }
}
