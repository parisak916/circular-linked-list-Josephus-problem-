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
public class Node<E> {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    }
        
	private E element;
	Node<E> next;
       // Node <E> prev;
	
	
	
	public Node() {
		this.element = null;
		this.next = null;
                //this.prev = null;
		
	}
	
	public Node(E e) {
		this.element = e;
		this.next = null;
               // this.prev = null;
	}
	
	
	public E getElement() {
		return this.element;
	}
	
	
	public void setElement(E element) {
		this.element= element;
	}
    }
    
