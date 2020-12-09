package br.com.zup.jocivaldias.transaction.dto.response;


import br.com.zup.jocivaldias.transaction.entity.Transaction;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponse {

    private String id;
    private BigDecimal value;

    @JsonProperty("store")
    private TransactionStoreResponse transactionStoreResponse;

    @JsonProperty("effective_on")
    private LocalDateTime effectiveOn;

    public TransactionResponse(String id,
                               BigDecimal value,
                               TransactionStoreResponse transactionStoreResponse,
                               LocalDateTime effectiveOn) {
        this.id = id;
        this.value = value;
        this.transactionStoreResponse = transactionStoreResponse;
        this.effectiveOn = effectiveOn;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public TransactionStoreResponse getTransactionStoreResponse() {
        return transactionStoreResponse;
    }

    public LocalDateTime getEffectiveOn() {
        return effectiveOn;
    }

    public static TransactionResponse fromModel(Transaction transaction) {
        TransactionStoreResponse transactionStoreResponse = new TransactionStoreResponse(transaction.getStoreName(),
                transaction.getStoreCity(),
                transaction.getStoreAddress());

        return new TransactionResponse(transaction.getId(),
                transaction.getValue(),
                transactionStoreResponse,
                transaction.getEffectiveOn());
    }
}
