package aipathfinder;

import java.util.*;

class Checker implements Comparator<Node>
  {
    @Override
    public int compare(Node a, Node b){
        if(a.getFCost() < b.getFCost()){
            return -1;
        }
        else if(a.getFCost() > b.getFCost()){
            return 1;
        }
        else if(a.getFCost() == b.getFCost()){
            if(a.getHCost() <= b.getHCost()){
                return -1;
            }
            else{
                return 1;
            }
        }
        return 0;
    }
  }
