package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.dao.HibernateDaoImpl;
import com.dto.ClassInfo;
import com.dto.Student;
import com.dto.Subject;
import com.dto.Teacher;

/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private List<String> classList=new ArrayList<String>() {{
    	add("Class 1");
    	add("Class 2");
    }};   
    private List<String> studentList=new ArrayList<String>() {{
    	add("Simran");
    	add("Gagan");
    	add("Arti");
    	add("Jagjot");
    	add("Harjot");
    	add("Verma");
    }}; 
    private List<String> subjectsList=new ArrayList<String>() {{
    	add("English");
    	add("Hindi");
    	add("Maths");
    }}; 
    
    private List<String> teacherList=new ArrayList<String>() {{
    	add("Pavan");
    	add("Nisha");
    	add("Dharmendra");
    }}; 
    private  HibernateDaoImpl hibernateDaoImpl=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        	hibernateDaoImpl=new HibernateDaoImpl();
        	List<Student> studentClass1List= studentList.stream().map(studentItem->{
        		Student student=new Student();
        		student.setName(studentItem);
        		return student;
        	}).collect(Collectors.toList()).subList(0, 3) ;
        	
        	List<Student> studentClass2List= studentList.stream().map(studentItem->{
        		Student student=new Student();
        		student.setName(studentItem);
        		return student;
        	}).collect(Collectors.toList()).subList(3,6) ;
        	
        	List<Subject> subjectClassList = subjectsList.stream().map(subjectItem->{
	        	Subject subjectmapItem=new Subject();
	        	subjectmapItem.setName(subjectItem);
	        	return subjectmapItem;
        	}).collect(Collectors.toList());
        	
        	 AtomicInteger i=new AtomicInteger(0);
        	classList.stream().forEach(classItem->{
        		ClassInfo classInfo=new ClassInfo();
        		classInfo.setName(classItem);
        		classInfo.setStudent( i.get()==0? studentClass1List : studentClass2List);
        		Teacher teacher = new Teacher();
        		teacher.setName(teacherList.get(i.getAndIncrement()));
        		classInfo.setTeacher(teacher);
//        		classInfo.setSubject(subjectClassList);
        		hibernateDaoImpl.addClassInfo(classInfo);
        			
        	});
        	
        	System.out.println("Class data"+ hibernateDaoImpl.listClassInfo());
        	
//        classInfo.setStudent(student);
       
      
        // TODO Auto-generated constructor stub
    }
    public void init() {

    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	        PrintWriter out=response.getWriter();
	        out.println("response");
//		doPost(request, response);
	}
// Servlet/classId=""
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
	    String uname = request.getParameter("username");
	    String password = request.getParameter("password");
	    PrintWriter out=response.getWriter();
	    HttpSession session = request.getSession();
	    if(uname!=null && password!=null) {
	    	session.setAttribute("username", uname);
	    	session.setAttribute("password", password);
	    }
	    uname=(String)session.getAttribute("username");
	    password=(String)session.getAttribute("password");
	    if(uname!=null && uname.equals("admin") && password!=null && password.equals("Simran@123")) {
//	    	out.println("<h1>Valid user</h1>");
	    	String className=request.getParameter("class")!=null ? request.getParameter("class") : "";
	    	out.println("<form action='AdminServlet' method='post'>");
	    	
	    	out.println("<select name='class' id='class'>");
	    	out.println("<option value=''>");
    		out.println("Select class");
    		out.println("</option>");
	    	classList.stream().forEach(classname -> {
	    		out.println("<option value='"+classname+"'>");
	    		out.println(classname);
	    		out.println("</option>");
	    	});
	    	out.println("</select>");
	    	out.println("<button type='submit' id='leaner_data'>");
	    	out.println("Submit");
	    	out.println("</button>");
	    	out.println("</form>");  	
	    	
	    	List<ClassInfo> classInfo=null;
	    	if(className==null || className =="") {
	    		classInfo = hibernateDaoImpl.listClassInfo();
	    	}
	    	else {
	    		classInfo = hibernateDaoImpl.listClassInfo();
	    		classInfo = classInfo.stream()
	    					.filter(classInfoItemElem->classInfoItemElem.getName().equalsIgnoreCase(className))
	    					.collect(Collectors.toList());
	    		
	    	}
	    		System.out.println("Class data"+ classInfo);
	    	//else TODO
	    	
	    	if(classInfo!=null && !classInfo.isEmpty()) {
	    		out.println("<table>");
	    		out.println("<tr>");
	    		out.println("<th> Class </th>");
	    		out.println("<th> Student </th>");
	    		out.println("<th> Teacher </th>");
	    		out.println("</tr>");
	    		
	    		for (ClassInfo classinfoItem: classInfo) {
	    			for(Student studentItem: classinfoItem.getStudent()) {
	    				out.println("<tr>");
			    		out.println("<td>"+ classinfoItem.getName() + "</td>");
			    		out.println("<td>" + studentItem.getName() + "</td>");
			    		out.println("<td>"+ classinfoItem.getTeacher().getName() +"</td>");
			    		out.println("</tr>");
	    			}
	    		}
	    		out.println("</table>");
	    	}
	    }
	    else {
	    	out.println("Invalid user");
	    }
	}

}
