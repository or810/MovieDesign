public enum MovieMedia {
    // an appropriate cost and id for a day of rent
    DVD(0,15),
    CASETTE(1,20),
    BLU_RAY(2,10);

    private int id;
    private int cost;
    
    MovieMedia(int id, int cost) {
        this.cost = cost;
        this.id = id;
    }

    public int getCost() {
        return cost;
    }
    
    public int getId() {
        return id;
    }

    public static String getNameById(int index) {
        for(MovieMedia media : MovieMedia.values())
            if(media.id == index)
                return media.name();
        return null;
    }
}
