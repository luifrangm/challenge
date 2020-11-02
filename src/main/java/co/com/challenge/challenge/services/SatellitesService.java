package co.com.challenge.challenge.services;

import co.com.challenge.challenge.components.SatellitesComponent;
import co.com.challenge.challenge.models.SatellitesModel;
import co.com.challenge.challenge.models.SatellitesRequest;
import co.com.challenge.challenge.models.SatellitesResponse;
import co.com.challenge.challenge.singletons.SatellitesSingleton;
import io.vavr.control.Try;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SatellitesService {

  private static final Logger LOGGER = Logger.getLogger(SatellitesService.class.getName());
  public static final String ERROR_WHEN_SAVING_THE_SATELLITE_INFORMATION = "error when saving the satellite information";
  private final SatellitesComponent component;
  private static final SatellitesSingleton SATELLITE_INFO_SINGLETON =
      SatellitesSingleton.getInstance();

  @Autowired
  public SatellitesService(SatellitesComponent component) {
    this.component = component;
  }

  public SatellitesResponse getLocation(final SatellitesRequest request) {
    return
        component.getLocationInfo(request);
  }

  public SatellitesResponse getLocation() {
    return
        component.getLocationInfo();
  }

  public void setData(SatellitesModel request) {
    Try.run(()->
        SATELLITE_INFO_SINGLETON.setData(request))
        .onFailure(
            throwable -> {
              LOGGER.log(Level.SEVERE,ERROR_WHEN_SAVING_THE_SATELLITE_INFORMATION);
              throw new NotFoundException(ERROR_WHEN_SAVING_THE_SATELLITE_INFORMATION,
                  Response.noContent().build());
            }
        );
  }

}

