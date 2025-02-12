package util;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import controller.porder.PorderMainUI;
import controller.member.LoginUI;


public class Tool {

    private static final String DATE_FORMAT = "yyyy-MM-dd"; 

    
    public static void save(Object obj, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public static Object read(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

   
    public static LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    
    public static void gotoPorderMain() {
        PorderMainUI porderMainUI = new PorderMainUI();
        porderMainUI.setVisible(true);
    }

    
    public static void gotoLogin() {
        LoginUI loginUI = new LoginUI();
        loginUI.setVisible(true);
    }

   
    public static void closeFrame(JFrame frame) {
        frame.dispose();
    }
}