package src.main.java.com.unlimint.orderparser.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.opencsv.CSVParser;

import src.main.java.com.unlimint.orderparser.impl.validator.ValidateAndConvertCsvResult;
import src.main.java.com.unlimint.orderparser.impl.validator.ValidateAndConvertJsonResult;

@Service
public class DataParserFromFileBuffer {

	public void useCSVParserForRecordExtractionBuffer(BufferedReader fileBufferReader, int maxLinesToRead,
			String[] headerArray, CSVParser csvParser, String fileName) throws IOException {
		Integer totalRecords = 0;
		List<Map<String, Object>> recordsList = new ArrayList<Map<String, Object>>();
		String line;
		String[] previousLineArray = null;
		for (int count = 0; count < maxLinesToRead; count++) {
			if ((line = fileBufferReader.readLine()) != null) {
				Map<String, Object> record = new HashMap<String, Object>();
				Object[] lineArray = getLineArrayFromCsvParser(csvParser, line, previousLineArray);
				if (lineArray != null) {
						createRecordList(recordsList, totalRecords, headerArray, record, lineArray, fileName,
								count + 1);
				}
			}else {
				break;
			}

		}

	}

	private void createRecordList(List<Map<String, Object>> recordsList, Integer totalRecords, String[] headerArray,
			Map<String, Object> record, Object[] lineArray, String fileName, Integer lineNo) {
		for (int i = 0; i < headerArray.length; i++) {
			String headerValue = headerArray[i];
			record.put(headerValue, lineArray[i]);
		}
		recordsList.add(record);
		Thread validateAndConvertResult = new Thread(new ValidateAndConvertCsvResult(record, fileName, lineNo));
		validateAndConvertResult.start();

	}

	public Object[] getLineArrayFromCsvParser(CSVParser csvParser, String line, String[] previousLine) {
		Object[] valuesArray = null;
		try {
			if (line != null && line.length() >= 1) {
				valuesArray = csvParser.parseLineMulti(line);
			}
		} catch (Exception e) {
		}

		return valuesArray;
	}

	public void useJsonParserForRecordExtractionBuffer(BufferedReader fileBufferReader, int maxLinesToRead,
			String fileName) throws IOException {
		String line;

		for (int count = 0; count < maxLinesToRead; count++) {
			if ((line = fileBufferReader.readLine()) != null) {
				JSONParser parser = new JSONParser();
				try {
					JSONObject jsonObject = (JSONObject) parser.parse(line);
					Thread validateAndConvertResult = new Thread(
							new ValidateAndConvertJsonResult(jsonObject, fileName, count + 1));
					validateAndConvertResult.start();
				} catch (ParseException e) {
					// e.printStackTrace();
				}

			}

		}

	}
}
