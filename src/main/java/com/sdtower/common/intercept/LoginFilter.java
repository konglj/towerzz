package com.sdtower.common.intercept;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sdtower.common.bean.SysUserInfo;
import com.sdtower.common.util.ParamerUtil;

@Controller
public class LoginFilter extends HandlerInterceptorAdapter {

	private String[] strs = { "/login.html", "/assets/", "/image/",
			"/images/", "/go_login.html","/twnotice/noticeinfo.html"

	};

	private boolean checkUrl(String url) {
		for (int i = 0; i < strs.length; i++) {
			if (url.contains(strs[i]))
				return true;
		}
		return false;
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,
			Object handler) {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();
		String url = request.getServletPath();
		String contextPath = request.getContextPath();
		if (url.equals("")) {
			url += "/";
		}
		System.out.println(url);

		if (!checkUrl(url)) {// 若访问后台资源 过滤到login
			
			SysUserInfo userInfo=ParamerUtil.getSysUserFromSesson(request);
			if(userInfo==null||userInfo.getId()==0){

				try {
					response.sendRedirect(contextPath + "/login.html");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}
				
		}
		return true;
	}
}
