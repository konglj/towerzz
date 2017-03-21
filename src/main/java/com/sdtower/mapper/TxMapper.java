package com.sdtower.mapper;

import java.util.List;
import java.util.Map;

import com.sdtower.common.bean.TxDoinfo;
import com.sdtower.common.bean.TxPage;
import com.sdtower.common.bean.TxRecord;
import com.sdtower.common.bean.TxSource;

public interface TxMapper {
	
	public List<TxRecord> getTxs(TxPage txpage);
	
	public int getTxsCount(TxPage txpage);
	
	public TxRecord getTx(String txid);
	
	
	public List<TxSource> getTxSources(String txid);
	
	public List<TxDoinfo> getTxDoinfos(String txid);
	
	public int doinfo(Map map);
	
	public int addTxdoinfo(TxDoinfo txdoinfo);
	
	public int shnocharge(Map map);
	
	public int shnotxsource(Map map);
	
	public int dkyescharge(Map map);
	
	public int dkdoinfo(Map map);
	
	public List<Map> getDcTxs(TxPage txPage);
	
	public TxRecord getTxRecord(String txid);
	

}
