package com.place.helper.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.place.helper.persistent.entity.PlacePoiid;
import com.place.helper.persistent.entity.PlacePois;
import com.place.helper.persistent.repository.PlacePoiidRepository;
import com.place.helper.persistent.repository.PlacePoisRepository;
import com.place.helper.service.PlacePoiidService;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PlacePoiidServiceImpl implements PlacePoiidService {

	@Inject
	private PlacePoisRepository poisRepository;
	@Inject
	private PlacePoiidRepository poiidRepository;
	
	public void synchronizePoiidAndPois() {
		
		List<PlacePois> placePoisz = new ArrayList<PlacePois>();
		
		placePoisz = poisRepository.findAll();
		
		for (PlacePois placePois : placePoisz) {
			String poisStr = placePois.getPlacePois();
			JSONObject json = null;
			Iterator<?> iterator =null;
			try{
				json = JSONObject.parseObject(poisStr);
				JSONArray pois = json.getJSONArray("pois");
				iterator = pois.iterator();
			}catch (Exception e){
				e.printStackTrace();
				return;
			}

			while (iterator.hasNext()) {
				JSONObject placeBlock = (JSONObject)iterator.next();
				String poiid = placeBlock.getString("poiid");
				PlacePoiid placePoiid = new PlacePoiid();
				placePoiid.setPoiId(poiid);
				placePoiid.setPlacePois(placePois);
				String placeName = null;
				String country = null;
				String province = null;
				String city = null;
				String county = null;
				String lat = null;
				String lon = null;
				try{
					placeName = placeBlock.getString("title");
					JSONObject district_info = placeBlock.getJSONObject("district_info");
					country = district_info.getString("country");
					province = district_info.getString("province");
					city = district_info.getString("city");
					county = district_info.getString("county");	
					lon = district_info.getString("lng");
					lat = district_info.getString("lat");
				}catch(Exception e){
					e.printStackTrace();
				}
				placePoiid.setPlaceName(placeName);
				placePoiid.setCity(city);
				placePoiid.setCountry(country);
				placePoiid.setCounty(county);
				placePoiid.setProvince(province);
				placePoiid.setLat(lat);
				placePoiid.setLon(lon);
				
				if (placePoiid != null) {
					if (poiidRepository.findByPoiId(placePoiid.getPoiId()) == null) {
						poiidRepository.save(placePoiid);
					}
				}
			}
		}
	}

}
