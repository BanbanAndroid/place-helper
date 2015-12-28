package com.place.helper.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.place.helper.common.util.Constans;
import com.place.helper.common.util.CookieConfig;
import com.place.helper.service.CrawlService;

@Service
public class CrawlServiceImpl implements CrawlService {
	
	private static final Logger logger = LoggerFactory.getLogger(CrawlServiceImpl.class);

	public StringBuffer crawlWebContent(String url, CookieStore cookieStore) {

		HttpGet httpGet = new HttpGet(url);
//		setHeaders(httpGet);

		CloseableHttpClient client = CookieConfig.configeClient(cookieStore);
		
		HttpResponse response = null;
		try{
			response = client.execute(httpGet);
		}catch (HttpHostConnectException ee){
			logger.info("getContent:连接超时!!!网络不稳定...稍后重试...");
			return null;
		} catch (ClientProtocolException e) {
			System.err.println("getContent error..\n"+e.toString());
			return null;
		} catch (IOException e) {
			System.err.println("getContent error..\n"+e.toString());
			return null;
		}
		HttpEntity entity = response.getEntity();
		StringBuffer sb = new StringBuffer();
		if(entity == null) {
			logger.info("Error happened in crawler" + httpGet.getURI());
			
			System.out.println("Error happened in crawler" + httpGet.getURI());
			
			return null;
		} else {
			try {
				InputStream dataHtml = entity.getContent();
				BufferedReader htmlBufferReader = new BufferedReader(new InputStreamReader(dataHtml));
				for(String e = htmlBufferReader.readLine(); e != null; e = htmlBufferReader.readLine()) {
					sb.append(e + '\r' + '\n');
				}
			} catch (Exception e) {
				httpGet.abort();
				return null;
			}
		}
		String dataHtml = new String(sb);
		StringBuffer htmlsb = new StringBuffer(dataHtml);
		return htmlsb;
	}

	
	@SuppressWarnings("unused")
	private static void setHeaders(HttpGet httpGet){
		
		String userAgent = Constans.userAgent;
		String accept = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8";
		String acceptEncoding = "gzip,deflate,sdch";
		String acceptLanguage = "zh-CN,zh;q=0.8";
		String cacheControl = "max-age=0";
		String connection = "keep-alive";
		String host = "weibo.com";
		
		httpGet.setHeader("User-Agent", userAgent);
		httpGet.setHeader("Connection", connection);
		httpGet.setHeader("Accept", accept);
		httpGet.setHeader("Host", host);
		httpGet.setHeader("Accept-Encoding", acceptEncoding);
		httpGet.setHeader("Accept-Language", acceptLanguage);
		httpGet.setHeader("Cache-Control", cacheControl);
		httpGet.setHeader("HTTPS", "1");
	}
}
