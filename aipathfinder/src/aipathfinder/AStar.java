package aipathfinder;

import java.util.*;
import java.lang.Math;
import java.util.ArrayList;


public class AStar
{
    private Board board;
    private Node[][] Values;
    private double weight;
    private int visitedNodes;
    
    public AStar(){
    }
    
    public AStar(Board board, double weight)
    {
        this.board = board;
        Values = new Node[120][160];
        this.weight = weight;
        setValues();
        run();
    }
    
    public Board getBoard(){
        return board;
    }
    
    public int getVisitedNodes(){
        return visitedNodes;
    }
    
    public void run(){
        int x = board.getStart()[0];
        int y = board.getStart()[1];
        PriorityQueue<Node> open = new PriorityQueue<>(new Checker());
        Values[x][y].setGCost(0);
        open.add(Values[x][y]);
        int count = 0;
        
        while(open.size() != 0){
             Node current = open.poll();
             x = current.getX();
             y = current.getY();
             Values[x][y].setVisited();
             count++;
             if(x == board.getFinish()[0] && y == board.getFinish()[1]){
                 break;
              }
             
             if(x + 1 < 120){
                if(!board.getSpace(x + 1, y).getColor().equals("black") && !Values[x + 1][y].getVisited()){
                        if(calculateGCosts(x, y, x + 1, y, false) < Values[x + 1][y].getGCost()){
                            Values[x + 1][y].setGCost(calculateGCosts(x, y, x + 1, y, false));
                            Values[x + 1][y].setParent(x, y);
                            open.remove(Values[x + 1][y]);
                        }
                        if(!open.contains(Values[x + 1][y])){
                            open.add(Values[x + 1][y]);
                        }
                    }
                }
             if(y + 1 < 160){
                if(!board.getSpace(x, y + 1).getColor().equals("black") && !Values[x][y + 1].getVisited()){
                        if(calculateGCosts(x, y, x, y + 1, false) < Values[x][y + 1].getGCost()){
                            Values[x][y + 1].setGCost(calculateGCosts(x, y, x, y + 1, false));
                            Values[x][y + 1].setParent(x, y);
                            open.remove(Values[x][y + 1]);
                        }
                        if(!open.contains(Values[x][y + 1])){
                            open.add(Values[x][y + 1]);
                        }
                    }
                }
             if(x - 1 >= 0){
                if(!board.getSpace(x - 1, y).getColor().equals("black") && !Values[x - 1][y].getVisited()){
                        if(calculateGCosts(x, y, x - 1, y, false) < Values[x - 1][y].getGCost()){
                            Values[x - 1][y].setGCost(calculateGCosts(x, y, x - 1, y, false));
                            Values[x - 1][y].setParent(x, y);
                            open.remove(Values[x - 1][y]);
                        }
                        if(!open.contains(Values[x - 1][y])){
                            open.add(Values[x - 1][y]);
                        }
                    }
                }
             if(y - 1 >= 0){
                if(!board.getSpace(x, y - 1).getColor().equals("black") && !(Values[x][y - 1].getVisited())){
                        if(calculateGCosts(x, y, x, y - 1, false) < Values[x][y - 1].getGCost()){
                            Values[x][y - 1].setGCost(calculateGCosts(x, y, x, y - 1, false));
                            Values[x][y - 1].setParent(x, y);
                            open.remove(Values[x][y - 1]);
                        }
                        if(!open.contains(Values[x][y - 1])){
                            open.add(Values[x][y - 1]);
                        }
                    }
                }
             if(x + 1 < 120 && y + 1 < 160){
                if(!board.getSpace(x + 1, y + 1).getColor().equals("black") && !Values[x + 1][y + 1].getVisited()){
                        if(calculateGCosts(x, y, x + 1, y + 1, true) < Values[x + 1][y + 1].getGCost()){
                            Values[x + 1][y + 1].setGCost(calculateGCosts(x, y, x + 1, y + 1, true));
                            Values[x + 1][y + 1].setParent(x, y);
                            open.remove(Values[x + 1][y + 1]);
                        }
                        if(!open.contains(Values[x + 1][y + 1])){
                            open.add(Values[x + 1][y + 1]);
                        }
                    }
                }
             if(x - 1 >= 0 && y - 1 >= 0){
                if(!board.getSpace(x - 1, y -1).getColor().equals("black") && !Values[x - 1][y - 1].getVisited()){
                        if(calculateGCosts(x, y, x - 1, y - 1, true) < Values[x - 1][y - 1].getGCost()){
                            Values[x - 1][y - 1].setGCost(calculateGCosts(x, y, x - 1, y - 1, true));
                            Values[x - 1][y - 1].setParent(x, y);
                            open.remove(Values[x - 1][y - 1]);
                        }
                        if(!open.contains(Values[x - 1][y - 1])){
                            open.add(Values[x - 1][y - 1]);
                        }
                    }
                }
             if(x + 1 < 120 && y - 1 >= 0){
                if(!board.getSpace(x + 1, y - 1).getColor().equals("black") && !Values[x + 1][y - 1].getVisited()){
                        if(calculateGCosts(x, y, x + 1, y - 1, true) < Values[x + 1][y - 1].getGCost()){
                            Values[x + 1][y - 1].setGCost(calculateGCosts(x, y, x + 1, y - 1, true));
                            Values[x + 1][y - 1].setParent(x, y);
                            open.remove(Values[x + 1][y - 1]);
                        }
                        if(!open.contains(Values[x + 1][y - 1])){
                            open.add(Values[x + 1][y - 1]);
                        }
                    }
                }
             if(x - 1 >= 0 && y + 1 < 160){
                if(!board.getSpace(x - 1, y + 1).getColor().equals("black") && !Values[x - 1][y + 1].getVisited()){
                        if(calculateGCosts(x, y, x - 1, y + 1, true) < Values[x - 1][y + 1].getGCost()){
                            Values[x - 1][y + 1].setGCost(calculateGCosts(x, y, x - 1, y + 1, true));
                            Values[x - 1][y + 1].setParent(x, y);
                            open.remove(Values[x - 1][y + 1]);
                        }
                        if(!open.contains(Values[x - 1][y + 1])){
                            open.add(Values[x - 1][y + 1]);
                        }
                    }
                }
        }
        this.visitedNodes = count;
        System.out.println("Elements visited: " + count);
        System.out.println("Total path cost: " + Values[board.getFinish()[0]][board.getFinish()[1]].getFCost());
        if(Values[board.getFinish()[0]][board.getFinish()[1]].getFCost() == Double.POSITIVE_INFINITY){
            System.out.println("No path found ");
        }
    }
    

    
    
    
    private void printPath(){
        Node current = Values[board.getFinish()[0]][board.getFinish()[1]];
        Node start = Values[board.getStart()[0]][board.getStart()[1]];
        while(current != start){
            System.out.println(" FCOST: " +  Values[current.getX()][current.getY()].getGCost() + " X: " + current.getX()+ ", Y: " + current.getY());
            current = Values[current.getParent()[0]][current.getParent()[1]];
        }
    }
    
    
    private double calculateGCosts(int x1, int y1, int x2, int y2, boolean isDiagonal){
        double gCost = Values[x1][y1].getGCost();
        double addedG = 0;
        
        if(board.getSpace(x1,y1).getColor().equals("gray")){
            if(board.getSpace(x2,y2).getColor().equals("gray")){
                if(isDiagonal){
                    addedG = Math.sqrt(8);
                }
                else{
                    addedG = 2;
                }
            }
            if(board.getSpace(x2,y2).getColor().equals("white")){
                if(isDiagonal){
                    addedG = (Math.sqrt(8) + Math.sqrt(2))/2;
                }
                else{
                    addedG = 1.5;
                }
            }
        }
        
        if(board.getSpace(x1,y1).getColor().equals("white")){
            if(board.getSpace(x2,y2).getColor().equals("white")){
                if(isDiagonal){
                    addedG = Math.sqrt(2);
                }
                else{
                    addedG = 1;
                }
            }
            if(board.getSpace(x2,y2).getColor().equals("gray")){
                if(isDiagonal){
                    addedG = (Math.sqrt(8) + Math.sqrt(2))/2;
                }
                else{
                    addedG = 1.5;
                }
            }
        }
        
        if(board.getSpace(x1,y1).getHighway() && board.getSpace(x2,y2).getHighway() && !isDiagonal){
            addedG = addedG/4;
        }
        return gCost + addedG;
    }
    
    public double calculateHCost(int x, int y){
        int a = Math.abs(x - board.getFinish()[0]);
        int b = Math.abs(y - board.getFinish()[1]);
        return weight * (a + b) * .25;
    }
    
    private void setValues(){
        for(int i = 0; i < 120; i++){
            for(int j = 0; j < 160; j++){
                Values[i][j] = new Node();
                Values[i][j].setX(i);
                Values[i][j].setY(j);
                Values[i][j].setHCost(calculateHCost(i, j));
            }
        }
    }
    
    public Node[][] getValues(){
        return Values;
    }
    
    public double getWeight(){
        return weight;
    }
}
