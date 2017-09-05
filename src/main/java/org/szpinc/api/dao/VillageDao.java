package org.szpinc.api.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.szpinc.api.model.Village;
@Repository
public interface VillageDao extends BaseDao<Village> {
	public void batchAdd(List<Village> list) ;
	
	public List<Village> getWithLimit(Integer index, Integer pageSize) ;
	
	public Village getByVillageId(String villageId) ;
	
	public List<Village> getByVillageName(String villageName) ;
}
