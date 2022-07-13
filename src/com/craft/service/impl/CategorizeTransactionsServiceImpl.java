package com.craft.service.impl;

import com.craft.constants.Constants;
import com.craft.domain.BuyerTransaction;
import com.craft.domain.CategoryEnum;
import com.craft.domain.SupplierTransaction;
import com.craft.domain.TransactionsMatch;
import com.craft.service.CategorizeTransactionsService;
import com.craft.utils.MatchUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CategorizeTransactionsServiceImpl implements CategorizeTransactionsService {
    Map<Integer, TransactionsMatch> transactionsMap = new HashMap<Integer, TransactionsMatch>();
    Map<SupplierTransaction, BuyerTransaction> exactMatchTransactionsMap = new HashMap<>();
    Map<SupplierTransaction, BuyerTransaction> partialMatchTransactionsMap = new HashMap<>();


    private Integer checkPartialMatch(BuyerTransaction buyerTransaction, SupplierTransaction supplierTransaction) {
        Integer transactionMatch = 0, fieldMatch = 0;

        fieldMatch = MatchUtils.StringMatch(supplierTransaction.getGSTNumber(), buyerTransaction.getGSTNumber());
        if (checkIfAboveThreshold(fieldMatch, transactionMatch, Constants.STRING_THRESHOLD))
            return Constants.PARTIAL_MATCH_THRESHOLD + 1;
        transactionMatch += fieldMatch;

        fieldMatch = MatchUtils.DateMatch(supplierTransaction.getTransactionDate(), buyerTransaction.getTransactionDate()).intValue();
        if (checkIfAboveThreshold(fieldMatch, transactionMatch, Constants.DATE_THRESHOLD))
            return Constants.PARTIAL_MATCH_THRESHOLD + 1;
        transactionMatch += fieldMatch;

        fieldMatch = MatchUtils.StringMatch(supplierTransaction.getBillNo(), buyerTransaction.getBillNo());
        if (checkIfAboveThreshold(fieldMatch, transactionMatch, Constants.STRING_THRESHOLD))
            return Constants.PARTIAL_MATCH_THRESHOLD + 1;
        transactionMatch += fieldMatch;

        fieldMatch = MatchUtils.IntegerMatch(supplierTransaction.getGSTRate(), buyerTransaction.getGSTRate());
        if (checkIfAboveThreshold(fieldMatch, transactionMatch, Constants.INTEGER_THRESHOLD))
            return Constants.PARTIAL_MATCH_THRESHOLD + 1;
        transactionMatch += fieldMatch;

        fieldMatch = MatchUtils.BigDecimalMatch(supplierTransaction.getTaxableValue(), buyerTransaction.getTaxableValue());
        if (checkIfAboveThreshold(fieldMatch, transactionMatch, Constants.BIGDECIMAL_THRESHOLD))
            return Constants.PARTIAL_MATCH_THRESHOLD + 1;
        transactionMatch += fieldMatch;

        fieldMatch = MatchUtils.BigDecimalMatch(supplierTransaction.getIGST(), buyerTransaction.getIGST());
        if (checkIfAboveThreshold(fieldMatch, transactionMatch, Constants.BIGDECIMAL_THRESHOLD))
            return Constants.PARTIAL_MATCH_THRESHOLD + 1;
        transactionMatch += fieldMatch;

        fieldMatch = MatchUtils.BigDecimalMatch(supplierTransaction.getCGST(), buyerTransaction.getCGST());
        if (checkIfAboveThreshold(fieldMatch, transactionMatch, Constants.BIGDECIMAL_THRESHOLD))
            return Constants.PARTIAL_MATCH_THRESHOLD + 1;
        transactionMatch += fieldMatch;

        fieldMatch = MatchUtils.BigDecimalMatch(supplierTransaction.getSGST(), buyerTransaction.getSGST());
        if (checkIfAboveThreshold(fieldMatch, transactionMatch, Constants.BIGDECIMAL_THRESHOLD))
            return Constants.PARTIAL_MATCH_THRESHOLD + 1;
        transactionMatch += fieldMatch;

        fieldMatch = MatchUtils.BigDecimalMatch(supplierTransaction.getTotal(), buyerTransaction.getTotal());
        if (checkIfAboveThreshold(fieldMatch, transactionMatch, Constants.BIGDECIMAL_THRESHOLD))
            return Constants.PARTIAL_MATCH_THRESHOLD + 1;
        transactionMatch += fieldMatch;

        return transactionMatch;
    }

    @Override
    public Map<Integer, TransactionsMatch> compareAndCategorizeTransactions(List<BuyerTransaction> buyerTransactions, List<SupplierTransaction> supplierTransactions) {
        if ((buyerTransactions == null || buyerTransactions.isEmpty()) && (supplierTransactions == null || supplierTransactions.isEmpty())) {
            System.out.println("Buyer and Supplier Transactions are empty/null");
            return transactionsMap;
        }
        List<SupplierTransaction> onlySupplierTransactions = new ArrayList<>();

        for (SupplierTransaction supplierTransaction : supplierTransactions) {
            for (BuyerTransaction buyerTransaction : buyerTransactions) {
                checkPartialOrExactTransactionsMatch(buyerTransaction, supplierTransaction);
            }
            if (!transactionsMap.containsKey(supplierTransaction.getTransactionId()))
                onlySupplierTransactions.add(supplierTransaction);
        }

        List<BuyerTransaction> onlyBuyerTransactions = getOnlyBuyerCategory(buyerTransactions);
        outputAllTransactionsMatched(onlyBuyerTransactions, onlySupplierTransactions);
        return transactionsMap;
    }

    @Override
    public List<BuyerTransaction> getOnlyBuyerCategory(List<BuyerTransaction> buyerTransactions) {
        List<BuyerTransaction> onlyBuyerTransactionList = new ArrayList<>();
        List<Integer> matchedBuyerTransactionIds = transactionsMap.values().stream().map(tm -> tm.getBuyerTransactionId()).collect(Collectors.toList());
        for (BuyerTransaction buyerTxn : buyerTransactions) {
            boolean isMatched = false;
            for (Integer txnId : matchedBuyerTransactionIds) {
                if (txnId.equals(buyerTxn.getTransactionId())) {
                    isMatched = true;
                    break;
                }
            }
            if (isMatched == false) {
                onlyBuyerTransactionList.add(buyerTxn);
            }
        }
        return onlyBuyerTransactionList;
    }

    private boolean checkIfAboveThreshold(Integer fieldMatch, Integer transactionMatch, Integer threshold) {
        if (fieldMatch > threshold && fieldMatch + transactionMatch > Constants.PARTIAL_MATCH_THRESHOLD)
            return true;
        return false;
    }

    private void checkPartialOrExactTransactionsMatch(BuyerTransaction buyerTransaction, SupplierTransaction supplierTransaction) {
        if (buyerTransaction.equals(supplierTransaction)) {
            setExactMatch(buyerTransaction, supplierTransaction);
            return;
        }
        Integer partialMatchScore = checkPartialMatch(buyerTransaction, supplierTransaction);
        if (partialMatchScore <= Constants.PARTIAL_MATCH_THRESHOLD) {
            setPartialMatch(buyerTransaction, supplierTransaction, partialMatchScore);
        }
    }

    private void setPartialMatch(BuyerTransaction buyerTransaction, SupplierTransaction supplierTransaction, Integer partialMatchScore) {
        TransactionsMatch tm = new TransactionsMatch(buyerTransaction.getTransactionId(), supplierTransaction.getTransactionId(), CategoryEnum.PARTIAL_MATCH, partialMatchScore);
        TransactionsMatch partialMatch = transactionsMap.getOrDefault(supplierTransaction.getTransactionId(), null);

        if (partialMatch != null &&
                partialMatch.getCategory().equals(CategoryEnum.PARTIAL_MATCH) &&
                partialMatch.getTransactionMatchScore() > partialMatchScore) {
            transactionsMap.put(supplierTransaction.getTransactionId(), tm);
            partialMatchTransactionsMap.put(supplierTransaction, buyerTransaction);
        } else {
            transactionsMap.put(supplierTransaction.getTransactionId(), tm);
            partialMatchTransactionsMap.put(supplierTransaction, buyerTransaction);
        }
    }

    private void setExactMatch(BuyerTransaction buyerTransaction, SupplierTransaction supplierTransaction) {
        TransactionsMatch tm = new TransactionsMatch(buyerTransaction.getTransactionId(), supplierTransaction.getTransactionId(), CategoryEnum.EXACT_MATCH);
        if (transactionsMap.containsKey(supplierTransaction.getTransactionId()) && transactionsMap.get(supplierTransaction.getTransactionId()).getCategory().equals(CategoryEnum.PARTIAL_MATCH)) {
            transactionsMap.put(supplierTransaction.getTransactionId(), tm);
            if (partialMatchTransactionsMap.containsKey(supplierTransaction)) {
                partialMatchTransactionsMap.remove(supplierTransaction);
                exactMatchTransactionsMap.put(supplierTransaction, buyerTransaction);
            }
        } else {
            transactionsMap.put(supplierTransaction.getTransactionId(), tm);
            exactMatchTransactionsMap.put(supplierTransaction, buyerTransaction);
        }
    }

    private void outputAllTransactionsMatched(List<BuyerTransaction> onlyBuyerTxns, List<SupplierTransaction> onlySupplierTxns) {
        ConsoleOutputServiceImpl consoleOutputService = new ConsoleOutputServiceImpl();
        consoleOutputService.outputExactMatchedTransactions(exactMatchTransactionsMap);
        consoleOutputService.outputPartialMatchedTransactions(partialMatchTransactionsMap);
        consoleOutputService.outputOnlyBuyerTransactions(onlyBuyerTxns);
        consoleOutputService.outputOnlySupplierTransactions(onlySupplierTxns);
    }

}



