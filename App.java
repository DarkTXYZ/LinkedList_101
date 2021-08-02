public class App {
    public static void main(String[] args) {
        Node node;
        DoublyLinkedList list = new DoublyLinkedList("list5");
        node = list.whoGotHighestGPA();
        node.printIDName();
        list.pushBack(new Node(5906001, "Matthew", 3.25));
        list.pushBack(new Node(5906002, "Mark", 2.75));
        list.pushBack(new Node(5906003, "Luke", 3.00));
        list.printStructure();
        node = list.whoGotHighestGPA();
        node.printIDName();
        Node newNode = new Node(5906004, "John", 3.30);
        list.addNodeBefore(node, newNode);
        list.printStructure();
        list.addNodeAfter(list.whoGotHighestGPA(), new Node(5906005, "James", 3.40));
        list.printStructure();
        (list.whoGotHighestGPA()).printIDName();
    }

}
