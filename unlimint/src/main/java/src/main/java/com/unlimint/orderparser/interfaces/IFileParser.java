package src.main.java.com.unlimint.orderparser.interfaces;

import java.io.File;

public interface IFileParser {
	public void parseFile(File file);
	public static final int CUSTOMER_INSERT_BATCH_SIZE_1000 = 1000;

}
