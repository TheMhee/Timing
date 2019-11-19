package Application;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.HashMap;

public class DayBox extends JPanel {

    private JLabel dayTxt, workTxt1, workTxt2, workTxt3;
    private int index;
    private String inDate, inDay, inYear, inMonth;

    private String dTxt[] = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private Color dColor[] = new Color[]{new Color(200, 200, 200), 
                                        new Color(242, 232, 141), 
                                        new Color(255, 139, 147), 
                                        new Color(3, 192, 60), 
                                        new Color(243, 154, 39), 
                                        new Color(87, 154, 190), 
                                        new Color(151, 110, 215)};

    private static Calendar c = Calendar.getInstance();
    private static int year = c.get(c.YEAR);
    private static int month = c.get(c.MONTH);

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getInDate() {
        return inDate;
    }

    public String getInDay() {
        return inDay;
    }

    public String getInYear() {
        return inYear;
    }

    public String getInMonth() {
        return inMonth;
    }

    public JLabel getWorkTxt1() {
        return workTxt1;
    }

    public JLabel getWorkTxt2() {
        return workTxt2;
    }

    private static int datePos = 1;

    public DayBox(JPanel jp, int i) {

        dayTxt = new JLabel();
        workTxt1 = new JLabel();
        workTxt2 = new JLabel();
        workTxt3 = new JLabel();
        index = i;
        inDate = "";
        inDay = "";
        inMonth = "";
        inYear = "";
        //default year
//		Calendar c = Calendar.getInstance();
//		int year = c.get(c.YEAR);
//		int month = c.get(c.MONTH);

        this.setPreferredSize(new Dimension(200, 160));

        showDay(i, year, month);

        dayTxt.setFont(new Font("Serif", Font.BOLD, 20));
        this.setLayout(new GridLayout(4, 1));
        this.add(dayTxt);
        this.add(workTxt1);
        this.add(workTxt2);
        this.add(workTxt3);
        this.setOpaque(true);
    }

    public void addDay(String s) {
        this.dayTxt.setText(s);
    }

    public void addDay(double s) {
        this.dayTxt.setText(s + "");
    }

    public void addWork(String point, String text) {
        if (point.equals("1")) {
            this.workTxt1.setText(text);
        } else if (point.equals("2")) {
            this.workTxt2.setText(text);
        } else if (point.equals("3")) {
            this.workTxt2.setText(text);
        }
    }

    public JLabel getDayTxt() {
        return dayTxt;
    }

    public void showDay(int i, int year, int month) {
        if (i == 0) {
            datePos = 1;
        }
        if ((datePos <= JsonManager.getMonthLen(year + "", month + "") && (dTxt[i % 7].equals(JsonManager.getDayOfWeek(year, month, datePos))))) {
            
            this.setBackground(new Color(200, 200, 200));
            workTxt1.setText(JsonManager.getActTitle(year + "", month + "", datePos + "", 1));
            if (!JsonManager.getActTimeStart(year + "", month + "", datePos + "", 1).equals("")&&!(JsonManager.getActTimeStart(year + "", month + "", datePos + "", 1).equals("00.00")&&JsonManager.getActTimeEnd(year + "", month + "", datePos + "", 1).equals("00.00"))) {
                workTxt2.setText(JsonManager.getActTimeStart(year + "", month + "", datePos + "", 1) + " - " + JsonManager.getActTimeEnd(year + "", month + "", datePos + "", 1));
            } else {
                workTxt2.setText("");
            }
            dayTxt.setText(datePos + "");
            inDate = datePos + "";
            inDay = JsonManager.getDayOfWeek(year, month, datePos);
            inMonth = JsonManager.getMonthName(year + "", month + "");
            inYear = year + "";
            datePos += 1;
//			System.out.println(inDate+" "+inDay+" "+inMonth+" "+inYear+" ");
        }
        else{
            this.setBackground(new Color(230, 230, 230));
        }

    }

    public int getIndex() {
        return index;
    }

    public void clearDay() {
        dayTxt.setText("");
        workTxt1.setText("");
        workTxt2.setText("");
    }
}
