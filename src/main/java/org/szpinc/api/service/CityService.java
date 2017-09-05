package org.szpinc.api.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.szpinc.api.dao.CityDao;
import org.szpinc.api.model.City;

@Service
public class CityService {

	@Resource
	private CityDao cityDao;

	/**
	 * 通过城市的ID值获取
	 * 
	 * @param id
	 * @return
	 */
	public City getCityById(Integer id) {
		return cityDao.get(id);
	}

	/**
	 * 通过城市的编码获取
	 * 
	 * @param cityId
	 * @return
	 */
	public City getCityByCityId(String cityId) {
		return cityDao.getByCityId(cityId);
	}

	/**
	 * 通过城市的名称获取城市集合
	 * 
	 * @param cityName
	 * @return
	 */
	public List<City> getCityByCityName(String cityName) {
		return cityDao.getByCityName(cityName);
	}
}
