package com.fixmate.backend_application.controller;
import com.fixmate.backend_application.dto.CardRequestDTO;
import com.fixmate.backend_application.dto.CardResponseDTO;
import com.fixmate.backend_application.service.CardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    //TESTED
    @PostMapping
    public ResponseEntity<CardResponseDTO> createCard(@Valid @RequestBody CardRequestDTO dto){
        return new ResponseEntity<>(cardService.createCard(dto), HttpStatus.CREATED);
    }

    //TESTED
    @GetMapping
    public ResponseEntity<List<CardResponseDTO>> getAllCards(){
        return ResponseEntity.ok(cardService.getAllCards());
    }

    //TESTED
    @GetMapping("/user-card/{userId}")
    public ResponseEntity<List<CardResponseDTO>> getUserCardsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(cardService.getAllCardsByUser(userId));
    }

    //TESTED
    @PutMapping("/{cardId}")
    public ResponseEntity<CardResponseDTO> getCardById(@PathVariable Long cardId,
                                                       @Valid @RequestBody CardRequestDTO dto){
        return ResponseEntity.ok(cardService.updateCard(cardId, dto));
    }

    //TESTED
    @DeleteMapping("/{cardId}")
    public ResponseEntity<Void> deleteByCardId(@PathVariable Long cardId){
        cardService.deleteById(cardId);
        return ResponseEntity.noContent().build();
    }
}
