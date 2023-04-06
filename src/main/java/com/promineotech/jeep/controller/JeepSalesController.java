package com.promineotech.jeep.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.annotation.RequestScope;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
/*
 *  Interface for the controller to inherit, helps keeps things modular.
 *  
 *  RequestMapping allows us to map the /jeeps request onto methods in the 
 *  request-handling class.
 */
@RequestMapping("/jeeps")
// our swagger UI
//Make sure the server you initialize is HTTP.
// Otherwise you will have to enable HTTPS.
@OpenAPIDefinition(info = @Info(title = "Jeep Sales Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})
public interface JeepSalesController {
  
  // messages for our response codes
  // formatter:off
  @Operation(
      summary = "Returns a list of Jeeps",
      description = "Returns a list of Jeeps given an optional model/or trim",
      responses = {
          @ApiResponse(
              responseCode = "200", 
              description = "A list of Jeeps is returned", 
              content = @Content(
                  mediaType = "application/json", 
                  schema =  @Schema(implementation = Jeep.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request paramenter are invalid", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Jeeps were found with the input criteria", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred", 
              content = @Content(mediaType = "application/json"))
      },
     // parameters used by the fetchJeeps method below
      parameters = {
          @Parameter(
              name = "model", 
              allowEmptyValue = false, 
              required = false, 
              description = "The model name (i.e., 'WRANGLER')"),
          @Parameter(
              name = "trim", 
              allowEmptyValue = false, 
              required = false, 
              description = "the trim level (i.e., 'Sport')")
      }
      )
  // @formatter:on
  // maps a get request to the method
  @GetMapping
  // Status update
  @ResponseStatus(code = HttpStatus.OK)
  // method to fetch jeeps based on model and trim.
  List<Jeep> fetchJeeps(
      @RequestParam (required = false) JeepModel model, 
      @RequestParam (required = false) String trim);
}
