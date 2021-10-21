package threads;

import java.sql.SQLOutput;

public class ThreadsSample {
    public static void main(String[] args) {
        System.out.println("Thread main starts ");
        new Thread(new MyCustomThread()).start();
        System.out.println("Thread main finished ");
    }
    public static class MyCustomThread implements Runnable {
        public void run() {
            System.out.printf("I will work in thread %s 1 second\n", Thread.currentThread().getName());

            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println("Thread has been interrupted");
            }

            System.out.printf("Thread %s finished execution\n", Thread.currentThread().getName());
        }
    }
}
