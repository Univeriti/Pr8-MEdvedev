public class MyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public MyLinkedList(int capacity) throws InvalidCapacityException {
        if (capacity < 0) {
            throw new InvalidCapacityException("Помилка: Невірний початковий capacity");
        }
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void addLast(int data) {
        if (data < 0) {
            throw new InvalidDataException("Помилка: Дані не можуть бути від'ємними");
        }
        Node newNode = new Node(data);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void addFirst(int data) {
        if (data < 0) {
            throw new InvalidDataException("Помилка: Дані не можуть бути від'ємними");
        }
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void add(int index, int data) {
        if (data < 0) {
            throw new InvalidDataException("Помилка: Дані не можуть бути від'ємними");
        }
        if (index < 0 || index > size) {
            throw new InvalidIndexException("Помилка: Індекс поза межами при додаванні");
        }
        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == size) {
            addLast(data);
            return;
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        Node newNode = new Node(data);
        Node prevNode = current.prev;

        prevNode.next = newNode;
        newNode.prev = prevNode;
        newNode.next = current;
        current.prev = newNode;

        size++;
    }

    public int get(int index) throws EmptyLinkedListException {
        if (size == 0) {
            throw new EmptyLinkedListException("Помилка: Список порожній");
        }
        if (index < 0 || index >= size) {
            throw new InvalidIndexException("Помилка: Невірний індекс");
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return size;
    }

    public void remove(int index) throws EmptyLinkedListException {
        if (size == 0) {
            throw new EmptyLinkedListException("Помилка: Список порожній, видалення неможливе");
        }
        if (index < 0 || index >= size) {
            throw new InvalidIndexException("Помилка: Індекс поза межами при видаленні");
        }

        if (size == 1) {
            head = null;
            tail = null;
        } else if (index == 0) {
            head = head.next;
            head.prev = null;
        } else if (index == size - 1) {
            tail = tail.prev;
            tail.next = null;
        } else {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            Node prevNode = current.prev;
            Node nextNode = current.next;

            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }
        size--;
    }

    public void removeByValue(int value) throws EmptyLinkedListException, ValueNotFoundException {
        if (size == 0) {
            throw new EmptyLinkedListException("Помилка: Список порожній");
        }
        Node current = head;
        int index = 0;
        while (current != null) {
            if (current.data == value) {
                remove(index);
                return;
            }
            current = current.next;
            index++;
        }
        throw new ValueNotFoundException("Помилка: Елемент з таким значенням не знайдено");
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public void printList() {
        Node current = head;
        System.out.print("Список: ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}