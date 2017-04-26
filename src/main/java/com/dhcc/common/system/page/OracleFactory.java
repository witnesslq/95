package com.dhcc.common.system.page;

import com.dhcc.modal.system.PageModel;

/**
 * Oracle
 * @author GYR
 *
 */
public class OracleFactory implements PageInter{

	public String createSql(String querysql, PageModel pm) {
	String sql = "SELECT * FROM " +
			     "(SELECT a.*,ROWNUM  rn from ("+querysql+")a " +
	     		 "where  ROWNUM<="+pm.getPerPage()*pm.getCurrentPage()+") " +
 		 		 "where rn>"+(pm.getCurrentPage()-1)*pm.getPerPage()+"";
		return sql;
	}


	
	public String createAllSql(String querysql) {
		String sql = "SELECT * FROM " +
				     "(SELECT a.*,ROWNUM  rn from ("+querysql+")a " ;
			return sql;
		}
}
