package fifteen_game;
import java.util.Random;
import puzzleboard.*;

public class FifteenGameK {

    private static int n;
    private static int[] array;
    private static int[] arrayM;
    private static int[][] matrix;
    private static int[] zero;

    public FifteenGameK(int n){

        this.n = n;
        array = new int[n*n];
        arrayM = new int[n*n];
        matrix = new int[n][n];
        zero = new int[2];

        FifteenGameK tavola = new FifteenGameK(this);

    }

    private FifteenGameK(FifteenGameK x){

        array = x.orderedArray();
        arrayM = x.randomArray();
        matrix = x.randomBoard();
        zero = x.coordinate(0);

        /*printer(array);
        printer(arrayM);
        printer(matrix);
        printer(zero);*/

    }

    public void game(){

        System.out.println(config());

        PuzzleBoard gui = new PuzzleBoard( this.n );

        int l = 0;
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix.length; j++) {
                gui.setNumber(i+1, j+1, arrayM[l]);
                l++;
            }
        }
        /*for (int l = 0; l < matrix.length * matrix.length; l++) {
            int i = l / matrix.length;
            int j = l % matrix.length;
            gui.setNumber(i + 1, j + 1, arrayM[l]);
        }*/

        while(!isOrdered()) {

            int k = gui.get();

            if(isMovable(k)){

                int[] coordinates = coordinate(k);

                gui.setNumber(zero[0]+1,zero[1]+1, k);
                gui.clear(coordinates[0]+1, coordinates[1]+1);
                gui.display();

                move(k);
                zero = coordinates;

            }

        }

    }

    private int[] orderedArray(){

        for(int i = 0; i < this.array.length-1; i++){
            this.array[i] = i + 1;
        }

        return array;

    }

    private int[] randomArray(){

        Random random = new Random();

        this.arrayM = this.array.clone();

        for (int i = 0; i < this.arrayM.length; i++) {

            int swap = random.nextInt(this.arrayM.length);
            int tempArray = this.arrayM[swap];
            this.arrayM[swap] = this.arrayM[i];
            this.arrayM[i] = tempArray;

        }

        return arrayM;

    }

    private int[][] randomBoard(){

        for (int i = 0; i < this.matrix.length; i++){
            for (int j = 0; j < this.matrix.length; j++){
                this.matrix[i][j] = this.arrayM[i * this.n + j];
            }
        }

        return matrix;

    }

    private boolean isOrdered(){

        for(int i = 0; i < this.array.length; i++){
            if (this.array[i] != this.arrayM[i]){
                return false;
            }
        }

        return true;

    }

    private int[] coordinate(int n){
        int[] array = new int[3];

        for(int i = 0; i < this.matrix.length; i++){
            for(int j = 0; j < this.matrix.length; j++){
                if(this.matrix[i][j] == n){
                    array = new int[]{i,j,0};
                    break;
                }
            }
        }

        for(int i = 0; i < this.arrayM.length; i++){
            if(this.arrayM[i] == n){
                array[2] = i;
            }
        }

        return array;
    }

    private boolean[] inOut(int n){

        boolean[] possibleB = new boolean[4];

        int[] target = coordinate(n);

        possibleB[0] = (target[0] == 0) ? false : true;
        possibleB[1] = (target[0] == this.n-1) ? false : true;
        possibleB[2] = (target[1] == 0) ? false : true;
        possibleB[3] = (target[1] == this.n-1) ? false : true;

        return possibleB;

    }

    private boolean isMovable(int n){

        boolean[] UpDownLeftRight = inOut(0);
        int[][] cycleDir = {{-1,0},{1,0},{0,-1},{0,+1}};

        for(int i = 0; i < 4; i++){
            if(UpDownLeftRight[i] && matrix[zero[0]+cycleDir[i][0]][zero[1]+cycleDir[i][1]] == n){
                return true;
            }
        }
        return false;
    }

    private String config(){

        String tempConfig = " ";

        for(int i = 0; i < this.arrayM.length; i++){
            tempConfig += this.arrayM[i] + " ";
        }

        return tempConfig;

    }

    private void move(int x){

        int[] number = coordinate(x);

        this.matrix[zero[0]][zero[1]] = x;
        this.matrix[number[0]][number[1]] = 0;

        this.arrayM[zero[2]] = x;
        this.arrayM[number[2]] = 0;

    }

    /*public void printer(int[] x){

        for (int i = 0; i < x.length; i++){
            System.out.print(x[i] + " ");
        }

        System.out.println();

    }

    public void printer(boolean[] x){

        for (int i = 0; i < x.length; i++){
            System.out.print(x[i] + " ");
        }

        System.out.println();

    }

    public void printer(int[][] x){

        for (int i = 0; i < x.length; i++){
            for (int j = 0; j < x.length; j++) {
                System.out.print(x[i][j] + " ");
            }
            System.out.println();
        }

    }*/

}