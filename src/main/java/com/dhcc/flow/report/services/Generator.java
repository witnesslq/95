package com.dhcc.flow.report.services;

import java.io.IOException;
import java.io.OutputStream;

import com.dhcc.flow.report.models.Exportable;

public abstract class Generator implements Generatable {

	/* (non-Javadoc)
	 * @see com.dhcc.flow.report.services.Generatable#toOutputStream(java.io.OutputStream)
	 */
	public void toOutputStream(OutputStream os) throws IOException {
		// TODO Auto-generated method stub
		
	}

	private Exportable exportableObj;

	/**
	 * @return the exportableObj
	 */
	public Exportable getExportableObj() {
		return exportableObj;
	}

	/**
	 * @param exportableObj the exportableObj to set
	 */
	public void setExportableObj(Exportable exportableObj) {
		this.exportableObj = exportableObj;
	}


	/**
	 * @param exportableObj
	 */
	public Generator(Exportable exportableObj) {
		super();
		this.exportableObj = exportableObj;
	}

	/**
	 * 
	 */
	public Generator() {
		super();
	}

	
}
