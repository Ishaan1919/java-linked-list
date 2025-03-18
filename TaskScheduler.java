// Implement the following functionalities:
//
//Display all tasks in the list starting from the head node.
//        Search for a task by Priority.
//Hint:
//Use a circular linked list where the last node’s next pointer points back to the first node, creating a circular structure.
//Ensure that the list loops when traversed from the head node, so tasks can be revisited in a circular manner.
//When deleting or adding tasks, maintain the circular nature by updating the appropriate next pointers.


class TaskNode{
    int taskId;
    String taskName;
    int priority;
    String dueDate;
    TaskNode next;

    public TaskNode(int taskId, String taskName, int priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = this;
    }
}

class TaskCLL{
    TaskNode head;
    TaskNode tail;
    TaskNode currTask;

    void insertAtBeginning(int taskId, String taskName, int priority, String dueDate){
        TaskNode node = new TaskNode(taskId, taskName, priority, dueDate);

        if(head==null){
            node = head;
            tail = head;
            currTask = head;
            return ;
        }
        node.next = head;
        tail.next = node;
    }

    void insertAtEnd(int taskId, String taskName, int priority, String dueDate){
        insertAtBeginning(taskId, taskName, priority, dueDate);
    }

    void insertAtSpecificPosition(int taskId, String taskName, int priority, String dueDate, int pos){
        TaskNode node = new TaskNode(taskId, taskName, priority, dueDate);

        if(head==null){
            head = node;
            tail = node;
            return ;
        }
        TaskNode temp = head;
        while(temp!=tail && pos-- > 0){
            temp = temp.next;
        }
        if(temp==tail){
            tail = node;
        }

        node.next = temp.next;
        temp.next = node;

    }

    void removeTaskByTaskId(int id){
        TaskNode temp = head;
        TaskNode prev = tail;

        while(temp!=tail && temp.taskId!=id){
            prev = temp;
            temp = temp.next;
        }

        if(temp==head){
            head = head.next;
        }

        if(temp.taskId == id){
            if(head==tail){
                tail = prev;
            }
            prev.next = temp.next;
            temp.next = null;
        }
    }

    void moveCurrentTaskToNext(){
        if(currTask!=null){
            currTask = currTask.next;
        }
    }

    void displayCurrentTask(){
        if(currTask!=null)
        displayDetails(currTask);
    }

    void displayDetails(TaskNode node){
        System.out.println("Task Name: " + node.taskName);
        System.out.println("Task Id: " + node.taskId);
        System.out.println("Task Priority: " + node.priority);
        System.out.println("Task Due Date: " + node.dueDate);
    }

    void displayAllTask(){
        TaskNode node = head;
        do{
            displayDetails(node);
            node = node.next;
        }
        while(node!=head);
    }

    TaskNode searchTaskByPriority(int priority){
        TaskNode node = head;

        do{
            if(node.priority == priority){
                return node;
            }
            node = node.next;
        }
        while(node!=head);

        return null;
    }

}

public class TaskScheduler {
    public static void main(String[] args) {
        TaskCLL scheduler = new TaskCLL();

        scheduler.insertAtBeginning(101, "Task A", 1, "2025-03-20");
        scheduler.insertAtEnd(102, "Task B", 2, "2025-03-22");
        scheduler.insertAtSpecificPosition(103, "Task C", 3, "2025-03-25", 1);

        System.out.println("All Tasks:");
        scheduler.displayAllTask();

        System.out.println("\nCurrent Task:");
        scheduler.displayCurrentTask();

        System.out.println("\nMoving to Next Task...");
        scheduler.moveCurrentTaskToNext();
        scheduler.displayCurrentTask();

        System.out.println("\nSearching Task with Priority 2:");
        TaskNode searchResult = scheduler.searchTaskByPriority(2);
        if (searchResult != null) {
            scheduler.displayDetails(searchResult);
        } else {
            System.out.println("Task not found");
        }

        System.out.println("\nRemoving Task with ID 102");
        scheduler.removeTaskByTaskId(102);
        scheduler.displayAllTask();

//        All Tasks:
//        Task Name: Task C
//        Task Id: 103
//        Task Priority: 3
//        Task Due Date: 2025-03-25
//
//        Current Task:
//
//        Moving to Next Task...
//
//        Searching Task with Priority 2:
//        Task not found
//
//        Removing Task with ID 102
//        Task Name: Task C
//        Task Id: 103
//        Task Priority: 3
//        Task Due Date: 2025-03-25
    }
}