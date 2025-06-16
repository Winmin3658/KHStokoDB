# KhStoko

📦 **KhStoko**는 Java 기반의 간단한 콘솔 애플리케이션으로, 고객 및 재고(상품) 정보를 효율적으로 등록하고 관리할 수 있는 시스템입니다.  
MVC 패턴의 기본 구조를 바탕으로, `VO`, `DAO`, `Manager` 계층을 통해 기능을 분리하여 구현했습니다.

---

## 📅 개발 기간

- **2025년 6월 9일 ~ 2025년 6월 14일**


---

## 🧩 주요 기능

- 고객 정보 등록 및 조회
- 재고(상품) 정보 등록 및 조회
- 콘솔 UI를 통한 간단한 메뉴 기반 조작
- 배열 기반의 간단한 데이터 저장 구조 사용

---

## 📁 프로젝트 구조

src/
├── KHStokoMain.java // 애플리케이션 실행 및 메뉴 제공
│
├── controller/
│ ├── DBUtil.java // DB 연결 
│ ├── KhStokoDAO.java // 관리자 DAO (등록/조회)
│ └── KhStokoCusDAO.java // 고객 DAO
│ │
│ ├── KhStokoManager.java // 관리자 등록/조회 로직
│ └── KhStokoCusManager.java // 고객 등록/조회 로직
│
├── model/
│ ├── KhStokoVO.java // 관리자 정보 VO
│ └── KhStokoCusVO.java // 고객 정보 VO
│
├── view /
│ ├── KhMenu.java // 전체적인 메뉴 구현
│ ├── KhMenuChoice.java // 전체적인 메뉴 인터페이스 구현
│ ├── KhStokoChoice.java // 관리자 메뉴 인터페이스 구현
│ └── KhStokoCusChoice.java // 사용자 메뉴 인터페이스 구현

---

## 🚀 실행 방법

1. Java 개발 환경을 준비합니다. (JDK 8 이상 권장)
2. 프로젝트 전체를 다운로드 받습니다. (src/config/db.properties 작성 요망)
3. 터미널에서 `bin` 디렉토리로 이동한 후 아래 명령어를 실행합니다:

```bash
javac KHStokoMain.java
java KHStokoMain
