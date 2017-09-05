package org.szpinc.api.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.szpinc.api.dao.VillageDao;
import org.szpinc.api.model.Village;

@Service
public class VillageService {

	@Resource
	private VillageDao villageDao;

	/**
	 * 通过ID获取街道
	 * 
	 * @param id
	 * @return
	 */
	public Village getVillageById(Integer id) {
		return villageDao.get(id);
	}

	/**
	 * 通过编码获取街道
	 * 
	 * @param villageId
	 * @return
	 */
	public Village getVillageByVillageId(String villageId) {
		return villageDao.getByVillageId(villageId);
	}

	/**
	 * 通过名称获取街道集合
	 * 
	 * @param villageName
	 * @return
	 */
	public List<Village> getVillageByVillageName(String villageName) {
		return villageDao.getByVillageName(villageName);
	}
}