package com.sdtower.mapper;

import java.util.List;
import java.util.Map;

import com.sdtower.common.bean.Notice;
import com.sdtower.common.bean.NoticePage;

public interface NoticeMapper {
	
	public List<Notice> getNotices(NoticePage noticePage);
	
	public int getNoticesCount(NoticePage NoticePage);
	
	public Notice getNotice(int ID);
	
	public int changeNotice(Map map);
	
	public int addNotice(Map map);
	
	public int delNotice(Map map);
	
}

