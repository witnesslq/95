package com.dhcc.bussiness.sxydidc.contract;

import java.io.PrintWriter;
import org.apache.struts2.ServletActionContext;
import net.sf.json.JSONObject;

import com.dhcc.common.util.AnyTypeAction;
import com.dhcc.common.util.CreateNum;
import com.dhcc.modal.sxydidc.contract.Buscontract;
import com.dhcc.modal.system.PageModel;
import com.mockrunner.util.common.StringUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * 合同信息
 */
public class ContractAction  extends AnyTypeAction<Buscontract,ContractModel>{


	private static final long serialVersionUID = 1L;
	private ContractDao dao = new ContractDao();
	private JSONObject json;
	private String id;
	private String type;
	private String codeType;
	private String ids;
	private String key;
	private String custid;
	private String orderid;
	private String flag;
	private String applyno;
	private ContractModel contract;

	private String flownumber;//工单号
	

	public String queryContract(){
		PageModel pm = new PageModel();
		pm.setCurrentPage(super.getPage());
		pm.setPerPage(super.getPagesize());
		if(StringUtil.isEmptyOrNull(key)){
			if(StringUtil.isEmptyOrNull(flag)){
				if(contract==null){
					pm = dao.queryContract(pm);
				}else{
					pm = dao.queryContractByCondition(pm, contract);
				}				
			}else{
				if(flag.trim().equals("01")){
					if(contract==null){
						pm = dao.queryContract(pm);
					}else{
						pm = dao.queryContractByCondition(pm, contract);
					}
				}else if(flag.trim().equals("02")){
					pm = dao.queryHisContractByApplyno(pm,applyno);
				}
			}
			
		}else{
			pm=dao.quickSearch(pm, key);
		}
		super.setRecord(pm.getTotalRecord());
		super.setTotal(pm.getTotalPage());
		super.setListmodel(pm.getList());
		return SUCCESS;
	}
	
	public String queryContractByCustomer(){
		PageModel pm = new PageModel();
		pm.setCurrentPage(super.getPage());
		pm.setPerPage(super.getPagesize());
		if(StringUtil.isEmptyOrNull(key)){
			if(contract==null){
				custid=ActionContext.getContext().getSession().get("userid").toString();
				pm = dao.queryContractByCustomer(pm, custid,orderid);
			}else{
				pm = dao.queryContractByCondition(pm, contract);
			}

		}else{
			pm=dao.quickSearch(pm, key);
		}
		super.setRecord(pm.getTotalRecord());
		super.setTotal(pm.getTotalPage());
		super.setListmodel(pm.getList());
		return SUCCESS;
	}	
	
	public String deleteContractByIds(){
		 dao.deleteContractByIds(ids);
		return SUCCESS;
	}
	public String queryContractByflownum() {
		contract=dao.queryContractByflownum(flownumber);
		if(contract!=null){
			json = JSONObject.fromObject(contract);
			System.out.println(json);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	public String queryContractById(){
		contract=dao.queryContractById(id);
		if(contract!=null){
			json = JSONObject.fromObject(contract);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
		
	}
	
	public String updateContract(){
		boolean result=dao.updateContract(contract);
		if(result){
			contract=dao.queryContractById(contract.getId());
			json = JSONObject.fromObject(contract);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	
	
	public String saveContract(){
		id=dao.saveContract(contract);
		contract=dao.queryContractById(id);
		if(contract!=null){
			json = JSONObject.fromObject(contract);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	
	public String createCode(){
		CreateNum createNum=new CreateNum();
		String code=createNum.getNum(codeType);
		JSONObject json;
		if(!StringUtil.isEmptyOrNull(code)){
			json = JSONObject.fromObject("{\"result\":\""+code+"\"}");
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	
/*	public String createContractNo(){
		contract=new ContractModel();
		CreateNum createNum=new CreateNum();
		String custNO=createNum.getNum("CONTRACTNO");
		contract.setContractno(custNO);
		if(!StringUtil.isEmptyOrNull(custNO)){
			json = JSONObject.fromObject(contract);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}*/
	
	
	private void setJSON(JSONObject json){
		try {
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			PrintWriter pw = ServletActionContext.getResponse().getWriter();
			pw.print(json);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getApplyno() {
		return applyno;
	}

	public void setApplyno(String applyno) {
		this.applyno = applyno;
	}

	public ContractModel getContract() {
		return contract;
	}

	public void setContract(ContractModel contract) {
		this.contract = contract;
	}

	@Override
	public java.util.List<ContractModel> getListmodel() {
		return super.getListmodel();
	}

	@Override
	public Integer getPage() {
		return super.getPage();
	}

	@Override
	public Integer getPagesize() {
		return super.getPagesize();
	}

	@Override
	public Integer getRecord() {
		return super.getRecord();
	}

	@Override
	public Integer getTotal() {
		return super.getTotal();
	}
	public String getFlownumber() {
		return flownumber;
	}

	public void setFlownumber(String flownumber) {
		this.flownumber = flownumber;
	}
}
