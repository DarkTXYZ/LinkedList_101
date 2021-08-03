public class SinglyLinkedList {
    Node head;
    String listName;

    public SinglyLinkedList(String name) {
        this.head = null;
        this.listName = name;
    }

    public void popBack() {

        // Concept : 
        // 1. หาก list มี Node แค่ตัวเดียว (head.next == null) ก็เซ็ตให้ head ชี้ไปยัง null ได้เลย
        // 2. หาก list มี Node หลายตัว เราจะลบสายที่เชื่อมกันระหว่าง Node ก่อนสุดท้าย กับ Node สุดท้าย
        //
        //           node -> node -> node -> node -> node 
        //             ↑                                 
        //         head,cur                              
        //
        // วิธีการลบก็คือ ให้หา Node ก่อนสุดท้ายให้เจอ โดยจะวน loop ไปใน list 
        // ภายใต้เงื่อนไขที่ว่า หาก Node สองตัวถัดไปของ cur เป็น null แล้วให้หยุด loop
        //
        //           node -> node -> node -> node1 -> node2 
        //             ↑                       ↑        
        //           head                     cur      
        //                        
        // * cur == node1
        // ** cur.next == node1.next == node2
        // *** cur.next.next == node1.next.next == node2.next == null
        // จะพบว่าเมื่อ cur อยู่ Node ก่อนสุดท้าย loop จะหยุด
        //
        // เมื่อได้ Node ก่อนสุดท้ายแล้ว ก็ให้ Node นั้นชี้ลง null (cur.next = null)
        //
        //           node -> node -> node -> node1    node2 
        //             ↑                       ↑        
        //           head                     cur         
        //

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

        // Concept : 
        // 1. หาก list มี Node แค่ตัวเดียว (head.next == null) ให้ head ชี้ไปยัง null ได้เลย
        // 2. หาก list มี Node หลายตัว ต้องลบสายที่เชื่อมกันระหว่าง head 
        //    กับ Node ถัดไป (nextHead = head.next)
        //
        //           node -> node -> node -> node -> node 
        //             ↑       ↑                          
        //           head   nextHead                           
        //
        // วิธีการลบก็คือ ให้ head ชี้ลง null  
        //
        //           node    node -> node -> node -> node 
        //             ↑       ↑                          
        //           head   nextHead     
        //                        
        //  แล้วทำการเปลี่ยน head
        //
        //           node    node -> node -> node -> node 
        //                     ↑                          
        //               head,nextHead        
        //

        if (isEmpty()) {
            System.out.println("ERROR");
        } else {
            if (head.next == null)
                head = null;
            else{
                Node nextHead = head.next;
                head.next = null;
                head = nextHead;
            }
                
        }
    }

    public Node topFront() {

        // Concept :
        // ต้องการ Node หน้าสุด นั่นก็คือ head นั่นเอง

        if (isEmpty()) {
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else {
            return head;
        }
    }

    public Node topBack() {

        // Concept :
        // ไล่ list ไปทุกโหนด จนกระทั่งถึงโหนดสุดท้าย
        // วิธีการคือ สร้าง Node ขึ้นมาเพื่อใช้ในการวน loop (cur) โดยให้ Node นั้นเริ่มที่ head 
        //
        //           node -> node -> ... -> node -> node 
        //             ↑                                 
        //         head,cur                  
        //            
        // จากนั้น จะวน loop ไปใน list ภายใต้เงื่อนไขที่ว่า 
        // หาก Node ตัวถัดไปของ cur เป็น null แล้วให้หยุด loop
        //
        //           node -> node -> ... -> node -> node1 
        //             ↑                             ↑                      
        //           head                           cur
        //
        // * cur == node1
        // ** cur.next == node1.next == null
        // จะพบว่าเมื่อ cur อยู่ Node สุดท้าย loop จะหยุด

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

    public void pushFront(Node newNode) {

        // Concept : 
        // 1. กรณีที่ list ว่าง ให้ newNode เป็น head 
        // 2. กรณีที่ list ไม่ว่าง จะต้องเชื่อม newNode ก่อนหน้า head และทำการเปลี่ยน head ใหม่
        //
        //              node     node  -> node  -> ...
        //                ↑        ↑
        //             newNode   head
        //
        // นั่นคือ newNode.next ชี้ไปยัง head 
        //
        //              node  -> node  -> node  -> ...
        //                ↑        ↑
        //             newNode   head
        //
        // และทำการเปลี่ยน head
        //
        //              node  -> node  -> node  -> ...
        //               ↑
        //          newNode,head
        //

        if (isEmpty()) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    public void pushBack(Node newNode) {

        // Concept : 
        // 1. กรณีที่ list ว่าง ให้ newNode เป็น head
        // 2. กรณีที่ list ไม่ว่าง จะต้องเชื่อม newNode ตามหลัง Node หลังสุด (cur = topBack()) 
        //
        //              ...  -> node  -> node     node
        //                                ↑         ↑
        //                               cur     newNode
        //
        // นั่นคือ cur.next ชี้ไปยัง newNode 
        //
        //              ...  -> node  -> node ->  node
        //                                ↑         ↑
        //                               cur     newNode
        //

        if (isEmpty()) {
            head = newNode;
        } else {
            Node cur = topBack();
            cur.next = newNode;
        }
    }

    public Node findNode(int id) {

        // Concept : 
        // จะต้องวนทั่วทั้ง list เพื่อหา Node ที่ต้องการ
        // วิธีการคือ สร้าง Node ขึ้นมาเพื่อใช้ในการวน loop (cur) โดยให้ Node นั้นเริ่มที่ head 
        //
        //           node -> node -> ... -> node -> node 
        //             ↑                                  
        //         head,cur                              
        //
        // หาก Node นี้ไม่ใช่ Node ที่ต้องการ
        // ก็จะทำการวน cur ไปยัง node ถัดไป (cur = cur.next) 
        //
        //           node ->  node -> ... -> node -> node 
        //             ↑        ↑                          
        //           head      cur                       
        //
        // และทำอย่างนี้ไปเรื่อยๆ จนกว่าจะเจอ Node ที่ต้องการ
        // loop เหล่านี้จะหยุด ก็ต่อเมื่อ cur นั้นชี้ null
        //
        //              ... -> node -> node -> node
        //                                             ↑
        //                                            cur
        //

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

        // Concept : 
        // 1. หาก Node ที่ต้องการจะลบ คือ head (cur == head) ใช้คำสั่ง popFront()
        // 2. หาก Node ที่ต้องการจะลบ คือ Node สุดท้าย (cur.next == null) ใช้คำสั่ง popBack()
        // 3. หาก Node ที่ต้องการจะลบไม่ใช่ head และ tail ใช้วิธีการด้านล่าง
        // วิธีการคือ สร้าง Node ขึ้นมา 2 Node คือ prev และ cur เพื่อใช้ในการวน loop (cur) 
        // โดยให้ cur เริ่มที่ head ส่วน prev ชี้ไปยัง null ก่อน
        //
        //              node  -> node  -> node  -> node  -> node 
        //     ↑          ↑                                  
        //    prev    head,cur                              
        //
        // หาก cur ยังไม่ใช่ Node ที่ต้องการจะลบ ก็วน cur ไปยัง Node ถัดไป (cur = cur.next) 
        // แต่ก่อนที่จะวน จะให้ prev = cur เพื่อเก็บตัวก่อนหน้า
        //
        //              node  -> node  -> node  -> node  -> node 
        //               ↑        ↑                         
        //           head,prev   cur                       
        //
        //              node  -> node  -> node  -> node  -> node 
        //               ↑        ↑        ↑                
        //             head     prev      cur                       
        //
        // เมื่อเจอ Node ที่ต้องการจะลบแล้ว (node2 == cur) จะต้องทำการนำ Node ก่อนหน้า (node1 == prev) 
        // กับ Node ตามหลัง (node3 == cur.next) มาเชื่อมกัน
        //
        //              node  -> node1  -> node2  -> node3  -> node 
        //               ↑         ↑         ↑                
        //              head     prev       cur  
        //
        // นั่นคือ node1.next ชี้ไปยัง node3 (prev.next ชี้ไปยัง cur.next) 
        //
        //                          ___________________
        //                         |                   ↓
        //              node  -> node1     node2  -> node3  -> node 
        //               ↑         ↑         ↑                
        //              head     prev       cur 
        //
        // และ node2.next (cur.next) ชี้ null
        //
        //                          ___________________
        //                         |                   ↓
        //              node  -> node1     node2     node3  -> node 
        //               ↑         ↑         ↑                
        //              head     prev       cur 
        //

        if (isEmpty()) {
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else {
            Node cur = head;
            Node prev = null;
            while (cur != null) {
                if (cur.student_id == id) {
                    if (cur == head) {
                        popFront();
                    } 
                    else if(cur.next == null){
                        popBack();
                    }
                    else {
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

        // node2 คือ Node ที่จะมาแทรก

        // Concept : 
        // 1. ถ้า node1 เป็น Node สุดท้าย ก็สามารถใช้คำสั่ง pushBack() ได้เลย
        // 2. ถ้าไม่ ต้องให้ node2 ชี้ไปยัง Node หลัง node1 ก่อน (node3 = node1.next) 
        // แล้วค่อยให้ node1 ชี้ไปยัง node2 
        //
        //              ...  -> node  -> node  -> ...
        //                      ↑           ↑
        //                   node1         node3
        //                           node
        //                            ↑
        //                          node2 
        //
        // นั่นคือ node2.next ชี้ node3 (node1.next)
        //
        //              ...  -> node  ->  node  -> ...
        //                      ↑         ↑  ↑
        //                   node1        |  node3
        //                           node-'
        //                            ↑
        //                          node2 
        //
        // และ node1.next ชี้ node2
        //
        //              ...  -> node     node  -> ...
        //                      ↑  |      ↑ ↑
        //                  node1  |      | node3
        //                         '→node-'
        //                            ↑
        //                          node2 
        //

        if(node1 == topBack()){
            pushBack(node2);
        }
        else{
            node2.next = node1.next;
            node1.next = node2;
        }
    }

    public void addNodeBefore(Node node1, Node node2) {

        // node2 คือ Node ที่จะมาแทรก

        // Concept : 
        // 1. ถ้า node1 == head ใช้คำสั่ง pushFront() ได้เลย
        // 2. ถ้าไม่ ต้องให้ node2 ชี้ไปยัง node1 ก่อน
        // แล้วค่อยให้ Node ก่อน node1 (หาโดยใช้หลักการ cur , prev) ชี้ไปยัง node2 
        //
        //              ...  -> node  -> node  -> ...
        //                      ↑           ↑
        //                   node3         node1
        //                           node
        //                            ↑
        //                          node2
        // 
        // นั่นคือ node2.next ชี้มา node1
        //
        //              ...  -> node  ->  node  -> ...
        //                      ↑         ↑  ↑
        //                   node3        |  node1
        //                           node-'
        //                            ↑
        //                          node2 
        //
        // node3.next ชี้มา node2
        //
        //              ...  -> node     node  -> ...
        //                      ↑  |      ↑ ↑
        //                  node3  |      | node1
        //                         '→node-'
        //                            ↑
        //                          node2 
        //
       
        if(node1 == head){
            pushFront(node2);
        }
        else{
            Node cur = head;
            Node prev = null;
            while (cur.student_id != node1.student_id) {
                prev = cur;
                cur = cur.next;
            }
            Node node3 = prev;
            node2.next = node1;
            node3.next = node2;
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

    public void merge(SinglyLinkedList listAdd) {

        // Concept : 
        // นำ Node สุดท้าย ของ list ตั้งต้น (this.topBack()) 
        // ชี้ไปยัง head ของ list ที่จะนำมาต่อ (listAdd.head)
        //
        //     ...  -> node  -> node         node2  -> node2  -> ...
        //                        ↑           ↑
        //                 this.topBack()  listAdd.head
        //
        // นั่นคือ this.topBack().next ชี้ไปยัง listAdd.head
        //
        //     ...  -> node  -> node    ->   node2  -> node2  -> ...
        //                        ↑           ↑
        //                 this.topBack()  listAdd.head
        //

        this.topBack().next = listAdd.head;
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

        // Concept : 
        // ไล่ดูแต่ละ Node ใน List เพื่อเก็บค่าสูงสุด (mx) และ Node ที่มีค่าสูงสุด (mxNode)

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
