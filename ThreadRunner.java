import java.util.Date;
import java.util.Scanner;

public class ThreadRunner {
   
   public static void main(String[] args) {
      Date date = new Date();
      Queue queue = new Queue();
      Scanner in = new Scanner(System.in);
      System.out.println("Number of producers: ");
      int t1 = in.nextInt();
      System.out.println("Number of consumers: ");
      int t2 = in.nextInt();
      
      final int rep = 100;
      //final int threads = 100;
 
      //for (int i = 1; i <= threads; i++) {
      for(int i = 1, j = 1; i <= t1 && j <= t2; i++, j++) {
         if(i <= t1) {
            ProducerRunnable p = new ProducerRunnable(queue, date, rep);
            Thread pt = new Thread(p);
            pt.start();
         }
         if(j <= t2) {
            ConsumerRunnable c = new ConsumerRunnable(queue, rep);
            Thread ct = new Thread(c);
            ct.start();
         }
      }
   }
}