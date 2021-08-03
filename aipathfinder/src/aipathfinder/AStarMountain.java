package aipathfinder;


public class AStarMountain extends AStar
{

   
    public AStarMountain(Board board, double weight)
    {
        super(board, weight);
    }

    @Override
    public double calculateHCost(int x, int y){
        double a = Math.abs(x - super.getBoard().getFinish()[0]);
        double b = Math.abs(y - super.getBoard().getFinish()[1]);
        double h = .25 * (a + b);
        if(super.getBoard().getSpace(x, y).getColor().equals("gray") && !super.getBoard().getSpace(x, y).getHighway()){
            h = h * 1.3;
        }
        return super.getWeight() * h;
    }
}
