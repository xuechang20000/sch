package com.wb.login.model;

public class Resourcevo implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long pid;
	private String text;
	private String iconcls;
	private Long resourceid;//undefined
    private Integer domainid;//undefined
    private Integer appid;//undefined
    private Long parentresourceid;//undefined
    private Long code;//undefined
    private String name;//undefined
    private String description;//undefined
    private String url;//undefined
    private String icon1;//undefined
    private String icon2;//undefined
    private String icon3;//undefined
    private Integer priority;//undefined
    private String status;//undefined
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconcls() {
		return iconcls;
	}
	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getResourceid() {
		return resourceid;
	}
	public void setResourceid(Long resourceid) {
		this.resourceid = resourceid;
	}
	public Integer getDomainid() {
		return domainid;
	}
	public void setDomainid(Integer domainid) {
		this.domainid = domainid;
	}
	public Integer getAppid() {
		return appid;
	}
	public void setAppid(Integer appid) {
		this.appid = appid;
	}
	public Long getParentresourceid() {
		return parentresourceid;
	}
	public void setParentresourceid(Long parentresourceid) {
		this.parentresourceid = parentresourceid;
	}
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon1() {
		return icon1;
	}
	public void setIcon1(String icon1) {
		this.icon1 = icon1;
	}
	public String getIcon2() {
		return icon2;
	}
	public void setIcon2(String icon2) {
		this.icon2 = icon2;
	}
	public String getIcon3() {
		return icon3;
	}
	public void setIcon3(String icon3) {
		this.icon3 = icon3;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
}
