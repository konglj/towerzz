package com.sdtower.service;

import java.util.List;
import java.util.Map;

import com.sdtower.common.bean.TxPage;
import com.sdtower.common.bean.TxRecord;
import com.sdtower.common.bean.TxSource;

public interface TxService {
	
	public List<TxRecord> getTxs(TxPage txPage);
	
	public TxRecord getTx(String id);
	
	public TxSource getTxSources(String id);

	int shTx(Map map);

	public  int dkTx(Map map);
	
	public List<Map> getDcTxs(TxPage txPage);
	
	public int dkTx(List<Map> list,int adminid);

}
