package interface_package;

import java.io.Console;

public class CheckInput{

    public static String[] check_buffer(String buf){
      Console console = System.console();
      String[] res;
      String parameter = " ";
      res = buf.split(parameter);

      while (res.length != 2 || true == res[1].contentEquals(" ")){
          System.out.print("Error : please enter <type> <pseudo> : ");
          res = console.readLine().split(parameter);
      }

      return res;
    }

    public static String check_type(String buf){
      Console console = System.console();
      int valid = 0;

      while (valid == 0){

        if (buf.equals("human") || buf.equals("ia") ){
          return buf;
        }
        else{
           System.out.print("Error : please enter a valid type (human or ia) : ");
           buf = console.readLine();
           System.out.println(buf);
        }
      }
      return buf;
    }


}
