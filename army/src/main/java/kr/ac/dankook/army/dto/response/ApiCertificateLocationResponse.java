package kr.ac.dankook.army.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiCertificateLocationResponse {
    private String placeName;
    private String latitude;
    private String longitude;
    private String address;
    private String phoneNumber;
}
