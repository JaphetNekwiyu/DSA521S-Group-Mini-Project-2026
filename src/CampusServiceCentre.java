import java.util.Scanner;

public class CampusServiceCentre {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentQueue waitingQueue = new StudentQueue(100);
    private static final StudentLinkedList records = new StudentLinkedList();
    private static final DailyStatistics statistics = new DailyStatistics(100);

    public static void main(String[] args) {
        int option;

        do {
            displayMenu();
            option = readInt("Select option: ");

            switch (option) {
                case 1:
                    addStudentToQueue();
                    break;
                case 2:
                    serveNextStudent();
                    break;
                case 3:
                    waitingQueue.displayQueue();
                    break;
                case 4:
                    addStudentRecord();
                    break;
                case 5:
                    records.displayStudents();
                    break;
                case 6:
                    searchStudentRecord();
                    break;
                case 7:
                    removeStudentRecord();
                    break;
                case 8:
                    statistics.displayStatistics();
                    break;
                case 9:
                    sortServiceTimes();
                    break;
                case 10:
                    SortingExperiment.runExperiment();
                    break;
                case 11:
                    System.out.println("\nExiting Campus Service Centre...");
                    break;
                default:
                    System.out.println("Invalid option. Please select 1-11.");
            }
        } while (option != 11);

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("        CAMPUS SERVICE CENTRE");
        System.out.println("========================================");
        System.out.println("1. Add student to waiting queue");
        System.out.println("2. Serve next student");
        System.out.println("3. Display waiting students");
        System.out.println("4. Add student service record");
        System.out.println("5. Display student service records");
        System.out.println("6. Search for student record");
        System.out.println("7. Remove student record");
        System.out.println("8. Display daily statistics");
        System.out.println("9. Sort service times");
        System.out.println("10. Run sorting experiment");
        System.out.println("11. Exit");
        System.out.println("========================================");
    }

    private static void addStudentToQueue() {
        System.out.println("\n--- ADD STUDENT TO QUEUE ---");
        waitingQueue.enqueue(readStudent());
    }

    private static void serveNextStudent() {
        System.out.println("\n--- SERVE NEXT STUDENT ---");
        Student student = waitingQueue.dequeue();

        if (student == null) {
            return;
        }

        System.out.println("Now serving: " + student.name);
        System.out.println("Service: " + student.serviceType);
        System.out.println(
            "Estimated service time: " + student.estimatedServiceTime + " minutes"
        );

        statistics.addServiceTime(student.estimatedServiceTime);
        records.insertAtEnd(student);

        System.out.println("Student served successfully.");
    }

    private static void addStudentRecord() {
        System.out.println("\n--- ADD SERVICE RECORD ---");
        Student student = readStudent();

        System.out.println("1. Insert at beginning");
        System.out.println("2. Insert at end");
        System.out.println("3. Insert at position");

        int choice = readInt("Choose insertion method: ");

        switch (choice) {
            case 1:
                records.insertAtBeginning(student);
                break;
            case 2:
                records.insertAtEnd(student);
                break;
            case 3:
                int position = readInt("Enter position: ");
                records.insertAtPosition(student, position);
                break;
            default:
                System.out.println("Invalid insertion option.");
        }
    }

    private static void searchStudentRecord() {
        System.out.println("\n--- SEARCH STUDENT RECORD ---");
        int studentNumber = readInt("Enter student number: ");
        records.searchStudent(studentNumber);
    }

    private static void removeStudentRecord() {
        System.out.println("\n--- REMOVE STUDENT RECORD ---");
        int studentNumber = readInt("Enter student number: ");
        records.deleteStudent(studentNumber);
    }

    private static void sortServiceTimes() {
        if (statistics.getCount() == 0) {
            System.out.println("No service times available to sort.");
            return;
        }

        int[] serviceTimes = statistics.getServiceTimesCopy();

        System.out.println("\n--- SORT SERVICE TIMES ---");
        System.out.print("Current service times: ");
        SortingAlgorithms.displayArray(serviceTimes);

        System.out.println("1. Selection Sort");
        System.out.println("2. Insertion Sort");
        System.out.println("3. Merge Sort");
        System.out.println("4. Quick Sort");

        int choice = readInt("Choose sorting algorithm: ");

        switch (choice) {
            case 1:
                SortingAlgorithms.selectionSort(serviceTimes);
                break;
            case 2:
                SortingAlgorithms.insertionSort(serviceTimes);
                break;
            case 3:
                SortingAlgorithms.mergeSort(serviceTimes);
                break;
            case 4:
                SortingAlgorithms.quickSort(serviceTimes);
                break;
            default:
                System.out.println("Invalid sorting option.");
                return;
        }

        System.out.print("Sorted service times: ");
        SortingAlgorithms.displayArray(serviceTimes);
    }

    private static Student readStudent() {
        int studentNumber = readInt("Student number: ");

        System.out.print("Student name: ");
        String name = scanner.nextLine();

        System.out.print("Service type: ");
        String serviceType = scanner.nextLine();

        int serviceTime = readInt("Estimated service time (minutes): ");

        return new Student(studentNumber, name, serviceType, serviceTime);
    }

    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
