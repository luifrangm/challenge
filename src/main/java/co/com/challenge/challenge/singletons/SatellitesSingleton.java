package co.com.challenge.challenge.singletons;

import co.com.challenge.challenge.models.SatellitesModel;
import java.util.logging.Logger;

public class SatellitesSingleton {

  private static final Logger LOGGER = Logger.getLogger(SatellitesSingleton.class.getName());

  private static final String KENOBI = "kenobi";
  private static final String SKYWALKER = "skywalker";
  private static final String SATO = "sato";
  public static final String THE_NAME_OF_THE_SATELLITE_DOES_NOT_EXIST = "the name of the satellite does not exist";
  private static SatellitesSingleton singletonInstance;

  private SatellitesModel kenobiInfo;
  private SatellitesModel skywalkerInfo;
  private SatellitesModel satoInfo;

  private SatellitesSingleton() {

  }

  public static SatellitesSingleton getInstance() {
    if(singletonInstance == null) {
      singletonInstance = new SatellitesSingleton();
    }
    return singletonInstance;
  }

  public SatellitesModel getSatelliteData(final String satelliteName) {
    switch (satelliteName) {
      case KENOBI:
        return kenobiInfo;
      case SKYWALKER:
        return skywalkerInfo;
      case SATO:
        return satoInfo;
      default:
        LOGGER.warning(THE_NAME_OF_THE_SATELLITE_DOES_NOT_EXIST);
    }
    return SatellitesModel.builder().build();
  }

  public void setData(final SatellitesModel data) {
    switch (data.getName()) {
      case KENOBI:
        kenobiInfo = data;
        break;
      case SKYWALKER:
        skywalkerInfo = data;
        break;
      case SATO:
        satoInfo = data;
        break;
      default:
        LOGGER.warning(THE_NAME_OF_THE_SATELLITE_DOES_NOT_EXIST);
    }
  }


}


