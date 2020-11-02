package co.com.challenge.challenge.services;

import co.com.challenge.challenge.components.SatellitesComponent;
import co.com.challenge.challenge.models.PointModel;
import co.com.challenge.challenge.models.SatellitesResponse;
import co.com.challenge.challenge.providers.SampleDataProvider;
import co.com.challenge.challenge.singletons.SatellitesSingleton;
import java.util.Objects;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class SatellitesServiceTest {

  private SatellitesService service;

  @Mock private SatellitesComponent component;
  @Mock private SatellitesSingleton singleton;

  private static final double DELTA = 0.1;

  @Before
  public void init() throws NoSuchFieldException{
    MockitoAnnotations.openMocks(this);
    service = new SatellitesService(component);
  }

  @Test
  public void getLocationTestOk() {
    final String messageExpect = "este es un mensaje secreto";
    final PointModel positionExpect =
        SampleDataProvider.buildPointModel(200,200);

    Mockito.when(component.getLocationInfo(
        Mockito.any()))
        .thenReturn(SampleDataProvider.buldSatellitesResponse_Ok());

    SatellitesResponse response =
        service.getLocation(SampleDataProvider.buildSatellitesRequest());

    Assert.assertNotNull(response);
    Assert.assertEquals(messageExpect,
        Objects.requireNonNull(response.getMessage()));
    Assert.assertEquals(positionExpect.getComponentX(),
        response.getPosition().getComponentX(),DELTA);
    Assert.assertEquals(positionExpect.getComponentY(),
        response.getPosition().getComponentY(), DELTA);
  }

  @Test
  public void getLocationTestOutParameterOk() {
    final String messageExpect = "este es un mensaje secreto";
    final PointModel positionExpect =
        SampleDataProvider.buildPointModel(200,200);

    Mockito.when(component.getLocationInfo())
        .thenReturn(SampleDataProvider.buldSatellitesResponse_Ok());

    SatellitesResponse response =
        service.getLocation();

    Assert.assertNotNull(response);
    Assert.assertEquals(messageExpect,
        Objects.requireNonNull(response.getMessage()));
    Assert.assertEquals(positionExpect.getComponentX(),
        response.getPosition().getComponentX(),DELTA);
    Assert.assertEquals(positionExpect.getComponentY(),
        response.getPosition().getComponentY(), DELTA);
  }

}


