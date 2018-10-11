public class Produce {
    boolean inSeason;
    Season season;
    String name;
    public Produce(Season season, boolean inSeason, String name){
        this.season = season;
        this.inSeason = inSeason;
        this.name = name;
    }

    public Season getSeason() {
        return season;
    }

    public String getName() {
        return name;
    }

    public boolean getInSeason(){
        return inSeason;
    }

    public void setInSeason(boolean inSeason) {
        this.inSeason = inSeason;
    }
}
