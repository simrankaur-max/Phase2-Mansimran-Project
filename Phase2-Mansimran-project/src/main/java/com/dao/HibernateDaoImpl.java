package com.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.dto.ClassInfo;



public class HibernateDaoImpl implements HibernateDao {
	private SessionFactory factory;
	public HibernateDaoImpl() {
		// TODO Auto-generated constructor stub
		System.out.println("Inside HibernateDaoImpl");
			StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  
	        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();  
	        factory = meta.getSessionFactoryBuilder().build();
	}

	@Override
	public Integer addClassInfo(ClassInfo classInfo) {
		// TODO Auto-generated method stub
		    System.out.println("Inside HibernateDaoImpl add method");	
			Session session = factory.openSession();
			Transaction txn = session.beginTransaction();
			int id = (Integer)session.save(classInfo);
			txn.commit();
			session.close();
			return id;
		
	}

	@Override
	public List<ClassInfo> listClassInfo() {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction txn = session.beginTransaction();
		List<ClassInfo> classInfo=null;
		String hql="FROM ClassInfo";
		TypedQuery<ClassInfo> query = session.createQuery(hql);
		classInfo = query.getResultList();
//		session.close();
		return classInfo;
	}

	@Override
	public ClassInfo searchClassByName(Integer classInfoID) {
		Session session = factory.openSession();
		Transaction txn = session.beginTransaction();
		ClassInfo employee = session.get(ClassInfo.class, classInfoID);
		session.close();
		return employee;
	}

}
