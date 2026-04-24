package lld.lruCache;

import java.util.HashMap;

public class LRUCache {

    int capacity;
    Node head;
    Node tail;
    HashMap<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;

    }


    public void put(int key, int val) {

        if (map.containsKey(key)) {
            Node existing = map.get(key);
            remove(existing);
            map.remove(key);
        }

        if (map.size() == capacity) {
            Node l1 = tail.prev;
            remove(l1);
            map.remove(l1.key);
        }

        Node n1 = new Node(key, val);
        addToFront(n1);
        map.put(key, n1);
    }

    public int get(int key){
        if (!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);

        remove(node);
        addToFront(node);

        return node.val;
    }


    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToFront(Node node) {
        head.next.prev = node;
        node.next = head.next;
        head.next = node;
        node.prev = head;
    }

    public static void main(String[] args) {

        LRUCache cache = new LRUCache(2);

        cache.put(1, 10);
        cache.put(2, 20);

        System.out.println(cache.get(1)); // 10

        cache.put(3, 30); // evicts key 2

        System.out.println(cache.get(2)); // -1

        cache.put(4, 40); // evicts key 1

        System.out.println(cache.get(1)); // -1
        System.out.println(cache.get(3)); // 30
        System.out.println(cache.get(4)); // 40
    }

}
