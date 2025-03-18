class TextStateNode {
    String text;
    TextStateNode next;
    TextStateNode prev;

    public TextStateNode(String text) {
        this.text = text;
        this.next = null;
        this.prev = null;
    }
}

class UndoRedoManager {
    private TextStateNode current;
    private int historySize;
    private int stateCount;

    public UndoRedoManager(int historySize) {
        this.historySize = historySize;
        this.stateCount = 0;
        this.current = null;
    }

    public void addState(String text) {
        TextStateNode newNode = new TextStateNode(text);

        if (current == null) {
            current = newNode;
        } else {
            newNode.prev = current;
            current.next = newNode;
            current = newNode;
        }

        stateCount++;
        if (stateCount > historySize) {
            trimOldestState();
        }
    }

    private void trimOldestState() {
        TextStateNode temp = current;
        while (temp.prev != null) {
            temp = temp.prev;
        }

        if (temp.next != null) {
            temp.next.prev = null;
        }
        stateCount--;
    }

    public void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
        }
    }

    public void redo() {
        if (current != null && current.next != null) {
            current = current.next;
        }
    }

    public void displayCurrentState() {
        if (current != null) {
            System.out.println("Current State: " + current.text);
        } else {
            System.out.println("No text history available.");
        }
    }
}

public class TextEditor {
    public static void main(String[] args) {
        UndoRedoManager editor = new UndoRedoManager(10);

        editor.addState("Hello");
        editor.addState("Hello World");
        editor.addState("Hello World!");

        editor.displayCurrentState();

        System.out.println("Undoing...");
        editor.undo();
        editor.displayCurrentState();

        System.out.println("Undoing...");
        editor.undo();
        editor.displayCurrentState();

        System.out.println("Redoing...");
        editor.redo();
        editor.displayCurrentState();

//        Current State: Hello World!
//                Undoing...
//        Current State: Hello World
//        Undoing...
//        Current State: Hello
//        Redoing...
//        Current State: Hello World
    }
}
