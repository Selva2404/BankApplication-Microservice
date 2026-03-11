package org.bankapp.service.client;

import org.bankapp.DTO.LoanDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansfallBack implements LoanFeingClient{
    @Override
    public ResponseEntity<LoanDTO> findLoan(String traceId, String mobileNumber) {
        return null;
    }
}
