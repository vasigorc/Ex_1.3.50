package ca.vgorcinschi.ex1_3_50;

import java.util.Iterator;
import java.util.concurrent.CompletableFuture;

import static java.lang.Integer.valueOf;

/**
 * Created by vgorcinschi on 28/09/16.
 */
public class AppRunner {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack();
        for (int i = 0; i < 10; i++) {
            stack.push(valueOf(i+1));
        }
        CompletableFuture<Void> future = CompletableFuture.runAsync(()->{
            for (int i = 10; i < 20; i++) {
                stack.push(valueOf(i+1));
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    System.out.println(e.getCause());
                }
            }
        });

        Iterator<Integer> iterator = stack.iterator();
        while (iterator.hasNext()){
            System.out.println("The next item on the stack is: "+iterator.next());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
