package co.com.challenge.challenge.controllers;

import co.com.challenge.challenge.models.SatellitesModel;
import co.com.challenge.challenge.models.SatellitesResponse;
import co.com.challenge.challenge.services.SatellitesService;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${satellite.location.base.url}")
@CrossOrigin
public class SatellitesSplitController {

  private static final Logger LOGGER = Logger.getLogger(SatellitesSplitController.class.getName());
  private static final String ENDPOINT = "/topsecret_split";
  private final SatellitesService service;

  @Autowired
  public SatellitesSplitController(SatellitesService service) {
    this.service = service;
  }

  @GetMapping(ENDPOINT)
  @ResponseBody
  public ResponseEntity<SatellitesResponse> getLocation() {
    LOGGER.info("init SatellitesSplitController - getLocation without parameters");
    SatellitesResponse response = service.getLocation();
    return
        response.getMessage() != null
            ? ResponseEntity.ok(service.getLocation())
            : ResponseEntity.notFound().build();
  }

  @PostMapping(ENDPOINT +  "/{satellite_name}")
  public ResponseEntity<Void> setLocation (
      @PathVariable("satellite_name") final String satelliteName,
      @RequestBody final SatellitesModel request) {

    request.setName(satelliteName);
    service.setData(request);
    return
        ResponseEntity.accepted().build();
  }
}

