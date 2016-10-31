package com.dhcc.bussiness.sxydidc.opcode;

import com.dhcc.common.util.AnyTypeAction;
import com.dhcc.modal.sxydidc.Opcode;
/**
 * @描述：工单号列表查询
 * @作者：GYR
 * @时间：2014-11-25 下午03:14:52
 */
public class OpcodeQueryListAction extends AnyTypeAction<Opcode, OpcodeModel>{

	private static final long serialVersionUID = 1L;
	private OpcodeDao dao = new OpcodeDao();
	
	/**
	 * 页面列表查询
	 */
	public String execute() throws Exception {
		OpcodeModel model = new OpcodeModel();
		String sql = dao.QueryList();
		return super.List(model,sql);
	}
	public String codeInfoQueryList(){
		OpcodeModel model = new OpcodeModel();
		String sql = dao.QueryList();
		return super.ListInfo(model,sql);
	}
	@Override
	public java.util.List<OpcodeModel> getListmodel() {
		// TODO Auto-generated method stub
		return super.getListmodel();
	}

	@Override
	public Integer getPage() {
		// TODO Auto-generated method stub
		return super.getPage();
	}

	@Override
	public Integer getPagesize() {
		// TODO Auto-generated method stub
		return super.getPagesize();
	}

	@Override
	public Integer getRecord() {
		// TODO Auto-generated method stub
		return super.getRecord();
	}

	@Override
	public Integer getTotal() {
		// TODO Auto-generated method stub
		return super.getTotal();
	}
	
}
