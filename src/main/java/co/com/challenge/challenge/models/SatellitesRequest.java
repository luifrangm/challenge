package co.com.challenge.challenge.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SatellitesRequest {
  private SatellitesModel[] satellites;

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    for (SatellitesModel satellite : satellites) {
      builder.append(satellite.toString())
          .append(" ");
    }
    return builder.toString();
  }
}
