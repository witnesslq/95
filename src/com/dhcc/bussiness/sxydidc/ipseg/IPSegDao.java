package com.dhcc.bussiness.sxydidc.ipseg;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import com.dhcc.common.database.DBManager;
import com.dhcc.common.system.page.PageFactory;
import com.dhcc.common.util.CommUtil;
import com.dhcc.common.util.DataCenterUtil;
import com.dhcc.modal.sxydidc.ipseg.Rsipseg;
import com.dhcc.modal.system.PageModel;
import com.dhcc.modal.system.Tsdict;
import com.mockrunner.util.common.StringUtil;

public class IPSegDao {
	private static final Logger logger = Logger.getLogger(IPSegDao.class);
	private static DBManager dbm;  

	
	public PageModel queryIPSeg(PageModel pm,String needRoleFilter){
		dbm=new DBManager();
		logger.info("call IPSegDao.queryIPSeg() start");
		StringBuilder querySql=new StringBuilder(" select seg.*,cust.name as customername,dc.name as dcname,corp.corpname as deptname,temp.totalip,temp.freeip,temp.preip,temp.factip from rsipseg seg left join busccustomer cust on seg.customerid=cust.id left join tscorp corp on seg.deptid=corp.id left join rsdatacenter dc on seg.dcid=dc.id left join (");	
		querySql.append(" select ip.ipsegid as ipsegid, count(*) as totalip,sum(case ip.status when '01' then 1 else 0 end) as freeip,sum(case ip.status when '02' then 1 else 0 end) as preip,sum(case ip.status when '03' then 1 else 0 end) as factip  from rsip ip where (ip.status!='99' or ip.status is null) group by ip.ipsegid ");
		querySql.append(")temp on seg.id=temp.ipsegid where (seg.status!='99' or seg.status is null) and (seg.dcid='"+DataCenterUtil.getDCID()+"' ");
		System.out.println(needRoleFilter+"DataCenterUtil.queryAllData()========"+DataCenterUtil.queryAllData());
		
		if(Boolean.parseBoolean(needRoleFilter)&&DataCenterUtil.queryAllData()){
			querySql.append(" or 1=1 ");
		}
		querySql.append(") ");
		querySql.append(" order by seg.customerid,seg.name ");
		try {
			int count =dbm.executeQueryCount(" select count(*) from ("+querySql.toString()+")temp");
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);
			
			/**
			 * 分页sql构造
			 */
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql.toString(), pm);
			pageFactory = null;

			List<IPSegModel> list =  dbm.getObjectList(IPSegModel.class, sql);
			pm.setList(list);
			logger.info("call IPSegDao.queryIPSeg() success");
		} catch (Exception e) {
			logger.info("call IPSegDao.queryIPSeg() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call IPSegDao.queryIPSeg() finish");
		return pm;
	}
	public PageModel queryFreeIPSeg(PageModel pm,String cusid,String ipid,String dcid){
		dbm=new DBManager();
		logger.info("call IPSegDao.queryIPSeg() start");
		StringBuilder countSql=new StringBuilder(" select count(*) from rsipseg seg left join busccustomer cust on seg.customerid=cust.id left join tscorp corp on seg.areaid=corp.id where (seg.status ='01' or (seg.status = '04' and seg.customerid ='"+cusid+"')) and seg.dcid='"+dcid+"' ");
		StringBuilder querySql=new StringBuilder(" select seg.*,cust.name as customername,corp.corpname as areaname from rsipseg seg left join busccustomer cust on seg.customerid=cust.id left join tscorp corp on seg.areaid=corp.id where (seg.status ='01' or (seg.status = '04' and seg.customerid ='"+cusid+"')) and seg.dcid='"+dcid+"' ");	
		
		if(!StringUtil.isEmptyOrNull(ipid)){
			String ipsegidsArr[] = ipid.split(",");
			String ipsegids = "";
			for(int i=0;i<ipsegidsArr.length;i++){
				if(!StringUtil.isEmptyOrNull(ipsegidsArr[i])){
					ipsegids += "'"+ipsegidsArr[i]+"'";
					if(i < ipsegidsArr.length - 1){
						ipsegids += ",";
					}
				}
			}
			if(!StringUtil.isEmptyOrNull(ipsegids)){
				countSql.append(" and seg.id not in (select ipsegid from rsip where id in ("+ipsegids+"))");
				querySql.append(" and seg.id not in (select ipsegid from rsip where id in ("+ipsegids+"))");
			}
		}
		querySql.append(" order by seg.startip,seg.endip ");
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

			List<IPSegModel> list =  dbm.getObjectList(IPSegModel.class, sql);
			pm.setList(list);
			logger.info("call IPSegDao.queryIPSeg() success");
		} catch (Exception e) {
			logger.info("call IPSegDao.queryIPSeg() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call IPSegDao.queryIPSeg() finish");
		return pm;
	}
	
	public PageModel quickFreeIPSegSearch(PageModel pm,String cusid,String username,String ipid){
		dbm=new DBManager();
		logger.info("call IPSegDao.queryIPSeg() start");
		StringBuilder countSql=new StringBuilder(" select count(*) from rsipseg seg left join busccustomer cust on seg.customerid=cust.id left join tscorp corp on seg.areaid=corp.id where (seg.status ='01' or (seg.status = '04' and seg.customerid ='"+cusid+"')) ");
		StringBuilder querySql=new StringBuilder(" select seg.*,cust.name as customername,corp.corpname as areaname from rsipseg seg left join busccustomer cust on seg.customerid=cust.id left join tscorp corp on seg.areaid=corp.id where (seg.status ='01' or (seg.status = '04' and seg.customerid ='"+cusid+"'))  ");	
		if(!StringUtil.isEmptyOrNull(username)){
			querySql.append(" and seg.name like '%"+username+"%'");
			countSql.append(" and seg.name like '%"+username+"%'");
		}
		
		if(!StringUtil.isEmptyOrNull(ipid)){
			String ipsegidsArr[] = ipid.split(",");
			String ipsegids = "";
			for(int i=0;i<ipsegidsArr.length;i++){
				if(!StringUtil.isEmptyOrNull(ipsegidsArr[i])){
					ipsegids += "'"+ipsegidsArr[i]+"'";
					if(i < ipsegidsArr.length - 1){
						ipsegids += ",";
					}
				}
			}
			if(!StringUtil.isEmptyOrNull(ipsegids)){
				countSql.append(" and seg.id not in (select ipsegid from rsip where id in ("+ipsegids+"))");
				querySql.append(" and seg.id not in (select ipsegid from rsip where id in ("+ipsegids+"))");
			}
		}
		
		querySql = querySql.append(" order by seg.startip,seg.endip ASC");
		
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

			List<IPSegModel> list =  dbm.getObjectList(IPSegModel.class, sql);
			pm.setList(list);
			logger.info("call IPSegDao.queryIPSeg() success");
		} catch (Exception e) {
			logger.info("call IPSegDao.queryIPSeg() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call IPSegDao.queryIPSeg() finish");
		return pm;
	}
	
	
	public PageModel queryIPSegByCondition(PageModel pm,IPSegModel iPSegModel,String needRoleFilter){
		dbm=new DBManager();
		logger.info("call IPSegDao.queryIPSegByCondition() start");
		StringBuilder querySql=new StringBuilder(" select seg.*,cust.name as customername,dc.name as dcname,corp.corpname as deptname,temp.totalip,temp.freeip,temp.preip,temp.factip from rsipseg seg left join busccustomer cust on seg.customerid=cust.id left join tscorp corp on seg.deptid=corp.id left join rsdatacenter dc on seg.dcid=dc.id left join (");	
		querySql.append(" select ip.ipsegid as ipsegid, count(*) as totalip,sum(case ip.status when '01' then 1 else 0 end) as freeip,sum(case ip.status when '02' then 1 else 0 end) as preip,sum(case ip.status when '03' then 1 else 0 end) as factip  from rsip ip  where (ip.status!='99' or ip.status is null) group by ip.ipsegid ");
		querySql.append(")temp on seg.id=temp.ipsegid where (seg.status!='99' or seg.status is null) and (seg.dcid='"+DataCenterUtil.getDCID()+"' ");
		if(Boolean.parseBoolean(needRoleFilter)&&DataCenterUtil.queryAllData()){
			querySql.append(" or 1=1 ");
		}
		querySql.append(") ");
		StringBuilder conditionSql=new StringBuilder();
		if(!StringUtil.isEmptyOrNull(iPSegModel.getName())){
			conditionSql.append(" and seg.name like '%").append(iPSegModel.getName()).append("%' ");
		}
		
		if(!StringUtil.isEmptyOrNull(iPSegModel.getStartip())){
			conditionSql.append(" and seg.startip='").append(iPSegModel.getStartip()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(iPSegModel.getEndip())){
			conditionSql.append(" and seg.endip='").append(iPSegModel.getEndip()).append("' ");
		}		
				
		if(!StringUtil.isEmptyOrNull(iPSegModel.getNetmask())){
			conditionSql.append(" and seg.netmask='").append(iPSegModel.getNetmask()).append("' ");
		}	
		
		if(!StringUtil.isEmptyOrNull(iPSegModel.getGatewayip())){
			conditionSql.append(" and seg.gatewayip='").append(iPSegModel.getGatewayip()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(iPSegModel.getDns1())){
			conditionSql.append(" and seg.dns1='").append(iPSegModel.getDns1()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(iPSegModel.getDns2())){
			conditionSql.append(" and seg.dns2='").append(iPSegModel.getDns2()).append("' ");
		}		
		
		if(!StringUtil.isEmptyOrNull(iPSegModel.getStatus())){
			conditionSql.append(" and seg.status='").append(iPSegModel.getStatus()).append("' ");
		}
		
		if(!StringUtil.isEmptyOrNull(iPSegModel.getCustomerid())){
			conditionSql.append(" and seg.customerid='").append(iPSegModel.getCustomerid()).append("' ");
		}		

		if(!StringUtil.isEmptyOrNull(iPSegModel.getAreaid())){
			conditionSql.append(" and seg.areaid='").append(iPSegModel.getAreaid()).append("' ");
		}
		querySql=querySql.append(conditionSql.toString()).append(" order by seg.customerid,seg.name ");
		
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
			
			List<IPSegModel> list =  dbm.getObjectList(IPSegModel.class, sql);
			pm.setList(list);
			logger.info("call IPSegDao.queryIPSegByCondition() success");
			
		} catch (Exception e) {
			logger.info("call IPSegDao.queryIPSegByCondition() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call IPSegDao.queryIPSegByCondition() finish");
		return pm;		
	}
	
	
	public List<IPSegModel> queryIPSegProperty(String name){
		dbm=new DBManager();
		logger.info("call IPSegDao.queryIPSegProperty() start");
		StringBuilder querySql=new StringBuilder(" select seg.* from rsipseg seg where (seg.status!='99' or seg.status is null) ");
		if(!StringUtil.isEmptyOrNull(name)){
			querySql.append(" and seg.name like '%"+name+"%' ");
		}
		List<IPSegModel> list=new ArrayList<IPSegModel>();
		try {
			if(!StringUtil.isEmptyOrNull(name)){
				querySql.append(" and seg.name like '%"+name+"%' ");
			}
			list=dbm.getObjectList(IPSegModel.class, querySql.toString());
			logger.info("call IPSegDao.queryIPSegProperty() success");
		} catch (Exception e) {
			logger.info("call IPSegDao.queryIPSegProperty() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call IPSegDao.queryIPSegProperty() finish");
		return list;
	}
	
	public String deleteIPSegByIds(String ids){
		dbm=new DBManager();
		logger.info("call IPSegDao.deleteIPSegByIds() start");
		String result="03";
		StringBuilder deleteSql=null;
		List<String> list=new ArrayList<String>();  
			if(!StringUtil.isEmptyOrNull(ids)){
				if(ids.indexOf(",")!=-1){//如果是批量删除
					ids=ids.replaceAll(",", "','");
					for(String ipsegId:ids.split(",")){
						int count=dbm.executeQueryCount(" select count(*) from rsip ip where ip.status!='01' and ip.ipsegid='"+ipsegId+"' ");
						if(count==0){
							list.add(new String(" update rsipseg set status='99' where id='"+ipsegId+"' and status='01' "));
							list.add(new String(" update rsip set status='99' where ipsegid='"+ipsegId+"' and status='01' "));
							result="01";							
						}else{
							result="02";
							break;
						}
					}
				}else{
					//网段下所有的IP地址为空闲状态，才可以删除网段
					int count=dbm.executeQueryCount(" select count(*) from rsip ip where ip.status!='01' and ip.ipsegid='"+ids+"' ");
					if(count==0){
						list.add(new String(" update rsipseg set status='99' where id='"+ids+"' and status='01' "));
						list.add(new String(" update rsip set status='99' where ipsegid='"+ids+"' and status='01' "));
						result="01";
					}else{
						result="02";
					}					
					
				}
				try {
					if(result.trim().equals("01")){
						dbm.excuteBatchSql(list); 
						logger.info("call IPSegDao.deleteIPSegByIds() success");
					}					
				} catch (Exception e) {
					result="03";
					logger.info("call IPSegDao.deleteIPSegByIds() fail");
					e.printStackTrace();
				}finally{
					dbm.close();
					dbm=null;
				}
			}
		logger.info("call IPSegDao.deleteIPSegByIds() finish");
		return result;
	}
	
	public boolean mergeIPSegByIds(String ids){
		dbm=new DBManager();
		logger.info("call IPSegDao.mergeIPSeg() start");
		boolean result=false;
		StringBuilder querySql=null; 
			if(!StringUtil.isEmptyOrNull(ids)){
				if(ids.indexOf(",")!=-1){//如果是批量合并
					ids=ids.replaceAll(",", "','");
				}
				try {
					querySql=new StringBuilder(" select seg.* from rsipseg  seg where seg.id in('"+ids+"') order by seg.startip,seg.endip ");
					List<Rsipseg> list = dbm.getObjectList(Rsipseg.class,querySql.toString()); 
					Rsipseg seg=new Rsipseg();
					if(list!=null&&(!list.isEmpty())){
						seg=list.get(0);
						list.remove(seg);
						if(seg!=null){
							for(Rsipseg temp:list){
								if(seg.getStartip().substring(0, seg.getStartip().lastIndexOf(".")).trim().equals(temp.getStartip().substring(0, temp.getStartip().lastIndexOf(".")))
									&&seg.getEndip().substring(0, seg.getEndip().lastIndexOf(".")).trim().equals(temp.getEndip().substring(0, temp.getEndip().lastIndexOf(".")))){
									if(Integer.parseInt(temp.getStartip().substring(temp.getStartip().lastIndexOf(".")+1))-Integer.parseInt(seg.getEndip().substring(seg.getEndip().lastIndexOf(".")+1))==1){
										seg.setEndip(temp.getEndip());
										seg.setName(seg.getStartip()+"~"+seg.getEndip().substring(seg.getEndip().lastIndexOf(".")+1));
										seg.setCount(seg.getCount()+temp.getCount());
									}
								}else{
									result=false;
									break;
								}
							}
						}
					}
					
					List<String> execList=new ArrayList<String>();
					StringBuilder updateIPSegSql=new StringBuilder(" update rsipseg seg set seg.name='"+seg.getName()+"',seg.startip='"+seg.getStartip()+"',seg.endip='"+seg.getEndip()+"',seg.count="+seg.getCount()+" where seg.id='"+seg.getId()+"' ");
					execList.add(updateIPSegSql.toString());
					
					ids=ids.replace(seg.getId(), "");
					StringBuilder deleteIPSegSql=new StringBuilder(" delete from rsipseg where id in('"+ids+"') ");
					execList.add(deleteIPSegSql.toString());
					
					StringBuilder updateIPSql=new StringBuilder(" update rsip ip set ip.ipsegid='"+seg.getId()+"' where ip.ipsegid in('"+ids+"') ");
					execList.add(updateIPSql.toString());
					
					dbm.excuteBatchSql(execList);
					result=true;
					logger.info("call IPSegDao.mergeIPSeg() success");
				} catch (Exception e) {
					result=false;
					logger.info("call IPSegDao.mergeIPSeg() fail");
					e.printStackTrace();
				}finally{
					dbm.close();
					dbm=null;
				}
			}
		logger.info("call IPSegDao.mergeIPSeg() finish");
		return result;		
	}
	
	public boolean splitIPSeg(IPSegModel ipseg,List<IPSegModel> list){
		DBManager manager=new DBManager();
		boolean result=false;
		String id="";
		logger.info("call IPSegDao.splitIPSeg() start");
		manager.addBatch(" update rsipseg seg set seg.name='"+ipseg.getName()+"',seg.startip='"+ipseg.getStartip()+"',seg.endip='"+ipseg.getEndip()+"',seg.count="+ipseg.getCount()+" where seg.id='"+ipseg.getId()+"' ");
		String startIP=ipseg.getStartip().substring(ipseg.getStartip().lastIndexOf(".")+1);
		String endIP=ipseg.getEndip().substring(ipseg.getEndip().lastIndexOf(".")+1);
		String subIP=ipseg.getStartip().substring(0,ipseg.getStartip().lastIndexOf("."));
		manager.addBatch(" update rsip ip set ip.ipsegid='"+ipseg.getId()+"' where substring_index(ip.ipadd,'.',-1)+0 between ('"+startIP+"'+0) and ('"+endIP+"'+0) and substring_index(ip.ipadd,'.',3) like '"+subIP+"%' ");
		try {
			for(IPSegModel ipsegModel:list){
				id=CommUtil.getID();
				IPSegModel copyIPSeg=queryIPSegById(ipseg.getId());
				copyIPSeg.setId(id);
				copyIPSeg.setName(ipsegModel.getName());
				copyIPSeg.setStartip(ipsegModel.getStartip());
				copyIPSeg.setEndip(ipsegModel.getEndip());
				copyIPSeg.setCount(ipsegModel.getCount());
				BeanUtils.copyProperties(ipsegModel, copyIPSeg);
				Rsipseg seg=new Rsipseg();
				BeanUtils.copyProperties(seg, ipsegModel);
				
				startIP=ipsegModel.getStartip().substring(ipsegModel.getStartip().lastIndexOf(".")+1);
				endIP=ipsegModel.getEndip().substring(ipsegModel.getEndip().lastIndexOf(".")+1);
				subIP=ipsegModel.getStartip().substring(0,ipsegModel.getStartip().lastIndexOf("."));
				
				manager.insertObject(seg, "rsipseg");
				manager.addBatch(" update rsip ip set ip.ipsegid='"+id+"' where substring_index(ip.ipadd,'.',-1)+0 between ('"+startIP+"'+0) and ('"+endIP+"'+0) and substring_index(ip.ipadd,'.',3) like '"+subIP+"%' ");
			}
			manager.executeBatch();
			result=true;
			logger.info("call IPSegDao.splitIPSeg() success");
		} catch (Exception e) {
			logger.info("call IPSegDao.splitIPSeg() fail");
			result=false;
			e.printStackTrace();
		}finally{
			manager.close();
			manager=null;
		}
		logger.info("call IPSegDao.splitIPSeg() finish");
		return result;
	}
	
	public IPSegModel queryIPSegById(String id){
		dbm=new DBManager();
		logger.info("call IPSegDao.queryIPSegById() start");
		IPSegModel iPModel=new IPSegModel();
		if(!StringUtil.isEmptyOrNull(id)){
			StringBuilder querySql=new StringBuilder(" select seg.*,cust.name as customername,dc.name as dcname,corp.corpname as deptname from rsipseg seg left join busccustomer cust on seg.customerid=cust.id left join tscorp corp on seg.deptid=corp.id left join rsdatacenter dc on seg.dcid=dc.id where seg.id='"+id+"' ");
			try {
				iPModel=  (IPSegModel) dbm.getObject(IPSegModel.class, querySql.toString());
				logger.info("call IPSegDao.queryIPSegById() success");
			} catch (Exception e) {
				logger.info("call IPSegDao.queryIPSegById() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call IPSegDao.queryIPSegById() finish");
		return iPModel;
	}
	
	public String saveIPSeg(IPSegModel iPSegModel){
		dbm=new DBManager();
		logger.info("call IPSegDao.saveIPSeg() start");
		String id=CommUtil.getID();
		if(iPSegModel!=null){
			iPSegModel.setId(id);
			iPSegModel.setDcid(DataCenterUtil.getDCID());
			try {
				Rsipseg ipseg=new Rsipseg();
				BeanUtils.copyProperties(ipseg, iPSegModel);
				dbm.insertObject(ipseg, "rsipseg");
				addIP(dbm,iPSegModel);
				logger.info("call IPSegDao.saveIPSeg() success");
			}catch (Exception e) {
				logger.info("call IPSegDao.saveIPSeg() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call IPSegDao.saveIPSeg() finish");
		return id;
	}
	
	public boolean updateIPSeg(IPSegModel iPSegModel){
		dbm=new DBManager();
		logger.info("call IPSegDao.updateIPSeg() start");
		boolean result=false;
		if(!StringUtil.isEmptyOrNull(iPSegModel.getId())){
			Rsipseg ipseg=new Rsipseg();
			try {
				BeanUtils.copyProperties(ipseg, iPSegModel);
				result=dbm.updateObject(ipseg); 
				logger.info("call IPSegDao.updateIPSeg() success");
			} catch (IllegalAccessException e) {
				result=false;
				logger.info("call IPSegDao.updateIPSeg() fail");
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				result=false;
				logger.info("call IPSegDao.updateIPSeg() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call IPSegDao.updateIPSeg() finish");
		return result;		
	}
	
	/**
	 * 批量增加IP地址
	 * @param iPSegModel
	 * @return
	 */
	private boolean addIP(DBManager dbm,IPSegModel iPSegModel){
		boolean result=false;	
		logger.info("call IPSegDao.addIP() start");
		String startIP=iPSegModel.getStartip().split("\\.")[3];
		String endIP=iPSegModel.getEndip().split("\\.")[3];
		String subIP=iPSegModel.getStartip().substring(0, iPSegModel.getStartip().lastIndexOf(".")+1);
		int sort=0;
		try {
			for(Integer ip=Integer.parseInt(startIP);ip<=Integer.parseInt(endIP);ip++){
				dbm.addBatch(new String("insert into rsip(id,ipadd,ipsegid,status,customerid,sort,dcid) values('"+CommUtil.getID()+"','"+(subIP+ip)+"','"+iPSegModel.getId()+"','01','"+iPSegModel.getCustomerid()+"',"+sort+",'"+iPSegModel.getDcid()+"')"));
				sort++;
			}			
			dbm.executeBatch();
			logger.info("call IPSegDao.addIP() success");
		} catch (Exception e) {
			result=false;
			logger.info("call IPSegDao.addIP() fail");
		}
		logger.info("call IPSegDao.addIP() finish");	
		return result;
	}
	
	public PageModel quickSearch(PageModel pm,String key,String needRoleFilter){
		dbm=new DBManager();
		logger.info("call IPSegDao.quickSearch() start");
		List<Tsdict> dics=new ArrayList<Tsdict>();
		StringBuilder keys=new StringBuilder("");
		StringBuilder querySql=new StringBuilder(" select seg.*,cust.name as customername,dc.name as dcname,corp.corpname as deptname,temp.totalip,temp.freeip,temp.preip,temp.factip from rsipseg seg left join busccustomer cust on seg.customerid=cust.id left join tscorp corp on seg.deptid=corp.id left join rsdatacenter dc on seg.dcid=dc.id left join (");	
		querySql.append(" select ip.ipsegid as ipsegid, count(*) as totalip,sum(case ip.status when '01' then 1 else 0 end) as freeip,sum(case ip.status when '02' then 1 else 0 end) as preip,sum(case ip.status when '03' then 1 else 0 end) as factip  from rsip ip  where (ip.status!='99' or ip.status is null) group by ip.ipsegid ");
		querySql.append(")temp on seg.id=temp.ipsegid where (seg.status!='99' or seg.status is null) and (seg.dcid='"+DataCenterUtil.getDCID()+"' ");
		if(Boolean.parseBoolean(needRoleFilter)&&DataCenterUtil.queryAllData()){
			querySql.append(" or 1=1 ");
		}
		querySql.append(") ");
		
		StringBuilder conditionSql=new StringBuilder();
		if(!StringUtil.isEmptyOrNull(key)){
			conditionSql.append(" seg.name like '%").append(key).append("%' ");
			conditionSql.append(" or seg.startip like '%").append(key).append("%' ");
			conditionSql.append(" or seg.endip like '%").append(key).append("%' ");
			conditionSql.append(" or seg.netmask like '%").append(key).append("%' ");
			conditionSql.append(" or seg.gatewayip like '%").append(key).append("%' ");
			for(Tsdict dic:dics){
				if(dics.lastIndexOf(dic)!=dics.size()-1){
					keys.append("'").append(dic.getDkey()).append("',");
				}else{
					keys.append("'").append(dic.getDkey()).append("'");
				}
			}

			if(!StringUtil.isEmptyOrNull(keys.toString())){
				conditionSql.append(" or seg.status in(").append(keys.toString()).append(") ");
			}			
		}
		querySql=querySql.append(" and("+conditionSql.toString()+") order by seg.customerid,seg.name ");
		try {
			int count =dbm.executeQueryCount(" select count(*) from("+querySql.toString()+")tempa");
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);
			
			/**
			 * 分页sql构造
			 */
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querySql.toString(), pm);
			pageFactory = null;
			
			List<IPSegModel> list =  dbm.getObjectList(IPSegModel.class, sql);
			pm.setList(list);
			logger.info("call IPSegDao.quickSearch() success");
			
		} catch (Exception e) {
			logger.info("call IPSegDao.quickSearch() fail");
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm=null;
		}
		logger.info("call IPSegDao.quickSearch() finish");
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
