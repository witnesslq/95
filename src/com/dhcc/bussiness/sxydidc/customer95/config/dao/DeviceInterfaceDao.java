package com.dhcc.bussiness.sxydidc.customer95.config.dao;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import com.dhcc.bussiness.sxydidc.alarm.HibernateUtil;
import com.dhcc.bussiness.sxydidc.alarm.actions.PaginationData;
import com.dhcc.bussiness.sxydidc.customer95.config.models.DeviceDetail;
import com.dhcc.bussiness.sxydidc.customer95.config.models.InterfaceDetail;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.bussiness.sxydidc.quality.models.TopoHostNode;
import com.dhcc.bussiness.sxydidc.quality.models.TopoInterface;

public class DeviceInterfaceDao {
	
	/*
	 * 分页的设备
	 */
	public List<TopoHostNode> queryPagingListBy(PaginationData<DeviceDetail> data){

		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
//			分页之后的某几个设备
			Query query = session.createSQLQuery("select t.ip_address from (select  ip_address,0 as portCount from Topo_Host_Node t where not exists (select * from Device_Interface d where t.ip_address= d.node_id) "
					+ "union "
					+ "select ip_address,count(*) as portCount from Topo_Host_Node t join Device_Interface d on t.ip_address= d.node_id group by t.ip_address,t.id) t order by  t.portCount desc ")
					.addScalar("ip_address", StringType.INSTANCE)
					.setCacheable(true);
			
			data.setTotalCount(query.list().size());
			query.setFirstResult(data.getCurrentPageStart());
			query.setMaxResults(data.getCountPerPage());
			List<String> rows = query.list();

			List<TopoHostNode> hostList = new ArrayList();
			for(String row:rows){
				TopoHostNode host= new TopoHostNode();
				host.setIpAddress(row);
				
				hostList.add(host);
			}
			transaction.commit();
			return hostList;
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
			throw e;
		}
	
	}
	
	/*
	 * 每个设备所有的端口以及这些端口的占用情况
	 */
	public List<InterfaceDetail> queryUnboundedInterfaceBy(TopoHostNode host){

		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			Query query = session.createSQLQuery("select d.id,d.node_Id ,d.if_Index ,d.if_Desc  ,if_speed  ,if_status  ,if_descr ,null as customer_Id  ,null as startTime,null as endTime from (select * from Device_Interface d where d.node_Id = :ipAddress  and not exists (select * from Topo_Interface t where t.endTime is null and d.node_Id = t.node_Id and d.if_index = t.if_index )) d order by to_number( if_index) asc ")
					.addEntity(InterfaceDetail.class);
			query.setString("ipAddress", host.getIpAddress());
			
			List<InterfaceDetail> list =  query.list();
			
			transaction.commit();
			return list;
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
			throw e;
		}
	
	
	}
	
	/*
	 * 每个设备所有的端口以及这些端口的占用情况
	 */
	public List<InterfaceDetail> queryBoundedInterfaceBy(TopoHostNode host){

		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			Query query = session.createSQLQuery("select d.id,d.node_Id ,d.if_Index ,d.if_Desc  ,if_speed  ,if_status  ,if_descr  ,customer_Id  ,startTime,endTime from (select * from Device_Interface d where d.node_Id = :ipAddress) d  join  (select * from Topo_Interface where endTime is null) t on d.node_Id = t.node_Id and d.if_index = t.if_index")
				
					.addEntity(InterfaceDetail.class);
			query.setString("ipAddress", host.getIpAddress());
			
			List<InterfaceDetail> list =  query.list();
			
			transaction.commit();
			return list;
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
		}
	
		return Collections.EMPTY_LIST;
	}
	
	/*
	 * 设备及其未绑定端口
	 */
	public List<TopoHostNode> queryUnboundedDeviceBy(PaginationData<DeviceDetail> data){

		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
//			分页之后的某几个设备
			Query query = session.createSQLQuery("select t.ip_address from (select  ip_address,0 as portCount from Topo_Host_Node t where not exists (select * from Device_Interface d where t.ip_address= d.node_id) "
					+ "union "
					+ "select ip_address,count(*) as portCount from Topo_Host_Node t join (select * from Device_Interface d where not exists (select * from Topo_interface t where t.endTime is null and t.node_id = d.node_id and t.if_index=d.if_index)) d on t.ip_address= d.node_id group by t.ip_address,t.id) t order by  t.portCount desc ")
					.addScalar("ip_address", StringType.INSTANCE)
					.setCacheable(true);
			
			data.setTotalCount(query.list().size());
			query.setFirstResult(data.getCurrentPageStart());
			query.setMaxResults(data.getCountPerPage());
			List<String> rows = query.list();

			List<TopoHostNode> hostList = new ArrayList();
			for(String row:rows){
				TopoHostNode host= new TopoHostNode();
				host.setIpAddress(row);
				
				hostList.add(host);
			}
			transaction.commit();
			return hostList;
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
			throw e;
		}
	
	}
	
	/*
	 * 设备及其已经绑定的端口
	 */
	public List<TopoHostNode> queryBoundedDeviceBy(PaginationData<DeviceDetail> data,Customer customer){

		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
//			分页之后的某几个设备
			Query query = session.createSQLQuery("select t.ip_address from ( "
					+ "select ip_address,count(*) as portCount from Topo_Host_Node t join  (select * from Device_Interface d where  exists (select * from Topo_interface t where t.customer_id =:customer_id and t.endTime is null and t.node_id = d.node_id and t.if_index=d.if_index)) d on t.ip_address= d.node_id group by t.ip_address,t.id) t order by  t.portCount desc ")
					.addScalar("ip_address", StringType.INSTANCE)
					.setString("customer_id", customer.getCustomerId())
					.setCacheable(true);
			
			data.setTotalCount(query.list().size());
			query.setFirstResult(data.getCurrentPageStart());
			query.setMaxResults(data.getCountPerPage());
			List<String> rows = query.list();

			List<TopoHostNode> hostList = new ArrayList();
			for(String row:rows){
				TopoHostNode host= new TopoHostNode();
				host.setIpAddress(row);
				
				hostList.add(host);
			}
			transaction.commit();
			return hostList;
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
		}
		return Collections.EMPTY_LIST;
	}
}
