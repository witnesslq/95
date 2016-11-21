package com.dhcc.bussiness.sxydidc.order;

public class OriApplyModel {
		private String id;//
	    private String oribusid;//原始工单ID
	    private String oribustype;//原始工单业务类型
	    private String custid;//客户ID
	    private String applytime;//申请时间
	    private String refbusid;//关联工单ID（绿色通道补开通或资源预占取消工单的ID）
	    private String status;
	    private String custName;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getOribusid() {
			return oribusid;
		}
		public void setOribusid(String oribusid) {
			this.oribusid = oribusid;
		}
		public String getOribustype() {
			return oribustype;
		}
		public void setOribustype(String oribustype) {
			this.oribustype = oribustype;
		}
		public String getCustid() {
			return custid;
		}
		public void setCustid(String custid) {
			this.custid = custid;
		}
		public String getApplytime() {
			return applytime;
		}
		public void setApplytime(String applytime) {
			this.applytime = applytime;
		}
		public String getRefbusid() {
			return refbusid;
		}
		public void setRefbusid(String refbusid) {
			this.refbusid = refbusid;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getCustName() {
			return custName;
		}
		public void setCustName(String custName) {
			this.custName = custName;
		}
		
		
	    
}
