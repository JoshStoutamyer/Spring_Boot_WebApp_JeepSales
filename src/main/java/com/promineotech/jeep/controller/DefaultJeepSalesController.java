package com.promineotech.jeep.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.service.JeepSalesService;
import lombok.extern.slf4j.Slf4j;

/*
 * RestController lets Spring know that this class uses the RESTful architecture
 * and is the controller for JeepSalesController. 
 */

@RestController
@Slf4j
public class DefaultJeepSalesController implements JeepSalesController {

  @Autowired
  private JeepSalesService jeepSalesService;
  
  @Override
  public List<Jeep> fetchJeeps(JeepModel model, String trim) {
    /*
     * logger to track activity on the web server.
     * follows parameters that have been mapped onto the URI
     */
     log.info("model={}, trim={}", model, trim);
     // passing it through the service layer
     return jeepSalesService.fetchJeeps(model, trim);
   }

}
