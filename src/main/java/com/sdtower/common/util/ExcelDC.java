package com.sdtower.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class ExcelDC {
	
	private String path;
	public ExcelDC(HttpServletRequest request,String filename){
		 path = request.getSession().getServletContext().getRealPath("/")
				+ "excel_template\\"+filename;
	
	}
	
	/*
	 *path 模板路径、list数据源 
	 */
	
	
	public  HSSFWorkbook getTowers(List<Map> list){
	String[] headers = { "towerid", "toweridefined", "towername", "towerwho","cityname","areaname","toweraddress","towerlevelname",
			"towerfee","towerstatename","towertypename","towerlarge","towerj","towermanagername","towermanager","towerinfo","toweradddate"};
	HSSFWorkbook wb = ExcelHelper.exportExcel(path, headers,list);
	
	return wb;
	}
	public  HSSFWorkbook getOrders(List<Map> list){
		String[] headers = {"orderid", "username", "usertele", "userlevelname", "usercityname","userareaname","usercompany","towername","towercityname",
				"towerareaname","toweraddress","towerlevelname","towertypename","towerlarge","towerj","towerw","towermanager","towerinfo","towerstatename","towerfee","towerfirstfee","towerendfee","toweraddtime"};
		HSSFWorkbook wb = ExcelHelper.exportExcel(path, headers,list);
		
		return wb;
		}
	
	public  HSSFWorkbook getMoneys111(List<Map> list){
		String[] headers = { "username", "usertele", "levelname", "cityname","areaname","typename","charge","gettingnow","getnow",
				"allgeting","bankaccount","bankname","bankopen","bankcardid","fee","statename","bz","adddate"};
		HSSFWorkbook wb = ExcelHelper.exportExcel(path, headers,list);
		
		return wb;
		}
	public  HSSFWorkbook getMoneys(List<Map> list){
		String[] headers = {"txid","bankaccount","bankname","bankopen","bankcardid","fee","cityname","areaname","yhdh"};
		HSSFWorkbook wb = ExcelHelper.exportExcel(path, headers,list);
		
		return wb;
		}
	
	

	public  HSSFWorkbook getUsers(List<Map> list){
		String[] headers = { "username","usersex" ,"usertele","wxname","cityname","areaname","usercompany","statename","levelname", "typename","addtime"};
		HSSFWorkbook wb = ExcelHelper.exportExcel(path, headers,list);
		
		return wb;
		}
	
	public  HSSFWorkbook getUsersFee(List<Map> list){
		String[] headers = { "username","usertele","cityname","areaname","levelname", "typename","addtime","charge","getnow","gettingnow","allgeting","score","exp"};
		HSSFWorkbook wb = ExcelHelper.exportExcel(path, headers,list);
		
		return wb;
		}
	
	
	public  HSSFWorkbook getDataTowers(List<Map> list){
		String[] headers = { "towername", "cityname", "areaname", "towerlevel","towerfee","cancelcount","rejectcount","selecount",
				"viewcount","timecount"};
		HSSFWorkbook wb = ExcelHelper.exportExcel(path, headers,list);
		
		return wb;
		}
	

	public  HSSFWorkbook getDataUsers(List<Map> list){
		String[] headers = { "username", "usertele","cityname", "areaname", "gzcount","qdcount","succcount","rejectcount","cancelcount",
				"timecount"};
		HSSFWorkbook wb = ExcelHelper.exportExcel(path, headers,list);
		
		return wb;
		}
	
	
	public  HSSFWorkbook getReportOrders(List<Map> list){
		String[] headers = {"orderid", "username", "usertele", "userlevelname", "usercityname","userareaname","usercompany","towername","towercityname",
				"towerareaname","toweraddress","towerlevelname","towertypename","towerlarge","towerj","towerw","towermanager","towerinfo","towerstatename","towerfee","towerfirstfee","towerendfee","toweraddtime"};
		HSSFWorkbook wb = ExcelHelper.exportExcel(path, headers,list);
		
		return wb;
		}
	
	public  HSSFWorkbook getReportTxs(List<Map> list){
		String[] headers = {"txid", "username", "bankname", "bankopen", "bankcardid","bankbackid","orderid","towerid","towername",
				"towercityname","towerareaname","feetype","fee","adddate"};
		HSSFWorkbook wb = ExcelHelper.exportExcel(path, headers,list);
		
		return wb;
		}
	
	public  HSSFWorkbook getReportUsers(List<Map> list){
		String[] headers = {"wxname", "username", "usersex", "usertele", "usercityname","userareaname","usercompany","userlevel","usertype",
				"favouritecount","ordercount","succcount","failcount","cancelcount","ingcount","charge","getnow","gettingnow","useraddtime"};
		HSSFWorkbook wb = ExcelHelper.exportExcel(path, headers,list);
		
		return wb;
		}
	
	
	public  HSSFWorkbook getTowerOrderHistory(List<Map> list){
		String[] headers = { "orderid", "username", "usertele", "usercityname","userareaname","towername","towerstatename","toweraddtime"};
		HSSFWorkbook wb = ExcelHelper.exportExcel(path, headers,list);
		
		return wb;
		}
}
