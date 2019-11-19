package Application;

import javax.swing.*;
import java.awt.*;

public class DayHead extends JPanel{
	private JLabel dayTxt;

	String dTxt[] = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
	Color dColor[] = new Color[] {new Color(194, 59, 35), new Color(234, 218, 82), new Color(255, 139, 147), new Color(3, 192, 60), new Color(243, 154, 39), new Color(87, 154, 190), new Color(151, 110, 215)};
	public DayHead(JPanel jp, int i) {
		dayTxt = new JLabel();
		
		//System.out.print(jp.getPreferredSize().width + " " + jp.getPreferredSize().height);
		dayTxt.setHorizontalAlignment(SwingConstants.CENTER);
		dayTxt.setText(dTxt[i]);
		
		this.setPreferredSize(new Dimension(jp.getPreferredSize().width/8 , (int)(jp.getPreferredSize().height/6.5)));
		this.setLayout(new GridLayout(1, 1));
		this.setBackground(dColor[i]);
		this.setOpaque(true);
		this.add(dayTxt);

	}
	public void addDay(String s) {
		dayTxt.setText(s);
	}
}
