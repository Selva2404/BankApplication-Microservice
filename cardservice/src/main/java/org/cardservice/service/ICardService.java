package org.cardservice.service;

import org.cardservice.DTO.CardDTO;

public interface ICardService {

     void createCardDetails(String mobileNumber);

     CardDTO findCardDetails(String mobileNumber);

     boolean updateCardDetails(CardDTO CardDTO);

     void deleteCardDetails(String mobileNumber);
}
