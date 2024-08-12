package kr.ac.dankook.army.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LocationRequest {
    private String originX;
    private String originY;
    private String destinationX;
    private String destinationY;
}
