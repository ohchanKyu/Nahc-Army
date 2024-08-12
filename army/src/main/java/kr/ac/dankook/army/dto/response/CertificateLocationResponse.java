package kr.ac.dankook.army.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CertificateLocationResponse {
    private ApiCertificateLocationResponse location;
    private RouteResponse routeResponse;
}
