package org.jdbc.mainmethod;

import org.jdbc.dao.DaoImpl;
import org.jdbc.dao.DaoSecondImpl;
import org.jdbc.model.Triangle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MainClass {

	public static void main(String args[]  ) {
		
		System.out.println("main method");
		
		
      ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
      
      DaoSecondImpl Secdao = context.getBean("daoSecondImpl", DaoSecondImpl.class);
      System.out.println("Secomnddao:"+Secdao.getTriangleCount());
   DaoImpl dao = context.getBean("daoImpl", DaoImpl.class);
           
   Triangle triangle = dao.getTriangle(1);
      
      System.out.println("dao");
    
		
		System.out.println(triangle.getName());
		
		System.out.println("Count value is:"+ dao.getTriangleCount());
		System.out.println("names are:"+ dao.getTriangleName(1));
		System.out.println("entire records NAME:"+dao.getentireRecords(2).getName());
		System.out.println("entire records ID:"+dao.getentireRecords(2).getId());
		 
		
	
      dao.createRecTable();
     dao.insertTri(new Triangle( 3,"Third Triangle "));
  	System.out.println("Record Count :"+dao. allTriangles().size());
    
   
		 
		
		
		
		
		
           
	}

}
