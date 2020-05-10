import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
  
public class Queue {
   
   class Node {
      private String data;
      private Node next;
      private Node(String data) {
         this.data = data;
      }
   }
   
   private Node head;
   private Node tail;
   private int size;
   private Lock sizeLock;
   private Condition sufficientSizeCondition;
   
   public Queue() {
        size = 0;
        sizeLock = new ReentrantLock();
        sufficientSizeCondition = sizeLock.newCondition();     
   }
   
   public boolean isEmpty() {
      return head == null;
   }
   
   public String peek() {
      return head.data;
   }
   
   public void add(String data) throws InterruptedException {
      sizeLock.lock();
      try {
         while (size > 10) {
            sufficientSizeCondition.await();
         }
         Node node = new Node(data);
         if(tail != null) {
            tail.next = node;
         }
         tail = node;
         if(head == null) {
            head = node;
         }
         size++;
         System.out.print("Adding to Queue: " + data);
         System.out.println(", and size is " + size);
         sufficientSizeCondition.signalAll();
      } finally {
         sizeLock.unlock();
      }
   }
   
   public String remove() throws InterruptedException {
      sizeLock.lock();
      try {
         while (size == 0) {
            sufficientSizeCondition.await();
         }
         String data = head.data;
         head = head.next;
         if(head == null) {
            tail = null;
         }
         size--;
         System.out.print("Removing from Queue: " + data);
         System.out.println(", and size is " + size);
         sufficientSizeCondition.signalAll();
         return data;
      } finally {
         sizeLock.unlock();
      }
   }
   
   public int size() {
      return size;
   }
   
}