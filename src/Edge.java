public class Edge {

    //define public variables
    private int x;
    private int y;
    private int weight;


    //class contructor
    public Edge(int x, int y, int weight){
        this.x=x;
        this.y=y;
        this.weight=weight;
    }

    //contructor with default values
    public Edge(){
        this.x=0;
        this.y=0;
        this.weight=0;

    }
    //the following methods are getters and setters.
    public int getFirstEnd(){

        return this.x;
    }

    public void setFirstEnd(int x){
        this.x=x;
    }

    public void setLastEnd(int y){
        this.y=y;
    }

    public int getLastEnd(){

        return this.y;
    }

    public int getWeight(){
        return this.weight;
    }

    public void setWeight(int w){
        weight=w;

    }


}
