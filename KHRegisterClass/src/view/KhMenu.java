package view;

public class KhMenu {

	// 메인 메뉴
	public static void mainMenu() {
		System.out.println();
		System.out.println("KH Stoko 프로그램");
		System.out.println("해당 번호를 입력하세요.");
		System.out.println("1. 관리자 메뉴");
		System.out.println("2. 사용자 메뉴");
		System.out.println("3. 프로그램 종료");
		System.out.print("번호 선택 : ");
	}

	// 제품 메뉴
	public static void KhStokoMenu() {
		System.out.println();
		System.out.println("관리자 메뉴 번호를 입력하세요.");
		System.out.println("1. 재고 목록 전체 출력");
		System.out.println("2. 재고 추가");
		System.out.println("3. 재고 정보 수정");
		System.out.println("4. 재고 삭제");
		System.out.println("5. 메인 메뉴");
		System.out.print("번호 선택 : ");
	}

	// 영수증 메뉴
	public static void KhStokoCusMenu() {
		System.out.println();
		System.out.println("사용자 영수증 정보 메뉴 번호를 입력하세요.");
		System.out.println("1. 영수증 전체 출력");
		System.out.println("2. 사용자 물품 구매");
		System.out.println("3. 영수증 정보 수정");
		System.out.println("4. 영수증 정보 삭제");
		System.out.println("5. 영수증 번호 조회");
		System.out.println("6. 메인 메뉴");
		System.out.print("번호 선택 : ");
	}

}
