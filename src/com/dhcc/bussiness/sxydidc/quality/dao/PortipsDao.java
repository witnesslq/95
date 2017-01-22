package com.dhcc.bussiness.sxydidc.quality.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dhcc.bussiness.sxydidc.alarm.HibernateUtil;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.bussiness.sxydidc.quality.models.Portips;
import com.dhcc.bussiness.sxydidc.quality.models.TopoInterface;

public class PortipsDao {

	private static final Log log = LogFactory.getLog(PortipsDao.class);
	private String tableType;
	private String dateFormat;
	private String type;
	private String date;
	private static final String DAY_FORMAT = "yyyy-MM-dd";
	private static final String MONTH_FORMAT = "yyyy-MM";
	private static final String YEAR_FORMAT = "yyyy";
	private static final SimpleDateFormat DAY_SDF = new SimpleDateFormat(
			DAY_FORMAT);
	private static final SimpleDateFormat MONTH_SDF = new SimpleDateFormat(
			MONTH_FORMAT);
	private static final SimpleDateFormat YEAR_SDF = new SimpleDateFormat(
			YEAR_FORMAT);
	private static final String DAY = "day";
	private static final String MONTH = "month";
	private static final String YEAR = "year";

	/**
	 * 默认采集点表和按天查询
	 */
	public PortipsDao() {
		super();
		this.tableType = "";
		this.type = DAY;
		this.dateFormat = DAY_FORMAT;
		this.date = DAY_SDF.format(new Date());
	}

	/*
	 * 按照type和date查询对应表date时间的数据
	 * 日期是毫秒数
	 */
	public PortipsDao(String type,  long dateMillisecond) {
		this();
		if (date != null) {//没有传入日期，查询当天的信息

			if (DAY.equals(type)) {
				this.tableType = "";
				this.dateFormat = this.DAY_FORMAT;
				this.date = DAY_SDF.format(new Date(dateMillisecond));
			} else if (MONTH.equals(type)) {
				this.tableType = "day";
				this.dateFormat = this.MONTH_FORMAT;
				this.date = MONTH_SDF.format(new Date(dateMillisecond));
			} else if (YEAR.equals(type)) {
				this.tableType = "day";
				this.dateFormat = this.YEAR_FORMAT;
				this.date = YEAR_SDF.format(new Date(dateMillisecond));
			} else {
				this.tableType = "";
				this.dateFormat = this.DAY_FORMAT;
				this.date = DAY_SDF.format(new Date(dateMillisecond));
			}
			
		}
	}

	private final SessionFactory sessionFactory = HibernateUtil
			.getSessionFactory();

	/*
	 * 客户按采集时间点分组的所有IP的平均丢包率、错包率 客户和IP视角的数据可视化页面使用
	 */
	public List<Portips> queryPortipsForInterface(
			List<TopoInterface> interfaceList) {

		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<Portips> list = new ArrayList();
		try {

			// 客户有IP
			if (interfaceList.size() > 0) {
				StringBuffer sql = new StringBuffer();
				for (TopoInterface topoInterface : interfaceList) {
					String ip = topoInterface.getNodeId()
							.replaceAll("\\.", "_");
					sql.append(
							"select discardsperc,errorsperc,collecttime from portips")
							.append(tableType)
							.append(ip)
							.append(" where entity='出口' and subentity='"
									+ topoInterface.getIfIndex()
									+ "' and collecttime between to_date('"
									+ topoInterface.getStartTime() + "','"
									+ this.DAY_FORMAT
									+ " hh24:mi:ss') and to_date('"
									+ topoInterface.getEndTime() + "','"
									+ this.DAY_FORMAT + " hh24:mi:ss')")
							.append(" union all ");
				}
				if (sql.length() > 0) {
					sql.delete(sql.length() - " union all ".length(),
							sql.length());
				}

				StringBuffer finalSql = new StringBuffer();
				finalSql.append("select round(avg(discardsperc),1) as discardsperc,round(avg(errorsperc),1) as errorsperc,");

				if (YEAR.equals(type)) {
					finalSql.append("to_char(collecttime,'" + this.MONTH_FORMAT
							+ "') as collecttime");
				} else if (MONTH.equals(type)) {
					finalSql.append("to_char(collecttime,'" + this.DAY_FORMAT
							+ "') as collecttime");
				} else if (DAY.equals(type)) {
					finalSql.append("to_char(collecttime,'" + this.DAY_FORMAT
							+ " hh24:mi:ss') as collecttime");
				} else {
					finalSql.append("to_char(collecttime,'" + this.DAY_FORMAT
							+ " hh24:mi:ss') as collecttime");
				}

				finalSql.append(" from ( ").append(sql.toString())
						.append(") group by ");

				if (YEAR.equals(type)) {
					finalSql.append("to_char(collecttime,'" + this.MONTH_FORMAT
							+ "')");
				} else {
					finalSql.append("collecttime");
				}
				finalSql.append(" order by collecttime asc");
				Query query = session.createSQLQuery(finalSql.toString());

				List<Object[]> rows = query.list();

				for (Object[] row : rows) {
					Portips portips = new Portips();
					portips.setDiscardsperc(((BigDecimal) row[0]).toString());
					portips.setErrorsperc(((BigDecimal) row[1]).toString());
					portips.setCollecttime((String) row[2]);
					list.add(portips);
				}

				log.info(sql);
			}

			transaction.commit();

			return list;
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return list;
	}

	/*
	 * 每个客户当天所有IP的平均丢包率，错包率 所有客户概况列表页使用
	 */
	public Portips queryAvgPortipsForInterface(List<TopoInterface> interfaceList) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		Portips portips = new Portips("无", "无");
		try {

			// 客户有IP
			if (interfaceList.size() > 0) {
				StringBuffer sql = new StringBuffer();
				for (TopoInterface topoInterface : interfaceList) {
					String ip = topoInterface.getNodeId()
							.replaceAll("\\.", "_");
					sql.append(
							"select discardsperc,errorsperc,collecttime from portips")
							.append(tableType)
							.append(ip)
							.append(" where entity='出口' and subentity='"
									+ topoInterface.getIfIndex()
									+ "' and collecttime between to_date('"
									+ topoInterface.getStartTime() + "','"
									+ this.DAY_FORMAT
									+ " hh24:mi:ss') and to_date('"
									+ topoInterface.getEndTime() + "','"
									+ this.DAY_FORMAT + " hh24:mi:ss')")
							.append(" union all ");
				}
				if (sql.length() > 0) {
					sql.delete(sql.length() - " union all ".length(),
							sql.length());
				}

				StringBuffer finalSql = new StringBuffer();
				finalSql.append(
						"select round(avg(discardsperc),1) as discardsperc,round(avg(errorsperc),1) as errorsperc from ( ")
						.append(sql.toString()).append(")");
				Query query = session.createSQLQuery(finalSql.toString());

				List<Object[]> list = query.list();

				for (Object[] row : list) {
					portips = new Portips();
					portips.setDiscardsperc((row[0] == null ? "无"
							: (BigDecimal) row[0]).toString());
					portips.setErrorsperc((row[1] == null ? "无"
							: (BigDecimal) row[1]).toString());
				}

				log.info(sql);
			}

			transaction.commit();

			return portips;
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return portips;
	}
}
