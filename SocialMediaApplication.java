import java.util.Arrays;

class UserNode {
    int userId;
    String name;
    int age;
    int[] friendIds;
    int friendCount;
    UserNode next;

    public UserNode(int userId, String name, int age, int maxFriends) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendIds = new int[maxFriends]; // Fixed-size array for friends
        this.friendCount = 0;
        this.next = null;
    }
}

class SocialMediaNetwork {
    UserNode head;
    int maxFriendsPerUser;

    public SocialMediaNetwork(int maxFriendsPerUser) {
        this.head = null;
        this.maxFriendsPerUser = maxFriendsPerUser;
    }

    void addUser(int userId, String name, int age) {
        UserNode newUser = new UserNode(userId, name, age, maxFriendsPerUser);
        newUser.next = head;
        head = newUser;
    }

    UserNode findUser(int userId) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.userId == userId) return temp;
            temp = temp.next;
        }
        return null;
    }

    void addFriend(int userId1, int userId2) {
        UserNode user1 = findUser(userId1);
        UserNode user2 = findUser(userId2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        if (user1.friendCount < maxFriendsPerUser && user2.friendCount < maxFriendsPerUser) {
            user1.friendIds[user1.friendCount++] = userId2;
            user2.friendIds[user2.friendCount++] = userId1;
            System.out.println("Friend connection added successfully.");
        } else {
            System.out.println("Friend list full for one or both users.");
        }
    }

    void removeFriend(int userId1, int userId2) {
        UserNode user1 = findUser(userId1);
        UserNode user2 = findUser(userId2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        user1.friendCount = removeFromArray(user1.friendIds, user1.friendCount, userId2);
        user2.friendCount = removeFromArray(user2.friendIds, user2.friendCount, userId1);
        System.out.println("Friend connection removed successfully.");
    }

    int removeFromArray(int[] arr, int count, int target) {
        for (int i = 0; i < count; i++) {
            if (arr[i] == target) {
                arr[i] = arr[count - 1]; // Replace with last element
                return count - 1;
            }
        }
        return count;
    }

    void displayFriends(int userId) {
        UserNode user = findUser(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        System.out.println("Friends of " + user.name + ": " + Arrays.toString(Arrays.copyOf(user.friendIds, user.friendCount)));
    }

    void findMutualFriends(int userId1, int userId2) {
        UserNode user1 = findUser(userId1);
        UserNode user2 = findUser(userId2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        System.out.print("Mutual friends: ");
        for (int i = 0; i < user1.friendCount; i++) {
            for (int j = 0; j < user2.friendCount; j++) {
                if (user1.friendIds[i] == user2.friendIds[j]) {
                    System.out.print(user1.friendIds[i] + " ");
                }
            }
        }
        System.out.println();
    }

    void displayAllUsers() {
        UserNode temp = head;
        while (temp != null) {
            System.out.println("User ID: " + temp.userId + ", Name: " + temp.name + ", Age: " + temp.age);
            temp = temp.next;
        }
    }
}

public class SocialMediaApplication {
    public static void main(String[] args) {
        SocialMediaNetwork network = new SocialMediaNetwork(5);

        network.addUser(1, "Alice", 25);
        network.addUser(2, "Bob", 27);
        network.addUser(3, "Charlie", 22);

        network.addFriend(1, 2);
        network.addFriend(1, 3);
        network.addFriend(2, 3);

        network.displayFriends(1);
        network.displayFriends(2);

        network.findMutualFriends(1, 2);

        network.removeFriend(1, 3);
        network.displayFriends(1);


//        Friend connection added successfully.
//        Friend connection added successfully.
//        Friend connection added successfully.
//        Friends of Alice: [2, 3]
//        Friends of Bob: [1, 3]
//        Mutual friends: 3
//        Friend connection removed successfully.
//        Friends of Alice: [2]
    }
}
