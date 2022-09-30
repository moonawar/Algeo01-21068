import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Converter {
    public static Matrix readTxt(String filename) throws FileNotFoundException{
        int baris, kolom, j, tempASCII;
        int[] size;
        String[] elemen;
        Matrix m;
        char tempchar;
        String temp;

        size = prereadTxt(filename);

        m = new Matrix(size[0], size[1]);

        if (size[0] >= 1) {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            
            // pre read filenya biar tau ukuran matriks
            baris = 0;

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                elemen = data.split(" ");

                for (j = 0; j <= elemen.length - 1; ++j) {
                    m.setElmt(baris, j, Float.parseFloat(elemen[j]));
                }

                ++baris;
            }
            myReader.close();
        }
        
        return m;
    }

    public static int[] prereadTxt(String filename) throws FileNotFoundException{
        int baris, kolom;
        String[] elemen;
        // baris, kolom
        int[] size;
        size = new int[2];

        File myObj = new File(filename);
        Scanner myReader = new Scanner(myObj);

        baris = 0;

        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            elemen = data.split(" ");
            if (baris == 0) {
                kolom = elemen.length;
                size[1] = kolom;
            }
            ++baris;
        }

        size[0] = baris;  
        myReader.close();

        return size;
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
