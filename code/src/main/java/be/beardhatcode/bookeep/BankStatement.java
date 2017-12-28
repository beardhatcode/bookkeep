package be.beardhatcode.bookeep;

import java.util.Date;
import java.util.function.BiFunction;
/**
 *
 * Subclasses should be made for each source_id.
 * 
 */
public class BankStatement {
    private final long id;
    private final double amount;
    private final String sender;
    private final String receiver;
    private final String comment;
    private final Date date;
    private final String note;
    private final String raw;

    public BankStatement(long id, double amount, String sender, String receiver, String comment, Date date, String note, String raw) {
        this.id = id;
        this.amount = amount;
        this.sender = sender;
        this.receiver = receiver;
        this.comment = comment;
        this.date = date;
        this.note = note;
        this.raw = raw;
    }

    public long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate() {
        return date;
    }

    public String getNote() {
        return note;
    }

    public String getRaw() {
        return raw;
    }

    public enum RelationType{
        PAYS_FOR((s,o) -> s.amount - o.amount),
        REIMBURSES((s,o) -> s.amount - o.amount);

        private final BiFunction<BankStatement, BankStatement, Double> combinator;

        RelationType(BiFunction<BankStatement,BankStatement,Double> combinator) {
            this.combinator = combinator;
        }

        public double combine(BankStatement subject, BankStatement object){
            return this.combinator.apply(subject,object);
        }
    }
}
