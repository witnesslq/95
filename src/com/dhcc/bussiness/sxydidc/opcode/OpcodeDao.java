package com.dhcc.bussiness.sxydidc.opcode;

import java.util.List;

import com.dhcc.common.database.DBManager;
import com.dhcc.modal.system.CommModel;



public class OpcodeDao {
	public String QueryList(){
		String querysql = "select opc.*,tsc.dvalue as codetypevalue from opcode opc left join tsconfig tsc on tsc.id = opc.codetype where 1=1";
		
		querysql += " order by codetype ";
		return querysql;
	}
	public String QuerySql(String id){
		return "select opc.*,tsc.dvalue as codetypevalue from opcode opc left join tsconfig tsc on tsc.id = opc.codetype where opc.id = '"+id+"'";
	}
	
	/**
	 * 根据配置表的类型，返回前台使用的select的
	 * @param dictType
	 * @return
	 */
	public  List<CommModel> getSelectHTML(String dictType) {
		String sql = "select t.id as id,t.dvalue as text from tsconfig t where dtype='" + dictType
					+ "' order by dkey";
		DBManager dbm = null;
		List<CommModel> list = null;
		try {
			dbm = new DBManager();
		    list = dbm.getObjectList(CommModel.class, sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close();
		}
		return list;
	}
}
