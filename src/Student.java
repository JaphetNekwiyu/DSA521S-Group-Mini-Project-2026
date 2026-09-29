public class Student {
    int studentNumber;
    String name;
    String serviceType;
    int estimatedServiceTime;

    public Student(int studentNumber, String name, String serviceType, int estimatedServiceTime) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.serviceType = serviceType;
        this.estimatedServiceTime = estimatedServiceTime;
    }

    @Override
    public String toString() {
        return "Student No: " + studentNumber
            + " | Name: " + name
            + " | Service: " + serviceType
            + " | Estimated Time: " + estimatedServiceTime + " min";
    }
}
