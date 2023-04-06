package com.promineotech.jeep.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import lombok.extern.slf4j.Slf4j;

// Tells Spring to manage this class. Becomes a Bean!
@Service
@Slf4j
public class DefaultJeepSalesService implements JeepSalesService {
  
  @Override
  public List<Jeep> fetchJeeps(JeepModel model, String trim) {
    log.info("The fetchJeeps method was called with model={} and trim={}"
        , model, trim);
    // For video example, otherwise comment out.
//    System.out.println("Model=" + model + " Trim=" + trim);
    return null;
  }
}
