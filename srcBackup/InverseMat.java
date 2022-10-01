public class InverseMat {
    private static void swapRow(Matrix m, int row1, int row2){
        for (int i = 0; i <= m.getLastIdxCol(); i++) {
            float temp = m.getElmt(row1, i);
            m.setElmt(row1, i, m.getElmt(row2, i));
            m.setElmt(row2, i, temp);
        }
    }

    private static void sortRow(Matrix m, Matrix mAug){
        for (int i = 0; i <= m.getLastIdxRow()-1; i++) {
            for (int j = 0; j <= m.getLastIdxRow()-1-i; j++) {
                int k = 0;
                while ((m.getElmt(j, k) ==  m.getElmt(j+1, k)) && (k <= m.getLastIdxCol())) {
                    k++;
                }
                
                if (m.getElmt(j, k) == 0) {
                    swapRow(m, j, j+1);
                    swapRow(mAug, j, j+1);
                }        
            }
        }
    }

    public static Matrix InverseWithRed(Matrix m){
        if (DetCofactor.determinanCofactor(m) == 0.0f || m.rowEff != m.colEff) {
            System.out.println("Matriks tidak memilki invers");
            return null;
        }

        Matrix mAug = MatrixOperations.IdentityMatrix(m.rowEff);

        sortRow(m, mAug);

        // Do Gauss Reduction for both Matrix Input and Matrix Identity
        for (int i = 1; i <= m.getLastIdxRow(); i++) {
            for (int k = i; k <= m.getLastIdxRow(); k++) {
                float ratio = m.getElmt(k, i-1) / m.getElmt(i-1, i-1);
                for (int j = 0; j <= m.getLastIdxCol(); j++) {
                    m.setElmt(k, j, m.getElmt(k, j) - ratio * m.getElmt(i-1, j));
                    mAug.setElmt(k, j, mAug.getElmt(k, j) - ratio * mAug.getElmt(i-1, j));
                }
            }
            sortRow(m, mAug);
        }

        // For each row, normalize the diagonal element to 1
        for (int i = 0; i <= m.getLastIdxRow(); i++) {
            float normalizer = 1 / m.getElmt(i, i);
            for (int k = 0; k <= m.getLastIdxCol(); k++) {
                m.setElmt(i, k, m.getElmt(i, k) * normalizer);
                mAug.setElmt(i, k, mAug.getElmt(i, k) * normalizer);
            }
        }
        
        // Jordan reduction to make the input matrix become an identity matrix
        for (int j = 1; j <= m.getLastIdxCol(); j++) {
            for (int k = 0; k <= j - 1; k++) {
                float ratio = m.getElmt(k, j) / m.getElmt(j, j);
                for (int n = 0; n <= m.getLastIdxCol(); n++) {
                    m.setElmt(k, n, m.getElmt(k, n) - ratio * m.getElmt(j, n));
                    mAug.setElmt(k, n, mAug.getElmt(k, n) - ratio * mAug.getElmt(j, n));
                } 
            } 
        }  
        
        return mAug;
    } 

    public static Matrix MatCofactor(Matrix m){        
        Matrix mOut = new Matrix(m.rowEff, m.colEff);
        for (int i = 0; i <= m.getLastIdxRow(); i++) {
            for (int j = 0; j <= m.getLastIdxCol(); j++) {
                float minorDet = determinanCofactor(ExcludeRowCol(m, i, j));
                float x;
                if (minorDet == 0) {
                    x = 0;
                } else {
                    x = (float) Math.pow(-1, i+j) * minorDet;
                }
                
                mOut.setElmt(i, j, x);
            }
        }

        return mOut;
    }

    public static float determinanCofactor(Matrix m){
        if (m.rowEff == 1) {
            return m.getElmt(0, 0);
        } else {
            float det = 0;
            for (int i = 0; i <= m.getLastIdxCol(); i++) {
                det += Math.pow(-1, i) * m.getElmt(0, i) * determinanCofactor(ExcludeRowCol(m, 0, i));
            }
            return det;
        }
    }

    public static Matrix ExcludeRowCol(Matrix m, int row, int col){
        Matrix mExclude = new Matrix(m.rowEff-1, m.colEff-1);
        int i = 0;
        int j = 0;
        for (int k = 0; k <= m.getLastIdxRow(); k++) {
            for (int l = 0; l <= m.getLastIdxCol(); l++) {
                if ((k != row) && (l != col)) {
                    mExclude.setElmt(i, j, m.getElmt(k, l));
                    j++;
                    if (j > mExclude.getLastIdxCol()) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
        return mExclude;
    }

    public static Matrix transposeMat(Matrix m){
        Matrix mTranspose = new Matrix(m.colEff, m.rowEff);
        for (int i = 0; i <= m.getLastIdxRow(); i++) {
            for (int j = 0; j <= m.getLastIdxCol(); j++) {
                mTranspose.setElmt(j, i, m.getElmt(i, j));
            }
        }
        return mTranspose;
    }

    public static Matrix multiplyMatConst(Matrix m, float c){
        Matrix mOut = new Matrix(m.rowEff, m.colEff);
        
        for (int i = 0; i <= m.getLastIdxRow(); i++) {
            for (int j = 0; j <= m.getLastIdxCol(); j++) {
                mOut.setElmt(i, j, m.getElmt(i, j)*c);
            }
        }
        return mOut;
    }

    public static Matrix InverseWithAdjoin(Matrix m){
        Matrix mOut;
        Matrix adjoin = transposeMat(MatCofactor(m));
        if (determinanCofactor(m) == 0) {
            System.out.println("Matriks tidak memilki invers");
            return null;
        } else {
            mOut = multiplyMatConst(adjoin, 1 / determinanCofactor(m));
            return mOut;
        }
    }

    
}