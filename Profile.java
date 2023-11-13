import java.util.Arrays;
import java.util.HashSet;

public class Profile {

    HashSet<String> movies;

    public Profile(String films) {

        String[] data = films.split(";");
        HashSet<String> temp=new HashSet<String>();
        movies.addAll(Arrays.asList(data));

  
    }
}
