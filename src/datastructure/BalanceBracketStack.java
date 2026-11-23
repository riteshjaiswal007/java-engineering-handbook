package datastructure;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class BalanceBracketStack {

    public static void main(String[] args){
        String s="{{()}}";

        Deque<Character> deque=new ArrayDeque<>();
        Stack<Character> stack=new Stack<>();
        stack.push('c');
        stack.pop();

        if(s.length()<2) {

            System.out.println("false");
        }
        for(char c:s.toCharArray()){
            char ch=' ';
          if(c=='{' || c== '['||c=='('){
              deque.push(c);
          }

        else if(c=='}' || c== ']'||c==')'){
            ch=deque.pop();

        if(ch!=c){
            System.out.println("Not Balanced");
        }
        }
    }
    if(deque.isEmpty()){
        System.out.println(" Balanced");
    }
    }
}
