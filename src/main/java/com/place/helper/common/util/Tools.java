package com.place.helper.common.util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

public class Tools {
	private static Logger logger = LoggerFactory.getLogger(Tools.class);
	
	/**
	 * @param s
	 * �Կհ׽��й���
	 */
	public static String filterBlankCharacters(String s){
		if (s == null || s.equals("")) {
			return s;
		}
		
		String goalStr = "";
		for (int i = 0; i < s.length(); i++) {
			if (33 <= s.charAt(i) && s.charAt(i) <= 127) {
				goalStr += s.charAt(i);
			}
		}
		
		return goalStr;
	}
	
	/**
	 * ���һ��ʵ���json��ʽ��String
	 */
	public static String getEntityJsonStr (String wholeJson, String pojoName){
		String entityStr = "";
		
		if (wholeJson == null || "".equals(wholeJson)) {
			logger.info("----------the json father is null-----------");
			
			return null;
		}
		if (pojoName == null || "".equals(pojoName)) {
			logger.info("----------the pojoName is null-----------");
			
			return null;
		}
		
		JSONObject jsonObject = null;
		JSONObject json = null;
		
		try{
			jsonObject = JSONObject.parseObject(wholeJson);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("------------your wholejson is not allow-------------");
			return null;
		}
		try{
			json = jsonObject.getJSONObject(pojoName);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("------something happend in pojoName------your pojoName is not allow-------------");
			return null;
		}
		
		entityStr = json.toString();
		return entityStr;
	}
	
	/**
	 * pojoToJson  ��ʵ��ת��Ϊjson��ʽ
	 */
	public static String pojoToJson (Object object){
		String json = "";
		
		if (object == null) {
			logger.info("--------your object is null-----------");
			
			return null;
		}
		Gson gson = new Gson();
		json = gson.toJson(object);
		return json;
	}
	
	/**
	 * jsonת��Ϊ����
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public static Object jsonToPojo(String jsonStr, Class<?> clazz){
		Gson gson = new Gson();
		JSONObject json = JSONObject.parseObject(jsonStr);
		Object object = new Object();
		object = gson.fromJson(json.toString(), clazz);
		return object;
	}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static File createFile(String fileName){
        /*
         * �����ļ�
         */
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
            	logger.info("createFile:"+e.toString());
            	return null;
            }
        }
        return file;
	}
	/**
	 * @param dir
	 * @param fileName
	 * @param suffix
	 * @return
	 */
	public static File createFile(String dir, String fileName){
		/*
		 * ����Ŀ¼
		 */
		File dirFile = new File(dir);
        if (!dirFile.exists() && !dirFile.isDirectory()) {
            logger.info("��(��o��)��  ������,���ڴ���....");
            try{
            	dirFile.mkdir();
            }catch (Exception e){
            	logger.info("�ļ�����ʧ��");
                return null;
            }
            logger.info("O(��_��)O�����ɹ�");
        } 
        /*
         * �����ļ�
         */
        File file = new File(dir+fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
            	logger.info("createFile"+e.toString());
            	return null;
            }
        }
        return file;
	}
	/**
	 * ��ȡ�ļ�����
	 * @param filePath
	 * @return
	 */
	public static String readFile(String filePath){
		String fileContent = "";
		File file = new File(filePath);
		BufferedReader reader = null;
		if (!file.exists()) {
			logger.info("readFile:the file:"+filePath+" is not exists");
			return null;
		}
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(filePath),"UTF-8");
			reader=new BufferedReader(read);
			String line;
			while ((line = reader.readLine()) != null) {
			fileContent += line;
			} 
			read.close();
		} catch (FileNotFoundException e) {
			logger.error("Tools:readFile:file not found.............."+e.toString());
			return null;
		} catch (IOException e) {
			logger.error("Tools:readFile:io exception.............."+e.toString());
			return null;
		}finally{
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return fileContent;
	}

	/**
	 * ��ӡһ�����ȵ�*��
	 * @param len
	 */
	public static void printStarLine(int len){
		for(int i = 0;i<len;i++){
			System.out.print("*");
		}
		System.out.println("\n");
	}
	
	/**
	 * @param file
	 * @param content
	 * @param appendok
	 * @throws IOException
	 */
	public static void writeFile(File file, String content, boolean appendok) throws IOException{
		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file, appendok),"UTF-8");
		BufferedWriter writer=new BufferedWriter(write); 
		writer.write(content);
		writer.close();
	}
	/**
	 * @param path
	 * @return
	 */
	public static boolean fileExists(String path){
		 File pathFile = new File(path);
		 if (pathFile.exists()) {
			 return true;
	     } 
		 else{
			 return false;
		 }
	}
	/**
	 * @param file
	 * @return
	 */
	public static boolean fileExists(File file){
		 if (file.exists()) {
			 return true;
	     } 
		 else{
			 return false;
		 }
	}
	/**
	 * ��������Ƿ����
	 * @return
	 */
	public synchronized static boolean networkUsable() {
		String urlStr = "https://www.baidu.com/";
		int state = -1;
		HttpURLConnection connection = null;
		try{
			URL url = new URL(urlStr);
			connection = (HttpURLConnection)url.openConnection();
			state = connection.getResponseCode();
		}catch (Exception e){
			logger.error("���粻����.....");
			return false;
		} finally{
			if (connection != null) {
				connection.disconnect();
			}
		}
		if(state == HttpStatus.SC_OK){
			return true;
		} else{
			logger.error("���Է����ѹ���ҳʧ��,�����쳣");
			return false;
		}
	}
	
	public static String wrapTime(Date date){
		String dateTime = "1970-01-01 00:00:00";
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		try{
			dateTime = time.format(date);
		}catch (Exception e){
			System.err.println(e.toString());
			return null;
		}
		return dateTime;
	}
	

	/**
	 * @param map
	 * @param file
	 */
	public static boolean writeObject(Map<Object, Object> map, File file) {
		FileOutputStream outStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {  
			outStream = new FileOutputStream(file);
			objectOutputStream = new ObjectOutputStream(outStream);
			objectOutputStream.writeObject(map);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally{
			if (outStream != null) {
				try {
					outStream.close();
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * @param file
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<Object, Object> readObject(File file){
		FileInputStream freader = null;
		ObjectInputStream objectInputStream = null;
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		try {
			freader = new FileInputStream(file);
		    objectInputStream = new ObjectInputStream(freader);
		    if (objectInputStream != null) {
		    	map = (HashMap<Object, Object>) objectInputStream.readObject();	
			}	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {	  
			e.printStackTrace();
			return null;
		} finally{
			try{
				if (objectInputStream != null) {
					objectInputStream.close();
				}
				if (freader != null) {
					freader.close();
				}
			}catch (Exception e){
				e.printStackTrace();
				return null;
			}
		}
		return map;
	}
	/**
	 * @param imageUrl
	 * @param imageName
	 * @return
	 */
	public static boolean fetchImage(String imageUrl, String imageName){
		try{
			//newһ��URL����  
	        URL url = new URL(imageUrl);  
	        //������  
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
	        //��������ʽΪ"GET"  
	        conn.setRequestMethod("GET");  
	        //��ʱ��Ӧʱ��Ϊ5��  
	        conn.setConnectTimeout(5 * 1000);  
	        //ͨ����������ȡͼƬ����  
	        InputStream inStream = conn.getInputStream();  
	        //�õ�ͼƬ�Ķ��������ݣ��Զ����Ʒ�װ�õ����ݣ�����ͨ����  
	        byte[] data = null;
	        try{
	        	data = readInputStream(inStream);  
	        }catch (Exception e){
	        	logger.info("readInputStream:inStream to byte[] error");
	        	return false;
	        }
	        //newһ���ļ�������������ͼƬ��Ĭ�ϱ��浱ǰ���̸�Ŀ¼  
	        File imageFile = new File(imageName);  
	        //���������  
	        FileOutputStream outStream = new FileOutputStream(imageFile);  
	        //д������  
	        outStream.write(data);  
	        //�ر������  
	        outStream.close();  
		}catch(Exception ee){
			logger.info("fetchImage:fetch image error");
			return false;
		}
		return true;
	}
	/** 
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        //����һ��Buffer�ַ���  
        byte[] buffer = new byte[1024];  
        //ÿ�ζ�ȡ���ַ������ȣ����Ϊ-1������ȫ����ȡ���  
        int len = 0;  
        //ʹ��һ����������buffer������ݶ�ȡ����  
        while( (len=inStream.read(buffer)) != -1 ){  
            //���������buffer��д�����ݣ��м����������ĸ�λ�ÿ�ʼ����len�����ȡ�ĳ���  
            outStream.write(buffer, 0, len);  
        }  
        //�ر�������  
        inStream.close();  
        //��outStream�������д���ڴ�  
        return outStream.toByteArray();  
    }  
	/**
	 * 
	 * @param url
	 * @return
	 */
	public static BufferedImage getImageFromUrl(String url){
		BufferedImage image = null;
        try {
            URL imageURL = new URL(url);
            InputStream is = imageURL.openConnection().getInputStream();
            image = ImageIO.read(is);
        } catch (Exception e) {
            logger.info("get image from net fail:"+e.toString());
            return null;
        }
        return image;
	}
}
