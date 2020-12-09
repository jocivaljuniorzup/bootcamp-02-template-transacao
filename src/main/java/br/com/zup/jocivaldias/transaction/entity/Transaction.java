package br.com.zup.jocivaldias.transaction.entity;

import antlr.actions.python.CodeLexer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    private String id;

    @NotNull
    @Column(nullable = false)
    private BigDecimal value;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime effectiveOn;

    @NotNull
    @Column(nullable = false)
    private String storeName;

    @NotNull
    @Column(nullable = false)
    private String storeCity;

    @NotNull
    @Column(nullable = false)
    private String storeAddress;

    @ManyToOne(optional = false)
    private Card card;

    @Deprecated
    private Transaction() {
    }

    public Transaction(String id,
                       @NotNull BigDecimal value,
                       @NotNull LocalDateTime effectiveOn,
                       @NotNull String storeName,
                       @NotNull String storeCity,
                       @NotNull String storeAddress,
                       Card card) {
        this.id = id;
        this.value = value;
        this.effectiveOn = effectiveOn;
        this.storeName = storeName;
        this.storeCity = storeCity;
        this.storeAddress = storeAddress;
        this.card = card;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public LocalDateTime getEffectiveOn() {
        return effectiveOn;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getStoreCity() {
        return storeCity;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public Card getCard() {
        return card;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", value=" + value +
                ", effectiveOn=" + effectiveOn +
                ", card=" + card +
                '}';
    }
}
