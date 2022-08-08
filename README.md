

# 블로그 검색 서비스 API
## 목차
- `개요`
- `프로젝트 구성`
- `프로그램 실행방법`
- `테스트 방법`
- `ERD`
- `API 명세`
- `접근/해결방식`
- `결과 화면`

---

## 개요
블로그 검색 서비스
1. 계정 로그인
2. 블로그 검색 (카카오 API 연동)
3. 인기검색어
4. 북마크 즐겨찾기
---

## 프로젝트 구성
분야| stack |
--|--|
 |언어 | JAVA 8 |
 |서버 | JPA, Spring Boot, Spring Validation, Spring Security(JWT), Feign Client |
 |DB | H2 |
 | IDE | 이클립스
  |빌드 툴 | Gradle |
 |API 테스트 툴 | Swagger UI |
 | 테스트 | Junit
 | 기타 | Lombok, ModelMapper


---

## 프로그램 실행방법
### 터미널 환경
- Git, Java, Lombok 은 설치되어 있다고 가정.
```
마스터 브랜치 기준
$ https://github.com/moonsungwan/search-blog-service.git
```
### JAR 파일 싱생
- 다운로그 링크 : https://github.com/moonsungwan/search-blog-service/blob/master/src/main/resources/search-blog-backend-0.0.1-SNAPSHOT
```
$ java -jar search-blog-backend-0.0.1-SNAPSHOT
```


### 이클립스 기준
- Gradle Spring boot 실행

- H2 접속정보
  - http://localhost:8080/h2-console
  - Saved Sttings : Generic H2 (Embedded)
  - Driver Class : org.h2.Driver
  - jdbc URL : jdbc:h2:mem:jpa-test
  - User Name : sa (비밀번호 없음)

---

## 테스트 방법
- 기본계정 : accountA (ID) / 1234 (비밀번호)
- Swagger 접속정보
  - 토큰 기반 API호출 (로그인 후 토큰 값 Authorize에 입력)
  - http://localhost:8080/swagger-ui/index.html

---
## ERD
![image](https://user-images.githubusercontent.com/18672444/183285563-a6cb5b62-e5a3-4286-8ac8-bede5d04319d.png)

---
## API 명세

### 응답 공통

`HTTP 응답코드`

| 응답코드 | 설명                  |
| -------- | --------------------- |
| `message` | **결과 메시지**         |
| `data` | **결과 데이터**         |
| `success` | **성공여부**         |

`응답 예`

```json
{
  "success": true,
  "data": {
    "loginId": "accountA",
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhY2NvdW50QSIsImlhdCI6MTY1OTg2NjY3NywiZXhwIjoxNjU5ODcwMjc3fQ.K7l6p3RFlROcPB0gmFbth2XF_cpkWdeTmhHiYO0oswYR9nCBtNUJFdlha3l39_NFICxmSLLCsguzG963GXnqUQ"
  },
  "message": "성공하였습니다."
}
```

---

`에러코드 및 메시지`

| 에러코드 | 메시지                  |
| -------- | --------------------- |
| `SUCCEED` | 성공하였습니다.       |
| `REGISTERED` | 등록되었습니다.       |
| `UPDATED` | 수정되었습니다.       |
| `DELETED` | 삭제되었습니다.       |
| `LOGOUT` | 로그아웃 되었습니다.       |
| `NO_CONTENT` | 데이터가 없습니다.       |
| `FAILED` | 실패하였습니다.       |
| `INVALID_AUTH_TOKEN` | 토큰 정보가 유효하지 않습니다.       |
| `INVALID_PASSWORD` | 잘못된 비밀번호입니다.       |
| `BUSINESS_EXCEPTION` | 업무로직에서 에러가 발생했습니다.       |
| `MEMBER_EXISTING` | 이미 등록된 유저 정보입니다.       |
---
`에러 응답 예`

```json
{
    "timestamp": "2022-08-07T19:12:33.3440113",
    "status": 409,
    "error": "CONFLICT",
    "code": "MEMBER_EXISTING",
    "message": "이미 등록된 유저 정보입니다."
}
```

### 1-1. 계정 - 로그인 API
---

| 항목 | 값             |
| ---- | -------------- |
| URL  | `POST` /api/v1/account/login |

요청

```json
{
    "loginId": "accountA",
    "password": "1234"
}
```

#### 응답

```json
{
    "success": true,
    "data": {
        "loginId": "accountA",
        "nickName": "문성완",
        "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhY2NvdW50QSIsImlhdCI6MTY1OTg2NzE5MSwiZXhwIjoxNjU5ODcwNzkxfQ.GoBl3DmqNycmwFhCdbH54SGW0pqBjIgEREz1ngVJmfJkAm13vsknv0qifZfMZD-Az5zsh7JFufxAkI6BJ9sefw"
    },
    "message": "성공하였습니다."
}
```

### 1-2. 계정 - 회원가입 API
---

| 항목 | 값             |
| ---- | -------------- |
| URL  | `POST` /api/v1/account/sign-up |
요청

```json
{
    "loginId": "accountD",
    "nickName": "계정D",
    "password": "1234"
}
```

#### 응답

```json
{
    "success": true,
    "data": {
        "loginId": "accountD",
        "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhY2NvdW50RCIsImlhdCI6MTY1OTg2NzUxOCwiZXhwIjoxNjU5ODcxMTE4fQ.Yf3JvXgjGjl6lniqiRwOVoVMIkTRNewLtrQn9uvT0ueHqberX1s3bo_tzr-xAyBoLyHpNa8qU5oQjK6QFqa1kA"
    },
    "message": "성공하였습니다."
}
```

### 2. 블로그 - 블로그 검색 API
---

| 항목 | 값             |
| ---- | -------------- |
| URL  | `GET` /api/v1/blog |
요청

```json
?page=1&query=카카오블로그&size=10&sort=accuracy

sort 종류 - accuracy, recency
```

#### 응답

```json
{
    "success": true,
    "data": {
        "meta": {
            "total_count": "2498757",
            "pageable_count": "800",
            "is_end": "false"
        },
        "documents": [
            {
                "title": "다음 <b>카카오</b> 티스토리 <b>블로그</b> 만들기, 글쓰기, 꾸미기, 확실히 알아보기",
                "contents": "그 성격이 많이 다를 것 같았는데요 막상 티스토리로 이전을 하고 보니 글쓰기 기능이라든자 꾸미기 기능이 기종 다음 <b>블로그</b>와 똑같았습니다 그러면 지금부터 다음<b>카카오</b> 티스토리 <b>블로그</b>를 초간단하게 만드는 방법과 글쓰기, 꾸미기 기능을 확실하게 알아보도록 하겠습니다 티스토리 <b>블로그</b>로 이전을 하려면 우선...",
                "url": "http://11757.tistory.com/16887190",
                "blogname": "비단장수 왕서방 이야기, 종합침구 코코하우스",
                "thumbnail": "https://search3.kakaocdn.net/argon/130x130_85_c/4TxWSoSjPdX",
                "datetime": "2022-08-07T16:10:02.000+09:00"
            },
            .
            .
            .
        ]
    },
    "message": "성공하였습니다."
}
```

### 3-1. 인기 검색어 - 인기검색어 목록 API
---

| 항목 | 값             |
| ---- | -------------- |
| URL  | `GET` /api/v1/search-history |


#### 응답

```json
{
    "success": true,
    "data": [
        {
            "searchWord": "검색어",
            "searchCount": 3
        },
        {
            "searchWord": "조회수테스트",
            "searchCount": 2
        }
    ],
    "message": "성공하였습니다."
}
```

### 3-2. 인기검색어 - 인기검색어 등록 API
---

| 항목 | 값             |
| ---- | -------------- |
| URL  | `POST` /api/v1/account/search-history
 |
요청

```json
{
    "searchWord": "블로그"
}
```

#### 응답

```json
{
    "success": true,
    "data": true,
    "message": "등록되었습니다."
}
```
### 4-1. 북마크 - 북마크 즐겨찾기 목록 API
---

| 항목 | 값             |
| ---- | -------------- |
| URL  | `GET` /api/v1/bookmark-blog |
요청

```json
{
    "bookmarkTitle": "북마크 이름",
    "bookmarkUrl": "북마크 URL"
}
```
### 4-2. 북마크 - 북마크 등록 API
---

| 항목 | 값             |
| ---- | -------------- |
| URL  | `POST` /api/v1/bookmark-blog |
요청

```json
{
    "bookmarkTitle": "북마크 이름",
    "bookmarkUrl": "북마크 URL"
}
```

#### 응답

```json
{
    "success": true,
    "data": true,
    "message": "등록되었습니다."
}
```

### 4-3. 북마크 - 북마크 삭제 API
---

| 항목 | 값             |
| ---- | -------------- |
| URL  | `DELETE` /api/v1/bookmark-blog/5 |

#### 응답

```json
{
    "success": true,
    "data": true,
    "message": "삭제되었습니다."
}
```
---
## 접근/해결방식
* ### 계정 인증
    * JWT 기반 Spring Security 사용
    * 회원 가입 시 응답받은 토큰 값으로 메인 화면 (블로그 목록) 이동
    * 최초 로그인 시 응답받은 토큰 값으로 API 호출
* ### 블로그 검색
    * Feign Cient 사용
    * 카카오 오픈 API용 Feign Client Config 생성
    * #### ***분리 이유***
      * NAVER 오픈 API 사용시 도메인 및 Client Key 분리를 위함
    * 블로그 검색 목록에서 25개 단위로 페이징 출력
    * 블로그 제목 클릭시 북마크 URL 새창 열기
    * 기본 검색 조건 
      * '정확도순'
      * 최초 진입시 검색결과가 없으면 목록 출력 하지 않음
    * 검색시 인기검색어 등록 API 호출
    * 페이지 이동 및 정렬순서 변경시 인기검색어 목록 갱신
* ### 인기 검색어
    * 프론트에서 검색어 입력시 DB에 검색어 저장
    * 검색어 카운트 순으로 조회
    * 비관적 LOCK 사용하여 동시성 처리
    * #### ***추가되면 좋을 기능***
      * 추후 Redis Sorted set 이용하여 개선
* ### 북마크 즐겨찾기
    * 로그인 한 정보에 맞는 북마크 목록 및 삭제
    * 같은 블로그 중복 저장되도록 처리
    * #### ***이유***
      * 북마크 URL로 PK를 설정하기에는 사이즈가 크다고 생각
    * 북마크 제목 클릭시 북마크 URL 새창 열기 
---
## 결과화면
* ### 로그인
![image](https://user-images.githubusercontent.com/18672444/183444777-5e6f1b07-797f-46a0-b46d-67d242c5d53d.png)

* ### 회원가입
![image](https://user-images.githubusercontent.com/18672444/183445004-62d3fddf-46df-4ac6-8491-e8f134d0060e.png)

* ### 블로그 검색
![image](https://user-images.githubusercontent.com/18672444/183445249-ad1af8b6-e27d-4641-b43b-d8396acdd50a.png)

* ### 북마크
![image](https://user-images.githubusercontent.com/18672444/183445438-2fd32b1e-e166-4191-80df-9c1302a240d8.png)
