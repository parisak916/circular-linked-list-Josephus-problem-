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
       
	
	
	//constructer
	public Node() {
		this.element = null;
		this.next = null;
                
		
	}
	
	//constructer
	public Node(E e) {
		this.element = e;
		this.next = null;
             
	}
	
	//gets E element
	public E getElement() {
		return this.element;
	}
	
	//sets E element
	public void setElement(E element) {
		this.element= element;
	}
    }
    
