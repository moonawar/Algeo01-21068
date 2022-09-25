public class DetReduction{
    public float determinan(Matrix m) {
        // prekondisi : matriks sudah dalam bentuk segitiga bawah/atas atau keduanya
        float determinan = 1;
        for (int i = 0; i <= m.getLastIdxRow(); ++i) {
            determinan *= m.getElmt(i, i);
        }

        return determinan;
    }
}