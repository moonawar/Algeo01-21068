public class DetCofactor {

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

}
