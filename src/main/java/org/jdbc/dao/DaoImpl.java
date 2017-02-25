package org.jdbc.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;


import java.sql.Connection;
import org.jdbc.model.Triangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;



@Component
public class DaoImpl {
	
	
	

	private DataSource dataSource;
	
	private JdbcTemplate jdbctemplate = new JdbcTemplate();
	

	
	
	
	public Triangle getTriangle(int triangleid){
		
		System.out.println("Before Con");
		
		
		Connection con =null;
		
	    try{
	    	System.out.println("before getting connection");
	    	con = dataSource.getConnection();
	    	
	    	System.out.println("after gettoing connection");
		
		
	
			String preparedSQL = "select  * from triangle where id =?";
			
			PreparedStatement ps = con.prepareStatement(preparedSQL);
			
		    System.out.println("connected to database");
		    
			ps.setInt(1, triangleid);
			
			System.out.println("Before tri.null");
			
			Triangle tri= null;
			
			
			ResultSet rs = ps.executeQuery();
			
			System.out.println("Before rs.next");
			if(rs.next()){
				System.out.println("inside rs.next");
				  tri = new Triangle(triangleid, rs.getString("name"));
			}
			else{
				System.out.println("null");
			}
			rs.close();
			ps.close();
			return tri;
	}
	
	catch(Exception e){
		 throw new RuntimeException("errot" +e);
	}
	
	finally{
		try{
			con.close();
		}catch(SQLException e){
			
		}
	}}
	
	public int getTriangleCount(){
		
	    String sql = "Select count(*) from Triangle";
		
		return jdbctemplate.queryForInt(sql);
	
	}
	
	public String getTriangleName(int triangleid){
		
		String sqlName = "select name from Triangle where id=?";
		return jdbctemplate.queryForObject(sqlName, new Object[] {triangleid}, String.class);
		
		
	}
	
	
	
	public Triangle getentireRecords(int triangleid ){
		
		String completeRecords = "select * from triangle where id=?";
		return jdbctemplate.queryForObject(completeRecords, new Object[] {triangleid}, new triangleMapper() );
	}
	
	//to retrieve entire records form table, use LIST
	
	public List<Triangle> allTriangles(){
		
		 String allRecords = "select * from Triangle";
		return  jdbctemplate.query(allRecords, new triangleMapper());
		
	}
	
	
	
	
	
	//jdbctemplate cannot map the values, so rowmapper is used
	private static final class triangleMapper implements RowMapper<Triangle>{

		public Triangle mapRow(ResultSet resultset, int rownum) throws SQLException {
			
			Triangle t = new Triangle();
			t.setId(resultset.getInt("ID"));
			t.setName(resultset.getString("NAME"));
			return t;
		}
		
		
	}

	
	
	
	
	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbctemplate = new JdbcTemplate(dataSource) ;
	}

	public JdbcTemplate getJdbctemplate() {
		return jdbctemplate;
	}
	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}
	


}
