package be.beardhatcode.bookeep.sources;

import be.beardhatcode.bookeep.BankStatement;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class SourceTest {

    @Test
    public void testConstruct() {
        new Source(1,"test", Source.ImportType.BankStatement,SourceTestTestBS.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructWrongInterface() {
        new Source(1,"test",Source.ImportType.Invoice,SourceTestTestBS.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructNoInterface1() {
        new Source(1,"test",Source.ImportType.Invoice,SourceTestTestNothing.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructNoInterface2() {
        new Source(1,"test",Source.ImportType.BankStatement,SourceTestTestNothing.class);
    }
}

class SourceTestTestBS implements ImportSource<BankStatement>{

    @Override
    public List<BankStatement> parseFile(File lol) {
        return null;
    }

    @Override
    public String check() {
        return "Fuck off";
    }
}

class SourceTestTestNothing{

}
