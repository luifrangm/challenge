package co.com.challenge.challenge.components;

import co.com.challenge.challenge.models.SatellitesResponse;
import co.com.challenge.challenge.providers.SampleDataProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SatellitesComponentTest {

  public static final float DELTA = 0.5F;
  private SatellitesComponent component;

  @Before
  public void init() {
    component = new SatellitesComponent();
  }

  @Test
  public void getLocationInfoTestOk() {
    final float componentX = -201.0F;
    final float componentY = -96.7F;
    final String message = "este es un mensaje secreto";

    SatellitesResponse response =
        component.getLocationInfo(SampleDataProvider.buildSatellitesRequest());

    Assert.assertNotNull(response);
    Assert.assertEquals(componentX,response.getPosition().getComponentX(), DELTA);
    Assert.assertEquals(componentY,response.getPosition().getComponentY(), DELTA);
    Assert.assertEquals(message,response.getMessage());

  }

}


