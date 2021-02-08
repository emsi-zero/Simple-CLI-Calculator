package com.company;

import java.util.Stack;

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

        public void insert(Node newNode){
            if (cursor.prev == null){
                head = newNode;
            }else {
                cursor.prev.next = newNode;
                newNode.prev = cursor.prev;
            }
            newNode.next = cursor;
            cursor.prev = newNode;
        }

        public void delete(){
            Node p;
            if (cursor.prev!= null){
                if (head == cursor.prev) {
                    head = cursor;
                }
                cursor.prev = cursor.prev.prev;
                if (cursor.prev != null){
                    cursor.prev.next = cursor;
                }
            }
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




        private int getPrecedence(char c){
            if (c == '+' || c == '-'){
                return 1;
            }else if (c == '*' || c == '/'){
                return 2;
            }else {
                return 0;
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
        inputString.insert(new Node('A'));
        inputString.insert(new Node('B'));
        inputString.insert(new Node('C'));
        inputString.insert(new Node('D'));
        System.out.println(inputString.returnStr());
        inputString.delete();
        System.out.println(inputString.returnStr());
        inputString.moveCursor(false);
        System.out.println(inputString.returnStr());
        inputString.delete();
        System.out.println(inputString.returnStr());
        inputString.insert(new Node('H'));
        System.out.println(inputString.returnStr());
        inputString.moveCursor(true);
        System.out.println(inputString.returnStr());

    }
}
