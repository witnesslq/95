package com.dhcc.bussiness.sxydidc.customer;

import java.io.Serializable;

/**
 * 客户资料表
 */
public class CustomerModel implements Serializable {
	private String id="";              
	private String name="";            
	private String no="";              
	private String customerlevel="";   
	private String type="";            
	private String contactname="";      //客户联系人
	private String contactphone="";     //客户联系人手机
	private String contactaddress="";  
	private String manager="";
	private String managername="";  
	private String managerphone="";
	private String regionid=""; 
	private String regionname=""; 
	private String parentid="";        
	private String sortname="";        
	private String customerfield="";   
	private String customerproperty="";
	private String icpno="";           
	private String corporate="";
	private String sitename="";
	private String domainname="";
	private String skillpeople="";
	private String subdomain="";
	private String content="";
	private String bandwidth="";
	private String method="";
	private String dispatch="";
	private String prot="";
	private String province=""; 
	private String mobilephone="";
	private String registername="";    
	private String slano="";           
	private String companyname=""; 
	private String createdate="";
	private String bossnum="";
	private String enddate="";
	private String remark="";
	private String status="";
	private String managermobile="";   //客户经理手机
	private String custkey="";
	
	/*添加于 2015.09.16 start*/
	private String type1;  //客户类型 返回值（不是代码） 用于导出excel（）
	private String status1;//客户状态 返回值（不是代码 用于导出excel
	/*添加于 2015.09.16 end*/
	public CustomerModel() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getCustomerlevel() {
		return customerlevel;
	}

	public void setCustomerlevel(String customerlevel) {
		this.customerlevel = customerlevel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContactname() {
		return contactname;
	}

	public void setContactname(String contactname) {
		this.contactname = contactname;
	}

	public String getContactphone() {
		return contactphone;
	}

	public void setContactphone(String contactphone) {
		this.contactphone = contactphone;
	}

	public String getContactaddress() {
		return contactaddress;
	}

	public void setContactaddress(String contactaddress) {
		this.contactaddress = contactaddress;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getManagername() {
		return managername;
	}

	public void setManagername(String managername) {
		this.managername = managername;
	}

	public String getManagerphone() {
		return managerphone;
	}

	public void setManagerphone(String managerphone) {
		this.managerphone = managerphone;
	}

	public String getRegionid() {
		return regionid;
	}

	public void setRegionid(String regionid) {
		this.regionid = regionid;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getSortname() {
		return sortname;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	public String getCustomerfield() {
		return customerfield;
	}

	public void setCustomerfield(String customerfield) {
		this.customerfield = customerfield;
	}

	public String getCustomerproperty() {
		return customerproperty;
	}

	public void setCustomerproperty(String customerproperty) {
		this.customerproperty = customerproperty;
	}

	public String getIcpno() {
		return icpno;
	}

	public void setIcpno(String icpno) {
		this.icpno = icpno;
	}

	public String getCorporate() {
		return corporate;
	}

	public void setCorporate(String corporate) {
		this.corporate = corporate;
	}
	
	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public String getDomainname() {
		return domainname;
	}

	public void setDomainname(String domainname) {
		this.domainname = domainname;
	}

	public String getSkillpeople() {
		return skillpeople;
	}

	public void setSkillpeople(String skillpeople) {
		this.skillpeople = skillpeople;
	}

	public String getSubdomain() {
		return subdomain;
	}

	public void setSubdomain(String subdomain) {
		this.subdomain = subdomain;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(String bandwidth) {
		this.bandwidth = bandwidth;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getDispatch() {
		return dispatch;
	}

	public void setDispatch(String dispatch) {
		this.dispatch = dispatch;
	}

	public String getProt() {
		return prot;
	}

	public void setProt(String prot) {
		this.prot = prot;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getRegistername() {
		return registername;
	}

	public void setRegistername(String registername) {
		this.registername = registername;
	}

	public String getSlano() {
		return slano;
	}

	public void setSlano(String slano) {
		this.slano = slano;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	

	public String getBossnum() {
		return bossnum;
	}

	public void setBossnum(String bossnum) {
		this.bossnum = bossnum;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRegionname() {
		return regionname;
	}

	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getManagermobile() {
		return managermobile;
	}

	public void setManagermobile(String managermobile) {
		this.managermobile = managermobile;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getStatus1() {
		return status1;
	}

	public void setStatus1(String status1) {
		this.status1 = status1;
	}

	public String getCustkey() {
		return custkey;
	}

	public void setCustkey(String custkey) {
		this.custkey = custkey;
	}
}