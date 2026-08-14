import java.util.Random;
import java.util.Scanner;
public class NumberGuessing {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Welcome to the Number Guessing Game!");
        String playAgain ="yes";
        while (playAgain.equalsIgnoreCase("yes")){
        System.out.println("Select the difficulty level:");
        System.out.println("1. Easy (1-50, 10 attempts)");
        System.out.println("2. Medium (1-100, 7 attempts)");
        System.out.println("3. Hard (1-200, 5 attempts)");
        System.out.println("Enter your choice (1, 2, or 3):");
        int choice=sc.nextInt();
        int maxNumber=0;  
        int maxAttempt=0;
        if(choice==1){
            System.out.println("You have selected Easy mode");
            maxNumber=50;
            maxAttempt=10;
        }else if(choice==2){
            System.out.println("You have selected Medium mode");
            maxNumber=100;
            maxAttempt=7;
        }else if(choice==3){
            System.out.println("You have selected Hard mode");
            maxNumber=200;
            maxAttempt=5;
        }else{
            System.out.println("Invalid choice");
            continue;
        
        }
            int secretNumber = random.nextInt(maxNumber) + 1;
           
        int attempt=0;
        boolean guessed=false;
        while(attempt<maxAttempt){
            System.out.println("Enter your guess number");
            int guess= sc.nextInt();
            attempt++;
        
        if(guess<secretNumber){
            System.out.println("to low !" );
        }
        else if(guess>secretNumber){
            System.out.println("Too high !");

        }
        else{
            System.out.println("Correct");
            System.out.println("You guessed the number in " + attempt + " attempts");
            guessed=true;
            break;
        
    }
     System.out.println(
                        "Attempts remaining: " +
                        (maxAttempt - attempt));
            }
    if (!guessed) {

                System.out.println("\nYou Lost!");

                System.out.println("The correct number was: " +secretNumber);
            }
    System.out.println("do you want to play again ? (yes/no)");
     playAgain = sc.next();
        }
    System.out.println("Thank you for playing the Number Guessing Game!");

    sc.close();
    
}
}