public class lesson3DZ {

    private static class Node{
        Node next;
        Node previus;
        int value;

    }
    Node head;
    Node tail;
    public void revers(){
        Node correntNode = head;
        while (correntNode != null){
            Node next = correntNode.next;
            Node previus = correntNode.previus;
            correntNode.next = previus;
            correntNode.previus = next;
            if (previus == null) tail = correntNode;
            if (next == null) head = correntNode;
            correntNode = correntNode.next;
        }
    }
}
