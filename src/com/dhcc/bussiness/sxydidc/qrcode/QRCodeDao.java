package com.dhcc.bussiness.sxydidc.qrcode;

import java.util.ArrayList;
import java.util.List;

import com.dhcc.common.database.DBManager;
import com.dhcc.modal.sxydidc.rack.Rsrack;

public class QRCodeDao {
	@SuppressWarnings("unchecked")
	public List<Rsrack> queryAllRacksByRoomId(String roomId) {
		DBManager dbManager = new DBManager();
		String sql = "SELECT * FROM RSRACK WHERE ROOMID = '" + roomId + "' AND STATUS != '05' ORDER BY NAME";
		List<Rsrack> racks = new ArrayList<Rsrack>();
		try {
			racks = dbManager.getObjectList(Rsrack.class, sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return racks;
	}
	
	public Rsrack queryRackByRackId(String rackId) {
		DBManager dbManager = new DBManager();
		String sql = "SELECT * FROM RSRACK WHERE ID = '" + rackId + "'";
		Rsrack rack = null;
		try {
			rack = (Rsrack) dbManager.getObject(Rsrack.class, sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return rack;
	}
}