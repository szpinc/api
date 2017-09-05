package org.szpinc.api.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.szpinc.api.dao.TownDao;
import org.szpinc.api.model.Town;

@Service
public class TownService {

	@Resource
	private TownDao townDao;

	/**
	 * 通过ID值获取城镇
	 * 
	 * @param id
	 * @return
	 */
	public Town getTownById(Integer id) {
		return townDao.get(id);
	}

	/**
	 * 通过编码值获取城镇
	 * 
	 * @param townId
	 * @return
	 */
	public Town getTownByTownId(String townId) {
		return townDao.getByTownId(townId);
	}

	/**
	 * 通过名称获取城镇集合
	 * 
	 * @param townName
	 * @return
	 */
	public List<Town> getTownByTownName(String townName) {
		return townDao.getByTownName(townName);
	}

}
