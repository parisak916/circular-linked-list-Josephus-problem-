/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circularlinkedlist;

/**
 *
 * @author Parisa Khan
 */
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CircularLinkedList<E> implements Iterable<E> {

	
	
	// Your variables
	// You can include a reference to a tail if you want
	Node<E> head;
        Node<E> tail;
	 int size;  // BE SURE TO KEEP TRACK OF THE SIZE
      
	
	// implement this constructor
	public CircularLinkedList() {
            head = null;
            tail = null;
            size = 0;
	}
       


	// writing helper functions for add and remove, like the book did can help
	// but remember, the last element's next node will be the head!



	// attach a node to the end of the list
	// Be sure to handle the adding to an empty list
	// always returns true 
	public boolean add(E e) {            
           add(size, e); 
            
        return true;
        }
           
       
        

	
	// need to handle
	// out of bounds
	// empty list
	// adding to front
	// adding to middle
	// adding to "end"
	// REMEMBER TO INCREMENT THE SIZE
	public boolean add(int index, E e){
            
            if(index < 0 || size > index )
            {
                throw new IndexOutOfBoundsException("out of bounds:" + index);
            }
            
            Node <E> temp = new Node<E>(e);
            if(size == 0)
            {
               
                head = temp;
                tail = temp;
                
            }
            //in front of head
            else if(index == 0)
            {
                temp.next = head;
                head = temp;
                tail.next = head;
            }
            //adding after tail
            else if(index == size)
            {
                tail.next = temp;
                tail = temp;
                //circular
                tail.next = head;
            }
            //adding inmiddle
            else { 
                Node<E> after = getNode(index);
                Node<E> beforeafter = getNode(index-1);
                temp.next = after;
                beforeafter.next = temp;
            
            }
            size++;
            return true;
	}

	// I highly recommend using this helper method
	// Return Node<E> found at the specified index
	// be sure to handle out of bounds cases
	private Node<E> getNode(int index ) {
           
            if (index < 0 || index > size) {
                
            throw new IndexOutOfBoundsException("out of bounds: " + index);
                
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
	}

	
	
	// remove must handle the following cases
	// out of bounds
	// removing the only thing in the list
	// removing the first thing in the list (need to adjust the last thing in the list to point to the beginning)
	// removing the last thing (if you have a tail)
	// removing any other node.
	// REMEMBER TO DECREMENT THE SIZE
	public E remove(int index) {
            
		if (index < 0 || index > size) {
                    System.out.println("index  < 0");
                    throw new IndexOutOfBoundsException("out of bounds: " + index);
                }
     
        E item = null;
           
        if(size == 1) { // only 1 item
           
            item =  head.getElement();
            head = null;
            tail = null;
        } else if(index == 0) { // removing the head
          
            item = head.getElement();
            head = head.next;
            tail.next = head;
         
        } else if(index == size){ //removing tail
           
            Node<E> beforetail = getNode(size-1);
            
            item = tail.getElement();
            tail = beforetail.next;
            tail.next = head;
        } 
        else{ //removing in the middle
         
            Node<E> node = getNode(index);
            Node<E> beforenode = getNode(index-1);
            beforenode.next = node.next;
            item = node.getElement();          
        }
           
        size--;
        return item;
	}
	
	
	
	
	// Turns your list into a string
	// Useful for debugging
	public String toString(){
		Node<E> current =  head;
		StringBuilder result = new StringBuilder();
		if(size == 0){
			return "";
		}
		if(size == 1) {
			return head.getElement().toString();
			
		}
		else{
			do{
				result.append(current.getElement());
				result.append(" ==> ");
				current = current.next;
			} while(current != head);
		}
		return result.toString();
	}
	
	
	public Iterator<E> iterator() {
		return new ListIterator<E>();
	}
	
	// provided code
	// read the comments to figure out how this works and see how to use it
	// you should not have to change this
	// change at your own risk!
	private class ListIterator<E> implements Iterator<E>{
		
		Node<E> nextItem;
		Node<E> prev;
		int index;
		
		@SuppressWarnings("unchecked")
		//Creates a new iterator that starts at the head of the list
		public ListIterator(){
			nextItem = (Node<E>) head;
			index = 0;
		}

		// returns true if there is a next node
		// this is always should return true if the list has something in it
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return size != 0;
		}
		
		// advances the iterator to the next item
		// handles wrapping around back to the head automatically for you
		public E next() {
			
			prev =  nextItem;
			nextItem = nextItem.next;
			index =  (index + 1) % size;
			return prev.getElement();
	
		}
		
		// removed the last node was visted by the .next() call 
		// for example if we had just created a iterator
		// the following calls would remove the item at index 1 (the second person in the ring)
		// next() next() remove()
		public void remove() {
			int target = index;
			if(nextItem == head) {
				target = size - 1;
			} else{ 
                            //added if statement because throws  out of bounds exception when index is 0
                            if(index != 0) {
				target = index - 1;
                            }
                                
				index--; 
                                
			}
			CircularLinkedList.this.remove(target); //calls the above class
		}
		
	}
	
	
	// Solve the problem in the main method
	// The answer of n = 13,  k = 2 is 
	// the 11th person in the ring (index 10)
	public static void main(String[] args){
            
		CircularLinkedList<Integer> l =  new CircularLinkedList<Integer>();
                //works for any n and any k
		int n = 5;
		int k = 2;
		
                //adds values to list
               for(int i = 1; i < n+1; i++)
               {
                   l.add(i);
               }
               Iterator<Integer> iter = l.iterator();
             
               //while there is a node, it is true
               while(iter.hasNext())
               {
                   System.out.println(l.toString());
                   //skips every k-1 th element
                   for(int z = 0; z < k; z++)
                   {
                       iter.next();
                   }
                   //deletes every kth element
                   iter.remove();
                   
                   
                 }
                   
               }
                             
}

                
		
		
