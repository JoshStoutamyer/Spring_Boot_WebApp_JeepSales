package com.promineotech.jeep.entity;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//adds getter and setters
@Data
//creates constructors for each field below, able to accept
//args or not. 
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Jeep {
  private long modelPK;
  private JeepModel modelId;
  private String trimLevel;
  private int numDoors;
  private int wheelSize;
  private BigDecimal basePrice;
  
  @JsonIgnore
  public Long getModelPK() {
    return modelPK;
  }

}
