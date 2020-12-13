package gamePackage;

import java.io.File;
import java.io.FileWriter;

public class WriteInLog{

  // Création du fichier log.txt
  public static void createLog(){
    String filename = "log.txt";

    try{
      File fileToCreate = new File(filename);
      fileToCreate.delete();      
    }
    catch (Exception e){
      System.err.println(e);
    }
  }


  // Ecriture dans log.txt la chaîne "buffer"
  public static void writeBuffer(String buffer){
    String filename = "log.txt";
    try{
      FileWriter fileToWrite = new FileWriter(filename, true);
      fileToWrite.write(buffer+"\n");
      fileToWrite.close();
    }
    catch (Exception e){
      System.err.println(e);
    }
  }
}
