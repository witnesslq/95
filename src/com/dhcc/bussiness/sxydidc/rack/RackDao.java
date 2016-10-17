package com.dhcc.bussiness.sxydidc.rack;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import com.dhcc.common.database.DBManager;
import com.dhcc.common.system.page.PageFactory;
import com.dhcc.common.util.CommUtil;
import com.dhcc.common.util.DataCenterUtil;
import com.dhcc.modal.sxydidc.rack.Rsrack;
import com.dhcc.modal.system.PageModel;
import com.dhcc.modal.system.Tsdict;
import com.mockrunner.util.common.StringUtil;

public class RackDao {
	private static final Logger logger = Logger.getLogger(RackDao.class);
	private static DBManager dbm;  

	
	public PageModel queryRack(PageModel pm,String needRoleFilter){
		dbm=new DBManager();
		logger.info("call RackDao.queryRack() start");
		StringBuilder querySql=null;
		if(Boolean.parseBoolean(needRoleFilter)&&DataCenterUtil.queryAllData()){
			querySql=new StringBuilder(" select rack.*,room.roomname as roomname,temp.name as customername,dc.name as dcname,temp.totalu as totalU,temp.freeu as freeU,temp.disrentu as disRentU,temp.whlrentu as whlRentU,temp.preu as preU from rsrack rack left join rsroom room on rack.roomid=room.id left join rsdatacenter dc on room.dcid=dc.id left join (");	
			querySql.append(" select u.rackid as rackid,group_concat(distinct(cust.name)) as name,count(*) as totalu,sum(case u.status when '01' then 1 else 0 end) as freeu,sum(case u.status when '02' then 1 else 0 end) as disrentu,sum(case u.status when '03' then 1 else 0 end) as whlrentu,sum(case u.status when '04' then 1 else 0 end) as preu from rsuseat u left join busccustomer cust on cust.id=u.customerid where (u.status!='99' or u.status is null) group by u.rackid " );
			querySql.append(")temp on rack.id=temp.rackid ");
			querySql.append(" where (rack.status!='99' or rack.status is null) and rack.typeid!='05' and rack.roomid is not null ");
		}else{
			querySql=new StringBuilder(" select rack.*,room.roomname as roomname,temp.name as customername,dc.name as dcname,temp.totalu as totalU,temp.freeu as freeU,temp.disrentu as disRentU,temp.whlrentu as whlRentU,temp.preu as preU from rsrack rack left join rsroom room on rack.roomid=room.id left join rsdatacenter dc on rack.dcid=dc.id left join (");	
			querySql.append(" select u.rackid as rackid,group_concat(distinct(cust.name)) as name,count(*) as totalu,sum(case u.status when '01' then 1 else 0 end) as freeu,sum(case u.status when '02' then 1 else 0 end) as disrentu,sum(case u.status when '03' then 1 else 0 end) as whlrentu,sum(case u.status when '04' then 1 else 0 end) as preu from rsuseat u left join busccustomer cust on cust.id=u.customerid where (u.status!='99' or u.status is null) group by u.rackid " );
			querySql.append(")temp on rack.id=temp.rackid ");
			querySql.append(" where (rack.status!='99' or rack.status is null) and rack.typeid!='05' and rack.roomid is not null and room.dcid='"+DataCenterUtil.getDCID()+"' ");
						
		}
		querySql.append(" order by room.roomcode,rack.name ");
		try {
			int count =dbm.executeQueryCount(" select count(*) from ("+querySql.toString()+")tempa ");
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);
			
			/**
			 * 分页sql构造
			 */
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql.toString(), pm);
			pageFactory = null;

			List<RackModel> list =  dbm.getObjectList(RackModel.class, sql);
			pm.setList(list);
			logger.info("call RackDao.queryRack() success");
		} catch (Exception e) {
			logger.info("call RackDao.queryRack() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call RackDao.queryRack() finish");
		return pm;
	}
	public PageModel queryFreeRack(PageModel pm,String cusid){
		dbm=new DBManager();
		logger.info("call RackDao.queryRack() start");
		StringBuilder countSql=new StringBuilder(" select count(*) from rsrack rack where rack.status ='01' or (rack.status = '02' and rack.customerid ='"+cusid+"') ");
		StringBuilder querySql=new StringBuilder(" select rack.*,(select room.roomname from rsroom room where rack.roomid=room.id) as roomname,(select cust.name from busccustomer cust where rack.customerid=cust.id) as customername from rsrack rack where rack.status ='01' or (rack.status = '02' and rack.customerid ='"+cusid+"') ");	
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

			List<RackModel> list =  dbm.getObjectList(RackModel.class, sql);
			pm.setList(list);
			logger.info("call RackDao.queryRack() success");
		} catch (Exception e) {
			logger.info("call RackDao.queryRack() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call RackDao.queryRack() finish");
		return pm;
	}
	
	public List<RackModel> queryRackProperty(String rackName){
		dbm=new DBManager();
		logger.info("call RscustomerDao.queryRackProperty() start");
		StringBuilder querySql=new StringBuilder(" select rack.* from rsrack rack where rack.status!='99' or rack.status is null");
		List<RackModel> list=new ArrayList<RackModel>();
		try {
			if(!StringUtil.isEmptyOrNull(rackName)){
				querySql.append(" and rack.name like '%"+rackName+"%' ");
			}
			list=dbm.getObjectList(RackModel.class, querySql.toString());
			logger.info("call RscustomerDao.queryRackProperty() success");
		} catch (Exception e) {
			logger.info("call RscustomerDao.queryRackProperty() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call RscustomerDao.queryRackProperty() finish");
		return list;
	}
	
	public PageModel queryRackByCondition(PageModel pm,RackModel rackModel,String needRoleFilter){
		dbm=new DBManager();
		logger.info("call RackDao.queryRackByCondition() start");
		StringBuilder querySql=null;
		if(Boolean.parseBoolean(needRoleFilter)&&DataCenterUtil.queryAllData()){
			querySql=new StringBuilder(" select rack.*,room.roomname as roomname,cust.name as customername,dc.name as dcname,temp.totalu as totalu,temp.freeu as freeu,temp.disrentu as disrentu,temp.whlrentu as whlrentu,temp.preu as preu from rsrack rack left join rsroom room on rack.roomid=room.id left join busccustomer cust on rack.customerid=cust.id left join rsdatacenter dc on rack.dcid=dc.id left join (");	
			querySql.append(" select u.rackid as rackid,group_concat(distinct(cust.name)) as name,count(*) as totalu,sum(case u.status when '01' then 1 else 0 end) as freeu,sum(case u.status when '02' then 1 else 0 end) as disrentu,sum(case u.status when '03' then 1 else 0 end) as whlrentu,sum(case u.status when '04' then 1 else 0 end) as preu from rsuseat u left join busccustomer cust on cust.id=u.customerid where (u.status!='99' or u.status is null) group by u.rackid " );
			querySql.append(")temp on rack.id=temp.rackid ");
			querySql.append(" where (rack.status!='99' or rack.status is null) and rack.roomid is not null ");
		}else{
			querySql=new StringBuilder(" select rack.*,room.roomname as roomname,cust.name as customername,dc.name as dcname,temp.totalu as totalu,temp.freeu as freeu,temp.disrentu as disrentu,temp.whlrentu as whlrentu,temp.preu as preu from rsrack rack left join rsroom room on rack.roomid=room.id left join busccustomer cust on rack.customerid=cust.id left join rsdatacenter dc on rack.dcid=dc.id left join (");	
			querySql.append(" select u.rackid as rackid,group_concat(distinct(cust.name)) as name,count(*) as totalu,sum(case u.status when '01' then 1 else 0 end) as freeu,sum(case u.status when '02' then 1 else 0 end) as disrentu,sum(case u.status when '03' then 1 else 0 end) as whlrentu,sum(case u.status when '04' then 1 else 0 end) as preu from rsuseat u left join busccustomer cust on cust.id=u.customerid where (u.status!='99' or u.status is null) group by u.rackid " );
			querySql.append(")temp on rack.id=temp.rackid ");
			querySql.append(" where (rack.status!='99' or rack.status is null) and rack.roomid is not null and room.dcid='"+DataCenterUtil.getDCID()+"' ");
		}
		
		StringBuilder conditionSql=new StringBuilder();
		
		if(!StringUtil.isEmptyOrNull(rackModel.getName())){
			conditionSql.append(" and rack.name like '%").append(rackModel.getName()).append("%' ");
		}
				
		if(!StringUtil.isEmptyOrNull(rackModel.getCode())){
			conditionSql.append(" and rack.code like '%").append(rackModel.getCode()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(rackModel.getTypeid())){
			conditionSql.append(" and rack.typeid='").append(rackModel.getTypeid()).append("' ");
		}		
				
		if(rackModel.getUcount()!=null&&rackModel.getUcount()!=0){
			conditionSql.append(" and rack.ucount=").append(rackModel.getUcount()).append(" ");
		}
		
		if(!StringUtil.isEmptyOrNull(rackModel.getRoomid())){
			conditionSql.append(" and rack.roomid='").append(rackModel.getRoomid()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(rackModel.getCustomerid())){
			conditionSql.append(" and rack.customerid='").append(rackModel.getCustomerid()).append("' ");
		}
		
		if(!rackModel.getNeedFilter()){
			if(!StringUtil.isEmptyOrNull(rackModel.getStatus())){
				conditionSql.append(" and rack.status='").append(rackModel.getStatus()).append("' ");
			}			
		}else{
			conditionSql.append(" and rack.status!='03' ");
		}
		querySql=querySql.append(conditionSql.toString()).append(" order by room.roomcode,rack.name ");
		
		try {
			int count =dbm.executeQueryCount(" select count(*) from ("+querySql.toString()+")tempa ");
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);
			
			/**
			 * 分页sql构造
			 */
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql.toString(), pm);
			pageFactory = null;
			
			List<RackModel> list =  dbm.getObjectList(RackModel.class, sql);
			pm.setList(list);
			logger.info("call RackDao.queryRackByCondition() success");
			
		} catch (Exception e) {
			logger.info("call RackDao.queryRackByCondition() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call RackDao.queryRackByCondition() finish");
		return pm;		
	}
	
	public PageModel queryFreeRackByCondition(PageModel pm,RackModel rackModel){
		dbm=new DBManager();
		logger.info("call RackDao.queryRackByCondition() start");
		String countSql=" select count(*) from rsrack rack left join rsroom room on rack.roomid=room.id left join busccustomer cust on rack.customerid=cust.id where rack.status='01' ";
		String querySql=" select rack.*,room.roomname as roomname,cust.name as customername from rsrack rack left join rsroom room on rack.roomid=room.id left join busccustomer cust on rack.customerid=cust.id where rack.status='01' ";
		StringBuilder conditionSql=new StringBuilder();
		
		if(!StringUtil.isEmptyOrNull(rackModel.getName())){
			conditionSql.append(" and rack.name like '%").append(rackModel.getName()).append("%' ");
		}
				
		if(!StringUtil.isEmptyOrNull(rackModel.getCode())){
			conditionSql.append(" and rack.code like '%").append(rackModel.getCode()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(rackModel.getTypeid())){
			conditionSql.append(" and rack.typeid='").append(rackModel.getTypeid()).append("' ");
		}else{
			conditionSql.append(" and rack.typeid!='05' ");
		}
				
		if(rackModel.getUcount()!=null&&rackModel.getUcount()!=0){
			conditionSql.append(" and rack.ucount=").append(rackModel.getUcount()).append(" ");
		}
		
		if(!StringUtil.isEmptyOrNull(rackModel.getRoomid())){
			conditionSql.append(" and rack.roomid='").append(rackModel.getRoomid()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(rackModel.getCustomerid())){
			conditionSql.append(" and rack.customerid='").append(rackModel.getCustomerid()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(rackModel.getStatus())){
			conditionSql.append(" and rack.status='").append(rackModel.getStatus()).append("' ");
		}

		countSql=countSql+conditionSql.toString();
		querySql=querySql+conditionSql.toString();
		
		try {
			int count =dbm.executeQueryCount(countSql);
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);
			
			/**
			 * 分页sql构造
			 */
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql, pm);
			pageFactory = null;
			
			List<RackModel> list =  dbm.getObjectList(RackModel.class, sql);
			pm.setList(list);
			logger.info("call RackDao.queryRackByCondition() success");
			
		} catch (Exception e) {
			logger.info("call RackDao.queryRackByCondition() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call RackDao.queryRackByCondition() finish");
		return pm;		
	}
	
	public String deleteRackByIds(String ids){
		dbm=new DBManager();
		logger.info("call RackDao.deleteRackByIds() start");
		String result="03";
		List<String> list=new ArrayList<String>();  
			if(!StringUtil.isEmptyOrNull(ids)){
				if(ids.indexOf(",")!=-1){//如果是批量删除
					//机架所有U位为空闲状态且机架本身为空闲状态才可以删除；
					for(String rackid:ids.split(",")){
						int count=dbm.executeQueryCount(" select count(*) from rsuseat u where u.status in('02','03','04') and u.rackid='"+rackid+"' ");
						if(count==0){
							list.add(new String(" update rsrack set status='99' where id in('"+rackid+"') and status='01' "));
							list.add(new String(" update rsuseat set status='99' where rackid='"+rackid+"' "));
							result="01";
						}else{
							result="02";
							break;
						}
					}
				}else{
					//机架所有U位为空闲状态且机架本身为空闲状态才可以删除；
					int count=dbm.executeQueryCount(" select count(*) from rsuseat u where u.status in('02','03','04') and u.rackid='"+ids+"' ");
					if(count==0){
						list.add(" update rsrack set status='99' where id in('"+ids+"') and status='01' ");
						list.add(" update rsuseat set status='99' where rackid='"+ids+"' ");
						result="01";
					}else{
						result="02";
					}
				}
				
				try {
					if(result.trim().equals("01")){
						dbm.excuteBatchSql(list); 
						logger.info("call RackDao.deleteRackByIds() success");
					}
				} catch (Exception e) {
					result="03";
					logger.info("call RackDao.deleteRackByIds() fail");
					e.printStackTrace();
				}finally{
					dbm.close();
					dbm=null;
				}
			}
		logger.info("call RackDao.deleteRackByIds() finish");
		return result;
	}
	
	public RackModel queryRackById(String id){
		dbm=new DBManager();
		logger.info("call RackDao.queryRackById() start");
		RackModel rackModel=new RackModel();
		if(!StringUtil.isEmptyOrNull(id)){
			StringBuilder querySql=new StringBuilder(" select rack.*,room.roomname as roomname,dc.name as dcname,temp.name as customername from rsrack rack left join rsroom room on rack.roomid=room.id left join rsdatacenter dc on rack.dcid=dc.id left join (select u.rackid as rackid,group_concat(distinct(cust.name)) as name from rsuseat u left join busccustomer cust on cust.id=u.customerid group by u.rackid having u.rackid='"+id+"')temp on rack.id=temp.rackid where rack.id='"+id+"' ");
			try {
				rackModel=  (RackModel) dbm.getObject(RackModel.class, querySql.toString());
				logger.info("call RackDao.queryRackById() success");
			} catch (Exception e) {
				logger.info("call RackDao.queryRackById() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call RackDao.queryRackById() finish");
		return rackModel;
	}
	
	public String saveRack(RackModel rackModel){
		dbm=new DBManager();
		logger.info("call RackDao.saveRack() start");
		String id=CommUtil.getID();
		if(rackModel!=null){
			Rsrack rack=new Rsrack();
			rackModel.setId(id);
			try {
				BeanUtils.copyProperties(rack, rackModel);
				dbm.insertObject(rack, "rsrack");
				addUSeat(dbm,rackModel);
				logger.info("call RackDao.saveRack() success");
			} catch (IllegalAccessException e) {
				logger.info("call RackDao.saveRack() fail");
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				logger.info("call RackDao.saveRack() fail");
				e.printStackTrace();
			}
		}
		logger.info("call RackDao.saveRack() finish");
		return id;
	}
	
	public boolean updateRack(RackModel rackModel){
		dbm=new DBManager();
		logger.info("call RackDao.updateRack() start");
		boolean result=false;
		if(!StringUtil.isEmptyOrNull(rackModel.getId())){
			Rsrack rack=new Rsrack();
			try {
				BeanUtils.copyProperties(rack, rackModel);
				result=dbm.updateObject(rack); 
				logger.info("call RackDao.updateRack() success");
			} catch (IllegalAccessException e) {
				result=false;
				logger.info("call RackDao.updateRack() fail");
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				result=false;
				logger.info("call RackDao.updateRack() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call RackDao.updateRack() finish");
		return result;		
	}
	
	/**
	 * 批量增加U位
	 * @param iPSegModel
	 * @return
	 */
	private boolean addUSeat(DBManager dbm,RackModel rackModel){
		boolean result=false;	
		logger.info("call IPSegDao.addUSeat() start");
		int ucount=rackModel.getUcount();
		String id;
		try {
			for(int i=0;i<ucount;i++){
				id=CommUtil.getID();
				dbm.addBatch(new String(" insert into rsuseat(id,no,rackid,customerid,status) values('"+id+"','"+i+"','"+rackModel.getId()+"','"+rackModel.getCustomerid()+"','01') "));
			}
			dbm.executeBatch();
			logger.info("call IPSegDao.addUSeat() success");
		} catch (Exception e) {
			result=false;
			logger.info("call IPSegDao.addUSeat() fail");
		}finally{			
			dbm.close();
			dbm=null;
		}
		logger.info("call IPSegDao.addUSeat() finish");	
		return result;
	}

	public PageModel quickSearch(PageModel pm,String key,String needRoleFilter){
		dbm=new DBManager();
		logger.info("call RackDao.quickSearch() start");
		List<Tsdict> dics=new ArrayList<Tsdict>();
		StringBuilder keys=new StringBuilder("");
		
		StringBuilder querySql=null;
		if(Boolean.parseBoolean(needRoleFilter)&&DataCenterUtil.queryAllData()){
			querySql=new StringBuilder(" select rack.*,room.roomname as roomname,cust.name as customername,dc.name as dcname,temp.totalu as totalu,temp.freeu as freeu,temp.disrentu as disrentu,temp.whlrentu as whlrentu,temp.preu as preu from rsrack rack left join rsroom room on rack.roomid=room.id left join rsdatacenter dc on rack.dcid=dc.id left join busccustomer cust on rack.customerid=cust.id left join (");	
			querySql.append(" select u.rackid as rackid,group_concat(distinct(cust.name)) as name,count(*) as totalu,sum(case u.status when '01' then 1 else 0 end) as freeu,sum(case u.status when '02' then 1 else 0 end) as disrentu,sum(case u.status when '03' then 1 else 0 end) as whlrentu,sum(case u.status when '04' then 1 else 0 end) as preu from rsuseat u left join busccustomer cust on cust.id=u.customerid where (u.status!='99' or u.status is null) group by u.rackid " );
			querySql.append(")temp on rack.id=temp.rackid ");
			querySql.append(" where (rack.status!='99' or rack.status is null) and rack.typeid!='05' and rack.roomid is not null ");

		}else{
			querySql=new StringBuilder(" select rack.*,room.roomname as roomname,cust.name as customername,dc.name as dcname,temp.totalu as totalu,temp.freeu as freeu,temp.disrentu as disrentu,temp.whlrentu as whlrentu,temp.preu as preu from rsrack rack left join rsroom room on rack.roomid=room.id left join rsdatacenter dc on rack.dcid=dc.id left join busccustomer cust on rack.customerid=cust.id left join (");	
			querySql.append(" select u.rackid as rackid,group_concat(distinct(cust.name)) as name,count(*) as totalu,sum(case u.status when '01' then 1 else 0 end) as freeu,sum(case u.status when '02' then 1 else 0 end) as disrentu,sum(case u.status when '03' then 1 else 0 end) as whlrentu,sum(case u.status when '04' then 1 else 0 end) as preu from rsuseat u left join busccustomer cust on cust.id=u.customerid where (u.status!='99' or u.status is null) group by u.rackid " );
			querySql.append(")temp on rack.id=temp.rackid ");
			querySql.append(" where (rack.status!='99' or rack.status is null) and rack.typeid!='05' and rack.roomid is not null and room.dcid='"+DataCenterUtil.getDCID()+"' ");
		}
		
		StringBuilder conditionSql=new StringBuilder();
		
		if(!StringUtil.isEmptyOrNull(key)){
			conditionSql.append(" rack.name like '%").append(key).append("%' ");
			conditionSql.append(" or rack.code like '%").append(key).append("%' ");
			conditionSql.append(" or rack.typeid like '%").append(key).append("%' ");
			
			dics=this.queryDicIds(dbm, key);
			for(Tsdict dic:dics){
				if(dics.lastIndexOf(dic)!=dics.size()-1){
					keys.append("'").append(dic.getDkey()).append("',");
				}else{
					keys.append("'").append(dic.getDkey()).append("'");
				}
			}

			if(!StringUtil.isEmptyOrNull(keys.toString())){
				conditionSql.append(" or rack.status in(").append(keys.toString()).append(") ");
			}
		}
		
		querySql=querySql.append(" and ("+conditionSql.toString()+") ").append(" order by room.roomcode,rack.name ");
		
		try {
			int count =dbm.executeQueryCount(" select count(*) from ("+querySql.toString()+")tempa ");
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);
			
			/**
			 * 分页sql构造
			 */
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql.toString(), pm);
			pageFactory = null;
			
			List<RackModel> list =  dbm.getObjectList(RackModel.class, sql);
			pm.setList(list);
			logger.info("call RackDao.quickSearch() success");
			
		} catch (Exception e) {
			logger.info("call RackDao.quickSearch() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call RackDao.quickSearch() finish");
		return pm;		
	}
	
	private List<Tsdict> queryDicIds(DBManager dbm,String dvalue){
		List<Tsdict> list=new ArrayList<Tsdict>();
		try {
			list = dbm.getObjectList(Tsdict.class, " select dkey from tsdict where dvalue like '%"+dvalue+"%' ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
