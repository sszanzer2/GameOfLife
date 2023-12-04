package ss;

public class CircularQueue<T> implements QueueInterface<T> {
	 private static final int INITIAL_CAPACITY = 10;
	 private Object[] array;
	 private int head;
	 private int tail;
	 private int size;
	
	 // Constructor
	 public CircularQueue() {
	   array = new Object[INITIAL_CAPACITY];
	   head = 0;
	   tail = 0;
	   size = 0;
	 }
	
	 @Override
	 // if data is completed at the back of the array 
	 // we go to the front of the array to keep enqueuing data
	 public void enqueue(T element) {
	   ensureCapacity();
	   array[tail] = element;
	 
	   tail = (tail + 1) % array.length;
	 
	   size++;
	 }
	
	 @Override
	 public T dequeue() {
	   if (isEmpty()) {
	     throw new IllegalStateException("Queue is empty");
	   }
	   @SuppressWarnings("unchecked")
	   T removed = (T) array[head];
	   // Dereference to allow for garbage collection
	 
	   array[head] = null;
	   head = (head + 1) % array.length;
	 
	   size--;
	   return removed;
	 }
	
	 @SuppressWarnings("unchecked")
		@Override
	 public T peek() {
	   if (isEmpty()) {
	     throw new IllegalStateException("Queue is empty");
	   }
	   return (T) array[head];
	 }
	
	 @Override
	 public boolean isEmpty() {
	   return size == 0;
	 }
	
	 @Override
	 public int size() {
	   return size;
	 }
	
	 @Override
	 public void clear() {
	 // Reset array and indices
	   array = new Object[INITIAL_CAPACITY];
	   head = 0;
	   tail = 0;
	   size = 0;
	 }
	
	 private void ensureCapacity() {
	   if (size == array.length) {
	   // Resize the array
	   Object[] newArray = new Object[array.length * 2];
	   int index = 0;
	 
	 // Copy elements to the new array with circular indexing
	 
	   for (int i = head; i < head + size; i++) {
	     newArray[index++] = array[i % array.length];
	 }
	
	   // Reset indices and set the new array
	   head = 0;
	   tail = index;
	 
	   array = newArray;
	   }
	 }
	 
	 @Override
	 public String toString() {
	   StringBuilder stringBuilder = new StringBuilder();
	   stringBuilder.append("Contents of the queue: \n");
	
	 // Iterate from the head to the tail
	   for (int i = head; i != tail; i = (i + 1) % array.length) {
	     if (array[i] != null) {
	   stringBuilder.append(array[i]);
	   stringBuilder.append("\n");
	   }
	  }
	
	  return stringBuilder.toString();
	 }
}
