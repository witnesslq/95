package com.dhcc.common.system.page;

import com.dhcc.modal.system.PageModel;

/**
 * 分页SQL构造
 * @author GYR
 */
public class PageFactory {
	private static final String DATABASE_TYPE = "MYSQL";
	/**
	 * @param querysql 查询sql
	 * @param pm 分页bean
	 */
	public  String createPageSQL(String querysql,PageModel pm){
		String sql  = "";
		if(DATABASE_TYPE.equals("ORACLE")){
			OracleFactory temp = new  OracleFactory();
			sql = temp.createSql(querysql, pm);
			temp = null;
		}else if(DATABASE_TYPE.equals("MYSQL")){
			MySqlFactory temp = new MySqlFactory();
			sql = temp.createSql(querysql, pm);
		    temp = null;
		}
		return sql;
	}

	public  String createPageSQL(String querysql){
		String sql  = "";
		if(DATABASE_TYPE.equals("ORACLE")){
			OracleFactory temp = new  OracleFactory();
			sql = temp.createAllSql(querysql);
			temp = null;
		}else if(DATABASE_TYPE.equals("MYSQL")){
			MySqlFactory temp = new MySqlFactory();
			sql = temp.createAllSql(querysql);
		    temp = null;
		}
		return sql;
	}

	public  String createSQL(String querysql){
		String sql  = "";
		if(DATABASE_TYPE.equals("ORACLE")){
			OracleFactory temp = new  OracleFactory();
			sql = temp.createAllSql(querysql);
			temp = null;
		}else if(DATABASE_TYPE.equals("MYSQL")){
			MySqlFactory temp = new MySqlFactory();
			sql = temp.createAllSql(querysql);
		    temp = null;
		}
		return sql;
	}
}
