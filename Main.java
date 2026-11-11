import java.io.*;
import java.nio.file.*;
import java.util.*;
public class Main{
    static void main() {
        Scanner sc = new Scanner(System.in);
        //asks for ID #
        System.out.print("This Software will create files in your device within a folder named \"Local Invoker\",\n By continuing you confirm understand this.\nAdditionally, you cant use this software twice, if you would like to use it a second time,\nGo into the bat file and edit the run (filename) to run your new exe.\n");
        System.out.println("Enter Student ID (Without the S)");
        int ID = sc.nextInt();
        //makes sure ID is valid
        if(ID<11000000&&ID>10000000){
            System.out.println("ID Confirmed.");
            String Path = "C:\\Users\\S"+ID+"\\Desktop\\";
            CreateFolder(Path,"LocalInvoker");//Creates Directory for install location
            Path+="LocalInvoker";
            CreateFile(Path,"invoke.bat");
            System.out.println("Please now put your exe file into the file \"Local Invoker\" and type in its name (including the .exe) into the terminal.\n");
            String exeName = sc.nextLine();
            WriteInvoke(Path,exeName);
            System.out.println("READY! run the bat file to run the exe!");
        }
        else{
            System.out.println("ID Invalid.");
        }
        sc.close();
    }
    public static void CreateFolder(String path,String name){
        //location thingy
        String folderPath = path + File.separator + name;
        File dir = new File(folderPath);
        // mkdirs() creates the folder and any necessary parent directories if they don't exist
        boolean created = dir.mkdirs();
        if (created) {
            System.out.println("Folder created successfully: " + dir.getAbsolutePath());
        } else if (dir.exists()) {
            System.out.println("Folder already exists: " + dir.getAbsolutePath());
        } else {
            System.out.println("Failed to create folder.");
        }
    }
    public static void CreateFile(String folderPath,String fileName) {
        // 2. Pass both the folder path and file name into the File constructor
        File file = new File(folderPath, fileName);

        try {
            if (file.createNewFile()) {
                System.out.println("File created successfully at: " + file.getAbsolutePath());
            }else {
                System.out.println("File already exists at this location.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
        }
    }

    public static void WriteInvoke(String filePath,String filename) {
        String batchCommands = "@echo off\n" +
                "set __COMPAT_LAYER=RunAsInvoker" +
                "start \""+filePath+"\\"+filename+"\"" ;

        try {
            // OPTION 1: APPEND text to the end of the existing file
            Files.write(
                    Paths.get(filePath),
                    batchCommands.getBytes(),
                    StandardOpenOption.APPEND
            );
            System.out.println("Text successfully appended to the .bat file.");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }

    }
}
