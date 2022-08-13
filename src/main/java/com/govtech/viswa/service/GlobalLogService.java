package com.govtech.viswa.service;

import com.govtech.viswa.entity.GlobalLog;
import com.govtech.viswa.repo.GlobalLogRepo;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author saravan on 2021 Jul 24
 * @project beapp
 * @package com.jcs.beapp.service.ge
 * @class GlobalLogService
 */

@Log4j2
@Service
@AllArgsConstructor
public class GlobalLogService {

    @Autowired
    GlobalLogRepo globalLogRepo;

    @Transactional
    public void save( GlobalLog globalLog ) {
        log.error( globalLog );
        globalLogRepo.saveAndFlush( globalLog );
    }

}
