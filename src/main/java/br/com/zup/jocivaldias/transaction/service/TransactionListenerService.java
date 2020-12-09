package br.com.zup.jocivaldias.transaction.service;

import br.com.zup.jocivaldias.transaction.dto.event.TransactionEvent;
import br.com.zup.jocivaldias.transaction.dto.request.TransactionCardRequest;
import br.com.zup.jocivaldias.transaction.entity.Card;
import br.com.zup.jocivaldias.transaction.entity.Transaction;
import br.com.zup.jocivaldias.transaction.repository.CardRepository;
import br.com.zup.jocivaldias.transaction.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;


@Component
public class TransactionListenerService {

    private CardRepository cardRepository;
    private TransactionRepository transactionRepository;
    private Logger logger = LoggerFactory.getLogger(TransactionListenerService.class);

    public TransactionListenerService(CardRepository cardRepository,
                                      TransactionRepository transactionRepository) {
        this.cardRepository = cardRepository;
        this.transactionRepository = transactionRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void listen(TransactionEvent transactionEvent) {
        TransactionCardRequest cardRequest = transactionEvent.getCardRequest();
        Optional<Card> optionalCard = cardRepository.findByCardNumber(cardRequest.getId());

        Card card = optionalCard.orElse(new Card(cardRequest.getId(), cardRequest.getEmail()));

        if(card.getId() == null) {
            cardRepository.save(card);
        }

        Transaction transaction = transactionEvent.toModel(card);
        transaction.toString();
        transactionRepository.save(transaction);
        logger.info("New transaction received: " + transactionEvent);
    }

}