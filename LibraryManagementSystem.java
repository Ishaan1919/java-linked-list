class BookNode {
    String title, author, genre;
    int bookId;
    boolean isAvailable;
    BookNode next, prev;

    BookNode(String title, String author, String genre, int bookId, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.isAvailable = isAvailable;
        this.next = this.prev = null;
    }
}

class LibraryDLL {
    private BookNode head, tail;
    private int bookCount = 0;

    void addAtBeginning(String title, String author, String genre, int bookId, boolean isAvailable) {
        BookNode node = new BookNode(title, author, genre, bookId, isAvailable);
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        bookCount++;
    }

    void addAtEnd(String title, String author, String genre, int bookId, boolean isAvailable) {
        BookNode node = new BookNode(title, author, genre, bookId, isAvailable);
        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        bookCount++;
    }

    void addAtPosition(String title, String author, String genre, int bookId, boolean isAvailable, int pos) {
        if (pos <= 0) {
            addAtBeginning(title, author, genre, bookId, isAvailable);
            return;
        }
        BookNode temp = head;
        for (int i = 0; temp != null && i < pos - 1; i++) temp = temp.next;
        if (temp == null || temp.next == null) {
            addAtEnd(title, author, genre, bookId, isAvailable);
        } else {
            BookNode node = new BookNode(title, author, genre, bookId, isAvailable);
            node.next = temp.next;
            node.prev = temp;
            temp.next.prev = node;
            temp.next = node;
            bookCount++;
        }
    }

    void removeBookById(int bookId) {
        BookNode temp = head;
        while (temp != null && temp.bookId != bookId) temp = temp.next;
        if (temp == null) return;
        if (temp == head) head = temp.next;
        if (temp == tail) tail = temp.prev;
        if (temp.prev != null) temp.prev.next = temp.next;
        if (temp.next != null) temp.next.prev = temp.prev;
        bookCount--;
    }

    BookNode searchByTitleOrAuthor(String key) {
        BookNode temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(key) || temp.author.equalsIgnoreCase(key)) return temp;
            temp = temp.next;
        }
        return null;
    }

    void updateAvailability(int bookId, boolean status) {
        BookNode temp = head;
        while (temp != null && temp.bookId != bookId) temp = temp.next;
        if (temp != null) temp.isAvailable = status;
    }

    void displayForward() {
        BookNode temp = head;
        while (temp != null) {
            displayBook(temp);
            temp = temp.next;
        }
    }

    void displayBackward() {
        BookNode temp = tail;
        while (temp != null) {
            displayBook(temp);
            temp = temp.prev;
        }
    }

    void displayBook(BookNode book) {
        System.out.println("Title: " + book.title);
        System.out.println("Author: " + book.author);
        System.out.println("Genre: " + book.genre);
        System.out.println("Book ID: " + book.bookId);
        System.out.println("Available: " + (book.isAvailable ? "Yes" : "No"));
        System.out.println("----------------------");
    }

    int getTotalBooks() {
        return bookCount;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        LibraryDLL library = new LibraryDLL();

        library.addAtBeginning("The Great Gatsby", "F. Scott Fitzgerald", "Fiction", 101, true);
        library.addAtEnd("To Kill a Mockingbird", "Harper Lee", "Fiction", 102, true);
        library.addAtPosition("1984", "George Orwell", "Dystopian", 103, false, 1);

        System.out.println("Library Collection (Forward Order):");
        library.displayForward();

        System.out.println("Library Collection (Reverse Order):");
        library.displayBackward();

        System.out.println("Total Books in Library: " + library.getTotalBooks());

        System.out.println("Updating availability of '1984'...");
        library.updateAvailability(103, true);

        System.out.println("Searching for 'Harper Lee'...");
        BookNode foundBook = library.searchByTitleOrAuthor("Harper Lee");
        if (foundBook != null) {
            library.displayBook(foundBook);
        } else {
            System.out.println("Book not found!");
        }

        System.out.println("Removing 'The Great Gatsby'...");
        library.removeBookById(101);

        System.out.println("Updated Library Collection:");
        library.displayForward();


//        Library Collection (Forward Order):
//        Title: The Great Gatsby
//        Author: F. Scott Fitzgerald
//        Genre: Fiction
//        Book ID: 101
//        Available: Yes
//                ----------------------
//        Title: 1984
//        Author: George Orwell
//        Genre: Dystopian
//        Book ID: 103
//        Available: No
//                ----------------------
//        Title: To Kill a Mockingbird
//        Author: Harper Lee
//        Genre: Fiction
//        Book ID: 102
//        Available: Yes
//                ----------------------
//        Library Collection (Reverse Order):
//        Title: To Kill a Mockingbird
//        Author: Harper Lee
//        Genre: Fiction
//        Book ID: 102
//        Available: Yes
//                ----------------------
//        Title: 1984
//        Author: George Orwell
//        Genre: Dystopian
//        Book ID: 103
//        Available: No
//                ----------------------
//        Title: The Great Gatsby
//        Author: F. Scott Fitzgerald
//        Genre: Fiction
//        Book ID: 101
//        Available: Yes
//                ----------------------
//        Total Books in Library: 3
//        Updating availability of '1984'...
//        Searching for 'Harper Lee'...
//        Title: To Kill a Mockingbird
//        Author: Harper Lee
//        Genre: Fiction
//        Book ID: 102
//        Available: Yes
//                ----------------------
//        Removing 'The Great Gatsby'...
//        Updated Library Collection:
//        Title: 1984
//        Author: George Orwell
//        Genre: Dystopian
//        Book ID: 103
//        Available: Yes
//                ----------------------
//        Title: To Kill a Mockingbird
//        Author: Harper Lee
//        Genre: Fiction
//        Book ID: 102
//        Available: Yes
//                ----------------------
    }
}
