//Design an inventory management system using a singly linked list
// where each node stores information about an item such as
// Item Name, Item ID, Quantity, and Price. Implement the following functionalities:

class InventoryNode {
    String name;
    int itemId;
    int quantity;
    int price;
    InventoryNode next;

    InventoryNode(String name, int itemId, int quantity, int price){
        this.name = name;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

class InventorySinglyLinkedList {
    InventoryNode head;

    void addAtBeginning(String name, int itemId, int quantity, int price){
        InventoryNode inventoryNode = new InventoryNode(name, itemId, quantity, price);
        if(head == null){
            head = inventoryNode;
            return ;
        }
        inventoryNode.next = head;
        head = inventoryNode;
    }

    void addAtBeginning(InventoryNode inventoryNode){
        if(head == null){
            head = inventoryNode;
            return ;
        }
        inventoryNode.next = head;
        head = inventoryNode;
    }

    void addAtEnding(String name, int itemId, int quantity, int price){
        InventoryNode inventoryNode = new InventoryNode(name, itemId, quantity, price);
        InventoryNode prev = null;
        InventoryNode temp = head;
        while(temp != null){
            prev = temp;
            temp = temp.next;
        }
        if(prev != null){
            prev.next = inventoryNode;
        }
        else{
            head = inventoryNode;
        }
    }

    void addAtSpecificPosition(String name, int itemId, int quantity, int price, int pos){
        InventoryNode inventoryNode = new InventoryNode(name, itemId, quantity, price);
        InventoryNode temp = head;
        if(pos==0){
            addAtBeginning(inventoryNode);
            return ;
        }
        InventoryNode prev = null;
        while(temp != null && pos-- > 0) {
            prev = temp;
            temp = temp.next;
        }
        if(prev != null){
            prev.next = inventoryNode;
            inventoryNode.next = temp;
        }
    }

    void deleteItemByItemId(int id){
        InventoryNode temp = head;
        InventoryNode prev = null;
        while(temp != null){
            if(id == temp.itemId){
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

    InventoryNode searchItemByItemId(int id){
        InventoryNode temp = head;
        while(temp != null){
            if(id == temp.itemId){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    InventoryNode searchItemByItemName(String name){
        InventoryNode temp = head;
        while(temp != null){
            if(name.equals(temp.name)){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    void updateQuantityByItemIdOrName(int id, String name, int quantity){
        InventoryNode item = searchItemByItemId(id);

        if(item == null){
            item = searchItemByItemName(name);
        }

        if(item != null){
            item.quantity = quantity;
        }
    }

    void displayDetails(InventoryNode inventoryNode){
        System.out.println("Name: " + inventoryNode.name);
        System.out.println("Item Id: " + inventoryNode.itemId);
        System.out.println("Quantity: " + inventoryNode.quantity);
        System.out.println("Price: " + inventoryNode.price);
        System.out.println("Total value: " + inventoryNode.quantity * inventoryNode.price);
    }

    void displayDetailsForAllItems(){
        InventoryNode temp = head;
        while(temp != null){
            displayDetails(temp);
            System.out.println("----------");
            temp = temp.next;
        }
    }

    void swap(InventoryNode a, InventoryNode b){
        String tempName = a.name;
        int tempId = a.itemId;
        int tempQuantity = a.quantity;
        int tempPrice = a.price;

        a.name = b.name;
        a.itemId = b.itemId;
        a.quantity = b.quantity;
        a.price = b.price;

        b.name = tempName;
        b.itemId = tempId;
        b.quantity = tempQuantity;
        b.price = tempPrice;
    }

    void sortAccordingToItemName(boolean flag) {

        if(head==null || head.next==null){
            return ;
        }
        if(flag){
            for(InventoryNode i=head ; i!=null ; i=i.next){
                for(InventoryNode j=i.next ; j!=null ; j=j.next){
                    if(i.name.compareTo(j.name) > 0){
                        swap(i,j);
                    }
                }
            }
        }
        else{
            for(InventoryNode i=head ; i!=null ; i=i.next){
                for(InventoryNode j=i.next ; j!=null ; j=j.next){
                    if(i.name.compareTo(j.name) < 0){
                        swap(i,j);
                    }
                }
            }
        }

    }

    void sortAccordingToItemPrice(boolean flag){

        if(head==null || head.next==null){
            return ;
        }

        if(flag){
            for(InventoryNode i=head ; i!=null ; i=i.next){
                for(InventoryNode j=i.next ; j!=null ; j=j.next){
                    if(i.price > j.price){
                        swap(i,j);
                    }
                }
            }
        }
        else{
            for(InventoryNode i=head ; i!=null ; i=i.next){
                for(InventoryNode j=i.next ; j!=null ; j=j.next){
                    if(i.price < j.price){
                        swap(i,j);
                    }
                }
            }
        }

    }
}

public class InventoryManagementSystem {
    public static void main(String[] args) {
        InventorySinglyLinkedList inventory = new InventorySinglyLinkedList();

        inventory.addAtBeginning("Laptop", 101, 10, 50000);
        inventory.addAtEnding("Mouse", 102, 50, 1000);
        inventory.addAtSpecificPosition("Keyboard", 103, 30, 2000, 1);
        inventory.displayDetailsForAllItems();

        System.out.println("Sorting by Name...");
        inventory.sortAccordingToItemName(false);
        inventory.displayDetailsForAllItems();

        System.out.println("Sorting by Price...");
        inventory.sortAccordingToItemPrice(true);
        inventory.displayDetailsForAllItems();

//        Name: Laptop
//        Item Id: 101
//        Quantity: 10
//        Price: 50000
//        Total value: 500000
//                ----------
//        Name: Keyboard
//        Item Id: 103
//        Quantity: 30
//        Price: 2000
//        Total value: 60000
//                ----------
//        Name: Mouse
//        Item Id: 102
//        Quantity: 50
//        Price: 1000
//        Total value: 50000
//                ----------
//        Sorting by Name...
//        Name: Mouse
//        Item Id: 102
//        Quantity: 50
//        Price: 1000
//        Total value: 50000
//                ----------
//        Name: Laptop
//        Item Id: 101
//        Quantity: 10
//        Price: 50000
//        Total value: 500000
//                ----------
//        Name: Keyboard
//        Item Id: 103
//        Quantity: 30
//        Price: 2000
//        Total value: 60000
//                ----------
//        Sorting by Price...
//        Name: Mouse
//        Item Id: 102
//        Quantity: 50
//        Price: 1000
//        Total value: 50000
//                ----------
//        Name: Keyboard
//        Item Id: 103
//        Quantity: 30
//        Price: 2000
//        Total value: 60000
//                ----------
//        Name: Laptop
//        Item Id: 101
//        Quantity: 10
//        Price: 50000
//        Total value: 500000
//                ----------
    }
}