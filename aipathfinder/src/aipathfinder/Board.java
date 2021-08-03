package aipathfinder;

import java.util.Random;
import java.lang.Math;
import java.util.ArrayList;


public class Board 
{
    private Space[][] BoardPosition = new Space[120][160];
    private int[] Start = new int[2];
    private int[] Finish = new int[2];
    
    
    public Board(){
    }
    
    
    public void setBoard(){
        initArray();
        setGraySquares();
        setHighways();
        setBlackSquares();
        setStartAndFinish();
    }

    public Space getSpace(int x, int y){
        return BoardPosition[x][y];
    }
    
    public int[] getStart(){
        return this.Start;
    }
    
    public int[] getFinish(){
        return this.Finish;
    }
    
    public void setStart(int x, int y){
        this.Start[0] = x;
        this.Start[1] = y;
    }
    public void setFinish(int x, int y){
        this.Finish[0] = x;
        this.Finish[1] = y;
    }
    
    public void setStartAndFinish(){
        Random rand = new Random();
        int StartRow = 0;
        int StartColumn = 0;
        int EndRow = 0;
        int EndColumn = 0;
        do{
            int StartSide = rand.nextInt(4);
            int EndSide = rand.nextInt(4);
            if(StartSide == 0){
                StartRow = rand.nextInt(20);
                StartColumn = rand.nextInt(160);
            }
            if(StartSide == 1){
                StartRow = rand.nextInt(120);
                StartColumn = rand.nextInt(20) + 140;
            }
            if(StartSide == 2){
                StartRow = rand.nextInt(20) + 100;
                StartColumn = rand.nextInt(160);
            }
            if(StartSide == 3){
                StartRow = rand.nextInt(120);
                StartColumn = rand.nextInt(20);
            }
            if(EndSide == 0){
                EndRow = rand.nextInt(20);
                EndColumn = rand.nextInt(160);
            }
            if(EndSide == 1){
                EndRow = rand.nextInt(120);
                EndColumn = rand.nextInt(20) + 140;
            }
            if(EndSide == 2){
                EndRow = rand.nextInt(20) + 100;
                EndColumn = rand.nextInt(160);
            }
            if(StartSide == 3){
                EndRow = rand.nextInt(120);
                EndColumn = rand.nextInt(20);
            }
            this.Start[0] = StartRow;
            this.Start[1] = StartColumn;
            this.Finish[0] = EndRow;
            this.Finish[1] = EndColumn;
        } while(getDistance(StartRow, StartColumn, EndRow, EndColumn) < 100 || BoardPosition[StartRow][StartColumn].getColor().equals("black") || BoardPosition[EndRow][EndColumn].getColor().equals("black"));
    }
    
    
    public void initArray(){
        for(int i = 0; i < 120; i++){
            for(int j = 0; j < 160; j++){
                BoardPosition[i][j] = new Space();
            }
        }
    }
    
    private void setGraySquares(){
        Random rand = new Random();
        for(int k = 0; k < 8; k++){
            int xCord = rand.nextInt(120);
            int yCord = rand.nextInt(160);
            for(int i = xCord - 15; i <= xCord + 15; i++){
                for(int j = yCord - 15; j <= yCord + 15; j++){
                    if(i >= 120 || i < 0){
                        break;
                    }
                    else if(j >= 160 || j < 0){
                    }
                    else{
                        int GrayIfZero = rand.nextInt(2);
                        if(GrayIfZero == 0){
                            BoardPosition[i][j].setColor("gray");
                        }
                    }
                }
            }
        }
    }
    
    private void setBlackSquares(){
        Random rand = new Random();
        int BlackSquares = 0;
        while(BlackSquares != 3840){
            int xCord = rand.nextInt(120);
            int yCord = rand.nextInt(160);
            
            if(!BoardPosition[xCord][yCord].getColor().equals("black") && BoardPosition[xCord][yCord].getHighway() == false){
                BoardPosition[xCord][yCord].setColor("black");
                BlackSquares++;
            }
        }
    }
    
    
    
    private double getDistance(int x1, int y1, int x2, int y2){
        int a = Math.abs(x1 - x2);
        int b = Math.abs(y1 - y2);
        return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }
    
    public void setHighways(){
        Random rand = new Random();
        ArrayList<Space> h1 = new ArrayList<Space>();
        ArrayList<Space> h2 = new ArrayList<Space>();
        int Paths = 0;
        
        while(Paths < 4){
        int TotalPathLength = 0;
        int PathLength = 0;
        boolean BoundaryReached = false;
        int[]Cords = genBoundaryCords();
        int xCord = Cords[0];
        int yCord = Cords[1];
        int PathDirection = rand.nextInt(4);
        
       
        while(true){
            if(PathDirection == 0){
                if(xCord - 20 >= 0){
                    PathLength = 20;
                }
                else{
                    PathLength = xCord + 1;
                    BoundaryReached = true;
                }
                    
                if(TestPath(xCord, yCord, PathDirection, PathLength, h1, h2) == false){
                    h1.clear();
                    break;
                }
                else{
                    h1 = BuildPath(xCord, yCord, PathDirection, PathLength, h1);
                    TotalPathLength += PathLength;
                    xCord = xCord - PathLength;
                }

            }
            else if(PathDirection == 1){
                if(yCord + 20 < 160){
                    PathLength = 20;
                }
                else{
                    PathLength = 160 - yCord;
                    BoundaryReached = true;
                }
                    
                if(TestPath(xCord, yCord, PathDirection, PathLength, h1, h2) == false){
                    h1.clear();
                    break;
                }
                else{
                    h1 = BuildPath(xCord, yCord, PathDirection, PathLength, h1);
                    TotalPathLength += PathLength;
                    yCord = yCord + PathLength;
                }
                
            }
            else if(PathDirection == 2){
                if(xCord + 20 < 120){
                    PathLength = 20;
                }
                else{
                    PathLength = 120 - xCord;
                    BoundaryReached = true;
                }
                    
                if(TestPath(xCord, yCord, PathDirection, PathLength, h1, h2) == false){
                    h1.clear();
                    break;
                }
                else{
                    h1 = BuildPath(xCord, yCord, PathDirection, PathLength, h1);
                    TotalPathLength += PathLength;
                    xCord = xCord + PathLength;
                }
                
            }
            else if(PathDirection == 3){
                if(yCord - 20 >= 0){
                    PathLength = 20;
                }
                else{
                    PathLength = yCord + 1;
                    BoundaryReached = true;
                }
                    
                if(TestPath(xCord, yCord, PathDirection, PathLength, h1, h2) == false){
                    h1.clear();
                    break;
                }
                else{
                    h1 = BuildPath(xCord, yCord, PathDirection, PathLength, h1);
                    TotalPathLength += PathLength;
                    yCord = yCord - PathLength;
                }
                
            }
            
            if(BoundaryReached == true){
                if(TotalPathLength >= 100){
                    Paths++;
                    h2.addAll(h1);
                    
                }
                h1.clear();
                break;
            }
            
            int RandNum = rand.nextInt(5);
            if(RandNum == 4){
                PathDirection = (PathDirection + 1) % 4;
            }
            else if(RandNum == 3){
                PathDirection = (PathDirection + 3) % 4;
            }
            
        }
      }
    
      for(int i = 0; i < h2.size(); i++){
          h2.get(i).setHighway(true);
      }
    }

    
    private boolean TestPath(int XCord, int YCord, int PathDirection, int PathLength, ArrayList<Space>h1, ArrayList<Space>h2){
            if(h1.contains(BoardPosition[XCord][YCord]) || h2.contains(BoardPosition[XCord][YCord])){
                    return false;
        }
        
        if(PathDirection == 0){
            for(int i = 0; i < PathLength; i++){
                if(h1.contains(BoardPosition[XCord - i][YCord]) || h2.contains(BoardPosition[XCord - i][YCord])){
                    return false;
                }
            }
        }
        else if(PathDirection == 1){
            for(int i = 0; i < PathLength; i++){
                if(h1.contains(BoardPosition[XCord][YCord + i]) || h2.contains(BoardPosition[XCord][YCord + i])){
                    return false;
                }  
            }
        }
        else if(PathDirection == 2){
            for(int i = 0; i < PathLength; i++){
                if(h1.contains(BoardPosition[XCord + i][YCord]) || h2.contains(BoardPosition[XCord + i][YCord])){
                    return false;
                }  
            }
        }
        else if(PathDirection == 3){
            for(int i = 0; i < PathLength; i++){
                if(h1.contains(BoardPosition[XCord][YCord - i]) || h2.contains(BoardPosition[XCord][YCord - i])){
                    return false;
                }
            }
        }
        return true;
    }
    
    private ArrayList<Space> BuildPath(int XCord, int YCord, int PathDirection, int PathLength, ArrayList<Space>h){

        if(PathDirection == 0){
            for(int i = 0; i < PathLength; i++){
                h.add(BoardPosition[XCord - i][YCord]);
            }
        }
        else if(PathDirection == 1){
            for(int i = 0; i < PathLength; i++){
                h.add(BoardPosition[XCord][YCord + i]);
                }
        }
        else if(PathDirection == 2){
            for(int i = 0; i < PathLength; i++){
                h.add(BoardPosition[XCord + i][YCord]);
            }
        }
        else if(PathDirection == 3){
            for(int i = 0; i < PathLength; i++){
                h.add(BoardPosition[XCord][YCord - i]);
            }
        }
        return h;
    }
    
    private int[] genBoundaryCords(){
        int[]Cords = new int[2];
        Random rand = new Random();
        int BoundarySide = rand.nextInt(4); //0 = North, 1 = East, 2 = South, 3 = West
        if(BoundarySide == 0){
            Cords[0] = 0;
            Cords[1] = rand.nextInt(160);
        }
        else if(BoundarySide == 1){
            Cords[0] = rand.nextInt(120);
            Cords[1] = 159;
        }
        else if(BoundarySide == 2){
            Cords[0] = 119;
            Cords[1] = rand.nextInt(160);
        }
        else if(BoundarySide == 3){
            Cords[0] = rand.nextInt(120);
            Cords[1] = 0;
        }
        return Cords;
    }
    
}
