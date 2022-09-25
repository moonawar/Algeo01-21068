import java.util.Scanner;

public class MatrixOperations {
    public Matrix readMatrix(){
        try (Scanner sc = new Scanner(System.in)) {
            int nRow, nCol;
            System.out.printf("Enter the number of rows: ");
            nRow = sc.nextInt();
            System.out.printf("Enter the number of columns: ");
            nCol = sc.nextInt();

            Matrix m = new Matrix(nRow, nCol);

            for (int i = 0; i <= m.getLastIdxRow(); i++) {
                for (int j = 0; j <= m.getLastIdxCol(); j++) {
                    m.setElmt(i, j, sc.nextFloat());
                }
            }
            
            return m;
        }
    }

    public void displayMatrix(Matrix m){
        for (int i = 0; i <= m.getLastIdxRow(); i++) {
            for (int j = 0; j <= m.getLastIdxCol(); j++) {
                System.out.printf("%.4f ", m.getElmt(i, j));
            }
            System.out.print("\n");
        }
    }
}
