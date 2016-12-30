package com.dhcc.flow.task;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.BeanUtils;

import com.dhcc.common.database.OracleDBManager;



public class PortIpsDao
{
   
   /*public BaseVo loadFromRS(ResultSet rs)
   {
	   ErrorCorrectionModel vo = new ErrorCorrectionModel();
       try
       {
    	   vo.setIpaddress(rs.getString("ipaddress")); //ip地址
    		vo.setEntity(rs.getString("entity"));		//OutBandwidthUtilHdx、InBandwidthUtilHdx
    		vo.setSubentity(rs.getString("subentity"));	//端口索引
    		vo.setCollecttime(rs.getString("collecttime")); //采集时间
    		vo.setRestype(rs.getString("restype"));   
    		vo.setCategory(rs.getString("category"));
    		vo.setUtilhdxunit(rs.getString("utilhdxunit"));
    		vo.setUtilhdx(rs.getString("utilhdx"));  //流量
    		vo.setUtilhdxperc(rs.getString("utilhdxperc"));  //带宽利用率
    		vo.setDiscardsperc(rs.getString("discardsperc"));// 丢包率
    		vo.setErrorsperc(rs.getString("errorsperc"));  //错误率
    		vo.setPercunit(rs.getString("percunit"));   //利用率单位
    		vo.setUtilhdxflag(rs.getString("utilhdxflag"));  //是否为异常
    		vo.setRecover(rs.getString("recover"));      //纠错后数据
    		vo.setIfspeed(rs.getString("ifspeed"));     //速率
       }
       catch(Exception e)
       {
 	       SysLogger.error("ErrorCorrectionModel.loadFromRS()",e); 
       }	   
       return vo;
   }	*/
	/**
	 * 获取1小时时间粒度
	 */
	public void gethalfyearData(String ip) {
		OracleDBManager dbm=new OracleDBManager();
		
		Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -6);
        //前6个月的日期
        String halfyear = new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
        String sql = "select a.ipaddress,a.restype,a.category,a.entity,a.subentity,round(avg(a.utilhdx),2) as utilhdx,round(avg(a.utilhdxperc),2) as utilhdxperc,round(avg(a.discardsperc),2) as discardsperc ,round(avg(a.errorsperc),2) as errorsperc, a.collecttime ,a.utilhdxunit,a.percunit,a.ifspeed from (select  ipaddress,restype,category,entity,subentity,utilhdx,utilhdxperc,discardsperc,errorsperc,TO_CHAR(collecttime,'YYYY/MM/DD hh24') as collecttime ,utilhdxunit,percunit,ifspeed from portips"+ip+" t where t.collecttime<to_date('"+halfyear+"','YYYY/MM/DD'))  a  group by a.collecttime,a.ipaddress,a.restype,a.category,a.entity,a.subentity, a.collecttime ,a.utilhdxunit,a.percunit,a.ifspeed";
		//select a.ipaddress,a.restype,a.category,a.entity,a.subentity,avg(a.utilhdx) as utilhdx,avg(a.utilhdxperc) as utilhdxperc,avg(a.discardsperc) as discardsperc ,avg(a.errorsperc) as errorsperc, a.collecttime ,a.utilhdxunit,a.percunit,a.ifspeed from (select  ipaddress,restype,category,entity,subentity,utilhdx,utilhdxperc,discardsperc,errorsperc,TO_CHAR(collecttime,'YYYY/MM/DD hh24') as collecttime ,utilhdxunit,percunit,ifspeed from portips183_203_0_111 t)  a group by a.collecttime,a.ipaddress,a.restype,a.category,a.entity,a.subentity,a.utilhdx,a.utilhdxperc,a.discardsperc,a.errorsperc, a.collecttime ,a.utilhdxunit,a.percunit,a.ifspeeda
		List<PortIpsModel> list = null;
		try {
			list = dbm.getObjectList(PortIpsModel.class, sql);
			if(list==null||list.size()==0) return; 
			PortIpsDao dao = new PortIpsDao();
			List insertsql = dao.inserthelfyear(list, ip,"hour");
			
			//执行删除操作
			String deletesql = "delete from portips"+ip+" t where t.collecttime<to_date('"+halfyear+"','YYYY/MM/DD')";
			insertsql.add(deletesql);
			boolean t = dbm.excuteBatchSql(insertsql);
			System.out.println(t);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbm.close();
		}
	
	}
	
	/**
	 * 获取一天时间粒度
	 */
	public void getyearData(String ip) {
		OracleDBManager dbm=new OracleDBManager();
		
		Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -18);
        //前12个月的日期
        String year = new SimpleDateFormat( "yyyy-MM-dd").format(cal.getTime());
        String sql = "select a.ipaddress,a.restype,a.category,a.entity,a.subentity,round(avg(a.utilhdx),2) as utilhdx,round(avg(a.utilhdxperc),2) as utilhdxperc,round(avg(a.discardsperc),2) as discardsperc ,round(avg(a.errorsperc),2) as errorsperc, a.collecttime ,a.utilhdxunit,a.percunit,a.ifspeed from (select  ipaddress,restype,category,entity,subentity,utilhdx,utilhdxperc,discardsperc,errorsperc,TO_CHAR(collecttime,'YYYY/MM/DD') as collecttime ,utilhdxunit,percunit,ifspeed from portipshour"+ip+" t where t.collecttime<to_date('"+year+"','YYYY/MM/DD'))  a  group by a.collecttime,a.ipaddress,a.restype,a.category,a.entity,a.subentity, a.collecttime ,a.utilhdxunit,a.percunit,a.ifspeed";
		//select a.ipaddress,a.restype,a.category,a.entity,a.subentity,avg(a.utilhdx) as utilhdx,avg(a.utilhdxperc) as utilhdxperc,avg(a.discardsperc) as discardsperc ,avg(a.errorsperc) as errorsperc, a.collecttime ,a.utilhdxunit,a.percunit,a.ifspeed from (select  ipaddress,restype,category,entity,subentity,utilhdx,utilhdxperc,discardsperc,errorsperc,TO_CHAR(collecttime,'YYYY/MM/DD hh24') as collecttime ,utilhdxunit,percunit,ifspeed from portips183_203_0_111 t)  a group by a.collecttime,a.ipaddress,a.restype,a.category,a.entity,a.subentity,a.utilhdx,a.utilhdxperc,a.discardsperc,a.errorsperc, a.collecttime ,a.utilhdxunit,a.percunit,a.ifspeeda
		List<PortIpsModel> list = null;
		try {
			list = dbm.getObjectList(PortIpsModel.class, sql);
			if(list==null||list.size()==0) return; 
			PortIpsDao dao = new PortIpsDao();
			List insertsql = dao.inserthelfyear(list, ip,"day");
			//执行删除操作
			String deletesql = "delete from portipshour"+ip+" t where t.collecttime<to_date('"+year+"','YYYY/MM/DD')";
			insertsql.add(deletesql);
			boolean t = dbm.excuteBatchSql(insertsql);
			System.out.println(t);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbm.close();
		}
	
	}
	
	/**
	 * 根据ip生成插入语句 （PORTIPSDAY） 颗粒为小时  
	 * @param list
	 * @param ip
	 * @return
	 */
   public List inserthelfyear(List list,String ip,String flag)
   {	
		String allipstr = ip.replaceAll("\\.", "_");
	    List insertsql = new ArrayList();
			//端口状态信息入库
			Calendar tempCal = null;
			Date cc = null;
			//StringBuffer sBuffer = null;
			String time = null;
			//UtilHdx
			List portipslist  = list;
			String tablename="";
			if (portipslist != null && portipslist.size() > 0) {
				if(flag.equals("day")) {
					tablename = "PORTIPSDAY" + allipstr;
				}else {
					tablename = "PORTIPSHOUR" + allipstr;
				}
				 
				PortIpsModel utilhdx = null;
				for (int si = 0; si < portipslist.size(); si++) {
					
						utilhdx = (PortIpsModel) portipslist.get(si);
						if (utilhdx.getRestype().equals("dynamic")) {
							time = utilhdx.getCollecttime();
							StringBuffer sBuffer = new StringBuffer();
							sBuffer.append("insert into ");
							sBuffer.append(tablename);
							sBuffer.append("(ipaddress,restype,category,entity,subentity,utilhdxunit,utilhdx,utilhdxperc,discardsperc,errorsperc,percunit,utilhdxflag,recover,ifspeed,collecttime) ");
							sBuffer.append("values('");
							sBuffer.append(utilhdx.getIpaddress());
							sBuffer.append("','");
							sBuffer.append(utilhdx.getRestype());
							sBuffer.append("','");
							sBuffer.append(utilhdx.getCategory());
							sBuffer.append("','");
							sBuffer.append(utilhdx.getEntity());
							sBuffer.append("','");
							sBuffer.append(utilhdx.getSubentity());
							sBuffer.append("','");
							sBuffer.append(utilhdx.getUtilhdxunit());
							sBuffer.append("','");
							sBuffer.append(utilhdx.getUtilhdx());
							sBuffer.append("','");
							sBuffer.append(utilhdx.getUtilhdxperc());
							sBuffer.append("','");
							sBuffer.append(utilhdx.getDiscardsperc());
							sBuffer.append("','");
							sBuffer.append(utilhdx.getErrorsperc());
							sBuffer.append("','");
							sBuffer.append(utilhdx.getPercunit());
							sBuffer.append("','");
							sBuffer.append(utilhdx.getUtilhdxflag());
							sBuffer.append("','");
							sBuffer.append(utilhdx.getRecover());
							sBuffer.append("','");
							sBuffer.append(utilhdx.getIfspeed());
							sBuffer.append("',");
							sBuffer.append("to_date('"+time+"','YYYY-MM-DD HH24:MI:SS')");
							sBuffer.append(")");
							 //System.out.println(sBuffer.toString());
							 insertsql.add(sBuffer.toString());
							//GathersqlListManager.Addsql(sBuffer.toString());
					sBuffer = null;
				}
						
			}
			}
			return insertsql;
   }

   /**
    * 获取被监控设备的ip
    * @return
    */
   public List getNodeHost() {
	   OracleDBManager dbm=new OracleDBManager();
	   List list =null;
	   try {
		   String sql = "select distinct thn.ip_address from TOPO_HOST_NODE thn,topo_interface ti where thn.ip_address=ti.node_id";
		   list = dbm.getObjectList(HostNode.class, sql);
		   
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbm.close();
		}
	   
	   return list;
	   
   }

	
}
