package com.fixmate.backend_application.service;
import com.fixmate.backend_application.dto.CardRequestDTO;
import com.fixmate.backend_application.dto.CardResponseDTO;
import java.util.List;

public interface CardService {

    CardResponseDTO createCard(CardRequestDTO dto);
    List<CardResponseDTO> getAllCardsByUser(Long userId);
    CardResponseDTO updateCard(Long cardId, CardRequestDTO dto);
    void deleteById(Long cardId);
    List<CardResponseDTO> getAllCards();
}
