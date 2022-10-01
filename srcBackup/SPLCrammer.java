public class SPLCrammer {
    public static void cramer(final Matrix mA, final Matrix mB) {
        if (DetCofactor.determinanCofactor(mA) == 0.0f || mA.rowEff != mA.colEff) {
            System.out.println("Determinan Matriks inputnya 0 atau matriks bukan persegi, sehingga matriks tidak dapat diselesaikan dengan metode Cramer");
        }
        else {
            System.out.println("Solusi SPL dengan menggunakan metode Cramer:");
            for (int i = 0; i <= mA.getLastIdxCol(); i++) {
                System.out.println("x" + (i + 1) + " = " + DetCofactor.determinanCofactor(subsCramer(mA, mB, i)) / DetCofactor.determinanCofactor(mA));
            }
        }
    }
    
    public static Matrix subsCramer(final Matrix mA, final Matrix mB, final int n) {
        final Matrix mOut = new Matrix(mA.rowEff, mA.rowEff);
        for (int i = 0; i <= mA.getLastIdxRow(); i++) {
            for (int j = 0; j <= mA.getLastIdxCol(); j++) {
                if (j != n) {
                    mOut.setElmt(i, j, mA.getElmt(i, j));
                }
                else {
                    mOut.setElmt(i, n, mB.getElmt(i, 0));
                }
            }
        }
        return mOut;
    }
}
