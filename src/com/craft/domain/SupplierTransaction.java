package com.craft.domain;

import java.math.BigDecimal;
import java.util.Date;

public class SupplierTransaction extends Transaction {
    private static Integer count = 1;

    private Integer transactionId;

    public SupplierTransaction(String GSTNumber, Date transactionDate, String billNo, Integer GSTRate, BigDecimal taxableValue, BigDecimal IGST, BigDecimal CGST, BigDecimal SGST, BigDecimal total) {
        super(GSTNumber, transactionDate, billNo, GSTRate, taxableValue, IGST, CGST, SGST, total);
        setTransactionId(count++);
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer Id) {
        this.transactionId = Id;
    }
}
