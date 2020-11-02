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
public class SatellitesModel {
  private String name;
  private float distance;
  private String[] message;

  @Override
  public String toString() {
    final StringBuilder builder =
        new
            StringBuilder("name: ").append(name)
            .append(" distance: ").append(distance)
            .append(" message ").append(String.join(" ",message));
    return builder.toString();

  }
}
