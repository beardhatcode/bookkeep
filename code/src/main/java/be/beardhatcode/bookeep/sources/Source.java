package be.beardhatcode.bookeep.sources;

import be.beardhatcode.bookeep.BankStatement;
import be.beardhatcode.bookeep.Invoice;

import java.util.Arrays;

public class Source {
    private final long id;
    private final String name;
    private final ImportType type;


    public Source(long id, String name, ImportType type, Class aClass) {
        this.id = id;
        this.name = name;
        this.type = type;
        try {
            String targetInterface = this.type.requiredInterface;
            boolean canCast = Arrays.stream(aClass.getGenericInterfaces()).anyMatch(
                    x -> x.getTypeName().equals(targetInterface)
            );
            if (canCast){
                ImportSource<BankStatement> x = (ImportSource<BankStatement>) aClass.newInstance();
            } else {
                throw new IllegalArgumentException("The class does not have interface "+targetInterface);
            }
        } catch (Throwable e) {
            throw new IllegalArgumentException("Could not get class working",e);
        }
    }

    public enum ImportType {
        BankStatement(BankStatement.class), Invoice(Invoice.class);

        public final String requiredInterface;
        ImportType(Class cls) {
            this.requiredInterface = String.format("%s<%s>", ImportSource.class.getName(), cls.getName());
        }
    }
}
