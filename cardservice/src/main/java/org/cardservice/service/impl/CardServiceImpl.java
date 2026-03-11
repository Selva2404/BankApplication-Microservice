package org.cardservice.service.impl;

import org.cardservice.DTO.CardDTO;
import org.cardservice.Exception.CustomeException.CardAvailableException;
import org.cardservice.service.ICardService;

import org.cardservice.Entity.Card;
import org.cardservice.Exception.CustomeException.ResourceNotFoundException;
import org.cardservice.Repository.CardRepository;
import org.cardservice.mapper.CardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
@AllArgsConstructor
public class CardServiceImpl implements ICardService {


    CardRepository cardRepository;
    @Override
    public void createCardDetails(String mobileNumber) {
        Card card = new Card();
        card.setMobileNumber(mobileNumber);
        Optional<Card> loanDetails=cardRepository.findByMobileNumber(mobileNumber);
        if(loanDetails.isPresent()){
            throw new CardAvailableException(" card already exists for this user  " + mobileNumber);
        }
        cardRepository.save(updateCard(card));

    }
    private Card updateCard(Card card) {

        Long cardNumber =100000000000L+new Random().nextInt(900000000);
        card.setCardNumber(cardNumber);
        card.setCardLimit("100000");
        card.setOutstandingAmount("12000");
        card.setBillAmount("12000");
        card.setCardType("Business Card");
        return card;
    }


    @Override
    public CardDTO findCardDetails(String mobileNumber) {
        Card card=cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("card","mobileNumber", mobileNumber)
        );
      //  log.debug("loan ="+card);
        return CardMapper.mapToCardDTO(card,new CardDTO());
    }


    @Override
    public boolean updateCardDetails(CardDTO cardDTO) {
        boolean isUpdated=false;
        if(cardDTO.getMobileNumber() != null) {
            Card card = cardRepository.findByMobileNumber(cardDTO.getMobileNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("card", "mobileNumber", cardDTO.getMobileNumber())
            );
            log.debug("card=>"+card);
            CardMapper.mapToCard(cardDTO, card);
            cardRepository.save(card);
            isUpdated = true;
        }
    return isUpdated;
    }

    @Override
    public void deleteCardDetails(String mobileNumber) {
        cardRepository.deleteByMobileNumber(mobileNumber);
    }
}
