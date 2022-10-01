public class Interpolation {
    public static void PolinomInterpolation(Matrix mData){
        Matrix mA = new Matrix(mData.rowEff, mData.rowEff);
        Matrix mB = new Matrix(mData.rowEff, 1);

        for (int i = 0; i < mData.rowEff; i++) {
            for (int j = 0; j < mData.rowEff; j++) {
                mA.setElmt(i, j, (float) Math.pow(mData.getElmt(i, 0), j));
            }
            mB.setElmt(i, 0, mData.getElmt(i, 1));
        }
        Matrix mX = SPLInverse.SPLWithInverse(mA, mB);

        System.out.printf("Masukkan x yang ingin ditaksir dengan interpolasi polinom: ");
        float e = MainScanner.sc.nextFloat();

        System.out.printf("Hasil interpolasi polinom \nf(x) = ");
        for (int i = mX.getLastIdxRow(); i >= 0; i--) {
            if (i == 0) {
                System.out.printf("%.4f\n", mX.getElmt(i, 0));
            } else if (i == 1){
                System.out.printf("%.4fx + ", mX.getElmt(i, 0));
            } else {
                System.out.printf("%.4fx^%d + ", mX.getElmt(i, 0), i);
            }
        }

        System.out.printf("f(%.4f) = ", e);
        float sum = 0f;
        for (int i = mX.getLastIdxRow(); i >= 0; i--) {
            sum += mX.getElmt(i, 0) * (float) Math.pow(e, i);
            if (i == 0) {
                System.out.printf("%.4f", mX.getElmt(i, 0) * (float) Math.pow(e, i));
            } else {
                System.out.printf("%.4f + ", mX.getElmt(i, 0) * (float) Math.pow(e, i));
            }
        }
        System.out.printf(" = %.4f\n", sum);
    }


    public static Matrix ReadInterpolationData() {
        System.out.printf("Masukkan jumlah data poin: ");
        int n = MainScanner.sc.nextInt();

        Matrix mData = new Matrix(n, 2);

        System.out.println("Masukkan masing-masing data poin format \"x y\":");
        for (int i = 0; i < n; i++) {
            float x = MainScanner.sc.nextFloat();
            float y = MainScanner.sc.nextFloat();

            mData.setElmt(i, 0, x);
            mData.setElmt(i, 1, y);
        }

        return mData;
    }

    public static void BicubicInterpolation(Matrix m, float inX, float inY){
        Matrix X = new Matrix(16, 16);
        int row = 0;
        for (int y = -1; y <= 2; y++) {
            for (int x = -1; x <= 2; x++) {
                int col = 0;
                for (int j = 0; j <= 3; j++) {
                    for (int i = 0; i <= 3; i++) {
                        X.setElmt(row, col, (float) Math.pow(x, i) * (float) Math.pow(y, j));
                        col++; 
                    }
                }
                row++;
            }
        }

        Matrix y = new Matrix(16, 1);
        row = 0;
        for (int j = 0; j <= m.getLastIdxCol(); j++) {
            for (int i = 0; i <= m.getLastIdxRow(); i++) {
                y.setElmt(row, 0, m.getElmt(i, j));
                row++;
            }
        }

        Matrix XInverse = InverseMat.InverseWithRed(X);
        Matrix a = MatrixOperations.multiplyMatrix(XInverse, y);

        row = 0;
        float f_xy = 0;

        for (int j = 0; j <= 3; j++) {
            for (int i = 0; i <= 3; i++) {
                f_xy += a.getElmt(row, 0) * (float) Math.pow(inX, i) * (float) Math.pow(inY, j);
                row++;
            }
        }

        System.out.printf("f(%.4f, %.4f) = %.4f\n", inX, inY, f_xy);
    }

    public static Matrix ReadBicubicMatrix() {
        Matrix m = new Matrix(5, 4);
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
        System.out.printf("Masukkan nilai x: ");
        m.setElmt(4, 0, MainScanner.sc.nextFloat());
        System.out.printf("Masukkan nilai y: ");
        m.setElmt(4, 1, MainScanner.sc.nextFloat());

        return m;
    }
}