package be.beardhatcode.bookeep.sources;

import be.beardhatcode.bookeep.BankStatement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

/**
 * ISO 8859-1
 */
public class Argenta implements ImportSource<BankStatement> {
    private static final SimpleDateFormat dateFmt = new SimpleDateFormat("dd-MM-yyyy") {{
        setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
    }};

    private static final DecimalFormat moneyFmt = new DecimalFormat(){{
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        setDecimalFormatSymbols(symbols);
    }};

    private String me; //TODO remove and clean

    @Override
    public List<BankStatement> parseFile(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            this.me = reader.readLine().split(";")[1];
            return reader.lines().skip(1).map(this::parseLine).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private <R> BankStatement parseLine(String line) {
        String[] parts = line.split(";", 9);

        String ref = parts[1];

        Double amount = null;
        try {
            amount = Argenta.moneyFmt.parse(parts[3]).doubleValue();
        } catch (ParseException e) {
            throw new IllegalArgumentException("Amount on 3th field is not parsable");
        }

        String sender,receiver; //TODO better
        if(amount < 0){
            sender = this.me + " -- me";
            receiver = parts[6] + " -- "+ parts[7];
        }
        else
        {
            receiver = this.me + " -- me";
            sender = parts[6] + " -- "+ parts[7];
        }

        Date moment;
        try {
            moment = Argenta.dateFmt.parse(parts[5]);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Date on 5th field is not parsable");
        }

        String comment = parts[2] +"\n"+ parts[8]; //TODO replace ;
        return new BankStatement(null, ref, amount, sender, receiver, comment, moment, null, line);
    }

    /*
     * 0 Valutadatum               26-09-2017
     * 1 Ref. v/d verrichting      B7I26Z20K70000BE
     * 2 Beschrijving              "Aankoop brandstof Bancontact" , "Uw storting", ...
     * 3 Bedrag v/d verrichting    "98,00"
     * 4 Munt                      EUR
     * 5 Datum v. verrichting      01-02-2016
     * 6 Rekening tegenpartij      "PL95 2490 0005 0000 4001 0019 9234"
     * 7 Naam v/d tegenpartij :    "vzw Boombal"
     * 8 Mededeling 1 :            String
     * 9 Mededeling 2 :            String
     *
     * We split on 9 fields -> (last 2 are moerged)
     */
}
