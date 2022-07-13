package com.craft.service;

import com.craft.domain.BuyerTransaction;
import com.craft.domain.SupplierTransaction;

import java.util.List;

public interface ReadFromExcelService {
    List<SupplierTransaction> readSupplierTransactionsFromExcel(String fileName);

    List<BuyerTransaction> readBuyerTransactionsFromExcel(String fileName);
}
