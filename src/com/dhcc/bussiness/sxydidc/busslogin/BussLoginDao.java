package com.dhcc.bussiness.sxydidc.busslogin;

import org.apache.log4j.Logger;

import com.dhcc.common.database.DBManager;
import com.dhcc.modal.system.Tsuser;

public class BussLoginDao {
	
	private static final Logger logger = Logger.getLogger(BussLoginDao.class);
	
	
	/**
	 * 根据用户id查询是否登录
	 * @param userid
	 */
	public Tsuser queryUserByUserId(String userid){
		Tsuser tsuser = null;
		 DBManager dbm = new DBManager();
		 try {
			String sql = "select * from tsuser where id = '"+userid+"'";
			tsuser = (Tsuser) dbm.getObject(Tsuser.class, sql);
		} catch (Exception e) {
			logger.error("查询数据库中是否存在该用户**********", e);
			return null;
		}finally{
			dbm.close();
		}
		return tsuser;
	}

}
