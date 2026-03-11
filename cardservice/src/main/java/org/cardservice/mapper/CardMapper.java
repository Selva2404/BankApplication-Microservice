package org.cardservice.mapper;

import org.cardservice.DTO.CardDTO;
import org.cardservice.Entity.Card;

public class CardMapper {

    public static CardDTO mapToCardDTO(Card card, CardDTO cardDTO){
        cardDTO.setBillAmount(card.getBillAmount());
        cardDTO.setCardLimit(card.getCardLimit());
        cardDTO.setCardNumber(card.getCardNumber());
        cardDTO.setCardType(card.getCardType());
        cardDTO.setMobileNumber(card.getMobileNumber());
        cardDTO.setOutstandingAmount(card.getOutstandingAmount());
        return cardDTO;
    }

    public static Card mapToCard(CardDTO cardDTO, Card card){
        card.setBillAmount(cardDTO.getBillAmount());
        card.setCardLimit(cardDTO.getCardLimit());
        //card.setCardNumber(cardDTO.getCardNumber());
        card.setCardType(cardDTO.getCardType());
        card.setMobileNumber(cardDTO.getMobileNumber());
        card.setOutstandingAmount(cardDTO.getOutstandingAmount());
        return card;
    }
}
