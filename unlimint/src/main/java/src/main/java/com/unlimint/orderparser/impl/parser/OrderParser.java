package src.main.java.com.unlimint.orderparser.impl.parser;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderParser {

	private static final String JSON = "json";
	private static final String CSV = "csv";

	@Autowired
	CsvFileParser csvFileParser;

	@Autowired
	JsonFileParser jsonFileParser;

	public void parseOrder(List<File> files) {
		for (File file : files) {
			Optional<String> fileExtension = getExtensionByStringHandling(file.getName());
			//can do this as multiple thread as well.Currently skipping that
			if (fileExtension.isPresent()) {
				if (CSV.equals(fileExtension.get())) {
					csvFileParser.parseFile(file);
				} else if (JSON.equals(fileExtension.get())) {
					jsonFileParser.parseFile(file);

				}
			}
		}
	}

	public Optional<String> getExtensionByStringHandling(String filename) {
		return Optional.ofNullable(filename).filter(f -> f.contains("."))
				.map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}
}
