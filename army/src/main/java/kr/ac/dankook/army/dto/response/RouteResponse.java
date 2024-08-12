package kr.ac.dankook.army.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RouteResponse {
    private String distance;
    private int taxiFare;
    private int carTotalTime;
    private int roadTotalTime;
    private int totalFare;
}
