package com.place.helper.admin;

import org.apache.http.client.CookieStore;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.place.helper.common.util.Constans;
import com.place.helper.service.CrawlService;
import com.place.helper.service.WeiboLoginService;
import com.place.helper.service.impl.CrawlServiceImpl;

public class Administrator {

	private static AbstractApplicationContext ctx = null;
	
	public static void main(String[] args) {
		ctx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
		ctx.getBeanDefinitionNames();
		
		WeiboLoginService weiboLoginService = ctx.getBean(WeiboLoginService.class);
		weiboLoginService.setUsername(Constans.sina_name);
		weiboLoginService.setPassword(Constans.sina_password);
		
		CookieStore cookieStore = weiboLoginService.getCookieStore(weiboLoginService.getLoginEntity(weiboLoginService.getPreLoginInfo(), null));
		
		if (cookieStore == null) {
			System.out.println(" Login Failure ");
			return;
		}

		CrawlService crawlService = ctx.getBean(CrawlServiceImpl.class);
		String page = crawlService.crawlWebContent("http://weibo.com/p/1001018008632010000000000", cookieStore).toString();
		if (page != null) {
			System.out.println(page);
		}
		
		ctx.registerShutdownHook();
	}
}
