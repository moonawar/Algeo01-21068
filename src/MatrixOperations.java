public class MatrixOperations {
    
    public static Matrix readMatrix(){
        /*KAMUS*/
        int nRow, nCol;
        System.out.printf("\nMasukkan jumlah baris: ");
        nRow = MainScanner.sc.nextInt();
        System.out.printf("Masukkan jumlah kolom: ");
        nCol = MainScanner.sc.nextInt();

        Matrix m = new Matrix(nRow, nCol);
        
        /*ALGORITMA*/
        System.out.printf("""
            
            Cara input elemen matriks:    
            Input elemen dengan memasukkan baris per baris. Untuk kolom, setiap elemen dipisahkan dengan spasi.    
            Untuk bicubic interpolation, matriks yang diinputkan adalah matriks 4x4. Contoh input matrix 4x4:   
            
            1 2 3 4
            5 6 7 8
            9 10 11 12    
            13 14 15 16
            
            Masukkan matriks:
            """
                );

        for (int i = 0; i <= m.getLastIdxRow(); i++) {
            for (int j = 0; j <= m.getLastIdxCol(); j++) {
                m.setElmt(i, j, MainScanner.sc.nextFloat());
            }
        }
        return m;
        
    }

    public static Matrix readSPL(){
        /*KAMUS*/
        int nRow, nCol;
        System.out.printf("\nMasukkan jumlah persamaan: ");
        nRow = MainScanner.sc.nextInt();
        System.out.printf("Masukkan jumlah variable peubah (x): ");
        nCol = MainScanner.sc.nextInt();

        Matrix m = new Matrix(nRow, nCol + 1);
        

        /*ALGORITMA*/
        for (int i = 0; i <= m.getLastIdxRow(); i++) {
            System.out.println("\nPersamaan ke-" + (i+1));
            for (int j = 0; j <= m.getLastIdxCol(); j++) {
                if (j == m.getLastIdxCol()) {
                    System.out.printf("Masukkan nilai solusi y%d: ", i+1);
                } else {
                    System.out.printf("Masukkan nilai koefisien x%d: ", j+1);
                }
                m.setElmt(i, j, MainScanner.sc.nextFloat());
            }
        }
        return m;
        
    }

    public static void displayMatrix(Matrix m){
        /*ALGORITMA*/
        for (int i = 0; i <= m.getLastIdxRow(); i++) {
            for (int j = 0; j <= m.getLastIdxCol(); j++) {
                System.out.printf("%.4f ", m.getElmt(i, j));
            }
            System.out.print("\n");
        }
    }

    public static Matrix multiplyMatrix(Matrix m1, Matrix m2){
        /*KAMUS*/
        Matrix mOut = new Matrix(m1.rowEff, m2.colEff);

        /*ALGORITMA*/
        for (int i = 0; i <= mOut.getLastIdxRow(); i++) {
            for (int j = 0; j <= mOut.getLastIdxCol(); j++) {
                float sum = 0f;
                for (int k = 0; k <= m1.getLastIdxCol(); k++)
                    sum += m1.getElmt(i, k) * m2.getElmt(k, j); 
                    
                mOut.setElmt(i, j, sum);
            } 
        } 
        
        return mOut;
    }

    public static Matrix IdentityMatrix(int size){
        /*KAMUS*/
        Matrix MIdentity = new Matrix(size, size);
        /*ALGORITMA*/
        for (int i = 0; i <= MIdentity.getLastIdxRow(); i++)
            MIdentity.setElmt(i, i, 1.0f);

        return MIdentity;
    }
}
