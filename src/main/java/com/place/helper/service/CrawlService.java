package com.place.helper.service;

import org.apache.http.client.CookieStore;

public interface CrawlService {

	public StringBuffer crawlWebContent(String url, CookieStore cookieStore);
	
}
