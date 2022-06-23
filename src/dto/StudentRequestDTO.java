package dto;

import java.util.List;

public class StudentRequestDTO {
	private String stuId;
	private String stuName ;
	private String stuDob ;
	private String stuGender ;
	private String stuPhone ;
	private String stuEducation ;
	private List<String> stuAttend;
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuDob() {
		return stuDob;
	}
	public void setStuDob(String stuDob) {
		this.stuDob = stuDob;
	}
	public String getStuGender() {
		return stuGender;
	}
	public void setStuGender(String stuGender) {
		this.stuGender = stuGender;
	}
	public String getStuPhone() {
		return stuPhone;
	}
	public void setStuPhone(String stuPhone) {
		this.stuPhone = stuPhone;
	}
	public String getStuEducation() {
		return stuEducation;
	}
	public void setStuEducation(String stuEducation) {
		this.stuEducation = stuEducation;
	}
	public List<String> getStuAttend() {
		return stuAttend;
	}
	public void setStuAttend(List<String> stuAttend) {
		this.stuAttend = stuAttend;
	}
}
