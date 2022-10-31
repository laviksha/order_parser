package src.main.java.com.unlimint.orderparser.impl.validator;

import java.util.Map;

import src.main.java.com.unlimint.orderparser.beans.OrderParserOutput;
import src.main.java.com.unlimint.orderparser.constants.ErrorConstants;
import src.main.java.com.unlimint.orderparser.constants.FileConstants;
import src.main.java.com.unlimint.orderparser.impl.GenerateUniqueuId;

public class ValidateAndConvertCsvResult implements Runnable {
	Map<String, Object> record;
	String fileName;
	Integer line;

	public ValidateAndConvertCsvResult(Map<String, Object> record, String fileName, Integer line) {
		this.record = record;
		this.fileName = fileName;
		this.line = line;
	}

	private String validateRecord(Map<String, Object> record) {
		String result = "OK";

		if (record.size() != FileConstants.HEADERS.length) {
			result = ErrorConstants.ALL_VALUES_REQUIRED;
		} else {
			Object order_id = record.get(FileConstants.ORDER_ID);
			try {
				Integer.valueOf((String) order_id);
			} catch (Exception e) {
				result = ErrorConstants.ORDER_ID_MUST_BE_INTEGER;
				return result;
			}
			Object amount = record.get(FileConstants.AMOUNT);
			if (!(amount instanceof Double || amount instanceof Integer)) {
				try {
					Double.valueOf((String) amount);
				} catch (Exception e) {
					result = ErrorConstants.AMOUNT_MUST_BE_NUMBER;
					return result;
				}
			}
		}
		return result;

	}

	@Override
	public void run() {
		String result = validateRecord(record);
		OrderParserOutput output = new OrderParserOutput(GenerateUniqueuId.getId(), record.get(FileConstants.ORDER_ID),
				record.get(FileConstants.AMOUNT), record.get(FileConstants.COMMENT), fileName, line, result,
				record.get(FileConstants.CURRENCY));
		System.out.println(output.toString());

	}
}
