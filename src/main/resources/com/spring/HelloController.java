package com.spring;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vo.User;

@Controller
public class HelloController {

	// 需要视图解析器，直接返回视图
	@RequestMapping(value = "/dataModel", method = RequestMethod.GET)
	public String dataModel(User user){
		System.out.println(user.toString());
		return "index";
	}
	
	// 需要配置视图解析器， 如果接收不到数据，请尝试使高版本的 Tomcat 即可
	@RequestMapping("/dataString")
	public String dataString(@RequestParam(value = "uname", required = false)String name, ModelMap modeleMap){
		System.out.println(name);
		return "index";
	}
	
	// 需要视图解析器，返回 ModelAndView
	@RequestMapping(value = "/dataModelAndView", method = RequestMethod.GET)
	public ModelAndView dataModelAndView(User user){
		System.out.println(user.toString());
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "This is ModelAndView demo...");
		mv.setViewName("index");
		return mv;
	}
	
	// 不需要视图解析器，使用 ModelMap 返回数据，只传递一个字符串（ dataString 方法）为什么不行呢？
	// 不行的原因为：springMVC 的版本和 Servlet 版本不一致导致，用高版本的 Tomcat 即可
	@RequestMapping(value = "/dataModelMap", method = RequestMethod.GET)
	public String dataModelMap(@RequestParam Map<String, String> params, ModelMap modeleMap){
		modeleMap.addAttribute("msg", params);
		System.out.println(params.get("name"));
		return "index"; // 如果没有配置视图解析器应该为：return "index.jsp"; 
	}
	
}
