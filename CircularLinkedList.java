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

	
	
	//top of linked list
	Node<E> head;
	//bottom of linked list
        Node<E> tail;
	 int size; 
      
	
	//constructor
	public CircularLinkedList() {
            head = null;
            tail = null;
            size = 0;
	}
       


	// attach a node to the end of the list
	// handle the adding to an empty list
	// always returns true
	//wrapper method for add
	public boolean add(E e) {            
           add(size, e); 
            
        return true;
        }
           

	// handles out of bounds
	// empty list
	// adding to front
	// adding to middle
	// adding to "end"
	//adds to size
	public boolean add(int index, E e){
            
	 //throws exception if size is greater than index or if index is negative
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
            //adding in middle
            else { 
                Node<E> after = getNode(index);
                Node<E> beforeafter = getNode(index-1);
                temp.next = after;
                beforeafter.next = temp;
            
            }
            size++;
            return true;
	}


	// Return Node<E> found at the specified index
	// handles out of bounds cases
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

	
	
	// remove handles the following cases
	// out of bounds
	// removing the only thing in the list
	// removing the first thing in the list (need to adjust the last thing in the list to point to the beginning)
	// removing the last thing (if you have a tail)
	// removing any other node.
	// decrements size
	public E remove(int index) {
            
		if (index < 0 || index > size) {
                    System.out.println("index  < 0");
                    throw new IndexOutOfBoundsException("out of bounds: " + index);
                }
     
        E item = null;
          
	//only 1 item in list
        if(size == 1) {
           
            item =  head.getElement();
            head = null;
            tail = null;
	//removes head
        } else if(index == 0) { 
          
            item = head.getElement();
            head = head.next;
            tail.next = head;
         
	//removing tail
        } else if(index == size){ 
           
            Node<E> beforetail = getNode(size-1);
            
            item = tail.getElement();
            tail = beforetail.next;
            tail.next = head;
        } 
	//removing in the middle
        else{ 
         
            Node<E> node = getNode(index);
            Node<E> beforenode = getNode(index-1);
            beforenode.next = node.next;
            item = node.getElement();          
        }
           
        size--;
        return item;
	}
	
	
	
	
	// Turns  list into a string
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
		
		//provided code
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
	
	
	// Solves the problem in the main method
	// The answer of n = 13,  k = 2 is 
	// the 11th person in the ring (index 10)
	public static void main(String[] args){
            
		CircularLinkedList<Integer> l =  new CircularLinkedList<Integer>();
                //works for any n and any k
		int n = 13;
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
                
		
		
