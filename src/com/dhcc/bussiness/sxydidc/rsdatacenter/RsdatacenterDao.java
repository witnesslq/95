package com.dhcc.bussiness.sxydidc.rsdatacenter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.dhcc.common.database.DBManager;
import com.dhcc.common.system.page.PageFactory;
import com.dhcc.common.util.CommUtil;
import com.dhcc.modal.sxydidc.rsdatacenter.Rsdatacenter;
import com.dhcc.modal.system.PageModel;
import com.dhcc.modal.system.Tsdict;
import com.mockrunner.util.common.StringUtil;

	public class RsdatacenterDao {
		private static final Logger logger = Logger.getLogger(RsdatacenterDao.class);
		private static DBManager dbm;  
		
		public String saveRsdatacenter(RsdatacenterModel rsdata){
			this.init();
			logger.info("call saveRsdatacenter() start");
			String result="fail";
			String id=CommUtil.getID();
			if(rsdata !=null){
				rsdata.setId(id);
				try {
					Rsdatacenter rsdatamodel=new Rsdatacenter();
					BeanUtils.copyProperties(rsdatamodel, rsdata);
					dbm.insertObject(rsdatamodel, "rsdatacenter");
					result="success";
					logger.info("call saveRsdatacenter() success");
				} catch (Exception e) {
					result="fail";
					logger.info("call saveRsdatacenter() fail");
					e.printStackTrace();
				}finally{
					this.destory();
				}
			}
			logger.info("call savecustMail() finish");
			return result;
		}
		
		public RsdatacenterModel queryRsdatacenterById(String id){
			this.init();
			logger.info("call queryRsdatacenterById() start");
			RsdatacenterModel rsdatacenterModel=new RsdatacenterModel();
			if(!StringUtil.isEmptyOrNull(id)){
				StringBuilder sql=new StringBuilder("select t.id,t.companyId,t.regionId,t.name,t.address,(select corpname from tscorp where id=t.companyId ) as companyname,(select areaname from tsarea where id=t.regionId) as regionname,t.remark from rsdatacenter t");
				sql.append(" where t.id='"+id+"' ");				
					try {
						rsdatacenterModel=(RsdatacenterModel) dbm.getObject(RsdatacenterModel.class, sql.toString()); 
						logger.info("call queryRsdatacenterById() success");
					} catch (Exception e) {
						logger.info("call queryRsdatacenterById() fail");
						e.printStackTrace();
					}finally{
						this.destory();
					}
				
			}
			logger.info("call queryRsdatacenterById() finish");
			return rsdatacenterModel;
		}
		
		public RsdatacenterModel queryRsdatacenterByUserId(String userid){
			this.init();
			logger.info("call queryRsdatacenterByUserId() start");
			RsdatacenterModel rsdatacenterModel=new RsdatacenterModel();
			if(!StringUtil.isEmptyOrNull(userid)){
				String sql = "select t.id,t.companyId,t.regionId,t.name,t.address,(select corpname from tscorp where id=t.companyId ) as companyname,(select areaname from tsarea where id=t.regionId) as regionname,t.remark from rsdatacenter t " +
						"LEFT JOIN tsuser m on m.dcid= t.id WHERE m.id='"+userid+"'";
					try {
						rsdatacenterModel=(RsdatacenterModel) dbm.getObject(RsdatacenterModel.class, sql); 
						logger.info("call queryRsdatacenterByUserId() success");
					} catch (Exception e) {
						logger.info("call queryRsdatacenterByUserId() fail");
						e.printStackTrace();
					}finally{
						this.destory();
					}
				
			}
			logger.info("call queryRsdatacenterByUserId() finish");
			return rsdatacenterModel;
		}
		
		
		public PageModel queryRsdatacenter(PageModel pm){
			this.init();
			logger.info("call queryRsdatacenter() start");
			StringBuilder querySql=new StringBuilder("select t.id,t.companyId,t.regionId,t.name,t.address,(select corpname from tscorp where id=t.companyId ) as companyname,(select areaname from tsarea where id=t.regionId) as regionname,t.remark from rsdatacenter t");
			
			try {
				int count = dbm.executeQueryCount(" select count(*) from ("+querySql.toString()+")tempa ");
				int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
				pm.setTotalPage(total);
				pm.setTotalRecord(count);

				PageFactory pageFactory = new PageFactory();
				String sql = pageFactory.createPageSQL(querySql.toString(), pm);
				pageFactory = null;
				List<RsdatacenterModel> list =  dbm.getObjectList(RsdatacenterModel.class, sql);
				pm.setList(list);
				logger.info("call queryRsdatacenter() success");
			} catch (Exception e) {
				logger.info("call queryRsdatacenter() fail");
				e.printStackTrace();
			}finally{
				this.destory();
			}
			logger.info("call queryRsdatacenter() finish");
			return pm;
		}
		
		public PageModel quickSearchRsdatacenter(PageModel pm,String key){
			this.init();
			logger.info("call quickSearchRsdatacenter() start");
			List<Tsdict> dics=new ArrayList<Tsdict>();
			StringBuilder keys=new StringBuilder("");
			StringBuilder querySql=new StringBuilder("select t.id,t.companyId,t.regionId,t.name,t.address,(select corpname from tscorp where id=t.companyId ) as companyname,(select areaname from tsarea where id=t.regionId) as regionname,t.remark from rsdatacenter t");
			
			StringBuilder conditionSql=new StringBuilder();
			if(!StringUtil.isEmptyOrNull(key)){
				conditionSql.append(" where t.name like '%").append(key).append("%' ");
				conditionSql.append(" or t.address like '%").append(key).append("%' ");
				conditionSql.append(" or (select corpname from tscorp where id=t.companyId ) like '%").append(key).append("%' ");
				conditionSql.append(" or (select areaname from tsarea where id=t.regionId ) like '%").append(key).append("%' ");
			}
			querySql=querySql.append(" "+conditionSql.toString()+" ").append("  order by t.name ");
			try {
				int count = dbm.executeQueryCount(" select count(*) from ("+querySql.toString()+")tempa");
				int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
				pm.setTotalPage(total);
				pm.setTotalRecord(count);

				PageFactory pageFactory = new PageFactory();
				String sql = pageFactory.createPageSQL(querySql.toString(), pm);
				pageFactory = null;
				List<RsdatacenterModel> list =  dbm.getObjectList(RsdatacenterModel.class, sql);
				logger.info(list.size());
				pm.setList(list);
				logger.info("call quickSearchRsdatacenter() success");
			} catch (Exception e) {
				logger.info("call quickSearchRsdatacenter() fail");
				e.printStackTrace();
			}finally{
				this.destory();
			}
			logger.info("call quickSearchRsdatacenter() finish");
			return pm;
		}
		
		
		public boolean deleteRsdatacenterByIds(String ids){
			dbm=new DBManager();
			logger.info("call deleteRsdatacenterByIds() start");
			boolean result=false;
			StringBuilder deleteSql=null;
			List<String> list=new ArrayList<String>();  
				if(!StringUtil.isEmptyOrNull(ids)){
					if(ids.indexOf(",")!=-1){//如果是批量删除
						ids=ids.replaceAll(",", "','");
					}
					deleteSql=new StringBuilder(" delete  from rsdatacenter  where id in('"+ids+"')  ");
					list.add(deleteSql.toString());
					try {
						result=dbm.excuteBatchSql(list); 
						logger.info("call deleteRsdatacenterByIds() success");
					} catch (Exception e) {
						logger.info("call deleteRsdatacenterByIds() fail");
						e.printStackTrace();
					}finally{
						dbm.close();
						dbm=null;
					}
				}
			logger.info("call deleteRsdatacenterByIds() finish");
			return result;
		}
		
		public boolean updateRsdatacenter(RsdatacenterModel rsdata){
			this.init();
			logger.info("call updateRsdatacenter() start");
			boolean result=false;
				Rsdatacenter rsdatamodel=new Rsdatacenter();
				List<String> list=new ArrayList<String>();  
				StringBuilder querySql=new StringBuilder("update rsdatacenter set name='"+rsdata.getName()+"' , address='"+rsdata.getAddress()+"' , companyId='"+rsdata.getCompanyId()+"' , regionId='"+rsdata.getRegionId()+"' where id='"+rsdata.getId()+"'  ");
				list.add(querySql.toString());
				try {
					result=dbm.excuteBatchSql(list); 
					logger.info("call updateRsdatacenter() success");
				} catch (Exception e) {
					logger.info("call updateRsdatacenter() fail");
					e.printStackTrace();
				}finally{
					this.destory();
				}
			logger.info("call updateRsdatacenter() finish");
			return result;
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
		
		public void init(){
			logger.info("call SectmailDao.init() start");
			dbm=new DBManager();
			logger.info("call SectmailDao.init() finish");
		}
		
		public void destory(){
			logger.info("call SectmailDao.destory() start");
			dbm.close();
			logger.info("call SectmailDao.destory() finish");
		}
		
		
}
