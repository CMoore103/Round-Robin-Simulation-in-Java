public class Process {
    private final int id;
    private final int arrivalTime;
    private final int serviceTime;
    private int remainingServiceTime;
    private int startTime = -1; // Indicates not started, to be set only once
    private int endTime = 0;

    private int lastExecutionTime;
    private int totalWaitTime = 0;

    public Process(int num, int arrivalTime, int serviceTime) {
        this.id = num;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.remainingServiceTime = serviceTime; //Set remainingServiceTime to the starting service time
        this.lastExecutionTime = arrivalTime; // Set the lastExecutionTime equal to the arrivalTime
    }

    // Create getter methods for all the class fields
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

    public int getTotalWaitTime() {
        return totalWaitTime;
    }

    public int getLastExecutionTime() {
        return lastExecutionTime;
    }

    public int getTurnAroundTime() {
        return this.endTime - this.arrivalTime;
    }

    // Setter methods for the class fields
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



    public void setLastExecutionTime(int lastExecutionTime) {
        this.lastExecutionTime = lastExecutionTime;
    }
}
