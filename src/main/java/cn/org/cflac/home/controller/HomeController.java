package cn.org.cflac.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HomeController {

	@RequestMapping("/home")
	public String hello(){
		
		return "home";
		
	}
	
	@RequestMapping("/")
	public String hello1(){
		
		return "login";
		
	}
	@RequestMapping("/video")
	public String hello2(){
		
		return "video";
		
	}
}
