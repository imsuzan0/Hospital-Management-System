import java.io.*;
import java.util.Scanner;

public class HospitalManagement {
    public static void addAppointment() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Patient Name:");
        String patientName = sc.nextLine();
        System.out.println("Enter Doctor Name:");
        String doctorName = sc.nextLine();
        System.out.println("Enter Appointment Date (dd/mm/yyyy):");
        String appointmentDate = sc.nextLine();
        System.out.println("Enter Appointment Time (HH:MM):");
        String appointmentTime = sc.nextLine();
        System.out.println("What is the reason for the appointment?:");
        String appointmentReason = sc.nextLine();

        sc.close();

        boolean patientExists = false;
        boolean doctorExists = false;
        // Check if patient exists
        try {
            File file = new File("patients.txt");
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                String[] patientData = data.split(",");
                if (patientData[0].equalsIgnoreCase(patientName)) {
                    patientExists = true;
                    break;
                }
            }
            fileScanner.close();

            File doctorFile = new File("doctors.txt");
            Scanner doctorScanner = new Scanner(doctorFile);
            // Check if doctor exists
            while (doctorScanner.hasNextLine()) {
                String data = doctorScanner.nextLine();
                String[] doctorData = data.split(",");
                if (doctorData[0].equalsIgnoreCase(doctorName)) {
                    doctorExists = true;
                    break;
                }
            }
            doctorScanner.close();

            if (!patientExists) {
                throw new Exception("Patient does not exist. Please add the patient first.");
            }
            if (!doctorExists) {
                throw new Exception("Doctor does not exist.Please recheck the doctor details.");
            }

            if (patientName.isEmpty() || doctorName.isEmpty() || appointmentDate.isEmpty()
                    || appointmentTime.isEmpty()) {
                throw new Exception("Invalid input. Please enter a valid appointment details.");
            } else {
                FileWriter writer = new FileWriter("appointments.txt", true);
                writer.write(patientName + "," + doctorName + "," + appointmentReason + "," + appointmentDate + "," + appointmentTime + "\n");
                writer.close();
                System.out.println("Appointment details saved successfully.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void viewAppointments() {
        File file = new File("appointments.txt");
        if (!file.exists() || file.length() == 0) {
            System.out.println("No appointments found.");
            System.out.println();
            return;
        }
        try {
            Scanner fileScanner = new Scanner(file);
            System.out.println("List of Appointments:");
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                System.out.println(data);
            }
            fileScanner.close();
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Patient patient = new Patient();
        Doctor doctor = new Doctor();

        System.out.println("---------------------- Hospital Management System ----------------------");
        while (true) {
            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("3. Check Patient Exists");
            System.out.println("4. View Doctors");
            System.out.println("5. Check Doctor Exists");
            System.out.println("6. Add Appointment");
            System.out.println("7. View Appointments");
            System.out.println("8. Exit");
            System.out.println("Enter your choice (1-8): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            switch (choice) {
                case 1:
                    patient.addPatient();
                    break;
                case 2:
                    patient.viewPatients();
                    break;
                case 3:
                    System.out.println("Enter Patient Name to check:");
                    String patientName = scanner.nextLine();
                    patient.checkPatientExists(patientName);
                    break;
                case 4:
                    doctor.viewDoctors();
                    break;
                case 5:
                    System.out.println("Enter Doctor Name to check:");
                    String doctorName = scanner.nextLine();
                    doctor.checkDoctorExists(doctorName);
                    break;
                case 6:
                    addAppointment();
                    break;
                case 7:
                    viewAppointments();
                    break;

                case 8:
                    System.out.println("Exiting the system. Thank you!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                    break;
            }
        }

    }
}