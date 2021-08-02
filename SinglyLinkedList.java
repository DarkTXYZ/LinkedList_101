public class SinglyLinkedList {
    Node head;
    String listName;

    public SinglyLinkedList(String name) {
        this.head = null;
        this.listName = name;
    }

    public void popBack() {
        if (isEmpty()) {
            System.out.println("ERROR");
        } else if (head.next == null) {
            head = null;
        } else {
            Node cur = head;
            while (cur.next.next != null)
                cur = cur.next;
            cur.next = null;
        }
    }

    public void popFront() {
        if (isEmpty()) {
            System.out.println("ERROR");
        } else {
            if (head.next == null)
                head = null;
            else
                head = head.next;
        }
    }

    public Node topFront() {
        if (isEmpty()) {
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else {
            return head;
        }
    }

    public Node topBack() {
        if (isEmpty()) {
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else {
            Node cur = head;
            while (cur.next != null)
                cur = cur.next;
            return cur;
        }
    }

    public void pushFront(Node node) {
        if (isEmpty()) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    public void pushBack(Node node) {
        if (isEmpty()) {
            head = node;
        } else {
            Node cur = topBack();
            cur.next = node;
        }
    }

    public Node findNode(int id) {
        if (isEmpty()) {
            return new Node("Empty List!");
        } else {
            Node cur = head;
            while (cur != null) {
                if (cur.student_id == id)
                    return cur;
                cur = cur.next;
            }
            return new Node("Student Not Found!");
        }
    }

    public Node eraseNode(int id) {
        if (isEmpty()) {
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else {
            Node cur = head;
            Node prev = null;
            while (cur != null) {
                if (cur.student_id == id) {
                    if (prev == null) {
                        popFront();
                    } else {
                        prev.next = cur.next;
                        cur.next = null;
                    }
                    return cur;
                }
                prev = cur;
                cur = cur.next;
            }
            return new Node("Student Not Found!");
        }
    }

    public void addNodeAfter(Node node1, Node node2) {
        node2.next = node1.next;
        node1.next = node2;
    }

    public void addNodeBefore(Node node1, Node node2) {
        Node cur = head;
        Node prev = null;
        while (cur.student_id != node1.student_id) {
            prev = cur;
            cur = cur.next;
        }
        if (prev == null) {
            pushFront(node2);
        } else {
            prev.next = node2;
            node2.next = cur;
        }

    }

    public boolean isEmpty() {
        if (head == null)
            return true;
        return false;
    }

    public void merge(SinglyLinkedList list) {
        Node tail = topBack();
        tail.next = list.head;
    }

    public void printStructure() {
        Node current = head;
        System.out.print(listName + ": head -> ");
        while (current != null) {
            System.out.print("{" + current.student_id + "} -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public Node whoGotHighestGPA() {
        if (isEmpty()) {
            return new Node("Empty List!");
        } else {
            Node cur = head;
            double mx = 0;
            Node mxnode = head;
            while (cur != null) {
                if (mx <= cur.gpa) {
                    mx = cur.gpa;
                    mxnode = cur;
                }
                cur = cur.next;
            }
            return mxnode;
        }
    }
}
