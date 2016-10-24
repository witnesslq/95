package com.dhcc.common.system.corp;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.dhcc.modal.system.PageModel;
import com.dhcc.modal.system.Tscorp;
import com.dhcc.modal.system.Tspost;
import com.opensymphony.xwork2.ActionSupport;



/**
 * @描述：页面要显示的单位信息列表查询
 * @作者：SZ
 * @时间：2014-10-14 上午09:55:23
 */
public class CorpQueryListAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Tscorp> listmodal = new ArrayList<Tscorp>();// 前台的数据集合
	private Integer page; // 当前页码
	private Integer total; // 
	private Integer pagesize; // 当前页显示的数据行数
	private Integer record;// 当前数据条数
	private String sortname = "corpname";// 排序列名
	private String sortorder = "asc";// 排序方向 asc
	
	@Override
	public String execute() throws Exception {
		CorpDao md = new CorpDao();
		PageModel pm = new PageModel();
		pm.setCurrentPage(page);
		pm.setPerPage(pagesize);
		pm = md.corpQueryList(pm,sortname,sortorder);
		record = pm.getTotalRecord();
		total = pm.getTotalPage();
		listmodal = pm.getList();
		return SUCCESS;
	}
	
	public String deptQueryList() throws Exception {
		CorpDao md = new CorpDao();
		PageModel pm = new PageModel();
		pm.setCurrentPage(page);
		pm.setPerPage(pagesize);
		pm = md.deptQueryList(pm,sortname,sortorder);
		record = pm.getTotalRecord();
		total = pm.getTotalPage();
		listmodal = pm.getList();
		return SUCCESS;
	}
	
	public String deptInfoQueryList() throws Exception {
		CorpDao md = new CorpDao();
		List<Tscorp> list = new ArrayList<Tscorp>();
		list=md.deptInfoQueryList(sortname,sortorder);
		PrintWriter pw = null;
		try {
			JSONArray json = JSONArray.fromObject(list);//鎶妉ist杞崲鎴恓son
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			pw = ServletActionContext.getResponse().getWriter();
			pw.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.flush();
			pw.close();
		}
		return SUCCESS;
	}
	
	
	
	
	public String testAndroid() throws Exception {
		CorpDao md = new CorpDao();
		PageModel pm = new PageModel();
		pm.setCurrentPage(page);
		pm.setPerPage(pagesize);
		pm = md.testAndroid(pm,sortname,sortorder);
		record = pm.getTotalRecord();
		total = pm.getTotalPage();
		listmodal = pm.getList();
		return SUCCESS;
	}
	
	
	public List<Tscorp> getListmodal() {
		return listmodal;
	}

	public void setListmodal(List<Tscorp> listmodal) {
		this.listmodal = listmodal;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public Integer getRecord() {
		return record;
	}

	public void setRecord(Integer record) {
		this.record = record;
	}

	public String getSortname() {
		return sortname;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	public String getSortorder() {
		return sortorder;
	}

	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}

}
