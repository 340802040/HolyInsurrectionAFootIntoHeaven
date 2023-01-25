import java.util.*;
import java.io.*;

/**
 * Write a description of class Writer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Writer  
{
    // instance variables - replace the example below with your own
    private int x;

    public Writer() {

    }

    static void writeToFile(String fileName, ArrayList<Ally> allies) throws IOException {
        File f = new File(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(allies);
        oos.flush();
        oos.close();    
    }

    static ArrayList<Ally> readAllies(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream objinstream = new ObjectInputStream(new FileInputStream("file1.txt"));  
        return (ArrayList<Ally>)objinstream.readObject();      
    }
}
