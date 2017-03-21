package com.sdtower.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdtower.common.bean.Area;
import com.sdtower.common.bean.City;
import com.sdtower.common.bean.SendNoticeParameter;
import com.sdtower.common.bean.TwNotice;
import com.sdtower.common.bean.TwNoticePage;
import com.sdtower.common.bean.TwNoticeSend;
import com.sdtower.common.bean.UserInfo;
import com.sdtower.common.wxmsg.WXCommon;
import com.sdtower.common.wxmsg.WXInfo;
import com.sdtower.mapper.AreaMapper;
import com.sdtower.mapper.TwnoticeMapper;
import com.sdtower.mapper.UserMapper;
import com.sdtower.service.TwnoticeService;

@Service
public class TwnoticeServiceImpl implements TwnoticeService {

	@Autowired
	private TwnoticeMapper twnoticeMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private AreaMapper areaMapper;

	@Override
	public void getTwNotices(TwNoticePage twNoticePage) {
		int count = twnoticeMapper.getTwNoticesCount(twNoticePage);
		twNoticePage.setPagerowtotal(count);
		twNoticePage.setTwNotices(twnoticeMapper.getTwNotices(twNoticePage));
	}

	@Override
	public TwNotice getTwNotice(int id) {
		return twnoticeMapper.getTwNotice(id);
	}

	@Override
	public TwNotice getTwNoticeInfo(int id) {
		return twnoticeMapper.getTwNoticeInfo(id);
	}

	@Override
	public int insertTwNotice(TwNotice twNotice) {
		return twnoticeMapper.insertTwNotice(twNotice);
	}

	@Override
	public int updateTwNotice(TwNotice twNotice) {
		return twnoticeMapper.updateTwNotice(twNotice);
	}

	@Override
	public int delTwNotice(int id) {
		return twnoticeMapper.delTwNotice(id);
	}

	@Override
	public int sendNotice(SendNoticeParameter parameter) {
		int count = 0;
		List<UserInfo> userInfos = userMapper.getUserInfosByTwNotice(parameter);
		if (userInfos == null || userInfos.size() == 0)
			return 0;
		// 设置发送区域
		String sendAreasStr = new String();
		if (parameter.getAdmintype() == 0) {
			// 获取发送的城市
			List<String> sendCitys = parameter.getCitys();
			// 获取所有城市信息
			Map map = new HashMap();
			map.put("cityids", sendCitys);
			List<City> citys = areaMapper.getCitys(map);
			for (City city : citys) {
				for (String str : sendCitys) {
					if (str.equals(city.getCityid())) {
						sendAreasStr += city.getCityname() + ",";
					}
				}

			}
		} else {
			// 获取发送的区域
			List<Integer> sendAreas = parameter.getAreas();
			// 获取城市下的区域信息
			Map map = new HashMap();
			map.put("areaids", sendAreas);
			List<Area> areas = areaMapper.getAreas(map);
			for (Area area : areas) {
				for (Integer str : sendAreas) {
					if (str == area.getId()) {
						sendAreasStr += area.getCityname() + "-"
								+ area.getAreaname() + ",";
					}

				}
			}
		}

		if (sendAreasStr.endsWith(","))
			sendAreasStr = sendAreasStr.substring(0, sendAreasStr.length() - 1);
		TwNoticeSend twNoticeSend = new TwNoticeSend();
		twNoticeSend.setNoticeid(parameter.getNoticeid());
		twNoticeSend.setUserlevel(parameter.getUserlevel());
		twNoticeSend.setUsertype(parameter.getUsertype());
		twNoticeSend.setSendareas(sendAreasStr);
        twNoticeSend.setSenduser(parameter.getSenduser());
		
		for (UserInfo userInfo : userInfos) {
			// 循环发送
			try {
				boolean result = sendTwNoticeMsg(parameter.getNoticeid(),
						userInfo.getWxid(), WXInfo.notice_add_title,
						parameter.getSendusername(),
						parameter.getNoticetitle(), WXInfo.notice_add_remark);
				if (result)
					count++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		twNoticeSend.setSenduserscount(count);
		int size=twnoticeMapper.insertNoticeSend(twNoticeSend);
		return count;
	}

	private boolean sendTwNoticeMsg(int noticeid, String wxid, String first,
			String key1, String key2, String remark) {
		// 发送订单提交消息模板
		// 获取管理员微信ID
		return WXCommon.sendTwNoticeMsg(noticeid, wxid, first, key1, key2,
				remark);

	}

	@Override
	public List<TwNoticeSend> getTwNoticeSends(int noticeid) {
		return twnoticeMapper.getNoticeSends(noticeid);
	}

}
