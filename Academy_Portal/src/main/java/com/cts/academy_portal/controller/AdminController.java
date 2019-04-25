package com.cts.academy_portal.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.cts.academy_portal.bean.Batch;
import com.cts.academy_portal.bean.BatchAllocation;
import com.cts.academy_portal.bean.FacultyCredit;
import com.cts.academy_portal.bean.Login;
import com.cts.academy_portal.bean.Module;
import com.cts.academy_portal.bean.ReportManagement;
import com.cts.academy_portal.bean.SkillMaster;
import com.cts.academy_portal.service.AdminService;
import com.cts.academy_portal.service.LoginService;

@Controller
@RequestMapping("admin")
public class AdminController {

	
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory getSessionFactory;
	
	//used for accessing login service functions
	@Autowired
	private LoginService loginService;
	
	//used for accessing admin service functions
	@Autowired
	private AdminService adminService;
	
	/*This Mapping provides logout facility to the admin
	This invalidate the session and changes the user Status from logged in to logged out */
	@RequestMapping(value="/logout.html")
	public ModelAndView logout(HttpSession httpSession){
		ModelAndView modelAndView = new ModelAndView();
		Login login = (Login)httpSession.getAttribute("login_object");
		System.out.print("Retrieved Attribute Logout method"+login);
		System.out.print(login);
		httpSession.invalidate();
		modelAndView.setViewName("redirect:/logout1.html");
		return modelAndView;
	}
	
	/*This mapping is used for class level mapping at Home button to directly land 
	on the Home page and avoiding the /admin and /faculty mapping  */
	@RequestMapping("/goToHome.html")
	public ModelAndView getHome( HttpSession httpSession){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/adminHome1.html");
		return modelAndView;
	}
	
	/*This Mapping is used for redirecting the admin from the admin home page
	to the register user page where the admin can give necessary details*/
	@RequestMapping("/registerUser.html")
	public ModelAndView addFacultyPage( HttpSession httpSession){
		ModelAndView modelAndView = new ModelAndView();
		Login logi = (Login)httpSession.getAttribute("login_object");
		modelAndView.addObject("user", logi);
		modelAndView.setViewName("registerUser");
		return modelAndView;
	}
	
	/*This Mapping is used for redirecting the admin from the admin home page
	to the add module page where the admin can give necessary details*/
	@RequestMapping("/addModule.html")
	public ModelAndView addModulePage( HttpSession httpSession){
		ModelAndView modelAndView = new ModelAndView();
		Login logi = (Login)httpSession.getAttribute("login_object");
		modelAndView.addObject("user", logi);
		modelAndView.setViewName("addModule");
		return modelAndView;
	}
	
	/*This Mapping is used for redirecting the admin from the admin home page
	to the insert skill page where the admin can give necessary details*/
	@RequestMapping("/insertSkill.html")
	public ModelAndView addskillPage( HttpSession httpSession){
		ModelAndView modelAndView = new ModelAndView();
		Login logi = (Login)httpSession.getAttribute("login_object");
		modelAndView.addObject("user", logi);
		modelAndView.setViewName("insertSkill");
		return modelAndView;
	}
	
	/*This Mapping is used for redirecting the admin from the admin home page
	to the batch allocation page where the admin can give necessary details*/
	@RequestMapping("/batchAllocation.html")
	public ModelAndView batchAllocationPage(@ModelAttribute BatchAllocation batchAllocation, HttpSession httpSession){
		ModelAndView modelAndView = new ModelAndView();
		Login logi = (Login)httpSession.getAttribute("login_object");
		modelAndView.addObject("user", logi);
		modelAndView.addObject("batch", adminService.getAllBatch());
		modelAndView.addObject("faculty", adminService.getAllFaculty());
		modelAndView.addObject("module", adminService.getAllModule());
		modelAndView.setViewName("batchAllocation");
		return modelAndView;
	}
	
	/*This Mapping is used for redirecting the admin from the admin home page
	to the update batch page where the admin can give necessary details*/
	@RequestMapping("/updateBatch.html")
	public ModelAndView batchAllocationUpdatePage(@ModelAttribute BatchAllocation batchAllocation, HttpSession httpSession){
		ModelAndView modelAndView = new ModelAndView();
		Login logi = (Login)httpSession.getAttribute("login_object");
		modelAndView.addObject("user", logi);
		modelAndView.addObject("batchModule", adminService.getAllBatchAllocation());
		modelAndView.setViewName("updateBatch");
		return modelAndView;
	}
	
	/*This Mapping is used for redirecting the admin from the admin home page
	to the generate report page where the admin can give necessary details*/
	@RequestMapping("/generateReport.html")
	public ModelAndView generateReport(@ModelAttribute ReportManagement reportManagement, HttpSession httpSession){
		ModelAndView modelAndView =  new ModelAndView();
		Login logi = (Login)httpSession.getAttribute("login_object");
		modelAndView.addObject("user", logi);
		System.out.println("In gr");

		modelAndView.addObject("batch", adminService.getAllBatchFromReference());
		modelAndView.setViewName("generateReport");
		return modelAndView;
	}
	
	/*This Mapping is used for redirecting the admin from the admin home page
	to the insert batch page where the admin can give necessary details*/
	@RequestMapping("/insertBatch.html")
	public ModelAndView insertBatch( HttpSession httpSession){
		ModelAndView modelAndView =  new ModelAndView();
		Login logi = (Login)httpSession.getAttribute("login_object");
		modelAndView.addObject("user", logi);
		modelAndView.setViewName("insertBatch");
		return modelAndView;
	}
	
	/*This Mapping is used for redirecting the admin from the admin home page
	to the generate report page where the admin can give necessary details*/
	@RequestMapping("/generateReport1.html")
	public ModelAndView getViewBatch(@ModelAttribute ReportManagement reportManagement, HttpSession httpSession){
		ModelAndView modelAndView =  new ModelAndView();
		Login logi = (Login)httpSession.getAttribute("login_object");
		modelAndView.addObject("user", logi);
		System.out.println("In gr1");
		modelAndView.addObject("batch", adminService.getAllBatchFromReference());
		modelAndView.setViewName("generateReport1");
		return modelAndView;
	}
	
	/*This Mapping is used for redirecting the admin from the admin home page
	to the view page where the admin can give necessary details*/
	@RequestMapping("/viewReport.html")
	public ModelAndView viewReport(@ModelAttribute ReportManagement reportManagement, HttpSession httpSession){
		ModelAndView modelAndView = new ModelAndView();
		Login logi = (Login)httpSession.getAttribute("login_object");
		modelAndView.addObject("user", logi);
		System.out.println(reportManagement.getBatchId());
		modelAndView.addObject("facultyCredit", adminService.getFacultyCredit(reportManagement.getBatchId()));
		modelAndView.setViewName("showReport");
		return modelAndView;
	}
	
	
	/*This Mapping is used for receiving data from the update page 
	 where the admin has entered details regarding the batch updation and from this all the data will be transfered to the
	 respective admin service */
	@PostMapping("/generateReport.html")
	public ModelAndView getReport(@ModelAttribute ReportManagement reportManagement ){
		ModelAndView modelAndView = new ModelAndView();
		reportManagement = adminService.getFeedback(reportManagement.getBatchId());
		int weeklyReport;
		int bhsReport;
		int mockTest;
		int innovationProject;
		int total;
		if(reportManagement.getWeeklyReport()==0){
			weeklyReport=0;
		}
		else if(reportManagement.getWeeklyReport()==1 || reportManagement.getWeeklyReport()==2){
			weeklyReport=5;
		}
		else if(reportManagement.getWeeklyReport()==3 || reportManagement.getWeeklyReport()==4){
			weeklyReport=10;
		}
		else {
			weeklyReport=0;
		}
		if("complete".equals(reportManagement.getBhsReport())){
			bhsReport=5;
		}
		else if("notComplete".equals(reportManagement.getBhsReport())){
			bhsReport=0;
		}
		else{
			bhsReport=0;
		}
		if(reportManagement.getMockTest()==0){
			mockTest=0;
		}
		else if(reportManagement.getMockTest()==1){
			mockTest=5;
		}
		else if(reportManagement.getMockTest()==2){
			mockTest=8;
		}
		else if(reportManagement.getMockTest()>3){
			mockTest=10;
		}
		else{
			mockTest=0;
		}
		if("notStarted".equals(reportManagement.getInnovationProject())){
			innovationProject=0;
		}
		else if("inComplete".equals(reportManagement.getInnovationProject())){
			innovationProject=2;
		}
		else if("complete".equals(reportManagement.getInnovationProject())){
			innovationProject=5;
		}
		else{
			innovationProject=0;
		}
		total= weeklyReport+bhsReport+mockTest+innovationProject;
		FacultyCredit facultyCredit = new FacultyCredit(reportManagement.getBatchId(), reportManagement.getFacultyId(), weeklyReport, bhsReport, mockTest, innovationProject,total);
		adminService.insertFacultyCredit(facultyCredit);
		System.out.println(bhsReport+" "+reportManagement.getBhsReport());
		modelAndView.addObject("facultyCredit", adminService.getFacultyCredit(reportManagement.getBatchId()));
		modelAndView.setViewName("showReport");
		return modelAndView;
	   }
	
	 /*This Mapping is used for receiving data from the register user page 
	 where the admin has entered details regarding the new users either a faculty or admin and 
	 from this all the data will be transfered to the respective admin service */
	@PostMapping("/registerUser.html")
	public ModelAndView saveUser(@ModelAttribute Login login,HttpServletRequest request ) throws IOException, ServletException{
		System.out.println(login);
		Login login2 = new Login();
		System.out.println(login.toString());
		ModelAndView modelAndView = new ModelAndView();
		if(loginService.insertUser(login)!=null){
			modelAndView.setViewName("done");
		}
		else{
			modelAndView.setViewName("fails");
		}
		return modelAndView;
	   }
	
	 /*This Mapping is used for receiving data from the add module page 
	 where the admin has entered details regarding the new modules and from this all the data will be transfered to the
	 respective admin service */
	@PostMapping("/addModule.html")
	public ModelAndView saveProduct(@ModelAttribute Module module ){
		ModelAndView modelAndView = new ModelAndView();
		if(adminService.insertModule(module)!=null){
			modelAndView.setViewName("done");
		}
		else{
			modelAndView.setViewName("fails");
		}
		return modelAndView;
	   }
	
	/*This Mapping is used for receiving data from the batch allocation page 
	 where the admin has entered details regarding the batch allocation and from this all the data will be transfered to the
	 respective admin service */
	@PostMapping("/batchAllocation.html")
	public ModelAndView saveBatchAllocation(@ModelAttribute BatchAllocation batchAllocation ){
		ModelAndView modelAndView = new ModelAndView();
		if(adminService.insertBatchAllocation(batchAllocation)!=null){
			modelAndView.setViewName("done");
		}
		else{
			modelAndView.setViewName("fails");
		}
		return modelAndView;
	   }
	
	/*This Mapping is used for receiving data from the insert batch page 
	 where the admin has entered details regarding the new batches and from this all the data will be transfered to the
	 respective admin service */
	@PostMapping("/insertBatch.html")
	public ModelAndView saveBatch(@ModelAttribute Batch batch){
		ModelAndView modelAndView = new ModelAndView();
		if(adminService.insertBatch(batch)!=null){
			modelAndView.setViewName("done");
		}
		else{
			modelAndView.setViewName("fails");
		}
		return modelAndView;
	}
	
	 /*This Mapping is used for receiving data from the insert skill page 
	 where the admin has entered details regarding the skill and from this all the data will be transfered to the
	 respective admin service */
	@PostMapping("/insertSkill.html")
	public ModelAndView saveSkill(@ModelAttribute SkillMaster skillMaster){
		ModelAndView modelAndView = new ModelAndView();
		if(adminService.insertSkill(skillMaster)!=null){
			modelAndView.setViewName("done");
		}
		else{
			modelAndView.setViewName("fails");
		}
		return modelAndView;
	}
	
	 /*This Mapping is used for receiving data from the update page 
	 where the admin has entered details regarding the batch updation and from this all the data will be transfered to the
	 respective admin service */
	@PostMapping("/updateBatch.html")
	public ModelAndView updateBatchAllocation(@ModelAttribute BatchAllocation batchAllocation ){
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(batchAllocation.getBatchAllocationId());
		System.out.println(batchAllocation);
		if(adminService.updateBatchAllocation(batchAllocation.getBatchAllocationId(), batchAllocation.getCloseDate())!=null){
			modelAndView.setViewName("done");
		}
		else{
			modelAndView.setViewName("fails");
		}
		return modelAndView;
	   }

}
