package com.dhcc.common.system.post;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.dhcc.modal.system.PageModel;
import com.dhcc.modal.system.Tspost;
import com.opensymphony.xwork2.ActionSupport;



/**
 * 页面要显示的职务列表查询
 * @author GYR
 */
public class PostQueryListAction extends ActionSupport {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	private List listmodal = new ArrayList();// 前台的数据集合
	private Integer page; // 当前页码
	private Integer total; // 
	private Integer pagesize; // 当前页显示的数据行数
	private Integer record;// 当前数据条数
	private String sortname = "postname";// 排序列名
	private String sortorder = "asc";// 排序方向 asc
	private String name;//模糊查询字段

	@Override
	public String execute() throws Exception {
		PostDao md = new PostDao();
		PageModel pm = new PageModel();
		pm.setCurrentPage(page);
		pm.setPerPage(pagesize);
		pm = md.postQueryList(pm,name,sortname,sortorder);
		record = pm.getTotalRecord();
		total = pm.getTotalPage();
		listmodal = pm.getList();
		return SUCCESS;
	}
    public String postQueryUnList() throws Exception {
    	PostDao md = new PostDao();
		PageModel pm = new PageModel();
		List<Tspost> list = new ArrayList<Tspost>();
		list = md.postQueryList(name,sortname,sortorder);
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
	public List getListmodal() {
		return listmodal;
	}

	public void setListmodal(List listmodal) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
