package com.craft.service;

import com.craft.domain.BuyerTransaction;
import com.craft.domain.SupplierTransaction;

import java.util.List;
import java.util.Map;

public interface ConsoleOutputService {
    void outputPartialMatchedTransactions(Map<SupplierTransaction, BuyerTransaction> partialMatchTransactionMap);

    void outputExactMatchedTransactions(Map<SupplierTransaction, BuyerTransaction> exactMatchTransactionMap);

    void outputOnlySupplierTransactions(List<SupplierTransaction> onlySupplierTransactionList);

    void outputOnlyBuyerTransactions(List<BuyerTransaction> onlyBuyerTransactionList);
}
