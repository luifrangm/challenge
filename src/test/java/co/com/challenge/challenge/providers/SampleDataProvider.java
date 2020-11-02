package co.com.challenge.challenge.providers;

import co.com.challenge.challenge.models.LineEquation;
import co.com.challenge.challenge.models.PointModel;
import co.com.challenge.challenge.models.SatellitesModel;
import co.com.challenge.challenge.models.SatellitesRequest;
import co.com.challenge.challenge.models.SatellitesResponse;

public class SampleDataProvider {

  public static LineEquation buildFinalEquation() {
    return
        LineEquation.builder()
            .componentX(700)
            .componentY(10)
            .ordered(3)
            .build();
  }

  public static SatellitesRequest buildSatellitesRequest() {
    return
        SatellitesRequest.builder()
            .satellites(buildSatellitesModelList())
            .build();
  }

  public static SatellitesModel buildSatellitesModel(
      final String name, final float distance, final String[] message) {
    return
        SatellitesModel.builder()
            .name(name)
            .distance(distance)
            .message(message)
            .build();
  }

  private static SatellitesModel[] buildSatellitesModelList() {
    SatellitesModel[] satellitesModelList = new SatellitesModel[3];
    satellitesModelList[0] = buildSatellitesModel("kenobi",316.2F,
        new String[]{"este", "", "", "mensaje", ""});
    satellitesModelList[1] = buildSatellitesModel("skywalker",300.0F,
        new String[]{"", "es", "", "", "secreto"});
    satellitesModelList[2] = buildSatellitesModel("sato",728.0F,
        new String[]{"este", "", "un", "", ""});
    return satellitesModelList;
  }


  public static SatellitesResponse buldSatellitesResponse_Ok() {
    return
        SatellitesResponse.builder()
            .message("este es un mensaje secreto")
            .position(buildPointModel(200,200))
            .build();
  }

  public static SatellitesResponse buldSatellitesResponse_Fail() {
    return
        SatellitesResponse.builder()
            .message(null)
            .position(buildPointModel(200,200))
            .build();
  }

  public static PointModel buildPointModel(
      final float componentX, final float componentY) {
    return
        PointModel.builder()
            .componentX(componentX)
            .componentY(componentY)
            .build();
  }

  public static LineEquation buildLineEquation() {
    return
        LineEquation.builder()
            .componentX(-400F)
            .componentY(-400F)
            .ordered(-170000.0F)
            .build();
  }

}


