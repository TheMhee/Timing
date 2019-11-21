package Application;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.*;

public class UIPanel {

    private JDesktopPane desktopPane;

    private JFrame fr;
    private JPanel mainPanel, firstPanel, leftPanel, cardCalendar, cardReminder, centerPanel;
    private JPanel calendarHead, calendarBody, cHead1, cHead2, cHead3;
    private JButton menuBtn, calendarBtn, reminderBtn, pMonth, nMonth, acEdit, acSave, pYear, nYear;
    private JLabel dayInfo_day, dayInfo_month, DayInfo_year, DayInfo_date, acTime, showMonth, acInfo1;
    private JComboBox acStartTime_h, acEndTime_h, acStartTime_m, acEndTime_m;
    private JTextArea dayInfo_insert, acTextInfo;

    private String welName; //for name in SQL

    private CardLayout cards;
    private String[] dTxt;
    private DayHead[] dayHead;
    private DayBox[] dayBox;
    private Color[] dColor;

    private JInternalFrame frame1, frame2, frame3, loginFrame;

    private acInfo dayInfo;
    private acEditInfo editInfo;

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public CardLayout getCards() {
        return cards;
    }

    public DayBox[] getDayBox() {
        return dayBox;
    }

    public void init(String name) {

        welName = name;
        desktopPane = new JDesktopPane();
        frame1 = new JInternalFrame("", true, false, false, false);
        frame2 = new JInternalFrame("", false, false, false, false);
        frame3 = new JInternalFrame("", false, false, false, false);
        loginFrame = new JInternalFrame("", false, false, false, false);
        frame2.setBorder(null);

        //remove frame2's title bar
        ((javax.swing.plaf.basic.BasicInternalFrameUI) frame2.getUI()).setNorthPane(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) frame1.getUI()).setNorthPane(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) frame3.getUI()).setNorthPane(null);

        fr = new JFrame("Timing");
        mainPanel = new JPanel();
        leftPanel = new JPanel();
        menuBtn = new JButton();
        calendarBtn = new JButton("calender");
        reminderBtn = new JButton("reminder");

        cardCalendar = new JPanel();
        cardReminder = new JPanel();
        centerPanel = new JPanel();
        cards = new CardLayout();

        calendarHead = new JPanel();
        calendarBody = new JPanel();
        cHead1 = new JPanel();
        cHead2 = new JPanel();
        cHead3 = new JPanel();
        pMonth = new JButton("<");
        pYear = new JButton("<<");
        showMonth = new JLabel();
        nMonth = new JButton(">");
        nYear = new JButton(">>");

        //check calendarJSON file
        JsonManager.checkFile();

        fr.setResizable(false);

        //get screenSize 
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sHeight = (int) screenSize.getHeight();
        int sWidth = (int) screenSize.getWidth();

        //Panel and Component management
        mainPanel.setLayout(new BorderLayout());
        centerPanel.setLayout(new BorderLayout());
        leftPanel.setLayout(new GridLayout(4, 1));
        cardCalendar.setLayout(new BorderLayout());
        cardReminder.setLayout(new BorderLayout());

        leftPanel.setBackground(Color.BLACK);
        leftPanel.setOpaque(true);
        centerPanel.setBackground(new Color(220, 250, 250));
        centerPanel.setOpaque(true);
//		cardReminder.setBackground(new Color(220, 250, 0));
//		cardReminder.setOpaque(true);
        cardCalendar.setBackground(new Color(220, 250, 250));
        cardCalendar.setOpaque(true);

//		fr.setPreferredSize(new Dimension((int)(sWidth/1.2), (int)(sHeight/1.1)));
        Dimension di = fr.getPreferredSize();
        int fWidth = di.width;
        int fHeight = di.height;
//		centerPanel.setPreferredSize(new Dimension(fWidth-(fWidth/8), fHeight));
//		frame2.setPreferredSize(new Dimension((int)(fWidth-(fWidth*0.01)), (int)(fHeight-(fHeight*0.039))));
//		leftPanel.setPreferredSize(new Dimension(fWidth/8, fHeight));
//		reminderBtn.setPreferredSize(new Dimension(fWidth/8, fHeight));
//		calendarBtn.setPreferredSize(new Dimension(fWidth/8, fHeight));
        reminderBtn.setName("Login");
        calendarBtn.setName("Calendar");

        //EventListener
        EventHandler eh = new EventHandler(this);

        calendarBtn.addActionListener(eh);
        reminderBtn.addActionListener(eh);
        pMonth.addActionListener(eh);
        nMonth.addActionListener(eh);
        pYear.addActionListener(eh);
        nYear.addActionListener(eh);

        //calendar panel
        cardCalendar.setLayout(new BorderLayout());

        cHead1.setLayout(new FlowLayout());
        cHead2.setLayout(new FlowLayout());
        cHead3.setLayout(new FlowLayout());

        //plz add name form database
        JLabel WelcomeHeadLabel = new JLabel("Welcome " + welName + ".  Have a good day!");
        System.out.println(cHead1.getFont());
        WelcomeHeadLabel.setFont(new Font(cHead1.getFont().getName(), Font.PLAIN, 16));
        cHead1.add(WelcomeHeadLabel);
        //clock
//        Clock cl = new Clock();
//        Thread t = new Thread(cl);
//        t.start();
        //clock
        cHead2.add(new WelcomePanel());

        cHead3.add(pYear);
        cHead3.add(pMonth);
        cHead3.add(new JLabel("        "));
        cHead3.add(showMonth);
        cHead3.add(new JLabel("        "));
        cHead3.add(nMonth);
        cHead3.add(nYear);

        calendarHead.setLayout(new GridLayout(2, 1));
        calendarBody.setLayout(new GridLayout(7, 7, 2, 2));
        fr.setSize(1600, 1000);
        frame2.setSize(1585, 960);
        centerPanel.setSize(1600, 1000);
        System.out.println(centerPanel.getWidth());
        calendarHead.setPreferredSize(new Dimension(1600, 100));
        calendarBody.setPreferredSize(new Dimension(1600, 900));
        calendarBody.setBackground(new Color(230, 230, 224));
        cardReminder.setOpaque(true);
        System.out.println(calendarBody.getSize());

        dayHead = new DayHead[7];
        dColor = new Color[]{new Color(194, 59, 35), new Color(234, 218, 82), new Color(255, 139, 147), new Color(3, 192, 60), new Color(243, 154, 39), new Color(87, 154, 190), new Color(151, 110, 215)};
        for (int i = 0; i < 7; i++) {
            dayHead[i] = new DayHead(calendarBody, i);
            dayHead[i].addMouseListener(eh);
//			dayHead[i].addDay(dTxt[i]);
            calendarBody.add(dayHead[i]);
        }
        dayBox = new DayBox[42];
        for (int i = 0; i < 42; i++) {
            dayBox[i] = new DayBox(calendarBody, i);

//			calendarBody.add(dayBox[i]);
            dayBox[i].addMouseListener(eh);
            calendarBody.add(dayBox[i]);
        }
        //default time
        Calendar c = Calendar.getInstance();
        int year = c.get(c.YEAR);
        int month = c.get(c.MONTH);

        //SQL TEST
//        db.addYearToDb(year);
        showMonth.setText(JsonManager.getMonthName(year + "", month + "") + " - " + year);
        showMonth.setFont(new Font(cHead1.getFont().getName(), Font.PLAIN, 16));

        calendarHead.add(cHead2);
        calendarHead.add(cHead3);
        cardCalendar.add(calendarHead, BorderLayout.NORTH);
        cardCalendar.add(calendarBody, BorderLayout.CENTER);

//        dayInfo = new JPanel();
//        dayInfo_day = new JLabel();
//        dayInfo_month = new JLabel("111111111111111111111111111");
//        DayInfo_date = new JLabel();
//        DayInfo_year = new JLabel();
        acTime = new JLabel();
        acEdit = new JButton("Edit");
        acSave = new JButton("Save");
        acInfo1 = new JLabel();
        acTextInfo = new JTextArea();
        acTextInfo.setEditable(false);
        acTextInfo.setSize(acTextInfo.getWidth(), acTextInfo.getHeight() * 2);

// 11/21/2019 move all day Info into acInfo Class
//        dayInfo.setLayout(new GridLayout(7, 1));
//        dayInfo.add(dayInfo_day);
//        JPanel pa = new JPanel(new FlowLayout());
//        pa.add(DayInfo_date);
//        pa.add(dayInfo_month);
//        pa.add(DayInfo_year);
//        dayInfo.add(pa);
//        dayInfo.add(new JLabel("Activity : "));
//        dayInfo.add(acTextInfo);
//        dayInfo.add(new JLabel("Time : "));
//        dayInfo.add(acTime);
//        dayInfo.add(new JPanel(new FlowLayout()).add(acEdit));
// 11/21/2019 move all day Info into acInfo Class
        dayInfo = new acInfo();
        frame1.add(dayInfo);

//        dayInfo_insert = new JTextArea();
//        dayInfo_insert.setLineWrap(true);
//        dayInfo_insert.setWrapStyleWord(true);
//        JPanel pb = new JPanel(new GridLayout(4, 1));
//        JPanel temp = new JPanel(new GridLayout(2, 1));
//        JPanel pc = new JPanel(new FlowLayout());
//        JPanel pd = new JPanel(new FlowLayout());
//        acEndTime_h = new JComboBox();
//        acEndTime_m = new JComboBox();
//        acStartTime_h = new JComboBox();
//        acStartTime_m = new JComboBox();
//        
//        JPanel toAddEditLabel = new JPanel(new GridLayout(2, 1));
//        toAddEditLabel.add(new JLabel(""));
//        toAddEditLabel.add(new JLabel("Edit your activity"));
//        pb.add(toAddEditLabel);
//        pb.add(dayInfo_insert);
//        dayInfo_insert.setSize(250, 250);
//        pc.add(new JLabel("Time : "));
//        pc.add(acStartTime_h);
//        pc.add(acStartTime_m);
//        pd.add(new JLabel(" To "));
//        pd.add(acEndTime_h);
//        pd.add(acEndTime_m);
//        temp.add(pc);
//        temp.add(pd);
//        pb.add(temp);
//        JPanel toAddAcSave = new JPanel(new GridLayout(2, 1));
//        toAddAcSave.add(new JLabel(""));
//        toAddAcSave.add(acSave);
//        pb.add(toAddAcSave);

        editInfo = new acEditInfo();
        for (int i = 0; i < 24; i++) {
            editInfo.getAcEndTime_h().addItem(String.format("%02d", i));
            editInfo.getAcStartTime_h().addItem(String.format("%02d", i));
        }
        for (int i = 0; i < 60; i++) {
            editInfo.getAcEndTime_m().addItem(String.format("%02d", i));
            editInfo.getAcStartTime_m().addItem(String.format("%02d", i));
        }
        frame3.setSize(270, 320);
        frame3.add(editInfo, BorderLayout.CENTER);

        //Listener
        dayInfo.getEditButton().addActionListener(eh);
        editInfo.getSaveButton().addActionListener(eh);
        frame3.addInternalFrameListener(eh);
        frame1.addInternalFrameListener(eh);

        //Add Component
        leftPanel.add(calendarBtn);
        leftPanel.add(reminderBtn);
        centerPanel.add(cardCalendar, BorderLayout.CENTER);
//	centerPanel.add(cardReminder, "cardReminder");

//	frame2.add(leftPanel, BorderLayout.WEST);
        frame2.add(centerPanel, BorderLayout.CENTER);
        frame1.setSize(270, 300);
//        frame1.pack();
//		frame2.pack();
        frame2.setVisible(true);

        frame1.setVisible(false);
        frame1.setResizable(false);
        frame3.setResizable(false);
        frame3.setVisible(false);
        frame1.setLocation(500, 500);
        frame3.setLocation(900, 500);

        desktopPane.add(frame3);
        desktopPane.add(frame1);
        desktopPane.add(frame2);
        desktopPane.add(loginFrame);

//		fr.getContentPane().add(centerPanel, BorderLayout.CENTER);
//		fr.getContentPane().add(leftPanel, BorderLayout.WEST);
        fr.add(desktopPane, BorderLayout.CENTER);

        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public acEditInfo getEditInfo() {
        return editInfo;
    }

    public JPanel getCalendarHead() {
        return calendarHead;
    }

    public void setCalendarHead(JPanel calendarHead) {
        this.calendarHead = calendarHead;
    }

    public JButton getpYear() {
        return pYear;
    }

    public DayHead[] getDayHead() {
        return dayHead;
    }

    public void setDayHead(DayHead[] dayHead) {
        this.dayHead = dayHead;
    }

    public void setpYear(JButton pYear) {
        this.pYear = pYear;
    }

    public JButton getnYear() {
        return nYear;
    }

    public void setnYear(JButton nYear) {
        this.nYear = nYear;
    }

    public JInternalFrame getLoginFrame() {
        return loginFrame;
    }

    public void setLoginFrame(JInternalFrame loginFrame) {
        this.loginFrame = loginFrame;
    }

    public JLabel getShowMonth() {
        return showMonth;
    }

    public JLabel getAcInfo1() {
        return acInfo1;
    }

    public JLabel getAcTime() {
        return acTime;
    }

    public JComboBox getAcStartTime_h() {
        return acStartTime_h;
    }

    public JComboBox getAcEndTime_h() {
        return acEndTime_h;
    }

    public JComboBox getAcStartTime_m() {
        return acStartTime_m;
    }

    public JComboBox getAcEndTime_m() {
        return acEndTime_m;
    }

    public JButton getAcSave() {
        return acSave;
    }

    public JButton getAcEdit() {
        return acEdit;
    }

    public acInfo getDayInfo() {
        return dayInfo;
    }

    public JTextArea getDayInfo_insert() {
        return dayInfo_insert;
    }

    public JButton getCalendarBtn() {
        return calendarBtn;
    }

    public JInternalFrame getFrame1() {
        return frame1;
    }

    public JInternalFrame getFrame2() {
        return frame2;
    }

    public JInternalFrame getFrame3() {
        return frame3;
    }

    public JButton getReminderBtn() {
        return reminderBtn;
    }

    public JButton getpMonth() {
        return pMonth;
    }

    public JButton getnMonth() {
        return nMonth;
    }

    public static void main(String[] args) {
        new UIPanel().init("Guest");
    }

}
