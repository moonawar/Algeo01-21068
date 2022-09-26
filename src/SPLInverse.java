public class SPLInverse {
    InverseMat inv = new InverseMat(); 
    MatrixOperations matOps = new MatrixOperations();

    public Matrix SPLWithInverse(Matrix mA, Matrix mB){
        Matrix mInvA = inv.Inverse(mA);
        if (mInvA == null) {
            System.out.println("This matrix doesn't have an inverse matrix, thus the system of linear equations cannot be solved with this method");
            return null;
        } else {
            Matrix mX = matOps.multiplyMatrix(mInvA, mB);

            System.out.println("Solution of SPL with Inverse Matrix:");
            for (int i = 0; i <= mX.getLastIdxRow(); i++) {
                System.out.println("x" + (i+1) + " = " + mX.getElmt(i, 0));
            }
            return mX;
        }
    }
}
