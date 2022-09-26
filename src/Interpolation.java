public class Interpolation {
    SPLInverse splInverse = new SPLInverse();
    InverseMat inverse = new InverseMat();
    static MatrixOperations matOps = new MatrixOperations();
    

    public static void main(String[] args) {
        Interpolation interpolation = new Interpolation();
        Matrix mat = matOps.readMatrix();
        interpolation.BicubicInterpolation(mat);
    }

    public void PolinomInterpolation(int method){
        // int method will be used for choosing weather input is from file or from keyboard
        // for now, input will only be from keyboard
        

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

        Matrix mA = new Matrix(n, n);
        Matrix mB = new Matrix(n, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mA.setElmt(i, j, (float) Math.pow(mData.getElmt(i, 0), j));
            }
            mB.setElmt(i, 0, mData.getElmt(i, 1));
        }
        Matrix mX = splInverse.SPLWithInverse(mA, mB);

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

    public void BicubicInterpolation(Matrix m){
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

        Matrix XInverse = inverse.Inverse(X);
        Matrix a = matOps.multiplyMatrix(XInverse, y);

        row = 0;
        float f_xy = 0;
        System.out.printf("Masukkan nilai x: ");
        float inX = MainScanner.sc.nextFloat();
        System.out.printf("Masukkan nilai y: ");
        float inY = MainScanner.sc.nextFloat();

        for (int j = 0; j <= 3; j++) {
            for (int i = 0; i <= 3; i++) {
                f_xy += a.getElmt(row, 0) * (float) Math.pow(inX, i) * (float) Math.pow(inY, j);
                row++;
            }
        }

        System.out.printf("f(%.4f, %.4f) = %.4f\n", inX, inY, f_xy);
    }
}