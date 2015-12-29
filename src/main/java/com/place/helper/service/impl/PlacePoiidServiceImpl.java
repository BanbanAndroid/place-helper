package com.place.helper.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
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
			JSONObject json = JSONObject.parseObject(poisStr);
			JSONArray pois = json.getJSONArray("pois");
			Iterator<?> iterator = pois.iterator();
			
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
				try{
					placeName = placeBlock.getString("title");
					JSONObject district_info = placeBlock.getJSONObject("district_info");
					country = district_info.getString("country");
					province = district_info.getString("province");
					city = district_info.getString("city");
					county = district_info.getString("county");	
				}catch(JSONException e){
					e.printStackTrace();
				}
				placePoiid.setPlaceName(placeName);
				placePoiid.setCity(city);
				placePoiid.setCountry(country);
				placePoiid.setCounty(county);
				placePoiid.setProvince(province);
				
				if (placePoiid != null) {
					poiidRepository.save(placePoiid);
				}
			}
		}
	}

}
