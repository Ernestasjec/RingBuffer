package Buffer;

/**
 *
 * @author Ernestas
 */
public interface RingBufferADT<T> {
    int size();                     // return number of items currently in the buffer
    boolean isEmpty();              // is the buffer empty (size equals zero)?
    boolean isFull();               // is the buffer full  (size equals capacity)?
    void enqueue(T elemnet);        // add item x to the end
    T dequeue();                    // delete and return item from the front
    T peek();                       // return (but do not delete) item from the front
}
