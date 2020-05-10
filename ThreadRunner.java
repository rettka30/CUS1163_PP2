import java.util.Date;

public class ThreadRunner {
   
   public static void main(String[] args) {
      Date date = new Date();
      Queue queue = new Queue();
      
      final int rep = 100;
      final int threads = 100;
 
      for (int i = 1; i <= threads; i++) {
         ProducerRunnable p = new ProducerRunnable(queue, date, rep);
         ConsumerRunnable c = new ConsumerRunnable(queue, rep);

         Thread pt = new Thread(p);
         Thread ct = new Thread(c);
    
         pt.start();
         ct.start();
      }
   }
}