package com.place.helper.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.place.helper.persistent.repository.PlacePoiidRepository;
import com.place.helper.service.CSVService;

@Service
@Transactional(readOnly = true)
public class CSVServiceImpl implements CSVService {
	
	@Inject
	private PlacePoiidRepository poiidRepository;
	
	public synchronized void writeCSV(File file){
		OutputStreamWriter streamWriter = null;
		BufferedWriter writer = null;
		try{
			File csvFile = new File("guangzhoudata.csv");
			
			FileOutputStream out = new FileOutputStream(csvFile, true);
			streamWriter = new OutputStreamWriter(out, "gbk");
			writer = new BufferedWriter(streamWriter);

			
		}catch (Exception e){
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (streamWriter != null) {
				try {
					streamWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public void controllerCSV(){
		InputStreamReader reader = null;
		CSVReader csvReader = null;
		try{
			File csvFile = new File("guangzhoudata.csv");
			
			FileInputStream in  = new FileInputStream(csvFile);
			reader = new InputStreamReader(in, "gbk");
			csvReader = new CSVReader(reader);
			
			String[] nextLine;
			while ((nextLine = csvReader.readNext()) != null){
				for (String line : nextLine) {
					System.out.println(line);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (csvReader != null) {
				try {
					csvReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
