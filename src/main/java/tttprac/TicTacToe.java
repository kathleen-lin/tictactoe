package tttprac;

public class TicTacToe {
    public String drawBoard(String[] game){

        String board = " " + game[0] + " | " + game[1] + " | " + game[2] + " \n" + "___|___|___\n" + " " + game[3] + " | " + game[4] + " | " + game[5] + " \n" + "___|___|___\n" + " " + game[6] + " | " + game[7] + " | " + game[8] + " \n" + "   |   |   ";

        return board;

    }

    public String checkWinner(String[] game){
        String winner = "";
        String line = "";
        int i = 0;
        while (i < 8){

            switch (i){
                case 0: 
                    line = game[0] + game[1] + game[2];
                    break;
                case 1: 
                    line = game[3] + game[4] + game[5];
                    break;
                case 2: 
                    line = game[6] + game[7] + game[8];
                    break;
                case 3: 
                    line = game[0] + game[3] + game[6];
                    break;
                case 4: 
                    line = game[1] + game[4] + game[7];
                    break;
                case 5: 
                    line = game[2] + game[5] + game[8];
                    break;
                case 6: 
                    line = game[0] + game[4] + game[8];
                    break;
                case 7: 
                    line = game[2] + game[4] + game[6];
                    break;
            } i++;

            if (line.equals("XXX")){
                winner = "X";
                break;
            } else if (line.equals("OOO")){
                winner = "O";
                break;
            } else {
                int count = 0;
                for (int a = 0; a < game.length; a++){
                    if (game[a].equals(String.valueOf(a+1))){
                        count++;
                    }
                    else{
                        continue;
                    }
                }
                //case 1: only 1 number left in the game = draw
                if (count < 2){
                    winner = "draw";
                } //case 2: game haven't end 
            }
        }
        return winner;
    }
}