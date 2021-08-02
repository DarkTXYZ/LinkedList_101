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
        // 1. หาก list มี Node แค่ตัวเดียว (head == tail) ก็เซ็ตให้ head และ tail ชี้ไปยัง null ได้เลย
        // 2. หาก list มี Node หลายตัว เราจะลบสายที่เชื่อมกันระหว่าง tail 
        // และ Node ก่อน tail (prevTail = tail.previous) จากนั้นจึงทำการเปลี่ยน tail ใหม่
        //
        //              ... <-> node <-> node <-> node
        //                                 ↑        ↑
        //                              prevTail   tail
        //
        // โดยวิธีการลบก็คือ ให้ prevTail.next เลิกชี้ไปยัง tail 
        //
        //              ... <-> node <-> node <-  node
        //                                 ↑        ↑
        //                             prevTail    tail
        //
        // และ tail.previous เลิกชี้ไปยัง prevTail
        //
        //              ... <-> node <-> node     node
        //                                 ↑        ↑
        //                             prevTail    tail
        //
        // และค่อยเซ็ต prevTail ให้เป็น Tail ใหม่
        //
        //              ... <-> node <-> node     node
        //                                 ↑
        //                          prevTail,tail          
        //

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
        // 1. หาก list มี Node แค่ตัวเดียว (head == tail) ก็เซ็ตให้ head และ tail ชี้ไปยัง null ได้เลย
        // 2. หาก list มี Node หลายตัว เราจะลบสายที่เชื่อมกันระหว่าง head 
        // และ Node หลังจาก head (nextHead = head.next) จากนั้นจึงทำการเปลี่ยน head ใหม่
        //
        //              node <-> node <-> node <-> ...
        //                ↑       ↑
        //              head    nextHead
        //
        // โดยวิธีการลบก็คือ ให้ head.next เลิกชี้ไปยัง nextHead 
        //
        //              node <-  node <-> node <-> ...
        //               ↑         ↑
        //              head   nextHead
        //
        // และ nextHead.previous เลิกชี้ไปยัง head
        //
        //              node     node <-> node <-> ...
        //               ↑         ↑
        //              head   nextHead
        //
        // และค่อยเซ็ต nextHead ให้เป็น head ใหม่                 
        //
        //              node     node <-> node <-> ...
        //                         ↑
        //                    head,nextHead
        //

        if (isEmpty()) {
            System.out.println("ERROR");
        } else if (head == tail) {
            head = tail = null;
        } else {
            Node nextHead = head.next;
            head.next = null;
            nextHead.previous = null;
            head = nextHead;
        }
    }

    public Node topFront() {

        // Concept : 
        // ต้องการ Node ที่อยู่ข้างหน้าสุด นั่นก็คือ head นั่นเอง

        if (isEmpty()) {
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else {
            return head;
        }
    }

    public Node topBack() {

        // Concept : 
        // ต้องการ Node ที่อยู่ข้างหลังสุด นั่นก็คือ tail นั่นเอง

        if (isEmpty()) {
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else {
            return tail;
        }
    }

    public void pushFront(Node newNode) {

        // Concept : 
        // 1. กรณีที่ list ว่าง ก็ให้ newNode เป็น head และ tail เลย
        // 2. กรณีที่ list ไม่ว่าง จะต้องเชื่อม newNode ก่อนหน้า head และทำการเปลี่ยน head ใหม่
        //
        //              node     node <-> node <-> ...
        //                ↑        ↑
        //             newNode   head
        //
        // นั่นคือ newNode.next ชี้ไปยัง head 
        //
        //              node  -> node <-> node <-> ...
        //                ↑        ↑
        //             newNode   head
        //
        // และ head.previous ชี้กลับมายัง newNode
        //
        //              node <-> node <-> node <-> ...
        //                ↑        ↑
        //             newNode   head
        //
        // และทำการเปลี่ยน head
        //
        //              node <-> node <-> node <-> ...
        //               ↑
        //          newNode,head
        //

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
        // 1. กรณีที่ list ว่าง ก็ให้ newNode เป็น head และ tail เลย
        // 2. กรณีที่ list ไม่ว่าง จะต้องเชื่อม newNode ตามหลัง tail และทำการเปลี่ยน tail ใหม่
        //
        //              ... <-> node <-> node     node
        //                                ↑         ↑
        //                               tail    newNode
        //
        // นั่นคือ newNode.previous ชี้ไปยัง tail 
        //
        //              ... <-> node <-> node <-  node
        //                                ↑         ↑
        //                               tail    newNode
        //
        // และ tail.next ชี้กลับมายัง newNode
        //
        //              ... <-> node <-> node <-> node
        //                                ↑         ↑
        //                               tail    newNode
        //
        // และทำการเปลี่ยน tail
        //
        //              ... <-> node <-> node <-> node
        //                                          ↑
        //                                      tail,newNode
        //

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
        //
        //           node <-> node <-> ... <-> node <-> node 
        //             ↑                                  ↑
        //         head,cur                              tail
        //
        // หาก Node นี้ไม่ใช่ Node ที่ต้องการ
        // ก็จะทำการวน cur ไปยัง node ถัดไป (cur = cur.next) 
        //
        //           node <-> node <-> ... <-> node <-> node 
        //             ↑        ↑                         ↑  
        //           head      cur                       tail
        //
        // และทำอย่างนี้ไปเรื่อยๆ จนกว่าจะเจอ Node ที่ต้องการ
        // loop เหล่านี้จะหยุด ก็ต่อเมื่อ cur นั้นชี้ null (tail.next == null)
        //
        //              ... <-> node <-> node <-> node
        //                                          ↑       ↑
        //                                         tail    cur
        //

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
        // 3. หาก Node ที่ต้องการจะลบไม่ใช่ head และ tail ใช้วิธีการด้านล่าง
        // วิธีการคือ สร้าง Node ขึ้นมาเพื่อใช้ในการวน loop (cur) โดยให้ Node นั้นเริ่มที่ head 
        //
        //           node <-> node <-> ... <-> node <-> node 
        //             ↑                                  ↑
        //         head,cur                              tail
        //
        // หา Node ที่ต้องการจะลบ โดยทำการวน cur ไปยัง node ถัดไป (cur = cur.next) 
        //
        //           node <-> node <-> ... <-> node <-> node 
        //             ↑        ↑                         ↑
        //           head      cur                       tail
        //
        // และทำอย่างนี้ไปเรื่อยๆ จนกว่าจะเจอ Node ที่ต้องการ
        // เมื่อเจอ Node แล้ว จะต้องทำการนำ Node ก่อนหน้า(before = cur.previous) 
        // กับ Node ตามหลังมาเชื่อมกัน (after = cur.next)
        //
        //              ... <-> node <-> node <-> node <-> ...
        //                        ↑        ↑        ↑
        //                     before     cur    after
        //
        // นั่นคือ before.next ชี้ไปยัง after 
        //
        //                         ________________
        //                        |                ↓
        //              ... <-> node <-  node <-> node <-> ...
        //                        ↑        ↑        ↑
        //                     before     cur    after
        //
        // และ after.previous ชี้มายัง before
        //
        //                       ____________________
        //                      |  ________________  |
        //                      ↓ |                ↓ |
        //              ... <-> node <-  node  -> node <-> ...
        //                        ↑        ↑        ↑
        //                     before     cur    after
        //
        // จากนั้น ให้ cur.next และ cur.previous ชี้ไปยัง null 
        // เพื่อทำให้ Node cur ไม่มีการเชื่อมต่อกับ list อีก
        //
        //                        ___________________
        //                       | ________________  |
        //                       ↓|                ↓ |
        //              ... <-> node     node     node <-> ...
        //                        ↑        ↑        ↑
        //                     before     cur    after
        //

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
                        Node before = cur.previous;
                        Node after = cur.next;
                        before.next = after;
                        after.previous = before;
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

        // node2 คือ Node ที่จะมาแทรก

        // Concept : 
        // 1. ถ้า node1 == tail ใช้คำสั่ง pushBack() ได้เลย
        // 2. ถ้า node1 != tail ใช้วิธีการดังนี้
        // ให้ node1 และ node หลัง node1 (node3 = node1.next) ชี้ไปยัง node2
        // และ node2 ชี้กลับมายัง node เหล่านี้
        //
        //              ... <-> node <-> node <-> ...
        //                      ↑           ↑
        //                   node1         node3
        //                           node
        //                            ↑
        //                          node2 
        //
        // นั่นคือ node1.next ชี้มา node2
        //
        //              ... <-> node <-  node <-> ...
        //                      ↑  |        ↑
        //                   node1 |       node3
        //                         '→node
        //                            ↑
        //                          node2 
        //
        // node2.previous ชี้มา node1
        //
        //              ... <-> node <-  node <-> ...
        //                      ↑ ↑|        ↑
        //                  node1 ||       node3
        //                        |'→node
        //                        '--'↑
        //                          node2 
        //
        // node3.previous ชี้มา node2
        //
        //              ... <-> node      node <-> ...
        //                      ↑ ↑|      |  ↑
        //                  node1 ||      |  node3
        //                        |'→node←'
        //                        '--'↑
        //                          node2 
        //
        // node2.next ชี้มา node3
        //
        //              ... <-> node      node <-> ...
        //                      ↑ ↑|      |↑ ↑
        //                  node1 ||      || node3
        //                        |'→node←'|
        //                        '--'↑ '--'
        //                          node2 
        //

        if(node1 == tail){
            pushBack(node2);
        }
        else{
            Node node3 = node1.next;
            node1.next = node2;
            node2.previous = node1;
            node3.previous = node2;
            node2.next = node3;
        }
    }

    public void addNodeBefore(Node node1, Node node2) {

        // node2 คือ Node ที่จะมาแทรก

        // Concept : 
        // 1. ถ้า node1 == head ใช้คำสั่ง pushFront() ได้เลย
        // 2. ถ้า node1 != tail ใช้วิธีการจะคล้ายกับ addNodeAfter ดังนี้
        // ให้ node1 และ node ก่อน node1 (node3 = node1.previous) ชี้ไปยัง node2
        // และ node2 ชี้กลับมายัง node เหล่านี้
        //
        //              ... <-> node <-> node <-> ...
        //                      ↑           ↑
        //                   node3         node1
        //                           node
        //                            ↑
        //                          node2
        // 
        // นั่นคือ node3.next ชี้มา node2
        //
        //              ... <-> node <-  node <-> ...
        //                      ↑  |        ↑
        //                   node3 |       node1
        //                         '→node
        //                            ↑
        //                          node2 
        //
        // node2.previous ชี้มา node3
        //
        //              ... <-> node <-  node <-> ...
        //                      ↑ ↑|        ↑
        //                  node3 ||       node1
        //                        |'→node
        //                        '--'↑
        //                          node2 
        //
        // node1.previous ชี้มา node2
        //
        //              ... <-> node      node <-> ...
        //                      ↑ ↑|      |  ↑
        //                  node3 ||      |  node1
        //                        |'→node←'
        //                        '--'↑
        //                          node2 
        //
        // node2.next ชี้มา node1
        //
        //              ... <-> node      node <-> ...
        //                      ↑ ↑|      |↑ ↑
        //                  node3 ||      || node1
        //                        |'→node←'|
        //                        '--'↑ '--'
        //                          node2 
        //
        
        if(node1 == head){
            pushFront(node2);
        }
        else{
            Node node3 = node1.previous;
            node3.next = node2;
            node2.previous = node3;
            node1.previous = node2;
            node2.next = node1;
        }
    }

    public boolean isEmpty() {

        // Concept :
        // เช็คได้ด้วยการดูว่า head == null หรือเปล่า ถ้า null แสดงว่า list นี้ empty

        if (head == null)
            return true;
        else
            return false;
    }

    public void merge(DoublyLinkedList listAdd) {

        // Concept : 
        // นำ tail ของ list ตั้งต้น (this.tail) 
        // กับ head ของ list ที่จะนำมาต่อ (listAdd.head) มาเชื่อมกัน
        // และ เนื่องจากมี list ใหม่มาต่อ ทำให้ต้องทำการเปลี่ยน tail เป็น listAdd.tail
        //
        //     ... <-> node <-> node         node2 <-> node2 <-> ...
        //                        ↑           ↑
        //                    this.tail  listAdd.head
        //
        // นั่นคือ this.tail.next ชี้ไปยัง listAdd.head
        //
        //     ... <-> node <-> node    ->   node2 <-> node2 <-> ...
        //                        ↑           ↑
        //                    this.tail  listAdd.head
        //
        //  และ listAdd.head.previous ชี้ไปยัง this.tail
        //
        //     ... <-> node <-> node   <->   node2 <-> node2 <-> ...
        //                        ↑           ↑
        //                    this.tail  listAdd.head
        //
        //  ทำการเปลี่ยน this.tail
        //
        //     ... <-> node <-> node   <->   node2 <-> node2 <-> ... <-> node2
        //                                    ↑                            ↑
        //                                listAdd.head           this.tail,listAdd.tail
        //

        this.tail.next = listAdd.head;
        listAdd.head.previous = this.tail;
        this.tail = listAdd.tail;
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

    public Node whoGotHighestGPA() {

        // Concept : 
        // ไล่ดูแต่ละ Node ใน List เพื่อเก็บค่าสูงสุด (mx) และ Node ที่มีค่าสูงสุด (mxNode)

        if (isEmpty()) {
            return new Node("Empty List!");
        } else {
            Node cur = head;
            double mx = 0;
            Node mxNode = head;
            while (cur != null) {
                if (mx <= cur.gpa) {
                    mx = cur.gpa;
                    mxNode = cur;
                }
                cur = cur.next;
            }
            return mxNode;
        }
    }
}
