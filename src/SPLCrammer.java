public class SPLCrammer {
    public static void cramer(Matrix mat, Matrix nilai, int order){
    float detcramer = 0;
    for (int i=0; i< order; i++ ){
        detcramer = DetCofactor.DeterminanCofaktor(Subscramer(mat,nilai, order, i), order)/DetCofactor.DeterminanCofaktor(mat, order);  
        System.out.println("x" + (i + 1) + " = " + detcramer);
    }
}

    public static Matrix Subscramer(Matrix mat, Matrix nilai ,int order, int replacedcol){
        Matrix cram = new Matrix(order,order);
        for (int i=0; i<order; i++){
            for(int j=0; j<order; j++){
                 if (j != replacedcol){
                    cram.mem[i][j] = mat.getElmt(i, j);
                }
                else{
                    cram.mem[i][replacedcol]= nilai.getElmt(i, 0);
                }
            }
        }
        return cram;
    }
}
