package com.dhcc.bussiness.sxydidc.contract;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import com.dhcc.common.database.DBManager;
import com.dhcc.common.system.page.PageFactory;
import com.dhcc.common.util.CommUtil;
import com.dhcc.modal.sxydidc.contract.Buscontract;
import com.dhcc.modal.system.PageModel;
import com.mockrunner.util.common.StringUtil;

public class ContractDao {
	private static final Logger logger = Logger.getLogger(ContractDao.class);
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	private DBManager dbm;  

	
	public PageModel queryContract(PageModel pm){
		dbm=new DBManager();
		logger.info("call ContractDao.queryContract() start");
		StringBuilder countSql=new StringBuilder(" select count(*) from buscontract tract where (tract.status!='99' or tract.status is null) ");
		StringBuilder querySql=new StringBuilder(" select cust.no as custno,cust.name as custname,tract.*,FROM_UNIXTIME(tract.signdate/1000,'%Y-%m-%d %H:%i:%s') as signdate,FROM_UNIXTIME(tract.starttime/1000,'%Y-%m-%d %H:%i:%s') as starttime,FROM_UNIXTIME(tract.endtime/1000,'%Y-%m-%d %H:%i:%s') as endtime from buscontract tract left join busccustomer cust on tract.custid=cust.id where (tract.status!='99' or tract.status is null) order by cust.no,tract.starttime ");	
		try {
			int count =dbm.executeQueryCount(countSql.toString());
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);
			
			/**
			 * 分页sql构造
			 */
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql.toString(), pm);
			pageFactory = null;

			List<ContractModel> list =  dbm.getObjectList(ContractModel.class, sql);
			pm.setList(list);
			logger.info("call ContractDao.queryContract() success");
		} catch (Exception e) {
			logger.info("call ContractDao.queryContract() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call ContractDao.queryContract() finish");
		return pm;
	}
	
	public PageModel queryContractByCondition(PageModel pm,ContractModel contract){
		dbm=new DBManager();
		logger.info("call ContractDao.queryContractByCondition() start");
		String countSql="  select count(*) from buscontract tract where (tract.status!='99' or tract.status is null) ";
		String querySql="  select cust.no as custno,cust.name as custname,tract.*,FROM_UNIXTIME(tract.signdate/1000,'%Y-%m-%d %H:%i:%s') as signdate,FROM_UNIXTIME(tract.starttime/1000,'%Y-%m-%d %H:%i:%s') as starttime,FROM_UNIXTIME(tract.endtime/1000,'%Y-%m-%d %H:%i:%s') as endtime from buscontract tract left join busccustomer cust on tract.custid=cust.id where (tract.status!='99' or tract.status is null) ";
		StringBuilder conditionSql=new StringBuilder();
		if(!StringUtil.isEmptyOrNull(contract.getContractno())){
			conditionSql.append(" and tract.contractno like '%").append(contract.getContractno()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(contract.getCustid())){
			conditionSql.append(" and tract.custid='").append(contract.getCustid()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(contract.getOrderid())){
			conditionSql.append(" and tract.orderid='").append(contract.getOrderid()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(contract.getSigner())){
			conditionSql.append(" and tract.signer like '%").append(contract.getSigner()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(contract.getIsvalid())){
			conditionSql.append(" and tract.isvalid='").append(contract.getIsvalid()).append("' ");
		}
		
		
		countSql=countSql+conditionSql.toString();
		querySql=querySql+conditionSql.toString();
		querySql=querySql+" order by cust.no,tract.starttime ";
		try {
			int count =dbm.executeQueryCount(countSql);
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);

			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql, pm);
			pageFactory = null;
			
			List<ContractModel> list =  dbm.getObjectList(ContractModel.class, sql);
			pm.setList(list);
			logger.info("call ContractDao.queryContractByCondition() success");
			
		} catch (Exception e) {
			logger.info("call ContractDao.queryContractByCondition() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call ContractDao.queryContractByCondition() finish");
		return pm;		
	}
	
	public PageModel queryContractByCustomer(PageModel pm,String custid,String orderid){
		dbm=new DBManager();
		logger.info("call ContractDao.queryContractByCustomer() start");
		StringBuilder querySql=new StringBuilder(" select * from (select cust.no as custno,cust.name as custname,tract.id as id,tract.contractno as contractno,tract.signer as signer,tract.contractterm as contractterm,tract.amount as amount, tract.discount as discount,tract.isvalid as isvalid,FROM_UNIXTIME(tract.signdate/1000,'%Y-%m-%d %H:%i:%s') as signdate,FROM_UNIXTIME(tract.starttime/1000,'%Y-%m-%d %H:%i:%s') as starttime,FROM_UNIXTIME(tract.endtime/1000,'%Y-%m-%d %H:%i:%s') as endtime from buscontract tract left join buscnetuser net on tract.custid=net.id left join busccustomer cust on net.id=cust.id left join tsuser tsu on net.manager=tsu.id left join busorder border on tract.orderid=border.id where (tract.status!='99' or tract.status is null) and cust.type='03' ");
		if(!StringUtil.isEmptyOrNull(custid)){
			querySql.append(" and tsu.id='").append(custid).append("' ");
		}
		if(!StringUtil.isEmptyOrNull(orderid)){
			querySql.append(" and border.id='").append(orderid).append("' ");
		}
		querySql.append(" order by cust.no,tract.starttime)tempa ");
		querySql.append(" union all ");
		querySql.append(" select * from (select cust.no as custno,cust.name as custname,tract.id as id,tract.contractno as contractno,tract.signer as signer,tract.contractterm as contractterm,tract.amount as amount, tract.discount as discount,tract.isvalid as isvalid,FROM_UNIXTIME(tract.signdate/1000,'%Y-%m-%d %H:%i:%s') as signdate,FROM_UNIXTIME(tract.starttime/1000,'%Y-%m-%d %H:%i:%s') as starttime,FROM_UNIXTIME(tract.endtime/1000,'%Y-%m-%d %H:%i:%s') as endtime from buscontract tract left join buscgroupuser grp on tract.custid=grp.id left join busccustomer cust on grp.id=cust.id left join tsuser tsu on grp.manager=tsu.id left join busorder border on tract.orderid=border.id where (tract.status!='99' or tract.status is null) and cust.type='01' ");
		if(!StringUtil.isEmptyOrNull(custid)){
			querySql.append(" and tsu.id='").append(custid).append("' ");
		}
		if(!StringUtil.isEmptyOrNull(orderid)){
			querySql.append(" and border.id='").append(orderid).append("' ");
		}		
		querySql.append(" order by cust.no,tract.starttime)tempb ");
		StringBuilder countSql=new StringBuilder(" select count(*) from ("+querySql.toString()+")temp");
		try {
			int count =dbm.executeQueryCount(countSql.toString());
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);

			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql.toString(), pm);
			pageFactory = null;
			
			List<ContractModel> list =  dbm.getObjectList(ContractModel.class, sql);
			pm.setList(list);
			logger.info("call ContractDao.queryContractByCustomer() success");
			
		} catch (Exception e) {
			logger.info("call ContractDao.queryContractByCustomer() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call ContractDao.queryContractByCustomer() finish");
		return pm;		
	}
	
	public PageModel queryHisContractByApplyno(PageModel pm,String applyno){
		dbm=new DBManager();
		logger.info("call ContractDao.queryHisContractByCustomer() start");
		String countSql="  select count(*) from buscontract tract left join busorderhis border on tract.orderid=border.orderid  where (tract.status!='99' or tract.status is null) ";
		String querySql="  select tract.*,FROM_UNIXTIME(tract.signdate/1000,'%Y-%m-%d %H:%i:%s') as signdate,FROM_UNIXTIME(tract.starttime/1000,'%Y-%m-%d %H:%i:%s') as starttime,FROM_UNIXTIME(tract.endtime/1000,'%Y-%m-%d %H:%i:%s') as endtime from buscontract tract left join busorderhis border on tract.orderid=border.orderid where (tract.status!='99' or tract.status is null) ";
		StringBuilder conditionSql=new StringBuilder();
		
		if(!StringUtil.isEmptyOrNull(applyno)){
			conditionSql.append(" and border.applyno='").append(applyno).append("' ");
		}
		countSql=countSql+conditionSql.toString();
		querySql=querySql+conditionSql.toString();
		querySql=querySql+" order by tract.contractno,tract.starttime ";
		try {
			int count =dbm.executeQueryCount(countSql);
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);

			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql, pm);
			pageFactory = null;
			
			List<ContractModel> list =  dbm.getObjectList(ContractModel.class, sql);
			pm.setList(list);
			logger.info("call ContractDao.queryHisContractByCustomer() success");
			
		} catch (Exception e) {
			logger.info("call ContractDao.queryHisContractByCustomer() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call ContractDao.queryHisContractByCustomer() finish");
		return pm;		
	}	
		
	public boolean deleteContractByIds(String ids){
		dbm=new DBManager();
		logger.info("call ContractDao.deleteContractByIds() start");
		boolean result=false;
		StringBuilder deleteSql=null;
		List<String> list=new ArrayList<String>();  
			if(!StringUtil.isEmptyOrNull(ids)){
				if(ids.indexOf(",")!=-1){//如果是批量删除
					ids=ids.replaceAll(",", "','");
				}
				//deleteSql=new StringBuilder(" delete from buscontract where id in('"+ids+"') ");
				deleteSql=new StringBuilder(" update buscontract set status='99' where id in('"+ids+"') ");
				list.add(deleteSql.toString());
				try {
					result=dbm.excuteBatchSql(list); 
					logger.info("call ContractDao.deleteContractByIds() success");
				} catch (Exception e) {
					logger.info("call ContractDao.deleteContractByIds() fail");
					e.printStackTrace();
				}finally{
					dbm.close();
					dbm=null;
				}
			}
		logger.info("call ContractDao.deleteContractByIds() finish");
		return result;
	}
        public ContractModel queryContractByflownum(String flownumber){
        	dbm=new DBManager();
    		logger.info("call ContractDao.queryContractById() start");
    		ContractModel contractModel=new ContractModel();
    		
    		if(!StringUtil.isEmptyOrNull(flownumber)){
    			
    			StringBuilder querySql=new StringBuilder(" select tract.* from flowcontract tract where tract.busid='"+flownumber+"'");
    			try {
    				Buscontract contract =  (Buscontract) dbm.getObject(Buscontract.class, querySql.toString());
    				
    				BeanUtils.copyProperties(contractModel, contract);
    				contractModel.setStarttime(sdf.format(contract.getStarttime()));
    				contractModel.setEndtime(sdf.format(contract.getEndtime()));
    				contractModel.setSigndate(sdf.format(contract.getSigndate()));
    				logger.info("call ContractDao.queryContractById() success");
    			} catch (Exception e) {
    				logger.info("call ContractDao.queryContractById() fail");
    				e.printStackTrace();
    			}finally{
    				dbm.close();
    				dbm=null;
    			}
    		}
    		logger.info("call ContractDao.queryContractById() finish");
    		
    		return contractModel;
		
		
	}
	public ContractModel queryContractById(String id){
		dbm=new DBManager();
		logger.info("call ContractDao.queryContractById() start");
		ContractModel contractModel=new ContractModel();
		if(!StringUtil.isEmptyOrNull(id)){
			StringBuilder querySql=new StringBuilder(" select cust.no as custno,cust.name as custname,tract.* from buscontract tract left join busccustomer cust on tract.custid=cust.id where tract.id='"+id+"' ");
			try {
				ContractModel contract =  (ContractModel) dbm.getObject(ContractModel.class, querySql.toString());
				BeanUtils.copyProperties(contractModel, contract);
				contractModel.setStarttime(sdf.format(new Long(contract.getStarttime())));
				contractModel.setEndtime(sdf.format(new Long(contract.getEndtime())));
				contractModel.setSigndate(sdf.format(new Long(contract.getSigndate())));
				logger.info("call ContractDao.queryContractById() success");
			} catch (Exception e) {
				logger.info("call ContractDao.queryContractById() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call ContractDao.queryContractById() finish");
		return contractModel;
	}

	public String saveContract(ContractModel contractModel){
		dbm=new DBManager();
		logger.info("call ContractDao.saveContract() start");
		String id=CommUtil.getID();
		if(contractModel!=null){
			Buscontract contract=new Buscontract();
			Date now=new Date();
			contractModel.setId(id);
			try {
				if(StringUtil.isEmptyOrNull(contractModel.getSigndate())){
					contractModel.setSigndate(String.valueOf(now.getTime()));
				}else{
					contractModel.setSigndate(String.valueOf(sdf.parse(contractModel.getSigndate()).getTime()));
				}
				
				if(StringUtil.isEmptyOrNull(contractModel.getStarttime())){
					contractModel.setStarttime(String.valueOf(now.getTime()));
				}else{
					contractModel.setStarttime(String.valueOf(sdf.parse(contractModel.getStarttime()).getTime()));
				}
				
				if(StringUtil.isEmptyOrNull(contractModel.getEndtime())){
					contractModel.setEndtime(String.valueOf(now.getTime()));
				}else{
					contractModel.setEndtime(String.valueOf(sdf.parse(contractModel.getEndtime()).getTime()));
				}				
				BeanUtils.copyProperties(contract, contractModel);
				dbm.insertObject(contract, "buscontract");
				logger.info("call ContractDao.saveContract() success");
			} catch (Exception e) {
				logger.info("call ContractDao.saveContract() fail");
				e.printStackTrace();
			} finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call ContractDao.saveContract() finish");
		return id;
	}

	public boolean updateContract(ContractModel contractModel){
		dbm=new DBManager();
		logger.info("call ContractDao.updateContract() start");
		boolean result=false;
		if(!StringUtil.isEmptyOrNull(contractModel.getId())){
			Buscontract contract=new Buscontract();
			Date now=new Date();
			try {
				if(StringUtil.isEmptyOrNull(contractModel.getSigndate())){
					contractModel.setSigndate(String.valueOf(now.getTime()));
				}else{
					contractModel.setSigndate(String.valueOf(sdf.parse(contractModel.getSigndate()).getTime()));
				}
				
				if(StringUtil.isEmptyOrNull(contractModel.getStarttime())){
					contractModel.setStarttime(String.valueOf(now.getTime()));
				}else{
					contractModel.setStarttime(String.valueOf(sdf.parse(contractModel.getStarttime()).getTime()));
				}
				
				if(StringUtil.isEmptyOrNull(contractModel.getEndtime())){
					contractModel.setEndtime(String.valueOf(now.getTime()));
				}else{
					contractModel.setEndtime(String.valueOf(sdf.parse(contractModel.getEndtime()).getTime()));
				}				
				BeanUtils.copyProperties(contract, contractModel);
				result=dbm.updateObject(contract); 
				logger.info("call ContractDao.updateContract() success");
			} catch (Exception e) {
				result=false;
				logger.info("call ContractDao.updateContract() fail");
				e.printStackTrace();
			} finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call ContractDao.updateContract() finish");
		return result;		
	}
	
	public PageModel quickSearch(PageModel pm,String key){
		dbm=new DBManager();
		logger.info("call ContractDao.quickSearch() start");
		String countSql="  select count(*) from buscontract tract where (tract.status!='99' or tract.status is null) ";
		String querySql="  select cust.no as custno,cust.name as custname,tract.*,FROM_UNIXTIME(tract.signdate/1000,'%Y-%m-%d %H:%i:%s') as signdate,FROM_UNIXTIME(tract.starttime/1000,'%Y-%m-%d %H:%i:%s') as starttime,FROM_UNIXTIME(tract.endtime/1000,'%Y-%m-%d %H:%i:%s') as endtime from buscontract tract left join busccustomer cust on tract.custid=cust.id where (tract.status!='99' or tract.status is null) ";
		StringBuilder conditionSql=new StringBuilder();
		if(!StringUtil.isEmptyOrNull(key)){
			conditionSql.append(" tract.contractno like '%").append(key).append("%' ");
			conditionSql.append(" or cust.no like '%").append(key).append("%' ");
			conditionSql.append(" or cust.name like '%").append(key).append("%' ");
			conditionSql.append(" or tract.signer like '%").append(key).append("%' ");
		}
		
		countSql=countSql+" and ("+conditionSql.toString()+") ";
		querySql=querySql+" and ("+conditionSql.toString()+") ";
		querySql=querySql+" order by cust.no,tract.starttime ";
		try {
			int count =dbm.executeQueryCount(countSql);
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);

			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql, pm);
			pageFactory = null;
			
			List<ContractModel> list =  dbm.getObjectList(ContractModel.class, sql);
			pm.setList(list);
			logger.info("call ContractDao.quickSearch() success");
			
		} catch (Exception e) {
			logger.info("call ContractDao.quickSearch() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call ContractDao.quickSearch() finish");
		return pm;		
	}	
}
