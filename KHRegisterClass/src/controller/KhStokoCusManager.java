package controller;

import java.util.ArrayList;
import java.util.Scanner;
import model.KhStokoCusVO;
import model.KhStokoVO;


public class KhStokoCusManager {
	// 영수증 목록
	public void list() throws Exception {
		KhStokoCusDAO kd = new KhStokoCusDAO();
		System.out.println("영수증 전체 리스트");
		ArrayList<KhStokoCusVO> khStokoCusList = kd.selectAll();
		if(khStokoCusList.isEmpty()) {
			System.out.println("영수증 전체리스트 내용이 없습니다.");
			System.out.println(">> [DEBUG] 현재 리스트 사이즈: " + khStokoCusList.size());

			return;
		}else if(khStokoCusList == null) {
			System.out.println("영수증 전체리스트 에러발생");
			return;
		}
		for( KhStokoCusVO data : khStokoCusList) {
			System.out.println(data.toString());
		}
	}
	public void list2() throws Exception {
		KhStokoDAO sd = new KhStokoDAO();
		System.out.println("품목 전체 리스트");
		ArrayList<KhStokoVO> khStokoList = sd.selectAll();
		if(khStokoList.size() == 0) {
			System.out.println("품목 전체리스트 내용이 없습니다.");
			return;
		}else if(khStokoList == null) {
			System.out.println("품목 전체리스트 에러발생");
			return;
		}
		for( KhStokoVO data : khStokoList) {
			System.out.println(data.toString());
		}
	}

	// 영수증 등록
	public void register() throws Exception {
    Scanner scan = new Scanner(System.in);
    KhStokoDAO stockDAO = new KhStokoDAO();      // 재고용 DAO
    KhStokoCusDAO cusDAO = new KhStokoCusDAO();  // 영수증용 DAO
    KhStokoCusVO cusVO = new KhStokoCusVO();
	ArrayList<KhStokoVO> khStokoList = new ArrayList<KhStokoVO>();

    String categoryCus ="";
    String subCategoryCus="";
    int priceCus;
    int stockCus;
    String expDate;

	khStokoList = stockDAO.selectAll();
    list2(); // 재고 목록 출력
    System.out.println("구매할 품목 정보 입력");
    System.out.print("소분류 : ");
    subCategoryCus = scan.nextLine();

    // 재고 테이블에서 해당 품목 확인
    KhStokoVO stockVO = stockDAO.selectByCategory(subCategoryCus);
    if (stockVO == null) {
        System.out.println("해당 품목이 존재하지 않습니다.");
        return;
    }

    System.out.print("구매 수량 : ");
    stockCus = Integer.parseInt(scan.nextLine());

    if (stockVO.getStock() < stockCus) {
        System.out.println("재고가 부족합니다. 현재 재고: " + stockVO.getStock());
        return;
    }
	categoryCus = stockVO.getCategory();
	priceCus = stockVO.getPrice();
    expDate = stockVO.getExpDate(); // 재고에서 가져온 유통기한 사용
	expDate = expDate.substring(0,10);
	
    // 재고 차감
    stockDAO.updateStock(categoryCus, subCategoryCus,stockCus);

    // 영수증 등록
    cusVO.setCategoryCus(categoryCus);
    cusVO.setSubCategoryCus(subCategoryCus);
    cusVO.setPriceCus(priceCus);
    cusVO.setStockCus(stockCus);
    cusVO.setExpDate(expDate);
	System.out.println(cusVO);
	list();
    int count = cusDAO.insert(cusVO);
	System.out.println(count);

    System.out.println("구매 완료! 영수증 등록됨.");
    System.out.println();
    System.out.println("영수증 전체 리스트");
    list();
}

	// 영수증 수정 관리
	public void update() throws Exception {
		Scanner scan = new Scanner(System.in);

		KhStokoCusDAO kd = new KhStokoCusDAO();
		KhStokoCusVO kvo = new KhStokoCusVO();

		int recNo; // 입력한 일련번호
		String categoryCus; // 영수증 대분류
		String subCategoryCus; // 영수증 소분류
		int priceCus; // 영수증 가격
		int stockCus; // 영수증 재고
		String expDate; // 영수증 유통기한

		System.out.println("영수증 전체 리스트");
		list(); 
		System.out.println();

		System.out.println("수정할 영수증 번호 입력");
		System.out.print("영수증 번호 : ");
		recNo = Integer.parseInt(scan.nextLine());

		System.out.println();
		System.out.println("새로운 정보 모두 입력");
		System.out.print("대분류 : ");
		categoryCus = scan.nextLine();
		System.out.print("소분류  : ");
		subCategoryCus = scan.nextLine();
		System.out.print("가격  : ");
		priceCus = Integer.parseInt(scan.nextLine());
		System.out.print("재고  : ");
		stockCus = Integer.parseInt(scan.nextLine());
		System.out.print("유통기한  : ");
		expDate = scan.nextLine();

		kvo.setRecNo(recNo);
		kvo.setCategoryCus(categoryCus);
		kvo.setSubCategoryCus(subCategoryCus);
		kvo.setPriceCus(priceCus);
		kvo.setStockCus(stockCus);
		kvo.setExpDate(expDate);
		int count = kd.update(kvo);
		
		if(count == 0) {
			System.out.println("영수증 정보 수정 오류발생 조치바람");
			return; 
		}else {
			System.out.println("영수증 정보 수정완료");
		}
		
		System.out.println();
		System.out.println("영수증 전체 리스트");
		list(); 
		System.out.println();
	}

	// 영수증 삭제 관리
	public void delete() throws Exception {
		Scanner scan = new Scanner(System.in);

		KhStokoCusDAO kd = new KhStokoCusDAO();
		KhStokoCusVO kvo = new KhStokoCusVO();

		int recNo; // 영수증 번호
		System.out.println("영수증 전체 리스트");
		list();
		System.out.println();

		System.out.println("삭제할 영수증 번호 입력");
		System.out.print("영수증 번호 : ");
		recNo = Integer.parseInt(scan.nextLine());
		kvo.setRecNo(recNo);
		int count = kd.deleteByNum(kvo);
		if(count == 0) {
			System.out.printf("%s 번호 삭제 문제발생 조치바람\n",recNo);
		}else {
			System.out.printf("%s 번호 삭제성공 \n",recNo);
		}
		System.out.println();
		list();
		System.out.println();
	}

	// 영수증 번호로 검색
public void searchByRecNo() {
    Scanner sc = new Scanner(System.in);
	KhStokoCusDAO kd = new KhStokoCusDAO();

    System.out.print("조회할 영수증 번호(REC_NO)를 입력하세요: ");
    int recNo = sc.nextInt();

    // VO에 recNo 설정
    KhStokoCusVO inputVO = new KhStokoCusVO();
    inputVO.setRecNo(recNo);

    // DAO 메서드 호출
    KhStokoCusVO resultVO = kd.selectByNum(inputVO);

    // 결과 출력
    if (resultVO != null) {
        System.out.println("조회된 영수증 정보:");
        System.out.println(resultVO);
    } else {
        System.out.println("해당 번호의 영수증이 존재하지 않습니다.");
    }
}



}
