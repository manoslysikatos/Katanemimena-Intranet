package gr.hua.dit.ds.springmvcdemo1.controller;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import gr.hua.dit.ds.springmvcdemo1.dao.TaskDAO;
import gr.hua.dit.ds.springmvcdemo1.dao.UserDAO;
import gr.hua.dit.ds.springmvcdemo1.entity.Candidate;
import gr.hua.dit.ds.springmvcdemo1.entity.Student;
import gr.hua.dit.ds.springmvcdemo1.entity.Task;
import gr.hua.dit.ds.springmvcdemo1.entity.User;



@RestController
@RequestMapping("/api")
public class RESTController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
    private UserDAO userDAO;
	
	@Autowired
    private TaskDAO taskDAO;
	
	@PostMapping("/users")
    public String sayHello(@RequestBody String postParameters) {
		//System.out.println(postParameters);
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Candidate.class)
                .buildSessionFactory();
		JSONObject parameters = new JSONObject(postParameters);
		
		
// create session
		System.out.println("test2");
Session session = factory.getCurrentSession();
System.out.println("test3");
session.beginTransaction();
System.out.println("test4");
//PasswordEncoder encoder = new BCryptPasswordEncoder();
User user =  new User();
String sql;
String sql1;
Query<User> query = session.createQuery("from User where email='"+parameters.getString("email")+"'", User.class);

System.out.println("test5");
// execute the query and get the results list
if(query.getResultList().size()>0) {
	JSONObject json = new JSONObject();
    json.put("status","fail");
    json.put("error","Email Exists");
    return json.toString();
}
try {
        // create the objects
	switch(parameters.getString("role")) {
	  case "student":
		  user = new User(parameters.getString("firstName"),parameters.getString("lastName"),parameters.getString("email"),parameters.getString("phone"), 1, 1, "ROLE_CAN");
	      Candidate candidate = new Candidate(1,0);
	      candidate.setUser(user);
	      //candidate.setUser(user);
	      
	
	 
	        session.save(candidate);
	        sql = String.format("INSERT INTO authorities (username,authority) VALUES('%s',\"ROLE_CAN\");",parameters.getString("email"));
	        
	        
	        sql1 = String.format("INSERT INTO user (username,password,enabled) VALUES('%s','%s',1);",parameters.getString("email"), passwordEncoder.encode(parameters.getString("password")));
	        
	        session.createSQLQuery(sql1).executeUpdate();
	        session.createSQLQuery(sql).executeUpdate();
	        System.out.println(user.getId());
	    break;
	  case "professor":
		  user = new User(parameters.getString("firstName"),parameters.getString("lastName"),parameters.getString("email"),parameters.getString("phone"), 1, 1, "ROLE_SUPER");
		 
	        
	        // save the teacher
	        // NOTE: this will also save teacherProfile because of Cascade
		  	
	        session.save(user);
	        sql = String.format("INSERT INTO authorities (username,authority) VALUES('%s',\"ROLE_SUPER\");",parameters.getString("email"));
	        
	        
	        sql1 = String.format("INSERT INTO user (username,password,enabled) VALUES('%s','%s',1);",parameters.getString("email"), passwordEncoder.encode(parameters.getString("password")));
	        
	        session.createSQLQuery(sql1).executeUpdate();
	        session.createSQLQuery(sql).executeUpdate();
	        System.out.println(user.getId());
		  break;
	  case "staff":
		  user = new User(parameters.getString("firstName"),parameters.getString("lastName"),parameters.getString("email"),parameters.getString("phone"), 1, 1, "ROLE_ADMIN");

	        
	        // save the teacher
	        // NOTE: this will also save teacherProfile because of Cascade
	        session.save(user);
sql = String.format("INSERT INTO authorities (username,authority) VALUES('%s',\"ROLE_SUPER\");",parameters.getString("email"));
	        
	        
	        sql1 = String.format("INSERT INTO user (username,password,enabled) VALUES('%s','%s',1);",parameters.getString("email"), passwordEncoder.encode(parameters.getString("password")));
	        
	        session.createSQLQuery(sql1).executeUpdate();
	        session.createSQLQuery(sql).executeUpdate();
		  break;
	  case "board":
	
	        
	        // save the teacher
	        // NOTE: this will also save teacherProfile because of Cascade
	        
		  user = new User(parameters.getString("firstName"),parameters.getString("lastName"),parameters.getString("email"),parameters.getString("phone"), 1, 1, "ROLE_BOARD");
		  session.save(user);
		  sql = String.format("INSERT INTO authorities (username,authority) VALUES('%s',\"ROLE_SUPER\");",parameters.getString("email"));
	        
	        
	        sql1 = String.format("INSERT INTO user (username,password,enabled) VALUES('%s','%s',1);",parameters.getString("email"), passwordEncoder.encode(parameters.getString("password")));
	        
	        session.createSQLQuery(sql1).executeUpdate();
	        session.createSQLQuery(sql).executeUpdate();
		  break;
	  
	}
/*
        User user = new User("Nikos","Papadopoulos","it@hua.gr", "3456897", 1, 1, "ROLE_CAN");
        Candidate candidate = new Candidate(5,12);
        
        // associate the objects
        user.setCandidate(candidate);
      
       */
        // start a transaction
        
        
        // commit transaction
        session.getTransaction().commit();
        JSONObject json = new JSONObject();
        json.put("status","success");
        return json.toString();

      

}catch(HibernateException r){
    String error = r.getMessage();
    JSONObject json = new JSONObject();
    json.put("status","fail");
    json.put("error",error);
    return json.toString();
} finally {
        factory.close();
        session.close();
        
}
        
    }
	
	@GetMapping("/test")
	public String test(){
		return "test";
	}
	
	
	@PostMapping("/login")
    public String userLogin(@RequestBody String postParameters) {
		System.out.println("We get the request");
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Candidate.class)
                .buildSessionFactory();
		JSONObject parameters = new JSONObject(postParameters);
		
		Session session = factory.getCurrentSession();
		System.out.println("test1");
		session.beginTransaction();
		
		String email =  parameters.getString("email");
		try {
			String sql1 = String.format("Select password from user where username='%s'", email);
	        
			List<String> q = session.createSQLQuery(sql1).list();        
			if(passwordEncoder.matches(parameters.getString("password"), q.get(0))) {
	        	
		       
		       sql1 = String.format("Select * from userInfo where email='%s'", email);
		       List<Object[]> info = session.createSQLQuery(sql1).list();
		       
		      
		       JSONObject json = new JSONObject();
		       json.put("isValid", true);
		       json.put("id", info.get(0)[0]);
		       json.put("firstName", info.get(0)[1]);
		       json.put("lastName", info.get(0)[2]);
		       json.put("email", info.get(0)[3]);
		       json.put("tel", info.get(0)[4]);
		       json.put("status", info.get(0)[5]);
		       json.put("department", info.get(0)[6]);
		       json.put("role", info.get(0)[7]);
		       return json.toString();
	        }else {
	        	JSONObject json = new JSONObject();
			    json.put("status","fail");
			    json.put("error","Not valid");
			    return json.toString();
	        }
		}catch(HibernateException r){
			String error = r.getMessage();
		    JSONObject json = new JSONObject();
		    json.put("status","fail");
		    json.put("error",error);
		    return json.toString();
		}finally {
			factory.close();
			 session.close();
		}

	}
	
	@GetMapping(value="/supervisor/candidates",produces="text/plain;charset=UTF-8")
    public String getCandidatesPerSupervisor(@RequestParam String id) {
		
		
		System.out.println("We get the request");
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Candidate.class)
                .buildSessionFactory();
		
		Session session = factory.getCurrentSession();
	
		session.beginTransaction();
		
		List<Object[]> tmp = userDAO.getUsersPerSuper(id);
		JSONObject response = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(int i=0; i<tmp.size(); i++) {
			JSONObject json = new JSONObject();
			System.out.println(tmp.get(i)[0] + " " +tmp.get(i)[1]); 
			json.put("id", tmp.get(i)[2]);
			json.put("firstName", tmp.get(i)[0]);
			json.put("lastName", tmp.get(i)[1]);
			jsonArray.put(json);
		}
		System.out.println(jsonArray);
		return jsonArray.toString();
	}
	
	@GetMapping(value="points",produces="text/plain;charset=UTF-8")
    public String getPoints(@RequestParam String id) {
		
		
		System.out.println("We get the request");
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Candidate.class)
                .buildSessionFactory();
		
		Session session = factory.getCurrentSession();
	
		session.beginTransaction();
		
		//query2 = currentSession.createQuery("from Task t where t.supervisor_id="+id, Task.class);

		String sql1 = String.format("Select task_points from candidate where userInfo_id=%s",id);
	

		Object tmp = session.createSQLQuery(sql1).getSingleResult();
		JSONObject response = new JSONObject();
		response.put("points", tmp);
		
		return response.toString();
	}
	
	
	//Add New Task
 	@PostMapping("/add-new-task")
 	public String addTask(@RequestBody String postParameters) {
 		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Candidate.class)
                .addAnnotatedClass(Task.class)
                .buildSessionFactory();
		JSONObject parameters = new JSONObject(postParameters);
		
		Session session = factory.getCurrentSession();
		System.out.println("test1");
		session.beginTransaction();
		int points = 0;
		try {
			if(parameters.getInt("type")==1) {
				points = 1;
			}else if(parameters.getInt("type")==2){
				points = 1;
			}else if(parameters.getInt("type")==3) {
				points = 1;
			}
			
	        Query<User> query = session.createQuery("from User tmp where tmp.id='"+parameters.getInt("supervisor")+"'", User.class);


	        // execute the query and get the results list
	        User supervisor = query.getSingleResult();
			Task task = new Task(parameters.getInt("type"), parameters.getString("description"),points,0);
			task.setSupervisor(supervisor);
		    session.save(task);
			
			String sql1 = String.format("INSERT INTO task_per_candidate values(%d,%d,%d)",task.getId(),parameters.getInt("candidate"),0);
	        System.out.println(sql1);
			int q = session.createSQLQuery(sql1).executeUpdate();        
			session.getTransaction().commit();
			if(q==0) {
				JSONObject json = new JSONObject();
				json.put("status","fail");
			    json.put("error","Unable to add Task");
			    return json.toString();
			}else {
			    JSONObject json = new JSONObject();
			    json.put("status","success");
			    return json.toString();
			}
			
     
		    
		}catch(HibernateException r){
			String error = r.getMessage();
		    JSONObject json = new JSONObject();
		    json.put("status","fail");
		    json.put("error",error);
		    return json.toString();
		}finally {
			factory.close();
			 session.close();
		}
		

 		
 	}
 	
 	
 	//Get Task Waiting for Approval from BoardMembers
 	@GetMapping(value="/board-members/tasks/waiting",produces="text/plain;charset=UTF-8")
    public String getTasksBoard() {
		
		
		System.out.println("We get the request");
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Candidate.class)
                .addAnnotatedClass(Task.class)
                .buildSessionFactory();

		List<Task> tmp = taskDAO.getTaskWaitingForBoardValidation();
		
		
		JSONObject response = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for(int i=0; i<tmp.size(); i++) {
			JSONObject json = new JSONObject();
			json.put("id", tmp.get(i).getId());
			json.put("type", tmp.get(i).getType());
			json.put("points", tmp.get(i).getPoints());
			json.put("supervisor", tmp.get(i).getSupervisor().getFirstName()+" "+tmp.get(i).getSupervisor().getLastName());
			jsonArray.put(json);
		}
		System.out.println(jsonArray);
		return jsonArray.toString();
	}
 	
 	@GetMapping(value="/task",produces="text/plain;charset=UTF-8")
    public String getTask(@RequestParam String id) {
		
		
		System.out.println("We get the request");
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Candidate.class)
                .addAnnotatedClass(Task.class)
                .buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Task tmp = taskDAO.getTaskInfo(id);
		String sql1 = String.format("Select userInfo.first_name, userInfo.last_name from userInfo inner join task_per_candidate on userInfo.id=task_per_candidate.candidate_id where task_per_candidate.task_id=%d",tmp.getId());
	    System.out.println(sql1);
		List<Object[]> result = session.createSQLQuery(sql1).list();

		
		

			JSONObject json = new JSONObject();
			json.put("id", tmp.getId());
			json.put("type", tmp.getType());
			json.put("points", tmp.getPoints());
			json.put("description", tmp.getDescription());
			json.put("supervisor", tmp.getSupervisor().getFirstName()+" "+tmp.getSupervisor().getLastName());
			json.put("candidate", result.get(0)[0]+" "+result.get(0)[1]);
			
		
	
		return json.toString();
	}
 	
 	@GetMapping(value="/tasks/list",produces="text/plain;charset=UTF-8")
    public String getTasksPerUser(@RequestParam int id, @RequestParam String role, @RequestParam int status ) {
		
		
		System.out.println("We get the request");
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Candidate.class)
                .buildSessionFactory();

		String tmp = taskDAO.getTasksPerRole(role, id, status);
		
		return tmp;
	}
 	
 	@PostMapping("/tasks/approval")
    public String approveTask(@RequestBody String postParameters) {
		System.out.println("sdf");
 		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Candidate.class)
                .buildSessionFactory();
		JSONObject parameters = new JSONObject(postParameters);
		
		Session session = factory.getCurrentSession();
		System.out.println("test1");
		session.beginTransaction();
		
		String role = parameters.getString("role");
		int taskID = parameters.getInt("id");
		String sql1="";
		if(role.equals("ROLE_ADMIN")) {	
			JSONObject response = new JSONObject();
			sql1 = String.format("Update task set status=3 where id=%d",taskID);
			int resultRow = session.createSQLQuery(sql1).executeUpdate();
			if(resultRow==1) {
				response.put("success", true);
				
			}else {
				response.put("success", false);
				response.put("Error", "Unable to update task status");
				return response.toString();
			}
			sql1 = String.format("update candidate set task_points=task_points+(select points from task where id=%d) where userInfo_id=(select candidate_id from task_per_candidate where task_id=%d);",taskID,taskID);
			
		}else if(role.equals("ROLE_BOARD")) {
			sql1 = String.format("Update task set status=1 where id=%d",taskID);
		}else if(role.equals("ROLE_CAN")) {
			sql1 = String.format("Update task set status=2 where id=%d",taskID);
		}
		System.out.println(sql1);
		JSONObject response = new JSONObject();
		try {
			int resultRow = session.createSQLQuery(sql1).executeUpdate();
			
			if(resultRow==1) {
				response.put("success", true);
				session.getTransaction().commit();
			}else {
				response.put("success", false);
			}
		}finally{
			session.close();
			factory.close();
		}
		return response.toString();
		
	}
 	
 	@PostMapping("/tasks/disapproval")
    public String disapproveTask(@RequestBody String postParameters) {
		System.out.println("sdf");
 		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Candidate.class)
                .buildSessionFactory();
		JSONObject parameters = new JSONObject(postParameters);
		
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		String taskID = parameters.getString("id");
		String sql1="";
		
			JSONObject response = new JSONObject();
			
			sql1 = String.format("Update task set status=4 where id=%s",taskID);
	
	
			
		int resultRow = session.createSQLQuery(sql1).executeUpdate();
		if(resultRow==1) {
			response.put("success", true);	
			session.getTransaction().commit();
		}else {
			response.put("success", false);
			response.put("Error", "Unable to update task status");
		}
		session.close();
		factory.close();
		
		
		
		return response.toString();
	}

}
