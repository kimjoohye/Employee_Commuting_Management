package com.pcwk.work;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkDao {
	// 저장 파일에서 읽어 들인 Data : CRUD, 마지막에 파일에 기록
	public static List<Employee> employeeList = new ArrayList<>();
	// 저장 파일 경로
	public final static String SAVE_FILE_PATH = "C:\\DCOM_20220127\\01_JAVA\\workspace\\JAVA_JE\\src\\com\\pcwk\\work\\employeeList.csv";
	
	
	public WorkDao() {
		int readStatus = readFile(SAVE_FILE_PATH);
		if(0 == readStatus) {
			System.out.println("파일 읽기 실패");
		}else {
			System.out.println("파일 읽기 성공");
		}
	}
	
	/**
	 * employeeList.csv를 읽어서 bookList할당
	 * @param filePath
	 * @return 0(실패)/1(성공)
	 */
	public int readFile(String filePath) {
		int flag = 0;
		// try-with-resource
		try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line = "";
			
			int i = 0;
			while( (line = br.readLine()) != null ) {
				String[] inArray = line.split(",");
				Employee emp = new Employee(inArray[0], inArray[1], inArray[2]);
				employeeList.add(emp);
			}
			
			if(employeeList.size() > 0) flag++;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * ArrayList에 있는 내용을 SAVE_FILE_PATH에 기록
	 * @param filePath
	 * @return 0(실패)/1(성공)
	 */
	public int saveFile(String filePath) {
		int writeCnt = 0;
		// FileWriter fw = new FileWriter(filePath);
		
		//try-with-resource
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
			
			// arrayList -> file 기록
			for(Employee emp : employeeList) {
				String del = ",";
				
				String outData = emp.getEmployeeNum()+del+
								 emp.getEmployeeName()+del+
								 emp.getCheckStatus()+"\n";
				
				bw.write(outData);
				
				
				writeCnt++;
			} 	
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return writeCnt;
	}
	
	/**
	 * EmployeeNum이 존재하는지 확인
	 * @param emp
	 * @return 1(존재)/0(없음)
	 */
	public int isEmployeeExists(Employee emp) {
		int flag = 0;
		
		for(Employee employee : employeeList) {
			if(employee.getEmployeeNum().equals(emp.getEmployeeNum())) {
				flag = 1;
				break;
			}
		}
		
		return flag;
	}
	
	public int doSave(Employee emp) {
		
		if(1 == isEmployeeExists(emp)) { // 정상 등록
			System.out.println("사원번호가 존재합니다.\n중복 사원 번호 "+ emp.getEmployeeNum());
			return -1;
		}
		boolean flag = employeeList.add(emp);
//		 for(Employee emp2 : employeeList) {
//			 System.out.println(emp2);
//		 }
		return (flag == true)? 1: 0;
	
	
	}
	
	// 출근 체크 여부 변경
	public void checkChange(String readInput, int ischeck) {
		
		for(int i = employeeList.size()-1; i >= 0; i--) {
			Employee empU = employeeList.get(i);
			//사원 번호로 비교
			if(empU.getEmployeeNum().equals(readInput)) {
				if(ischeck == 0) {
					empU.setCheckStatus("지각");
				}else {
					empU.setCheckStatus("출근");
				}
				break;
			}//--if(empU.getEmployeeNum().equals(readInput))
		
		}//--for
	}
	

}
