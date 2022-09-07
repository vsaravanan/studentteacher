package com.govtech.viswa.config;

import com.govtech.viswa.service.MySession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ReactiveAuditorAware;
import reactor.core.publisher.Mono;

public class MyReactiveAuditorAware implements ReactiveAuditorAware<String> {


/*    @Override
    public Optional<User> getCurrentAuditor() {

        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(User.class::cast);
    } */


    @Autowired
    MySession mySession;

    @Override
    public Mono<String> getCurrentAuditor() {
        String userId = "saravan";
        try {
            userId = mySession.getUserId();
        } catch ( Exception ex ) {

        }
        return Mono.just(userId);
    }
}
