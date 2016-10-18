package com.dhcc.bussiness.sxydidc.qrcode;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.dhcc.modal.sxydidc.rack.Rsrack;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class QRCodeAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String path = ((ServletContext) ActionContext.getContext().get(ServletActionContext.SERVLET_CONTEXT)).getRealPath("/") + "qrcode" + File.separator;
	
	QRCode qrCode = QRCode.getInstance();
	QRCodeDao dao = new QRCodeDao();
	
	private String roomId;
	private String rackId;
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getRackId() {
		return rackId;
	}
	public void setRackId(String rackId) {
		this.rackId = rackId;
	}
	
	public String queryAllQRCodeImgsByRoomId() {
		StringBuffer json = new StringBuffer();
		json.append("[");
		if(roomId != null) {
			List<Rsrack> racks = dao.queryAllRacksByRoomId(roomId);
			for(int i = 0; i < racks.size(); i++) {
				json.append("{");
				Rsrack rack = racks.get(i);
				File file = new File(path + rack.getId() + ".png");
				if(!file.exists()) {
					qrCode.generateQRCode(path, rack.getId(), rack.getId(), rack.getName(), 500, 500);	
				}
				json.append("\"imgPath\":\"http://192.168.0.162/95/qrcode/" + rack.getId() + ".png\"");
				json.append("}");
				
				if(i + 1 != racks.size()) {
					json.append(",");
				}
			}
		}
		json.append("]");
		
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
	
	public String queryQRCodeImgByRackId() {
		StringBuffer json = new StringBuffer();
		json.append("[");
		if(rackId != null) {
			json.append("{");
			Rsrack rack = dao.queryRackByRackId(rackId);
			File file = new File(path + rack.getId() + ".png");
			if(!file.exists()) {
				qrCode.generateQRCode(path, rack.getId(), rack.getId(), rack.getName(), 500, 500);	
			}
			json.append("\"imgPath\":\"http://192.168.0.162/95/qrcode/" + rack.getId() + ".png\"");
			json.append("}");
		}
		json.append("]");
		
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