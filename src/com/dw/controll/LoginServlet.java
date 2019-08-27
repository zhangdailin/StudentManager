package com.dw.controll;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.dw.dao.impl.UserDaoImpl;
import com.dw.model.User;
import com.dw.util.StringUtil;
/**
 * �û���¼-ҵ���߼�����
 * @author DY1101shaoyuxian
 *
 */
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("进入loginServlet了!");
		HttpSession session=req.getSession();
		req.setCharacterEncoding("utf-8");//�����������
		String username=req.getParameter("username").trim();//��ȡusername��ȥ���ո�
		String password=req.getParameter("password").trim();//��ȡpassword��ȥ���ո�
		String imageValue=req.getParameter("imageValue");
		System.out.println("imageValue:"+imageValue);
		String remember=req.getParameter("remember");
		req.setAttribute("username", username);
		req.setAttribute("password", password);
		req.setAttribute("imageValue", imageValue);
	    String sRand=(String) session.getAttribute("sRand");
	    System.out.println("sRand="+sRand);
	 	 if(StringUtil.isEmpty(username)||StringUtil.isEmpty(password)){
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;
		 }
	 	if(StringUtil.isEmpty(imageValue)){
	 		req.setAttribute("error", "��������֤�룡");
	 		req.getRequestDispatcher("login.jsp").forward(req,resp);
			return;
		}else if(!imageValue.equals(sRand)){
			req.setAttribute("error", "��֤�����");
			req.getRequestDispatcher("login.jsp").forward(req,resp);
			return;
		}
		User user=new User(username,password);
		UserDaoImpl usersdao=new UserDaoImpl();//这句话出错了。
		System.out.println("username:"+username);
		session.setAttribute("username", username);	
		if(usersdao.isLogin(user)){  //
			System.out.println("成功进入isLogin()");
			if("remember-me".equals(remember)){
				rememberMe(username,password,resp);
			}
			resp.sendRedirect("main.jsp");
		}
		else{
			req.setAttribute("error", "�û������������!");
			RequestDispatcher requestdispatcher=req.getRequestDispatcher("login.jsp");
			requestdispatcher.forward(req, resp);
		}
		
	}

	
	private void rememberMe(String userName,String password,HttpServletResponse response){
		Cookie user;
		try {
			user = new Cookie("user",URLEncoder.encode(userName,"UTF-8")+"-"+URLEncoder.encode(password,"UTF-8"));
			user.setMaxAge(1*60*60*24*7);
			response.addCookie(user);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
