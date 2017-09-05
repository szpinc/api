package org.szpinc.api.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.szpinc.api.model.County;
@Repository
public interface CountyDao extends BaseDao<County> {
	
	public County getByCountyId(String countyId) ;
	
	public List<County> getByCountyName(String countyName) ;
	
}
