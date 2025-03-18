class MovieNode{
    String movieTitle;
    String director;
    String yearOfRelease;
    double rating;
    MovieNode next;
    MovieNode prev;

    public MovieNode(String movieTitle, String director, String yearOfRelease, double rating) {
        this.movieTitle = movieTitle;
        this.director = director;
        this.yearOfRelease = yearOfRelease;
        this.rating = rating;
        this.next = null;
        this.prev = null;
    }
}

class MovieDll{
    private MovieNode head;
    private MovieNode tail;

    void insertAtBeginning(String movieTitle, String director, String yearOfRelease, double rating){
        MovieNode node = new MovieNode(movieTitle, director, yearOfRelease, rating);
        if(head==null){
            head = node;
            tail = node;
            return ;
        }
        node.next = head;
        head.prev = node;
        head = node;

    }

    void insetAtEnd(String movieTitle, String director, String yearOfRelease, double rating){
        MovieNode node = new MovieNode(movieTitle, director, yearOfRelease, rating);

        MovieNode temp = tail;
        if(tail==null){
            if(head==null){
                head = node;
                tail = node;
                return ;
            }
            temp = head;
            while(temp.next!=null){
                temp = temp.next;
            }
        }
            temp.next = node;
            node.prev = temp;
            tail = node;
    }

    void insertAtSpecificPosition(String movieTitle, String director, String yearOfRelease, double rating, int pos){
        if(pos==0){
            insertAtBeginning(movieTitle, director, yearOfRelease, rating);
            return ;
        }
        MovieNode temp = head;
        MovieNode node = new MovieNode(movieTitle, director, yearOfRelease, rating);
        while(temp!=null && pos-- > 0){
            temp = temp.next;
        }
        if(temp==null || temp.next==null){
            insetAtEnd(movieTitle, director, yearOfRelease, rating);
        }
        else{
            temp.next.prev = node;
            node.next = temp.next;
            node.prev = temp;
            temp.next = node;

        }
    }

    void deleteMovieByMovieTitle(String title){
        MovieNode temp = head;
        MovieNode prv = null;

        if(temp.movieTitle.equals(title)){
            head = temp.next;
            head.prev = null;
            temp.next = null;
            return ;
        }

        while(temp!=null){
            if(temp.movieTitle.equals(title)){
                if(temp==tail){
                    tail = prv;
                    prv.next = null;
                    temp.prev = null;
                    temp.next = null;
                    return ;
                }
                prv.next = temp.next;
                temp.next.prev = prv;
                temp.next = null;
                temp.prev = null;
                return ;
            }
            prv = temp;
            temp = temp.next;
        }
    }

    MovieNode searchMovie(String director){
        MovieNode temp = head;
        while (temp!=null && !temp.director.equals(director)){
            temp = temp.next;
        }
        return temp;
    }

    MovieNode searchMovie(double rating){
        MovieNode temp = head;
        while (temp!=null && temp.rating != rating){
            temp = temp.next;
        }
        return temp;
    }

    void displayRecord(MovieNode node){
        System.out.println("Movie Title: " + node.movieTitle);
        System.out.println("Director: " + node.director);
        System.out.println("Year of release: " + node.yearOfRelease);
        System.out.println("Rating: " + node.rating);
    }

    void displayDetailForAllNodes(boolean flag){

        if(flag){
            MovieNode node = head;
            while(node!=null){
                displayRecord(node);
                node = node.next;
            }
        }
        else{
            MovieNode node = tail;
            while(node!=null){
                displayRecord(node);
                node = node.prev;
            }
        }

    }

    void updateMovieRating(double rating, String title){
        MovieNode node = searchMovieByTitle(title);
        if(node==null) return ;
        node.rating = rating;
    }

    MovieNode searchMovieByTitle(String title){
        MovieNode temp = head;
        while (temp!=null && !temp.movieTitle.equals(title)){
            temp = temp.next;
        }
        return temp;
    }

}


public class MovieManagementSystem {
    public static void main(String[] args) {
        MovieDll movieList = new MovieDll();

        // Adding movies to the list
        movieList.insertAtBeginning("Inception", "Christopher Nolan", "2010", 8.8);
        movieList.insetAtEnd("Interstellar", "Christopher Nolan", "2014", 8.6);
        movieList.insertAtSpecificPosition("The Dark Knight", "Christopher Nolan", "2008", 9.0, 1);
        movieList.insertAtBeginning("Titanic", "James Cameron", "1997", 7.8);
        movieList.insetAtEnd("Avatar", "James Cameron", "2009", 7.9);

        // Display all movies from head to tail
        System.out.println("Displaying all movies:");
        movieList.displayDetailForAllNodes(true);

        // Searching for a movie by director
        System.out.println("\nSearching for movies directed by 'Christopher Nolan':");
        MovieNode foundMovie = movieList.searchMovie("Christopher Nolan");
        if (foundMovie != null) {
            movieList.displayRecord(foundMovie);
        } else {
            System.out.println("No movie found!");
        }

        // Updating the rating of a movie
        System.out.println("\nUpdating rating for 'Titanic'...");
        movieList.updateMovieRating(8.2, "Titanic");

        // Display all movies again after the update
        System.out.println("\nDisplaying all movies after update:");
        movieList.displayDetailForAllNodes(true);

        // Deleting a movie
        System.out.println("\nDeleting 'Avatar'...");
        movieList.deleteMovieByMovieTitle("Avatar");

        // Display all movies after deletion
        System.out.println("\nDisplaying all movies after deletion:");
        movieList.displayDetailForAllNodes(true);

//        Displaying all movies:
//        Movie Title: Titanic
//        Director: James Cameron
//        Year of release: 1997
//        Rating: 7.8
//        Movie Title: Inception
//        Director: Christopher Nolan
//        Year of release: 2010
//        Rating: 8.8
//        Movie Title: Interstellar
//        Director: Christopher Nolan
//        Year of release: 2014
//        Rating: 8.6
//        Movie Title: The Dark Knight
//        Director: Christopher Nolan
//        Year of release: 2008
//        Rating: 9.0
//        Movie Title: Avatar
//        Director: James Cameron
//        Year of release: 2009
//        Rating: 7.9
//
//        Searching for movies directed by 'Christopher Nolan':
//        Movie Title: Inception
//        Director: Christopher Nolan
//        Year of release: 2010
//        Rating: 8.8
//
//        Updating rating for 'Titanic'...
//
//        Displaying all movies after update:
//        Movie Title: Titanic
//        Director: James Cameron
//        Year of release: 1997
//        Rating: 8.2
//        Movie Title: Inception
//        Director: Christopher Nolan
//        Year of release: 2010
//        Rating: 8.8
//        Movie Title: Interstellar
//        Director: Christopher Nolan
//        Year of release: 2014
//        Rating: 8.6
//        Movie Title: The Dark Knight
//        Director: Christopher Nolan
//        Year of release: 2008
//        Rating: 9.0
//        Movie Title: Avatar
//        Director: James Cameron
//        Year of release: 2009
//        Rating: 7.9
//
//        Deleting 'Avatar'...
//
//        Displaying all movies after deletion:
//        Movie Title: Titanic
//        Director: James Cameron
//        Year of release: 1997
//        Rating: 8.2
//        Movie Title: Inception
//        Director: Christopher Nolan
//        Year of release: 2010
//        Rating: 8.8
//        Movie Title: Interstellar
//        Director: Christopher Nolan
//        Year of release: 2014
//        Rating: 8.6
//        Movie Title: The Dark Knight
//        Director: Christopher Nolan
//        Year of release: 2008
//        Rating: 9.0
    }
}