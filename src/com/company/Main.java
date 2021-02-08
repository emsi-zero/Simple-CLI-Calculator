package com.company;

public class Main {

    public class InputString{
        Node head;
        Node cursor;
        Node tail;

        public InputString() {
            cursor = new Node('|');
            head = cursor;
            tail = cursor;
        }

        public InputString(String defaultString){
            cursor = new Node('|');
            head = cursor;
            tail = cursor;
        }

        public void Insert(Node newNode){
            if (cursor.prev == null){
                head = newNode;
            }else {
                cursor.prev.next = newNode;
                newNode.prev = cursor.prev;
            }
            newNode.next = cursor;
            cursor.prev = newNode;
        }
    }

    public class Node{
        char data;
        Node next;
        Node prev;

        public Node(char data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {

    }
}
