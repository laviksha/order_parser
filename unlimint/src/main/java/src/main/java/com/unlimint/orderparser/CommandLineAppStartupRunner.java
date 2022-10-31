package src.main.java.com.unlimint.orderparser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import src.main.java.com.unlimint.orderparser.impl.parser.OrderParser;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
	@Autowired
	OrderParser orderParser;

    @Override
    public void run(String...args) throws Exception {
    	List<File> filesList = new ArrayList<File>();
		for (String filePath : args) {
			File file = new File(filePath);
			filesList.add(file);
		}
		orderParser.parseOrder(filesList);

    }
}
