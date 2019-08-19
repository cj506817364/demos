package com.ppj.algorithm.List;

public class LinkedList<E> implements List<E> {

	private Node<E> head;
	//private Node tail;
	private int size;
	
	public LinkedList() {
		head = null;
		size = 0;
	}
	
	
	//O(1)
	@Override
	public int getSize() {
 		return size;
	}

	//O(1)
	@Override
	public boolean isEmpty() {
 		return size == 0;
	}

	//O(n)
	@Override
	public boolean contains(E o) {
		Node p = head;
		while(p != null) {
			if(p.data.equals(o))
				return true;
			p = p.next;
		}
 		return false;
	}

	//O(n)
	@Override
	public int indexOf(E e) {
		
		int result = -1;
		Node p = head;
		int i = 0;
		while(p != null) {
			if(p.data.equals(e))
			{
				result = i;
				break;
			}
 			p = p.next;
			i++;
		}
 		return result;
	}

	//O(n)
	@Override
	public E get(int index) {
		if(index<0 || index >= size) {
			throw new IllegalArgumentException("非法下标...");
		}
		
		Node<E> p = head;
		for(int i=0; i<index; i++)
			p = p.next;
		
		return p.data;
	}

	//O(n)
	@Override
	public void set(int index, E e) {
		if(index<0 || index >= size) {
			throw new IllegalArgumentException("非法下标...");
		}
		
		Node p = head;
		for(int i=0; i<index; i++)
			p = p.next;
		
		 p.data = e;		
	}

	//O(n)
	@Override
	public void add(int index, E e) {
		if(index < 0 || index > size) {
			throw new IllegalArgumentException("下标越界.....");
		}
		
		//插到链表头部
		if(index == 0) {
			addFirst(e);
		}else if(index == size) {
			addLast(e);
		}else {
			Node prev = head;
			for(int i=0; i<index; i++) {
				prev = prev.next;
			}
			
			prev.next = new Node(e, prev.next);
			
			size++;
		}
 		
	}
	
	//O(1)
	public void addFirst(E e) {
		head = new Node(e, head);
		
		size++;
	}
	
	//O(n)
	public void addLast(E e) {
		Node node = new Node(e, null);
		
		//链表为空
		if(head == null) {
			head = node;
		}else {
			Node prev = head;
			while(prev.next != null) {
				prev = prev.next;
			}
			
			prev.next = node;
		}
		
		size++;
	}

	//O(n)
	@Override
	public E remove(int index) {
		
		if(index<0 || index >= size) {
			throw new IllegalArgumentException("非法下标...");
		}
		
		if(index == 0) {
			return removeFirst();
		}else if (index == size -1) {
			return removeLast();
		}else{
			
			Node<E> prev = head;
			for(int i=0; i<index-1; i++)
				prev = prev.next;
			
			Node<E> tmp = prev.next;
			prev.next = tmp.next;
			tmp.next = null;
			size--;
			
			return tmp.data;
					
			
		}
 	}
	
	//O(1)
	public E removeFirst() {
		if(head == null)
			return null;
		
		E result = head.data;
		head = head.next;
		size--;
		
		return result;
	}
	
	//O(n)
	public E removeLast() {
		if(head == null)
			return null;
		
		E result;
		
		//链表只有一个节点
		if(head.next == null) {
			result = head.data;
			head = null;
		}else {
			
			Node<E> prev = head;
			while(prev.next.next != null)
				prev = prev.next;
			
			result = prev.next.data;
			prev.next = null;
		}
		
		size--;
		return result;

	}
	
	public static void main(String[] args) {
		List<Integer> list = new LinkedList<>();
		
		for(int i=0; i<10; i++)
			list.add(i, i);
		
		for(int i=0; i<10; i++) {
			System.out.println("The " + i + "th element is: " + list.get(i));
		}
		
		list.remove(5);
		
		for(int i=0; i<list.getSize(); i++) {
			System.out.println("After removing, the " + i + "th element is: " + list.get(i));
		}
		
	}

}
