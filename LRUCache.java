/* LRU Cache */
/*
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and set.
 *
 * get(key) - Get the value (will always be positive) of the key if the key 
 * exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present.
 * When the cache reaches its capacity, it should invalidate the least recently
 * used item before inserting the new item.
 * The LRUCache will be initialized with an integer corresponding to its capacity.
 * Capacity indicates the maximum number of unique keys it can hold at a time.
 *
 * Definition of least recently used : An access to an item is defined as a get
 * or a set operation of the item. Least recently used item is the one with the
 * oldest access time.
 */

public class Solution {

    int capacity = 0;
    HashMap<Integer, Node> map;
    Node head;
    Node end;

    private class Node {
        int key;
        int val;
        Node prev, next;
        Node (int k,int v) {
            this.key = k;
            this.val = v;
        }
    }

    public Solution(int capacity) {
        this.capacity = capacity;
        map = new HashMap<Integer, Node>();
        head = null;
        end = null;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            remove(n);
            setAsHead(n);
            return n.val;
        }

        return -1;
    }

    public void set(int key, int value) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            n.val = value;
            remove(n);
            setAsHead(n);
        }
        else {
            Node toInsert = new Node(key, value);

            if (map.size() >= capacity) {
                map.remove(end.key);
                remove(end);
            }

            setAsHead(toInsert);
            map.put(key, toInsert);
        }
    }

    private void remove(Node n) {
        if (n.prev != null) {
            n.prev.next = n.next;
        }
        else {
            head = n.next;
        }

        if (n.next != null) {
            n.next.prev = n.prev;
        }
        else {
            end = n.prev;
        }

    }

    private void setAsHead(Node n) {
        n.prev = null;
        n.next = head;

        if (head != null) {
            head.prev = n;
        }

        head = n;

        if (end == null) {
            end = n;
        }
    }
}

