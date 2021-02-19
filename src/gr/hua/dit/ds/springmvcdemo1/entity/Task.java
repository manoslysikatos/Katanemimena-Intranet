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
@Table(name = "task")
public class Task {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
	
	@Column(name = "type")
	private int type;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "points")
	private int points;

	@Column(name = "status")
	private int status;
	
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "supervisor_id")
	private User supervisor;
	
	@Column(name = "board_member_id")
	private int boardMember;
	
	
	public Task(int id, int type, String description, int points, int status, User supervisor, int boardMember) {
		super();
		this.id = id;
		this.type = type;
		this.description = description;
		this.points = points;
		this.status = status;
		this.supervisor = supervisor;
		this.boardMember = boardMember;
	}
	public Task(int type, String description, int points, int status) {
		super();
		this.type = type;
		this.description = description;
		this.points = points;
		this.status = status;
	
	}


	public Task() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getPoints() {
		return points;
	}


	public void setPoints(int points) {
		this.points = points;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public User getSupervisor() {
		return supervisor;
	}


	public void setSupervisor(User supervisor) {
		this.supervisor = supervisor;
	}




	@Override
	public String toString() {
		return "Task [id=" + id + ", type=" + type + ", description=" + description + ", points=" + points + ", status="
				+ status + ", supervisor=" + supervisor + ", boardMember=" + boardMember + "]";
	}
	
	
}
