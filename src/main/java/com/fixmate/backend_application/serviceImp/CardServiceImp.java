package com.fixmate.backend_application.serviceImp;
import com.fixmate.backend_application.dto.CardRequestDTO;
import com.fixmate.backend_application.dto.CardResponseDTO;
import com.fixmate.backend_application.entity.Card;
import com.fixmate.backend_application.entity.User;
import com.fixmate.backend_application.exceptions.CardNotFoundException;
import com.fixmate.backend_application.exceptions.UserNotFoundException;
import com.fixmate.backend_application.mapper.CardMapper;
import com.fixmate.backend_application.repository.CardRepository;
import com.fixmate.backend_application.repository.UserRepository;
import com.fixmate.backend_application.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CardServiceImp implements CardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardMapper cardMapper;

    @Autowired
    private CardRepository cardRepository;

    @Override
    public CardResponseDTO createCard(CardRequestDTO dto) {
        Card card = cardMapper.toEntity(dto);
        Card savedCard = cardRepository.save(card);
        return cardMapper.toDTO(savedCard);
    }

    @Override
    public List<CardResponseDTO> getAllCardsByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
        return cardMapper.toDTOList(cardRepository.findAll());
    }

    @Override
    public CardResponseDTO updateCard(Long cardId, CardRequestDTO dto) {
        Card card = cardRepository.findById(cardId).orElseThrow(()->new CardNotFoundException(cardId));
        card.setCardHolder(dto.getCardHolder());
        card.setCvv(dto.getCvv());
        card.setExpiryDate(dto.getExpiryDate());
        card.setCardNumber(dto.getCardNumber());
        card.setBankName(dto.getBankName());
        card.setAccountNumber(dto.getAccountNumber());
        Card updatedCard = cardRepository.save(card);
        return cardMapper.toDTO(card);
    }

    @Override
    public void deleteById(Long cardId) {
        Card card = cardRepository.findById(cardId).orElseThrow(()->new CardNotFoundException(cardId));
        cardRepository.deleteById(cardId);
    }

    @Override
    public List<CardResponseDTO> getAllCards() {
        return cardMapper.toDTOList(cardRepository.findAll());
    }
}
