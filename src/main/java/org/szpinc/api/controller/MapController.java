package org.szpinc.api.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.szpinc.api.base.BaseController;
import org.szpinc.api.model.*;
import org.szpinc.api.service.CityService;
import org.szpinc.api.service.CountyService;
import org.szpinc.api.service.ProvinceService;
import org.szpinc.api.service.TownService;
import org.szpinc.api.service.VillageService;

@Controller
@RequestMapping("/v1/map")
public class MapController extends BaseController {
	@Resource
	private ProvinceService provinceService;
	@Resource
	private CityService cityService ;
	@Resource
	private CountyService countyService ;
	@Resource
	private TownService townService ;
	@Resource
	private VillageService villageService ;


	@RequestMapping(value = "/province/{id}",method = RequestMethod.GET)

	public @ResponseBody Province getProvinceById (@PathVariable("id") Integer id) {
		Province pro = provinceService.getProvinceById(id);
		return pro;
	}

	@RequestMapping("/hello")
	public @ResponseBody String hello () {
		return "Hello World";
	}



	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody Object search (@RequestParam("type") String type,@RequestParam("value") String value) {
		if ("province".equals(type)) {
			return provinceService.getProvinceByProvinceName(value);
		}
		if ("city".equals(type)) {
			return cityService.getCityByCityName(value);
		}
		if ("county".equals(type)) {
			return countyService.getCountyByCountyName(value);
		}
		if ("town".equals(type)) {
			return townService.getTownByTownName(value);
		}
		if ("village".equals(type)) {
			return villageService.getVillageByVillageName(value);
		}
		return null;
	}

	@RequestMapping("/provinces")
	public @ResponseBody List<Province> getAllProvince () {
		return provinceService.getAllProvince();
	}

	/**
	 * 根据省编号查询其所有的城市
	 * @param provinceId
	 * @return
	 */
	@RequestMapping(value = "/cities",method = RequestMethod.GET)
	public @ResponseBody List<City> getCities (@RequestParam("province_id") String provinceId) {
		List<City> cities = provinceService.getProvinceByProvinceId(provinceId).getCities();
		for (City city : cities) {
			log.debug(city.getCityName());
		}
		return cities;
	}


	@RequestMapping(value = "/counties",method = RequestMethod.GET)
	public @ResponseBody List<County> getCounties (@RequestParam("city_id") String cityId) {
		return cityService.getCityByCityId(cityId).getCounties();
	}


	@RequestMapping(value = "/towns",method = RequestMethod.GET)
	public @ResponseBody List<Town> getTowns (@RequestParam("county_id") String countyId) {
		return countyService.getCountyByCountyId(countyId).getTowns();
	}

	@RequestMapping(value = "villages",method = RequestMethod.GET)
	public @ResponseBody List<Village> getVillages (@RequestParam("town_id") String townId) {
		return townService.getTownByTownId(townId).getVillages();
	}


}