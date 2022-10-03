public class SPLCrammer {
    public static Matrix swapCol(Matrix mB, int kolom, Matrix mA) {
        int i, j;
        Matrix temp = mA.copyMatrix();

        for (i = 0; i <= mA.getLastIdxRow(); ++i) {
            for (j = 0; j <= mA.getLastIdxCol(); ++j) {
                if (j == kolom) {
                    temp.setElmt(i, j, mB.getElmt(i, 0));
                } else {
                    temp.setElmt(i, j, mA.getElmt(i, j));
                }
            }
        }

        return temp;
    }

    public static Solution crammerSPL(Matrix m) {
        Matrix mA, mB, temp, tempMA;
        int i, j;
        float determinan, determinanAwal;
        boolean state = true;
        String[] listVarMat = new String[m.getLastIdxCol()];
        float[] hasil = new float[m.getLastIdxCol()];
        Solution sol = new Solution(hasil, listVarMat, state);
        // determinan awal di index m.getLastIdxCol()

        mA = Converter.augmentedToMatrix(m, true);
        mB = Converter.augmentedToMatrix(m, false);

        tempMA = mA.copyMatrix();
        determinanAwal = DetReduction.determinanGauss(tempMA);
        
        if (determinanAwal == 0) {
            sol.setState(false);
            return sol;
        } else {
            // cari determinan lainnya
            for (j = 0; j <= m.getLastIdxCol()-1; ++j) {
                temp = swapCol(mB, j, mA);
                determinan = DetReduction.determinanGauss(temp);
                determinan /= determinanAwal;
                sol.setHasil(j, determinan);
                sol.setVar(j, String.valueOf(determinan));
            }
        }

        return sol;
    }
}
