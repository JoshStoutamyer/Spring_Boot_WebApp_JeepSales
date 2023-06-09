package com.promineotech.jeep.controller;

/*
 * JUnit test class. Consolidated the various degrees of separation that the videos
 * displayed as per the assignment document, so there is no test Support package.
 */
import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import lombok.Getter;

// declaring that test run in a web environment, like the actual program
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // defines this as a test for the yaml
@Sql(
    scripts = {"classpath:flyway/migrations/V1.0__Jeep_Schema.sql", 
        "classpath:flyway/migrations/V1.1__Jeep_Data.sql"},
    config = @SqlConfig(encoding = "utf-8"))
class FetchJeepTest {
  
  @LocalServerPort
  private int serverPort;

  @Autowired
  @Getter
  private TestRestTemplate restTemplate;
  
  /*
   * Test method. Testing that by providing a valid option for our Web app
   * we can establish a connection, and then return a response. Hopefully and OK
   * response.
   */
  @Test
  void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied() {
    // Given: a valid model, trim and URI
    JeepModel model = JeepModel.WRANGLER;
    String trim = "Sport";
    String uri = 
        String.format("http://localhost:%d/jeeps?model=%s&trim=%s", serverPort, model, trim);
    
    // When: a connection is made to the URI
    ResponseEntity<List<Jeep>> response = 
        restTemplate.exchange(uri, HttpMethod.GET, null,
            new ParameterizedTypeReference<>() {});
    
    // Then: a success (OK - 200) status code is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    
    // For week 14 initial video example, comment out lines 70-71 for the test to pass.
    // And: the actual list returned is the same as the expect list
    List<Jeep> expected = buildExpected();
    assertThat(response.getBody()).isEqualTo(expected);
  }

  /*
   * List of Jeep to compare to the expected list of Jeeps
   */
 protected List<Jeep> buildExpected() {
    List<Jeep> list = new LinkedList<>();
    
    //@formatter:off
    list.add(Jeep.builder()
        .modelId(JeepModel.WRANGLER)
        .trimLevel("Sport")
        .numDoors(2)
        .wheelSize(17)
        .basePrice(new BigDecimal("28475.00"))
        .build());
    
    list.add(Jeep.builder()
        .modelId(JeepModel.WRANGLER)
        .trimLevel("Sport")
        .numDoors(4)
        .wheelSize(17)
        .basePrice(new BigDecimal("31975.00"))
        .build());
    
    //@formatter:on
    return list;
  }
  
}
