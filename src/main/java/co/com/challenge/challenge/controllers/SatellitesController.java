package co.com.challenge.challenge.controllers;

import co.com.challenge.challenge.models.SatellitesRequest;
import co.com.challenge.challenge.models.SatellitesResponse;
import co.com.challenge.challenge.services.SatellitesService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${satellite.location.base.url}")
@CrossOrigin
public class SatellitesController {

  private static final Logger LOGGER = Logger.getLogger(SatellitesController.class.getName());
  public static final String ENDPOINT = "/topsecret";
  private final SatellitesService service;

  @Autowired
  public SatellitesController(SatellitesService service) {
    this.service = service;
  }

  @PostMapping(ENDPOINT)
  @ResponseBody
  public ResponseEntity<SatellitesResponse> getLocation (@RequestBody final SatellitesRequest request) {

    LOGGER.log(
        Level.FINE, "init SatellitesController - getLocation with: {}",new Object[]{request});

    SatellitesResponse response = service.getLocation(request);
    return
        response.getMessage() != null
            ? ResponseEntity.ok(response)
            : ResponseEntity.notFound().build();
  }

}

