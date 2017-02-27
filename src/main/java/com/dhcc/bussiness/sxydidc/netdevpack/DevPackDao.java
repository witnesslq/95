package com.dhcc.bussiness.sxydidc.netdevpack;

import java.util.List;

import com.dhcc.common.database.DBManager;
import com.dhcc.common.util.CommUtil;
import com.dhcc.common.util.CreateNum;
import com.dhcc.common.util.StringUtil;
import com.dhcc.flow.bussiness.resschedu.RsDevicepackModel;
import com.dhcc.flow.bussiness.resschedu.RsnetdevportModel;
import com.dhcc.modal.sxydidc.device.Rsdevice;
import com.dhcc.modal.sxydidc.device.Rsnetdevpack;
import com.dhcc.modal.sxydidc.device.Rsnetdevport;
import com.dhcc.modal.system.Tsdict;

public class DevPackDao {
	
	/**
	 * 查询网络设备插槽、板卡
	 */
	public List<RsDevicepackModel> getDeviceslotList(String deviceid){
		List<RsDevicepackModel> list = null;
		DBManager dbm = new DBManager();  
			try {
				String querysql = "SELECT rdp.* from rsnetdevpack rdp " +
						"where rdp.deviceid = '"+deviceid+"' and (rdp.status!='99' or rdp.status is null) " +
						"order by rdp.slotno,rdp.packno ";
				list = dbm.getObjectList(RsDevicepackModel.class, querysql); 
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm = null;
			}
			return list;
		}
	
	/**
	 * 查询板卡端口
	 */
	public List<RsnetdevportModel> getPackPortList(String packid){
		List<RsnetdevportModel> list = null;
		DBManager dbm = new DBManager();  
			try {
				String querysql = "SELECT rdp.*,cust.name as customername,tsd.dvalue as porttypename,rsd.name as servicename " +
						"from rsnetdevport rdp " +
						"left join busccustomer cust on rdp.customerid = cust.id " +
						"left join rsdevice rsd on rsd.id = rdp.toserverid and rsd.devicetype = '02' " +
						"left join tsdict tsd on tsd.dkey = rdp.type and tsd.dtype='PORTTYPE' " +
						"where rdp.netdevpackid = '"+packid+"' and rdp.status != '99'" +
						"order by rdp.rowno,rdp.colno ";
				list = dbm.getObjectList(RsnetdevportModel.class, querysql); 
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				dbm.close();
				dbm = null;
			}
			return list;
		}
	
	/**
	 * @param obj
	 * 添加插槽信息
	 */
	public boolean addSlotInfo(Rsnetdevpack obj){
		DBManager dbm = new DBManager(); 
		boolean b = false;
		String sql = "";
		try {
			sql = "insert into rsnetdevpack(id,deviceid,code,slotno) values('"+CommUtil.getID()+"','"+obj.getDeviceid()+"','','"+obj.getSlotno()+"');";
			System.out.println(sql);
			int i = dbm.executeUpdate(sql);
			if(i == 1){
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			dbm.close();
			dbm = null;
		}
		return b;
	}
	
	/**
	 * 检查是否存在
	 * @param obj
	 */
	public boolean querySlotInfo(Rsnetdevpack obj){
		DBManager dbm = new DBManager(); 
		boolean b = false;
		String sql = "";
		try {
			sql = "select * from rsnetdevpack where deviceid = '"+obj.getDeviceid()+"' and slotno = '"+obj.getSlotno()+"' ";
			Rsnetdevpack model = (Rsnetdevpack) dbm.getObject(Rsnetdevpack.class, sql);
			if(model == null){
				b =  true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			dbm.close();
			dbm = null;
		}
		return b;
	}
	
	/**
	 * @param obj
	 * 添加板卡信息
	 */
	public boolean addPackInfo(Rsnetdevpack obj,Rsnetdevport portobj){
		DBManager dbm = new DBManager(); 
		boolean b = false;
		String sql = "";
		try {
//			sql = "select * from rsnetdevpack where deviceid = '"+obj.getDeviceid()+"' and slotno = '"+obj.getSlotno()+"' and packno is null ";
//			Rsnetdevpack model = (Rsnetdevpack) dbm.getObject(Rsnetdevpack.class, sql);
//			if(model == null){
				obj.setId(CommUtil.getID());
				sql = "select * from rsnetdevpack where deviceid = '"+obj.getDeviceid()+"' and slotno = '"+obj.getSlotno()+"' order by packno desc limit 0,1 ";
				Rsnetdevpack model1 = (Rsnetdevpack) dbm.getObject(Rsnetdevpack.class, sql);
				Rsdevice device=(Rsdevice) dbm.getObject(Rsdevice.class, "select * from rsdevice dev where dev.id='"+obj.getDeviceid()+"' ");
				if(model1 == null){
					obj.setPackno(0);
				}else{
					obj.setPackno(model1.getPackno() + 1);
				}
				b = dbm.insertObject(obj);
//			}
//			else{
//				obj.setId(model.getId());
//				obj.setPackno(0);
//				b = dbm.updateObject(obj);
//			}
			if(b){
				Rsnetdevport port = null;
				Tsdict dict = (Tsdict) dbm.getObject(Tsdict.class, "select * from tsdict where dtype = 'PORTTYPE' and dkey = '"+portobj.getType()+"'");
				String portname = this.createPortName(dict.getDvalue(),obj.getSlotno(),obj.getPackno());
				for(int i=1;i<=obj.getRowcount();i++){
					int sportno = Integer.parseInt(portobj.getNo());
					sportno = sportno + obj.getPerrow()*(i-1);
					int clo = 1;
					for(int j = sportno;j < obj.getPerrow()*i;j++){
						if(j < (obj.getPortcount())){
							port = new Rsnetdevport();
							port.setId(CommUtil.getID());
							port.setNo(j + "");
							port.setColno(clo);
							port.setRowno(i);
							port.setType(portobj.getType());
							port.setConfrate(portobj.getConfrate());
							port.setCustomerid("");
							port.setStatus("01");
							port.setDescription("");
							port.setIpaddr("");
							port.setPortname(portname + j);
							port.setPortcode(CreateNum.getNum("DEVPORTCODE"));
							port.setNetdevpackid(obj.getId());
							port.setTonetdevid("");
							port.setToserverid("");
							port.setMacaddr("");
							port.setDcid(device.getDcid());
							clo++;
							b = dbm.insertObject(port);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbm.close();
			dbm = null;
		}
		return b;
	}
	
	/**
	 * @param obj
	 * 删除插槽信息
	 */
	public boolean deleteSlotInfo(Rsnetdevpack obj){
		DBManager dbm = new DBManager(); 
		boolean b = true;
		String sql = "";
		try {
			sql = "delete from rsnetdevport where netdevpackid in (select id from rsnetdevpack where deviceid = '"+obj.getDeviceid()+"' and slotno = '"+obj.getSlotno()+"' )";
			dbm.addBatch(sql);
			sql = "delete from rsnetdevpack where deviceid = '"+obj.getDeviceid()+"' and slotno = '"+obj.getSlotno()+"' ";
			dbm.addBatch(sql);
			dbm.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			dbm.close();
			dbm = null;
		}
		return b;
	}
	
	/**
	 * @param obj
	 * 删除板卡信息
	 */
	public boolean deletePackInfo(String packid){
		DBManager dbm = new DBManager(); 
		boolean b = true;
		String sql = "";
		try {
			sql = "delete from rsnetdevport where netdevpackid = '"+packid+"'";
			dbm.addBatch(sql);
			sql = "delete from rsnetdevpack where id = '"+packid+"' ";
			dbm.addBatch(sql);
			dbm.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			dbm.close();
			dbm = null;
		}
		return b;
	}
	
	
	/**
	 * @param list 数据字典
	 * @param portytype 端口类型
	 * @param slot 插槽编号
	 * @param pack 板卡编号
	 * @return 端口名称
	 */
	private String createPortName(String portytype,int slot,int pack){
		String portname = "";
		if(portytype.indexOf("光") >= 0){
			portname += "XG/";
		}else{
			portname += "G/";
		}
		portname += slot + "/" + pack + "/";
		return portname;
	}
	
	/**
	 * 根据插槽和板卡序号，查询设备端口是否被占用
	 */
	public String queryPortExit(String deviceid,String slotno,String packno){
		DBManager dbm = new DBManager(); 
		String sql = "";
		int portcount = 0;
		try {
			sql = "select count(*) from rsnetdevport where netdevpackid in  (select id from rsnetdevpack where deviceid = '"+deviceid+"' and slotno = '"+slotno+"' " ;
			if(!StringUtil.isNullOrEmpty(packno)){
				sql += " and packno = '"+packno+"'";
			}		
			sql += ") and status != '01' ";
			System.out.println(sql);
			portcount = dbm.executeQueryCount(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}finally{
			dbm.close();
			dbm = null;
		}
		return portcount + "";
	}
	
	/**
	 * 根据板卡id，查询设备端口是否被占用
	 */
	public String queryPortExit(String id){
		DBManager dbm = new DBManager(); 
		String sql = "";
		int portcount = 0;
		try {
			sql = "select count(*) from rsnetdevport where netdevpackid = '"+id+"'  and status != '01' ";
			System.out.println(sql);
			portcount = dbm.executeQueryCount(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}finally{
			dbm.close();
			dbm = null;
		}
		return portcount + "";
	}

}
