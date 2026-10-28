package com.fixmate.backend_application.mapper;
import com.fixmate.backend_application.dto.CardRequestDTO;
import com.fixmate.backend_application.dto.CardResponseDTO;
import com.fixmate.backend_application.entity.Card;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CardMapper {

    //1. toEntity
    public Card toEntity(CardRequestDTO dto){
        if(dto==null){return null;}
        Card card = new Card();
        card.setId(dto.getId());
        card.setCardHolder(dto.getCardHolder());
        card.setCvv(dto.getCvv());
        card.setExpiryDate(dto.getExpiryDate());
        card.setCardNumber(dto.getCardNumber());
        card.setBankName(dto.getBankName());
        card.setAccountNumber(dto.getAccountNumber());
        card.setUserId(dto.getUserId());
        card.setCreatedAt(dto.getCreatedAt());
        return card;
    }

    //2. toDTO
    public CardResponseDTO toDTO(Card card){
        if(card==null){return null;}
        CardResponseDTO dto = new CardResponseDTO();
        dto.setId(card.getId());
        dto.setCardHolder(card.getCardHolder());
        dto.setCvv(card.getCvv());
        dto.setExpiryDate(card.getExpiryDate());
        dto.setCardNumber(card.getCardNumber());
        dto.setBankName(card.getBankName());
        dto.setAccountNumber(card.getAccountNumber());
        dto.setUserId(card.getUserId());
        dto.setCreatedAt(card.getCreatedAt());
        return dto;
    }

    //3. toDTOList
    public List<CardResponseDTO> toDTOList(List<Card> cards){
        if(cards.isEmpty()){Collections.emptyList();}
        return cards.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
