public class DetCofactor {
    public double DetCofaktor(Matriks M) {
        /* Prekondisi: M bujur sangkar */
        /* Menghitung nilai determinan M */
        double det;
    
        if ((M.Baris == 1) && (M.Kolom == 1)) // Basis 1x1
            det = M.Mat[0][0];
        else { // Rekurens nxn
            det = 0;
            for (int i = getFirstIdxBrs(M); i <= getLastIdxBrs(M); i++)
                det += M.Mat[i][getFirstIdxKol(M)] * Cofaktor(M, i, getFirstIdxKol(M));
        }
    
        return det;
    }
    
    private double Cofaktor(Matriks M, int i, int j) {
        return DetCofaktor(Minor(M, i, j)) * (((i + j) % 2 == 0) ? 1 : -1);
    }
    
    /* ********** MANIPULASI MATRIKS ********** */
    private Matriks Minor(Matriks M, int i, int j) {
        // Minor M(i,j) dari matriks M
        Matriks Minor = new Matriks(M.Baris - 1, M.Kolom - 1);
        int iMi, jMi, iM, jM;
        iMi = getFirstIdxBrs(Minor);
        for (iM = getFirstIdxBrs(M); iM <= getLastIdxBrs(M); iM++)
            if (iM != i) {
                jMi = getFirstIdxKol(Minor);
                for (jM = getFirstIdxKol(M); jM <= getLastIdxKol(M); jM++)
                    if (jM != j) {
                        Minor.Mat[iMi][jMi] = M.Mat[iM][jM];
                        jMi++;
                    }
                iMi++;
            }
        return Minor;
    }
}
