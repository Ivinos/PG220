package interfacePackage;

import java.io.File;
import java.io.FileWriter;

public class WriteInLog{

  //Write in log.txt the string
  public static void writeBuffer(String buffer){
    try{
      String filename = "log.txt";
      FileWriter fileToWrite = new FileWriter(filename, true);

      fileToWrite.write("\n"+buffer);
      fileToWrite.close();
    }

    catch (Exception e){
      System.err.println(e);
    }
  }
}
