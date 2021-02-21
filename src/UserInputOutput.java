import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputOutput {
    int a;
    public int getint(){
        try {
            a = new UserInputOutput().inputint();
        }
        catch (InputMismatchException ex){
            System.out.println("Enter only number");
            getint();
        }
        finally {
            return a;}

    }

    public int inputint(){
        Scanner scan = new Scanner(System.in);
        int value = scan.nextInt();
        return value;
    }

}