class LRUCache {

    class Node {
        int key, value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private HashMap<Integer, Node> map;
    private Node left;   // LRU (dummy)
    private Node right;  // MRU (dummy)

    public LRUCache(int capacity) 
    {
        this.capacity = capacity;
        map = new HashMap<>();

        // create dummy nodes
        left = new Node(0, 0);
        right = new Node(0, 0);

        // connect them
        left.next = right;
        right.prev = left;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        Node node = map.get(key);

        // ----- REMOVE node -----
        node.prev.next = node.next;
        node.next.prev = node.prev;

        // ----- INSERT at MRU (right side) -----
        node.prev = right.prev;
        node.next = right;
        right.prev.next = node;
        right.prev = node;

        return node.value;
    }
    
    public void put(int key, int value) 
    {
        if (map.containsKey(key)) 
        {
            Node node = map.get(key);
            node.value = value;

            // ----- REMOVE node -----
            node.prev.next = node.next;
            node.next.prev = node.prev;

            // ----- INSERT at MRU -----
            node.prev = right.prev;
            node.next = right;
            right.prev.next = node;
            right.prev = node;
        }
        else 
        {
            Node node = new Node(key, value);
            map.put(key, node);

            // ----- INSERT new node at MRU -----
            node.prev = right.prev;
            node.next = right;
            right.prev.next = node;
            right.prev = node;

            // if over capacity → remove LRU (left side)
            if (map.size() > capacity) {
                Node lru = left.next;

                // remove from list
                left.next = lru.next;
                lru.next.prev = left;

                // remove from hashmap
                map.remove(lru.key);
            }
        }
    }
}