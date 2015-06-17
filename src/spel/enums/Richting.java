package spel.enums;

public enum Richting {
    
    NEUTRAAL(0),
    LINKS(180),
    RECHTS(360),
    OMHOOG(270),
    OMLAAG(90);
    
    private int helling;

    private Richting(int graden) {
        this.helling = graden;
    }

    public double getGraden() {
        return Math.toRadians(this.helling);
    }
    
    @Override
    public String toString() {
        switch(helling) {
            case 0:
                return "neutraal";
                
            case 180:
                return "links";
                
            case 360:
                return "rechts";
                
            case 270:
                return "omhoog";
                
            case 90:
                return "omlaag";   
        }
        
        return null;
    }
}

