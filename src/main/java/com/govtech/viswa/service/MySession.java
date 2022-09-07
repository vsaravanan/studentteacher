package com.govtech.viswa.service;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * @author saravan on 2021 Aug 01
 * @project beapp
 * @package com.jcs.beapp.service.ge
 * @class MySession
 */

@Data
@Scope (  value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component

public class MySession {

    String userId = "sss"; // default value for simple sample application
    String input = "";
    // in real application it will be updated properly


}
