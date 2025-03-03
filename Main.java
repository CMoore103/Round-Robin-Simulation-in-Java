//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        // Initialize a list of processes to simulate.
        List<Process> processes = new ArrayList<>();

        //Generate a list of random inter-arrival times, arrivalTimes, and serviceTImes
        int[] interArrivals = Process.getInterArrivalTimes(); //Used to create arrival times
        int[] arrivalTimes = Process.getArrivalTimes(interArrivals);
        int[] serviceTimes = Process.getServiceTimes();

        //Initialize the id value to 1, will increment after a process is added to the queue
        int id = 1;

        //Add 100 processes to the List, with each process containing a arrival and service time stored in the
        //randomized arrays
        for (int i = 0; i < 100; i++) {
            Process p = new Process(id, arrivalTimes[i], serviceTimes[i]);
            processes.add(p);
            id++;
        }

        int currentTime = 0; //Represents a clock
        int quantum = 2;

        //Prompt the user to specify the context switch and store the result
        System.out.println("Enter the context switch: ");
        int contextSwitch = keyboard.nextInt();

        // Initialize the process queue with a copy of the processes.
        Queue<Process> processQueue = new LinkedList<>(processes);



        // Main scheduling loop.
        while (!processes.stream().allMatch(pr -> pr.getRemainingServiceTime() == 0)) {
            Process currentProcess = null;
            // Find the next process that can be executed based on current time. Exit the loop once found
            for (Process process : processQueue) {
                if (process.getArrivalTime() <= currentTime) {
                    currentProcess = process;
                    break;
                }
            }

            //If the process exists, remove it from the processQueue and perform calculations on it
            if (currentProcess != null) {
                processQueue.remove(currentProcess);
                // Start the process if it hasn't started yet.
                if (currentProcess.getStartTime() == -1) {
                    currentProcess.setStartTime(currentTime);

                }

                // Execute the process for a quantum or its remaining service time, whichever is less.
                int executionTime = Math.min(quantum, currentProcess.getRemainingServiceTime());
                currentTime += executionTime;
                currentProcess.setRemainingServiceTime(currentProcess.getRemainingServiceTime() - executionTime);

                // Apply context switch time if another process will follow or if the current process is not finished.
                if (currentProcess.getRemainingServiceTime() > 0 || !processQueue.isEmpty()) {
                    currentTime += contextSwitch;
                }



                // Requeue the process if it's not finished, otherwise mark its end time.
                if (currentProcess.getRemainingServiceTime() > 0) {
                    processQueue.add(currentProcess);
                } else {
                    currentProcess.setEndTime(currentTime);
                }
            } else {
                // No process is ready; advance time to the next process arrival.
                currentTime++;
            }

            // Check for new arrivals and add them to the queue.
            for (Process i : processes) {
                if (!processQueue.contains(i) && i.getArrivalTime() <= currentTime && i.getRemainingServiceTime() > 0) {
                    processQueue.add(i);
                }
            }
        }

        System.out.printf("%-12s %-12s %-12s %-15s %-15s %-15s%n", "Process ID", "Start Time", "End Time", "Initial Wait", "Total Wait", "Turnaround");

        //Initialize variables to calculate averages
        int totalInter = 0;
        int totalService = 0;
        int totalTurn = 0;
        int totalTotalWait = 0;


        //Calculate the total service times of all processes
        for (Process p : processes) {
            totalService += p.getServiceTime();
        }

        //Output the metrics to the console

        //Calculate sum of all inter-arrival times
        for (int t : interArrivals) {
            totalInter += interArrivals[t];
        }


        //Calculate the statistics for each process
        for (Process process : processes) {
            int initialWait = process.getStartTime() - process.getArrivalTime();
            int totalWait = process.getEndTime() - process.getArrivalTime() - process.getServiceTime();

            totalTotalWait += totalWait;

            int turnaroundTime = process.getEndTime() - process.getArrivalTime();
            totalTurn += turnaroundTime;


            //Print out all information regarding a process
            System.out.printf("%-12s %-12d %-12d %-15d %-15d %-15d%n",
                    process.getId(),
                    process.getStartTime(),
                    process.getEndTime(),
                    initialWait,
                    totalWait,
                    turnaroundTime);
        }

        //Output the average service time, inter-arrival time, turn-around time, and total wait time
        System.out.println("Average Service Time: " + totalService / 100);
        System.out.println("Average Total Wait: " + totalTotalWait / 100);
        System.out.println("Average Turn Around Time: " + totalTurn / 100);
        System.out.println("Average Inter-Arrival Time: " + totalInter/100);

    }
}



