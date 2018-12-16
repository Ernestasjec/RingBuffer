package TP_Jėcka_Ernestas;

import Buffer.RingBufferSet;

public class GuitarString {
    	private final double  DECAY_FACTOR = 0.996;	
	private RingBufferSet<Double> ringbuffer;
	private int sampleNum = 0;
        private int ticCounter = 0;

    // creates a guitar string of the specified frequency,
    // using sampling rate of 44,100
    public GuitarString(double frequency) {
        sampleNum = Math.round((float)(StdAudio.SAMPLE_RATE/frequency));
		
		if( frequency <= 0 || sampleNum < 2 ){
			throw new IllegalArgumentException();
		}
		
		ringbuffer = new RingBufferSet<Double>(sampleNum);		
		for( int i = 0; i < sampleNum; i++){
			ringbuffer.enqueue(0.0);
			
		}
    }

    // creates a guitar string whose size and initial values are given by
    // the specified array
    public GuitarString(double[] init) {
        sampleNum = init.length;
		
		if(sampleNum < 2) {
			throw new IllegalArgumentException();
		}
		
		ringbuffer = new RingBufferSet<Double>(init.length);
		
		for(Double values: init){
			ringbuffer.enqueue(values);			
		}	
		
		System.out.println(ringbuffer);
    }

    // returns the number of samples in the ring buffer
    public int length() {
        return 0;
    }

    // plucks the guitar string (by replacing the buffer with white noise)
    public void pluck() {
      for(int i = 0; i < sampleNum; i++){
            ringbuffer.dequeue();
            ringbuffer.enqueue(Math.random() - 0.5);
      }
    }

    // advances the Karplus-Strong simulation one time step
    public void tic() {
        double first = ringbuffer.dequeue();
        double second = ringbuffer.peek();	
	ringbuffer.enqueue(DECAY_FACTOR * 0.5 * (first + second));
        ticCounter++;
    }

    // returns the current sample
    public double sample() {
        return ringbuffer.peek();
    }

    // returns the current sample
    public int time() {
        return ticCounter;
    }
    

}
