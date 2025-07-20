import java.util.*;
import java.io.*;

public class Patient {
    String name;
    int age;
    String gender;

    public void addPatient() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Patient Name:");
        name = sc.next();
        System.out.println("Enter Patient Age:");
        age = sc.nextInt();
        System.out.println("Enter Patient's Gender (male/female):");
        gender = sc.next().toLowerCase(); // Normalize to lowercase

        try {
            if (name.isEmpty() || age <= 0 ||
                    (!gender.equals("male") && !gender.equals("female"))) {
                throw new Exception("Invalid input. Please enter valid patient details.");
            } else {
                FileWriter writer = new FileWriter("patients.txt", true); // 'true' for append mode
                writer.write(name + "," + age + "," + gender + "\n");
                writer.close();
                System.out.println("Patient details saved successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void viewPatients() {
        File file = new File("patients.txt");
        try {
            Scanner fileScanner = new Scanner(file);
            if (!file.exists() || file.length() == 0) {
                System.out.println("No patients found.");
                fileScanner.close();
                System.out.println();
                return;
            }
            System.out.println("List of Patients:");
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                System.out.println(data);
            }
            fileScanner.close();
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void checkPatientExists(String name) {
        File file = new File("patients.txt");
        try {
            Scanner fileScanner = new Scanner(file);
            boolean found = false;
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                String[] patientsData = data.split(",");
                if (patientsData[0].equalsIgnoreCase(name)) {
                    found = true;
                    System.out.println("Patient found: " + data);
                    System.out.println();
                    break;
                }
            }
            fileScanner.close();
            if (!found) {
                System.out.println("Patient not found.");
            }
        } catch (Exception e) {
            System.out.println("Error while checking patient: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
