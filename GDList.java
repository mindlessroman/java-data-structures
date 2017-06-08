/*
#3

@author: Michael Main [original]
@author: Hannah Kennedy @mindlessroman
@due_date: Spr 2017
 */

import java.util.Scanner;
import java.io.*;

/**
 * GDList is a generic doubly linked list.
 * All elements are distinct
 **/
public class GDList<E> implements Cloneable {
    /**
     * Nested class
     * GNode is a generic class representing a node in a list
     * E is generic type parameter of data
     * Has both previous and next pointers
     *
     * part of original implementation
     **/
    private static class GNode<E> {
        private E data;
        private GNode<E> previous;
        private GNode<E> next;

        // constructor
        public GNode (E e) {
            data = e;
            previous = null;
            next = null;
        }

        public E getData () { return data; }
        public GNode getPrevious () { return previous; }
        public GNode getNext () { return next; }
        public void setData (E e) { data = e; }
        public void setPrevious (GNode p) { previous = p; }
        public void setNext (GNode p) { next = p; }
    }


    private GNode<E> head;
    private GNode<E> tail;
    private int size;       // number of nodes in a list

    /**
     * no-arg constructor creates an empty list
     *
     * part of original implementation
     **/

    public GDList () {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * add a new node with data e to the head
     * if a node with e already exists, return 1
     * if not, create and add a new node with e to the head (this new node is the head now), and return 0
     * increment the size
     *
     * part of original implementation
     **/
    public int addToHead (E e) {
        GNode temp = new GNode(e);
        if (head == null) {
            head = temp;
            tail = temp;
        } else {
            if (findNode(head, e) == null) {
                temp.setNext(head);
                head.setPrevious(temp);
                head = temp;
            } else return 1;
        }
        size++;
        return 0;
    }

    /**
     * add a new node with data e to the tail
     * if a node with e already exists, return 1
     * if not, creatre and add a new node with e to the tail (this new node is the tail now), and return 0
     * increment the size
     *
     * part of original implementation
     **/
    public int addToTail (E e) {

        GNode temp = new GNode(e);
        if (head == null) {
            head = temp;
            tail = temp;
        } else {
            if (findNode(head, e) == null) {
                temp.setPrevious(tail);
                tail.setNext(temp);
                tail = temp;
            } else return 1;
        }
        size++;
        return 0;
    }

    /**
     * insert a new node with data e after node n
     * n is not null
     * if a node with e already exists, return 1
     * if not, create and add a new node with e after node n, and return 0
     * increment the size
     *
     * part of original implementation
     **/
    public int addAfter (GNode n, E e) {

        if (n == null)
            throw new IllegalArgumentException
                    ("The node n cannot be null");

        if (findNode(head, e) != null) return 1;
        if (n == tail) {
            addToTail(e);
        } else {
            GNode temp = new GNode(e);
            GNode tempNext = n.getNext();
            temp.setNext(tempNext);
            tempNext.setPrevious(temp);
            temp.setPrevious(n);
            n.setNext(temp);
            size++;
        }
        return 0;
    }

    /**
     * insert a new node with data e before node n
     * n is not null
     * if a node with e already exists, return 1
     * if not, create and add a new node with e before node n, and return 0
     * increment the size
     *
     * part of original implementation
     **/
    public int addBefore (GNode n, E e) {

        if (n == null)
            throw new IllegalArgumentException
                    ("The node n cannot be null");

        if (findNode(head, e) != null) return 1;
        if (n == head) addToHead(e);
        else {
            GNode temp = new GNode(e);
            GNode tempPrevious = n.getPrevious();
            temp.setNext(n);
            n.setPrevious(temp);
            tempPrevious.setNext(temp);
            temp.setPrevious(tempPrevious);
            size++;
        }
        return 0;
    }

    /**
     * delete the node with data e
     * if a node with e does not exist, return null
     * if exists, delete the node and return the pointer to the deleted node
     * decrement the size
     *
     * IMPLEMENTED FOR ASSIGNMENT
     **/
    public GNode deleteNode (E e) {
        // implement this method

        GNode nodeToFind = findNode(head, e);
        if (nodeToFind != null) {
            GNode beforeNode = nodeToFind.getPrevious();
            GNode afterNode = nodeToFind.getNext();
            beforeNode.setNext(afterNode);
            afterNode.setPrevious(beforeNode);
            size--;
            return nodeToFind;
        } else {
            return null;
        }
    }

    /**
     * delete the node which is located after the node with data e
     * if the node with e is tail, return null
     * if a node with e does not exist, return null
     * if a noide with e exists, delete the node after that node and return the pointer to the deleted node
     * decrement the size
     *
     * part of original implementation
     **/
    public GNode deleteAfter (E e) {
        GNode temp = findNode(head, e);
        if (temp == tail || temp == null) return null;
        return (deleteNode((E) temp.getNext().data));
    }

    /**
     * delete the node which is located before the node with data e
     * if the node with e is head, return null
     * if a node with e does not exist, return null
     * if a node with e exists, delete the node before that node and return the pointer to the deleted node
     * decrement the size
     *
     * part of original implementation
     **/
    public GNode deleteBefore (E e) {
        GNode temp = findNode(head, e);
        if (temp == head || temp == null) return null;
        return (deleteNode((E) temp.getPrevious().data));
    }

    /**
     * find a node with element e
     * start the search beginning at node p
     * if node with e does not exist, return null
     * if node with e exists, return the pointer to the node
     *
     * part of original implementation
     **/
    public GNode findNode (GNode p, E e) {
        GNode current = p;
        while (current != null && current.data != e)
            current = current.getNext();
        return current;
    }

    /**
     * exchange two nodes n1 and n2
     * n1 is not null
     * n2 is not null
     * exchange node n1 and node n2 (do not just exchange the data).
     *
     * IMPLENTED FOR ASSIGNMENT
     **/
    public void exchange (GNode n1, GNode n2) {
        System.out.println("swapping " + n1 + "and " + n2);

        if (n1 != null && n2 != null && !n1.equals(n2)) {
            System.out.println("neither of the items are null, nor are they the same element");
            GNode n1_before = n1.getPrevious(); //null
            GNode n1_next = n1.getNext(); // franklin
            GNode n2_before = n2.getPrevious(); //lewis
            GNode n2_next = n2.getNext(); //null

            //First link direction - going outwards from the nodes
            //swap node one's before
            n1.setPrevious(n2_before);
            //swap node one's after
            n1.setNext(n2_next);
            //swap node two's before
            n2.setPrevious(n1_before);
            //swap node two's after
            n2.setNext(n2_next);

            //Second Link Direction - going inwards to the nodes
            //if node one's before is null, then you can't call it; n2 will become head! otherwise no change
            //head = (n1_before == null) ? n2 : head ;
            if (n1_before == null) {
                head = n2;
                n1_next.setPrevious(n2);
            }
            //if node one's next is null, then you can't call it; n2 will become tail! otherwise no change
            //tail = (n1_next == null) ? n2 : tail ;
            if (n1_next == null) {
                tail = n2;
                //n1_before.setNext(n2);

            }
            //if node two's before is null, then you can't call it; n1 will become head otherwise no change
            //head = (n2_before == null) ? n1 : head;
            if (n2_before == null) {
                head = n1;
                n2_next.setPrevious(n1);
            }
            //if node two's next is null, then you can't call it; n1 will become tail! otherwise no change
            //tail = (n2_next == null) ? n1 : tail ;
            if (n2_next == null) {
                tail = n1;
                //(n1_before == null) ? : ;
                //n1_before.setNext(n1);

            }

        }

    }

    /**
     * add a new node with e at the specified position
     * pos specifies where new node is added
     * pos of the first element in a list is 0
     * pos >= 0
     * if a node with e already exists, return 1
     * if not, create and add a new node with e at position pos and return 0
     * increment the size
     *
     * IMPLENTED FOR ASSIGNMENT
     */
    public int addPos (E e, int pos) {
        // implement this method
        //create a cursor
        GNode cursorAdd = head;
        GNode add_node = new GNode(e);

        for (int i = 0; (i < pos && cursorAdd != null); i++) {
            cursorAdd = cursorAdd.getNext();
        }
        if (pos >= 0 && pos < size) {
            //System.out.println(cursorAdd.getData());
            GNode findN = findNode(head, e);
            if (findN == null) {
                GNode tempBefore = cursorAdd.getPrevious();
                GNode tempAfter = cursorAdd.getNext();
                //insert's before needs to be come tempbefore
                add_node.setPrevious(tempBefore);
                //insert's after needs to become the cursor
                add_node.setNext(cursorAdd);
                //tempbefore's after needs to become insert
                tempBefore.setNext(add_node);
                //tempafter's before needs to become insert
                cursorAdd.setPrevious(add_node);

                return 0;
            }
        }
        return 1;
    }

    /**
     * replace the node at the specified position with a new node with e
     * pos specifies the position of the node to be replaced
     * pos of the first element in a list is 0
     * pos >= 0
     * if a node with e already exists, return null
     * if not, create a new node with e and let it replace the node at position pos
     * return the pointer to the replaced node
     *
     * IMPLENTED FOR ASSIGNMENT
     */
    public GNode replacePos (E e, int pos) {
        // implement this method
        GNode cursorRep = head;
        GNode rep_node = new GNode(e);

        for (int i = 0; (i < pos && cursorRep != null); i++) {
            cursorRep = cursorRep.getNext();
        }
        if (pos >= 0 && pos < size) {
            GNode findN = findNode(head, e);
            if (findN == null) {
                GNode tempBefore = cursorRep.getPrevious();
                GNode tempAfter = cursorRep.getNext();
                //replacement's before needs to be the original's before
                rep_node.setPrevious(tempBefore);
                //original before's next needs to be the replacement
                tempBefore.setNext(rep_node);
                //replacement's next needs to be original's next
                rep_node.setNext(tempAfter);
                //original after's before needs to be replacement
                tempAfter.setPrevious(rep_node);
                return cursorRep;
            }
        }
        return null;

    }

    public void printList () {
        System.out.print("Number of nodes = " + size + ", List is: ");
        if (head != null) {
            GNode current = head;
            while (current != null) {
                System.out.print(current.data + " ");
                current = current.getNext();
            }
        } else {
            System.out.println("The list is empty");
        }
        System.out.println();
    }

    /**
    * main tests the various methods
    *
    *
    **/
    public static void main (String[] args) throws Exception {

        GDList<String> names = new GDList<String>();
        names.printList();
        names.addToHead("Decker");
        names.addToHead("Barbour");
        names.addToHead("Franklin");
        names.printList();
        names.addToTail("Smith");
        names.addToTail("Whatley");
        names.addToTail("Lewis");
        System.out.println();
        names.printList();

        GNode temp;
        // find and print Decker, search from head
        System.out.println("\nFind and print Decker. Search from head.");
        temp = names.findNode(names.head, "Decker");
        System.out.println(temp != null ? temp.data : "The node does not exist.");

        // find Whatley first and add Morse after Whatley
        System.out.println("\nFind Whatley first and add Morse after Whatley");
        temp = names.findNode(names.head, "Whatley");
        if (temp == null) {
            System.out.println("Such node does not exist in the list");
        } else {
            names.addAfter(temp, "Morse");
        }
        System.out.println();
        names.printList();

        //test deleting a node
        System.out.println("\nRemove Smith");
        temp = names.findNode(names.head, "Smith");
        names.deleteNode((String) temp.getData());
        names.printList();

        //test deleting something that doesn't exist
        System.out.println("\n Remove Gertrude -- nothing should happen");
        String deleteNotExists = "Gertrude";
        temp = names.findNode(names.head, deleteNotExists);
        names.deleteNode(deleteNotExists);
        names.printList();

        //test deleting a node after specified
        System.out.println("\nRemove node after Barbour (Should be Decker)");
        temp = names.findNode(names.head, "Barbour");
        names.deleteAfter((String) temp.getData());
        names.printList();


        names.addToTail("Bradislava");
        names.addToHead("Queenie");

        names.printList();
        //swap two nodes - where edge
        System.out.println("\nSwap two nodes: Franklin and Lewis");
        GNode swap1 = names.findNode(names.head, "Franklin");
        GNode swap2 = names.findNode(names.head, "Moore");
        names.exchange(swap1, swap2);
        names.printList();

        //Adding at posiition
        String addExists = "Queenie";
        String notExists = "Rhubarb";
        System.out.println("Adding queenie at pos 3 (which already exists) -- nothing should happen");
        names.addPos(addExists, 3);
        names.printList();//nothing should happen
        System.out.println("Adding rhubarb at pos 2 (which doesn't exist) - Rhubarb will be added as 3rd elem");
        names.addPos(notExists, 3);
        names.printList(); // Rhubarb should be 4h elem

        //replace at position
        String repExists = "Barbour";
        String repNotExists = "Koopa";
        System.out.println("Replacing the 4th elem with Barbour - nothing should happen");
        names.replacePos(repExists, 3);
        names.printList(); //nothing should happen
        System.out.println("Replacing the 4th elem (rhubarb) with Koopa -  success");
        names.replacePos(repNotExists, 3);
        names.printList(); // Koopa should become 4th elem
    }
}
