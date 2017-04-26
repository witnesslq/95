package com.dhcc.common.system.login;

import org.apache.log4j.Logger;

import com.dhcc.common.database.DBManager;
import com.dhcc.common.util.HashUtil;
import com.dhcc.modal.system.Tscorp;
import com.dhcc.modal.system.Tsrole;
import com.dhcc.modal.system.Tsuser;
 

public class LoginDao {
	
	private static final Logger logger = Logger.getLogger(LoginDao.class);

	
	/**
	 * 查询该用户名、密码是否正确
	 * @return 是否有改用户
	 */
	public Tsuser QueryUser(String username,String password){
		 Tsuser modal=null;
		 DBManager dbm = new DBManager();
		 password = HashUtil.hashCode(password);
		 try {
			
			String sql = "select * from tsuser where loginname=  '" + username
					+ "' and userpass='" + password + "' ";
			modal = (Tsuser) dbm.getObject(Tsuser.class, sql);
		} catch (Exception e) {
			logger.error("查询用户名、密码正确与否出错**********", e);
		}finally{
			dbm.close();
		}
		return modal;
	}
	
	/**
	 * 查询该用户所属的公司
	 * @param userid
	 * @return
	 */
	public String QueryUserCorpid(String userid){
		 DBManager dbm = new DBManager();
		 Tscorp model = null;
		 String result = "";
		 String sql = "SELECT tc.* FROM tsuser tu " +
					  "LEFT JOIN tslusercorp tsl  ON  tu.id = tsl.userid " +
					  "LEFT JOIN tscorp tc on tc.id = tsl.corpid " +
					  "WHERE tu.id = '"+userid+"'";
		model = (Tscorp) dbm.getObject(Tscorp.class, sql);
		dbm.close();
		if(model != null){
			result = model.getId();
		}
		return result;
	}
	
	/**
	 * 查询该用户所属的角色
	 * @param userid
	 * @return
	 */
	public String QueryUserRoleId(String userid){
		DBManager dbm = new DBManager();
		Tsrole model = new Tsrole();
		String result = "";
		String sql=" select distinct(group_concat(t.roleid)) as id from tsluserrole t where t.userid='"+userid+"'  group by t.userid "; 
		model = (Tsrole) dbm.getObject(Tsrole.class, sql);
		dbm.close();
		if(model != null){
			result = model.getId();
		}
		return result;
	}	
	
}
