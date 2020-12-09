package br.com.zup.jocivaldias.transaction.controller;

import br.com.zup.jocivaldias.transaction.dto.response.TransactionResponse;
import br.com.zup.jocivaldias.transaction.entity.Transaction;
import br.com.zup.jocivaldias.transaction.repository.TransactionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/cards")
public class CardTransactionController {

    private TransactionRepository transactionRepository;

    public CardTransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping(path = "/{id}/transactions")
    public ResponseEntity<?> findLastTransactions(@PathVariable(name = "id") UUID cardId){
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "effectiveOn"));
        List<Transaction> lastTransactions = transactionRepository.findAll(pageable).getContent();

        List<TransactionResponse> transactionResponseList = lastTransactions.stream()
                .map(TransactionResponse::fromModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(transactionResponseList);
    }

}
