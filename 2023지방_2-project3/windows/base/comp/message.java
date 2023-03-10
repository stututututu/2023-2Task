package base.comp;

import javax.swing.JOptionPane;

public class message {

	public message() {
		// TODO Auto-generated constructor stub
		
	}

	public static void error(String txt) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, txt, "경고", JOptionPane.ERROR_MESSAGE);
		
	}
	
	public static void info(String txt) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, txt, "정보", JOptionPane.INFORMATION_MESSAGE);
		
	}

}
