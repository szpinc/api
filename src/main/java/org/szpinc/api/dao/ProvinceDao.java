package org.szpinc.api.dao;

import com.googlecode.ehcache.annotations.TriggersRemove;
import org.springframework.stereotype.Repository;
import org.szpinc.api.model.Province;

@Repository
public interface ProvinceDao extends BaseDao<Province> {
	@TriggersRemove(cacheName = "mapCache",removeAll = true)
	public Province getByProvinceId(String provinceId) ;
	@TriggersRemove(cacheName = "mapCache",removeAll = true)
	public Province getByProvinceName(String provinceId) ;
}