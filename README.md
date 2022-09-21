# PROJECT : APARTMENT-HOW-MUCH

## 아파트 매매 실거래가 조회 프로그램
<br><br>

### 1. 이 프로그램에 대하여
> 아파트의 사양과 매매 실거래가 정보를 쉽고 빠르게 조회할 수 있는 프로그램입니다.
> 
> 부동산을 매매할 경우 거래 당사자는 30일 이내에 시장, 군수, 구청장에게 반드시 신고를 해야 합니다(「부동산 거래신고 등에 관한 법률」 제3조제1항).
> 
> 정부는 이 정보를 취합하여 공공데이터포털(data.go.kr)을 통해 파일 또는 API 형식으로 제공합니다.
> 
>본 프로그램은 공공데이터포털에서 제공하는 데이터를 바탕으로 제작합니다.
  
<br>

### 2. 다운로드 
> * 프로그램 다운로드 링크 (windows 전용)
>   * (준비중)
> * 개발문서
>   * [download - dev_document(pdf)](https://drive.google.com/file/d/1uSMxHxRhqIvmre2-DTt-DxtbuGUoBxku/view?usp=sharing)
>   * 프로젝트를 마치며 국비교육과정 강의실에서 진행한 최종 발표 프레젠테이션 자료입니다.
> 
<br>



### 3. 기술 스택
> * oracle open java jdk-17.0.2.jdk (arm64)
> * javafx-sdk-17.0.2 // GUI Framework
> * MySQL 8.0.28 // database

<br>

### 4. 프로젝트에 대하여
> * 국비교육과정에서 진행한 개인 프로젝트
>   * 교육 커리큘럼에서 제시한 목표
>     * Java 와 Database를 연동
>     * Graphic User Interface
> * 스스로 공부하고 적용한 추가 기술
>     * JavaFX
>     * Docker
>     * MVC 디자인 패턴
>       


### 5. 프로젝트를 진행하며 배운점, 문제와 해결
>  * 객제치향 프로그래밍의 본질에 대한 이해와 적용
>    * 캡슐화, 상속, 추상화, 다형성의 특징을 주도적으로 사용하여 프로그램 개발
>  * 기술을 스스로 습득하는 방법 - JavaFX
>    * JavaFX는 한국어 자료가 거의 없으나 영어로 쓰여진 공식 Documentation, Stack Overflow, 외국의 블로그/유투브 등을 참고하여 이해-습득-적용
>    * 교육받은 GUI 프레임워크는 Java AWT 였으나 AWT는 apple silicon MacOS 지원하지 않고 지금은 사용하지 않는 너무 오래된 기술
>    * Java GUI 프레임워크중 최근까지 지속적으로 업데이트/배포 되고 있는 JavaFX를 독학하여 개발
>  * 실용성과 완성도를 위해 찾은 해법 - Docker
>    * 교육과정에서는 개발용 컴퓨터에 DB를 설치하여 연동하는 방법만 제공 -> 따라서 개발 환경 이외의 컴퓨터에서 프로젝트를 실행하지 못하는 문제 발생
>    * 국비교육과정에서 교육한 Oracle DB는 MacOS를 지원하지 않아 대응책으로 Docker 의 전반적인 사용법을 독학(강사님께서 Docker 사용 경험 x)
>    * Oracle 대신 Docker 를 공식 지원하는 MySQL 을 습득하여 사용
>    * 개인 NAS 위에 MySQL Container 을 생성하고 특정 주소와 포트를 개방하여 프로젝트 프로그램이 DB에 접근하는 방법으로 해결  
>  * 보안에 대한 인식 - DB 접속 정보 숨기기
>    * 코드 전체를 github 공개 저장소에 업로드시 DB 접속 정보가 노출이 되고 이는 악의적 접근 가능성이 발생함을 인식
>    * 트위터의 다른 현업 개발자분께서 .gitignore 목록에 포함된 env 파일로 서버 정보를 관리하는 아이디어 조언을 받아 해결
>  * Query 최적화
>    * 16만개가 넘는 레코드를 검색하는 과정에서 발생하는 지연 시간 최적화 노력 

### 6. 프로젝트 진행 당시 개발 과정을 기록하고 정리하던 노션 페이지.

[![Notion Badge](https://img.shields.io/badge/APARTMENT_HOW_MUCH-808080?style=for-the-badge&logo=NOTION&logoColor=white&link=https://twitter.com/dpcalFola)](https://www.notion.so/PROJECT-APARTMENT-HOW-MUCH-c5e2a8a7cdff4c07ab3452ad95005020)
