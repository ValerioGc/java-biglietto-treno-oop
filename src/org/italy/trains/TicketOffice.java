package org.italy.trains;
  
import java.util.Scanner;

public class TicketOffice {

	public static void main(String[] args) {
		try {
			Scanner scn = new Scanner(System.in);
			System.out.println("Il biglietto è flessibile?");
			String userflex = scn.nextLine();
			System.out.println("Inserisci la tua età");
			int userAge = scn.nextInt();
			System.out.println("Inserisci la distanza da percorrere");
			int userKm = scn.nextInt();
			boolean flex;
			if (userflex.equals("si")) { 
				flex = true;
			} else {
				flex = false;
			}
			scn.close();
			Ticket tk1 = new Ticket(userAge, userKm, flex);
			System.out.println(tk1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
