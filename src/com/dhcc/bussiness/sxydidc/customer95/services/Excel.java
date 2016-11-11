package com.dhcc.bussiness.sxydidc.customer95.services;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;

public interface Excel {

	 boolean isExcelFile();
	 Workbook getWorkbook() throws IOException,FileNotFoundException;
}
