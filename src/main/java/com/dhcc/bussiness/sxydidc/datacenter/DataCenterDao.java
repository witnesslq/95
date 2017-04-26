package com.dhcc.bussiness.sxydidc.datacenter;

import org.apache.log4j.Logger;
import com.dhcc.common.database.DBManager;
import com.mockrunner.util.common.StringUtil;

public class DataCenterDao {
	private static final Logger logger = Logger.getLogger(DataCenterDao.class);
	
	
	public DataCenterModel queryDataCenterById(String id){
		DBManager dbm=new DBManager();
		logger.info("call DataCenterDao.queryDataCenterById() start");
		DataCenterModel model=new DataCenterModel();
		if(!StringUtil.isEmptyOrNull(id)){
			StringBuilder querySql=new StringBuilder(" select dc.* from rsdatacenter dc where dc.id='"+id+"' ");
			try {
				model=  (DataCenterModel) dbm.getObject(DataCenterModel.class, querySql.toString());
				logger.info("call DataCenterDao.queryDataCenterById() success");
			} catch (Exception e) {
				logger.info("call DataCenterDao.queryDataCenterById() fail");
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm=null;
			}
		}
		logger.info("call DataCenterDao.queryDataCenterById() finish");
		return model;
	}

}
