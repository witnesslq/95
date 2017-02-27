package com.dhcc.bussiness.sxydidc.device;

import java.io.PrintWriter;
import java.util.List;
import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.dhcc.common.util.AnyTypeAction;
import com.dhcc.common.util.CreateNum;

import com.dhcc.modal.sxydidc.device.Rsdevice;
import com.dhcc.modal.system.PageModel;
import com.mockrunner.util.common.StringUtil;

/**
 * 设备信息
 */
public class DeviceAction  extends AnyTypeAction<Rsdevice,DeviceModel>{


	private static final long serialVersionUID = 1L;
	private DeviceDao dao = new DeviceDao();
	
	private JSONObject json;
	private String flownumber;
	private String id;
	private String ids;
	private String key;
	private String needRoleFilter="false";//是否需要根据角色过滤综合查询模块里的数据true：需要，false：不需要
	private DeviceModel device;
	private DevicepackModel packdevice;
	private DeviceportModel portdevice;
	private String sysLogTitle;//日志标题
	private String sysLogContent;//日志内容
	
	public String queryDevice(){
		PageModel pm = new PageModel();
		pm.setCurrentPage(super.getPage());
		pm.setPerPage(super.getPagesize());
		if(StringUtil.isEmptyOrNull(key)){
			if(device==null){
				pm = dao.queryDevice(pm,needRoleFilter);
			}else{
				if(StringUtil.isEmptyOrNull(device.getDevicetype())){
					device.setDevicetype("01");
					device.setOwner(1);
				}
				pm = dao.queryDeviceByCondition(pm, device,needRoleFilter);
			}			
		}else{
			pm=dao.quickSearch(pm,key,needRoleFilter);
		}

		super.setRecord(pm.getTotalRecord());
		super.setTotal(pm.getTotalPage());
		super.setListmodel(pm.getList());
		return SUCCESS;
	}
	

	public String queryDeviceInfo(){
		List<DeviceModel> list=null;
		if(StringUtil.isEmptyOrNull(key)){
			if(device==null){
				list = dao.queryDevice(needRoleFilter);
			}else{
				if(StringUtil.isEmptyOrNull(device.getDevicetype())){
					device.setDevicetype("01");
					device.setOwner(1);
				}
				list = dao.queryDeviceByCondition(device,needRoleFilter);
			}			
		}else{
			list=dao.quickSearch(key,needRoleFilter);
		}
		PrintWriter pw = null;
		try {
			JSONArray json = JSONArray.fromObject(list);
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
	
	
	public String queryDeviceProperty(){
		PageModel pm = new PageModel();
		List<DeviceModel> list=dao.queryDeviceProperty(device.getName());
		pm.setList(list);
		if(list!=null&&(!list.isEmpty())){
			json = JSONObject.fromObject(pm);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	public String deleteDeviceByIds(){
		String result=dao.deleteDeviceByIds(ids);
		if(result.trim().equals("01")){
			json = JSONObject.fromObject("{\"result\":\"01\"}");
		}else if(result.trim().equals("02")){
			json = JSONObject.fromObject("{\"result\":\"02\"}");
		}else if(result.trim().equals("03")){
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);		
		return SUCCESS;
	}
	public String deleteDevicepackByIds(){
		String result=dao.deleteDevicepackByIds(ids);
		if(result.trim().equals("01")){
			json = JSONObject.fromObject("{\"result\":\"01\"}");
		}else if(result.trim().equals("02")){
			json = JSONObject.fromObject("{\"result\":\"02\"}");
		}else if(result.trim().equals("03")){
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	
	public String deleteDeviceportByIds(){
		dao.deleteDeviceportByIds(ids);
		return SUCCESS;
	}
	
	public String queryDeviceById(){
		device=dao.queryDeviceById(id);
		if(device!=null){
			json = JSONObject.fromObject(device);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
		
	}
	
	public String queryPortById(){
		portdevice=dao.queryPortById(id);
		if(portdevice!=null){
			json = JSONObject.fromObject(portdevice);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
		
	}
	
	public String queryDevicePackById(){
		packdevice=dao.queryDevicePackById(id);
		if(packdevice!=null){
			json = JSONObject.fromObject(packdevice);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
		
	}	
	
	public String querypackDeviceById(){
		PageModel pm = new PageModel();
		pm.setCurrentPage(super.getPage());
		pm.setPerPage(super.getPagesize());
		pm=dao.querypackDeviceById(id,pm);
		super.setRecord(pm.getTotalRecord());
		super.setTotal(pm.getTotalPage());
		super.setListmodel(pm.getList());
		return SUCCESS;
		
	}
		
	public String queryportByDevpackId(){
		PageModel pm = new PageModel();
		pm.setCurrentPage(super.getPage());
		pm.setPerPage(super.getPagesize());
		if(portdevice==null){
			pm=dao.queryportByDevpackId(pm,id,needRoleFilter);
		}else{
			pm=dao.queryDevicePortByCondition(pm,portdevice,needRoleFilter);
		}
		super.setRecord(pm.getTotalRecord());
		super.setTotal(pm.getTotalPage());
		super.setListmodel(pm.getList());
		return SUCCESS;
		
	}
	public String queryportInfoByDevpackId(){
		List<DeviceportModel> result=null;
		if(portdevice==null){
			 result=dao.queryportByDevpackId(id,needRoleFilter);
		}else{
			 result=dao.queryDevicePortByCondition(portdevice,needRoleFilter);
		}
		PrintWriter pw = null;
		try {
			JSONArray json = JSONArray.fromObject(result);
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
	
	public String updateDevicePack(){
		boolean result=dao.updateDevicePack(packdevice);
		if(result){
			packdevice=dao.queryDevicePackById(packdevice.getId());
			json = JSONObject.fromObject(packdevice);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	
	public String updateDevicePort(){
		boolean result=dao.updateDevicePort(portdevice);
		if(result){
			portdevice=dao.queryPortById(portdevice.getId());
			json = JSONObject.fromObject(portdevice);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	
	
	public String updateDevice(){
		if(StringUtil.isEmptyOrNull(device.getDevicetype())){
			device.setDevicetype("01");
			device.setOwner(1);
		}
		boolean result=dao.updateDevice(device);
		if(result){
			device=dao.queryDeviceById(device.getId());
			json = JSONObject.fromObject(device);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	
	
	public String saveDevice(){
		device.setDevicetype("01");
		device.setOwner(1);
		id=dao.saveDevice(device);
		device=dao.queryDeviceById(id);
		if(device!=null){
			json = JSONObject.fromObject(device);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	public String savepackDevice(){
		dao.savepackDevice(packdevice);
		if(packdevice!=null){
			json = JSONObject.fromObject(packdevice);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	public String saveportDevice(){
		dao.saveportDevice(portdevice);
		if(portdevice!=null){
			json = JSONObject.fromObject(portdevice);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}

	
	public String createDeviceNo(){
		device=new DeviceModel();
		CreateNum createNum=new CreateNum();
		String deviceNO=createNum.getNum("DEVCODE");
		device.setCode(deviceNO);
		JSONObject json;
		if(!StringUtil.isEmptyOrNull(deviceNO)){
			json = JSONObject.fromObject(device);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	public String createpackDeviceNo(){
		packdevice=new DevicepackModel();
		CreateNum createNum=new CreateNum();
		String devicepackNO=createNum.getNum("DEVPACKCODE");
		packdevice.setCode(devicepackNO);
		if(!StringUtil.isEmptyOrNull(devicepackNO)){
			json = JSONObject.fromObject(packdevice);
		}else{
			json = JSONObject.fromObject("{\"result\":\"error\"}");
		}
		setJSON(json);
		return SUCCESS;
	}
	public String createportDeviceNo(){
		portdevice=new DeviceportModel();
		CreateNum createNum=new CreateNum();
		String deviceportNO=createNum.getNum("DEVPORTCODE");
		portdevice.setPortcode(deviceportNO);
		if(!StringUtil.isEmptyOrNull(deviceportNO)){
			json = JSONObject.fromObject(portdevice);
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

	public String getNeedRoleFilter() {
		return needRoleFilter;
	}
	
	public void setNeedRoleFilter(String needRoleFilter) {
		this.needRoleFilter = needRoleFilter;
	}

	public DeviceModel getDevice() {
		return device;
	}

	public void setDevice(DeviceModel device) {
		this.device = device;
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
	
	public DevicepackModel getPackdevice() {
		return packdevice;
	}

	public void setPackdevice(DevicepackModel packdevice) {
		this.packdevice = packdevice;
	}

	@Override
	public java.util.List<DeviceModel> getListmodel() {
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

	public DeviceportModel getPortdevice() {
		return portdevice;
	}

	public void setPortdevice(DeviceportModel portdevice) {
		this.portdevice = portdevice;
	}


	public String getFlownumber() {
		return flownumber;
	}


	public void setFlownumber(String flownumber) {
		this.flownumber = flownumber;
	}
}
