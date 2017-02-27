package com.dhcc.common.system.components;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.dhcc.common.database.DBManager;
import com.dhcc.common.system.corp.CorpDao;
import com.dhcc.common.system.page.PageFactory;
import com.dhcc.common.system.systemLog.SaveLog;
import com.dhcc.common.util.CommDao;
import com.dhcc.modal.system.PageModel;
import com.dhcc.modal.system.TreeModal;
import com.dhcc.modal.system.Tsarea;
import com.dhcc.modal.system.Tscomponent;
import com.dhcc.modal.system.Tsconfig;
import com.dhcc.modal.system.Tscorp;
import com.opensymphony.xwork2.ActionContext;

public class ComponentDao {

	public ComponentDao() {
		// TODO Auto-generated constructor stub
	}
	
	private static final Logger logger = Logger.getLogger(CorpDao.class);
	
	 /**
    * @param sname
    * @param ly
    * @return
    */
	public PageModel componentQueryList(PageModel pm,String sortname,String sortorder){
		DBManager dbm=new DBManager();
		try {
			String querysql = "select * from tscomponent ";
			querysql += " order by "+sortname+" "+sortorder+" ";
			String countsql = "select count(*) from (" + querysql + ") t";
			int count =dbm.executeQueryCount(countsql);
			int total = count % pm.getPerPage() == 0 ? count /pm.getPerPage() : count / pm.getPerPage() + 1;
			pm.setTotalPage(total);
			pm.setTotalRecord(count);
			/**
			 * 分页sql构造
			 */
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querysql, pm);
			pageFactory = null;
			List<Tscomponent> list = new ArrayList<Tscomponent>();
		    list = dbm.getObjectList(Tscomponent.class, sql);
			pm.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询公司列表时候出错！"+e.getMessage());
		}finally{
			dbm.close();
		}
		
		return pm;
		
	}
	
	
	
	 
   /**
    * 添加一个新的组件
    * @param menu
    * @return 执行结果
    */
   public  boolean  componentAdd(Tscomponent model){
   	DBManager dbm=new DBManager();
   	boolean b=true;
		try {
			b = dbm.insertObject(model, "tscomponent");
			SaveLog saveLog = new SaveLog();
			saveLog.saveLog("组件信息添加", "添加一条ID为"+model.getId()+"的记录！");
			saveLog = null;
		} catch (Exception e) {
			logger.error(e);
		}finally{
   	  dbm.close();
		}
   	return b;
   }
	
   
   /**
    * @param ids 组件id的集合
    * 组件信息删除
    * @return
    */
	public String componentDel(String[] ids){
		String sql = "";
		DBManager dbm = new DBManager();
		SaveLog saveLog = new SaveLog();
		try {
			for (String id : ids) {
				sql = "delete from tscomponent where id='" + id + "'";
				dbm.addBatch(sql);
				sql = "delete from tslrolecomponent where componentid ='"+id+"'";
				dbm.addBatch(sql);
				saveLog.saveLog("组件信息删除", "删除了一条ID为"+id+"的单位记录！");
			}
			dbm.executeBatch();
		} catch (Exception e) {
			logger.error("公司信息删除--失败",e);
		} finally {
			dbm.close();
		}
		return "success";
	}
	
	
	/**
    * 根据id得到单个信息
    * @param sql 查询的sql语句
    * @return 查询到的信息模型
    */
   public Tscomponent componentQueryById(String id){
   	DBManager dbm = new DBManager();
   	String sql = "select * from tscomponent where id='"+id+"'";
   	Tscomponent model = null;
		try {
			model = (Tscomponent) dbm.getObject(Tscomponent.class, sql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
   	   dbm.close();
		}
   	return model;
   }
   
   /**
    * @param model 公司信息模型
    * 修改公司信息
    * @return 执行结果
    */
	public boolean componentModify(Tscomponent model){
		DBManager dbm = new DBManager();
	    boolean b = false;
		try {
			b = dbm.updateObject(model);
			
		} catch (Exception e) {
			logger.info(e);
			return b;
		}finally{
			dbm.close();
		}
		SaveLog saveLog = new SaveLog();
		saveLog.saveLog("组件信息修改", "修改一条ID为"+model.getId()+"的记录！");
		saveLog = null;
		return b;
	}
	
}
