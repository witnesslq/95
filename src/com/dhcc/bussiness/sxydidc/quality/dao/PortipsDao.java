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
	private static final String DEFAULT_DAY_FORMAT = "yyyy-MM-dd";// 默认的日期格式
	private static final String DEFAULT_SDF_SEPERATOR = "-";
	private static final SimpleDateFormat DAY_SDF = new SimpleDateFormat(
			DEFAULT_DAY_FORMAT);
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
		this.dateFormat = DEFAULT_DAY_FORMAT;
		this.date = DAY_SDF.format(new Date());
	}

	/**
	 * 带指定日期格式 1需要检测日期合格的合法性 2需要检测指定日期格式和日期是否匹配
	 * 目前只能检测像yyyy/MM/dd与2016-11-11这种日期间隔的不匹配
	 */
	public PortipsDao(String type, String dateFormat, String date)
			throws ParseException {
		this();
		if (null != dateFormat && date != null) {

			new SimpleDateFormat(dateFormat).parse(date);// 检测date合法性，必需得符合yyyy-MM-dd

			if (DAY.equals(type)) {
				this.tableType = "";
			} else if (MONTH.equals(type)) {
				this.tableType = "day";
			} else if (YEAR.equals(type)) {
				this.tableType = "day";
			} else {
				this.tableType = "";
			}
			this.dateFormat = dateFormat;
			this.date = date;
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
									+ "' and to_char(collecttime,'"
									+ dateFormat + "') = '" + date
									+ "'")
							.append(" union all ");
				}
				if (sql.length() > 0) {
					sql.delete(sql.length() - " union all ".length(),
							sql.length());
				}

				StringBuffer finalSql = new StringBuffer();
				finalSql.append("select round(avg(discardsperc),1) as discardsperc,round(avg(errorsperc),1) as errorsperc,");

				if (YEAR.equals(type)) {
					finalSql.append("to_char(collecttime,'yyyy"
							+ DEFAULT_SDF_SEPERATOR + "mm') as collecttime");
				} else if (MONTH.equals(type)) {
					finalSql.append("to_char(collecttime,'yyyy"
							+ DEFAULT_SDF_SEPERATOR + "mm"
							+ DEFAULT_SDF_SEPERATOR + "dd') as collecttime");
				} else if (DAY.equals(type)) {
					finalSql.append("to_char(collecttime,'yyyy"
							+ DEFAULT_SDF_SEPERATOR + "mm"
							+ DEFAULT_SDF_SEPERATOR
							+ "dd hh24:mi:ss') as collecttime");
				} else {
					finalSql.append("to_char(collecttime,'yyyy"
							+ DEFAULT_SDF_SEPERATOR + "mm"
							+ DEFAULT_SDF_SEPERATOR
							+ "dd hh24:mi:ss') as collecttime");
				}

				finalSql.append(" from ( ").append(sql.toString())
						.append(") group by ");

				if (YEAR.equals(type)) {
					finalSql.append("to_char(collecttime,'yyyy"
							+ DEFAULT_SDF_SEPERATOR + "mm')");
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
									+ "' and to_char(collecttime,'"
									+ dateFormat + "') = '" + date + "'")
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
