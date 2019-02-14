package services;

public class OperationService {
	public static double addition(double a, double b) {
		return a + b;
	}
	public static double multiplication(double a, double b) {
		return a * b;
	}
	
	public static double division(double a, double b) {
		return a / b;
	}
	
	public static double calcul(double a, double b, String operation) {
		switch (operation) {
		case "addition":
			return addition(a, b);
		case "multiplication":
			return multiplication(a, b);
		case "division":
			return division(a, b);
		default:
			return 0;
		}
	}
}
