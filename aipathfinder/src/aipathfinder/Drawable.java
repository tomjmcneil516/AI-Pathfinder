package aipathfinder;

import java.awt.*;
import javax.swing.*;

public class Drawable extends JPanel
{
    Board board;
    AStar a;

    public Drawable(Board board, AStar a)
    {
        this.board = board;
        this.a = a;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for(int i = 0; i < 120; i++){
            for(int j = 0; j < 160; j++){
                g.setColor(Color.BLACK);
                g.drawRect(5 * j, 5 * i, 5, 5);
                if(i == board.getFinish()[0] && j == board.getFinish()[1]){
                    g.setColor(Color.RED);
                }
                else if(i == board.getStart()[0] && j == board.getStart()[1]){
                    g.setColor(Color.GREEN);
                }
                else if(board.getSpace(i,j).getHighway() && board.getSpace(i,j).getColor().equals("gray")){
                    g.setColor(Color.BLUE);
                }
                else if(board.getSpace(i,j).getHighway() && board.getSpace(i,j).getColor().equals("white")){
                    g.setColor(Color.CYAN);
                }
                else if(board.getSpace(i,j).getColor().equals("black")){
                    g.setColor(Color.BLACK);
                }
                else if(board.getSpace(i,j).getColor().equals("gray")){
                    g.setColor(Color.GRAY);
                }
                else if(board.getSpace(i,j).getColor().equals("white")){
                    g.setColor(Color.WHITE);
                }
                g.fillRect(5 * j, 5 * i, 5, 5);
            }
        }
        
        
        g2.setStroke(new BasicStroke(2));
        g.setColor(Color.MAGENTA);
        Node [][]Values = a.getValues();
        
        
        Node current = Values[board.getFinish()[0]][board.getFinish()[1]];
        Node start = Values[board.getStart()[0]][board.getStart()[1]];
        
        if(current.getFCost() == Double.POSITIVE_INFINITY){
            return;
        }
        
        while(current != start){
            g.drawLine(5*current.getY() + 2, 5*current.getX() + 2, 5*current.getParent()[1] + 2, 5*current.getParent()[0] + 2);
            current = Values[current.getParent()[0]][current.getParent()[1]];
        }
        
       
        
    }

}
