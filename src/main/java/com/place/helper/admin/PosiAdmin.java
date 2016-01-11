package com.place.helper.admin;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.place.helper.service.PoisSerivice;
import com.place.helper.service.impl.PoisSeriviceImpl;

public class PosiAdmin {

	private static AbstractApplicationContext ctx = null;
	
	//rivate static String[] guangzhou = {"越秀区","荔湾区","海珠区","天河区","白云区","黄埔区","花都区","番禺区","南沙区","增城区","从化区"};
	//private static String[] shenzhen = {"福田区","罗湖区","南山区","盐田区","宝安区","龙岗区"};
	//private static String[] zhuhai = {"香洲区","斗门区","金湾区"};
	private static String[] shaoguan = {"浈江区","武江区","曲江区","乐昌市","南雄市","始兴县","仁化县","翁源县","新丰县","乳源瑶族自治县"};
	private static String[] panyuqu = {"广州市番禺区"};
	
	public static void main(String[] args) {
		ctx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
		ctx.getBeanDefinitionNames();
		
		PoisSerivice poisSerivice = ctx.getBean(PoisSeriviceImpl.class);
		poisSerivice.savePoisz(panyuqu);
		
		ctx.registerShutdownHook();
	}
}
