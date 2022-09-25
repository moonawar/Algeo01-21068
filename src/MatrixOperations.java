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

    public static void main(String[] args) {
        
    }
    

    public Matrix IdentityMatrix(int size){
        Matrix MIdentity = new Matrix(size, size);
        for (int i = 0; i <= MIdentity.getLastIdxRow(); i++)
            MIdentity.setElmt(i, i, 1.0f);

        return MIdentity;
    }

    public Matrix Inverse(Matrix m){
        if (gauss(m) == 0) {
            System.out.println("Inverse matrix doesn't exist");
            return m;
        } else {
            Matrix mInv = IdentityMatrix(m.rowEff);
            for (int i = 1; i <= m.getLastIdxRow(); i++) {
                for (int k = 0; k <= m.getLastIdxRow(); k++) {
                    float ratio = m.getElmt(k, i-1) / m.getElmt(i-1, i-1);
                    for (int j = 0; j <= m.getLastIdxCol(); j++) {
                        m.setElmt(i, j, m.getElmt(k, j) - ratio * m.getElmt(i-1, j));
                        mInv.setElmt(i, j, mInv.getElmt(k, j) - ratio * mInv.getElmt(i-1, j));
                    }
                }
            }
    
            for (int i = 0; i <= m.getLastIdxRow(); i++) {
                float normalizer = 1 / m.getElmt(i, i);
                for (int k = 0; k <= m.getLastIdxCol(); k++) {
                    m.setElmt(i, k, m.getElmt(i, k) * normalizer);
                    mInv.setElmt(i, k, mInv.getElmt(i, k) * normalizer);
                }
            }
    
            for (int j = 1; j <= m.getLastIdxCol(); j++) {
                for (int k = 0; k <= j - 1; k++) {
                    float ratio = m.getElmt(k, j) / m.getElmt(j, j);
                    for (int n = 0; n <= m.getLastIdxCol(); n++) {
                        m.setElmt(k, n, m.getElmt(k, n) - ratio * m.getElmt(j, n));
                        mInv.setElmt(k, n, mInv.getElmt(k, n) - ratio * mInv.getElmt(j, n));
                    } 
                    } 
            }  
            
            return mInv;
        }


    }

    public Matrix multiplyMatrix(Matrix m1, Matrix m2){
        Matrix mOut = new Matrix(m1.rowEff, m2.colEff);

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

    public void SolveSPLWithInverse(Matrix mP, Matrix mSol) {
        mP = Inverse(mP);
        mSol = multiplyMatrix(mP, mSol);

        if (mP != null) {
            for (int i = 0; i <= mSol.getLastIdxRow(); i++) {
                System.out.printf("x-%d = %.4f\n", i+1, mSol.getElmt(i, 0));
            } 
        }
      }

      static boolean segi3Bawah(Matrix m) {
        int i, j;
    
        if (m.getLastIdxRow() > 1) {
            for (i = 1; i <= m.getLastIdxRow(); i++) {
                for (j = 0; j < i; j++) {
                    if (m.getElmt(i, j) != 0) {
                        return false;
                    }
                }
            }
        } else if (m.getLastIdxRow() == 1) {
            return true;
        } else {
            return false;
        }
    
        return true;
    }

    static boolean segi3Atas(Matrix m) {
        int i, j;
    
        if (m.getLastIdxRow() >= 1) {
            for (i = 0; i <= m.getLastIdxRow()-1; i++) {
                for (j = i+1; j < m.getLastIdxCol(); j++) {
                    if (m.getElmt(i, j) != 0) {
                        return false;
                    }
                }
            }
        } else if (m.getLastIdxRow() == 1) {
            return true;
        } else {
            return false;
        }
    
        return true;
    }

    static float gauss(Matrix m) {
        int i, j, k;
        float ratio, determinan;
    
        if (!segi3Bawah(m)) {
            for (i = 0; i <= m.getLastIdxRow(); i++) {
                for (j = 0; j <= m.getLastIdxCol(); j++) {
                    if (j>i) {
                        ratio = (float) m.getElmt(i, j) / m.getElmt(i, i);
                        for (k = 0; k <= m.getLastIdxRow(); k++) {
                            m.setElmt(j, k, m.getElmt(j, k) - (m.getElmt(i, k) * ratio));
                        }
                    } 
                }
            }
        }
    
        determinan = 1;
        for (i = 0; i <= m.getLastIdxRow(); i++) {
            determinan *= m.getElmt(i, i);
        }
    
        return determinan;
    }

    static float gaussJordan(Matrix m) {
        int i, j, k;
        float ratio, determinan;
    
        if (!segi3Bawah(m)) {
            for (i = 0; i <= m.getLastIdxRow(); i++) {
                for (j = 0; j <= m.getLastIdxCol(); j++) {
                    if (j>i) {
                        ratio = (float)m.getElmt(i, j) / m.getElmt(i, i);
                        for (k = 0; k <= m.getLastIdxRow(); k++) {
                            m.setElmt(j, k, m.getElmt(j, k) - (m.getElmt(i, k) * ratio));
                        }
                    } 
                }
            }
        }
    
        if (!segi3Atas(m)) {
            for (i = m.getLastIdxRow(); i >= 0; i--) {
                for (j = m.getLastIdxCol(); j >= 0; j--) {
                    if (i != j) {
                        ratio = (float)m.getElmt(i, j) / m.getElmt(i, i);
                        for (k = m.getLastIdxCol(); k >= 0; k--) {
                            m.setElmt(j, k, m.getElmt(j, k) - (m.getElmt(i, k) * ratio));
                        }
                    }
                }
            }
        }
    
        determinan = 1;
        for (i = 0; i <= m.getLastIdxRow(); i++) {
            determinan *= m.getElmt(i, i);
        }
    
        return determinan;
    }
}
