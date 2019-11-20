package Application;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class EventHandler implements ActionListener, MouseListener, InternalFrameListener {

    private UIPanel up;
    private DayBox dbTemp;
    private int boxStat = 0;
    private int insertStat = 0;

    public EventHandler(UIPanel u) {
        up = u;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == up.getCalendarBtn()) {
            up.getCards().show(up.getCenterPanel(), "cardCalendar");
        } //when login
        else if (e.getSource() == up.getReminderBtn()) {
            try {
                up.getLoginFrame().setVisible(true);
                up.getLoginFrame().setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(EventHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == up.getnMonth()) {
            if (up.getDayBox()[1].getMonth() < 11) {
                up.getDayBox()[1].setMonth(up.getDayBox()[1].getMonth() + 1);
            } else {
                up.getDayBox()[1].setMonth(up.getDayBox()[1].getMonth() - 11);
                up.getDayBox()[1].setYear(up.getDayBox()[1].getYear() + 1);
            }
            JsonManager.checkFile(up.getDayBox()[1].getYear());
            up.getShowMonth().setText(JsonManager.getMonthName(up.getDayBox()[1].getYear() + "", up.getDayBox()[1].getMonth() + "") + "/" + up.getDayBox()[1].getYear());
            for (int i = 0; i < 42; i++) {
                up.getDayBox()[i].clearDay();
                up.getDayBox()[i].showDay(i, up.getDayBox()[1].getYear(), up.getDayBox()[1].getMonth());
            }

        } else if (e.getSource() == up.getpMonth()) {
            if (up.getDayBox()[1].getMonth() > 1) {
                up.getDayBox()[1].setMonth(up.getDayBox()[1].getMonth() - 1);
            } else {
                up.getDayBox()[1].setMonth(up.getDayBox()[1].getMonth() + 11);
                up.getDayBox()[1].setYear(up.getDayBox()[1].getYear() - 1);
            }
            JsonManager.checkFile(up.getDayBox()[1].getYear());
            up.getShowMonth().setText(JsonManager.getMonthName(up.getDayBox()[1].getYear() + "", up.getDayBox()[1].getMonth() + "") + "/" + up.getDayBox()[1].getYear());
            for (int i = 0; i < 42; i++) {
                up.getDayBox()[i].clearDay();
                up.getDayBox()[i].showDay(i, up.getDayBox()[1].getYear(), up.getDayBox()[1].getMonth());
            }
        } else if (e.getSource() == up.getpYear()) {
            up.getDayBox()[1].setYear(up.getDayBox()[1].getYear() - 1);
            JsonManager.checkFile(up.getDayBox()[1].getYear());
            up.getShowMonth().setText(JsonManager.getMonthName(up.getDayBox()[1].getYear() + "", up.getDayBox()[1].getMonth() + "") + "/" + up.getDayBox()[1].getYear());
            for (int i = 0; i < 42; i++) {
                up.getDayBox()[i].clearDay();
                up.getDayBox()[i].showDay(i, up.getDayBox()[1].getYear(), up.getDayBox()[1].getMonth());
            }
        } else if (e.getSource() == up.getnYear()) {
            up.getDayBox()[1].setYear(up.getDayBox()[1].getYear() + 1);
            JsonManager.checkFile(up.getDayBox()[1].getYear());
            up.getShowMonth().setText(JsonManager.getMonthName(up.getDayBox()[1].getYear() + "", up.getDayBox()[1].getMonth() + "") + "/" + up.getDayBox()[1].getYear());
            for (int i = 0; i < 42; i++) {
                up.getDayBox()[i].clearDay();
                up.getDayBox()[i].showDay(i, up.getDayBox()[1].getYear(), up.getDayBox()[1].getMonth());
            }

        } else if (e.getSource() == up.getAcEdit()) {
            up.getFrame3().setVisible(true);
            up.getDayInfo_insert().setText(up.getAcInfo1().getText());
            if (dbTemp.getIndex() % 7 < 4) {
                int x2 = dbTemp.getX() + dbTemp.getWidth();
                int y2 = dbTemp.getY() + dbTemp.getHeight() - 20;
                up.getFrame3().setLocation(x2, y2);
            } else {
                int x2 = dbTemp.getX() - dbTemp.getWidth();
                int y2 = dbTemp.getY() + dbTemp.getHeight() - 20;
                up.getFrame3().setLocation(x2, y2);
            }
            if (insertStat == 0) {
                up.getFrame3().setVisible(true);
                insertStat = 1;
            } else {
                up.getFrame3().setVisible(false);
                insertStat = 0;
            }
            try {
                up.getFrame3().setSelected(true);
            } catch (PropertyVetoException e1) {
                e1.printStackTrace();
            }

        } else if (e.getSource() == up.getAcSave()) {
            System.out.println(up.getDayInfo_insert().getText());
            //System.out.print(dbTemp.getMonth());
            JsonManager.setActTitle(dbTemp.getYear() + "", dbTemp.getMonth() + "", dbTemp.getInDate(), up.getDayInfo_insert().getText());
            JsonManager.setActTime(dbTemp.getYear() + "", dbTemp.getMonth() + "", dbTemp.getInDate(),
                    up.getAcStartTime_h().getSelectedItem().toString() + "." + up.getAcStartTime_m().getSelectedItem().toString(),
                    up.getAcEndTime_h().getSelectedItem().toString() + "." + up.getAcEndTime_m().getSelectedItem().toString());
            for (int i = 0; i < 42; i++) {
                up.getDayBox()[i].clearDay();
                up.getDayBox()[i].showDay(i, up.getDayBox()[1].getYear(), up.getDayBox()[1].getMonth());
            }
            up.getAcInfo1().setText(dbTemp.getWorkTxt1().getText());
            up.getAcTime().setText(dbTemp.getWorkTxt2().getText());
            up.getDayInfo_insert().setText("");
            up.getAcStartTime_h().setSelectedIndex(0);
            up.getAcStartTime_m().setSelectedIndex(0);
            up.getAcEndTime_h().setSelectedIndex(0);
            up.getAcEndTime_m().setSelectedIndex(0);

            up.getFrame1().setVisible(false);
            up.getFrame3().setVisible(false);
            boxStat = 0;
            insertStat = 0;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        if (e.getSource().equals(up.getCalendarHead())) {
//            up.getFrame1().setVisible(false);
//            up.getFrame3().setVisible(false);
//            boxStat = 0;
//        }
        if (e.getSource().getClass().getSimpleName() == "DayHead") {
            up.getFrame1().setVisible(false);
            up.getFrame3().setVisible(false);
            boxStat = 0;
        }
        dbTemp = (DayBox) e.getSource();
        up.getAcInfo1().setText(dbTemp.getWorkTxt1().getText());
        up.getAcTime().setText(dbTemp.getWorkTxt2().getText());
        if (dbTemp.getIndex() % 7 < 4) {
            int x2 = dbTemp.getX() + dbTemp.getWidth();
            int y2 = dbTemp.getY() + dbTemp.getHeight() - 20;
            up.getFrame1().setLocation(x2, y2);
        } else {
            int x2 = dbTemp.getX() - dbTemp.getWidth();
            int y2 = dbTemp.getY() + dbTemp.getHeight() - 20;
            up.getFrame1().setLocation(x2, y2);
        }
        if (!dbTemp.getDayTxt().getText().equals("")) {

            if (boxStat == 0) {
                up.getFrame1().setVisible(true);
                boxStat = 1;
            } else {
//                up.getFrame1().setVisible(false);
//                up.getFrame3().setVisible(false);
//                boxStat = 0;
//                insertStat = 0;
            }

            try {
                up.getFrame1().setSelected(true);
            } catch (PropertyVetoException e1) {
                e1.printStackTrace();
            }
        } else {
//            up.getFrame1().setVisible(false);
//            up.getFrame3().setVisible(false);
//            boxStat = 0;
//            insertStat = 0;
        }
        up.getDayInfo_date().setText(dbTemp.getInDate());
        up.getDayInfo_day().setText(dbTemp.getInDay());
        up.getDayInfo_year().setText(dbTemp.getInYear());
        up.getDayInfo_month().setText(dbTemp.getInMonth());

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameIconified(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {
        if (!e.getSource().getClass().getSimpleName().equals("DayBox")){
        up.getFrame1().setVisible(false);
        up.getFrame3().setVisible(false);
        boxStat = 0;
        insertStat = 0;
        }
    }

}
