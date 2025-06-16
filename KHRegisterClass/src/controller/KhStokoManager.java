package controller;

import java.util.ArrayList;
import java.util.Scanner;
import model.KhStokoVO;


public class KhStokoManager {
	// 제품 목록
	public void list() throws Exception {
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

	// 제품등록
	public void register() throws Exception {
		Scanner scan = new Scanner(System.in);

		KhStokoDAO kd = new KhStokoDAO();
		KhStokoVO kvo = new KhStokoVO();

		String category; // 대분류
		String subCategory; // 소분류
		int price; // 가격
		int stock; // 재고
		String expDate; // 유통기한
		
		System.out.println("품목 정보 입력");
		System.out.print("대분류  : ");
		category = scan.nextLine();
		System.out.print("소분류  : ");
		subCategory = scan.nextLine();
		System.out.print("가격  : ");
		price = Integer.parseInt(scan.nextLine());
		System.out.print("재고  : ");
		stock = Integer.parseInt(scan.nextLine());
		System.out.print("유통기한  : ");
		expDate = scan.nextLine();

		kvo.setCategory(category);
		kvo.setSubCategory(subCategory);
		kvo.setPrice(price);
		kvo.setStock(stock);
		kvo.setExpDate(expDate);

		kd.insert(kvo); 

		System.out.println();
		System.out.println("품목 전체 리스트");
		list();
		System.out.println();
	}

	// 제품 수정 관리
	public void update() throws Exception {
		Scanner scan = new Scanner(System.in);

		KhStokoDAO kd = new KhStokoDAO();
		KhStokoVO kvo = new KhStokoVO();

		int stokoNo; // 입력한 일련번호
		String category; // 대분류
		String subCategory; // 소분류
		int price; // 가격
		int stock; // 재고
		String expDate; // 유통기한

		System.out.println("품목 전체 리스트");
		list(); 
		System.out.println();

		System.out.println("수정할 품목 번호 입력");
		System.out.print("번호 : ");
		stokoNo = Integer.parseInt(scan.nextLine());

		System.out.println();
		System.out.println("새로운 정보 모두 입력");
		System.out.print("대분류  : ");
		category = scan.nextLine();
		System.out.print("소분류  : ");
		subCategory = scan.nextLine();
		System.out.print("가격  : ");
		price = Integer.parseInt(scan.nextLine());
		System.out.print("재고  : ");
		stock = Integer.parseInt(scan.nextLine());
		System.out.print("유통기한  : ");
		expDate = scan.nextLine();

		kvo.setStockNo(stokoNo);
		kvo.setCategory(category);
		kvo.setSubCategory(subCategory);
		kvo.setPrice(price);
		kvo.setStock(stock);
		kvo.setExpDate(expDate);
		int count = kd.update(kvo);
		
		if(count == 0) {
			System.out.println("품목 정보 수정 오류발생 조치바람");
			return; 
		}else {
			System.out.println("품목 정보 수정완료");
		}
		
		System.out.println();
		System.out.println("품목 전체 리스트");
		list(); 
		System.out.println();
	}

	// 제품 삭제 관리
	public void delete() throws Exception {
		Scanner scan = new Scanner(System.in);

		KhStokoDAO kd = new KhStokoDAO();
		ArrayList<KhStokoVO> khStokoList = kd.selectAll();
		KhStokoVO kvo = new KhStokoVO();
		KhStokoVO findData = null;

		int stokoNo; // 학과번호
		int delQuantity;

		System.out.println("품목 전체 리스트");
		list();
		System.out.println();

		System.out.println("삭제할 품목 번호 입력");
		System.out.print("품목 번호 : ");
		stokoNo = Integer.parseInt(scan.nextLine());
		for(KhStokoVO data : khStokoList) {
			if (data.getStockNo() == stokoNo) {
				findData = data;
			}
		}
		System.out.println("제거할 제품 수량 > ");
		delQuantity = Integer.parseInt(scan.nextLine());

		// 재고 마이너스
		findData.setStock(findData.getStock() - delQuantity);

		int count = kd.deleteByNum(kvo);
		if(count == 0) {
			System.out.printf("%s 번호 삭제 문제발생 조치바람\n",stokoNo);
		}else {
			System.out.printf("%s 번호 삭제성공 \n",stokoNo);
		}
		System.out.println();
		list();
		System.out.println();
	}

}
