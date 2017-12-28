package be.beardhatcode.bookeep;

import java.sql.Time;

/**
 *
 * Subclasses should be made for each source_id.
 *
 */
public class Invoice {

    private final long id;
    private String name;
    private long organisationId;
    private long typeId;
    private double amount;
    private Time date;
    private String note;
    private final String path;
    private String content;
    private Long paid;
    private final String raw;

    public Invoice(long id, String name, long organisationId, long typeId, double amount, Time date, String note, String path, String content, Long paid, String raw) {
        this.id = id;
        this.name = name;
        this.organisationId = organisationId;
        this.typeId = typeId;
        this.amount = amount;
        this.date = date;
        this.note = note;
        this.path = path;
        this.content = content;
        this.paid = paid;
        this.raw = raw;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getOrganisationId() {
        return organisationId;
    }

    public long getTypeId() {
        return typeId;
    }

    public double getAmount() {
        return amount;
    }

    public String getNote() {
        return note;
    }

    public String getPath() {
        return path;
    }

    public String getContent() {
        return content;
    }

    public Long getPaid() {
        return paid;
    }

    public String getRaw() {
        return raw;
    }

    public Time getDate() {
        return date;
    }
}
