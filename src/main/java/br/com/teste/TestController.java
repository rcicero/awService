package br.com.teste;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * teste status
 */
@RestController
@RequestMapping("/status")
public class TestController {
  
  @RequestMapping(method = RequestMethod.GET)
  public @ResponseBody ResponseEntity<String> getStatus() {
  
    return new ResponseEntity<String>("Olá!!! Eu estou bem. :)", HttpStatus.OK);
   
  }
  

}
