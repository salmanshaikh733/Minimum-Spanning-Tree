public class minHeap implements minHeapADT {

    public Edge[] Edges;
    public int[] Heap;
    private int max;
    private int heapMax;

    //create the heap
    @Override
    public void heap(Edge keys[], int n) {

        Edges = keys;
        max = n - 1;
        Heap = new int[2 * max];
        heapMax = 2*max-1;
        for (int i = max; i <= heapMax; i++) {
            Heap[i] = i - max + 1;
        }

        for (int i = max-1; i >= 1; i--) {

            if (Edges[Heap[2 * i]].getWeight() < Edges[Heap[2 * i + 1]].getWeight()) {
                Heap[i] = Heap[2 * i];
            } else {
                Heap[i] = Heap[2 * i + 1];
            }
        }

    }

    //check inside the heap
    public boolean in_heap(Edge id) {


        for (int i = 0; i < Edges.length; i++) {

            if (Edges[i].getFirstEnd() == id.getFirstEnd() && Edges[i].getLastEnd() == id.getLastEnd() && Edges[i].getWeight() == id.getWeight()) {
                return true;
            } else if (Edges[i].getFirstEnd() == id.getLastEnd() && Edges[i].getLastEnd() == id.getFirstEnd() && Edges[i].getWeight() == id.getWeight()) {
                return true;
            }
        }

        return false;
    }

    //get the min key
    public int min_key() {
        return Edges[Heap[1]].getWeight();
    }

    //get the min key id
    public int min_id() {
        return Heap[1];
    }

    //return the id of the specified key
    public int key(int id) {
        return Edges[id].getWeight();
    }


    //delete the element with minimum key and return it
    public Edge delete_min() {

        Edges[0].setLastEnd(0);
        Edges[0].setFirstEnd(0);
        Edges[0].setWeight(Integer.MAX_VALUE);
        Heap[Heap[1] + max - 1] = 0;

        Edge newEdge = Edges[Heap[1]];


        for(int i =(int) Math.floor((Heap[1] + max - 1) / 2); i>0; i=(int) Math.floor(i / 2)){
            if (Edges[Heap[2 * i]].getWeight() < Edges[Heap[2 * i + 1]].getWeight()) {
                Heap[i] = Heap[2 * i];
            } else {
                Heap[i] = Heap[2 * i + 1];
            }
        }

        return newEdge;

    }
    //sets the key of the element whose id isidtonewkeyif itscurrent key is greater thannewkey
    public void decrease_key(int id, int newKey) {

        Edges[id].setWeight(newKey);


        for(int i =(int) Math.floor((id + max - 1) / 2); i>0; i=(int) Math.floor(i/2)){
            if (Edges[Heap[2 * i]].getWeight() < Edges[Heap[2 * i + 1]].getWeight()) {
                Heap[i] = Heap[2 * i];
            } else {
                Heap[i] = Heap[2 * i + 1];
            }
        }
    }
}



