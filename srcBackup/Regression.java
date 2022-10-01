public class Regression {
    public static void MultipleLinearRegression(Matrix mData, int m, int n){
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

        Matrix mX = SPLInverse.SPLWithInverse(mEq, mSol, false);
        
        System.out.println("\nPersamaan regresi:");
        System.out.printf("f(x) = ");
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                System.out.printf("%.4f + ", mX.getElmt(i, 0));
            } else {
                System.out.printf("%.4fx%d\n", mX.getElmt(i, 0), i);
                if (i != n) {
                    System.out.printf(" + ");
                } 
            }
        }

        boolean isInputting = true;

        while (isInputting) {
            System.out.println("\nMasukkan data yang ingin ditaksir");
            float f_x = mX.getElmt(0, 0);
            for (int i = 0; i < n; i++) {
                System.out.printf("Masukkan nilai x%d: ", i+1);
                f_x += MainScanner.sc.nextFloat() * mX.getElmt(i+1, 0);
            }
    
            System.out.printf("f_x(k) = %.4f\n", f_x);

            System.out.printf("\nApakah anda ingin menaksir data lain? (y/n): ");
            String input = MainScanner.sc.next();

            while (!input.equals("y") && !input.equals("n")) {
                System.out.printf("Input tidak valid. Masukkan kembali: ");
                input = MainScanner.sc.next();
            }
            if (input.equals("n")) {
                isInputting = false;
            } 
        }

    }

    public static void ReadRegressionData() {
        System.out.printf("Masukkan jumlah variabel x: ", new Object[0]);
        int n = MainScanner.sc.nextInt();
        System.out.printf("Masukkan jumlah sampel data: ", new Object[0]);
        int m = MainScanner.sc.nextInt();

        Matrix mInput = new Matrix(m, n + 1);
        for (int i = 0; i < m; i++) {
            System.out.printf("Data sampel %d\n", i + 1);
            for (int j = 0; j < n + 1; j++) {
                if (j == m) {
                    System.out.printf("Masukkan nilai y-%d: ", i + 1);
                    mInput.setElmt(i, j, MainScanner.sc.nextFloat());
                }
                else {
                    System.out.printf("Masukkan nilai x-%d%d: ", j + 1, i + 1);
                    mInput.setElmt(i, j, MainScanner.sc.nextFloat());
                }
            }
        }
        MultipleLinearRegression(mInput, m, n);
    }
}
