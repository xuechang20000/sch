package com.wb.school.f1001.common.vo;

public class ProcessDTO {

	private String processcode;
	private String stepcode;
	private String next_processcode;
	private String next_stepcode;
	
	public ProcessDTO() {
	}
	public ProcessDTO(String processcode, String stepcode) {
		super();
		this.processcode = processcode;
		this.stepcode = stepcode;
	}
	public String getNext_processcode() {
		return next_processcode;
	}
	public void setNext_processcode(String next_processcode) {
		this.next_processcode = next_processcode;
	}
	public String getNext_stepcode() {
		return next_stepcode;
	}
	public void setNext_stepcode(String next_stepcode) {
		this.next_stepcode = next_stepcode;
	}
	public String getProcesscode() {
		return processcode;
	}
	public void setProcesscode(String processcode) {
		this.processcode = processcode;
	}
	public String getStepcode() {
		return stepcode;
	}
	public void setStepcode(String stepcode) {
		this.stepcode = stepcode;
	}
	
}
