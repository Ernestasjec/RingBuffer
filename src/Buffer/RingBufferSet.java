/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buffer;

import Buffer.RingBufferADT;

/**
 *
 * @author Ernestas
 */
public class RingBufferSet<T> implements RingBufferADT<T>{
    private T[] buffer = null;
    private int capacity; 
    private int first;
    private int last;
    private int size;

    public RingBufferSet(int capacity) {
        this.buffer = (T[]) new Object[capacity];
        this.capacity = capacity;
        this.first = 0;
        this.last = 0;
        this.size = 0;
    }
 
    @Override
    public int size() { return this.size; }

    @Override
    public T peek() {
        return (T) buffer[first];
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean isFull() {
        return this.size == this.capacity;
    }

    @Override
    public void enqueue(T element) {
	if (this.isFull()){
           throw new RuntimeException(" ring buffer is full");
        }
        if (size != 0)this.last++;
        this.last = this.last % capacity;
        this.buffer[this.last] = element;
        size++;
    }

    @Override
    public T dequeue() {
        if (this.isEmpty()) {
            throw new RuntimeException("ring buffer is emty");
        }
        T element = this.buffer[first];
        this.buffer[first] = null;
        size--;
        this.first++;
        this.first = this.first % capacity;
        return element;
    }
    
    public void println(){
        System.out.println("Spausdinami ring buffer elementai:");
        if (size == 0)
            System.out.println("Buferis yra tuščias");
        else {
            System.out.println("Buferio elementai:");
            for (int i = 0; i < capacity; i++) {
                System.out.print(buffer[i] + " ");
            }
            System.out.println();
        }
    }
}
