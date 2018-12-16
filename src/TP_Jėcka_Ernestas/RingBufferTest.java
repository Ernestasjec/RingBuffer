package TP_Jėcka_Ernestas;

import java.io.PrintStream;
import Buffer.RingBufferSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class RingBufferTest {
        private static PrintStream so = System.out;
        static int[] tiriamiKiekiai = {10_000, 20_000, 40_000, 80_000};

    public static void demonstracijaSuRingBuffer() {
        
      int N = 10;
      RingBufferSet<Integer> rBuffer = new RingBufferSet<Integer>(N);
        so.println("Ar tuščias? "+(rBuffer.isEmpty()?"tuščias":"turi elementų"));
        so.println("Ar pilnas? "+(rBuffer.isFull()?"pilnas":"nepilnas"));
        //so.println("Bandome šalinti pirmą elementą, kai sąrašas tuščias");
        //rBuffer.dequeue();
        so.println("Papildoma elementais");
          for (int i = 1; i <= N; i++) {
            rBuffer.enqueue(i);
        }
        so.println("Ar tuščias? "+(rBuffer.isEmpty()?"tuščias":"turi elementų"));
        so.println("Ar pilnas? "+(rBuffer.isFull()?"pilnas":"nepilnas"));
//        so.println("Bandome pridėti elementą, kai sąrašas pilnas");
//        rBuffer.enqueue(20);
        rBuffer.println();
        so.println("Šaliname pirmą = " + rBuffer.dequeue());
        so.println("Ar pilnas? "+(rBuffer.isFull()?"pilnas":"nepilnas"));
        rBuffer.println();
        rBuffer.enqueue(20);
        rBuffer.println();
        so.println("Pridedame į paskutinę vietą= " + 20);
        for (int i = 0; i < N-1; i++) {
            so.println("*************************");
            so.println("Šaliname pirmą = " + rBuffer.dequeue());
            rBuffer.enqueue(21 + i);
            so.println("Pridedame į paskutinę vietą= " + (21 + i));
            rBuffer.println();
        }
    }
    
    static void paprastasTyrimas(int elementųKiekis){
        RingBufferSet<Integer> rBuffer = new RingBufferSet<Integer>(elementųKiekis);
        Queue<Integer> Qbuffer = new LinkedList<Integer>();
        System.gc(); System.gc(); System.gc();
        long t0=System.nanoTime();
        for (int i = 0; i < elementųKiekis; i++) {
            rBuffer.enqueue(i);
        }
        long t1=System.nanoTime();
        for (int i = 0; i < elementųKiekis; i++) {
            rBuffer.dequeue();
        }
        long t2=System.nanoTime();
        for (int i = 0; i < elementųKiekis; i++) {
            Qbuffer.add(i);
        }
        long t3=System.nanoTime();
        for (int i = 0; i < elementųKiekis; i++) {
            Qbuffer.remove();
        }
        long t4=System.nanoTime();
        so.println(String.format("%7d %7.4f %7.4f %7.4f %7.4f \n", elementųKiekis,
                (t1-t0)/1e9, (t2-t1)/1e9, (t3-t2)/1e9, (t4-t3)/1e9));
    }
    
    public static void main(String[] args) {
        // Klaidų srautą err sutapatiname su out, tikslu,
        // kad klaidų pranešimai sinchronizuotųsi su rezultatų išvedimu
        System.setErr(so);
        demonstracijaSuRingBuffer();
        so.println(String.format("Greitaveika"));
        so.println(String.format("%6s %7s %7s %7s %7s\n", "El. kiekis","RBenqu","RBdeq","Qadd", "Qremove"));
        for(int n: tiriamiKiekiai)
           paprastasTyrimas(n);
//        greitaveikosTyrimas();
    }

}
