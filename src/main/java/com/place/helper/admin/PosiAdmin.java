package com.place.helper.admin;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.place.helper.service.PoisSerivice;
import com.place.helper.service.impl.PoisSeriviceImpl;

public class PosiAdmin {

	private static AbstractApplicationContext ctx = null;
	
	private static String[] placeNames = {"越秀区","荔湾区","海珠区","天河区","白云区","黄埔区","花都区","番禺区","南沙区","增城区","从化区"};
	
	public static void main(String[] args) {
		ctx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
		ctx.getBeanDefinitionNames();
		
		PoisSerivice poisSerivice = ctx.getBean(PoisSeriviceImpl.class);
		poisSerivice.savePoisz(placeNames);
		
		ctx.registerShutdownHook();
	}
}
