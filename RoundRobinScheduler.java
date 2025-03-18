class ProcessNode {
    int processId;
    int burstTime;
    int priority;
    ProcessNode next;

    public ProcessNode(int processId, int burstTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.priority = priority;
        this.next = this;
    }
}

class RoundRobin {
    private ProcessNode head;
    private ProcessNode tail;
    private int timeQuantum;

    public RoundRobin(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    void addProcess(int processId, int burstTime, int priority) {
        ProcessNode newNode = new ProcessNode(processId, burstTime, priority);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.next = head;
            tail = newNode;
        }
    }

    void removeProcess(int processId) {
        if (head == null) return;
        ProcessNode temp = head;
        ProcessNode prev = tail;
        do {
            if (temp.processId == processId) {
                if (temp == head) {
                    head = head.next;
                    tail.next = head;
                } else if (temp == tail) {
                    tail = prev;
                    tail.next = head;
                } else {
                    prev.next = temp.next;
                }
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);
    }

    void executeProcesses() {
        if (head == null) return;
        ProcessNode current = head;
        int totalTime = 0;
        int completedProcesses = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        while (completedProcesses < countProcesses()) {
            if (current.burstTime > 0) {
                int executionTime = Math.min(timeQuantum, current.burstTime);
                System.out.println("Executing Process " + current.processId + " for " + executionTime + " units");
                totalTime += executionTime;
                current.burstTime -= executionTime;

                if (current.burstTime == 0) {
                    completedProcesses++;
                    int turnaroundTime = totalTime;
                    int waitingTime = turnaroundTime - executionTime;
                    totalWaitingTime += waitingTime;
                    totalTurnaroundTime += turnaroundTime;
                    System.out.println("Process " + current.processId + " completed. Turnaround Time: " + turnaroundTime + ", Waiting Time: " + waitingTime);
                }
            }
            current = current.next;
        }

        System.out.println("Average Waiting Time: " + (double) totalWaitingTime / completedProcesses);
        System.out.println("Average Turnaround Time: " + (double) totalTurnaroundTime / completedProcesses);
    }

    int countProcesses() {
        if (head == null) return 0;
        ProcessNode temp = head;
        int count = 0;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);
        return count;
    }

    void displayProcesses() {
        if (head == null) return;
        ProcessNode temp = head;
        do {
            System.out.println("Process ID: " + temp.processId + ", Burst Time: " + temp.burstTime + ", Priority: " + temp.priority);
            temp = temp.next;
        } while (temp != head);
    }
}

public class RoundRobinScheduler {
    public static void main(String[] args) {
        RoundRobin scheduler = new RoundRobin(4);
        scheduler.addProcess(1, 10, 2);
        scheduler.addProcess(2, 5, 1);
        scheduler.addProcess(3, 8, 3);

        System.out.println("Initial Process Queue:");
        scheduler.displayProcesses();

        System.out.println("\nExecuting Round Robin Scheduling:");
        scheduler.executeProcesses();


//        Initial Process Queue:
//        Process ID: 1, Burst Time: 10, Priority: 2
//        Process ID: 2, Burst Time: 5, Priority: 1
//        Process ID: 3, Burst Time: 8, Priority: 3
//
//        Executing Round Robin Scheduling:
//        Executing Process 1 for 4 units
//        Executing Process 2 for 4 units
//        Executing Process 3 for 4 units
//        Executing Process 1 for 4 units
//        Executing Process 2 for 1 units
//        Process 2 completed. Turnaround Time: 17, Waiting Time: 16
//        Executing Process 3 for 4 units
//        Process 3 completed. Turnaround Time: 21, Waiting Time: 17
//        Executing Process 1 for 2 units
//        Process 1 completed. Turnaround Time: 23, Waiting Time: 21
//        Average Waiting Time: 18.0
//        Average Turnaround Time: 20.333333333333332
    }
}
