package org.szpinc.api.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.szpinc.api.dao.CountyDao;
import org.szpinc.api.model.County;

@Service
public class CountyService {

	@Resource
	private CountyDao countyDao;

	/**
	 * 通过ID值获取县
	 * 
	 * @param id
	 * @return
	 */
	public County getCountyById(Integer id) {
		return countyDao.get(id);
	}

	/**
	 * 通过县的编码获取县的对象
	 * 
	 * @param countyId
	 * @return
	 */
	public County getCountyByCountyId(String countyId) {
		return countyDao.getByCountyId(countyId);
	}

	/**
	 * 通过县的名称获取县的集合
	 * 
	 * @param countyName
	 * @return
	 */
	public List<County> getCountyByCountyName(String countyName) {
		return countyDao.getByCountyName(countyName);
	}
}
