<h1 align="center">$\bf{\large{\color{#e45735}  Nahc \ Army \ Service }}$</h1>
<h3 align="center">
 국군장병을 위한 자격증 서비스
</h3>
<p align="center">
 2024년 국방 공공데이터 활용 본선 진출 작품
</p>

<br>

<blockquote>
  <p dir="auto">
     <strong> 2024년 국방 공공데이터 활용 본선 진출 작품 </strong> <br>
     <strong> 개발 기간 : 2024.05.21 ~ 2024.06.03 </strong>
  </p>
</blockquote>

<br>

### 프로젝트 소개
해당 프로젝트는 자격증 정보 제공, 근처 CBT 시험장 추적, 맞춤형 플래너 작성, <br>
그리고 실시간 AI 채팅 지원을 통해 장병들의 자격증 취득을 돕는다. <br>
이 서비스는 군별, 군종별, 전공별로 맞춤 추천을 제공하며, <br>
훈련 일정을 고려한 플래너 작성 기능을 갖추고 있다. <br>

<br> 

### 개발 환경
<p>$\bf{\large{\color{#e45735} FrontEnd \ : HTML / CSS / JavaScript}}$</p>
<p>$\bf{\large{\color{#e45735} BackEnd \ : Java / Maria DB }}$</p>
<p>$\bf{\large{\color{#e45735} Cloud \ : CloudType}}$</p>
<p>$\bf{\large{\color{#e45735} Framework \ : Spring boot}}$</p>
<p>$\bf{\large{\color{#e45735} Framework \ Main \  Dependency}}$</p>

- [x] Jpa
- [x] Thymeleaf
- [x] Maven
<br>

### 데이터 및 API
- <p>$\bf{\large{\color{#e45735} Kakao \ API}}$</p>
  
  - Kakao API를 통해 CBT 시험장 검색

- <p>$\bf{\large{\color{#e45735} Google \ API}}$</p>

  - Google AI의 Gemini API를 통해 AI 채팅 모델 제공
 
- <p>$\bf{\large{\color{#e45735} TMap \ API}}$</p>

  -  TMap API를 통해 특정 장소까지의 교통정보 제공 및 위도 경도 변환 

- <p>$\bf{\large{\color{#e45735} 공공 \ 데이터포털 }}$</p>

  - 국방부 육군 국가기술 자격증 취득 현황 데이터 
  - 국가보훈부 제대군인 군별 군종별 희망 직종별 관련 자격증 정보 데이터
  - 한국산업인력공단 국가자격 CBT 시험장소 및 수험자 정보 데이터

<br>

### 서비스 기능
- <p>$\bf{\large{\color{#e45735} 자격증 \ 리스트 \ 및 \ 취득 \ 현황 \ 제공 \ 기능}}$</p>

   #### 기능
     * 국방부와 육군의 국가기술 자격증 취득 현황 데이터를 기반으로 자격증 리스트 제공
     * 자격증별 합격률, 응시율, 응시수 등의 통계를 제공
     * 큐넷을 통한 신청방법과 신청 사이트 URL을 제공
   #### 핵심 기술
     * 자격증 정보와 취득 현황 데이터 (DBMS)
     * 특정 단어를 인식하는 필터 기능
     * 정렬 알고리즘을 이용하여 합격률, 응시수 순으로 데이터를 정렬
   #### 사용자 UI
     * 자격증 리스트 및 검색 UI <br>
       <p align="left">
         <img src="https://github.com/user-attachments/assets/6bb44971-faa7-4fac-b3cb-8c91eaef7cca"/>
       </p>
       
     * 자격증 상세 정보 UI
       <p align="left">
         <img src="https://github.com/user-attachments/assets/70559cd4-9b6c-481e-b575-19c7f89a6515"/>
       </p>
  
<br> 

- <p>$\bf{\large{\color{#e45735} 맞춤형 \ 자격증 \ 추천 및 \ 근처 \ 시험장 \ 정보 \ 제공}}$</p>


   #### 기능
     * 군별, 군종을 선택 후 전공을 선택하여 이에 맞는 자격증을 추천
     * 장병의 군 경력, 전공과 연관된 자격증 추천
     * 사용자 친화적인 인터페이스로 손쉬운 자격증 검색 및 추천
     * 부대 위치를 기반으로 근처 CBT 시험장 정보 제공
     * 시험장 위치와 교통정보 제공
   #### 핵심 기술
     * 필터 및 가중치 알고리즘
     * 군별, 군종별, 전공별 데이터를 (DBMS)
     * 쿼리 검색
       회원 가입한 사용자의 주소를 바탕으로 지역 코드를 추출하여 <br>
       데이터베이스에서 해당 지역 코드에 위치한 시험장 위치 데이터를 추출
     * Kakao & TMap API
   #### 사용자 인터페이스
     * 맞춤 자격증 추천 결과 UI
       <p align="left">
         <img src="https://github.com/user-attachments/assets/d23bd9b3-4261-468f-bf2b-5c3c58b07264"/>
       </p>
       
     * 부대 근처 CBT 시험장 정보 UI
       <p align="left">
         <img src="https://github.com/user-attachments/assets/8b8caaf7-e1b1-4fcf-a9dd-080d85be5d84"/>
       </p>

     * 부대 지역 최근 시험장 장소 UI
       <p align="left">
         <img src="https://github.com/user-attachments/assets/203f8ed4-6b74-4564-9648-9d9fc451851d"/>
       </p>
<br>

- <p>$\bf{\large{\color{#e45735} 맞춤형 \ 플래너 \ 서비스 \ 및 \ AI \ 채팅 \ 기능 }}$</p>

   #### 기능
     * 자격증 준비 기간을 효율적으로 관리할 수 있는 맞춤형 플래너 제공
     * 훈련 일정과 학습 일정을 통합 관리
     * 자격증 정보, 자격증 시험 문제 등 실시간 정보 제공 및 장병들의 질문에 즉시 답변
   #### 핵심 기술
     * 자연어 처리(NLP)
     * Google Gemini API
     * Ck Editor
     * 사용자 훈련 일정과 자격증 학습 일정 데이터 (DBMS)
   #### 사용자 인터페이스
     * 플래너 기본, 작성 화면 UI
       <p align="left">
         <img height="200px" src="https://github.com/user-attachments/assets/cef98322-c168-4e16-aded-8ca52ea6989b"/>
         <img  height="200px" src="https://github.com/user-attachments/assets/ad85ec3f-8ebc-440c-95ca-08c0ed87842d"/>
       </p>
       
     * AI 채팅 화면 UI
       <p align="left">
         <img src="https://github.com/user-attachments/assets/7cd27c5d-c780-49d1-a262-315e4c8d245a"/>
       </p>

<br> 

### 배포 URL
- https://port-0-nahc-army-ss7z32llwmafmaz.sel5.cloudtype.app/public/main
- 현재 클라우드 타입 운영 종료

<br>

### 아키텍쳐
#### 디렉터리 구조
```
📦army
 ┣ 📂.idea
 ┃ ┣ 📂inspectionProfiles
 ┃ ┃ ┗ 📜Project_Default.xml
 ┣ 📂.mvn
 ┃ ┗ 📂wrapper
 ┃ ┃ ┗ 📜maven-wrapper.properties
 ┣ 📂src
 ┃ ┣ 📂main
 ┃ ┃ ┣ 📂java
 ┃ ┃ ┃ ┗ 📂kr
 ┃ ┃ ┃ ┃ ┗ 📂ac
 ┃ ┃ ┃ ┃ ┃ ┗ 📂dankook
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂army
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GeminiRestTemplateConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜WebMvcConfiguration.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜privateCertificateController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜privatePlannerController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜privateUserController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜publicCertificateController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PublicMainController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂common
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Authority.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Coordinates.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArmyCertificate.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Certificate.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CertificateLocation.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Member.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Planner.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PlannerComponent.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ChatRequest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜LocationRequest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂response
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ApiCertificateLocationResponse.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CertificateLocationResponse.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CertificateRecommend.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ChatResponse.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RouteResponse.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ApiJsonParsingException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ErrorCode.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ErrorResponse.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GlobalExceptionHandler.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NoEntityException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserNotFoundException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserNotInactiveException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂interceptor
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜LoginInterceptor.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArmyCertificateEntityManager.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArmyCertificateRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CertificateLocationRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CertificateRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PlannerComponentRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PlannerRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂restController
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthRestController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CertificateRestController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GeminiRestController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberRestController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PlannerRestController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ApiService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CertificateLocationService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CertificateRecommendService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CertificateService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GeminiService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PlannerComponentService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PlannerService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ArmyApplication.java
 ┃ ┃ ┗ 📂resources
 ┃ ┃ ┃ ┣ 📂META-INF
 ┃ ┃ ┃ ┃ ┗ 📜MANIFEST.MF
 ┃ ┃ ┃ ┣ 📂static
 ┃ ┃ ┃ ┃ ┣ 📂assets
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ai.jpg
                ...
 ┃ ┃ ┃ ┃ ┃ ┗ 📜planner.jpg
 ┃ ┃ ┃ ┃ ┣ 📂css
 ┃ ┃ ┃ ┃ ┃ ┣ 📂certificate
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜certificate.css
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜certificateDetail.css
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜certificateList.css
 ┃ ┃ ┃ ┃ ┃ ┣ 📂member
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜findId.css
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜findPassword.css
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜login.css
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜signup.css
 ┃ ┃ ┃ ┃ ┃ ┣ 📂planner
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜planner.css
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜plannerList.css
 ┃ ┃ ┃ ┃ ┃ ┣ 📜config.css
 ┃ ┃ ┃ ┃ ┃ ┣ 📜main.css
 ┃ ┃ ┃ ┃ ┃ ┗ 📜user.css
 ┃ ┃ ┃ ┃ ┗ 📂js
 ┃ ┃ ┃ ┃ ┃ ┣ 📂certificate
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜certificate.js
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜certificateDetail.js
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜certificateList.js
 ┃ ┃ ┃ ┃ ┃ ┣ 📂member
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜findId.js
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜findPassword.js
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜login.js
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜signup.js
 ┃ ┃ ┃ ┃ ┃ ┣ 📂planner
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜planner.js
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜plannerList.js
 ┃ ┃ ┃ ┃ ┃ ┣ 📜config.js
 ┃ ┃ ┃ ┃ ┃ ┣ 📜main.js
 ┃ ┃ ┃ ┃ ┃ ┗ 📜user.js
 ┃ ┃ ┃ ┣ 📂templates
 ┃ ┃ ┃ ┃ ┣ 📂common
 ┃ ┃ ┃ ┃ ┃ ┗ 📜config.html
 ┃ ┃ ┃ ┃ ┣ 📂fragments
 ┃ ┃ ┃ ┃ ┃ ┣ 📜footer.html
 ┃ ┃ ┃ ┃ ┃ ┗ 📜header.html
 ┃ ┃ ┃ ┃ ┗ 📂page
 ┃ ┃ ┃ ┃ ┃ ┣ 📂certificate
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜certificate.html
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜certificateDetail.html
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜certificateList.html
 ┃ ┃ ┃ ┃ ┃ ┣ 📂member
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜findId.html
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜findPassword.html
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜login.html
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜signup.html
 ┃ ┃ ┃ ┃ ┃ ┣ 📂planner
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜planner.html
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜plannerList.html
 ┃ ┃ ┃ ┃ ┃ ┣ 📜main.html
 ┃ ┃ ┃ ┃ ┃ ┗ 📜user.html
 ┃ ┃ ┃ ┗ 📜application.properties
 ┗ 📜pom.xml
```
