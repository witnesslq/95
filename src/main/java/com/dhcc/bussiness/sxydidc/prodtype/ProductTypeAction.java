package com.dhcc.bussiness.sxydidc.prodtype;

import java.io.PrintWriter;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.dhcc.common.util.AnyTypeAction;
import com.dhcc.modal.sxydidc.prodtype.Busproducttype;
import com.dhcc.modal.system.PageModel;
/**
 * 产品信息
 * @author Administrator
 *
 */
public class ProductTypeAction extends AnyTypeAction<Busproducttype,ProductTypeModel>{
	private static final long serialVersionUID = 1L;
	private ProductTypeDao dao=new ProductTypeDao();
	private String id;
	private String ids;
	private String dcid;
	private ProductTypeModel productType;
	private String sysLogTitle;//日志标题
	private String sysLogContent;//日志内容
	
	
	public String queryProductType(){
		PageModel pm = new PageModel();
		pm = dao.queryProductType(pm);
		super.setListmodel(pm.getList());
		return SUCCESS;
	}
	public String SelectedProductType(){
		List list = dao.getSelectedProductType(dcid);
		JSONArray json = JSONArray.fromObject(list);
		try {
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			PrintWriter pw = ServletActionContext.getResponse().getWriter();
			pw.print(json);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String SelectedProductBigType(){
		System.out.println(id+"111111111111");
		List list = dao.getSelectedProductBigType(id,dcid);
		JSONArray json = JSONArray.fromObject(list);
		try {
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			PrintWriter pw = ServletActionContext.getResponse().getWriter();
			pw.print(json);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String SelectedProductSmallType(){
		List list = dao.getSelectedProductSmallType(id,dcid);
		JSONArray json = JSONArray.fromObject(list);
		try {
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			PrintWriter pw = ServletActionContext.getResponse().getWriter();
			pw.print(json);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String queryAllProductType(){ 
		List<ProductTypeModel> list = dao.queryAllProductType();
		PrintWriter pw = null;
		try {
			JSONArray json = JSONArray.fromObject(list);//把list转换成json
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
	
	public String saveProductType(){
		String productTypeId=dao.saveProductType(productType);
		productType=dao.queryProductTypeById(productTypeId);
		JSONObject json;
		if(productType!=null){
			json = JSONObject.fromObject(productType);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	
	public String updateProductType(){
		boolean result=dao.updateProductType(productType);
		JSONObject json;
		if(result){
			productType=dao.queryProductTypeById(productType.getId());
			json = JSONObject.fromObject(productType);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	
	public String deleteProductByIds(){
		dao.deleteProductTypeByIds(ids);
		return SUCCESS;
	}
	
	public String queryProductTypeById(){
		productType=dao.queryProductTypeById(id);
		JSONObject json;
		if(productType!=null){
			json = JSONObject.fromObject(productType);
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
	
	public ProductTypeModel getProductType() {
		return productType;
	}

	public void setProductType(ProductTypeModel productType) {
		this.productType = productType;
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

	@Override
	public java.util.List<ProductTypeModel> getListmodel() {
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
	public String getDcid() {
		return dcid;
	}
	public void setDcid(String dcid) {
		this.dcid = dcid;
	}
}
