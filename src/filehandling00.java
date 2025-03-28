import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/*
interview book stuff

the heirarchy has two main classes
inputstream, outputstream

dataInputStream - readInt, readLine, etc
BufferedInputStream - faster

File is a non stream class, used to find propertiers

FileInputStream - used to read contents
FileOutputStream - write into file (REALLY?)

Scanner - more modern datainputstrea, more flexible

bufferedstream - data's not removed from stream but from buffer, seen as more efficient

can use sequenceinputstream to combine input streams

File tem = File.createTempFile("x",".txt");
tem.deleteOnExit();

serialization - maintain object as set of bytes,
deserialization - make object from bytes
book has examples, but I think this is what's going on in controller layer of spring web

can do this by implementing serializable for class


 */
public class filehandling00 {
    public void createFile00(){
        // Creating the File also
        // Handling Exception
        try {
            File Obj = new File("myfile.txt");

            // Creating File
            if (Obj.createNewFile()) {
                System.out.println("File created: " + Obj.getName());
                FileWriter Writer = new FileWriter("myfile.txt");

                // Writing File
                Writer.write("This is supposed to be better than Python context writers?");
                Writer.close();

                System.out.println("Successfully written.");
            }
            else {
                System.out.println("File already exists.");
            }
        }

        // Exception Thrown
        catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
    }
    public void readFile00(){
        // Reading File also
        // Handling Exception
        try {
            File Obj = new File("myfile.txt");
            Scanner Reader = new Scanner(Obj);

            // Traversing File Data
            while (Reader.hasNextLine()) {
                String data = Reader.nextLine();
                System.out.println(data);
            }

            Reader.close();
        }

        // Exception Cases
        catch (FileNotFoundException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
    }
    public void deleteFile00(){
        File Obj = new File("myfile.txt");

        // Deleting File
        if (Obj.delete()) {
            System.out.println("The deleted file is : " + Obj.getName());
        }
        else {
            System.out.println(
                    "Failed in deleting the file.");
        }
    }
    public static void main(String args[]){
        filehandling00 f = new filehandling00();
        f.deleteFile00();
        f.createFile00();
        f.readFile00();
        f.deleteFile00();
        f.readFile00();
    }
}
