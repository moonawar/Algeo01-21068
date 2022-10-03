public class SPLInverse {
    public static Matrix SPLWithInverse(Matrix mA, Matrix mB, boolean isOutputting){
        /*KAMUS*/
        Matrix mAinit = mA;
        Matrix mInvA = InverseMat.InverseWithRed(mA);
        /*ALGORITMA*/
        if (mInvA == null || DetReduction.determinanGauss(mAinit) == 0.0f) {
            System.out.println("Matriks ini tidak memilki invers, sehingga SPL tidak dapat diselesaikan dengan metode ini");
            return null;
        } else {
            Matrix mX = MatrixOperations.multiplyMatrix(mInvA, mB);

            if (isOutputting) {                
                System.out.println("Solusi SPL dengan menggunakan metode inverse:");
                for (int i = 0; i <= mX.getLastIdxRow(); i++) {
                    System.out.println("x" + (i+1) + " = " + mX.getElmt(i, 0));
                }
            }

            return mX;
        }
    }

    public static Solution SolutionWithInverse(Matrix mA, Matrix mB, boolean isOutputting){
        /*KAMUS*/
        Matrix mSol = SPLWithInverse(mA, mB, isOutputting);

        boolean state = true;
        String[] listVarMat = new String[mSol.getLastIdxCol()];
        float[] hasil = new float[mSol.getLastIdxCol()];
        Solution sol = new Solution(hasil, listVarMat, state);

        /*ALGORITMA*/
        if (sol == null) {
            sol.setState(false);
            return sol;
        } else {
            for (int i = 0; i <= mSol.getLastIdxRow(); i++) {
                sol.setHasil(i, mSol.getElmt(i, 0));
                sol.setVar(i, String.valueOf(mSol.getElmt(i, 0)));
            }
        }

        return sol;
    }
}
