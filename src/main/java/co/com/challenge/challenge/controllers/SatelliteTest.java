package co.com.challenge.challenge.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${satellite.location.base.url}")
@CrossOrigin
public class SatelliteTest {

  @GetMapping(value = "status")
  public String checkStatus() {
    return "ok";
  }
}