package com.bdg.transform.response.transaction;

import com.bdg.entity.transaction.Transaction;
import lombok.Data;

import java.util.List;

@Data
public class TransactionsListResponse {

    private List<Transaction> transactions;
}
