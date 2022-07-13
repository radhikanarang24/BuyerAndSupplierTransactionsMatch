package com.craft.service.impl;

import com.craft.domain.BuyerTransaction;
import com.craft.domain.CategoryEnum;
import com.craft.domain.SupplierTransaction;
import com.craft.service.ConsoleOutputService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.craft.constants.Constants.dateFormat;

public class ConsoleOutputServiceImpl implements ConsoleOutputService {
    public static String spacer = "  |  ";

    @Override
    public void outputPartialMatchedTransactions(Map<SupplierTransaction, BuyerTransaction> partialMatchTransactionMap) {
        if (partialMatchTransactionMap == null || partialMatchTransactionMap.size() == 0) return;
        System.out.println(CategoryEnum.PARTIAL_MATCH);
        outputHeader();
        for (Map.Entry<SupplierTransaction, BuyerTransaction> txn : partialMatchTransactionMap.entrySet()) {
            System.out.println("Seller Transaction : ");
            SupplierTransaction supplierTransaction = txn.getKey();
            outputSupplierTransaction(supplierTransaction);

            System.out.println("Buyer Transaction : ");
            BuyerTransaction buyerTransaction = txn.getValue();
            outputBuyerTransaction(buyerTransaction);
        }
        outputEnd();

    }

    @Override
    public void outputExactMatchedTransactions(Map<SupplierTransaction, BuyerTransaction> exactMatchTransactionMap) {
        if (exactMatchTransactionMap == null || exactMatchTransactionMap.size() == 0) return;
        System.out.println(CategoryEnum.EXACT_MATCH);
        outputHeader();
        for (Map.Entry<SupplierTransaction, BuyerTransaction> txn : exactMatchTransactionMap.entrySet()) {
            System.out.println("Seller Transaction : ");
            SupplierTransaction supplierTransaction = txn.getKey();
            outputSupplierTransaction(supplierTransaction);

            System.out.println("Buyer Transaction : ");
            BuyerTransaction buyerTransaction = txn.getValue();
            outputBuyerTransaction(buyerTransaction);
        }
        outputEnd();
    }

    @Override
    public void outputOnlySupplierTransactions(List<SupplierTransaction> onlySupplierTransactionList) {
        if (onlySupplierTransactionList.isEmpty()) return;
        System.out.println(CategoryEnum.ONLY_IN_SUPPLIER);
        outputHeader();
        for (SupplierTransaction supplierTransaction : onlySupplierTransactionList) {
            outputSupplierTransaction(supplierTransaction);
        }
        outputEnd();
    }

    @Override
    public void outputOnlyBuyerTransactions(List<BuyerTransaction> onlyBuyerTransactionList) {
        if (onlyBuyerTransactionList.isEmpty()) return;
        System.out.println(CategoryEnum.ONLY_IN_BUYER);
        outputHeader();
        for (BuyerTransaction buyerTransaction : onlyBuyerTransactionList) {
            outputBuyerTransaction(buyerTransaction);
        }
        outputEnd();
    }

    private void outputHeader() {
        System.out.println("GSTIN           | Date      | Bill No.      | GST Rate(%) | Taxable Value   | IGST  | CGST   | SGST   | Total  | Category");
    }

    private void outputEnd() {
        System.out.println("==========================================================================================================================");
    }

    private void outputBuyerTransaction(BuyerTransaction buyerTransaction) {
        System.out.println(
                buyerTransaction.getGSTNumber()
                        + spacer + outputFormattedDate(buyerTransaction.getTransactionDate())
                        + spacer + buyerTransaction.getBillNo()
                        + spacer + buyerTransaction.getGSTRate()
                        + spacer + outputFormattedDouble(buyerTransaction.getTaxableValue())
                        + spacer + outputFormattedDouble(buyerTransaction.getIGST())
                        + spacer + outputFormattedDouble(buyerTransaction.getCGST())
                        + spacer + outputFormattedDouble(buyerTransaction.getSGST())
                        + spacer + outputFormattedDouble(buyerTransaction.getTotal())
        );
    }

    private void outputSupplierTransaction(SupplierTransaction supplierTransaction) {
        System.out.println(
                supplierTransaction.getGSTNumber()
                        + spacer + outputFormattedDate(supplierTransaction.getTransactionDate())
                        + spacer + supplierTransaction.getBillNo()
                        + spacer + supplierTransaction.getGSTRate()
                        + spacer + outputFormattedDouble(supplierTransaction.getTaxableValue())
                        + spacer + outputFormattedDouble(supplierTransaction.getIGST())
                        + spacer + outputFormattedDouble(supplierTransaction.getCGST())
                        + spacer + outputFormattedDouble(supplierTransaction.getSGST())
                        + spacer + outputFormattedDouble(supplierTransaction.getTotal())
        );

    }

    private String outputFormattedDate(Date date) {
        return date != null ? dateFormat.format(date) : "null";
    }

    private Double outputFormattedDouble(BigDecimal val) {
        val = val.setScale(2, RoundingMode.HALF_EVEN);
        return val.doubleValue();
    }
}
