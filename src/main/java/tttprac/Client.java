package tttprac;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket sock = new Socket("localhost", 3000);
        System.out.println("Connected to the server on port 3000");

        InputStream is = sock.getInputStream();
        DataInputStream dis = new DataInputStream(is);

        System.out.println("Starting game...");
        String gameBoard = dis.readUTF();
        System.out.println(gameBoard);
        //let user choose if they want to be "X" or "O"
        Console cons = System.console();
        
        String player = "";
        OutputStream os = sock.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);

        while (true){
            String user = cons.readLine("Do you want to be player X or player O?: ");
            if ((user.equals("X")) || (user.equals("O"))){
                player = user;
                break;  
            } else {
                System.out.println("Invalid input");
            }
        }
        
        System.out.println("You chose " + player);
        dos.writeUTF(player);
        dos.flush();


        String playerChoice = "";
        //keep asking player for input, send to server to evaluate if theres a winner, send back result. If there's a winner, game ends
        
        String winner = "";

        while (winner.equals("")){
            while (true){
                playerChoice = cons.readLine("Your move: ");
                if ((Integer.parseInt(playerChoice) < 10) && (Integer.parseInt(playerChoice) > 0)){
                    break;
                } else {
                    System.out.println("Invalid input");
                }

            }
            // stream 1
            dos.writeUTF(playerChoice);
            dos.flush();
            // stream 2
            gameBoard = dis.readUTF();
            if (gameBoard.startsWith("Winner")){
                System.out.println(gameBoard);
                System.exit(0);
            }
        
            System.out.println(gameBoard);

        }


        sock.close();
    }
    }
    

