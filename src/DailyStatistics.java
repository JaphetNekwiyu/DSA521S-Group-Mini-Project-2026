public class DailyStatistics {
    private final int[] serviceTimes;
    private int count;

    public DailyStatistics(int capacity) {
        serviceTimes = new int[capacity];
        count = 0;
    }

    public void addServiceTime(int time) {
        if (count >= serviceTimes.length) {
            System.out.println("Service-time array is full.");
            return;
        }
        serviceTimes[count++] = time;
    }

    public int getCount() {
        return count;
    }

    public int[] getServiceTimesCopy() {
        int[] copy = new int[count];
        for (int i = 0; i < count; i++) {
            copy[i] = serviceTimes[i];
        }
        return copy;
    }

    public void displayStatistics() {
        if (count == 0) {
            System.out.println("No students have been served yet.");
            return;
        }

        int total = 0;
        int highest = serviceTimes[0];
        int lowest = serviceTimes[0];
        int longerThanTen = 0;

        for (int i = 0; i < count; i++) {
            int time = serviceTimes[i];
            total += time;

            if (time > highest) {
                highest = time;
            }
            if (time < lowest) {
                lowest = time;
            }
            if (time > 10) {
                longerThanTen++;
            }
        }

        double average = (double) total / count;

        System.out.println("\n========== DAILY STATISTICS ==========");
        System.out.println("Total students served : " + count);
        System.out.println("Total service time     : " + total + " minutes");
        System.out.printf("Average service time   : %.2f minutes%n", average);
        System.out.println("Highest service time   : " + highest + " minutes");
        System.out.println("Lowest service time    : " + lowest + " minutes");
        System.out.println("Services > 10 minutes  : " + longerThanTen);
        System.out.println("======================================");
    }
}
