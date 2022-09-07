package com.govtech.viswa.repo;

import com.govtech.viswa.entity.GlobalLog;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


/**
 * @author Sarav on 10 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.repo
 * @class GlobalLogRepo
 */

//@Repository
public interface GlobalLogRepo extends ReactiveCrudRepository<GlobalLog, Long > {

}
