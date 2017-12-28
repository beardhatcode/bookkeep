package be.beardhatcode.bookeep;

import java.util.Date;
import java.util.function.BiFunction;
/**
 *
 * Subclasses should be made for each source_id.
 * @todo put sourceid back in here
 * 
 */
public class BankStatement {
    private final Long id;
    private final String ref;
    private final double amount;
    private final String sender;
    private final String receiver;
    private final String comment;
    private final Date date;
    private final String note;
    private final String raw;

    public BankStatement(Long id, String ref, double amount, String sender, String receiver, String comment, Date date, String note, String raw) {
        this.id = id;
        this.ref = ref;
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

    public String getRef() {
        return ref;
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

    @Override
    public String toString() {
        return "BankStatement{" +
                "id=" + id +
                ", ref='" + ref + '\'' +
                ", amount=" + amount +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                ", note='" + note + '\'' +
                '}';
    }
}
