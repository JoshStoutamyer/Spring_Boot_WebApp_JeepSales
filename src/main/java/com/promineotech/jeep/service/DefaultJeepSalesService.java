package com.promineotech.jeep.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineotech.jeep.dao.JeepSalesDao;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import lombok.extern.slf4j.Slf4j;

// Tells Spring to manage this class. Becomes a Bean!
@Service
@Slf4j // lombok logger
public class DefaultJeepSalesService implements JeepSalesService {

  @Autowired
  /*
   * getting a weird error with this variable, says it's not connected to a bean,
   * but DefaultJeepSalesDao implements JeepSalesDao and has the @service annotation.
   * UPDATE: Package name for com.promineotech.jeep.dao was misspelled. Check your package names!
   */
  private JeepSalesDao jeepsSalesDao; 
  
  @Override
  public List<Jeep> fetchJeeps(JeepModel model, String trim) {
    
    // lombok logger to show implementation of the DAO layer
    log.info("The fetchJeeps method was called with model={} and trim={}", model, trim);
    
    // calling a list of Jeeps form the DAO layer fetchJeeps Method. Turns our JUnit test green.
    List<Jeep> jeeps = jeepsSalesDao.fetchJeeps(model, trim);
    
    return jeeps;
  }
}
