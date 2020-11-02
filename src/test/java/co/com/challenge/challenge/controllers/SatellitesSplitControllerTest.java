package co.com.challenge.challenge.controllers;

import co.com.challenge.challenge.models.PointModel;
import co.com.challenge.challenge.models.SatellitesResponse;
import co.com.challenge.challenge.providers.SampleDataProvider;
import co.com.challenge.challenge.services.SatellitesService;
import java.util.Objects;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class SatellitesSplitControllerTest {

  private SatellitesSplitController controller;

  @Mock
  private SatellitesService service;
  private static final double DELTA = 0.1;

  @Before
  public void init() {
    MockitoAnnotations.openMocks(this);
    controller = new SatellitesSplitController(service);
  }

  @Test
  public void getLocationTestOk() {
    final int statusExpect = 200;
    final String messageExpect = "este es un mensaje secreto";
    final PointModel positionExpect =
        SampleDataProvider.buildPointModel(200,200);

    Mockito
        .when(service.getLocation())
        .thenReturn(SampleDataProvider.buldSatellitesResponse_Ok());

    ResponseEntity<SatellitesResponse> response =
        controller.getLocation();

    Assert.assertNotNull(response);
    Assert.assertEquals(statusExpect,response.getStatusCodeValue());
    Assert.assertEquals(messageExpect,
        Objects.requireNonNull(response.getBody()).getMessage());
    Assert.assertEquals(positionExpect.getComponentX(),
        response.getBody().getPosition().getComponentX(),DELTA);
    Assert.assertEquals(positionExpect.getComponentY(),
        response.getBody().getPosition().getComponentY(), DELTA);
  }

  @Test
  public void getLocationTestFail() {
    final int statusExpect = 404;

    Mockito
        .when(service.getLocation())
        .thenReturn(SampleDataProvider.buldSatellitesResponse_Fail());

    ResponseEntity<SatellitesResponse> response =
        controller.getLocation();

    Assert.assertNotNull(response);
    Assert.assertEquals(statusExpect,response.getStatusCodeValue());
  }

  @Test
  public void setLocationTest() {


    Mockito
        .doNothing()
        .when(service).setData(Mockito.any());

    controller.setLocation("kenobi",
        SampleDataProvider.buildSatellitesModel(
            "",316.2F, new String[]{"este", "", "", "mensaje", ""}));

    Mockito.verify(service,Mockito.times(1))
        .setData(Mockito.any());
  }
}


