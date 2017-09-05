package org.szpinc.api.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.szpinc.api.model.City;
@Repository
public interface CityDao extends BaseDao<City> {
	
	public List<City> getByCityName(String cityName) ;
	
	
	public City getByCityId(String cityId) ;
	
	
	
}
