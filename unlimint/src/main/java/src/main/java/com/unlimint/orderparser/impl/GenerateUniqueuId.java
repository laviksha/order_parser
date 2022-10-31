package src.main.java.com.unlimint.orderparser.impl;

public class GenerateUniqueuId {
	private static Integer id = 0;

	public static Integer getId() {
		id++;
		return id;
	}
}
