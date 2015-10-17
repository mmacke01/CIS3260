
package lines.of.action;

import java.util.Scanner;

/**
 *
 * @author Mark Wilson mwilso14
 */
public class Input {
    
    public void Input(){
        
    }
    public char GetInput(){
        
        char in;
        String str;
        Scanner reader = new Scanner(System.in); // should be instance varable but not in design
        
        while(true)
            try{
               str = reader.nextLine();//take whole line
               in = str.charAt(0);// extract first char 
               
               if(Character.isLetter(in)|Character.isDigit(in)){
                   System.out.println("you entered \""+in+"\"");
                   break;// exit input loop
               }else{
                   System.out.println("input must be alphanumeric");
               }
            
            }catch(Exception e){
               System.out.println("invalid input try again");
            }

        return in;
    }
}
