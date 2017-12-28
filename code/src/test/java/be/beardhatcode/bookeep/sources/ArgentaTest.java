package be.beardhatcode.bookeep.sources;

import be.beardhatcode.bookeep.BankStatement;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public class ArgentaTest {

    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    public File testfile;

    @Before
    public void setUp() throws Exception {
        testfile = folder.newFile("testfile1.csv");
        try(  PrintWriter out = new PrintWriter( testfile )  ){
            out.println("Nr v/d rekening :;BE64 1234 5678 9120;Wasabi +\n" +
                    "Valutadatum;Ref. v/d verrichting;Beschrijving;Bedrag v/d verrichting;Munt;Datum v. verrichting;Rekening tegenpartij;Naam v/d tegenpartij :;Mededeling 1 :;Mededeling 2 :\n" +
                    "01-01-2015;B4L31INPL00A1B2A;Uw interest berekening;1,02;EUR;31-12-2014;BE64 5496 4682 1397; ; ; \n" +
                    "30-12-2014;B4K30AG0710901U2;Betaling Bancontact;-12,34;EUR;28-02-2014;BE53 9789 0400 7553;KINEPOLIS LONDON; ; \n" +
                    "29-12-2014;BAP29BIK06BK1500;Uw overschrijving;-12,00;EUR;29-12-2014;BE59 7320 0121 8426;vzw Bazinga; \n" +
                    "12-12-2014;B4L12OAA9IIK3001;Uw storting;123,00;EUR;12-12-2014; ; ;Deposit cash; " );
        }
    }

    @Test
    public void parseFile() {
        Argenta a = new Argenta();
        List<BankStatement> bankStatements = a.parseFile(testfile);
        for (BankStatement bankStatement : bankStatements) {
            System.out.println(bankStatement.getRef());
            System.out.println(bankStatement.getAmount());
            System.out.println(bankStatement.getDate());
            System.out.println(bankStatement.getSender());
            System.out.println(bankStatement.getReceiver());
            System.out.println(bankStatement.getComment());
            System.out.println("\n");
        }
    }
}