package be.beardhatcode.bookeep.sources;

import java.util.List;

public interface ImportSource<T> {
    public List<T> parseFile();

    default String check(){
        return "All ok";
    }
}
