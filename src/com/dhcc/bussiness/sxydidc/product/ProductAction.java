package com.dhcc.bussiness.sxydidc.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.dhcc.bussiness.sxydidc.rsdatacenter.RsdatacenterDao;
import com.dhcc.bussiness.sxydidc.rsdatacenter.RsdatacenterModel;
import com.dhcc.common.util.AnyTypeAction;
import com.dhcc.modal.sxydidc.product.Busproduct;
import com.dhcc.modal.system.PageModel;
import com.mockrunner.util.common.StringUtil;
import com.opensymphony.xwork2.ActionContext;
/**
 * 产品信息
 * @author Administrator
 *
 */
public class ProductAction extends AnyTypeAction<Busproduct,ProductModel>{
	private static final long serialVersionUID = 1L;
	private ProductDao dao=new ProductDao();
	private RsdatacenterDao dcdao = new RsdatacenterDao();
	private String id;
	private String ids;
	private String key;
	private ProductModel product;
	private String needRoleFilter="flase";//是否需要根据角色过滤综合查询模块里的数据true：需要，false：不需要
	private String sysLogTitle;//日志标题
	private String sysLogContent;//日志内容
	private String flownumber;//工单号
	private String modelname;
	private String userid = ActionContext.getContext().getSession().get("userid").toString();
	private String dcid;
	RsdatacenterModel rsdata  = new RsdatacenterModel();
	public String queryProduct(){
		PageModel pm = new PageModel();
		pm.setCurrentPage(super.getPage());
		pm.setPerPage(super.getPagesize());
		if(com.mockrunner.util.common.StringUtil.isEmptyOrNull(key)){
			if(product==null){
				pm = dao.queryProduct(pm);
			}else{
				pm = dao.queryProductByCondition(pm, product);
			}			
		}else{
			pm=dao.quickSearch(pm, key);
		}

		super.setRecord(pm.getTotalRecord());
		super.setTotal(pm.getTotalPage());
		super.setListmodel(pm.getList());
		return SUCCESS;
	}
	
	
	public String queryProductbyDcid(){
		PageModel pm = new PageModel();
		pm.setCurrentPage(super.getPage());
		pm.setPerPage(super.getPagesize());
		
		if(com.mockrunner.util.common.StringUtil.isEmptyOrNull(key)){
			if(product==null){
				pm = dao.queryProductByDcid(pm,needRoleFilter);
			}else{
				pm = dao.queryProductByConditionByDcid(pm, product,needRoleFilter);
			}			
		}else{
			pm=dao.quickSearch(pm, key);
		}

		super.setRecord(pm.getTotalRecord());
		super.setTotal(pm.getTotalPage());
		super.setListmodel(pm.getList());
		return SUCCESS;
	}
	
	
	public String queryPro() {
		System.out.println("++++++++++开始查询产品信息+++++++");
		System.out.println("++++++++++"+modelname);
		if(StringUtil.isEmptyOrNull(modelname)){
		List<ProductModel> list = dao.queryPro(dcid);
		JSONArray json = JSONArray.fromObject(list);
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		try {
			PrintWriter writer = ServletActionContext.getResponse().getWriter();
			writer.print(json);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
		}else{
			List<ProductModel> list = dao.queryquickPro(modelname);
			JSONArray json = JSONArray.fromObject(list);
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			try {
				PrintWriter writer = ServletActionContext.getResponse().getWriter();
				writer.print(json);
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return SUCCESS;
		}
		
	}
	public String queryProByflownum() {
		System.out.println("++++++++++开始查询产品信息+++++++");
		List<ProductModel> list = dao.queryProByflownum(flownumber);
		JSONArray json = JSONArray.fromObject(list);
		System.out.println("---------------------"+json+"----------------------");
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		try {
			PrintWriter writer = ServletActionContext.getResponse().getWriter();
			writer.print(json);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String queryProBytypeid() {
		System.out.println("++++++++++开始查询产品类型信息+++++++");
		System.out.println("++++++++++开始查询产品类型信息+++++++"+id);
		String code= dao.findcodebyid(id);
		System.out.println("++++++++++开始查询产品类型信息+++++++"+code);
		List list = dao.queryProBytypeid(code,dcid);
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		try {
			PrintWriter writer = ServletActionContext.getResponse().getWriter();
			writer.print(JSONArray.fromObject(list));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String saveProduct(){
		String productId=dao.saveProduct(product);
		product=dao.queryProductById(productId);
		JSONObject json;
		if(product!=null){
			json = JSONObject.fromObject(product);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	
	public String updateProduct(){
		boolean result=dao.updateProduct(product);
		JSONObject json;
		if(result){
			product=dao.queryProductById(product.getId());
			json = JSONObject.fromObject(product);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	
	public String deleteProductByIds(){
		dao.deleteProductByIds(ids);
		return SUCCESS;
	}
	
	public String queryProductById(){
		product=dao.queryProductById(id);
		JSONObject json;
		if(product!=null){
			json = JSONObject.fromObject(product);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
		
	}
	
	private void setJSON(JSONObject json){
		try {
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			PrintWriter pw = ServletActionContext.getResponse().getWriter();
			pw.print(json);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public String getSysLogTitle() {
		return sysLogTitle;
	}

	public void setSysLogTitle(String sysLogTitle) {
		this.sysLogTitle = sysLogTitle;
	}

	public String getSysLogContent() {
		return sysLogContent;
	}

	public void setSysLogContent(String sysLogContent) {
		this.sysLogContent = sysLogContent;
	}
	
	public String getFlownumber() {
		return flownumber;
	}
	public void setFlownumber(String flownumber) {
		this.flownumber = flownumber;
	}

	@Override
	public java.util.List<ProductModel> getListmodel() {
		return super.getListmodel();
	}

	@Override
	public Integer getPage() {
		return super.getPage();
	}

	@Override
	public Integer getPagesize() {
		return super.getPagesize();
	}

	@Override
	public Integer getRecord() {
		return super.getRecord();
	}

	@Override
	public Integer getTotal() {
		return super.getTotal();
	}
	public String getModelname() {
		return modelname;
	}
	public void setModelname(String modelname) {
		this.modelname = modelname;
	}
	public String getDcid() {
		return dcid;
	}
	public void setDcid(String dcid) {
		this.dcid = dcid;
	}


	public String getNeedRoleFilter() {
		return needRoleFilter;
	}


	public void setNeedRoleFilter(String needRoleFilter) {
		this.needRoleFilter = needRoleFilter;
	}
}
