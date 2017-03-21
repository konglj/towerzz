package com.sdtower.service;

import java.util.List;
import java.util.Map;

import com.sdtower.common.bean.Notice;
import com.sdtower.common.bean.NoticePage;

public interface NoticeService {

	public List<Notice> getNotices(NoticePage NoticePage);
	
	public int getNoticesCount(NoticePage NoticePage);

	public Notice getNotice(int ID);

	public int deleteNotice(Map map);

	public int changeNotice(Map map);

	public int addNotice(Map map);
	
	
}
