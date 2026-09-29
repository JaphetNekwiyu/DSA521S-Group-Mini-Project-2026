public class StudentQueue {
    private final Student[] queue;
    private int front;
    private int rear;

    public StudentQueue(int size) {
        queue = new Student[size];
        front = -1;
        rear = -1;
    }

    public void enqueue(Student student) {
        if (rear == queue.length - 1) {
            System.out.println("Queue is full.");
            return;
        }

        if (isEmpty()) {
            front = 0;
        }

        queue[++rear] = student;
        System.out.println(student.name + " added to queue.");
    }

    public Student dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return null;
        }

        Student student = queue[front];

        for (int i = front; i < rear; i++) {
            queue[i] = queue[i + 1];
        }

        queue[rear] = null;
        rear--;

        if (rear == -1) {
            front = -1;
        }

        return student;
    }

    public Student peek() {
        if (isEmpty()) {
            return null;
        }
        return queue[front];
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }

        System.out.println("\nWAITING QUEUE");
        System.out.println("----------------------------------------");
        for (int i = front; i <= rear; i++) {
            System.out.println(queue[i]);
        }
    }
}
