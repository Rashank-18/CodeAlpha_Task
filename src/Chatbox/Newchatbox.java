package Chatbox;
import java.util.Scanner;

public class Newchatbox {

    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            String[] questions = {
                    "How to reset my password?",
                    "How to contact support for help?",
                    "How to see the content?",
                    "How to reset my username?"
            };

            String[] answers = {
                    "Go to settings and click reset password.",
                    "Email us at support@example.com for any problem",
                    "Click on the home button",
                    "Go to settings and click reset password."


            };



            System.out.print("Ask the question: ");
            String input = sc.nextLine().toLowerCase();

            boolean found = false;
            for (int i = 0; i < questions.length; i++) {

                if (input.contains(questions[i].toLowerCase().split(" ")[2])) {
                    System.out.println("Bot: " + answers[i]);
                    found = true;
                    break;
                }
            }

            if (!found) System.out.println("Bot: Sorry, I do not understood.");
    }

}
