package com.craft.service.impl;

import com.craft.domain.BuyerTransaction;
import com.craft.domain.SupplierTransaction;
import com.craft.service.ReadFromExcelService;
import com.craft.utils.CommonUtils;

import java.math.BigDecimal;
import java.util.*;

public class ReadFromExcelServiceImpl implements ReadFromExcelService {

    private static final Map<String, List<SupplierTransaction>> supplierTransaction = new HashMap<>();
    private static final Map<String, List<BuyerTransaction>> buyerTransaction = new HashMap<>();

    @Override
    public List<SupplierTransaction> readSupplierTransactionsFromExcel(String fileName) {
        Date transactionDate1 = CommonUtils.getDateFromString("07/07/2022");
        Date transactionDate2 = CommonUtils.getDateFromString("10/07/2022");
        SupplierTransaction transaction1 = new SupplierTransaction("29AAACB2894G1ZJ", transactionDate1, "474330129", Integer.valueOf(18),
                new BigDecimal(2099.00), new BigDecimal(0), new BigDecimal(188.91), new BigDecimal(188.91), new BigDecimal(2746.82));
        SupplierTransaction transaction2 = new SupplierTransaction("29AAACB2894G1ZJ", transactionDate2, "54541184", Integer.valueOf(18),
                new BigDecimal(5004.00), new BigDecimal(0), new BigDecimal(450.98), new BigDecimal(450.98), new BigDecimal(5904.72));
        SupplierTransaction transaction3 = new SupplierTransaction("29AAZFA1011A1ZU", transactionDate1, "2167", Integer.valueOf(18),
                new BigDecimal(15000.00), new BigDecimal(0), new BigDecimal(1350.00), new BigDecimal(1350.00), new BigDecimal(17700.00));
        SupplierTransaction transaction4 = new SupplierTransaction("29ACWPJ0559R1ZH", transactionDate2, "3", Integer.valueOf(18),
                new BigDecimal(44100.00), new BigDecimal(32.14), new BigDecimal(3969.00), new BigDecimal(3969.00), new BigDecimal(52038.00));

        supplierTransaction.put("supplier1", Arrays.asList(transaction1, transaction2, transaction3));
        supplierTransaction.put("supplier2", Arrays.asList(transaction4, transaction2, transaction3));

        return supplierTransaction.get(fileName);
    }

    @Override
    public List<BuyerTransaction> readBuyerTransactionsFromExcel(String fileName) {
        Date transactionDate = CommonUtils.getDateFromString("07/08/2022");
        BuyerTransaction transaction1 = new BuyerTransaction("29AAACB2894G1ZJ", transactionDate, "474330129", Integer.valueOf(18),
                new BigDecimal(2099.00), new BigDecimal(0), new BigDecimal(188.91), new BigDecimal(188.91), new BigDecimal(2746.82));
        BuyerTransaction transaction2 = new BuyerTransaction("29AAACB2894G", transactionDate, "474330129", Integer.valueOf(18),
                new BigDecimal(2099.00), new BigDecimal(0), new BigDecimal(188.91), new BigDecimal(188.91), new BigDecimal(2746.82));
        BuyerTransaction transaction3 = new BuyerTransaction("74d951683412945", transactionDate, "2264", Integer.valueOf(18),
                new BigDecimal(1080.00), new BigDecimal(0), new BigDecimal(97.2), new BigDecimal(97.2), new BigDecimal(26656.41));

        buyerTransaction.put("buyer1", Arrays.asList(transaction1, transaction2));
        buyerTransaction.put("buyer2", Arrays.asList(transaction2, transaction3));
        return buyerTransaction.get(fileName);
    }

}
