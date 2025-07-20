import java.io.*;
import java.util.*;

public class Doctor {
    String name;
    String specialization;
    int experience;

    public void viewDoctors() {
        File file = new File("doctors.txt");
        try {
            Scanner fileScanner = new Scanner(file);
            if (!file.exists() || file.length() == 0) {
                System.out.println("No doctors found.");
                fileScanner.close();
                System.out.println();
                return;
            }
            System.out.println("List of Doctors:");
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                System.out.println(data);
                System.out.println();
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void checkDoctorExists(String name) {
        File file = new File("doctors.txt");
        try {
            Scanner fileScanner = new Scanner(file);
            boolean found = false;
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                String[] doctorData = data.split(",");
                if (doctorData[0].equalsIgnoreCase(name)) {
                    found = true;
                    System.out.println("Doctor found: " + data);
                    System.out.println();
                    break;
                }
            }
            fileScanner.close();
            if (!found) {
                System.out.println("Doctor not found.");
            }
        } catch (Exception e) {
            System.out.println("Error while checking doctor: " + e.getMessage());
            e.printStackTrace();
        }
    }

}