package org.jdbc.dao;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class DaoSecondImpl extends SimpleJdbcDaoSupport {
	
public int getTriangleCount(){
		
	    String sql = "Select count(*) from Triangle";
		//getting everything from parentclass(SimpleJdbcDaoSupport)
		return this.getJdbcTemplate().queryForInt(sql);
	
}

}
