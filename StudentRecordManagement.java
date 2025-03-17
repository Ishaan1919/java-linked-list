// Add a new student record at the beginning, end, or at a specific position.
// Delete a student record by Roll Number.
// Search for a student record by Roll Number.
// Display all student records.
// Update a student's grade based on their Roll Number.



class Node{
    String rollNumber;
    String name;
    int age;
    String grade;
    Node next;

    Node(String rollNumber, String name, int age, String grade){
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

class SinglyLinkedList {
    Node head;

    void addAtBeginning(String rollNumber, String name, int age, String grade){
        Node node = new Node(rollNumber, name, age, grade);
        if(head == null){
            head = node;
            return ;
        }
        node.next = head;
        head = node;
    }

    void addAtBeginning(Node node){
        if(head == null){
            head = node;
            return ;
        }
        node.next = head;
        head = node;
    }

    void addAtEnding(String rollNumber, String name, int age, String grade){
        Node node = new Node(rollNumber, name, age, grade);
        Node prev = null;
        Node temp = head;
        while(temp != null){
            prev = temp;
            temp = temp.next;
        }
        if(prev != null){
            prev.next = node;
        }
        else{
            head = node;
        }
    }

    void addAtSpecificPosition(String rollNumber, String name, int age, String grade, int pos){
        Node node = new Node(rollNumber, name, age, grade);
        Node temp = head;
        if(pos==0){
            addAtBeginning(node);
            return ;
        }
        Node prev = null;
        while(temp != null && pos-- > 0) {
            prev = temp;
            temp = temp.next;
        }
        prev.next = node;
        node.next = temp;
    }

    void deleteStudentRecordByRollNumber(String rollNumber){
        Node temp = head;
        Node prev = null;
        while(temp != null){
            if(rollNumber.equals(temp.rollNumber)){
                if(prev == null){
                    head = head.next;
                }
                else if(temp.next != null){
                    prev.next = temp.next;
                }
                else{
                    prev.next = null;
                }
                return ;
            }
            prev = temp;
            temp = temp.next;
        }
    }

    Node searchStudentRecordByRollNumber(String rollNumber){
        Node temp = head;
        while(temp != null){
            if(rollNumber.equals(temp.rollNumber)){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    void upgradeStudentGradeByRollNumber(String rollNumber, String grade){
        Node student = searchStudentRecordByRollNumber(rollNumber);
        if(student != null){
            student.grade = grade;
        }
    }

    void displayStudentRecords(Node node){
        System.out.println("Name: " + node.name);
        System.out.println("Roll Number: " + node.rollNumber);
        System.out.println("Age: " + node.age);
        System.out.println("Grade: " + node.grade);
    }

    void displayRecordsForAllStudents(){
        Node temp = head;
        while(temp != null){
            displayStudentRecords(temp);
            System.out.println("---------");
            temp = temp.next;
        }
    }

}


public class StudentRecordManagement {
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        list.addAtEnding("101", "Alice", 20, "A");
        list.addAtBeginning("102", "Bob", 21, "B");
        list.addAtSpecificPosition("103", "Charlie", 22, "C", 2);

        list.displayRecordsForAllStudents();

        System.out.println("Updating grade...");
        list.upgradeStudentGradeByRollNumber("102", "A+");
        list.displayRecordsForAllStudents();

        System.out.println("Deleting student...");
        list.deleteStudentRecordByRollNumber("103");
        list.displayRecordsForAllStudents();


//        Name: Bob
//        Roll Number: 102
//        Age: 21
//        Grade: B
//                ---------
//        Name: Alice
//        Roll Number: 101
//        Age: 20
//        Grade: A
//                ---------
//        Name: Charlie
//        Roll Number: 103
//        Age: 22
//        Grade: C
//                ---------
//                Updating grade...
//        Name: Bob
//        Roll Number: 102
//        Age: 21
//        Grade: A+
//                ---------
//        Name: Alice
//        Roll Number: 101
//        Age: 20
//        Grade: A
//                ---------
//        Name: Charlie
//        Roll Number: 103
//        Age: 22
//        Grade: C
//                ---------
//                Deleting student...
//        Name: Bob
//        Roll Number: 102
//        Age: 21
//        Grade: A+
//                ---------
//        Name: Alice
//        Roll Number: 101
//        Age: 20
//        Grade: A
//                ---------
    }
}