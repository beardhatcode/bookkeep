package be.beardhatcode.bookeep.sources;

import java.io.File;
import java.util.List;

public interface ImportSource<T> {
    public List<T> parseFile(File file);

    default String check(){
        return "All ok";
    }
}
