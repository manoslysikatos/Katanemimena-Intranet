package gr.hua.dit.ds.springmvcdemo1.controller;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gr.hua.dit.ds.springmvcdemo1.entity.User;
import gr.hua.dit.ds.springmvcdemo1.dao.TaskDAO;
import gr.hua.dit.ds.springmvcdemo1.dao.UserDAO;
import gr.hua.dit.ds.springmvcdemo1.entity.Candidate;

@Controller
public class HomeController {
	
	@Autowired
    private UserDAO userDAO;
	
	@Autowired
    private TaskDAO taskDAO;
	
	@GetMapping("/")
	public String home(Model model) {

		int supervisors = userDAO.getCount("ROLE_SUPER");
		int admins = userDAO.getCount("ROLE_ADMIN");
		int candidates = userDAO.getCount("ROLE_CAN");
		int members = userDAO.getCount("ROLE_BOARD");
		int tasksCount = taskDAO.getCount(1);
    	
    	model.addAttribute("supervisorsCount", supervisors);
    	model.addAttribute("staffCount", admins);
    	model.addAttribute("candidatesCount", candidates);
    	model.addAttribute("membersCount", members);
    	model.addAttribute("tasksCount", tasksCount);
		return "index";
	}
	
	/*
	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}*/
	
	/*@GetMapping("/login")
	public String login() {
		return "login";
	}*/
	
	
	
	@RequestMapping("/studentform")
	public String showForm() {
		return "student-form";
	}
	
	@GetMapping("/addCandidate")
	public String Candidate() {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Candidate.class)
                .buildSessionFactory();

// create session
Session session = factory.getCurrentSession();

try {
        // create the objects
        User user = new User("Nikos","Papadopoulos","it@hua.gr", "3456897", 1, 1, "ROLE_CAN");
        Candidate candidate = new Candidate(5,12);
        
        // associate the objects
       // user.setCandidate(candidate);
        
        // start a transaction
        session.beginTransaction();
        
        // save the teacher
        // NOTE: this will also save teacherProfile because of Cascade
        session.save(user);
        
        // commit transaction
        session.getTransaction().commit();

        System.out.println("Done!");

} finally {
        factory.close();
        
}
return "tmp";

	}
	
	@RequestMapping(value="/processform", method = RequestMethod.POST)
	public String processFormv2(HttpServletRequest request, Model model) {

	    String theName = request.getParameter("studentName");
	    theName = theName.toUpperCase();

	    String result ="Hello " + theName;

	    model.addAttribute("message",result);

	    return "student";
	}


}
