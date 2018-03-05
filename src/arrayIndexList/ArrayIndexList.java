package arrayIndexList;

import indexList.IndexList;

public class ArrayIndexList<E> implements IndexList<E> {
	private static final int INITCAP = 1; 
	private static final int CAPTOAR = 1; 
	private static final int MAXEMPTYPOS = 2; 
	private E[] element; 
	private int size; 

	public ArrayIndexList() { 
		element = (E[]) new Object[INITCAP]; 
		size = 0; 
	} 
	
	//For Validating
	private boolean valid(int i, int m){
		return(i<m || i>0);
		
	}
//	public void add(int index, E e) throws IndexOutOfBoundsException {
//		if(index>size || )
//			throw new IndexOutOfBoundsException("Invalid index");
//		if(size == element.length)
//			this.changeCapacity(CAPTOAR);
//		for(int i = size -1;i>= index;i--){
//			element[i+1]= element[i];
//		}
//		element[index]= e;
//		size++;
//	}
public void add(int index, E e) throws IndexOutOfBoundsException {
		
		if(index>size || index<0){
			throw new IndexOutOfBoundsException("add: Invalid Index = " + index );
		}
		if(size == element.length){
			changeCapacity(CAPTOAR);
			
		}
		this.moveDataOnePositionTR(index, size-1);
		element[index] = e;
		
		size++;
}


	public void add(E e) {
		add(size,e);
	}


	public E get(int index) throws IndexOutOfBoundsException {
		if(!valid(index,size))
			throw new IndexOutOfBoundsException("Invalid index");
		return element[index]; 
	}


	public boolean isEmpty() {
		return size == 0;
	}


	public E remove(int index) throws IndexOutOfBoundsException {
		if(!valid(index,size))
			throw new IndexOutOfBoundsException("Invalid index");
		
		if(element.length>size+CAPTOAR)
			changeCapacity(-CAPTOAR);
		if(index<size-1){
			moveDataOnePositionTL(index+1, size-1);
		}
		
		E etr = element[index];
		element[size-1]=null;
		size--;
		return etr;
	}


	public E set(int index, E e) throws IndexOutOfBoundsException {
		if(!valid(index,size)) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		E etr = element[index];
		element[index]=e;
		return etr;
	}


	public int size() {
		return size;
	}	
	
	
	
	// private methods  -- YOU CAN NOT MODIFY ANY OF THE FOLLOWING
	// ... ANALYZE AND USE WHEN NEEDED
	
	// you should be able to decide when and how to use
	// following method.... BUT NEED TO USE THEM WHENEVER
	// NEEDED ---- THIS WILL BE TAKEN INTO CONSIDERATION WHEN GRADING
	
	private void changeCapacity(int change) { 
		int newCapacity = element.length + change; 
		E[] newElement = (E[]) new Object[newCapacity]; 
		for (int i=0; i<size; i++) { 
			newElement[i] = element[i]; 
			element[i] = null; 
		} 
		element = newElement; 
	}
	
	// useful when adding a new element with the add
	// with two parameters....
	private void moveDataOnePositionTR(int low, int sup) { 
		// pre: 0 <= low <= sup < (element.length - 1)
		for (int pos = sup; pos >= low; pos--)
			element[pos+1] = element[pos]; 
	}

	// useful when removing an element from the list...
	private void moveDataOnePositionTL(int low, int sup) { 
		// pre: 0 < low <= sup <= (element.length - 1)
		for (int pos = low; pos <= sup; pos++)
			element[pos-1] = element[pos]; 
	}


	// The following two methods are to be implemented as part of an exercise
	public Object[] toArray() {
		// TODO es in Exercise 3
		return element;
	}


	@Override
	public <T1> T1[] toArray(T1[] array) {
		array = (T1[]) new Object [size()];
		for(int i = 0;i<this.size();i++) {
			array[i]=(T1) element[i];
		}
		return array;
	}

	@Override
	public int capacity() {
		// TODO Auto-generated method stub
		return element.length;
	}

}
