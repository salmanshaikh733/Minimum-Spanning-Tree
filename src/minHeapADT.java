public interface minHeapADT {

    // initialize heap
    void heap(Edge keys[], int n);

    // checks if the edge is in the heap
    boolean in_heap(Edge id);

    // returns the minimum key
    int min_key();

    // returns the minimum id
    int min_id();

    // returns the key at position id
    int key(int id);

    // deletes the minimum edge
    Edge delete_min();

    // decreases the key at position id
    void decrease_key(int id, int newKey);

}
