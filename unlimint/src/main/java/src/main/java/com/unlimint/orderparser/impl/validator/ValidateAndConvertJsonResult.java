package src.main.java.com.unlimint.orderparser.impl.validator;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.json.simple.JSONObject;

import src.main.java.com.unlimint.orderparser.beans.OrderParserOutput;
import src.main.java.com.unlimint.orderparser.constants.ErrorConstants;
import src.main.java.com.unlimint.orderparser.constants.FileConstants;
import src.main.java.com.unlimint.orderparser.impl.GenerateUniqueuId;

public class ValidateAndConvertJsonResult implements Runnable {
	JSONObject jsonObject;
	String fileName;
	int line;

	public ValidateAndConvertJsonResult(JSONObject jsonObject, String fileName, int line) {
		this.jsonObject = jsonObject;
		this.fileName = fileName;
		this.line = line;
	}

	public String validateJsonRecord(JSONObject jsonObject, Map<String, Object> record) {

		Object orderId = jsonObject.get(FileConstants.ORDER_ID) == null ? null : jsonObject.get(FileConstants.ORDER_ID);
		record.put(FileConstants.ORDER_ID, orderId);
		Object comment = jsonObject.get(FileConstants.COMMENT) == null ? null : jsonObject.get(FileConstants.COMMENT);
		record.put(FileConstants.COMMENT, comment);
		Object amount = jsonObject.get(FileConstants.AMOUNT) == null ? null : jsonObject.get(FileConstants.AMOUNT);
		record.put(FileConstants.AMOUNT, amount);
		Object currency = jsonObject.get(FileConstants.CURRENCY) == null ? null
				: jsonObject.get(FileConstants.CURRENCY);
		record.put(FileConstants.CURRENCY, currency);
		String result = "OK";
		if (ObjectUtils.isEmpty(orderId)) {
			return ErrorConstants.ORDER_ID_CANNOT_BE_BLANK;
		} else if (!(orderId instanceof Long)) {
			return ErrorConstants.ORDER_ID_MUST_BE_INTEGER;
		} else if (ObjectUtils.isEmpty(amount)) {
			return ErrorConstants.AMOUNT_CANNOT_BE_BLANK;
		} else if (ObjectUtils.isEmpty(currency)) {
			return ErrorConstants.CURENCY_CANNOT_BE_BLANK;
		} else if (ObjectUtils.isEmpty(comment)) {
			return ErrorConstants.COMMENT_CANNOT_BE_BLANK;
		} else if (!(amount instanceof Double || amount instanceof Integer)) {
			try {
				Double.valueOf((String) amount);
			} catch (Exception e) {
				result = ErrorConstants.AMOUNT_MUST_BE_NUMBER;
				return result;
			}
		}
		return result;
	}

	@Override
	public void run() {
		Map<String, Object> record = new HashMap<String, Object>();
		String result = validateJsonRecord(jsonObject, record);
		OrderParserOutput output = new OrderParserOutput(GenerateUniqueuId.getId(), record.get(FileConstants.ORDER_ID),
				record.get(FileConstants.AMOUNT), record.get(FileConstants.COMMENT), fileName, line, result,
				record.get(FileConstants.CURRENCY));
		System.out.println(output.toString());

	}

}
