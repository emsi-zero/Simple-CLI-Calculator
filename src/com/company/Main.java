package com.company;

import java.util.Scanner;
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
            for (char c: defaultString.toCharArray()
                 ) {
                insert(new Node(c));
            }
            while (cursor.prev != null){
                moveCursor(false);
            }
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

        public String returnPostFixStr(){
            String str = "";
            Stack<Character> stack = new Stack<Character>();
            stack.push('#');
            for (char c: returnStr().toCharArray()
            ) {
                if (c == '|'){
                    continue;
                }else if (c >= 48 && c<= 57){
                    str += c;
                }else if (c == '('){
                    stack.push(c);
                }else if (c == ')'){
                    while (stack.peek()!='#' && stack.peek() != '('){
                        str += stack.pop();
                    }
                    if (stack.peek() == '('){
                        stack.pop();
                    }
                }else {

                    while (stack.peek()!='#' && getPrecedence(c) <= getPrecedence(stack.peek())){
//                        if (stack.peek() >= 48 && stack.peek()<= 57){
//                            str += stack.pop();
//                        }else {
//                            str +=" " + stack.pop();
//                        }
                        str += stack.pop();
                    }
                    str += ' ';
                    stack.push(c);
                }
            }
            while (stack.peek() != '#'){
                str += ' ';
                str += stack.pop();
            }
            return str;
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

    public static int calculate(String exp){
        int a;
        int b;
        Stack<Integer> stack = new Stack<>();
        int temp = 0;
        for (char c: exp.toCharArray()
             ) {

            if (c  >= 48 && c <= 57){
                temp *=10;
                temp += ((int)c) - 48;
                continue;
            }else if (c == ' ' ){
                int operand = temp;
                stack.push(operand);
                temp = 0;
            }else {
                a = stack.pop();
                b = stack.pop();
                switch (c){
                    case '+':
                        stack.push(a + b);
                        break;
                    case '-':
                        stack.push(a - b);
                        break;
                    case '*':
                        stack.push(a * b);
                        break;
                    case '/':
                        stack.push(a / b);
                        break;
                }
            }
        }

        return stack.peek();
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int num = Integer.parseInt(scanner.nextLine());
        String defaultStr;
        defaultStr = scanner.nextLine();
        InputString inputString = new InputString(defaultStr);
        for (int i = 0; i < num; i++) {
            String [] strings = scanner.nextLine().split(" ");
            if (strings[0].equals("<")){
                inputString.moveCursor(false);
            }else if (strings[0].equals(">")){
                inputString.moveCursor(true);
            }else if (strings[0].equals("-")){
                inputString.delete();
            }else if (strings[0].equals("+")){
                inputString.insert(new Node(strings[1].toCharArray()[0]));
            }else if (strings[0].equals("?")){
                System.out.println(inputString.returnStr());
            }else if (strings[0].equals("!")){
                System.out.println(calculate(inputString.returnPostFixStr()));
            }
        }



    }
}
