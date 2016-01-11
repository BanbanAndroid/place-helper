package com.place.helper.service.impl;

import java.net.URLEncoder;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.place.helper.persistent.entity.PlacePois;
import com.place.helper.persistent.repository.PlacePoisRepository;
import com.place.helper.service.CrawlService;
import com.place.helper.service.PoisSerivice;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PoisSeriviceImpl implements PoisSerivice {

	@Inject
	private CrawlService crawlService;
	@Inject
	private PlacePoisRepository poisRepository;
	
	public void savePoisz(String[] places) {
		String urlStr = "https://api.weibo.com/2/place/pois/search.json?source=228094904&access_token=2.003NCcqB0EuD8P2c32ebc778EbpwkC&page=2&count=50&keyword=";
		for (String place : places) {
			try {
				String placeName = URLEncoder.encode(place, "utf-8");
				String url = urlStr + placeName;
				
				String page = crawlService.crawlWebContent(url, null).toString();
				
				if (page != null) {
					PlacePois placePois = new PlacePois();
					placePois.setPlaceName(place);
					placePois.setPlacePois(page);
					
					poisRepository.save(placePois);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
