package com.place.helper.admin;

import com.place.helper.service.impl.CSVServiceImpl;

public class CSVAdmin {

	public static void main(String[] args) {
		CSVServiceImpl csvServiceImpl = new CSVServiceImpl();
		csvServiceImpl.controllerCSV();
	}
}
