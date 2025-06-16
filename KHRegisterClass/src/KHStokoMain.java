import controller.DBUtil;
import controller.KhStokoCusManager;
import controller.KhStokoManager;
import java.sql.Connection;
import java.util.Scanner;
import view.KhMenu;
import view.KhMenuChoice;
import view.KhStokoChoice;
import view.KhStokoCusChoice;


public class KHStokoMain {
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		Connection con = DBUtil.getConnection();
		int choice = 0;
		boolean exitFlag = false;

		if (con == null) {
			System.out.println("디비연결오류");
			return;
		}
		// 화면설계진행
		while (!exitFlag) {
			try {
				KhMenu.mainMenu();
				choice = Integer.parseInt(scan.nextLine());

				switch (choice) {
				case KhMenuChoice.관리자메뉴:
					KhStokoMenu();
					break;
				case KhMenuChoice.사용자메뉴:
					KhStokoCusMenu();
					break;
				case KhMenuChoice.프로그램종료:
					exitFlag = true;
					break;
				}
			} catch (Exception e) {
				System.out.println("\n입력에 오류가 있습니다.\n프로그램을 다시 시작하세요.");
				exitFlag = true;
			}
		} // end of while
		System.out.println("KH STOKO 프로그램 종료");
	}// end of main

	// 관리자 메뉴
	public static void KhStokoMenu() {
		int choice = 0;
		boolean exitFlag = false;
		while (!exitFlag) {
			try {
				KhMenu.KhStokoMenu();
				KhStokoManager km = new KhStokoManager();
				choice = Integer.parseInt(scan.nextLine());
				switch (choice) {
				case KhStokoChoice.제품목록:
					km.list();
					break;
				case KhStokoChoice.제품등록:
					km.register();
					break;
				case KhStokoChoice.제품수정:
					km.update();
					break;
				case KhStokoChoice.제품삭제:
					km.delete();
					break;
				case KhStokoChoice.메인화면:
					System.out.println("제품 관리 종료");
					exitFlag = true;
					break;
				} // end of while
			} catch (Exception e) {
				System.out.println("제품 정보 예외발생 조치바람");
			}
		} // while
	}// end of subjectMenu

	// 사용자 메뉴
	public static void KhStokoCusMenu() {
		int choice = 0;
		boolean exitFlag = false;
		while (!exitFlag) {
			try {
				KhMenu.KhStokoCusMenu();
				KhStokoCusManager kcm = new KhStokoCusManager();
				choice = Integer.parseInt(scan.nextLine());
				switch (choice) {
				case KhStokoCusChoice.영수증목록:
					kcm.list();
					break;
				case KhStokoCusChoice.영수증등록:
					kcm.register();
					break;
				case KhStokoCusChoice.영수증수정:
					kcm.update();
					break;
				case KhStokoCusChoice.영수증삭제:
					kcm.delete();
					break;
				case KhStokoCusChoice.영수증조회:
					kcm.searchByRecNo();
					break;
				case KhStokoCusChoice.메인화면:
					System.out.println("영수증 관리 종료");
					exitFlag = true;
					break;
				} // end of while
			} catch (Exception e) {
				System.out.println("영수증 관리 예외발생 조치바람");
				e.printStackTrace();
			}
		} // while
	}// end of subjectMenu

}
