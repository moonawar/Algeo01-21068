import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Converter {
    public static Matrix readTxt(String filename) throws FileNotFoundException{
        // note jika membaca file untuk SPL, maka matrix yang dibaca akan disalin dalam bentuk augmented

        // pre-read data agar tahu jumlah baris dan kolomnya yang pasti
        int rows = 0;
        int columns = 0;

        File file = new File(filename);
        Scanner scan = new Scanner(file);

        while (scan.hasNextLine()) {
            ++rows;
            Scanner colReader = new Scanner(scan.nextLine());
            while(colReader.hasNextFloat()) {
                ++columns;
            }
        }
        scan.close();

        Matrix m = new Matrix(rows, columns);

        //read data
        scan = new Scanner(file);
        for (int i = 0; i <= m.getLastIdxRow(); ++i) {
            for (int j = 0; j <= m.getLastIdxCol(); ++j) {
                if (scan.hasNextFloat()) {
                    m.setElmt(i, j, scan.nextFloat());
                }
            }
        }
        scan.close();

        return m;
    }

    public Matrix matrixToAugmented (Matrix a, Matrix b) {
        Matrix m = new Matrix(a.rowEff, a.colEff + 1);
        for (int i = 0; i <= a.getLastIdxRow(); ++i) {
            for (int j = 0; j <= a.getLastIdxCol() + 1; ++j) {
                if (j == a.getLastIdxCol() + 1) {
                    m.setElmt(i, j, b.getElmt(i, 0));
                } else {
                    m.setElmt(i, j, a.getElmt(i, j));
                }
            }
        }
        return m;
    }

    public Matrix augmentedToMatrix (Matrix m, boolean A) {
        // Ax = B
        // if (A), maka fungsi mengembalikan A
        // if (!A) maka fungsi mengembalikan B

        if (A) {
            Matrix m1 = new Matrix(m.rowEff, m.colEff - 1);
            for (int i = 0; i <= m1.getLastIdxRow(); ++i) {
                for (int j = 0; j <= m1.getLastIdxCol(); ++j) {
                    if (j == m1.getLastIdxCol()) {
                        m1.setElmt(i, j, m.getElmt(i, 0));
                    } else {
                        m1.setElmt(i, j, m.getElmt(i, j));
                    }
                }
            }
            return m1;
        } else {
            Matrix m1 = new Matrix(m.rowEff, 1);
            for (int i = 0; i <= m1.getLastIdxRow(); ++i) {
                m1.setElmt(i, 0, m.getElmt(i, m.getLastIdxCol()));
            }
            return m1;
        }
    }
}
