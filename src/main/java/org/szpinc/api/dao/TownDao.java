package org.szpinc.api.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.szpinc.api.model.Town;
@Repository
public interface TownDao extends BaseDao<Town> {
	
	public List<Town> getByTownName(String townName) ;
	
	public Town getByTownId(String townId) ;
	
	
}
