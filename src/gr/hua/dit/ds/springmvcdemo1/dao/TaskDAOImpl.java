package gr.hua.dit.ds.springmvcdemo1.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.ds.springmvcdemo1.entity.Task;
import gr.hua.dit.ds.springmvcdemo1.entity.User;


@Repository
public class TaskDAOImpl implements TaskDAO {
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	@Transactional
    public List<Task> getAllTasks() { 
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // create a query
        //Query<User> query = currentSession.createQuery("from User tmp where tmp.id='"+id+"'", User.class);
        //User tmp = query.getSingleResult();
        Query<Task> query2 = null;
      
        	query2 = currentSession.createQuery("from Task", Task.class);
      
        
		// execute the query and get the results list
        List<Task> tasks = query2.getResultList();

        //return the results
        return tasks;
    }
	

	@Override
	@Transactional
    public String getTasksPerRole(String role, int id, int status) { 
		Session session = sessionFactory.getCurrentSession();
		//query2 = currentSession.createQuery("from Task t where t.supervisor_id="+id, Task.class);
		String sql1 = "";
		
		if(role.equals("ROLE_SUPER")&&status==-1) {		
			System.out.println("1");
			sql1 = String.format("select task.id,userInfo.first_name, userInfo.last_name, task.description, task.type, task.status from userInfo join task_per_candidate on task_per_candidate.candidate_id=userInfo.id join task on task.id=task_per_candidate.task_id where task.supervisor_id=%d",id);
		}else if(role.equals("ROLE_BOARD")&&status!=-1) {
			System.out.println("2");
			sql1 = String.format("select task.id,userInfo.first_name, userInfo.last_name, task.description, task.type, task.status from userInfo join task_per_candidate on task_per_candidate.candidate_id=userInfo.id join task on task.id=task_per_candidate.task_id where task.status=%d",status);
		}else if(role.equals("ROLE_CAN")&&status!=-1) {
			System.out.println("3");
			sql1 = String.format("select task.id,userInfo.first_name, userInfo.last_name, task.description, task.type, task.status from userInfo join task_per_candidate on task_per_candidate.candidate_id=userInfo.id join task on task.id=task_per_candidate.task_id where task_per_candidate.candidate_id=%d and task.status=%d",id, status);
		}else if(role.equals("ROLE_ADMIN")&&status!=-1) {		
			System.out.println("4");
			sql1 = String.format("select task.id,userInfo.first_name, userInfo.last_name, task.description, task.type, task.status, task.points from userInfo join task_per_candidate on task_per_candidate.candidate_id=userInfo.id join task on task.id=task_per_candidate.task_id where task.status=%d",status);
		}
		System.out.println(sql1);
		List<Object[]> tmp = session.createSQLQuery(sql1).list();
		JSONObject response = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(int i=0; i<tmp.size(); i++) {
			JSONObject json = new JSONObject();
			json.put("id", tmp.get(i)[0]);
			json.put("firstName", tmp.get(i)[1]);
			json.put("lastName", tmp.get(i)[2]);
			json.put("description", tmp.get(i)[3]);
			json.put("type", tmp.get(i)[4]);
			json.put("status", tmp.get(i)[5]);
			if(role.equals("ROLE_ADMIN")) {
				json.put("points", tmp.get(i)[6]);
			}
			jsonArray.put(json);
		}
        //return the results
		System.out.println(jsonArray);
        return jsonArray.toString();
    }
	
	
	
	
	@Override
	@Transactional
	 public int getCount(int id) { 
	    	//status = 0? Task Waiting For Approval By a Board Member
			//status = 1? Task Approved
	    	// get current hibernate session
	        Session currentSession = sessionFactory.getCurrentSession();

	        // create a query
	        Query<Task> query = currentSession.createQuery("from Task", Task.class);


	        // execute the query and get the results list
	        List<Task> result = query.getResultList();

	        //return the results
	        return result.size();
	    }
	
	@Override
    @Transactional
    public Task getTaskInfo(String taskID) { 
    	
    	// get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // create a query
        Query<Task> query = currentSession.createQuery("from Task tmp where tmp.id='"+taskID+"'", Task.class);


        // execute the query and get the results list
        Task result = query.getSingleResult();

        //return the results
        return result;
    }
	
	@Override
    @Transactional
	public List<Task> getTaskWaitingForBoardValidation() {
		 Session currentSession = sessionFactory.getCurrentSession();

	        // create a query
	        List<Task> query = currentSession.createQuery("from Task tmp where tmp.status=0", Task.class).list();


		return query;
		
	}
	
	@Override
	@Transactional
	public JSONObject getCandidateInfofromTask(String taskID) {
		Session session = sessionFactory.getCurrentSession();
		//query2 = currentSession.createQuery("from Task t where t.supervisor_id="+id, Task.class);

		String sql1 = String.format("select userInfo.id, userInfo.first_name, userInfo.last_name from userInfo join task_per_candidate on task_per_candidate.candidate_id=userInfo.id where task_per_candidate.task_id=%s",taskID);
	

	List<Object[]> tmp = session.createSQLQuery(sql1).list();
	
		JSONObject json = new JSONObject();
		json.put("id", tmp.get(0)[0]);
		json.put("firstName", tmp.get(0)[1]);
		json.put("lastName", tmp.get(0)[2]);

	
    //return the results
	System.out.println(json);
    return json;
	}
}
