import java.util.*;

public class Main {
    static List<Integer> playerPosition = new ArrayList<Integer>();
    static List<Integer> cpuPosition = new ArrayList<Integer>();
    public static String player = "Player";
    static String winner = "Saikumar";

    public static void main(String[] args) {
        char[][] gameBorad = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};
        printGameBorad(gameBorad);

        while (true) {
            System.out.println("Enter the Placement from (1-9)");
            Scanner scanner = new Scanner(System.in);
            int playerPos = scanner.nextInt();
            while (playerPosition.contains(playerPos) || cpuPosition.contains(playerPos)) {
                System.out.println("Enter unoccupied position");
                playerPos = scanner.nextInt();
            }
            placePiece(gameBorad, playerPos, player);
            printGameBorad(gameBorad);
            winner = checkWinner();
            if(winner.equals("Congratulations player wins") || winner.equals("Sorry!! CPU wins better luck next time") || winner.equals("CAT")){
                System.out.println(winner);
                break;
            }
            Random random = new Random();
            int cpuPos = random.nextInt(9) + 1;
            System.out.println("CPU position is " +cpuPos);
            while (playerPosition.contains(cpuPos) || cpuPosition.contains(cpuPos)) {
                System.out.println("Enter unoccupied position");
                cpuPos = random.nextInt(9) + 1;
            }
            placePiece(gameBorad, cpuPos, "cpu");
            printGameBorad(gameBorad);
            winner = checkWinner();
            if(winner.equals("Congratulations player wins") || winner.equals("Sorry!! CPU wins better luck next time") || winner.equals("CAT")){
                System.out.println(winner);
                break;
            }
        }
    }

    public static void printGameBorad(char[][] gameBorad) {
        for (char[] row : gameBorad) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int pos, String user) {
        char symbol = ' ';
        if (user.equals("Player")) {
            symbol = 'x';
            playerPosition.add(pos);
        }
        else if (user.equals("cpu")) {
            symbol = 'o';
            cpuPosition.add(pos);
        }
        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + pos);
        }
    }

    public static String checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List topCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List botCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        List<List> winner = new ArrayList<List>();
        winner.add(topRow);
        winner.add(midRow);
        winner.add(botRow);
        winner.add(topCol);
        winner.add(midCol);
        winner.add(botCol);
        winner.add(cross1);
        winner.add(cross2);

        for(List l : winner) {
            if (playerPosition.containsAll(l)) {
                return "Congratulations player wins";
            }
            else if (cpuPosition.containsAll(l))
                return "Sorry!! CPU wins better luck next time";
            else if(playerPosition.size() + cpuPosition.size() == 9){
                return "CAT";
            }
        }
        return " ";
    }

}
