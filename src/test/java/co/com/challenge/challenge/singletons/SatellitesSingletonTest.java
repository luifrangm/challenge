package co.com.challenge.challenge.singletons;

import co.com.challenge.challenge.models.SatellitesModel;
import co.com.challenge.challenge.providers.SampleDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SatellitesSingletonTest {

  public static final float DELTA = 15F;
  private SatellitesSingleton singleton =
      SatellitesSingleton.getInstance();

  @Test
  public void getSatelliteDataTest() {

    final String nameKenobi = "kenobi";
    final float distanceKenobi = 500F;
    final String messageKenobi = "message from kenobi";
    final String nameSkywalker = "skywalker";
    final float distanceSkywalker = 300F;
    final String messageSkywalker = "message from Skywalker";
    final String nameSato = "sato";
    final float distanceSato = 400F;
    final String messageSato = "message from sato";

    singleton.setData(SampleDataProvider.buildSatellitesModel(
        nameKenobi,distanceKenobi,new String[] {messageKenobi}));
    singleton.setData(SampleDataProvider.buildSatellitesModel(
        nameSkywalker,distanceSkywalker,new String[] {messageSkywalker}));
    singleton.setData(SampleDataProvider.buildSatellitesModel(
        nameSato,distanceSato,new String[] {messageSato}));

    final SatellitesModel kenobi = singleton.getSatelliteData("kenobi");
    final SatellitesModel skywalker = singleton.getSatelliteData("skywalker");
    final SatellitesModel sato = singleton.getSatelliteData("sato");

    Assert.assertNotNull(kenobi);
    Assert.assertNotNull(skywalker);
    Assert.assertNotNull(sato);
    Assert.assertEquals(nameKenobi,kenobi.getName());
    Assert.assertEquals(nameSkywalker,skywalker.getName());
    Assert.assertEquals(nameSato,sato.getName());
    Assert.assertEquals(distanceKenobi,kenobi.getDistance(), DELTA);
    Assert.assertEquals(distanceSkywalker,skywalker.getDistance(),DELTA);
    Assert.assertEquals(distanceSato,sato.getDistance(),DELTA);
    Assert.assertEquals(messageKenobi,kenobi.getMessage()[0]);
    Assert.assertEquals(messageSkywalker,skywalker.getMessage()[0]);
    Assert.assertEquals(messageSato,sato.getMessage()[0]);

  }

}


