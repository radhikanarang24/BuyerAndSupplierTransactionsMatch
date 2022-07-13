package com.craft.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Transaction {

    private String GSTNumber;
    private Date transactionDate;
    private String billNo;
    private Integer GSTRate;
    private BigDecimal taxableValue;
    private BigDecimal IGST;
    private BigDecimal CGST;
    private BigDecimal SGST;
    private BigDecimal Total;

    public Transaction(String GSTNumber, Date transactionDate, String billNo, Integer GSTRate, BigDecimal taxableValue, BigDecimal IGST, BigDecimal CGST, BigDecimal SGST, BigDecimal total) {
        this.GSTNumber = GSTNumber;
        this.transactionDate = transactionDate;
        this.billNo = billNo;
        this.GSTRate = GSTRate;
        this.taxableValue = taxableValue;
        this.IGST = IGST;
        this.CGST = CGST;
        this.SGST = SGST;
        Total = total;
    }

    public String getGSTNumber() {
        return GSTNumber;
    }

    public void setGSTNumber(String GSTNumber) {
        this.GSTNumber = GSTNumber;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public Integer getGSTRate() {
        return GSTRate;
    }

    public void setGSTRate(Integer GSTRate) {
        this.GSTRate = GSTRate;
    }

    public BigDecimal getTaxableValue() {
        return taxableValue;
    }

    public void setTaxableValue(BigDecimal taxableValue) {
        this.taxableValue = taxableValue;
    }

    public BigDecimal getIGST() {
        return IGST;
    }

    public void setIGST(BigDecimal IGST) {
        this.IGST = IGST;
    }

    public BigDecimal getCGST() {
        return CGST;
    }

    public void setCGST(BigDecimal CGST) {
        this.CGST = CGST;
    }

    public BigDecimal getSGST() {
        return SGST;
    }

    public void setSGST(BigDecimal SGST) {
        this.SGST = SGST;
    }

    public BigDecimal getTotal() {
        return Total;
    }

    public void setTotal(BigDecimal total) {
        Total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return getGSTNumber().equals(that.getGSTNumber()) && getTransactionDate().equals(that.getTransactionDate())
                && getBillNo().equals(that.getBillNo()) && getGSTRate().equals(that.getGSTRate())
                && getTaxableValue().equals(that.getTaxableValue())
                && Objects.equals(getIGST(), that.getIGST()) && Objects.equals(getCGST(), that.getCGST())
                && Objects.equals(getSGST(), that.getSGST()) && Objects.equals(getTotal(), that.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGSTNumber(), getTransactionDate(), getBillNo(), getGSTRate(), getTaxableValue(), getIGST(), getCGST(), getSGST(), getTotal());
    }
}
