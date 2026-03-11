package org.bankapp.service.client;

import org.bankapp.DTO.CardDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Cardsfallback implements CardFeingClient{

    @Override
    public ResponseEntity<CardDTO> findCard(String traceId, String mobileNumber) {
        return null;
    }
}
