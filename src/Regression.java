public class Regression {
    MatrixOperations matOps = new MatrixOperations();
    SPLInverse splInverse = new SPLInverse();

    public static void main(String[] args) {
        Regression regression = new Regression();
        regression.MultipleLinearRegression(1);;
    }

    public void MultipleLinearRegression(int method){
        System.out.printf("Masukkan jumlah variabel x: ");
        int n = MainScanner.sc.nextInt();
        System.out.printf("Masukkan jumlah sampel data: ");
        int m = MainScanner.sc.nextInt();
        
        Matrix mData = new Matrix(m, n+1);

        for (int i = 0; i < m; i++) {
            System.out.printf("Data sampel %d\n", i+1);
            for (int j = 0; j < n+1; j++) {
                if (j == n) {
                    System.out.printf("Masukkan nilai y-%d: ", i+1);
                    mData.setElmt(i, j, MainScanner.sc.nextFloat());   
                } else {
                    System.out.printf("Masukkan nilai x-%d%d: ", j+1, i+1);
                    mData.setElmt(i, j, MainScanner.sc.nextFloat());
                }
            }
        }

        Matrix mEq = new Matrix(n+1, n+1);
        Matrix mSol = new Matrix(n+1, 1);

        float sum;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                sum = 0;
                if (j == 0) {
                    if (i == 0) {
                        mEq.setElmt(i, j, m);
                    } else {
                        for (int k = 0; k < m; k++) {
                            sum += mData.getElmt(k, i-1);
                        }
                        mEq.setElmt(i, j, sum);
                    }
                } else {
                    if (i == 0) {
                        for (int k = 0; k < m; k++) {
                            sum += mData.getElmt(k, j-1);
                        }
                    } else {
                        for (int k = 0; k < m; k++) {
                            sum += mData.getElmt(k, i-1)*mData.getElmt(k, j-1);
                        }
                    }
                    mEq.setElmt(i, j, sum);
                }
            }
        }
        
        for (int i = 0; i <= n; i++) {
            sum = 0;
            if (i == 0) {
                for (int k = 0; k < m; k++) {
                    sum += mData.getElmt(k, n);
                }
            } else {
                for (int k = 0; k < m; k++) {
                    sum += mData.getElmt(k, i-1)*mData.getElmt(k, n);
                }
            }
            mSol.setElmt(i, 0, sum);
        }

        // Harusnya pake gauss
        Matrix mX = splInverse.SPLWithInverse(mEq, mSol);
        
        System.out.println("Persamaan regresi:");
        System.out.printf("f(x) = ");
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                System.out.printf("%.4f + ", mX.getElmt(i, 0));
            } else {
                System.out.printf("%.4fx%d", mX.getElmt(i, 0), i);
                if (i != n) {
                    System.out.printf(" + ");
                } 
            }
        }

        System.out.println("\nMasukkan data yang ingin ditaksir");
        float f_x = mX.getElmt(0, 0);
        for (int i = 0; i < n; i++) {
            System.out.printf("Masukkan nilai x%d: ", i+1);
            f_x += MainScanner.sc.nextFloat() * mX.getElmt(i+1, 0);
        }

        System.out.printf("f(x-k) = %.4f", f_x);
    }
}
