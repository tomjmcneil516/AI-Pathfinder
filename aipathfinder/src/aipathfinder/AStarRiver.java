package aipathfinder;


public class AStarRiver extends AStar
{
   
    public AStarRiver(Board board, double weight)
    {
        super(board, weight);
    }

    @Override
    public double calculateHCost(int x, int y){
        double a = Math.abs(x - super.getBoard().getFinish()[0]);
        double b = Math.abs(y - super.getBoard().getFinish()[1]);
        double h = .25 * (a + b);
        if(super.getBoard().getSpace(x, y).getHighway()){
            h = h/5;
        }
        return super.getWeight() * h;
    }
}
