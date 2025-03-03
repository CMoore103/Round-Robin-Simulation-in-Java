import java.util.Random;

public class Process {
    private final int id;
    private final int arrivalTime;
    private final int serviceTime;
    private int remainingServiceTime;
    private int startTime = -1; // Indicates not started, to be set only once
    private int endTime = 0;


    public Process(int id, int arrivalTime, int serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.remainingServiceTime = serviceTime; // Initially, remaining service time equals service time
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public int getRemainingServiceTime() {
        return remainingServiceTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }


    public int getTurnAroundTime() {
        return this.endTime - this.arrivalTime;
    }

    // Setters
    public void setRemainingServiceTime(int remainingServiceTime) {
        this.remainingServiceTime = remainingServiceTime;
    }

    public void setStartTime(int startTime) {
        if (this.startTime == -1) { // Ensure start time is set only once
            this.startTime = startTime;
        }
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public static int[] getInterArrivalTimes() {
        int high = 10;
        int low = 5;

        Random rand = new Random();
        int[] interArrivalTimes = new int[99];
        int[] arrivalTimes = new int[100];

        for (int i = 0; i < interArrivalTimes.length; i++) {
            int result = rand.nextInt(high - low) + low;
            interArrivalTimes[i] = result;
        }

        return interArrivalTimes;

    }

    public static int[] getArrivalTimes(int[] interArrivalTimes) {

        int[] arrivalTimes = new int[100];

        int a = 0;
        arrivalTimes[0] = a;

        for (int i = 1; i < interArrivalTimes.length; i++) {
            a += interArrivalTimes[i];
            arrivalTimes[i] = a;
        }

        arrivalTimes[interArrivalTimes.length] = arrivalTimes[interArrivalTimes.length-1] + 7;

        return arrivalTimes;
    }

    public static int[] getServiceTimes() {

        int[] serviceTimes = new int[100];

        int high = 8;
        int low = 4;

        Random rand = new Random();

        for (int i = 0; i < serviceTimes.length; i++) {
            int result = rand.nextInt(high - low) + low;
            serviceTimes[i] = result;
        }

        return serviceTimes;
    }


}
