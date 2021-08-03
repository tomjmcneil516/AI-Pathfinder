package aipathfinder;

public class Node

{
    private double GCost = Double.POSITIVE_INFINITY;
    private double HCost = 0;
    private int[] parent;
    private int x;
    private int y;
    private boolean visited = false;
    
    public Node()
    {
    }
   
    public boolean getVisited(){
        return visited;
    }
    
    public void setVisited(){
        visited = true;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public double getFCost(){
        return GCost + HCost;
    }
    
    public double getGCost(){
        return GCost;
    }
    
    public double getHCost(){
        return HCost;
    }
    
    public int[] getParent(){
        return parent;
    }
    
    public void setParent(int x, int y){
        int[] array = new int[2];
        array[0] = x;
        array[1] = y;
        parent = array;
    }
    
    public void setGCost(double g){
        GCost = g;
    }
    
    public void setHCost(double h){
        HCost = h;
    }

  
   
}