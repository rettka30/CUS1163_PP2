import java.util.Date;

public class ProducerRunnable implements Runnable {

   private static final int DELAY = 1; 
   private Queue queue;
   private Date date;
   private int count;

   public ProducerRunnable(Queue e, Date d, int r) {
      queue = e;
      date = d;
      count = r;
   }

   public void run() {
      try {
         for (int i = 1; i <= count; i++) {
               queue.add(date.toString());
               Thread.sleep(DELAY);
         }
      }
      catch (InterruptedException exception) {}
   }
}