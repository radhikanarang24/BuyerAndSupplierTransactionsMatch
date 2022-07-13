package com.craft.domain;

import java.util.Objects;

public class TransactionsMatch {

    private Integer supplierTransactionId;
    private Integer buyerTransactionId;
    private CategoryEnum category;
    private Integer transactionMatchScore;

    public TransactionsMatch(Integer supplierTransactionId, Integer buyerTransactionId, CategoryEnum category) {
        this.supplierTransactionId = supplierTransactionId;
        this.buyerTransactionId = buyerTransactionId;
        this.category = category;
        this.transactionMatchScore = 0;
    }

    public TransactionsMatch(Integer supplierTransactionId, Integer buyerTransactionId, CategoryEnum category, Integer transactionMatchScore) {
        this.supplierTransactionId = supplierTransactionId;
        this.buyerTransactionId = buyerTransactionId;
        this.category = category;
        this.transactionMatchScore = transactionMatchScore;
    }

    public TransactionsMatch(Integer supplierTransactionId, CategoryEnum category) {
        this.supplierTransactionId = supplierTransactionId;
        this.category = category;
    }

    public Integer getSupplierTransactionId() {
        return supplierTransactionId;
    }

    public void setSupplierTransactionId(Integer supplierTransactionId) {
        this.supplierTransactionId = supplierTransactionId;
    }

    public Integer getBuyerTransactionId() {
        return buyerTransactionId;
    }

    public void setBuyerTransactionId(Integer buyerTransactionId) {
        this.buyerTransactionId = buyerTransactionId;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public Integer getTransactionMatchScore() {
        return transactionMatchScore;
    }

    public void setTransactionMatchScore(Integer transactionMatchScore) {
        this.transactionMatchScore = transactionMatchScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionsMatch)) return false;
        TransactionsMatch that = (TransactionsMatch) o;
        return Objects.equals(getSupplierTransactionId(), that.getSupplierTransactionId()) && Objects.equals(getBuyerTransactionId(), that.getBuyerTransactionId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSupplierTransactionId(), getBuyerTransactionId());
    }
}
