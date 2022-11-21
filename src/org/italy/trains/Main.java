package org.italy.trains;

public class Main {

	public static void main(String[] args) {
		try {
			Ticket tk1 = new Ticket(25,100);
			System.out.println(tk1);
			Ticket tk2 = new Ticket(15,100);
			System.out.println(tk2);
			Ticket tk3 = new Ticket(75,100);
			System.out.println(tk3);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
