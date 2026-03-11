package org.cardservice.audite;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("cardAuditing")
public class cardAuditing implements AuditorAware {
    @Override
    public Optional getCurrentAuditor() {
        return Optional.of("Card_Service");
    }
}
