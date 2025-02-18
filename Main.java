//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*;

public class Main {
    public static void main(String[] args) {

        //Create a Scanner object to take user input; will be used to specify context switch
        Scanner keyboard = new Scanner(System.in);

        //Create a ArrayList to store the processes
        ArrayList<Process> processes = new ArrayList<>();
        Process p1 = new Process(1, 0, 75);
        Process p2 = new Process(2,10, 40 );
        Process p3 = new Process(3, 10, 25);
        Process p4 = new Process(4, 80, 20);
        Process p5 = new Process(5, 85, 45);

        //Add the processes to the ArrayList
        processes.add(p1);
        processes.add(p2);
        processes.add(p3);
        processes.add(p4);
        processes.add(p5);

        int quantum = 10;

        //Prompt the user to enter the context switch and store it
        System.out.println("Enter the context switch: ");
        int contextSwitch = keyboard.nextInt();

        //Create a process queue using the processes created above
        Queue<Process> processQueue = new LinkedList<>(processes);
        int currentTime = 0; //Initialize current time to zero
        boolean isFirstProcess = true;

        while (!processQueue.isEmpty()) {
            Process currentProcess = processQueue.poll();

            // If not the first process, add context switch time
            if (!isFirstProcess) {
                currentTime += contextSwitch;
            } else {
                isFirstProcess = false;
            }

            // Adjust current time if the process arrives after the current time
            if (currentTime < currentProcess.getArrivalTime()) {
                currentTime = currentProcess.getArrivalTime();
            }

            //If the current start time is -1, the process hasn't started. Set the start time to the current time
            if (currentProcess.getStartTime() == -1) {
                currentProcess.setStartTime(currentTime);
            }

            // Execute the process
            //Set execution time equal to the minimum of quantum or the remaining service time
            int executionTime = Math.min(currentProcess.getRemainingServiceTime(), quantum);
            currentTime += executionTime; //Add the executionTime to the totalTime

            //Update the remaining service time of the current process
            currentProcess.setRemainingServiceTime(currentProcess.getRemainingServiceTime() - executionTime);

            // Check if the process is completed; if not add it back into the queue
            if (currentProcess.getRemainingServiceTime() > 0) {
                processQueue.add(currentProcess);
                currentProcess.setLastExecutionTime(currentTime); //Set the last execution time of the current process
            } else {
                //If process is done, set the end time for the process
                currentProcess.setEndTime(currentTime);
            }
        }

        // Display the metrics after the scheduling process is completed
        System.out.printf("%-12s %-12s %-12s %-15s %-15s %-15s%n", "Process ID", "Start Time", "End Time", "Initial Wait", "Total Wait", "Turnaround");

        //Output the metrics to the console
        for (Process process : processes) {
            int initialWait = process.getStartTime() - process.getArrivalTime();
            int totalWait = process.getEndTime() - process.getArrivalTime() - process.getServiceTime();
            int turnaroundTime = process.getEndTime() - process.getArrivalTime();
            System.out.printf("%-12s %-12d %-12d %-15d %-15d %-15d%n",
                    process.getId(),
                    process.getStartTime(),
                    process.getEndTime(),
                    initialWait,
                    totalWait,
                    turnaroundTime);
        }
    }
}
