package co.com.challenge.challenge.services;

import co.com.challenge.challenge.models.SatellitesModel;
import co.com.challenge.challenge.models.SatellitesRequest;
import co.com.challenge.challenge.models.SatellitesResponse;

public interface ISatellitesService {
  SatellitesResponse getLocation(final SatellitesRequest request);
  void setData(SatellitesModel request);
}
