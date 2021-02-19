package gr.hua.dit.ds.springmvcdemo1.controller;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.mysql.cj.Query;

import gr.hua.dit.ds.springmvcdemo1.dao.TaskDAO;
import gr.hua.dit.ds.springmvcdemo1.entity.Candidate;
import gr.hua.dit.ds.springmvcdemo1.entity.Task;
import gr.hua.dit.ds.springmvcdemo1.entity.User;


@Controller
@RequestMapping("/tasks")
public class TaskController {

	 @Autowired
	    private TaskDAO taskDAO;
	    
	 
	 	@PreAuthorize("hasRole('ROLE_ADMIN')")
	    @GetMapping("list")
	    public String listAll(Model model) {
	    	
	    	List<Task> tasks = taskDAO.getAllTasks();
	    	
	    	model.addAttribute("tasks", tasks);
	    	
	    	return "tasks";
	    }
	 	
	 	@PreAuthorize("hasRole('ROLE_ADMIN')")
	    @GetMapping("list/requests")
	    public String listRequests(Model model) {
	    	
	    	return "tasks-request";
	    }
	 	
	 	@GetMapping("/info")
	    public String getUser(@RequestParam String id, Model model) {

	            Task tmp = taskDAO.getTaskInfo(id);
	            JSONObject tmp1 = taskDAO.getCandidateInfofromTask(id);
	            if (tmp == null) {
	                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Users Not Found");
	            }
	            System.out.println(tmp);
	            model.addAttribute("selectedInfoTask", tmp);
	            model.addAttribute("candidateInfo", tmp1);
	            return "approve-task";
	    }
	
}
