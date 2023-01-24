package tttprac;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) throws IOException{
        ServerSocket server = new ServerSocket(3000);
        System.out.println("Waiting for connection at port 3000");
        Socket sock = server.accept();
        System.out.println("Got a connection!");

        OutputStream os = sock.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);

        TicTacToe tttGame = new TicTacToe();
        String[] game = {"1","2","3","4","5","6","7","8","9"};
        
        String board = tttGame.drawBoard(game);
        dos.writeUTF(board);
        dos.flush();

        InputStream is = sock.getInputStream();
        DataInputStream dis = new DataInputStream(is);

        String player = dis.readUTF();
        //System.out.println(player);
        
        String comPlayer = "";
        if (player.equals("X")){
            comPlayer = "O";
        } else if (player.equals("O")) 
        {
            comPlayer = "X";
        } 

        //System.out.println("Player is " + player);
        //System.out.println("Computer is " + comPlayer);
        String winner = "";

        while (winner.equals(""))
        {
            // stream 1
            String playerChoice = dis.readUTF();
            //System.out.println(playerChoice);
            game[Integer.parseInt(playerChoice) - 1] = player;
            // assess if there's winner
            winner = tttGame.checkWinner(game);
            if (!winner.equals("")){
                break;
            }
    
            // computer to play
            Random rand = new Random();
            Integer comChoice = null;
            while (true){
                comChoice = rand.nextInt(9);
                if ((game[comChoice].equals("X")) ||(game[comChoice].equals("O"))){
                    continue;
                } else {
                    break;
                }
            }
            game[comChoice] = comPlayer; 
            
            // assess if there's winner
            board = tttGame.drawBoard(game);
            winner = tttGame.checkWinner(game);
            
            if (!winner.equals("")){
                break;
            }
            
            // stream 2
            dos.writeUTF(board);
            dos.flush();

        }
        // stream 2
        dos.writeUTF("Winner: " + winner);


        sock.close();


    }
}
