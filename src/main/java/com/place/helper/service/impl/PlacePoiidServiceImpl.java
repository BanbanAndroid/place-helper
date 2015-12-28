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
			JSONObject json = JSONObject.parseObject(poisStr);
			JSONArray pois = json.getJSONArray("pois");
			Iterator<?> iterator = pois.iterator();
			
			while (iterator.hasNext()) {
				JSONObject placeBlock = (JSONObject)iterator.next();
				String poiid = placeBlock.getString("poiid");
				PlacePoiid placePoiid = new PlacePoiid();
				placePoiid.setPoiId(poiid);
				placePoiid.setPlacePois(placePois);
				
				if (placePoiid != null) {
					poiidRepository.save(placePoiid);
				}
			}
		}
	}

}
