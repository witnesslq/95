package com.dhcc.bussiness.sxydidc.order;


import java.io.PrintWriter;
import org.apache.struts2.ServletActionContext;
import net.sf.json.JSONObject;
import com.dhcc.bussiness.sxydidc.order.OrderModel;
import com.dhcc.modal.system.Tsuser;
import com.mockrunner.util.common.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 客户信息
 */
public class OrderAction   extends ActionSupport {


	private static final long serialVersionUID = 1L;
	private OrderDao dao = new OrderDao();
	
	private String id;
	private String ids;
	private String key;
	private String applyno;
	private String flag;
	private String restype;
	private String sortname;// 排序列名
	private String sortorder;// 排序方向 asc
	private Tsuser user;
	private OrderModel order;

	private OriApplyModel oriapplyModel;
	private String flownum;
	private String orderid;
	private String customerId;
	private JSONObject json;
	
	
	
	public String queryOccuipedResourceCount(){
		OrderResModel orderRes=new OrderResModel();
		orderRes.setServerCount(dao.queryOccuipedResourceCount(this.customerId, "SERVER"));
		orderRes.setRackCount(dao.queryOccuipedResourceCount(this.customerId, "RACK"));
		orderRes.setUseatCount(dao.queryOccuipedResourceCount(this.customerId, "USEAT"));
		orderRes.setIpsegCount(dao.queryOccuipedResourceCount(this.customerId,"IPSEG"));
		orderRes.setIpCount(dao.queryOccuipedResourceCount(this.customerId,"IP"));
		orderRes.setPortCount(dao.queryOccuipedResourceCount(this.customerId,"PORT"));
		
		json = JSONObject.fromObject(orderRes);
		setJSON(json);
		return SUCCESS;
	}
	
	public String queryOrderResourceCount(){
		OrderResModel orderRes=new OrderResModel();
		if(StringUtil.isEmptyOrNull(flag)){
			orderRes.setServerCount(dao.queryOrderResourceCount(order,"SERVER"));
			orderRes.setRackCount(dao.queryOrderResourceCount(order,"RACK"));
			orderRes.setUseatCount(dao.queryOrderResourceCount(order,"USEAT"));
			orderRes.setIpsegCount(dao.queryOrderResourceCount(order,"IPSEG"));
			orderRes.setIpCount(dao.queryOrderResourceCount(order,"IP"));
			orderRes.setPortCount(dao.queryOrderResourceCount(order,"PORT"));
		}else{
			if(flag.trim().equals("01")){
				orderRes.setServerCount(dao.queryOrderResourceCount(order,"SERVER"));
				orderRes.setRackCount(dao.queryOrderResourceCount(order,"RACK"));
				orderRes.setUseatCount(dao.queryOrderResourceCount(order,"USEAT"));
				orderRes.setIpsegCount(dao.queryOrderResourceCount(order,"IPSEG"));
				orderRes.setIpCount(dao.queryOrderResourceCount(order,"IP"));
				orderRes.setPortCount(dao.queryOrderResourceCount(order,"PORT"));
			}else if(flag.trim().equals("02")){
				orderRes.setServerCount(dao.queryHisOrderResourceCountByApplyno(order,"SERVER"));
				orderRes.setRackCount(dao.queryHisOrderResourceCountByApplyno(order,"RACK"));
				orderRes.setUseatCount(dao.queryHisOrderResourceCountByApplyno(order,"USEAT"));
				orderRes.setIpsegCount(dao.queryHisOrderResourceCountByApplyno(order,"IPSEG"));
				orderRes.setIpCount(dao.queryHisOrderResourceCountByApplyno(order,"IP"));
				orderRes.setPortCount(dao.queryHisOrderResourceCountByApplyno(order,"PORT"));
			}
		}
		orderRes.setBusOrdCount(dao.queryBusSerCount(order, "BUSORD"));
		orderRes.setSerOrdCount(dao.queryBusSerCount(order, "SERORD"));
		json = JSONObject.fromObject(orderRes);
		setJSON(json);
		return SUCCESS;
	}
	
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
	
	

	public String getApplyno() {
		return applyno;
	}

	public void setApplyno(String applyno) {
		this.applyno = applyno;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getRestype() {
		return restype;
	}

	public void setRestype(String restype) {
		this.restype = restype;
	}
	
	

	public String getSortname() {
		return sortname;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	public String getSortorder() {
		return sortorder;
	}

	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}

	public Tsuser getUser() {
		return user;
	}

	public void setUser(Tsuser user) {
		this.user = user;
	}

	public OrderModel getOrder() {
		return order;
	}

	public void setOrder(OrderModel order) {
		this.order = order;
	}

	

	public String getFlownum() {
		return flownum;
	}

	public void setFlownum(String flownum) {
		this.flownum = flownum;
	}

	public OriApplyModel getOriapplyModel() {
		return oriapplyModel;
	}

	public void setOriapplyModel(OriApplyModel oriapplyModel) {
		this.oriapplyModel = oriapplyModel;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}