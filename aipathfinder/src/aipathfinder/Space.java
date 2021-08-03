package aipathfinder;

public class Space
{
    private String Color;
    private boolean isHighway;

    public Space()
    {
        this.Color = "white";
        this.isHighway = false;
    }

    public void setColor(String Color){
        this.Color = Color;
    }
    
    public void setHighway(boolean isHighway){
        this.isHighway = isHighway;
    }
    
    public String getColor(){
        return this.Color;
    }
    
    public boolean getHighway(){
        return this.isHighway;
    }
}

