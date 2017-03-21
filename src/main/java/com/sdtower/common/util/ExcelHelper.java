package com.sdtower.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.sdtower.common.bean.AddTowerInfo;

public class ExcelHelper {

	public static List<AddTowerInfo> readExcel1(String file, int adminid) {

		Workbook info;
		List<AddTowerInfo> list = new ArrayList<AddTowerInfo>();
		try {

			AddTowerInfo towerInfo = null;
			info = Workbook.getWorkbook(new File(file));
			Sheet sheet = info.getSheet(0);
			for (int i = 1; i < sheet.getRows(); i++) {

				towerInfo = new AddTowerInfo();
				towerInfo.setToweradduser(adminid);
				towerInfo.setTowerwho(sheet.getCell(0, i).getContents());
				towerInfo.setToweridefined(sheet.getCell(1, i).getContents());
				towerInfo.setCityname(sheet.getCell(2, i).getContents());
				towerInfo.setAreaname(sheet.getCell(3, i).getContents());
				towerInfo.setTowername(sheet.getCell(4, i).getContents());
				towerInfo.setToweraddress(sheet.getCell(5, i).getContents());
				towerInfo.setTowertype(sheet.getCell(6, i).getContents());
				towerInfo.setTowerlarge(sheet.getCell(7, i).getContents());
				towerInfo.setTowerfee(Integer.valueOf(sheet.getCell(8, i)
						.getContents()));
				towerInfo.setTowerj(sheet.getCell(9, i).getContents());
				towerInfo.setTowerw(sheet.getCell(10, i).getContents());
				towerInfo.setTowermanagername(sheet.getCell(11, i)
						.getContents());
				towerInfo.setTowermanager(sheet.getCell(12, i).getContents());
				towerInfo.setTowerinfo(sheet.getCell(13, i).getContents());
				towerInfo.setTowerlevelname(sheet.getCell(14, i).getContents());
				list.add(towerInfo);
			}

		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;

	}
	
	public static String getStringCellContent(Cell cell){
		String content="";
		
		return content;
	}
	public static String getJWCellContent(Cell cell){
		String content="";
		if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
			content=String.valueOf(cell.getNumericCellValue());
		}
		if(cell.getCellType()==cell.CELL_TYPE_STRING){
			content=cell.getStringCellValue().trim();
		}
		return content;
	}

	public static List<AddTowerInfo> readExcel(String file, int adminid) {

		boolean isExcel2003 = false;
		isExcel2003 = ParamerUtil.isExcel2003(file);

		org.apache.poi.ss.usermodel.Workbook info;
		List<AddTowerInfo> list = new ArrayList<AddTowerInfo>();
		InputStream is = null;

		try {
			is = new FileInputStream(new File(file));
			if (isExcel2003) {
				info = new HSSFWorkbook(is);
			} else {
				info = new XSSFWorkbook(is);
			}

			AddTowerInfo towerInfo = null;
			// info = Workbook.getWorkbook(new File(file));
			org.apache.poi.ss.usermodel.Sheet sheet = info.getSheetAt(0);
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				org.apache.poi.ss.usermodel.Row row = sheet.getRow(i);
				towerInfo = new AddTowerInfo();
				towerInfo.setToweradduser(adminid);

				if (row.getCell(0) != null) {
					row.getCell(0).setCellType(
							org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
					towerInfo.setTowerwho(row.getCell(0).getStringCellValue());
				}

				if(row.getCell(1)!=null){
				row.getCell(1).setCellType(
						org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
				towerInfo.setToweridefined(row.getCell(1).getStringCellValue());
				}

				row.getCell(2).setCellType(
						org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
				towerInfo.setCityname(row.getCell(2).getStringCellValue());

				row.getCell(3).setCellType(
						org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
				towerInfo.setAreaname(row.getCell(3).getStringCellValue());

				row.getCell(4).setCellType(
						org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
				towerInfo.setTowername(row.getCell(4).getStringCellValue());

				row.getCell(5).setCellType(
						org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
				towerInfo.setToweraddress(row.getCell(5).getStringCellValue());

				row.getCell(6).setCellType(
						org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
				towerInfo.setTowertype(row.getCell(6).getStringCellValue());

				row.getCell(7).setCellType(
						org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
				towerInfo.setTowerlarge(row.getCell(7).getStringCellValue());

				row.getCell(8).setCellType(
						org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC);
				towerInfo.setTowerfee((int) row.getCell(8)
						.getNumericCellValue());

				
				
				towerInfo.setTowerj(getJWCellContent(row.getCell(9)));

				towerInfo.setTowerw(getJWCellContent(row.getCell(10)));

				row.getCell(11).setCellType(
						org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
				towerInfo.setTowermanagername(row.getCell(11)
						.getStringCellValue());

				row.getCell(12).setCellType(
						org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
				towerInfo.setTowermanager(row.getCell(12).getStringCellValue());

				row.getCell(13).setCellType(
						org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
				towerInfo.setTowerinfo(row.getCell(13).getStringCellValue());

				row.getCell(14).setCellType(
						org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
				towerInfo.setTowerlevelname(row.getCell(14)
						.getStringCellValue());
				list.add(towerInfo);
			}

		} catch (IOException e) {
			list = new ArrayList<AddTowerInfo>();
			e.printStackTrace();
		} catch (Exception e) {
			list = new ArrayList<AddTowerInfo>();
			e.printStackTrace();
		}

		return list;

	}

	public static List<Map> getTxRecode(String file) {

		Workbook info;
		List<Map> records = new ArrayList<Map>();
		List<AddTowerInfo> list = new ArrayList<AddTowerInfo>();
		try {
			AddTowerInfo towerInfo = null;
			info = Workbook.getWorkbook(new File(file));
			Sheet sheet = info.getSheet(0);
			int row = sheet.getRows();

			for (int i = 1; i < sheet.getRows(); i++) {
				Map map = new HashMap();
				map.put("txid", sheet.getCell(0, i).getContents());
				map.put("fee", sheet.getCell(5, i).getContents());
				String bankbackid = sheet.getCell(8, i).getContents();
				if (bankbackid.equals("AB000000")) {
					map.put("state", 4);
				} else {
					map.put("state", 3);
				}
				map.put("bankbackid", bankbackid);
				records.add(map);
			}

		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return records;

	}

	/**
	 * 根据路径获取模板
	 * 
	 * @author konglingjiao
	 * @param path
	 * @return
	 */
	private static HSSFWorkbook getSheet(String path) {
		HSSFWorkbook workbook = null;
		POIFSFileSystem fs = null;
		try {
			File fi = new File(path);
			if (!fi.exists()) {
				return null;
			}
			fs = new POIFSFileSystem(new FileInputStream(fi));
			workbook = new HSSFWorkbook(fs);
			// sheet = workbook.getSheet(sheetName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return workbook;

	}

	/**
	 * 普通无条件限制的excel
	 * 
	 * @author konglingjiao
	 * 
	 * @param path
	 * @param headers
	 * @param filedTypes
	 * @param dataset
	 * @param start
	 * @return
	 */
	public static HSSFWorkbook exportExcel(String path, String[] headers,
			List<Map> dataset) {
		HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;
		int start = 1;

		workbook = getSheet(path);
		if (workbook == null)
			return null;
		sheet = workbook.getSheetAt(0);
		if (sheet == null)
			return null;
		HSSFRow row = null;

		if (dataset != null) {
			for (Map map : dataset) {
				row = sheet.createRow(start);
				start++;
				for (int i = 0; i < headers.length; i++) {
					HSSFCell cell = row.createCell(i);
					Object obj = (Object) map.get(headers[i]);
					String content = "";
					if (obj != null) {
						content = obj.toString();
					}

					cell.setCellValue(content);

				}
			}
		}

		return workbook;
	}

}
