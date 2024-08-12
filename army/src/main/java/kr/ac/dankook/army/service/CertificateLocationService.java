package kr.ac.dankook.army.service;

import jakarta.servlet.http.HttpServletRequest;
import kr.ac.dankook.army.dto.common.Coordinates;
import kr.ac.dankook.army.dto.entity.CertificateLocation;
import kr.ac.dankook.army.dto.entity.Member;
import kr.ac.dankook.army.dto.request.LocationRequest;
import kr.ac.dankook.army.dto.response.ApiCertificateLocationResponse;
import kr.ac.dankook.army.dto.response.CertificateLocationResponse;
import kr.ac.dankook.army.dto.response.RouteResponse;
import kr.ac.dankook.army.repository.CertificateLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateLocationService {

    private final CertificateLocationRepository certificateLocationRepository;
    private final ApiService apiService;
    private final MemberService memberService;

    public String getUserRegionCode(HttpServletRequest request) {
        Member loginMember = memberService.findMemberBySession(request);
        String memberAddress = loginMember.getAddress();
        return memberAddress.substring(0,2);
    }

    public List<CertificateLocation> getDataBaseCertificationLocationList(HttpServletRequest request){
        String regionCode = getUserRegionCode(request);
        return certificateLocationRepository.findByAddressContaining(regionCode);
    }

    public List<CertificateLocationResponse> getAroundCertificateLocationProcess(HttpServletRequest request) {

        Member loginMember = memberService.findMemberBySession(request);
        String memberLatitude = loginMember.getAddressLatitude();
        String memberLongitude = loginMember.getAddressLongitude();

        List<CertificateLocationResponse> allResponses = new ArrayList<>();

        List<ApiCertificateLocationResponse> apiCertificateLocationResponses = apiService.searchAroundParkingPlace(
                new Coordinates(memberLatitude,memberLongitude)
        );
        for(ApiCertificateLocationResponse apiCertificateLocationResponse : apiCertificateLocationResponses){
            LocationRequest locationRequest = new LocationRequest(
                    memberLatitude,memberLongitude,
                    apiCertificateLocationResponse.getLatitude(),apiCertificateLocationResponse.getLongitude()
            );
            RouteResponse routeResponse =
                    apiService.getAllRouteInformation(locationRequest);
            allResponses.add(new CertificateLocationResponse(
                    apiCertificateLocationResponse,routeResponse
            ));
        }
        return allResponses;
    }
}
