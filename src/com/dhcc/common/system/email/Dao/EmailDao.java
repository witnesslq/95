package com.dhcc.common.system.email.Dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.dhcc.common.database.DBManager;
import com.dhcc.common.system.email.Model.Tsemailconfig;
import com.dhcc.common.system.email.Model.Tsemailsmsrecord;
import com.dhcc.common.util.CommUtil;
import com.dhcc.common.util.DateUtil;
import com.dhcc.modal.system.Tsuser;

/**
 * 表tsemailsmsrecord 短信，电子邮件通知
 * 
 * @author sz
 * 
 */
public class EmailDao {

	private static final Logger logger = Logger.getLogger(EmailDao.class);
	/**
	 * 
	 *将要发送的短信注入短信表中
	 *
	 *表名：tsemailsmsrecord
	 *id：
	 *details：向被通知用户发送的短信，邮件内容
	 *mobile：被通知用户手机号码
	 *email：被通知用户电子邮件地址
	 *sendstate：送发状态（0：未发送；1：发送成功；2：发送失败）  int 默认0
	 *sendtime：发送成功时间（由短信、邮件接口返回）
	 *sendnumber：重新发送次数    int 默认0
	 *createtime：该条记录产生时间
	 *sort：数据类别，用于区分信息来源（1：待办通知  2：服务到期提醒）  int 默认0
	 *
	 */
	public String saveMessage(String details,String mobileNumber,String emailAddress,
			String createtime,String sendtime,int sort,int sendstate,int sendnumber){
		
		DBManager dbm = new DBManager();
//		Tsuser model = getUser(userid);
//		model.getMobileprivate()
//		model.getEmailprivate()
//		getdetails(flowid,flowname)
//		CreateDate.getDateString()
//		if(null==model) return "error";
		
		String sql = String.format("insert into tsemailsmsrecord(id,details,mobile,email" +
				",createtime,sendtime,sort,sendstate,sendnumber) " +
				" values('%s','%s','%s','%s','%s','%s','%s','%s','%s')",
				CommUtil.getID(),details,mobileNumber,emailAddress,DateUtil.toTime(createtime),DateUtil.toTime(sendtime),sort,sendstate,sendnumber);
		dbm.addBatch(sql);
		try {
			dbm.executeBatch();
		} catch (Exception e) {
			logger.info("短信或邮件信息入库失败！");
			return "error";
		} finally {
			dbm.close();
		}
		return "success";
	}
	/**
	 * 获取用户信息
	 * @param id
	 * @return
	 */
	public Tsuser getUser(String id){
		DBManager dbm = new DBManager();
		String sql = "select * from tsuser where id='"+id+"'";
		Tsuser model = (Tsuser)dbm.getObject(Tsuser.class,sql);
		dbm.close();
		return model;
	}
	/**
	 * 更新邮箱配置信息
	 * @param mail
	 * @return
	 */
	public boolean updateEmail(Tsemailconfig mail) {
		DBManager dbm=new DBManager();
		boolean succ=false;
		String sql="select count(*) from tsemailconfig ";
		if(dbm.executeQueryCount(sql)==0){
			succ=dbm.insertObject(mail);
		}else{
			succ= dbm.updateObject(mail);
		}
		dbm.close();
		return succ;
	}

	/**
	 * 查询邮箱配置信息
	 */
	public Tsemailconfig queryEmail() {
		DBManager dbm=new DBManager();
    	String sql="select * from tsemailconfig ";
    	Tsemailconfig model=null;
		try {
			List<Tsemailconfig> list = dbm.getObjectList(Tsemailconfig.class, sql);
			if(list!=null&&list.size()>0){
				model = list.get(0);
			}
		} catch (Exception e) {
			logger.error("queryEmail方法失败", e);
		}finally{
    	   dbm.close();
		}
    	return model;
	}
	
	
	/**
	 * 查询没有发送和发送失败的邮件
	 * @return
	 */
	public List<Tsemailsmsrecord> queryUnSendEmail(){
		DBManager dbm=new DBManager();
    	String sql="select * from tsemailsmsrecord t where t.sendstate=0 or t.sendstate=2 ";
    	List<Tsemailsmsrecord> list=null;
		try {
			list = dbm.getObjectList(Tsemailsmsrecord.class, sql);
		} catch (Exception e) {
			logger.error("queryUnSendEmail方法失败", e);
		}finally{
    	   dbm.close();
		}
		return list;
	}
	
	
	/**
	 * 更新邮箱发送成功的邮件状态
	 * @return
	 */
	public int updateSendEmailState(int sendstate,String id){
		DBManager dbm=new DBManager();
//    	String sql="update tsemailsmsrecord t set t.sendstate="+sendstate+",t.sendtime=date_format(now(),'%Y-%m-%d %H:%i:%s') where t.id in ('"+id+"') ";
    	String sql="update tsemailsmsrecord t set t.sendstate="+sendstate+",t.sendtime='"+DateUtil.toTime()+"' where t.id in ('"+id+"') ";
    	int count=0;
		try {
			count=dbm.executeUpdate(sql);
		} catch (Exception e) {
			logger.error("updateEmailState方法失败", e);
		}finally{
    	   dbm.close();
		}
		return count;
	}
	/**
	 * 查寻发送人的邮箱地址
	 */
	public String queryEmailAddress(String userid) {
		DBManager dbm=new DBManager();
    	String sql="select * from tsuser where id ='"+userid+"'";
    	Tsuser model=null;
		try {
			model = (Tsuser) dbm.getObject(Tsuser.class, sql);
		} catch (Exception e) {
			logger.error("queryEmailAddress方法失败", e);
		}finally{
    	   dbm.close();
		}
    	return model.getEmailprivate();
	}
}
