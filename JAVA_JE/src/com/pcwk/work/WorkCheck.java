package com.pcwk.work;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WorkCheck {
//	 사원번호, 이름
	WorkDao workdao = new WorkDao();//물어보기

	public static void main(String[] args) {
		WorkCheck workCheck = new WorkCheck();
		Scanner sc = new Scanner(System.in);
		String inCommand = ""; // 명령어 입력
		
		while (true) {
			System.out.println("===사원 출근 관리 프로그램===");
			System.out.println("1. 사원 등록");
			System.out.println("2. 출근 체크");
			System.out.println("3. 출근 현황 보기");
			System.out.println("4. 종료");
			System.out.println("========================");
			System.out.print("해당 번호를 입력하세요 >>");
			
			inCommand = sc.nextLine();
			inCommand = inCommand.trim();
			String[] dataArr = null;
			String readInput=""; // case내에서 입력 받는것 
			int flag = 0; // 사원번호가 있으면(1), 없으면(0)
			
			switch (inCommand) {
			case "1":
				System.out.print("사원 번호와 사원 이름 입력하세요(ex. 999,송사원) >> ");
				readInput = sc.nextLine().trim();
				//System.out.println(readInput);
				dataArr = readInput.split(",");
				
				String checkStatus = "-";
				Employee employee = new Employee(dataArr[0], dataArr[1], checkStatus);//

				int statusA = workCheck.workdao.doSave(employee);
				if (statusA == 1) { // 정상 등록
					System.out.println("사원번호 "+readInput + "님이 입력되었습니다.");
				} else { //중복
					System.out.println("등록 실패!");
				}
				
				workCheck.workdao.saveFile(WorkDao.SAVE_FILE_PATH);
				
				
				break;
			case "2":
				System.out.print("사원 번호를 입력하세요 >>");
				readInput = sc.nextLine().trim();
//				System.out.println(readInput);
						
					while(true) {
						
						for(Employee e : WorkDao.employeeList) {
							if(e.getEmployeeNum().equals(readInput)) {
								flag=1;
								break;
							}
						}//--for
						
						
						if(flag == 0) {
							System.out.println("잘못된 사원 번호입니다. 다시 입력하세요.");
							System.out.print("사원 번호를 입력하세요 >>");
							readInput = sc.nextLine().trim();
							continue;
						}else if (flag == 1) {
							
							int hour = (int)(Math.random() * 2+8); // 시
							int min = (int)(Math.random() * 59+1); // 분
							int ischeck = 0;					   // 출근(1), 지각(0)
							if(hour == 9) {
								System.out.println("[출근 체크 완료]");
								System.out.println("현재 시각 : "+hour+"시"+min+"분");
								System.out.println("★지각입니다★");
								
							}else {
								System.out.println("[출근 체크 완료]");
								System.out.println("현재 시각 : "+hour+"시"+min+"분");
								System.out.println("★정상 출근 입니다★");
								ischeck = 1;
							}//--else
							
							workCheck.workdao.checkChange(readInput, ischeck);
							
							// 파일에 저장
							workCheck.workdao.saveFile(WorkDao.SAVE_FILE_PATH);

							break;
						}//else if(flag == 1)
					}//--while
					break; // case break문
			case "3": 
				System.out.println("  << 사원 출근 현황 보기 >>");
				System.out.println("| 사원번호|  이름\t| 출근상태|");
				try(BufferedReader br = new BufferedReader(new FileReader(WorkDao.SAVE_FILE_PATH))){
					String line = ""; 
					
					while((line = br.readLine()) != null) {
						String[] totalEmp = line.split(",");
						
						System.out.println("| "+totalEmp[0]+"\t| "+totalEmp[1]+"  | "+totalEmp[2]+"\t|");
					}
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
			case "4":
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
				
				break;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력하세요.");
				break;

		}

	}

	}
}
