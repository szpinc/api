package org.szpinc.api.service;

import javax.annotation.Resource;

import com.googlecode.ehcache.annotations.Cacheable;
import org.springframework.stereotype.Service;
import org.szpinc.api.dao.ProvinceDao;
import org.szpinc.api.model.Province;

import java.util.List;

@Service
public class ProvinceService {
	
	@Resource
	private ProvinceDao provinceDao;
	
	/**
	 * 通过ID查询省
	 * @param id
	 * @return
	 */
	@Cacheable(cacheName = "mapCache")
	public Province getProvinceById (Integer id) {
		return provinceDao.get(id);
	}
	
	/**
	 * 通过省的编码查询
	 * @param provinceId
	 * @return
	 */
	@Cacheable(cacheName = "mapCache")
	public Province getProvinceByProvinceId (String provinceId) {
		return provinceDao.getByProvinceId(provinceId);
	}
	
	/**
	 * 通过省的名称查询省
	 * @param provinceName
	 * @return
	 */
	@Cacheable(cacheName = "mapCache")
	public Province getProvinceByProvinceName (String provinceName) {
		return provinceDao.getByProvinceName(provinceName);
	}

	/**
	 * 获取所有省的集合
	 * @return
	 */
	@Cacheable(cacheName = "mapCache")
	public List<Province> getAllProvince () {
		return provinceDao.getAll();
	}
}