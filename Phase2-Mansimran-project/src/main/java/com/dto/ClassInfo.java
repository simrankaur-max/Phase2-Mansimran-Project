package com.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="class_info")
public class ClassInfo {

	@Override
	public String toString() {
		return "ClassInfo [id=" + id + ", name=" + name + ", subject="  + ", student="  + ", teacher="
				+ teacher + "]";
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL) 
	private List<Subject> subject;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Student> student;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Teacher teacher;
//	@OneToOne(targetEntity=Student.class,cascade=CascadeType.ALL)  
//	private Student student;
	
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Subject> getSubject() {
		return subject;
	}

	public void setSubject(List<Subject> subject) {
		this.subject = subject;
	}

	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}

	public ClassInfo() {
		// TODO Auto-generated constructor stub
	}

}
