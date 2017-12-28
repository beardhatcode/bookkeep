package be.beardhatcode.bookeep;

import be.beardhatcode.bookeep.dbo.InvoiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@ComponentScan
public class GreetingController {

    private static final String template = "Hello, %s this is cool!";
    private final AtomicLong counter = new AtomicLong();

    @Value("aoa ${my.secret}")
    private String name;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name",required=false) String name) {
        if(name == null){
            name = this.name;
        }
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }


    @Autowired
    private InvoiceDAO t;

    @RequestMapping("/dao")
    public Invoice[] dao(@RequestParam(value="id",required = false) String id) throws SQLException {
        List<Invoice> theName;
        if (id == null) {
            theName = this.t.getAll();
        }
        else
        {
            try{
                long[] a = Arrays.stream(id.split(",")).mapToLong(Long::parseLong).filter(x->x>0).toArray();
                theName = this.t.getbyId(a);
            }
            catch (NumberFormatException e){
                theName = new ArrayList<>();
            }

        }
        Invoice[] r  = theName.toArray(new Invoice[0]);;
        return r;
    }


}
