package src.main.java.com.unlimint.orderparser.beans;

public class OrderParserOutput {
	private Integer id;
	private Object orderId;
	private Object amount;
	private Object comment;
	private String fileName;
	private Object line;
	private String result;
	private Object currency;


	public OrderParserOutput(Integer id, Object orderId, Object amount, Object comment, String fileName, Object line,
			String result,Object currency) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.amount = amount;
		this.comment = comment;
		this.fileName = fileName;
		this.line = line;
		this.result = result;
		this.currency=currency;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Object getOrderId() {
		return orderId;
	}


	public void setOrderId(Object orderId) {
		this.orderId = orderId;
	}


	public Object getAmount() {
		return amount;
	}


	public void setAmount(Object amount) {
		this.amount = amount;
	}


	public Object getComment() {
		return comment;
	}


	public void setComment(Object comment) {
		this.comment = comment;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public Object getLine() {
		return line;
	}


	public void setLine(Object line) {
		this.line = line;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	@Override
	public String toString() {
		return "{\"id\":" + id + ", \"orderId\":" + orderId + ", \"amount\":" + amount + ",\"curency\":\""+currency+ "\", \"comment\":\"" + comment + "\", \"fileName\":\""
				+ fileName + "\", \"line\":" + line + ", \"result\":\"" + result + "\"}";
	}

}
