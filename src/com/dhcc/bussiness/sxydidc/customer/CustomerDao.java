package com.dhcc.bussiness.sxydidc.customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import com.dhcc.common.database.DBManager;
import com.dhcc.common.util.CommUtil;
import com.dhcc.modal.sxydidc.customer.*;
import com.dhcc.modal.system.PageModel;
import com.dhcc.modal.system.Tsdict;
import com.mockrunner.util.common.StringUtil;

public class CustomerDao {
	private static final Logger logger = Logger.getLogger(CustomerDao.class);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static DBManager dbm;  
	
	public void init(){
		logger.info("call RscustomerDao.init() start");
		dbm=new DBManager();
		logger.info("call RscustomerDao.init() finish");
	}
	
	public void destory(){
		logger.info("call RscustomerDao.destory() start");
		dbm.close();
		logger.info("call RscustomerDao.destory() finish");
	}
	
	public String saveCustomer(CustomerModel customerModel){
		this.init();
		logger.info("call RscustomerDao.saveCustomer() start");
		String id = CommUtil.getID();
		if(customerModel!=null){
			customerModel.setId(id);
			customerModel.setStatus("01");
			Date now=new Date();
			try {
				if(StringUtil.isEmptyOrNull(customerModel.getCreatedate())){
					customerModel.setCreatedate(String.valueOf(now.getTime()));
				}else{
					customerModel.setCreatedate(String.valueOf(sdf.parse(customerModel.getCreatedate()).getTime()));
				}
				
				if(StringUtil.isEmptyOrNull(customerModel.getEnddate())){
					customerModel.setEnddate(String.valueOf(now.getTime()));
				}else{
					customerModel.setEnddate(String.valueOf(sdf.parse(customerModel.getEnddate()).getTime()));
				}
				
				if("01".equals(customerModel.getType().trim())){
					Buscgroupuser grp=new Buscgroupuser();
					BeanUtils.copyProperties(grp, customerModel);
					dbm.insertObject(grp,"buscgroupuser");
				}else if("03".equals(customerModel.getType().trim())){
					Buscnetuser net=new Buscnetuser();
					BeanUtils.copyProperties(net, customerModel);
					dbm.insertObject(net, "buscnetuser");
				}
				Busccustomer customer=new Busccustomer();
				BeanUtils.copyProperties(customer, customerModel);
				dbm.insertObject(customer, "busccustomer");
				logger.info("call RscustomerDao.saveCustomer() success");
			} catch (Exception e) {
				logger.info("call RscustomerDao.saveCustomer() fail");
				e.printStackTrace();
			}finally{
				this.destory();
			}
		}
		logger.info("call RscustomerDao.saveCustomer() finish");
		return id;
	}
	
	public boolean deleteCustomerByIds(String ids){
		this.init();
		logger.info("call RscustomerDao.deleteCustomerByIds() start");
		boolean result=false;
		String sql1=null;
		String sql2=null;
		String sql3=null;
			List<String> list=new ArrayList<String>();
			if(!StringUtil.isEmptyOrNull(ids)){
				if(ids.indexOf(",")!=-1){//如果是批量删除
					ids=ids.replaceAll(",", "','");
				}
				sql1=" update busccustomer set status='99' where id in('"+ids+"') ";
				sql2=" update buscgroupuser set status='99' where id in('"+ids+"') ";
				sql3=" update buscnetuser set status='99' where id in('"+ids+"') ";
				
				list.add(sql1);
				list.add(sql2);
				list.add(sql3);
				try {
					result=dbm.excuteBatchSql(list); 
					logger.info("call RscustomerDao.deleteCustomerByIds() success");
				} catch (Exception e) {
					logger.info("call RscustomerDao.deleteCustomerByIds() fail");
					e.printStackTrace();
				}finally{
					this.destory();
				}
			}
		logger.info("call RscustomerDao.deleteCustomerByIds() finish");
		return result;
	}
	
	public CustomerModel queryCustomerById(String id,String type){
		this.init();
		logger.info("call RscustomerDao.queryCustomerById() start");
		CustomerModel customerModel=new CustomerModel();
		if(!StringUtil.isEmptyOrNull(id)){
			StringBuilder sql=new StringBuilder();
			if(StringUtil.isEmptyOrNull(type)){
				try {
					ResultSet rs=dbm.executeQuery(" select type from busccustomer cust where cust.id='"+id+"' ");
					if(rs.next()) {
						type = rs.getString("type");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if("01".equals(type.trim())){
				//集团客户
				sql.append("select cust.*,grp.*,tsu.username as managername,FROM_UNIXTIME((grp.enddate-28800000)/1000,'%Y-%m-%d %H:%i:%s') as enddate,corp.corpname as regionname ,tsu.mobileprivate as managermobile from busccustomer cust left join buscgroupuser grp on cust.id=grp.id left join tscorp corp on corp.id = grp.regionid left join tsuser tsu on grp.manager=tsu.id  where cust.id='"+id+"' ");
			}else if("03".equals(type.trim())){
				//互联网客户
				sql.append("select cust.*,net.*,tsu.username as managername,FROM_UNIXTIME((net.enddate-28800000)/1000,'%Y-%m-%d %H:%i:%s') as enddate,corp.corpname as regionname ,tsu.mobileprivate as managermobile from busccustomer cust left join buscnetuser net on cust.id=net.id left join tscorp corp on corp.id = net.regionid  left join tsuser tsu on net.manager=tsu.id where cust.id='"+id+"'  ");
			}
			try {
				customerModel=(CustomerModel) dbm.getObject(CustomerModel.class, sql.toString()); 
				logger.info("call RscustomerDao.queryCustomerById() success");
			} catch (Exception e) {
				logger.info("call RscustomerDao.queryCustomerById() fail");
				e.printStackTrace();
			}finally{
				this.destory();
			}
		}
		logger.info("call RscustomerDao.queryCustomerById() finish");
		return customerModel;
	}
	
	public Busccustomer queryCustomerById(String id){
		this.init();
		logger.info("call RscustomerDao.queryCustomerById() start");
		Busccustomer customerModel=new Busccustomer();
		if(!StringUtil.isEmptyOrNull(id)){
			StringBuilder sql=new StringBuilder();
					sql.append("select cust.* from busccustomer cust where cust.id='"+id+"' ");
				
				try {
					customerModel=(Busccustomer) dbm.getObject(Busccustomer.class, sql.toString()); 
					logger.info("call RscustomerDao.queryCustomerById() success");
				} catch (Exception e) {
					logger.info("call RscustomerDao.queryCustomerById() fail");
					e.printStackTrace();
				}finally{
					this.destory();
				}
			
		}
		logger.info("call RscustomerDao.queryCustomerById() finish");
		return customerModel;
	}
	
	public 	boolean updateCustomer(CustomerModel customerModel){
		this.init();
		logger.info("call RscustomerDao.updateCustomer() start");
		boolean result=false;
		if(!StringUtil.isEmptyOrNull(customerModel.getId())){
			String sql1=null;
			String sql2=null;
			List<String> list=new ArrayList<String>();
			if(!StringUtil.isEmptyOrNull(customerModel.getType())){
				Date now=new Date();
				try {
					if(StringUtil.isEmptyOrNull(customerModel.getCreatedate())){
						customerModel.setCreatedate(String.valueOf(now.getTime()));
					}else{
						customerModel.setCreatedate(String.valueOf(sdf.parse(customerModel.getCreatedate()).getTime()));
					}
					
					if(StringUtil.isEmptyOrNull(customerModel.getEnddate())){
						customerModel.setEnddate(String.valueOf(now.getTime()));
					}else{
						customerModel.setEnddate(String.valueOf(sdf.parse(customerModel.getEnddate()).getTime()));
					}
					
					sql1=" update busccustomer cust set cust.no='"+customerModel.getNo()+"' , cust.name='"+customerModel.getName()+"' , cust.type='"+customerModel.getType()+"' , cust.remark='"+customerModel.getRemark()+"' where cust.id='"+customerModel.getId()+"' ";
					if("01".equals(customerModel.getType().trim())){//集团客户
						sql2=" update buscgroupuser grp set grp.manager='"+customerModel.getManager()+"',grp.contactname='"+customerModel.getContactname()+"',grp.mobilephone='"+customerModel.getMobilephone()+"',grp.contactphone='"+customerModel.getContactphone()+"',grp.contactaddress='"+customerModel.getContactaddress()+"',grp.customerproperty='"+customerModel.getCustomerproperty()+"',grp.parentid='"+customerModel.getParentid()+"',grp.sortname='"+customerModel.getSortname()+"',grp.corporate='"+customerModel.getCorporate()+"',grp.customerfield='"+customerModel.getCustomerfield()+"',grp.icpno='"+customerModel.getIcpno()+"',grp.province='"+customerModel.getProvince()+"',grp.regionid='"+customerModel.getRegionid()+"',grp.registername='"+customerModel.getRegistername()+"',grp.companyname='"+customerModel.getCompanyname()+"',grp.slano='"+customerModel.getSlano()+"',grp.enddate="+customerModel.getEnddate()+",grp.customerlevel='"+customerModel.getCustomerlevel()+"',grp.createdate="+customerModel.getCreatedate()+" where grp.id='"+customerModel.getId()+"' ";
					}else if("03".equals(customerModel.getType().trim())){//互联网客户
						sql2=" update buscnetuser net set net.manager='"+customerModel.getManager()+"',net.contactname='"+customerModel.getContactname()+"',net.contactphone='"+customerModel.getContactphone()+"',net.contactaddress='"+customerModel.getContactaddress()+"',net.regionid='"+customerModel.getRegionid()+"',net.parentid='"+customerModel.getParentid()+"',net.sortname='"+customerModel.getSortname()+"',net.customerfield='"+customerModel.getCustomerfield()+"',net.customerproperty='"+customerModel.getCustomerproperty()+"',net.icpno='"+customerModel.getIcpno()+"',net.corporate='"+customerModel.getCorporate()+"',net.province='"+customerModel.getProvince()+"',net.registername='"+customerModel.getRegistername()+"',net.slano='"+customerModel.getSlano()+"',net.companyname='"+customerModel.getCompanyname()+"',net.enddate="+customerModel.getEnddate()+",net.createdate="+customerModel.getCreatedate()+",net.sitename='"+customerModel.getSitename()+"',net.domainname='"+customerModel.getDomainname()+"',net.method='"+customerModel.getMethod()+"',net.dispatch='"+customerModel.getDispatch()+"',net.prot='"+customerModel.getProt()+"',net.subdomain='"+customerModel.getSubdomain()+"',net.content='"+customerModel.getContent()+"',net.bandwidth='"+customerModel.getBandwidth()+"',net.mobilephone='"+customerModel.getMobilephone()+"',net.skillpeople='"+customerModel.getSkillpeople()+"',net.bossnum='"+customerModel.getBossnum()+"',net.customerlevel='"+customerModel.getCustomerlevel()+"' where net.id='"+customerModel.getId()+"' ";
					}
					list.add(sql1);
					list.add(sql2);					
					result=dbm.excuteBatchSql(list); 
					logger.info("call RscustomerDao.updateCustomer() success");
				} catch (Exception e) {
					logger.info("call RscustomerDao.updateCustomer() fail");
					e.printStackTrace();
				}finally{
					this.destory();
				}
			}
		}
		logger.info("call RscustomerDao.updateCustomer() finish");
		return result;
	}
	
	public String queryCusByflownum(String flownumber) throws SQLException{
		logger.info("-----根据单号查询客户的信息-----");
		DBManager dbManager = new DBManager();
		String sql = "select t.id,t.no  from  busccustomer t where t.id = (select n.custid from flowcustomer n where n.busid='"+flownumber+"')";
		ResultSet set = dbManager.executeQuery(sql);
		StringBuffer jsonString = new StringBuffer();
		jsonString.append("[");
		while(set.next()){
			String index = set.getString(2).substring(0, 3);
			String clienttype = "01";
			if(index.equals("SYS"))
			{
				clienttype = "02";
			}
			jsonString.append("{");
			jsonString.append("\"clientid\":\"" + set.getString(1) + "\",\"clienttype\":\"" + clienttype + "\"");
			jsonString.append("}");
			
		}
		jsonString.append("]");
		return jsonString.toString();
	}
	
	@SuppressWarnings("unchecked")
	public PageModel queryCustomer(PageModel pm,String customerType){
		this.init();
		logger.info("call RscustomerDao.queryCustomer() start");
		StringBuilder countSql1=new StringBuilder(" select count(*) from busccustomer cust where (cust.status!='99' or cust.status is null) and ( cust.type='03') ");
		StringBuilder countSql2=new StringBuilder(" select count(*) from busccustomer cust where (cust.status!='99' or cust.status is null) and (cust.type='01' ) ");
		
		StringBuilder countSql=new StringBuilder(" select count(*) from busccustomer cust where (cust.status!='99' or cust.status is null) and (cust.type='01' or cust.type='03') ");
		
		String sql1=" select * from (select cust.*,net.customerlevel as customerlevel,net.customerproperty as customerproperty,net.customerfield as customerfield,net.registername as registername,net.contactname as contactname,net.mobilephone as mobilephone,net.contactphone as contactphone,FROM_UNIXTIME(net.createdate/1000,'%Y-%m-%d %H:%i:%s') as createdate,tsu.username as managername,tsu.mobileprivate as managerphone from buscnetuser net left join busccustomer cust on net.id=cust.id left join tsuser tsu on net.manager=tsu.id where cust.id=net.id and (cust.status!='99' or cust.status is null) and cust.type='03' order by cust.no) tempa ";
		String sql2=" select * from (select cust.*,grp.customerlevel as customerlevel,grp.customerproperty as customerproperty,grp.customerfield as customerfield,grp.registername as registername,grp.contactname as contactname,grp.mobilephone as mobilephone,grp.contactphone as contactphone,FROM_UNIXTIME(grp.createdate/1000,'%Y-%m-%d %H:%i:%s') as createdate,tsu.username as managername,tsu.mobileprivate as managerphone from buscgroupuser grp left join busccustomer cust on grp.id=cust.id left join tsuser tsu on grp.manager=tsu.id where cust.id=grp.id and (cust.status!='99' or cust.status is null) and cust.type='01' order by cust.no) tempb ";
		StringBuilder querySql=null;
		StringBuilder countquerySql=null; 
		if(!StringUtil.isEmptyOrNull(customerType)){
			if("03".equals(customerType)){
				querySql = new StringBuilder(sql1);	
				countquerySql = countSql1;
			}else if("01".equals(customerType)){
				querySql = new StringBuilder(sql2);	
				countquerySql = countSql2;
			}			
		}else{
			querySql = new StringBuilder(sql1+" union all "+sql2);
			countquerySql = countSql;
		}

		try {
			int count =dbm.executeQueryCount(countquerySql.toString());
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);

			String sql = querySql.toString()+" limit "+(pm.getCurrentPage()-1)*pm.getPerPage()+","+pm.getPerPage();
			List<CustomerModel> list =  dbm.getObjectList(CustomerModel.class, sql);
			pm.setList(list);
			logger.info("call RscustomerDao.queryCustomer() success");
		} catch (Exception e) {
			logger.info("call RscustomerDao.queryCustomer() fail");
			e.printStackTrace();
		}finally{
			this.destory();
		}
		logger.info("call RscustomerDao.queryCustomer() finish");
		return pm;
	}
	
	@SuppressWarnings("unchecked")
	public PageModel queryAllCustomer(PageModel pm){
		this.init();
		logger.info("call RscustomerDao.queryAllCustomer() start");
		StringBuilder countSql=new StringBuilder(" select count(*) from busccustomer cust where (cust.status!='99' or cust.status is null) ");
		StringBuilder querySql=new StringBuilder(" select * from busccustomer cust where (cust.status!='99' or cust.status is null) order by cust.type,cust.no ");
		try {
			int count =dbm.executeQueryCount(countSql.toString());
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);

			String sql = querySql.toString()+" limit "+(pm.getCurrentPage()-1)*pm.getPerPage()+","+pm.getPerPage();
			List<CustomerModel> list =  dbm.getObjectList(CustomerModel.class, sql);
			pm.setList(list);
			logger.info("call RscustomerDao.queryAllCustomer() success");
		} catch (Exception e) {
			logger.info("call RscustomerDao.queryAllCustomer() fail");
			e.printStackTrace();
		}finally{
			this.destory();
		}
		logger.info("call RscustomerDao.queryAllCustomer() finish");
		return pm;
	}
	
	@SuppressWarnings("unchecked")
	public List<CustomerModel> queryCustomerProperty(String customerName){
		this.init();
		logger.info("call RscustomerDao.queryCustomerProperty() start");
		StringBuilder querySql=new StringBuilder(" select cust.* from busccustomer cust where cust.status!='99' or cust.status is null ");
		List<CustomerModel> list=new ArrayList<CustomerModel>();
		try {
			if(!StringUtil.isEmptyOrNull(customerName)){
				querySql.append(" and cust.name like '%"+customerName+"%' ");
			}
			list=dbm.getObjectList(CustomerModel.class, querySql.toString());
			logger.info("call RscustomerDao.queryCustomerProperty() success");
		} catch (Exception e) {
			logger.info("call RscustomerDao.queryCustomerProperty() fail");
			e.printStackTrace();
		}finally{
			this.destory();
		}
		logger.info("call RscustomerDao.queryCustomerProperty() finish");
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public PageModel queryCustomerByCondition(PageModel pm,CustomerModel customer,String customerType){
		this.init();
		logger.info("call RscustomerDao.queryCustomerByCondition() start");
		String sql1=" select * from (select cust.*,net.customerlevel as customerlevel,net.customerproperty as customerproperty,net.customerfield as customerfield,net.registername as registername,net.contactname as contactname,net.mobilephone as mobilephone,net.contactphone as contactphone,FROM_UNIXTIME(net.createdate/1000,'%Y-%m-%d %H:%i:%s') as createdate,tsu.username as managername,tsu.mobileprivate as managerphone from buscnetuser net left join busccustomer cust on net.id=cust.id left join tsuser tsu on net.manager=tsu.id  where cust.id=net.id and cust.status!='99' and cust.type='03' ";
		String sql2=" select * from (select cust.*,grp.customerlevel as customerlevel,grp.customerproperty as customerproperty,grp.customerfield as customerfield,grp.registername as registername,grp.contactname as contactname,grp.mobilephone as mobilephone,grp.contactphone as contactphone,FROM_UNIXTIME(grp.createdate/1000,'%Y-%m-%d %H:%i:%s') as createdate,tsu.username as managername,tsu.mobileprivate as managerphone from buscgroupuser grp left join busccustomer cust on grp.id=cust.id left join tsuser tsu on grp.manager=tsu.id  where cust.id=grp.id and cust.status!='99' and cust.type='01' ";		
		
		StringBuilder conditionSql=new StringBuilder();
		
		if(!StringUtil.isEmptyOrNull(customer.getManager())){
			conditionSql.append(" and net.manager='").append(customer.getManager()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getCustomerlevel())){
			conditionSql.append(" and net.customerlevel='").append(customer.getCustomerlevel()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getSortname())){
			conditionSql.append(" and net.sortname like '%").append(customer.getSortname()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getContactname())){
			conditionSql.append(" and net.contactname like '%").append(customer.getContactname()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getMobilephone())){
			conditionSql.append(" and net.mobilephone='").append(customer.getMobilephone()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getContactphone())){
			conditionSql.append(" and net.contactphone='").append(customer.getContactphone()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getCustomerproperty())){
			conditionSql.append(" and net.customerproperty='").append(customer.getCustomerproperty()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getCustomerfield())){
			conditionSql.append(" and net.customerfield='").append(customer.getCustomerfield()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getNo())){
			conditionSql.append(" and cust.no like '%").append(customer.getNo()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getName())){
			conditionSql.append(" and cust.name like '%").append(customer.getName()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getType())){
			conditionSql.append(" and cust.type='").append(customer.getType()).append("' ");
		}		
		
		if(!StringUtil.isEmptyOrNull(customer.getStatus())){
			conditionSql.append(" and cust.status='").append(customer.getStatus()).append("' ");
		}
		
		sql1=sql1+conditionSql.toString()+"  order by cust.no) tempa ";
		conditionSql.delete(0, conditionSql.toString().length());
		
		if(!StringUtil.isEmptyOrNull(customer.getManager())){
			conditionSql.append(" and grp.manager='").append(customer.getManager()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getCustomerlevel())){
			conditionSql.append(" and grp.customerlevel='").append(customer.getCustomerlevel()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getSortname())){
			conditionSql.append(" and grp.sortname like '%").append(customer.getSortname()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getContactname())){
			conditionSql.append(" and grp.contactname like '%").append(customer.getContactname()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getMobilephone())){
			conditionSql.append(" and grp.mobilephone='").append(customer.getMobilephone()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getContactphone())){
			conditionSql.append(" and grp.contactphone='").append(customer.getContactphone()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getCustomerproperty())){
			conditionSql.append(" and grp.customerproperty='").append(customer.getCustomerproperty()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getCustomerfield())){
			conditionSql.append(" and grp.customerfield='").append(customer.getCustomerfield()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getNo())){
			conditionSql.append(" and cust.no like '%").append(customer.getNo()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getName())){
			conditionSql.append(" and cust.name like '%").append(customer.getName()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getType())){
			conditionSql.append(" and cust.type='").append(customer.getType()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getStatus())){
			conditionSql.append(" and cust.status='").append(customer.getStatus()).append("' ");
		}
		
		sql2=sql2+conditionSql.toString()+" order by cust.no) tempb ";
		String querySql = "";
		if(!StringUtil.isEmptyOrNull(customerType)){
			if("01".equals(customerType)){
				querySql = sql2;
			}else if("03".equals(customerType)){
				querySql = sql1;
			}			
		}else{
			querySql = sql1+" union all "+sql2;
		}

		String countSql=" select count(*) from ("+querySql+")temp ";
		try {
			int count =dbm.executeQueryCount(countSql);
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);

			String sql = querySql.toString()+" limit "+(pm.getCurrentPage()-1)*pm.getPerPage()+","+pm.getPerPage();
			List<CustomerModel> list =  dbm.getObjectList(CustomerModel.class, sql);
			pm.setList(list);
			logger.info("call RscustomerDao.queryCustomerByCondition() success");
		} catch (Exception e) {
			logger.info("call RscustomerDao.queryCustomerByCondition() fail");
			e.printStackTrace();
		}finally{
			this.destory();
		}
		logger.info("call RscustomerDao.queryCustomerByCondition() finish");
		return pm;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<CustomerModel> queryCustomerByCondition(CustomerModel customer,String customerType){
		List<CustomerModel> list=null;
		this.init();
		logger.info("call RscustomerDao.queryCustomerByCondition() start");
		String sql1=" select * from (select cust.*,net.customerlevel as customerlevel,net.customerproperty as customerproperty,net.customerfield as customerfield,net.registername as registername,net.contactname as contactname,net.mobilephone as mobilephone,net.contactphone as contactphone,FROM_UNIXTIME(net.createdate/1000,'%Y-%m-%d %H:%i:%s') as createdate,tsu.username as managername,tsu.mobileprivate as managerphone from buscnetuser net left join busccustomer cust on net.id=cust.id left join tsuser tsu on net.manager=tsu.id  where cust.id=net.id and cust.status!='99' and cust.type='03' ";
		String sql2=" select * from (select cust.*,grp.customerlevel as customerlevel,grp.customerproperty as customerproperty,grp.customerfield as customerfield,grp.registername as registername,grp.contactname as contactname,grp.mobilephone as mobilephone,grp.contactphone as contactphone,FROM_UNIXTIME(grp.createdate/1000,'%Y-%m-%d %H:%i:%s') as createdate,tsu.username as managername,tsu.mobileprivate as managerphone from buscgroupuser grp left join busccustomer cust on grp.id=cust.id left join tsuser tsu on grp.manager=tsu.id  where cust.id=grp.id and cust.status!='99' and cust.type='01' ";		
		
		StringBuilder conditionSql=new StringBuilder();
		
		if(!StringUtil.isEmptyOrNull(customer.getManager())){
			conditionSql.append(" and net.manager='").append(customer.getManager()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getCustomerlevel())){
			conditionSql.append(" and net.customerlevel='").append(customer.getCustomerlevel()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getSortname())){
			conditionSql.append(" and net.sortname like '%").append(customer.getSortname()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getContactname())){
			conditionSql.append(" and net.contactname like '%").append(customer.getContactname()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getMobilephone())){
			conditionSql.append(" and net.mobilephone='").append(customer.getMobilephone()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getContactphone())){
			conditionSql.append(" and net.contactphone='").append(customer.getContactphone()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getCustomerproperty())){
			conditionSql.append(" and net.customerproperty='").append(customer.getCustomerproperty()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getCustomerfield())){
			conditionSql.append(" and net.customerfield='").append(customer.getCustomerfield()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getNo())){
			conditionSql.append(" and cust.no like '%").append(customer.getNo()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getName())){
			conditionSql.append(" and cust.name like '%").append(customer.getName()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getType())){
			conditionSql.append(" and cust.type='").append(customer.getType()).append("' ");
		}		
		
		if(!StringUtil.isEmptyOrNull(customer.getStatus())){
			conditionSql.append(" and cust.status='").append(customer.getStatus()).append("' ");
		}
		
		sql1=sql1+conditionSql.toString()+"  order by cust.no) tempa ";
		conditionSql.delete(0, conditionSql.toString().length());
		
		if(!StringUtil.isEmptyOrNull(customer.getManager())){
			conditionSql.append(" and grp.manager='").append(customer.getManager()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getCustomerlevel())){
			conditionSql.append(" and grp.customerlevel='").append(customer.getCustomerlevel()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getSortname())){
			conditionSql.append(" and grp.sortname like '%").append(customer.getSortname()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getContactname())){
			conditionSql.append(" and grp.contactname like '%").append(customer.getContactname()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getMobilephone())){
			conditionSql.append(" and grp.mobilephone='").append(customer.getMobilephone()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getContactphone())){
			conditionSql.append(" and grp.contactphone='").append(customer.getContactphone()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getCustomerproperty())){
			conditionSql.append(" and grp.customerproperty='").append(customer.getCustomerproperty()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getCustomerfield())){
			conditionSql.append(" and grp.customerfield='").append(customer.getCustomerfield()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getNo())){
			conditionSql.append(" and cust.no like '%").append(customer.getNo()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getName())){
			conditionSql.append(" and cust.name like '%").append(customer.getName()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getType())){
			conditionSql.append(" and cust.type='").append(customer.getType()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getStatus())){
			conditionSql.append(" and cust.status='").append(customer.getStatus()).append("' ");
		}
		
		sql2=sql2+conditionSql.toString()+" order by cust.no) tempb ";
		String querySql = "";
		if(!StringUtil.isEmptyOrNull(customerType)){
			if("01".equals(customerType)){
				querySql = sql2;
			}else if("03".equals(customerType)){
				querySql = sql1;
			}			
		}else{
			querySql = sql1+" union all "+sql2;
		}

		String countSql=" select count(*) from ("+querySql+")temp ";
		try {

			String sql = querySql.toString();
			 list =  dbm.getObjectList(CustomerModel.class, sql);
			logger.info("call RscustomerDao.queryCustomerByCondition() success");
		} catch (Exception e) {
			logger.info("call RscustomerDao.queryCustomerByCondition() fail");
			e.printStackTrace();
		}finally{
			this.destory();
		}
		logger.info("call RscustomerDao.queryCustomerByCondition() finish");
		return list;
	}
	@SuppressWarnings("unchecked")
	public PageModel queryAllCustomerByCondition(PageModel pm,CustomerModel customer){
		this.init();
		logger.info("call RscustomerDao.queryAllCustomerByCondition() start");
		String querySql=" select * from busccustomer cust where (cust.status!='99' or cust.status is null) ";
		StringBuilder conditionSql=new StringBuilder();
		
		if(!StringUtil.isEmptyOrNull(customer.getNo())){
			conditionSql.append(" and cust.no like '%").append(customer.getNo()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getName())){
			conditionSql.append(" and cust.name like '%").append(customer.getName()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(customer.getType())){
			conditionSql.append(" and cust.type='").append(customer.getType()).append("' ");
		}		
		
		querySql=querySql+conditionSql.toString()+"  order by cust.type,cust.no ";
		String countSql=" select count(*) from ("+querySql+")temp ";
		try {
			int count =dbm.executeQueryCount(countSql);
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);

			String sql = querySql+" limit "+(pm.getCurrentPage()-1)*pm.getPerPage()+","+pm.getPerPage();
			List<CustomerModel> list =  dbm.getObjectList(CustomerModel.class, sql);
			pm.setList(list);
			logger.info("call RscustomerDao.queryAllCustomerByCondition() success");
		} catch (Exception e) {
			logger.info("call RscustomerDao.queryAllCustomerByCondition() fail");
			e.printStackTrace();
		}finally{
			this.destory();
		}
		logger.info("call RscustomerDao.queryAllCustomerByCondition() finish");
		return pm;
	}	
	
	@SuppressWarnings("unchecked")
	public PageModel quickSearch(PageModel pm,CustomerModel customer,String key){
		this.init();
		List<Tsdict> dics=new ArrayList<Tsdict>();
		StringBuilder keys=new StringBuilder("");
		logger.info("call RscustomerDao.quickSearch() start");
		String sql1=" select cust.*,net.customerproperty as customerproperty,net.customerfield as customerfield,net.customerlevel as customerlevel,net.registername as registername,net.contactname as contactname,net.mobilephone as mobilephone,net.contactphone as contactphone,FROM_UNIXTIME(net.createdate/1000,'%Y-%m-%d %H:%i:%s') as createdate,tsu.username as managername,tsu.mobileprivate as managerphone,ck.newkey as custkey from buscnetuser net left join busccustomer cust on cust.id=net.id left join tsuser  tsu on net.manager=tsu.id left join buscustkey ck on cust.id = ck.custid where (cust.status!='99' or cust.status is null) ";
		
		if(customer!=null&&(!StringUtil.isEmptyOrNull(customer.getManager()))){
			sql1=sql1+" and tsu.id='"+customer.getManager()+"' ";
		}
		
		StringBuilder conditionSql=new StringBuilder();
		String sql2=" select cust.*,grp.customerproperty as customerproperty,grp.customerfield as customerfield,grp.customerlevel as customerlevel,grp.registername as registername,grp.contactname as contactname,grp.mobilephone as mobilephone,grp.contactphone as contactphone,FROM_UNIXTIME((grp.createdate-28800000)/1000,'%Y-%m-%d %H:%i:%s') as createdate,tsu.username as managername,tsu.mobileprivate as managerphone,ck.newkey as custkey from buscgroupuser grp left join busccustomer cust on cust.id=grp.id left join tsuser tsu on grp.manager=tsu.id left join buscustkey ck on cust.id = ck.custid where (cust.status!='99' or cust.status is null)  ";
		if(customer!=null&&(!StringUtil.isEmptyOrNull(customer.getManager()))){
			sql2=sql2+" and tsu.id='"+customer.getManager()+"' ";
		}		
		
		if(!StringUtil.isEmptyOrNull(key)){
			conditionSql.append(" cust.no like '%").append(key).append("%' ");
			conditionSql.append(" or cust.name like '%").append(key).append("%' ");
			dics=this.queryDicIds(dbm, key);
			for(Tsdict dic:dics){
				if(dics.lastIndexOf(dic)!=dics.size()-1){
					keys.append("'").append(dic.getDkey()).append("',");
				}else{
					keys.append("'").append(dic.getDkey()).append("'");
				}
			}
			
			if(!StringUtil.isEmptyOrNull(keys.toString())){
				conditionSql.append(" or net.customerlevel in(").append(keys.toString()).append(") ");
				conditionSql.append(" or cust.type in(").append(keys.toString()).append(") ");
				conditionSql.append(" or cust.status in(").append(keys.toString()).append(") ");
				conditionSql.append(" or net.customerproperty in(").append(keys.toString()).append(") ");
				conditionSql.append(" or net.customerfield in(").append(keys.toString()).append(") ");				
			}
			conditionSql.append(" or tsu.loginname like '%").append(key).append("%' ");
			conditionSql.append(" or tsu.mobileprivate like '%").append(key).append("%' ");
			conditionSql.append(" or net.contactname like '%").append(key).append("%' ");
			conditionSql.append(" or net.mobilephone like '%").append(key).append("%' ");
			sql1=sql1+" and ("+conditionSql.toString()+") ";
			
			conditionSql.delete(0, conditionSql.toString().length());
			
			conditionSql.append(" cust.no like '%").append(key).append("%' ");
			conditionSql.append(" or cust.name like '%").append(key).append("%' ");
			if(!StringUtil.isEmptyOrNull(keys.toString())){
				conditionSql.append(" or grp.customerlevel in(").append(keys.toString()).append(") ");
				conditionSql.append(" or cust.type in(").append(keys.toString()).append(") ");
				conditionSql.append(" or cust.status in(").append(keys.toString()).append(") ");
				conditionSql.append(" or grp.customerproperty in(").append(keys.toString()).append(") ");
				conditionSql.append(" or grp.customerfield in(").append(keys.toString()).append(") ");				
			}
			conditionSql.append(" or tsu.loginname like '%").append(key).append("%' ");
			conditionSql.append(" or tsu.mobileprivate like '%").append(key).append("%' ");
			conditionSql.append(" or grp.contactname like '%").append(key).append("%' ");
			conditionSql.append(" or grp.mobilephone like '%").append(key).append("%' ");
			sql2=sql2+" and ("+conditionSql.toString()+") ";
		}
		
		String querySql=" select * from ("+sql1+" union all "+sql2+")temp order by temp.no ";
		String countSql=" select count(*) from ("+querySql+")temp ";
		try {
			int count =dbm.executeQueryCount(countSql);
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);

			String sql = querySql.toString()+" limit "+(pm.getCurrentPage()-1)*pm.getPerPage()+","+pm.getPerPage();
			List<CustomerModel> list =  dbm.getObjectList(CustomerModel.class, sql);
			pm.setList(list);
			logger.info("call RscustomerDao.quickSearch() success");
		} catch (Exception e) {
			logger.info("call RscustomerDao.quickSearch() fail");
			e.printStackTrace();
		}finally{
			this.destory();
		}
		logger.info("call RscustomerDao.quickSearch() finish");
		return pm;
	}
	@SuppressWarnings("unchecked")
	public List<CustomerModel> quickSearch(CustomerModel customer,String key){
		List<CustomerModel> list=null;
		this.init();
		List<Tsdict> dics=new ArrayList<Tsdict>();
		StringBuilder keys=new StringBuilder("");
		logger.info("call RscustomerDao.quickSearch() start");
		String sql1=" select cust.*,net.customerproperty as customerproperty,net.customerfield as customerfield,net.customerlevel as customerlevel,net.registername as registername,net.contactname as contactname,net.mobilephone as mobilephone,net.contactphone as contactphone,FROM_UNIXTIME(net.createdate/1000,'%Y-%m-%d %H:%i:%s') as createdate,tsu.username as managername,tsu.mobileprivate as managerphone,ck.newkey as custkey from buscnetuser net left join busccustomer cust on cust.id=net.id left join tsuser  tsu on net.manager=tsu.id left join buscustkey ck on cust.id = ck.custid where (cust.status!='99' or cust.status is null) ";
		
		if(customer!=null&&(!StringUtil.isEmptyOrNull(customer.getManager()))){
			sql1=sql1+" and tsu.id='"+customer.getManager()+"' ";
		}
		
		StringBuilder conditionSql=new StringBuilder();
		String sql2=" select cust.*,grp.customerproperty as customerproperty,grp.customerfield as customerfield,grp.customerlevel as customerlevel,grp.registername as registername,grp.contactname as contactname,grp.mobilephone as mobilephone,grp.contactphone as contactphone,FROM_UNIXTIME((grp.createdate-28800000)/1000,'%Y-%m-%d %H:%i:%s') as createdate,tsu.username as managername,tsu.mobileprivate as managerphone,ck.newkey as custkey from buscgroupuser grp left join busccustomer cust on cust.id=grp.id left join tsuser tsu on grp.manager=tsu.id left join buscustkey ck on cust.id = ck.custid where (cust.status!='99' or cust.status is null)  ";
		if(customer!=null&&(!StringUtil.isEmptyOrNull(customer.getManager()))){
			sql2=sql2+" and tsu.id='"+customer.getManager()+"' ";
		}		
		
		if(!StringUtil.isEmptyOrNull(key)){
			conditionSql.append(" cust.no like '%").append(key).append("%' ");
			conditionSql.append(" or cust.name like '%").append(key).append("%' ");
			dics=this.queryDicIds(dbm, key);
			for(Tsdict dic:dics){
				if(dics.lastIndexOf(dic)!=dics.size()-1){
					keys.append("'").append(dic.getDkey()).append("',");
				}else{
					keys.append("'").append(dic.getDkey()).append("'");
				}
			}
			
			if(!StringUtil.isEmptyOrNull(keys.toString())){
				conditionSql.append(" or net.customerlevel in(").append(keys.toString()).append(") ");
				conditionSql.append(" or cust.type in(").append(keys.toString()).append(") ");
				conditionSql.append(" or cust.status in(").append(keys.toString()).append(") ");
				conditionSql.append(" or net.customerproperty in(").append(keys.toString()).append(") ");
				conditionSql.append(" or net.customerfield in(").append(keys.toString()).append(") ");				
			}
			conditionSql.append(" or tsu.loginname like '%").append(key).append("%' ");
			conditionSql.append(" or tsu.mobileprivate like '%").append(key).append("%' ");
			conditionSql.append(" or net.contactname like '%").append(key).append("%' ");
			conditionSql.append(" or net.mobilephone like '%").append(key).append("%' ");
			sql1=sql1+" and ("+conditionSql.toString()+") ";
			
			conditionSql.delete(0, conditionSql.toString().length());
			
			conditionSql.append(" cust.no like '%").append(key).append("%' ");
			conditionSql.append(" or cust.name like '%").append(key).append("%' ");
			if(!StringUtil.isEmptyOrNull(keys.toString())){
				conditionSql.append(" or grp.customerlevel in(").append(keys.toString()).append(") ");
				conditionSql.append(" or cust.type in(").append(keys.toString()).append(") ");
				conditionSql.append(" or cust.status in(").append(keys.toString()).append(") ");
				conditionSql.append(" or grp.customerproperty in(").append(keys.toString()).append(") ");
				conditionSql.append(" or grp.customerfield in(").append(keys.toString()).append(") ");				
			}
			conditionSql.append(" or tsu.loginname like '%").append(key).append("%' ");
			conditionSql.append(" or tsu.mobileprivate like '%").append(key).append("%' ");
			conditionSql.append(" or grp.contactname like '%").append(key).append("%' ");
			conditionSql.append(" or grp.mobilephone like '%").append(key).append("%' ");
			sql2=sql2+" and ("+conditionSql.toString()+") ";
		}
		
		String querySql=" select * from ("+sql1+" union all "+sql2+")temp order by temp.no ";
		String countSql=" select count(*) from ("+querySql+")temp ";
		try {


			String sql = querySql.toString();
			 list =  dbm.getObjectList(CustomerModel.class, sql);
			logger.info("call RscustomerDao.quickSearch() success");
		} catch (Exception e) {
			logger.info("call RscustomerDao.quickSearch() fail");
			e.printStackTrace();
		}finally{
			this.destory();
		}
		logger.info("call RscustomerDao.quickSearch() finish");
		return list;
	}
	@SuppressWarnings("unchecked")
	private List<Tsdict> queryDicIds(DBManager dbm,String dvalue){
		List<Tsdict> list=new ArrayList<Tsdict>();
		try {
			list = dbm.getObjectList(Tsdict.class, " select dkey from tsdict where dvalue like '%"+dvalue+"%' ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 查询客户密钥
	public String queryCustomerKey(String likeQuery) {
		this.init();
		try {
			StringBuffer result = new StringBuffer();
			result.append("[");
			
			String sql = "SELECT CUST.NO, CUST.TYPE, CUST.NAME, USER.USERNAME, USER.MOBILEPRIVATE, CUSTKEY.NEWKEY, CUSTKEY.OLDKEY FROM BUSCUSTKEY " +
		  			     "CUSTKEY LEFT OUTER JOIN BUSCCUSTOMER CUST ON CUSTKEY.CUSTID = CUST.ID LEFT OUTER JOIN BUSCGROUPUSER GCUST ON CUST.ID = " +
		  			     "GCUST.ID LEFT OUTER JOIN TSUSER USER ON GCUST.MANAGER = USER.ID WHERE CUST.TYPE = '01' ";
			if(likeQuery != null && !likeQuery.trim().equals("")) {
				sql += "AND CUST.NAME LIKE '%" + likeQuery + "%' ";
			}
			sql += "ORDER BY CUST.NAME";
			ResultSet rs = dbm.executeQuery(sql);
			while(rs.next()) {
				String custNo = rs.getString("NO");
				String custNa = rs.getString("NAME");
				String custTp = rs.getString("TYPE");
				String custMg = rs.getString("USERNAME") == null ? "" : rs.getString("USERNAME");
				String custMp = rs.getString("MOBILEPRIVATE") == null ? "" : rs.getString("MOBILEPRIVATE");
				String custNk = rs.getString("NEWKEY");
				String custOk = rs.getString("OLDKEY") == null ? "" : rs.getString("OLDKEY");
				
				result.append("{");
				result.append("\"customerno\":\"" + custNo + "\",\"customername\":\"" + custNa + "\",\"customertype\":\"" + custTp + "\",\"customermanager\":\"" + custMg + "\",\"managerphone\":\"" + custMp + "\",\"newkey\":\"" + custNk + "\",\"oldkey\":\"" + custOk + "\"");
				result.append("},");
			}
			rs.close();
			
			sql = "SELECT CUST.NO, CUST.TYPE, CUST.NAME, USER.USERNAME, USER.MOBILEPRIVATE, CUSTKEY.NEWKEY, CUSTKEY.OLDKEY FROM BUSCUSTKEY " +
			      "CUSTKEY LEFT OUTER JOIN BUSCCUSTOMER CUST ON CUSTKEY.CUSTID = CUST.ID LEFT OUTER JOIN BUSCNETUSER NCUST ON CUST.ID = " +
			      "NCUST.ID LEFT OUTER JOIN TSUSER USER ON NCUST.MANAGER = USER.ID WHERE CUST.TYPE = '03' ";
			if(likeQuery != null && !likeQuery.trim().equals("")) {
				sql += "AND CUST.NAME LIKE '%" + likeQuery + "%' ";
			}
			sql += "ORDER BY CUST.NAME";
			rs = dbm.executeQuery(sql);
			while(rs.next()) {
				String custNo = rs.getString("NO");
				String custNa = rs.getString("NAME");
				String custTp = rs.getString("TYPE");
				String custMg = rs.getString("USERNAME") == null ? "" : rs.getString("USERNAME");
				String custMp = rs.getString("MOBILEPRIVATE") == null ? "" : rs.getString("MOBILEPRIVATE");
				String custNk = rs.getString("NEWKEY");
				String custOk = rs.getString("OLDKEY") == null ? "" : rs.getString("OLDKEY");
				
				result.append("{");
				result.append("\"customerno\":\"" + custNo + "\",\"customername\":\"" + custNa + "\",\"customertype\":\"" + custTp + "\",\"customermanager\":\"" + custMg + "\",\"managerphone\":\"" + custMp + "\",\"newkey\":\"" + custNk + "\",\"oldkey\":\"" + custOk + "\"");
				result.append("},");
			}
			rs.close();
			
			sql = "SELECT CUST.NO, CUST.TYPE, CUST.NAME, SYS.MANAGER, SYS.CONTACT, CUSTKEY.NEWKEY, CUSTKEY.OLDKEY FROM BUSCUSTKEY " +
	      	  	  "CUSTKEY LEFT OUTER JOIN BUSCCUSTOMER CUST ON CUSTKEY.CUSTID = CUST.ID LEFT OUTER JOIN " +
	      	      "BUSCOWNSYSINFO SYS ON CUST.ID = SYS.ID WHERE CUST.TYPE = '02' ";
			if(likeQuery != null && !likeQuery.trim().equals("")) {
				sql += "AND CUST.NAME LIKE '%" + likeQuery + "%' ";
			}
			sql += "ORDER BY CUST.NAME";
			rs = dbm.executeQuery(sql);
			while(rs.next()) {
				String custNo = rs.getString("NO");
				String custNa = rs.getString("NAME");
				String custTp = rs.getString("TYPE");
				String custMg = rs.getString("MANAGER") == null ? "" : rs.getString("MANAGER");
				String custMp = rs.getString("CONTACT") == null ? "" : rs.getString("CONTACT");
				String custNk = rs.getString("NEWKEY");
				String custOk = rs.getString("OLDKEY") == null ? "" : rs.getString("OLDKEY");
				
				result.append("{");
				result.append("\"customerno\":\"" + custNo + "\",\"customername\":\"" + custNa + "\",\"customertype\":\"" + custTp + "\",\"customermanager\":\"" + custMg + "\",\"managerphone\":\"" + custMp + "\",\"newkey\":\"" + custNk + "\",\"oldkey\":\"" + custOk + "\"");
				result.append("},");
			}
			
			result.append("]");
			return result.toString();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.destory();
		}
		
		return null;
	}
	// 创建客户密钥
	public void initCustomerKeys(String custId) {
		this.init();
		
		String sql = "INSERT INTO BUSCUSTKEY (CUSTID, NEWKEY) VALUES ('" + custId + "', '" + this.getCustKey() + "')";
		dbm.addBatch(sql);
		dbm.executeBatch();
		
		this.destory();
	}
	// 更新客户密钥
	public void updateCustomerKeys(String custId) {
		this.init();
		try {
			String sql = "SELECT NEWKEY FROM BUSCUSTKEY WHERE CUSTID = '" + custId + "'";
			ResultSet rs = dbm.executeQuery(sql);
			String oldKey = null;
			if(rs.next()) {
				oldKey = rs.getString("NEWKEY");
			}
			rs.close();
			
			if(oldKey == null) {
				sql = "INSERT INTO BUSCUSTKEY (CUSTID, NEWKEY) VALUES ('" + custId + "', '" + this.getCustKey() + "')";
				dbm.addBatch(sql);
				dbm.executeBatch();
			} else {
				sql = "UPDATE BUSCUSTKEY SET NEWKEY = '" + this.getCustKey() + "', OLDKEY = '" + oldKey + "' WHERE CUSTID = '" + custId + "'";
				dbm.executeUpdate(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.destory();
		}
	}
	// 生成密钥方法
	private String getCustKey() {
		int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		Random random = new Random();
		for (int i = 10; i > 1; i--) {
		    int index = random.nextInt(i);
		    int tmp = array[index];
		    array[index] = array[i - 1];
		    array[i - 1] = tmp;
		}
		int result = 0;
		for(int i = 0; i < 6; i++) {
		    result = result * 10 + array[i];
		}
		String key = Integer.toString(result);
		if(key.length() == 5) {
			key = key + array[random.nextInt(10)];
		}
		return key;
	}
}