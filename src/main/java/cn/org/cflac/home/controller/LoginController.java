package cn.org.cflac.home.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.org.cflac.entity.User;
import cn.org.cflac.home.service.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login")
	//@ResponseBody
	public String Login(//@RequestParam(value="userLoginname")String userLoginname,
						//@RequestParam(value="userPassword")String userPassword,
						HttpServletRequest req,
						HttpServletResponse response,
						HttpSession session) throws IOException {
		
		String userLoginname = req.getParameter("userLoginname");
		String userPassword = req.getParameter("userPassword");
		//System.out.println(userLoginname);
		//System.out.println(userPassword);
		User user = userService.getUser(userLoginname, userPassword );
		if(user == null) {
			System.out.println("user == null");
			return "login";
		}
		session.setAttribute("name", user.getUserName());
		System.out.println("session.getAttribute('name'):"+session.getAttribute("name"));
		response.sendRedirect("/home");
		return null;
		
	
		/***
		String userLoginname = u.getUserLoginname();
		System.out.println(userLoginname);
		String userPassword = u.getUserPassword();
		System.out.println(userPassword);
		User user = userService.getUser(userLoginname, userPassword);
		if(null == user) {
			return "redirect:/login";
		}
		session.setAttribute("name", user.getUserName());
		return "redirect:/home";		
	}
	*****/
	}
	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpServletRequest request){
		ModelAndView result = new ModelAndView("login");
		HttpSession session = request.getSession();//获取当前session
		if(session!=null){
			System.out.println("1:"+session.getAttribute("name"));
			//User user = (User)session.getAttribute("name");//从当前session中获取用户信息
			session.invalidate();//关闭session
			//System.out.println("2:"+session.getAttribute("name"));
		}
		return result;
		
	}

}
