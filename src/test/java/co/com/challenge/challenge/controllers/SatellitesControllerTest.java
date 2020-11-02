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
public class SatellitesControllerTest {

  private static final double DELTA = 0.1;
  private SatellitesController controller;

  @Mock private SatellitesService service;

  @Before
  public void init() {
    MockitoAnnotations.openMocks(this);
    controller = new SatellitesController(service);
  }

  @Test
  public void getLocationTest_Ok() {
    final int statusExpect = 200;
    final String messageExpect = "este es un mensaje secreto";
    final PointModel positionExpect =
        SampleDataProvider.buildPointModel(200,200);

    Mockito
        .when(service.getLocation(Mockito.any()))
        .thenReturn(SampleDataProvider.buldSatellitesResponse_Ok());

    ResponseEntity<SatellitesResponse> response =
        controller.getLocation(
            SampleDataProvider.buildSatellitesRequest());

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
  public void getLocationTest_Fail() {
    final int statusExpect = 404;

    Mockito
        .when(service.getLocation(Mockito.any()))
        .thenReturn(SampleDataProvider.buldSatellitesResponse_Fail());

    ResponseEntity<SatellitesResponse> response =
        controller.getLocation(
            SampleDataProvider.buildSatellitesRequest());

    Assert.assertNotNull(response);
    Assert.assertEquals(statusExpect,response.getStatusCodeValue());
  }


}


