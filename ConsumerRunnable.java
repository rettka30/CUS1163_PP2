import java.util.Date;

public class ConsumerRunnable implements Runnable {
   
   private static final int DELAY = 1;
   private Queue queue;
   private int count;

   public ConsumerRunnable(Queue e, int r) {
      queue = e;
      count = r;
   }

  public void run() {
      try {
         for (int i = 1; i <= count; i++) {
               System.out.println(queue.remove());
               Thread.sleep(DELAY);
         }
      }
      catch (InterruptedException exception) {}
   }
}