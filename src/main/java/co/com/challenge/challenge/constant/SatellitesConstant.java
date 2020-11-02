package co.com.challenge.challenge.constant;


import co.com.challenge.challenge.models.PointModel;

public class SatellitesConstant {

  private SatellitesConstant() {}

  private static final PointModel KENOBI_POSITION =
      PointModel.builder()
          .componentX(-500)
          .componentY(-200)
          .build();

  private static final PointModel SKYWALKER_POSITION =
      PointModel.builder()
          .componentX(100)
          .componentY(-100)
          .build();

  private static final PointModel SATO_POSITION =
      PointModel.builder()
          .componentX(500)
          .componentY(100)
          .build();

  public static PointModel getKenobiLocation() {
    return KENOBI_POSITION;
  }
  public static PointModel getSkywalkerLocation() {
    return SKYWALKER_POSITION;
  }
  public static PointModel getSatoLocation() {
    return SATO_POSITION;
  }

}

