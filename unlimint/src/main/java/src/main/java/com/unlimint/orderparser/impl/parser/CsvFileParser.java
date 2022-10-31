package src.main.java.com.unlimint.orderparser.impl.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.CSVParser;

import src.main.java.com.unlimint.orderparser.constants.FileConstants;
import src.main.java.com.unlimint.orderparser.impl.DataParserFromFileBuffer;
import src.main.java.com.unlimint.orderparser.interfaces.IFileParser;

@Service
public class CsvFileParser implements IFileParser {

	@Autowired
	DataParserFromFileBuffer dataParserFromFileBuffer;

	@Override
	public void parseFile(File file) {
		InputStream inStream = null;
		try {
			inStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			return;
		}
		BufferedReader fileBufferReader = new BufferedReader(new InputStreamReader(inStream));
		try {
			//currently handling for 1000 only. can do it in 1000 batches as well with counting records and loop
			dataParserFromFileBuffer.useCSVParserForRecordExtractionBuffer(fileBufferReader,
					CUSTOMER_INSERT_BATCH_SIZE_1000, FileConstants.HEADERS, new CSVParser(), file.getName());

		} catch (IOException e) {

		}

	}

}
