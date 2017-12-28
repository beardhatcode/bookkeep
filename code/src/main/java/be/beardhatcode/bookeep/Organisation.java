package be.beardhatcode.bookeep;

public class Organisation {
    private final long id;
    private String name;

    public Organisation(long id, String name){
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
