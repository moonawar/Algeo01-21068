public class DetReduction{
    public static float determinanGauss(Matrix m) {
        // mencari determinan dengan metode gauss
        // prekondisi : matriks persegi
        
        /*KAMUS*/
        int i, j, k, temp, indent, swapCounter;
        float pembagi, determinan;

        // urutkan baris berdasarkan jumlah 0 sebelum diagonal
        int[] jumlah0 = new int[m.getLastIdxRow() + 1];
        
        /*ALGORITMA*/
        for (i = 0; i <= m.getLastIdxRow(); ++i) {
            jumlah0[i] = 0;
            j = 0;
            while (j <= m.getLastIdxCol() && m.getElmt(i, j) == 0) {
                jumlah0[i]++;
                j++;
            }
        }

        swapCounter = 0;
        // tukar baris berdasarkan urutan jumlah 0
        for (i = 0; i <= m.getLastIdxRow(); ++i) {
            for (j = 0; j <= m.getLastIdxRow() - 1; ++j) {
                if (jumlah0[j] > jumlah0[j + 1]) {
                    SPLGauss.swap(m, j, j + 1);
                    temp = jumlah0[j];
                    jumlah0[j] = jumlah0[j + 1];
                    jumlah0[j + 1] = temp;
                    swapCounter++;
                }
            }
        }

        // proses reduksi baris
        indent = 0;
        
        for (i = 0; i <= m.getLastIdxRow(); ++i) {
            // mencari di kolom berapa ada elemen!=0 karena bisa jadi elemen pada kolom pertama bernilai 0
            // karena sudah diurutkan berdasarkan jumlah 0, maka indent hanya perlu dispesifikasikan sekali
            while (i + indent <= m.getLastIdxCol() && m.getElmt(i, i + indent) == 0) {
                indent++;
            }

            if (i + indent <= m.getLastIdxCol()) {
                // pengurangan
                for (j = i + 1; j <= m.getLastIdxRow(); ++j) {
                    if (m.getElmt(j, i + indent) != 0) {
                        pembagi = m.getElmt(j, i + indent)/m.getElmt(i, i + indent);
                        for (k = 0; k <= m.getLastIdxCol(); ++k) {
                            m.setElmt(j, k, m.getElmt(j, k) - (m.getElmt(i, k) * pembagi));
                        }
                    }
                }
            }
        }

        // jika hasil matriksnya ada elemen -0.0
        SPLGauss.rapihkan(m);

        // menghitung determinan
        // asumsikan determinan awal matriks = 1
        determinan = 1;

        // untuk menghitung determinan maka kita hanya perlu mengalikan elemen pada diagonal utamanya (dengan syarat sudah terbentuk segi3bawah)
        for (i = 0; i <= m.getLastIdxRow(); ++i) {
            determinan *= m.getElmt(i, i);
        }

        // hitung dengan berapa kali swap
        for (i = 0; i < swapCounter; ++i) {
            determinan *= (-1);
        }
        
        return determinan;
    }
}
