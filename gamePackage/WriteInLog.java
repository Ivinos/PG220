package gamePackage;

import java.io.File;
import java.io.FileWriter;

public class WriteInLog{

  // Create thelog.txt file
  public static void createLog(){
    String filename = "log.txt";

    try{
      File fileToCreate = new File(filename);
      fileToCreate.delete();

      // if (fileToCreate.createNewFile())
      //   System.out.println("\nFile "+filename+" created\n");
      
      // else{
      //   System.out.println("\nFile "+filename+" already exists");
      //   System.exit(0);
      // }
      
    }
    catch (Exception e){
      System.err.println(e);
    }
  }


  //Write in log.txt the string
  public static void writeBuffer(String buffer){
    try{
      String filename = "log.txt";
      FileWriter fileToWrite = new FileWriter(filename, true);

      fileToWrite.write(buffer+"\n");
      fileToWrite.close();
    }

    catch (Exception e){
      System.err.println(e);
    }
  }
}
