
import java.util.HashMap;
import java.util.Scanner;
/*
 * LEAST RECENTLY USED CACHE (LRU) CACHE IMPLEMENTATION
 * 
 * All corner cases included 
 * 
 */

class Node{
	int key;
	int value;
	Node prev = null;
	Node next = null;
	Node(int k , int v){
		key = k;
		value = v;
	}
}

public class LRU_Cache {
	
	public static Node DLL = null;
	
	public static Node head = new Node(0,0) ;
	public static Node tail = new Node(0,0) ;
	static int capacity ;
	
	static HashMap<Integer, Node> map = new HashMap<Integer, Node>();
	
	public LRU_Cache(int k)
	{
		head.next = tail;
		tail.prev = head;
		capacity = k;
		DLL = head;
	}
	
	// to change the position for get method
	
	
	
 
	//to delete the node
	//at the end of the list before tail node
	public static void remove(Node node) {
		
		node.prev.next = node.next;
		node.next.prev = node.prev;
		
		
	}
	
	//to insert node after head
	
	public static Node insert(Node node) {
		
		Node temp = head.next;
		head.next = node;
		node.prev = head;
		node.next = temp;
		temp.prev = node;
		
		return node;
		
	}
	
	//get the value method 
	//check if value present then return or return -1
	public static int get(int key) {
		// if key already present in map
		if(map.containsKey(key))
		{
			Node temp = map.get(key);
			map.remove(key);
			Node node = new Node(temp.key , temp.value);
			remove(temp);
			
			Node t = insert(node);
			map.put(key, t);
			
			return t.value;
		}
		//if not present 
		return -1;
	}
	
	
	
	public static void put(int key , int value) {
		//capacity of cache is full
		  if(map.size() == capacity) {
		    if(map.containsKey(key))
		    {
		    	Node temp = map.get(key);
				map.remove(key);
				Node node = new Node(key, value);
				remove(temp);
				
				Node t = insert(node);
				map.put(key, t);
		    	
		    }	
			else
			{
				//remove the last node
				Node to_remove = tail.prev;
				int k = to_remove.key;
				map.remove(k);
				remove(tail.prev);
				//create new node
				Node node = new Node(key, value);
				//get address of new node
				Node temp = insert(node);
				//make entry in map
				map.put(key, temp);
			}
			
		}
		
		//for the size of cache is full
		
		else
		{
			if(map.containsKey(key))
			{
				//get location of node in dll
				Node temp = map.get(key);
				//remove node from dll
				remove(temp);
				//remove entry from map
				map.remove(key);
			}
			//create new node
			Node node = new Node(key, value);
			Node t = insert(node);
			//add new entry to map
			map.put(key, t);
		}
		
   }
	
	
	
	
	public static void main(String args[]) {
			
		Scanner sc = new Scanner(System.in) ;
		System.out.println("Enter the capcity of LRU :");
		new LRU_Cache(sc.nextInt());
		put(1,10);
		put(3,15);
		put(2,12);
		System.out.println(get(3));
		System.out.println(get(2));
		put(4,20);
		put(5,6);
		System.out.println(get(2));
		put(4,25);
		System.out.println(get(4));
		put(5,9);
		System.out.println(get(1));
		System.out.println(get(5));
		
		//uncomment to check key/value pairs in dll 
		
//		while(DLL != null)
//		{
//			System.out.println(DLL.key + " " + DLL.value);
//			DLL = DLL.next;
//		}
	}
	
}
