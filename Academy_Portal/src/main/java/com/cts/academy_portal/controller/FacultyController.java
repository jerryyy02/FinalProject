package com.cts.academy_portal.controller;

import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cts.academy_portal.bean.Faculty;
import com.cts.academy_portal.bean.Login;
import com.cts.academy_portal.bean.ReportManagement;
import com.cts.academy_portal.bean.SkillMaster;
import com.cts.academy_portal.bean.SkillSet;
import com.cts.academy_portal.service.AdminService;
import com.cts.academy_portal.service.FacultyService;
import com.cts.academy_portal.service.LoginService;


@Controller
@RequestMapping("faculty")
public class FacultyController {
	
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory getSessionFactory;
	
	//used for accessing admin service functions
	@Autowired
	private AdminService adminService;
	
	//used for accessing faculty service functions
	@Autowired
	private FacultyService facultyService;
	
	//used for accessing login service functions
	@Autowired
	private LoginService loginService;
	
	/*This Mapping provides logout facility to the faculty
	This invalidate the session and changes the user Status from logged in to logged out */
	@RequestMapping(value="/logout.html")
	public ModelAndView logout(HttpSession httpSession){
		ModelAndView modelAndView = new ModelAndView();
		Login login = (Login)httpSession.getAttribute("login_object");
		System.out.print("Retrieved Attribute Logout method"+login);
		String execeute= loginService.sessionClosed(login.getUserName());
		System.out.print(login);
		httpSession.invalidate();
		modelAndView.setViewName("redirect:/logout1.html");
		return modelAndView;
	}
	
	/*This Mapping is used for redirecting the faculty to Home Page*/
	@RequestMapping("/goToHome.html")
	public ModelAndView getHome( HttpSession httpSession){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/facultyHome1.html");
		return modelAndView;
	}

	/*This Mapping is used for redirecting the faculty from the faculty home page
	to the add faculty page where the faculty can register his details*/
	@RequestMapping("/addFaculty.html")
	public ModelAndView addFacultyPage(HttpSession httpSession){
		ModelAndView modelAndView = new ModelAndView();
		Login logi = (Login)httpSession.getAttribute("login_object");
		modelAndView.addObject("user", logi);
		modelAndView.setViewName("addFaculty");
		return modelAndView;
	}
	
	/*This Mapping is used for redirecting the faculty from the faculty home page
	to the add faculty skill page where the faculty can register his skills*/
	@RequestMapping("/insertFacultySkill.html")
	public ModelAndView addskillPage(HttpSession httpSession){
		ModelAndView modelAndView = new ModelAndView();
		Login logi = (Login)httpSession.getAttribute("login_object");
		modelAndView.addObject("user", logi);
		modelAndView.setViewName("insertFacultySkill");
		return modelAndView;
	}
	
	/*This Mapping is used for redirecting the faculty from the faculty home page
	to the report management page where the faculty can give necessary details*/
	@RequestMapping("/reportManagement.html")
	public ModelAndView getReport(@ModelAttribute ReportManagement  reportManagement, HttpSession httpSession){
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("hohohohohoho");
		Login logi = (Login)httpSession.getAttribute("login_object");
		modelAndView.addObject("user", logi);
		System.out.println(adminService.getAllBatch());
		modelAndView.addObject("batch",adminService.getAllBatch());
		modelAndView.addObject("faculty", adminService.getAllFaculty());
		modelAndView.setViewName("reportManagement");
		return modelAndView;
	}
	
	/*This Mapping is used for receiving data from the add faculty page 
	 where the faculty has entered his details and from this all the data will be transfered to the respective faculty service */
	@PostMapping("/addFaculty.html")
	public ModelAndView saveProduct(@ModelAttribute Faculty faculty,HttpSession httpSession ){
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("in mapping");
		System.out.println(faculty.getFacultyId());
		if(facultyService.insertFaculty(faculty)!=null){
			modelAndView.setViewName("done");
		}
		else{
			modelAndView.setViewName("fails");
		}
		return modelAndView;
	   }
	
	/*This Mapping is used for receiving data from the report management page 
	 where the faculty has entered feedback and from this all the data will be transfered to the respective faculty service */
	@PostMapping("/reportManagement.html")
	public ModelAndView putReport(@ModelAttribute ReportManagement reportManagement){
	ModelAndView modelAndView = new ModelAndView();
	System.out.println(reportManagement.getBatchId());
	if(facultyService.insertReport(reportManagement)!=null){
		modelAndView.setViewName("done");
	}
	else{
		modelAndView.setViewName("fails");
	}
	return modelAndView;
}
	
	/*This Mapping is used for receiving data from the insert faculty skill page 
	 where the faculty has entered his skill details and from this all the data will be transfered to the
	 respective faculty service */
	@PostMapping("/insertSkill1.html")
	public ModelAndView saveSkill(@ModelAttribute SkillSet skillSet){
		ModelAndView modelAndView = new ModelAndView();
		if(facultyService.insertSkill(skillSet)!=null){
			modelAndView.setViewName("done");
		}
		else{
			modelAndView.setViewName("fails");
		}
		return modelAndView;
	}
}