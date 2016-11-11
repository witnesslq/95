package com.dhcc.bussiness.sxydidc.customer95.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dhcc.bussiness.sxydidc.customer95.models.Contract;
import com.dhcc.bussiness.sxydidc.customer95.models.Customer;
import com.dhcc.bussiness.sxydidc.customer95.models.Product;

public class CustomerParseUtil {

	private static final Log log = LogFactory.getLog(CustomerParseUtil.class);
	/*
	 * 表头类型
	 */
	private enum Header {
		CONTRACT(new Field[] { ContractHeaderField.CONTRACT_ID,
				ContractHeaderField.CONTRACT_MONEY,
				ContractHeaderField.CONTRACT_PERIOD,
				ContractHeaderField.DISCOUNT,
				ContractHeaderField.LOWEST_BANDWIDTH,
				ContractHeaderField.START_TIME,
				ContractHeaderField.TRAFFIC_CHARGE_RULE,
				ContractHeaderField.TRAFFIC_UNIT_PRICE }), PRODUCT(new Field[] {
				ProductHeaderField.CONTRACT_ID,
				ProductHeaderField.PRODUCT_CHARACTER,
				ProductHeaderField.PRODUCT_COUNT,
				ProductHeaderField.PRODUCT_NAME, ProductHeaderField.IP,
				ProductHeaderField.IS_CHARGE,
				ProductHeaderField.PRODUCT_UNIT_PRICE,
				ProductHeaderField.SETTING_BANDWIDTH }), NONE(new Field[0]);

		private final Field[] fields;

		private Header(Field[] fields) {
			this.fields = fields;
		}

		/*
		 * 判断表头类型
		 */
		public boolean equals(EnhancedRow enhancedRow) {
			List<String> fieldNameList = enhancedRow.getFieldNameList();
			for (String fieldName : fieldNameList) {
				boolean existThisField = false;
				for (Field field : this.fields) {

					if (field.equals(fieldName)) {
						// 存在这个表头域
						existThisField = true;
						break;
					}
				}

				// 只要有一个表头域不存在，判定为不是这种类型的表头
				if (!existThisField){
					enhancedRow.setHeader(false);
					return false;
				}
			}
			enhancedRow.setHeader(true);
			return true;
		}
	}

	private interface Field {
		public abstract boolean equals(String name);
	}

	/*
	 * 合同表头
	 */
	private enum ContractHeaderField implements Field {
		CONTRACT_ID("合同编号"), CONTRACT_MONEY("合同金额"), DISCOUNT("折扣率"), TRAFFIC_UNIT_PRICE(
				"流量单价"), START_TIME("开始时间"), CONTRACT_PERIOD("合同周期"), TRAFFIC_CHARGE_RULE(
				"流量计费规则"), LOWEST_BANDWIDTH("保底带宽");

		private final String name;

		private ContractHeaderField(String name) {
			this.name = name;
		}
		
		@Override
		public boolean equals(String name) {
			return this.name.equals(name);
		}

	}

	/*
	 * 产品表头
	 */
	private enum ProductHeaderField implements Field {
		CONTRACT_ID("合同编号"), PRODUCT_NAME("产品名称"), PRODUCT_CHARACTER("产品性质"), PRODUCT_COUNT(
				"产品数量"), PRODUCT_UNIT_PRICE("产品单价"), IP("IP"), SETTING_BANDWIDTH(
				"配置带宽"), IS_CHARGE("是否计费");

		private final String name;

		private ProductHeaderField(String name) {
			this.name = name;
		}

		@Override
		public boolean equals(String name) {
			return this.name.equals(name);
		}

	}

	private class EnhancedRow {
		Row row;
		boolean isHeader;
		
		/**
		 * @return the isHeader
		 */
		public boolean isHeader() {
			return isHeader;
		}

		/**
		 * @param isHeader the isHeader to set
		 */
		public void setHeader(boolean isHeader) {
			this.isHeader = isHeader;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "EnhancedRow [isHeader=" + isHeader + ", fieldNameList="
					+ fieldNameList + "]";
		}

		/**
		 * @return the fieldNameList
		 */
		public List<String> getFieldNameList() {
			return fieldNameList;
		}

		List<String> fieldNameList = new LinkedList<String>();
		/**
		 * @param row
		 */
		public EnhancedRow(Row row) {
			super();
			this.row = row;
			init();
		}

		private void init() {

			/*
			 * 表头名称 与合同编号 产品名称 产品性质 产品数量 产品单价 是否计费 IP 配置带宽
			 * 相对应，说明这是合同表头，下面的行可能是合同信息行
			 */
			for (Cell cell : row) {
				EnhancedCell enhancedCell = new EnhancedCell(cell);
				String cellValue = enhancedCell.getCellValue();
				
				fieldNameList.add(cellValue);
			}
		}
		private  final Log log = LogFactory.getLog(EnhancedRow.class);
		
		/*
		 * 根据不同类型格式化值，不合法的值转换为null
		 */
		private <Type> Type valueOf(String value,Class<Type> clazz){
			try{
				if(Double.class == clazz){
					return (Type)Double.valueOf(value);
				}else if(BigDecimal.class == clazz){
					return (Type)new BigDecimal(value);
				}else if(Date.class == clazz){
					return (Type)new Date(Long.valueOf(value));
				}
				
			}catch(NumberFormatException e){
//				log.error(e);
			}
			return null;
		}
		public <T> T get(T o){
			if(o instanceof Contract){
				Contract contract = (Contract)o;
				
				contract.setContractId(fieldNameList.get(0));
				
				contract.setContractMoney(valueOf(fieldNameList.get(1),Double.class));
				contract.setDiscount(valueOf(fieldNameList.get(2),Double.class));
				
				contract.setStartTime(valueOf(fieldNameList.get(3),Date.class));
				contract.setContractPeriod(valueOf(fieldNameList.get(4),BigDecimal.class));
				contract.setTrafficChargeRule(fieldNameList.get(5));
				contract.setLowestBandwidth(valueOf(fieldNameList.get(6),Double.class));
				contract.setTrafficUnitPrice(valueOf(fieldNameList.get(7),Double.class));
			}else if(o instanceof Product){
				Product product = (Product) o;
				
				product.setProductId(UUID.randomUUID().toString());
				product.setContract(new Contract(fieldNameList.get(0)));
				product.setProductName(fieldNameList.get(1));
				product.setProductCharacter(fieldNameList.get(2));
				product.setProductCount(valueOf(fieldNameList.get(3),BigDecimal.class));
				product.setProductUnitPrice(valueOf(fieldNameList.get(4),Double.class));
				product.setIsCharge(fieldNameList.get(5));
				Matcher matcher = Pattern.
						compile("(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})").
						matcher(fieldNameList.get(6));
				while(matcher.find()){
					product.addProductIp(matcher.group());
				}
				product.setSettingBandwidth(valueOf(fieldNameList.get(7),Double.class));
			}
			return o;
		}
	}
	
	
	private class EnhancedCell {
		private Cell cell;
		private boolean isMerged;
		private int mergedRowCount;
		private int mergedColumnCount;

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "EnhancedCell [cell=" + getCellValue() + ", isMerged=" + isMerged
					+ ", mergedRowCount=" + mergedRowCount
					+ ", mergedColumnCount=" + mergedColumnCount + "]";
		}

		/**
		 * @return the mergedRowCount
		 */
		public int getMergedRowCount() {
			return mergedRowCount;
		}

		/**
		 * @return the mergedColumnCount
		 */
		public int getMergedColumnCount() {
			return mergedColumnCount;
		}

		private void init() {}

		/**
		 * @param cell
		 */
		public EnhancedCell(Cell cell) {
			super();
			this.cell = cell;
			init();
		}

		/*
		 * 是否是合并的单元格
		 */
		private boolean isMerged() {

			Sheet sheet = cell.getSheet();
			int rowIndex = cell.getRowIndex(), cellIndex = cell
					.getColumnIndex();

			List<CellRangeAddress> list = sheet.getMergedRegions();
			this.isMerged = false; // 不是合并的单元格
			for (CellRangeAddress cellRangeAddress : list) {
				int firstRow = cellRangeAddress.getFirstRow(), lastRow = cellRangeAddress
						.getLastRow(), firstColumn = cellRangeAddress
						.getFirstColumn(), lastColumn = cellRangeAddress
						.getLastColumn();

				boolean isMerged = (rowIndex >= firstRow && rowIndex <= lastRow)
						&& (cellIndex >= firstColumn && cellIndex <= lastColumn);

				if (isMerged) {
					this.isMerged = true;
					this.mergedRowCount = lastRow - firstRow;
					this.mergedColumnCount = lastColumn - firstColumn ;
					break; // 是合并的单元格
				} else {
					continue;
				}

			}

			return this.isMerged;
		}

		/*
		 * 单元格的不同类型的数据
		 */
		private String getCellValue() {

			if (cell == null)
				return "";
			if (cell.getCellTypeEnum() == CellType.STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
				return String.valueOf(cell.getBooleanCellValue());
			} else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
				if(DateUtil.isCellDateFormatted(cell)){
					return String.valueOf(DateUtil.getJavaDate(cell.getNumericCellValue()).getTime());
				}
				return String.valueOf(cell.getNumericCellValue());
			} else if (cell.getCellTypeEnum() == CellType.FORMULA) {
				return cell.getCellFormula();
			}

			return "";
		}
	}

	public void read(String path, String fileName, String fileType) {
		File file = new File(path + fileName + "." + fileType);
		Excel excel = new Excel(){

			@Override
			public boolean isExcelFile() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public Workbook getWorkbook() throws IOException,
					FileNotFoundException {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
		try {
			if(excel.isExcelFile()){
				parse(excel);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/*
	 * 解析Excel，取得 客户及相关联的合同，产品表
	 */
	public List<Customer> parse(Excel excel) throws FileNotFoundException, IOException {
		
		List<Customer> customerList = new LinkedList<Customer>();
	
		Sheet sheet1 = excel.getWorkbook().getSheetAt(0);
		
		
		Customer customer =null;
		Contract contract = null;
		Product product = null;
		
		Header currentHeader = null; //当前的头类型
		for (int i = 0,lastRowNum = sheet1.getLastRowNum();i<=lastRowNum;i++) {
			Row row = sheet1.getRow(i);
			
			EnhancedCell enhancedCell = null;
			for (Cell cell : row) {
//				合并的单元格，表示这一行是客户名称
				 enhancedCell = new EnhancedCell(cell);
				if(enhancedCell.isMerged()) {
					System.out.println(enhancedCell);
					//新用户
					customer = new Customer();
					customerList.add(customer);
					customer.setCustomerId(UUID.randomUUID().toString());
					customer.setCustomerName(enhancedCell.getCellValue());
					break;
				}
			}
//			客户行
			/*if(enhancedCell != null && enhancedCell.isMerged()){
				i += enhancedCell.getMergedRowCount();
				continue;
			}*/
//			判断行是否是表头行
			EnhancedRow enhancedRow = new EnhancedRow(row);
			
			if(Header.CONTRACT.equals(enhancedRow)){	//合同表头行
				
				currentHeader = Header.CONTRACT;
			}else if(Header.PRODUCT.equals(enhancedRow)){	//产品表头行
				currentHeader = Header.PRODUCT;
			}else{
				if(currentHeader == Header.CONTRACT){
//					合同行
				    contract = new Contract();
				    contract = enhancedRow.get(contract);
				    if(customer != null) customer.addContract(contract);
				}else if(currentHeader == Header.PRODUCT){
//					产品行
					product = new Product();
					product = enhancedRow.get(product);
					Iterator<Contract> contracts = customer.getContracts().iterator();
					while(contracts.hasNext()){
						contract = contracts.next();
						if(contract.equals(product.getContract())){
							break;
						}
					}
					contract.addProduct(product);
				}
			}
			System.out.println(enhancedRow);
		}
		
		System.out.println(customerList);
		return customerList;
	}

}
