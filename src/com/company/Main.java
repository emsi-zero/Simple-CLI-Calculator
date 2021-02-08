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

        public void moveCursor(boolean right) {

            if (right){
                if (cursor.next != null) {
                    if (cursor.prev == null){
                        head = cursor.next;
                    }else {
                        cursor.prev.next = cursor.next;
                    }
                    cursor.next.prev = cursor.prev;
                    cursor.prev = cursor.next;
                    cursor.next = cursor.next.next;
                    cursor.prev.next = cursor;
                    if (cursor.next != null) {
                        cursor.next.prev = cursor;
                    }else {
                        tail = cursor;
                    }
                }
            }else {
                if (cursor.prev != null){
                    if (cursor.next == null){
                        tail = cursor.prev;
                    }else{
                        cursor.next.prev = cursor.prev;
                    }
                    cursor.prev.next = cursor.next;
                    cursor.next = cursor.prev;
                    cursor.prev = cursor.prev.prev;
                    cursor.next.prev = cursor;
                    if (cursor.prev != null){
                        cursor.prev.next = cursor;
                    }else {
                        head = cursor;
                    }
                }
            }

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
