package practice.과제.과제04.dto;

public class EmployeeDto {

	private int 	empNo;				// 사원번호
	private String	empImg;				// 사원사진
	private String 	empName;		 	// 사원명
	private String 	empGrade;	 		// 직급
	private String 	empConstruct;		// 고용형태
	private String	empDepart;	 		// 부서
	private String 	empSdate;	 		// 입사일
	private String	empLdate;			// 퇴사일
	private String	empLcomment;		// 퇴사사유
	
	public EmployeeDto() {
		super();
	}

	// 전체 출력
	public EmployeeDto(int empNo, String empImg, String empName, String empGrade, String empConstruct, String empDepart,
			String empSdate, String empLdate, String empLcomment) {
		super();
		this.empNo = empNo;
		this.empImg = empImg;
		this.empName = empName;
		this.empGrade = empGrade;
		this.empConstruct = empConstruct;
		this.empDepart = empDepart;
		this.empSdate = empSdate;
		this.empLdate = empLdate;
		this.empLcomment = empLcomment;
	}

	// 직무(부서별) 출력
	public EmployeeDto(String empName, String empGrade, String empDepart) {
		super();
		this.empName = empName;
		this.empGrade = empGrade;
		this.empDepart = empDepart;
	}
	
	// 퇴사자 출력
	@Override
	public String toString() {
		return "EmployeeDto [empNo=" + empNo + ", empImg=" + empImg + ", empName=" + empName + ", empGrade=" + empGrade
				+ ", empConstruct=" + empConstruct + ", empDepart=" + empDepart + ", empSdate=" + empSdate
				+ ", empLdate=" + empLdate + ", empLcomment=" + empLcomment + "]";
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpImg() {
		return empImg;
	}

	public void setEmpImg(String empImg) {
		this.empImg = empImg;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpGrade() {
		return empGrade;
	}

	public void setEmpGrade(String empGrade) {
		this.empGrade = empGrade;
	}

	public String getEmpConstruct() {
		return empConstruct;
	}

	public void setEmpConstruct(String empConstruct) {
		this.empConstruct = empConstruct;
	}

	public String getEmpDepart() {
		return empDepart;
	}

	public void setEmpDepart(String empDepart) {
		this.empDepart = empDepart;
	}

	public String getEmpSdate() {
		return empSdate;
	}

	public void setEmpSdate(String empSdate) {
		this.empSdate = empSdate;
	}

	public String getEmpLdate() {
		return empLdate;
	}

	public void setEmpLdate(String empLdate) {
		this.empLdate = empLdate;
	}

	public String getEmpLcomment() {
		return empLcomment;
	}

	public void setEmpLcomment(String empLcomment) {
		this.empLcomment = empLcomment;
	}
	
}
