package com.dhcc.common.system.email;

import com.dhcc.common.system.email.Dao.EmailDao;
import com.dhcc.common.util.StringUtil;
import com.dhcc.modal.system.Tsuser;

/**
 * 
 * @author sz
 * 发送邮件的接口
 *
 */
public class SendEmail {
	private EmailDao dao = new EmailDao();
	
	/**
	 * 将要发送的短信注入短信表中
	 * @param details  向被通知用户发送的短信，邮件内容
	 * @param mobileNumber 被通知用户手机号码
	 * @param emailAddress 被通知用户电子邮件地址
	 * @param createtime 该条记录产生时间
	 * @param sendtime 发送成功时间（由短信、邮件接口返回）
	 * @param sort 数据类别，用于区分信息来源（1：待办通知  2：服务到期提醒）  int 默认0
	 * @param sendstate  送发状态（0：未发送；1：发送成功；2：发送失败）  int 默认0 
	 * @param sendnumber 重新发送次数  int 默认0
	 * @return
	 */
	 
	public String saveMessage(String details,String mobileNumber,String emailAddress,
			String createtime,String sendtime,int sort,int sendstate,int sendnumber){
		return dao.saveMessage(details, mobileNumber, emailAddress, createtime, sendtime, sort, sendstate, sendnumber);
	}
	/**
	 * 查询用户model
	 * @param urerid
	 * @return
	 */
	public Tsuser getUserModel(String urerid){
		return dao.getUser(urerid);
	}
	/**
	 * 返回短信,邮件内容信息
	 * @param flowid
	 * @param flowname
	 * 该方法不用了
	 * @return
	 */
	public String getdetails1(String flowid,String flowname){
		return "您在山西移动IDC运营管理平台中收到一条单号为："+flowid+" 的"+flowname+"工单，请您及时处理！";
	}
	
	
	/**
	 * 返回短信内容信息
	 * @param flownumber
	 * @param flowname
	 * @return
	 */
	public String getdetailsForSMS(String flownumber,String flowname){
		return "您在山西移动IDC运营管理平台中收到一条单号为："+flownumber+" 的"+flowname+"工单，请您及时处理！";
	}
	
	/**
	 * 返回邮件内容信息
	 * @param flownumber  工单单号
	 * @param flowname 工单类型
	 * @param cusname 客户名称
	 * @param cusmanager 客户经理
	 * @param cmmobile 客户经理手机号
	 * @param submitUser 提交人
	 * @param submitTime 提交时间
	 * @param nextTask 下个节点
	 */
	public String getdetailsForEmail(String flownumber,String flowname,String cusname,String cusmanager,String cmmobile,String submitUser,String submitTime,String nextTask,String custype){
		StringBuffer sb = new StringBuffer();
		sb.append("您在山西移动IDC运营管理平台中收到一条工单：");
		sb.append("工单单号：");sb.append(flownumber);
		sb.append("，工单类型：");sb.append(flowname);
		if(custype != null && custype.equals("ZYYWXX")){
			sb.append("，系统名称：");sb.append(cusname);
			if(!StringUtil.isNullOrEmpty(cusmanager)&&!"null".equals(cusmanager)){
				sb.append("，业务负责人：");sb.append(cusmanager);
			}
			if(!StringUtil.isNullOrEmpty(cmmobile)&&!"null".equals(cmmobile)){
				sb.append("，联系方式：");sb.append(cmmobile);
			}
		}else{
			sb.append("，客户名称：");sb.append(cusname);
			sb.append("，客户经理：");sb.append(cusmanager);
			sb.append("，客户经理电话：");sb.append(cmmobile);
		}
		sb.append("，此工单由");
		sb.append(submitUser);
		sb.append("在");
		sb.append(submitTime);
		sb.append("提交，需要您执行");
		sb.append(nextTask);
		sb.append("操作，请您及时处理！");
		return sb.toString();
	}
	
}
