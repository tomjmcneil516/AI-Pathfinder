package aipathfinder;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class main
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int input = -1;
        Board board = new Board();
        board.setBoard();
        AStar a = null;
        boolean AlgRun = false;
        
        while(input != 0){
            System.out.println("Press 0 to exit");
            
            System.out.println("Press 1 to draw a new board");
        
            System.out.println("Press 2 to use the same board with a different Start and Finish");
            
            System.out.println("Press 3 to run the algorithm");
        
            System.out.println("Press 4 to get info about a specific cell");
      
        
        
            System.out.println("Input:");
            input = sc.nextInt();
            
            if(input == 0){
                System.out.println("Exiting...");
                return;
            }
            
            if(input == 1){
                board.setBoard();
                System.out.println("New board created");
                AlgRun = false;
            }
            
            if(input == 2){
                board.setStartAndFinish();
                System.out.println("New start and finish created");
                AlgRun = false;
            }
            
            if(input == 3){
                System.out.println("Enter weight: ");
                double weight =sc.nextDouble();
                a = new AStar(board, weight);
                JFrame f = new JFrame();
        
                Drawable drawing = new Drawable(board, a);

                f.setSize(1500, 1500);
        
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.getContentPane().add(drawing);
                f.setVisible(true);
                AlgRun = true;
            }
            
            if(input == 4){
                System.out.println("Enter X: ");
                int x = sc.nextInt();
                System.out.println("Enter Y: ");
                int y = sc.nextInt();
                Space s = board.getSpace(x, y);
                System.out.println("Color: " + s.getColor());
                System.out.println("Highway: " + s.getHighway());
                if(AlgRun == true){
                      System.out.println("FCost: " + a.getValues()[x][y].getFCost());
                      System.out.println("GCost: " + a.getValues()[x][y].getGCost());
                      System.out.println("HCost: " + a.getValues()[x][y].getHCost());
                      System.out.println("Parent: " + a.getValues()[x][y].getParent()[0] + ", " + a.getValues()[x][y].getParent()[1]);
                }
            }
            
            if(input == 5){
                System.out.println("Enter file name");
                String filename = sc.nextLine();
                Scanner fsc = null;
                try {
                    fsc = new Scanner(new File(filename));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();  
                }
                
                board.initArray();
                int linenumber = 0;
                
                while(fsc.hasNextLine()){
                    String line = sc.nextLine();
                    Scanner fsc2 = new Scanner(fsc.next());
                    if(linenumber == 0){
                         int x = Integer.parseInt(fsc2.next());
                         int y = Integer.parseInt(fsc2.next());
                         board.setStart(x, y);
                    }
                    else if(linenumber == 1){
                         int x = Integer.parseInt(fsc2.next());
                         int y = Integer.parseInt(fsc2.next());
                         board.setFinish(x, y);
                    }
                    else if(linenumber > 9){
                         String[] words = line.split(" ");
                         for(int i = 0; i < 160; i++){
                            if(words[i].equals("0")){
                                board.getSpace(linenumber - 9, i).setColor("black");
                            }
                            if(words[i].equals("1")){
                                board.getSpace(linenumber - 9, i).setColor("white");
                            }
                            if(words[i].equals("2")){
                                board.getSpace(linenumber - 9, i).setColor("white");
                            }
                            if(words[i].equals("a") || words[i].equals("b")){
                                board.getSpace(linenumber - 9, i).setHighway(true);
                            }
                         }
                    }
                    linenumber++;
                }
            }
        }
        
    }
}
