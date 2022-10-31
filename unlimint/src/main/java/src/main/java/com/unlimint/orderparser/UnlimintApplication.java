package src.main.java.com.unlimint.orderparser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import src.main.java.com.unlimint.orderparser.impl.parser.OrderParser;

@SpringBootApplication
public class UnlimintApplication {


	public static void main(String[] args) {
		SpringApplication.run(UnlimintApplication.class, args);
		
	}

	
//	private static final String FILE_PATH = "/dacx/var/ameyo/dacxdata/file_storage/bulk_customer_upload/";
//
//	@Autowired
//	OrderParser orderParser;
//	@PostConstruct
//	public void test1() {
//		List<File> files = new ArrayList<File>();
//		files.add(getCsvFile("1"));
//		files.add(getJsonFile("test"));
//		orderParser.parseOrder(files);
//	}
//
//	public File getCsvFile(String fileId) {
//		String filePath = FILE_PATH + "abc_" + fileId + ".csv";
//		File file = new File(filePath);
//		return file;
//	}
//
//	public File getJsonFile(String name) {
//		String filePath = FILE_PATH + "" + name + ".json";
//		File file = new File(filePath);
//		return file;
//	}

}
