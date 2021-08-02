public class DoublyLinkedList {
    Node head;
    Node tail;
    String listName;

    public DoublyLinkedList(String name) {
        this.head = null;
        this.tail = null;
        this.listName = name;
    }

    public void popBack() {

        // Concept : 
        
        // เราจะลบสายที่เชื่อมกันระหว่าง tail และ Node ก่อนหน้า tail (prevTail = tail.previous) 
        // จากนั้นจึงทำการเปลี่ยน tail ใหม่
        //              ... <-> node <-> node <-> node
        //                                 ↑        ↑
        //                              prevTail   tail
        // โดยวิธีการลบก็คือ ให้ prevTail.next เลิกชี้ไปยัง tail 
        //              ... <-> node <-> node <-  node
        //                                 ↑        ↑
        //                             prevTail    tail
        // และ tail.previous เลิกชี้ไปยัง prevTail
        //              ... <-> node <-> node     node
        //                                 ↑        ↑
        //                             prevTail    tail
        // และค่อยเซ็ต prevTail ให้เป็น Tail ใหม่
        //              ... <-> node <-> node     node
        //                                 ↑
        //                          prevTail,tail          

        // แต่จะมีกรณียกเว้นคือ ถ้า list มี Node แค่อันเดียว ก็เซ็ตให้ head และ tail ชี้ไปยัง null ได้เลย

        if (isEmpty()) {
            System.out.println("ERROR");
        } else if (head == tail) {
            head = tail = null;
        } else {
            Node prevTail = tail.previous;
            tail.previous = null;
            prevTail.next = null;
            tail = prevTail;
        }
    }

    public void popFront() {

        // Concept : 
        
        // เราจะลบ head และ เซ็ตให้ Node หลังจาก head (nextHead = head.next) กลับเป็น head ใหม่
        //              node <-> node <-> node <-> ...
        //                ↑       ↑
        //              head    nextHead
        // โดยวิธีการลบก็คือ ให้ head.next เลิกชี้ไปยัง nextHead 
        //              node <-  node <-> node <-> ...
        //               ↑         ↑
        //              head   nextHead
        // และ nextHead.previous เลิกชี้ไปยัง head
        //              node     node <-> node <-> ...
        //               ↑         ↑
        //              head   nextHead
        // และค่อยเซ็ต prevTail ให้เป็น Tail ใหม่                 
        //              node     node <-> node <-> ...
        //                         ↑
        //                    head,nextHead

        // แต่จะมีกรณียกเว้นคือ ถ้า list มี Node แค่อันเดียว ก็เซ็ตให้ head และ tail ชี้ไปยัง null ได้เลย

        if (isEmpty()) {
            System.out.println("ERROR");
        } else if (head == tail) {
            head = tail = null;
        } else {
            Node del = head;
            Node newHead = head.next;
            del.next = null;
            newHead.previous = null;
            head = newHead;
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
            return tail;
        }
    }

    public void pushFront(Node newNode) {

        // Concept : 
        
        // กรณีที่ list ว่าง ก็ให้ newNode เป็น head และ tail เลย

        // กรณีที่ list ไม่ว่าง
        // จะต้องเชื่อม newNode ก่อนหน้า head และทำการเปลี่ยน head ใหม่
        //              node     node <-> node <-> ...
        //                ↑        ↑
        //             newNode   head
        // นั่นคือ newNode.next ชี้ไปยัง head 
        //              node  -> node <-> node <-> ...
        //                ↑        ↑
        //             newNode   head
        // และ head.previous ชี้กลับมายัง newNode
        //              node <-> node <-> node <-> ...
        //                ↑        ↑
        //             newNode   head
        // และทำการเปลี่ยน head
        //              node <-> node <-> node <-> ...
        //               ↑
        //          newNode,head

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }
    }

    public void pushBack(Node newNode) {

        // Concept : 
        
        // กรณีที่ list ว่าง ก็ให้ newNode เป็น head และ tail เลย

        // กรณีที่ list ไม่ว่าง
        // จะต้องเชื่อม newNode ตามหลัง tail และทำการเปลี่ยน tail ใหม่
        //              ... <-> node <-> node     node
        //                                ↑         ↑
        //                               tail    newNode
        // นั่นคือ newNode.previous ชี้ไปยัง tail 
        //              ... <-> node <-> node <-  node
        //                                ↑         ↑
        //                               tail    newNode
        // และ tail.next ชี้กลับมายัง newNode
        //              ... <-> node <-> node <-> node
        //                                ↑         ↑
        //                               tail    newNode
        // และทำการเปลี่ยน tail
        //              ... <-> node <-> node <-> node
        //                                          ↑
        //                                      tail,newNode

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
    }

    public Node findNode(int id) {

        // Concept : 
        
        // จะต้องวนทั่วทั้ง list เพื่อหา Node ที่ต้องการ

        // วิธีการคือ สร้าง Node ขึ้นมาเพื่อใช้ในการวน loop (cur) โดยให้ Node นั้นเริ่มที่ head 
        //           node <-> node <-> ... <-> node <-> node 
        //             ↑                                  ↑
        //         head,cur                              tail
        // หาก Node นี้ไม่ใช่ Node ที่ต้องการ
        // ก็จะทำการวน cur ไปยัง node ถัดไป (cur = cur.next) 
        //           node <-> node <-> ... <-> node <-> node 
        //             ↑        ↑                         ↑  
        //           head      cur                       tail
        // และทำอย่างนี้ไปเรื่อยๆ จนกว่าจะเจอ Node ที่ต้องการ
        // loop เหล่านี้จะหยุด ก็ต่อเมื่อ cur นั้นชี้ null (tail.next == null)
        //              ... <-> node <-> node <-> node
        //                                          ↑       ↑
        //                                         tail    cur

        if (isEmpty()) {
            return new Node("Empty List!");
        } else {
            Node cur = head;
            while (cur != null) {
                if (id == cur.student_id)
                    return cur;
                cur = cur.next;
            }
            return new Node("Student Not Found!");
        }
    }

    public Node eraseNode(int id) {

        // Concept : 
        
        // 1. หาก Node ที่ต้องการจะลบ คือ head ก็แค่ใช้คำสั่ง popFront()

        // 2. หาก Node ที่ต้องการจะลบ คือ tail ก็แค่ใช้คำสั่ง popBack()

        // 3. หาก Node ที่ต้องการจะลบไม่ใช่ head และ tail 
        // วิธีการคือ สร้าง Node ขึ้นมาเพื่อใช้ในการวน loop (cur) โดยให้ Node นั้นเริ่มที่ head 
        //           node <-> node <-> ... <-> node <-> node 
        //             ↑                                  ↑
        //         head,cur                              tail
        // หา Node ที่ต้องการจะลบ
        // โดยทำการวน cur ไปยัง node ถัดไป (cur = cur.next) 
        //           node <-> node <-> ... <-> node <-> node 
        //             ↑        ↑                         ↑
        //           head      cur                       tail
        // และทำอย่างนี้ไปเรื่อยๆ จนกว่าจะเจอ Node ที่ต้องการ
        // เมื่อเจอ Node แล้ว จะต้องทำการนำ Node ก่อนหน้า(before = cur.previous) 
        // กับ Node ตามหลังมาเชื่อมกัน (after = cur.next)
        //              ... <-> node <-> node <-> node <-> ...
        //                        ↑        ↑        ↑
        //                     before     cur    after
        // นั่นคือ before.next ชี้ไปยัง after 
        //                         ________________
        //                        |                ↓
        //              ... <-> node <-  node <-> node <-> ...
        //                        ↑        ↑        ↑
        //                     before     cur    after
        // และ after.previous ชี้มายัง before
        //                       ____________________
        //                      |  ________________  |
        //                      ↓ |                ↓ |
        //              ... <-> node <-  node  -> node <-> ...
        //                        ↑        ↑        ↑
        //                     before     cur    after
        // จากนั้น ให้ cur.next และ cur.previous ชี้ไปยัง null 
        // เพื่อทำให้ Node cur ไม่มีการเชื่อมต่อกับ list อีก
        //                        ___________________
        //                       | ________________  |
        //                       ↓|                ↓ |
        //              ... <-> node     node     node <-> ...
        //                        ↑        ↑        ↑
        //                     before     cur    after

        if (isEmpty()) {
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else {
            Node cur = head;
            while (cur != null) {
                if (id == cur.student_id) {
                    if (cur == head) {
                        popFront();
                        return cur;
                    } else if (cur == tail) {
                        popBack();
                        return cur;
                    } else {
                        Node prev = cur.previous;
                        Node next = cur.next;
                        prev.next = next;
                        next.previous = prev;
                        cur.next = null;
                        cur.previous = null;
                        return cur;
                    }
                }
                cur = cur.next;
            }
            return new Node("Student Not Found!");
        }
    }

    public void addNodeAfter(Node node1, Node node2) {

        // Concept : 
        
        // ให้ node1 และ node หลัง node1 (node3 = node1.next) ชี้ไปยัง node2
        // และ node2 ชี้กลับมายัง node เหล่านี้
        //              ... <-> node <-> node <-> ...
        //                      ↑           ↑
        //                   node1         node3
        //                           node
        //                            ↑
        //                          node2 
        // นั่นคือ node1.next ชี้มา node2
        //              ... <-> node <-  node <-> ...
        //                      ↑  |        ↑
        //                   node1 |       node3
        //                         '→node
        //                            ↑
        //                          node2 
        // node2.previous ชี้มา node1
        //              ... <-> node <-  node <-> ...
        //                      ↑ ↑|        ↑
        //                  node1 ||       node3
        //                        |'→node
        //                        '--'↑
        //                          node2 
        // node3.previous ชี้มา node2
        //              ... <-> node      node <-> ...
        //                      ↑ ↑|      |  ↑
        //                  node1 ||      |  node3
        //                        |'→node←'
        //                        '--'↑
        //                          node2 
        // node2.next ชี้มา node3
        //              ... <-> node      node <-> ...
        //                      ↑ ↑|      |↑ ↑
        //                  node1 ||      || node3
        //                        |'→node←'|
        //                        '--'↑ '--'
        //                          node2 

        Node pre = findNode(node1.student_id);
        Node nxt = pre.next;
        if (nxt == null) {
            pushBack(node2);
        } else {
            pre.next = node2;
            nxt.previous = node2;
            node2.previous = pre;
            node2.next = nxt;
        }
    }

    public void addNodeBefore(Node node1, Node node2) {
        Node nxt = findNode(node1.student_id);
        Node pre = nxt.previous;
        if (pre == null) {
            pushFront(node2);
        } else {
            pre.next = node2;
            nxt.previous = node2;
            node2.previous = pre;
            node2.next = nxt;
        }
    }

    public boolean isEmpty() {
        if (head == null)
            return true;
        return false;
    }

    public void merge(DoublyLinkedList list) {
        this.tail.next = list.head;
        list.head.previous = this.tail;
    }

    public void printStructure() {
        Node current = head;
        System.out.print(listName + ": head <-> ");
        while (current != null) {
            System.out.print("{" + current.student_id + "} <-> ");
            current = current.next;
        }
        System.out.println("tail");
    }

    // This may be useful for you for implementing printStructure()
    public void printStructureBackward() {
        Node current = tail;
        System.out.print(listName + ": tail <-> ");
        while (current != null) {
            System.out.print("{" + current.student_id + "} <-> ");
            current = current.previous;
        }
        System.out.println("head");
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
