package com.place.helper.admin;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.place.helper.service.PlacePoiidService;
import com.place.helper.service.impl.PlacePoiidServiceImpl;

public class PoiidAdmin {

	private static AbstractApplicationContext ctx = null;
		
	public static void main(String[] args) {
		ctx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
		ctx.getBeanDefinitionNames();
		
		PlacePoiidService poiidService = ctx.getBean(PlacePoiidServiceImpl.class);
		poiidService.synchronizePoiidAndPois();
		
		ctx.registerShutdownHook();
	}
}
