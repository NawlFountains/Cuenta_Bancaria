package Programa;

import GUI.Login;

public class Tester {
	public static void main(String [] args) {
		try {
			Login inicio= new Login();
			inicio.setVisible(true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
