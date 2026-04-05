public class Main {
    public static void main(String[] args) {
        try {
            MyLinkedList listWithBadCapacity = new MyLinkedList(-10);
        } catch (InvalidCapacityException e) {
            System.out.println(e.getMessage());
        }

        MyLinkedList list = new MyLinkedList();

        try {
            list.get(0);
        } catch (EmptyLinkedListException e) {
            System.out.println(e.getMessage());
        }

        try {
            list.addLast(-5);
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }

        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        try {
            list.get(100);
        } catch (InvalidIndexException | EmptyLinkedListException e) {
            System.out.println(e.getMessage());
        }

        try {
            list.removeByValue(999);
        } catch (ValueNotFoundException | EmptyLinkedListException e) {
            System.out.println(e.getMessage());
        }

        list.printList();
    }
}