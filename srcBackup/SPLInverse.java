public class SPLInverse {
    public static Matrix SPLWithInverse(Matrix mA, Matrix mB, boolean b){
        Matrix mInvA = InverseMat.InverseWithRed(mA);
        if (mInvA == null || DetReduction.determinanGauss(mA) == 0.0f) {
            System.out.println("Matriks ini tidak memilki invers, sehingga SPL tidak dapat diselesaikan dengan metode ini");
            return null;
        } else {
            Matrix mX = MatrixOperations.multiplyMatrix(mInvA, mB);

            System.out.println("Solusi SPL dengan menggunakan metode inverse:");
            for (int i = 0; i <= mX.getLastIdxRow(); i++) {
                System.out.println("x" + (i+1) + " = " + mX.getElmt(i, 0));
            }
            return mX;
        }
    }
}
