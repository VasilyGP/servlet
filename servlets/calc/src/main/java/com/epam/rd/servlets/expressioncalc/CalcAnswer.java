package com.epam.rd.servlets.expressioncalc;

import java.util.*;

public class CalcAnswer {
    Queue<String> queue = new LinkedList();
    Deque<SingPriority> deque = new LinkedList();

    CalcAnswer(String string, Map<String, String> params) {
        for (Character c : string.toCharArray()) {
            if(c == ' ') continue;
            if (Character.isAlphabetic(c)) {
                queue.add(params.get(c.toString()));
            } else {
                switch (c) {
                    case '(':
                        deque.add(new SingPriority(c));
                        break;
                    case ')':
                        for (SingPriority lastSing = deque.pollLast(); lastSing.c != '('; lastSing = deque.pollLast()) {
                            queue.add(lastSing.c.toString());
                        }
                        break;
                    default:
                        SingPriority sing = new SingPriority(c);
                        SingPriority lastSing = deque.pollLast();
                        if (lastSing != null) {
                            if (sing.compareTo(lastSing) >= 0) {//priority last!!
                                queue.add(lastSing.c.toString());
                                deque.add(sing);
                            } else {
                                deque.add(lastSing);
                                deque.add(sing);
                            }
                        } else {
                            deque.add(sing);
                        }
                }
            }

        }
        SingPriority sing = deque.pollLast();
        while (sing != null) {
            queue.add(sing.c.toString());
            sing = deque.pollLast();
        }
    }

    public String getAnswer() {
        Deque<Integer> intDeque = new LinkedList();
        for (String str : queue) {
            switch (str) {
                case "+": {
                    int a = intDeque.pollLast();
                    int b = intDeque.pollLast();
                    intDeque.add(a + b);
                    break;
                }
                case "-": {
                    int a = intDeque.pollLast();
                    int b = intDeque.pollLast();
                    intDeque.add(b - a);
                    break;
                }
                case "/": {
                    int a = intDeque.pollLast();
                    int b = intDeque.pollLast();
                    intDeque.add(b / a);
                    break;
                }
                case "*": {
                    int a = intDeque.pollLast();
                    int b = intDeque.pollLast();
                    intDeque.add(a * b);
                    break;
                }
                default:
                    intDeque.add(Integer.parseInt(str));
                    break;
            }
        }
        return intDeque.pollLast().toString();
    }

    private static class SingPriority implements Comparable<SingPriority> {
        private final Character c;
        private final Priority priority;

        SingPriority(Character c) {
            this.c = c;
            if (c == '(' || c == ')') {
                priority = Priority.LOW;
            } else if (c == '*' || c == '/') {
                priority = Priority.HIGH;
            } else if (c == '+' || c == '-') {
                priority = Priority.MEDIUM;
            } else {
                throw new IllegalArgumentException("This math sing is not allowed");
            }
        }

        @Override
        public int compareTo(SingPriority o) {
            return priority.compareTo(o.priority);
        }
    }
}