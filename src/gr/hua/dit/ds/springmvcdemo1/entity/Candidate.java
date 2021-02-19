package gr.hua.dit.ds.springmvcdemo1.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "candidate")
public class Candidate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int candidateID;
	
	@Column(name = "supervisor")
    private int supervisorID;
	
	

	@Column(name = "task_points")
    private int taskPoints;
	
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="userInfo_id")
	private User user;
	
	
	


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Candidate(int supervisorID, int taskPoints) {
		super();
		this.supervisorID = supervisorID;
		this.taskPoints = taskPoints;
	}

	public Candidate() {
		super();
	}

	public int getCandidateID() {
		return candidateID;
	}

	public void setCandidateID(int candidateID) {
		this.candidateID = candidateID;
	}

	public int getSupervisorID() {
		return supervisorID;
	}

	public void setSupervisorID(int supervisorID) {
		this.supervisorID = supervisorID;
	}

	public int getTaskPoints() {
		return taskPoints;
	}

	public void setTaskPoints(int taskPoints) {
		this.taskPoints = taskPoints;
	}

	@Override
	public String toString() {
		return "candidate [candidateID=" + candidateID + ", supervisorID=" + supervisorID + ", taskPoints=" + taskPoints
				+ "]";
	}
	
	
}
