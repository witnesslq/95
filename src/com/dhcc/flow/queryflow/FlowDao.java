package com.dhcc.flow.queryflow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.dhcc.common.database.OracleDBManager;
import com.dhcc.common.system.page.PageFactory;
import com.dhcc.common.util.AnyTypeDao;
import com.dhcc.common.util.CommDao;
import com.opensymphony.xwork2.ActionContext;

public class FlowDao {
	private static final Logger logger = Logger.getLogger(FlowDao.class);
	
	/*
	 * 按客户查询客户所有ip 的流量
	 */
	public List getuserforip() {
		OracleDBManager dbm=new OracleDBManager();
		List<FlowModel> list = null;
		try {
			String sql = "SELECT id AS userid,username AS ipaddress FROM `tsuser`";
			list = dbm.getObjectList(FlowModel.class, sql);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--QueryList--失败"+e.getMessage());
		}finally{
			dbm.close();
		}
		
		return list;
	}
	
	/*
	 * 得到所有用户当前日期的总流量
	 */
	public List getcustomerflow(String ymd,String datetype) {
		String alluseripsql="select t.if_index as port,t.node_id  from customer c ,topo_interface t where c.customer_id=t.customer_id order by node_id ";
		String alluseripsql_ip="select t.node_id  from customer c ,topo_interface t where c.customer_id=t.customer_id  group by t.node_id order by t.node_id ";
		//得到所有用户流量信息
		return getUserFlow(alluseripsql,alluseripsql_ip,ymd,datetype);
	} 
	
	/*
	 * 按客户id和日期得到用户总流量
	 */
	public List getcustomeranddateflow(String userid,String ipaddress,String ip,String ymd,String datetype) {
		String alluseripsql="";
		String alluseripsql_ip="";
		StringBuffer ipbuffer =new StringBuffer();
		StringBuffer portbuffer =new StringBuffer();
		
		if (userid!=null && !userid.equals("")&&ipaddress!=null && !ipaddress.equals("")) {
			
			String ports[] = ipaddress.split(",");
			portbuffer.append("('"+ports[0]+"'");
			for(int i=1;i<ports.length;i++) {
				portbuffer.append(",'"+ports[i]+"'");
			}
			portbuffer.append(")");
			
			String ipadds[] = ip.split(",");
			ipbuffer.append("('"+ipadds[0]+"'");
			for(int i=1;i<ipadds.length;i++) {
				ipbuffer.append(",'"+ipadds[i]+"'");
			}
			ipbuffer.append(")");
			
			
			alluseripsql="select t.if_index as port,t.node_id  from customer c ,topo_interface t where c.customer_id=t.customer_id and c.customer_id='"+userid+"'  and t.if_desc in "+portbuffer+" and t.node_id in "+ipbuffer+" order by t.node_id ";
			alluseripsql_ip = "select t.node_id  from customer c ,topo_interface t where c.customer_id=t.customer_id  and c.customer_id='"+userid+"'  and t.if_desc in "+portbuffer+" and t.node_id in "+ipbuffer+" group by t.node_id order by t.node_id ";
		} else if(ipaddress!=null && !ipaddress.equals("")) {
			String ports[] = ipaddress.split(",");
			portbuffer.append("('"+ports[0]+"'");
			for(int i=1;i<ports.length;i++) {
				portbuffer.append(",'"+ports[i]+"'");
			}
			portbuffer.append(")");
			
			String ipadds[] = ip.split(",");
			ipbuffer.append("('"+ipadds[0]+"'");
			for(int i=1;i<ipadds.length;i++) {
				ipbuffer.append(",'"+ipadds[i]+"'");
			}
			ipbuffer.append(")");
			
			alluseripsql="select t.if_index as port,t.node_id  from customer c ,topo_interface t where c.customer_id=t.customer_id and t.if_desc in "+ipbuffer+" and t.node_id in "+ipbuffer+" order by t.node_id ";
			alluseripsql_ip ="select t.node_id  from customer c ,topo_interface t where c.customer_id=t.customer_id and t.if_desc in "+ipbuffer+" and t.node_id in "+ipbuffer+" group by t.node_id order by t.node_id";
		}  else if(userid!=null && !userid.equals("")){
			alluseripsql="select t.if_index as port,t.node_id  from customer c ,topo_interface t where c.customer_id=t.customer_id and c.customer_id='"+userid+"' order by t.node_id ";
			alluseripsql_ip="select t.node_id  from customer c ,topo_interface t where c.customer_id=t.customer_id and c.customer_id='"+userid+"' group by t.node_id order by t.node_id ";
		} 
		
		 
		//根据条件得到用户流量信息
		return getUserFlow(alluseripsql,alluseripsql_ip,ymd,datetype);
	} 
	
	/*
	 * 得到所有用户当前日期的总流量
	 */
	public List getuserflow(String ymd,String datetype) {
		OracleDBManager dbm=new OracleDBManager();
		String alluseripsql="SELECT topo.PORT,topo.NODE_ID FROM product_ip pro ,topo_interface topo WHERE pro.IP = topo.IP_ADDRESS";
		StringBuffer sql= new StringBuffer("select collecttime,SUM(utilhdx) as utilhdx,sum(utilhdxperc) as utilhdxperc from (");
		StringBuffer avgandsumsql= new StringBuffer("select MAX(to_number(utilhdx)) sumthevalue,round(AVG(utilhdx),2) avgthevalue from (");
		List list =null;
		try {
			List alluseriplist = dbm.getObjectList(ProductIpModel.class, alluseripsql);
			
			if(alluseriplist.size()==0) {
				return list;
			}
			if (alluseriplist.size()>=1) {
				ProductIpModel model = (ProductIpModel)alluseriplist.get(0);
				sql.append(" SELECT  utilhdx,utilhdxperc,collecttime,subentity FROM portips"+model.getNODE_ID().replaceAll("\\.", "_")+" WHERE subentity='"+model.getPORT()+"' AND entity='出口'  ");
				//avgandsumsql.append(" SELECT  utilhdx,collecttime,subentity FROM portips"+model.getNODE_ID().replaceAll("\\.", "_")+" WHERE subentity='"+model.getPORT()+"' AND entity='出口' ");
				sql = getsql(sql, ymd, datetype);
				//avgandsumsql = getavgandsumsql(avgandsumsql, ymd, datetype);
				for(int i=1;i< alluseriplist.size();i++) {
					ProductIpModel model1 = (ProductIpModel)alluseriplist.get(i);
					sql.append("UNION ALL SELECT utilhdx,utilhdxperc,collecttime,subentity FROM portips"+model1.getNODE_ID().replaceAll("\\.", "_")+" WHERE subentity='"+model1.getPORT()+"' AND entity='出口'  ");
					//avgandsumsql.append("UNION ALL SELECT utilhdx,collecttime,subentity FROM portips"+model1.getNODE_ID().replaceAll("\\.", "_")+" WHERE subentity='"+model1.getPORT()+"' AND entity='出口' ");
					sql = getsql(sql, ymd, datetype);
					//avgandsumsql = getavgandsumsql(avgandsumsql, ymd, datetype);
				}
			}
			
			sql.append(") a  GROUP BY a.collecttime order by a.collecttime");
			avgandsumsql.append(""+sql.toString()+") a ");
			
			System.out.println(sql.toString());
			list = dbm.getObjectList(FlowModel.class, sql.toString());
			List avgsumlist = dbm.getObjectList(FlowModel.class, avgandsumsql.toString());
			FlowModel flowModel = (FlowModel)avgsumlist.get(0);
			list.add(flowModel);
			
		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--QueryList--失败"+e.getMessage());
		}finally{
			dbm.close();
		}
		
		return list;
	} 
	
	
	/*
	 * 按客户id和日期得到用户总流量
	 */
	public List getuseranddateflow(String userid,String ipaddress,String ymd,String datetype) {
		OracleDBManager dbm=new OracleDBManager();
		String alluseripsql="";
		StringBuffer ipbuffer =new StringBuffer();
		if (userid!=null && !userid.equals("")&&ipaddress!=null && !ipaddress.equals("")) {
			String ip[] = ipaddress.split(",");
			ipbuffer.append("('"+ip[0]+"'");
			for(int i=1;i<ip.length;i++) {
				ipbuffer.append(",'"+ip[i]+"'");
			}
			ipbuffer.append(")");
			alluseripsql="SELECT topo.NODE_ID,topo.PORT FROM customer c ,contract con,product pro,product_ip proip ,topo_interface topo WHERE c.customer_id=con.customer_id AND con.contract_id=pro.contract_id AND pro.product_id=proip.product_id AND topo.IP_ADDRESS=proip.IP AND c.CUSTOMER_ID='"+userid+"'  AND topo.IP_ADDRESS IN "+ipbuffer+"";
		} else if(ipaddress!=null && !ipaddress.equals("")) {
			String ip[] = ipaddress.split(",");
			ipbuffer.append("('"+ip[0]+"'");
			for(int i=1;i<ip.length;i++) {
				ipbuffer.append(",'"+ip[i]+"'");
			}
			ipbuffer.append(")");
			alluseripsql="SELECT topo.NODE_ID,topo.PORT FROM customer c ,contract con,product pro,product_ip proip ,topo_interface topo WHERE c.customer_id=con.customer_id AND con.contract_id=pro.contract_id AND pro.product_id=proip.product_id AND topo.IP_ADDRESS=proip.IP AND topo.IP_ADDRESS IN "+ipbuffer+"";
		}  else if(userid!=null && !userid.equals("")){
			alluseripsql="SELECT topo.NODE_ID,topo.PORT FROM customer c ,contract con,product pro,product_ip proip ,topo_interface topo WHERE c.customer_id=con.customer_id AND con.contract_id=pro.contract_id AND pro.product_id=proip.product_id AND topo.IP_ADDRESS=proip.IP AND c.CUSTOMER_ID='"+userid+"'";
		} 
		 
		StringBuffer sql= new StringBuffer("select collecttime,SUM(utilhdx) as utilhdx,sum(utilhdxperc) as utilhdxperc  from (");
		StringBuffer avgandsumsql= new StringBuffer("select MAX(to_number(utilhdx)) sumthevalue,round(AVG(utilhdx),2) avgthevalue from (");
		List list =null;
		try {
			List alluseriplist = dbm.getObjectList(ProductIpModel.class, alluseripsql);
			
			if(alluseriplist.size()==0) {
				return list;
			}
			if (alluseriplist.size()>=1) {
				ProductIpModel model = (ProductIpModel)alluseriplist.get(0);
				sql.append(" SELECT  utilhdx,utilhdxperc,collecttime,subentity FROM portips"+model.getNODE_ID().replaceAll("\\.", "_")+" WHERE subentity='"+model.getPORT()+"' AND entity='出口'  ");
				//avgandsumsql.append(" SELECT  utilhdx,collecttime,subentity FROM portips"+model.getNODE_ID().replaceAll("\\.", "_")+" WHERE subentity='"+model.getPORT()+"' AND entity='出口'  ");
				sql = getsql(sql, ymd, datetype);
				//avgandsumsql = getavgandsumsql(avgandsumsql, ymd, datetype);
				for(int i=1;i< alluseriplist.size();i++) {
					ProductIpModel model1 = (ProductIpModel)alluseriplist.get(i);
					sql.append("UNION ALL SELECT utilhdx,utilhdxperc,collecttime,subentity FROM portips"+model1.getNODE_ID().replaceAll("\\.", "_")+" WHERE subentity='"+model1.getPORT()+"' AND entity='出口'  ");
					//avgandsumsql.append("UNION ALL SELECT utilhdx,collecttime,subentity FROM portips"+model1.getNODE_ID().replaceAll("\\.", "_")+" WHERE subentity='"+model1.getPORT()+"' AND entity='出口' ");
					sql = getsql(sql, ymd, datetype);
					//avgandsumsql = getavgandsumsql(avgandsumsql, ymd, datetype);
				}
			}
			
			sql.append(") a  GROUP BY a.collecttime order by a.collecttime");
			avgandsumsql.append(""+sql.toString()+") a ");
			
			System.out.println(sql.toString());
			list = dbm.getObjectList(FlowModel.class, sql.toString());
			List avgsumlist = dbm.getObjectList(FlowModel.class, avgandsumsql.toString());
			FlowModel flowModel = (FlowModel)avgsumlist.get(0);
			list.add(flowModel);
		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--QueryList--失败"+e.getMessage());
		}finally{
			dbm.close();
		}
		
		return list;
	} 
	
	/**
	 * 用户名称
	 * @return
	 */
	public List getcustomerName() {
		OracleDBManager dbm=new OracleDBManager();
		List<UserInfo> list=null;
		try {
			String querysql = "select c.customer_id,c.customer_name from customer c ,topo_interface t where c.customer_id=t.customer_id group by c.customer_name,c.customer_id";
			String countsql = "select count(*) from (" + querysql + ") t";
			
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querysql);
			
			list = dbm.getObjectList(UserInfo.class, sql);
		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询客户名称列表时候出错！"+e.getMessage());
		}finally{
			dbm.close();
		}
		return list;
	}
	
	/**
	 * 根据用户名称查询用户的所有ip
	 * @return
	 */
	public List getcustomer_port(String userid) {
		OracleDBManager dbm=new OracleDBManager();
		List<UserInfo> list=null;
		String querysql="";
		try {
			if(userid!=null && !userid.equals("")) {
				 querysql = "select c.customer_id,c.customer_name,t.if_desc,t.node_id as ipaddress from customer c ,topo_interface t where c.customer_id=t.customer_id and c.customer_id='"+userid+"' ";
			} else {
				 querysql = "select c.customer_id,c.customer_name,t.if_desc,t.node_id as ipaddress from customer c ,topo_interface t where c.customer_id=t.customer_id ";
			}
			
			String countsql = "select count(*) from (" + querysql + ") t";
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querysql);
			list = dbm.getObjectList(UserInfo.class, sql);
		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询公司列表时候出错！"+e.getMessage());
		}finally{
			dbm.close();
		}
		return list;
	}
	
	/**
	 * 用户名称和ip数量
	 * @return
	 */
	/*public List getuserinfo() {
		OracleDBManager dbm=new OracleDBManager();
		List<UserInfo> list=null;
		try {
			String querysql = "select c.customer_id as userid,c.customer_name as username,count(proip.ip) as ipsum from customer c ,contract con,product pro,product_ip proip where c.customer_id=con.customer_id and con.contract_id=pro.contract_id and pro.product_id=proip.product_id group by c.customer_name, c.customer_id";
			String countsql = "select count(*) from (" + querysql + ") t";
			
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querysql);
			
			list = dbm.getObjectList(UserInfo.class, sql);
		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询客户名称列表时候出错！"+e.getMessage());
		}finally{
			dbm.close();
		}
		return list;
	}*/
	
	/**
	 * 根据用户名称查询用户的所有ip
	 * @return
	 */
	/*public List getuser_ip(String userid) {
		OracleDBManager dbm=new OracleDBManager();
		List<UserInfo> list=null;
		String querysql="";
		try {
			if(userid!=null && !userid.equals("")) {
				 querysql = "select c.customer_id as userid,c.customer_name as username,proip.ip as ipsum from customer c ,contract con,product pro,product_ip proip where c.customer_id=con.customer_id and con.contract_id=pro.contract_id and pro.product_id=proip.product_id and c.customer_id='"+userid+"'";
			} else {
				 querysql = "select c.customer_id as userid,c.customer_name as username,proip.ip as ipsum from customer c ,contract con,product pro,product_ip proip where c.customer_id=con.customer_id and con.contract_id=pro.contract_id and pro.product_id=proip.product_id ";
			}
			
			String countsql = "select count(*) from (" + querysql + ") t";
			PageFactory pageFactory = new PageFactory();
			String sql = pageFactory.createPageSQL(querysql);
			list = dbm.getObjectList(UserInfo.class, sql);
		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询公司列表时候出错！"+e.getMessage());
		}finally{
			dbm.close();
		}
		return list;
	}*/
	/**
	 * 
	 * @param sql
	 * @param ymd
	 * @param datetype
	 * @return
	 */
	public StringBuffer getsql(StringBuffer sql,String ymd,String datetype) {
		//日期
		if(datetype.equals("day")) {
			sql.append(" AND collecttime >=to_date('"+ymd+" 00:00:00','YYYY/MM/DD hh24:mi:ss') AND collecttime <=to_date('"+ymd+" 23:59:59','YYYY/MM/DD hh24:mi:ss')");
		//月
		} else if(datetype.equals("month")) {
			sql.append(" AND TO_CHAR(collecttime,'YYYY/MM')=TO_CHAR(TO_DATE('"+ymd+"','YYYY/MM'),'YYYY/MM')");
		//年	
		} else if(datetype.equals("year")) {
			sql.append(" AND TO_CHAR(collecttime,'YYYY')=TO_CHAR(TO_DATE('"+ymd+"','YYYY'),'YYYY')");
			
		}
		return sql;
	}
	/**
	 * 
	 * @param avgandsumsql
	 * @param ymd
	 * @param datetype
	 * @return
	 */
	public StringBuffer getavgandsumsql(StringBuffer avgandsumsql,String ymd,String datetype) {
		//日期
		if(datetype.equals("day")) {
			avgandsumsql.append(" AND collecttime >=to_date('"+ymd+" 00:00:00','YYYY/MM/DD hh24:mi:ss') AND collecttime <=to_date('"+ymd+" 23:59:59','YYYY/MM/DD hh24:mi:ss')");
		//月
		} else if(datetype.equals("month")) {
			avgandsumsql.append(" AND TO_CHAR(collecttime,'YYYY/MM')=TO_CHAR(TO_DATE('"+ymd+"','YYYY/MM'),'YYYY/MM')");
		//年	
		} else if(datetype.equals("year")) {
			avgandsumsql.append(" AND TO_CHAR(collecttime,'YYYY')=TO_CHAR(TO_DATE('"+ymd+"','YYYY'),'YYYY')");
			
		}
		return avgandsumsql;
	}
	
	/**
	 * 得到用户流量信息
	 * @param alluseripsql
	 * @param alluseripsql_ip
	 * @param ymd
	 * @param datetype
	 * @return list
	 */
	public List getUserFlow(String alluseripsql,String alluseripsql_ip, String ymd,String datetype) {
		OracleDBManager dbm=new OracleDBManager();
		
		StringBuffer sql= new StringBuffer("select collecttime,round(SUM(utilhdx)*64/300,2) as utilhdx,round(((sum(utilhdx)*8*8)/(sum(ifspeed)*300))*100,2)  as utilhdxperc ,entity from (");
		StringBuffer avgandsumsql= new StringBuffer("select MAX(to_number(utilhdx)) sumthevalue,round(AVG(utilhdx),2) avgthevalue,entity from (");
		List list =null;
		try {
			List alluseriplist = dbm.getObjectList(ProductIpModel.class, alluseripsql);
			//客户端口不存在
			if(alluseriplist.size()==0) {
				return list;
			} 
			
			//数据不为空，将数据进行相同ip的端口排列
			List alluseriplist_ip = dbm.getObjectList(ProductIpModel.class, alluseripsql_ip);
			List listdate = new ArrayList();
			String port = "0";
			for( int i=0;i< alluseriplist_ip.size();i++) {
				ProductIpModel promodel = (ProductIpModel)alluseriplist_ip.get(i);
				for (int j=0;j<alluseriplist.size();j++) {
					ProductIpModel promodelnext = (ProductIpModel)alluseriplist.get(j);
					//System.out.println(promodel.getNODE_ID().equals(promodelnext.getNODE_ID()));
					if(promodel.getNODE_ID().equals(promodelnext.getNODE_ID())) {
						port = port+","+promodelnext.getPORT();
					} 
					
				}
				ProductIpModel pro = new ProductIpModel();
				pro.setNODE_ID(promodel.getNODE_ID());
				pro.setPORT(port);
				listdate.add(pro);
				port="0";
			}
			//AND entity='出口'
			if (listdate.size()>=1) {
				ProductIpModel model = (ProductIpModel)listdate.get(0);
				sql.append(" SELECT  utilhdx,ifspeed,collecttime,subentity,entity FROM portips"+model.getNODE_ID().replaceAll("\\.", "_")+" WHERE subentity in ("+model.getPORT()+")   ");
				//avgandsumsql.append(" SELECT  utilhdx,collecttime,subentity FROM portips"+model.getNODE_ID().replaceAll("\\.", "_")+" WHERE subentity='"+model.getPORT()+"' AND entity='出口' ");
				sql = getsql(sql, ymd, datetype);
				//avgandsumsql = getavgandsumsql(avgandsumsql, ymd, datetype);
				for(int i=1;i< listdate.size();i++) {
					ProductIpModel model1 = (ProductIpModel)listdate.get(i);
					sql.append("UNION ALL SELECT utilhdx,ifspeed,collecttime,subentity,entity FROM portips"+model1.getNODE_ID().replaceAll("\\.", "_")+" WHERE subentity in ("+model1.getPORT()+")  ");
					//avgandsumsql.append("UNION ALL SELECT utilhdx,collecttime,subentity FROM portips"+model1.getNODE_ID().replaceAll("\\.", "_")+" WHERE subentity='"+model1.getPORT()+"' AND entity='出口' ");
					sql = getsql(sql, ymd, datetype);
					//avgandsumsql = getavgandsumsql(avgandsumsql, ymd, datetype);
				}
			}
			
			sql.append(") a  GROUP BY a.collecttime,a.entity  order by a.collecttime");
			avgandsumsql.append(""+sql.toString()+") a group by a.entity ");
			
			System.out.println(sql.toString());
			list = dbm.getObjectList(FlowModel.class, sql.toString());
			if (list.size()==0) {
				return list;
			}
			List avgsumlist = dbm.getObjectList(FlowModel.class, avgandsumsql.toString());
			FlowModel flowModel = (FlowModel)avgsumlist.get(0);
			FlowModel flowModel1 = (FlowModel)avgsumlist.get(1);
			list.add(flowModel);
			list.add(flowModel1);
		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--QueryList--失败"+e.getMessage());
		}finally{
			dbm.close();
		}
		
		return list;
	}
	
	/**
	 * 流量计费
	 */
	public void getUserFlowchargeSql() {
		OracleDBManager dbm=new OracleDBManager();
		List listsql = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		String ym = sdf.format(getLastDate(date)); //得到上个月 yyyy-mm
		String usersql="select  c.customer_id from customer c order by c.customer_id";   //得到所有用户信息
		List userlist = dbm.getObjectList(UserInfo.class, usersql);
		try {
			for (int i=0; i <userlist.size();i++) {
				//流量计费
				FlowAccount flowAccount = new FlowAccount();
				
				UserInfo info = (UserInfo)userlist.get(i);
				flowAccount.setCustomer_id(info.getCustomer_id());
				
				String userflowsql = "select t.if_index as port,t.node_id  from customer c ,topo_interface t where c.customer_id=t.customer_id and t.customer_id='"+info.getCustomer_id()+"' order by c.customer_id";
				String userflowsql_ip="select t.node_id  from customer c ,topo_interface t where c.customer_id=t.customer_id and c.customer_id='"+info.getCustomer_id()+"' group by t.node_id order by t.node_id ";
				
				//获取客户流量信息
				StringBuffer sql= new StringBuffer("select collecttime,round(SUM(utilhdx)*64/300,2) as utilhdx, entity from (");
				//客户流量信息的最大值
				StringBuffer maxvaluesql= new StringBuffer("SELECT max(utilhdx) as utilhdx FROM ( select collecttime,utilhdx,entity from (");
				//一个月的客户流量总条数
				StringBuffer totalcount= new StringBuffer("select count(*) as utilhdx from (");
				List list =null;
				
					List alluseriplist = dbm.getObjectList(ProductIpModel.class, userflowsql);
					//客户没有匹配的端口
					if(alluseriplist.size()==0){
						continue;
					}
					//数据不为空，将数据进行相同ip的端口排列
					List alluseriplist_ip = dbm.getObjectList(ProductIpModel.class, userflowsql_ip);
					List listdate = new ArrayList();
					String port = "0";
					for( int j=0;j< alluseriplist_ip.size();j++) {
						ProductIpModel promodel = (ProductIpModel)alluseriplist_ip.get(j);
						for (int k=0;k<alluseriplist.size();k++) {
							ProductIpModel promodelnext = (ProductIpModel)alluseriplist.get(k);
							//System.out.println(promodel.getNODE_ID().equals(promodelnext.getNODE_ID()));
							if(promodel.getNODE_ID().equals(promodelnext.getNODE_ID())) {
								port = port+","+promodelnext.getPORT();
							} 
							
						}
						ProductIpModel pro = new ProductIpModel();
						pro.setNODE_ID(promodel.getNODE_ID());
						pro.setPORT(port);
						listdate.add(pro);
						port="0";
					}
					//AND entity='出口'
					if (listdate.size()>=1) {
						ProductIpModel model = (ProductIpModel)listdate.get(0);
						sql.append(" SELECT  utilhdx,collecttime,subentity,entity FROM portips"+model.getNODE_ID().replaceAll("\\.", "_")+" WHERE subentity in ("+model.getPORT()+")   and entity='入口' ");
						sql = getsql(sql, ym, "month");
						for(int m=1;m< listdate.size();m++) {
							ProductIpModel model1 = (ProductIpModel)listdate.get(m);
							sql.append("UNION ALL SELECT utilhdx,collecttime,subentity,entity FROM portips"+model1.getNODE_ID().replaceAll("\\.", "_")+" WHERE subentity in ("+model1.getPORT()+")   and entity='入口' ");
							sql = getsql(sql, ym, "month");
						}
					}
					
					maxvaluesql.append(""+sql+") a  GROUP BY a.collecttime,a.entity  order by utilhdx) b  where rownum<=5) c");
					
					sql.append(") a  GROUP BY a.collecttime,a.entity  order by a.collecttime");
					
					totalcount.append(""+sql+")");
					
					List totalcountlist = dbm.getObjectList(FlowModel.class, totalcount.toString());
					
					//得到总的条数
					int total = Integer.parseInt(((FlowModel)totalcountlist.get(0)).getUtilhdx());
					
					flowAccount.setTotalcount(total+"");
					//得到5%的条目数
					int num =(int) (total*0.05);
					// 要获取的数目
					String count = total-num+"";
					flowAccount.setFlowcount(count);
					// 获取的条目sql
					String maxvalue= maxvaluesql.toString().replaceAll("rownum<=5", "rownum<="+count+"");
					List maxvaluelist = dbm.getObjectList(FlowModel.class,maxvalue);
					if(((FlowModel)maxvaluelist.get(0)).getUtilhdx()!=null){
						flowAccount.setUtilhdx(Float.parseFloat(((FlowModel)maxvaluelist.get(0)).getUtilhdx()));
					} else {
						flowAccount.setUtilhdx(0);
					}
					
					flowAccount.setDatetime(ym);
					
					String insertFlowAccount="insert into FLOW_ACCOUNT(customer_id,datetime,totalcount,flowcount,utilhdx,cabinet) "
							+ "VALUES('"+flowAccount.getCustomer_id()+"',"
									+"'"+ flowAccount.getDatetime()+"',"
									+"'"+ flowAccount.getTotalcount()+"',"
									+"'"+ flowAccount.getFlowcount()+"',"
									+"'"+ flowAccount.getUtilhdx()+"',"
									+"'"+ flowAccount.getCabinet()+"'"
									+")";
					
					listsql.add(insertFlowAccount);
					//插入计费流量信息 
					
			}
			insertFlowAccount(listsql);
		}  catch (Exception e) {
			e.printStackTrace();
			logger.error("--QueryList--失败"+e.getMessage());
		}finally{
			dbm.close();
		}
		
	}
	/**
	 * 批量添加客户计费信息
	 * @param listsql
	 */
	public void insertFlowAccount(List listsql) {
		OracleDBManager dbm=new OracleDBManager();
		try {
			dbm.excuteBatchSql(listsql);
		}  catch (Exception e) {
			e.printStackTrace();
			logger.error("--QueryList--失败"+e.getMessage());
		}finally{
			dbm.close();
		}
	}
	/**
	 * 获取上个月YM
	 * @param date
	 * @return
	 */
	 public Date getLastDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }
	
	public static void main(String[] args) {
		FlowDao dao = new FlowDao();
		dao.getUserFlowchargeSql();
	}

}
