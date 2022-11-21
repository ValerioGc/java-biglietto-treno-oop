package org.italy.trains;

public class Ticket {
	private int pKm;
	private int pAge;
	
	private static final double KM_PRICE = 0.21;
	private static final int UNDER_DISCOUNT = 20;
	private static final int OVER_DISCOUNT = 40;
	
	public Ticket(int pAge, int pKm) throws Exception {
		setpAge(pAge);
//		isValidAge(pAge);
		setpKm(pKm);
//		isValidKm(pKm);
	}

	public int getpAge() {
		return pAge;
	}
	
	public void setpAge(int pAge) throws Exception {
		if ((pAge < 0)|| (pAge > 120)) {			
			throw new Exception("Inserisci un età valida");
		}
		this.pAge = pAge;
	}
	
	public int getpKm() {
		return pKm;
	}

	public void setpKm(int pKm) throws Exception {
		
		if (pKm == 0){			
			throw new Exception("Inserisci una distanza valida");
		}
		this.pKm = pKm;
	}

	
//	public void isValidAge(int pAge) throws Exception {
//		if ((pAge < 1) || (pAge > 120)) {			
//			throw new Exception("Inserisci un età valida");
//		}
//		else {
//			setpAge(pAge);
//		}
//	} 	
//	public void isValidKm(double pKm) throws Exception {
//		if (pKm == 0) {			
//			throw new Exception("Inserisci un età valida");
//		}
//		else {
//			setpKm(pKm);
//		}
//	}
	
	public double calcPrice() {
		double price = ((double)pKm) * KM_PRICE;
		double discount = calcDiscount(price, pAge);
		return price - discount;
	}
	
	
	private double calcDiscount(double price, int age) {
		double discount;
		if (pAge <= 18) {			
			discount = (price * UNDER_DISCOUNT) / 100;
		} else if (pAge >= 65 ) {
			discount = (price * OVER_DISCOUNT) / 100;
		} else {
			discount = 0;
		}
		return discount;
	}
	
	@Override
	public String toString() {
		return "Distanza da percorrere: " + pKm + "km"
				+ "\nEtà passeggero: " + pAge + " anni"
				+ "\nPrezzo: " + calcPrice() + " euro"
				+ "\n----------------------------------\n";
	}
}
