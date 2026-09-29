class StudentNode {
    Student data;
    StudentNode next;

    StudentNode(Student data) {
        this.data = data;
        this.next = null;
    }
}

public class StudentLinkedList {
    private StudentNode head;

    public void insertAtBeginning(Student student) {
        StudentNode newNode = new StudentNode(student);
        newNode.next = head;
        head = newNode;
    }

    public void insertAtEnd(Student student) {
        StudentNode newNode = new StudentNode(student);

        if (head == null) {
            head = newNode;
            return;
        }

        StudentNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    public void insertAtPosition(Student student, int position) {
        if (position < 1) {
            System.out.println("Invalid position.");
            return;
        }

        StudentNode newNode = new StudentNode(student);

        if (position == 1) {
            newNode.next = head;
            head = newNode;
            return;
        }

        StudentNode current = head;
        for (int i = 1; i < position - 1 && current != null; i++) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Invalid position.");
            return;
        }

        newNode.next = current.next;
        current.next = newNode;
    }

    public void deleteStudent(int studentNumber) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        if (head.data.studentNumber == studentNumber) {
            head = head.next;
            System.out.println("Student deleted.");
            return;
        }

        StudentNode current = head;
        while (current.next != null) {
            if (current.next.data.studentNumber == studentNumber) {
                current.next = current.next.next;
                System.out.println("Student deleted.");
                return;
            }
            current = current.next;
        }

        System.out.println("Student not found.");
    }

    public void searchStudent(int studentNumber) {
        StudentNode current = head;

        while (current != null) {
            if (current.data.studentNumber == studentNumber) {
                System.out.println("\nStudent found:");
                System.out.println(current.data);
                return;
            }
            current = current.next;
        }

        System.out.println("Student not found.");
    }

    public void displayStudents() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        System.out.println("\nSTUDENT SERVICE RECORDS");
        System.out.println("----------------------------------------");

        StudentNode current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}
