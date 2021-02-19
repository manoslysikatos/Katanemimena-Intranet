package gr.hua.dit.ds.springmvcdemo1.dao;

import java.util.List;

import org.json.JSONObject;

import gr.hua.dit.ds.springmvcdemo1.entity.Task;

public interface TaskDAO {
	public List<Task> getAllTasks();
	public int getCount(int id);
	public Task getTaskInfo(String taskID);
	public List<Task> getTaskWaitingForBoardValidation();
	String getTasksPerRole(String role, int id, int status);
	public JSONObject getCandidateInfofromTask(String taskID);
}
