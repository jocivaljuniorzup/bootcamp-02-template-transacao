package br.com.zup.jocivaldias.transaction.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "binary(16)")
    private UUID id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String cardNumber;

    @NotNull
    @Column(nullable = false)
    private String email;

    @Deprecated
    private Card() {
    }

    public Card(@NotNull String cardNumber, @NotNull String email) {
        this.cardNumber = cardNumber;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
