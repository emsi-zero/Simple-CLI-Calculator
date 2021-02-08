package com.company;

public class Main {

    public static class InputString{
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

        public String returnStr(){
            String str = "";
            Node p;
            p = head;
            while (p != null){
                str += p.data;
                p=p.next;
            }
            return str;
        }
    }

    public static class Node{
        char data;
        Node next;
        Node prev;

        public Node(char data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {

        InputString inputString = new InputString();


    }
}
