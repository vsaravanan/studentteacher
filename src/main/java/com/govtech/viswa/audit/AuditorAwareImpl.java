package com.govtech.viswa.audit;

import com.govtech.viswa.service.MySession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Log4j2
public class AuditorAwareImpl implements AuditorAware<String> {


    @Autowired
    MySession mySession;

    @Override
    public Optional<String> getCurrentAuditor() {
        String userId = "saravan";
        try {
            userId = mySession.getUserId();
        } catch ( Exception ex ) {

        }
        return Optional.of( userId );
    }
}
