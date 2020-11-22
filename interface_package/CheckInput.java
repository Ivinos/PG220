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



}