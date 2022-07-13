package com.craft.service;

import com.craft.domain.BuyerTransaction;
import com.craft.domain.SupplierTransaction;
import com.craft.domain.TransactionsMatch;

import java.util.List;
import java.util.Map;

public interface CategorizeTransactionsService {

    Map<Integer, TransactionsMatch> compareAndCategorizeTransactions(List<BuyerTransaction> buyerTransactions, List<SupplierTransaction> supplierTransactions);

    List<BuyerTransaction> getOnlyBuyerCategory(List<BuyerTransaction> buyerTransactions);
}
