package be.beardhatcode.bookeep;

public class Warranty {
    private final long id;
    private final String name;
    private final long receipt;
    private final String note;

    public Warranty(long id, String name, long receipt, String note) {
        this.id = id;
        this.name = name;
        this.receipt = receipt;
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getReceipt() {
        return receipt;
    }

    public String getNote() {
        return note;
    }
}
