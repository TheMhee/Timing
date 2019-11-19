package Application;

import java.io.*;
import org.json.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonManager {

    private static String dOfw[] = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private static String mOfy[] = new String[]{"January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"};
    private static JSONParser parser = new JSONParser();
    private static Calendar c = Calendar.getInstance();
    private static int year = c.get(c.YEAR);
    int month = c.get(c.MONTH);
    public static String getJSONString(int year){
        String s = "";
        try {
            Object obj = parser.parse(new FileReader("Calendar" + year + ".json"));
            JSONObject jsonObj = (JSONObject) obj;
            s = jsonObj.toString();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } catch (ParseException ex) {
        }
        return s;
    }

    public static String getMonthName(String year, String month) {
        String monthName = "";
        try {
            Object obj = parser.parse(new FileReader("Calendar" + year + ".json"));
            JSONObject jsonObj = (JSONObject) obj;
            monthName = (String) ((JSONObject) jsonObj.get(mOfy[Integer.parseInt(month)])).get("name");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return monthName;
    }

    public static String getDayOfWeek(String year, String month, String date) {
        String dayOfWeek = "";
        try {
            Object obj = parser.parse(new FileReader("Calendar" + year + ".json"));
            JSONObject jsonObj = (JSONObject) obj;
            dayOfWeek = (String) ((JSONObject) ((JSONObject) ((JSONObject) jsonObj.get(mOfy[Integer.parseInt(month)])).get("date")).get(date)).get("day");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dayOfWeek;
    }

    public static String getDayOfWeek(int year, int month, int date) {
        //System.out.println(year+" "+month+" "+date);
        String dayOfWeek = "";
        try {
            Object obj = parser.parse(new FileReader("Calendar" + (year + "") + ".json"));
            JSONObject jsonObj = (JSONObject) obj;
            dayOfWeek = (String) ((JSONObject) ((JSONObject) ((JSONObject) jsonObj.get(mOfy[month])).get("date")).get(date + "")).get("day");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dayOfWeek;
    }

    public static int getMonthLen(String year, String month) {
        int monthLen = 0;
        try {
            Object obj = parser.parse(new FileReader("Calendar" + year + ".json"));
            JSONObject jsonObj = (JSONObject) obj;
            monthLen = (int) ((JSONObject) ((JSONObject) jsonObj.get(mOfy[Integer.parseInt(month)])).get("date")).size();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return monthLen;
    }

    public static String getActTitle(String year, String month, String date, int pos) {
        String act = "";
        try {
            Object obj = parser.parse(new FileReader("Calendar" + year + ".json"));
            JSONObject jsonObj = (JSONObject) obj;
            act = (String) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) jsonObj.get(mOfy[Integer.parseInt(month)])).get("date")).get(date)).get("active")).get(pos + "")).get("title");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return act;
    }

    public static String getActTimeStart(String year, String month, String date, int pos) {
        String act = "";
        try {
            Object obj = parser.parse(new FileReader("Calendar" + year + ".json"));
            JSONObject jsonObj = (JSONObject) obj;
            act = (String) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) jsonObj.get(mOfy[Integer.parseInt(month)])).get("date")).get(date)).get("active")).get(pos + "")).get("tstart");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return act;
    }

    public static String getActTimeEnd(String year, String month, String date, int pos) {
        String act = "";
        try {
            Object obj = parser.parse(new FileReader("Calendar" + year + ".json"));
            JSONObject jsonObj = (JSONObject) obj;
            act = (String) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) jsonObj.get(mOfy[Integer.parseInt(month)])).get("date")).get(date)).get("active")).get(pos + "")).get("tend");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return act;
    }

    public static void setActTitle(String year, String month, String date, String string) {
        String act = "";
        try {
            Object obj = parser.parse(new FileReader("Calendar" + year + ".json"));
            JSONObject jsonObj = (JSONObject) obj;
            JSONObject jsonObj_m = (JSONObject) jsonObj.get(mOfy[Integer.parseInt(month)]);
            JSONObject jsonObj_d = (JSONObject) jsonObj_m.get("date");
            JSONObject jsonObj_da = (JSONObject) jsonObj_d.get(date);
            JSONObject jsonObj_ac = (JSONObject) jsonObj_da.get("active");
            JSONObject jsonObj_ac1 = (JSONObject) jsonObj_ac.get("1");
            jsonObj_ac1.put("title", string);
            jsonObj_ac.put("1", jsonObj_ac1);
            jsonObj_da.put("active", jsonObj_ac);
            jsonObj_d.put(date, jsonObj_da);
            jsonObj_m.put("date", jsonObj_d);
            jsonObj.put(mOfy[Integer.parseInt(month)], jsonObj_m);
            try {
                FileOutputStream fout = new FileOutputStream("Calendar" + year + ".json");
                PrintWriter pw = new PrintWriter(fout);
                pw.write(jsonObj.toJSONString());
                pw.close();
                fout.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void setActTime(String year, String month, String date, String tstart, String tend) {
        String act = "";
        try {
            Object obj = parser.parse(new FileReader("Calendar" + year + ".json"));
            JSONObject jsonObj = (JSONObject) obj;
            JSONObject jsonObj_m = (JSONObject) jsonObj.get(mOfy[Integer.parseInt(month)]);
            JSONObject jsonObj_d = (JSONObject) jsonObj_m.get("date");
            JSONObject jsonObj_da = (JSONObject) jsonObj_d.get(date);
            JSONObject jsonObj_ac = (JSONObject) jsonObj_da.get("active");
            JSONObject jsonObj_ac1 = (JSONObject) jsonObj_ac.get("1");
            jsonObj_ac1.put("tstart", tstart);
            jsonObj_ac1.put("tend", tend);
            jsonObj_ac.put("1", jsonObj_ac1);
            jsonObj_da.put("active", jsonObj_ac);
            jsonObj_d.put(date, jsonObj_da);
            jsonObj_m.put("date", jsonObj_d);
            jsonObj.put(mOfy[Integer.parseInt(month)], jsonObj_m);
            try {
                FileOutputStream fout = new FileOutputStream("Calendar" + year + ".json");
                PrintWriter pw = new PrintWriter(fout);
                pw.write(jsonObj.toJSONString());
                pw.close();
                fout.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

//	public static int getDate(int year, int month)
    public static void checkFile() {
        File f = new File("Calendar" + year + ".json");
        if (!f.exists()) {
            String json = "{";
            String jsonf = "";
            for (int i = 0; i < 12; i++) {
                c.set(year, i, 1);
                jsonf += "\"" + mOfy[c.get(c.MONTH)] + "\"  : {";
                jsonf += "\"name\"  : \"" + mOfy[c.get(c.MONTH)] + "\",";
                jsonf += "\"date\"  : {";
                while (c.get(c.MONTH) == i) {
                    jsonf += "\"" + c.get(c.DATE) + "\"  : {";
                    jsonf += "\"mdate\"  : \"" + c.get(c.DATE) + "\",";
                    jsonf += "\"day\"  : \"" + dOfw[c.get(c.DAY_OF_WEEK) - 1] + "\",";
                    jsonf += "\"active\"  : {\"1\":{\"title\":\"\",\"tstart\":\"\",\"tend\":\"\"}}";
                    jsonf += "}";

                    c.add(c.DATE, 1);
                    if (c.get(c.MONTH) == i) {
                        jsonf += ",";
                    }
                }
                jsonf += "}}";
                if (i < 11) {
                    jsonf += ",";
                }
            }
            jsonf += "}";
            json += jsonf;
            try {
                FileOutputStream fout = new FileOutputStream("Calendar" + year + ".json");
                PrintWriter pw = new PrintWriter(fout);
                pw.write(json);
                pw.close();
                fout.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void checkFile(int year) {
        File f = new File("Calendar" + year + ".json");
        c.set(year, 1, 1);
        if (!f.exists()) {
            String json = "{";
            String jsonf = "";
            for (int i = 0; i < 12; i++) {
                c.set(year, i, 1);
                jsonf += "\"" + mOfy[c.get(c.MONTH)] + "\"  : {";
                jsonf += "\"name\"  : \"" + mOfy[c.get(c.MONTH)] + "\",";
                jsonf += "\"date\"  : {";
                while (c.get(c.MONTH) == i) {
                    jsonf += "\"" + c.get(c.DATE) + "\"  : {";
                    jsonf += "\"mdate\"  : \"" + c.get(c.DATE) + "\",";
                    jsonf += "\"day\"  : \"" + dOfw[c.get(c.DAY_OF_WEEK) - 1] + "\",";
                    jsonf += "\"active\"  : {\"1\":{\"title\":\"\",\"tstart\":\"\",\"tend\":\"\"},"
                            + "\"2\":{\"title\":\"\",\"tstart\":\"\",\"tend\":\"\"},"
                            + "\"3\":{\"title\":\"\",\"tstart\":\"\",\"tend\":\"\"}}";
                    jsonf += "}";

                    c.add(c.DATE, 1);
                    if (c.get(c.MONTH) == i) {
                        jsonf += ",";
                    }
                }
                jsonf += "}}";
                if (i < 11) {
                    jsonf += ",";
                }
            }
            jsonf += "}";
            json += jsonf;
            try {
                FileOutputStream fout = new FileOutputStream("Calendar" + year + ".json");
                PrintWriter pw = new PrintWriter(fout);
                pw.write(json);
                pw.close();
                fout.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
