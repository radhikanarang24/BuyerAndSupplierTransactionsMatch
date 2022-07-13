package com.craft.service.impl;

import com.craft.domain.BuyerTransaction;
import com.craft.domain.SupplierTransaction;

import java.util.List;

public class TestService {

    public static void main(String[] args) {
        ReadFromExcelServiceImpl readFromExcelService = new ReadFromExcelServiceImpl();
        CategorizeTransactionsServiceImpl categorizeTransactionsService = new CategorizeTransactionsServiceImpl();
        List<SupplierTransaction> supplierTransactions = readFromExcelService.readSupplierTransactionsFromExcel("supplier1");
        List<BuyerTransaction> buyerTransactions = readFromExcelService.readBuyerTransactionsFromExcel("buyer1");
        categorizeTransactionsService.compareAndCategorizeTransactions(buyerTransactions, supplierTransactions);
    }


}
