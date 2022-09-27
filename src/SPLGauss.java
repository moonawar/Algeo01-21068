public class SPLGauss {
    public static void rapihkan(Matrix m) {
        // jika ada hasil di matriks yang bernilai -0.0
        int i, j;

        for (i = 0; i <= m.getLastIdxRow(); i++) {
            for (j = 0; j <= m.getLastIdxCol(); j++) {
                if (m.getElmt(i, j) == -0) {
                    m.setElmt(i, j, 0);
                }
            }
        }
    }

    public static void swap(Matrix m ,int i1, int i2) {
        int j;
        float temp;

        for (j = 0; j <= m.getLastIdxCol(); ++j) {
            temp = m.getElmt(i1, j);
            m.setElmt(i1, j, m.getElmt(i2, j));
            m.setElmt(i2, j, temp);
        }
    }

    public static void gauss(Matrix m) {
        int i, j, k, temp, indent;
        float pembagi;

        // urutkan baris berdasarkan jumlah 0 sebelum diagonal
        int[] jumlah0 = new int[m.getLastIdxRow() + 1];
        for (i = 0; i <= m.getLastIdxRow(); ++i) {
            jumlah0[i] = 0;
            j = 0;
            while (j <= m.getLastIdxCol() && m.getElmt(i, j) == 0) {
                jumlah0[i]++;
                j++;
            }
        }

        // tukar baris berdasarkan urutan jumlah 0
        for (i = 0; i <= m.getLastIdxRow(); ++i) {
            for (j = 0; j <= m.getLastIdxRow() - 1; ++j) {
                if (jumlah0[j] > jumlah0[j + 1]) {
                    swap(m, j, j + 1);
                    temp = jumlah0[j];
                    jumlah0[j] = jumlah0[j + 1];
                    jumlah0[j + 1] = temp;
                }
            }
        }
        
        // proses reduksi baris
        indent = 0;

        for (i = 0; i <= m.getLastIdxRow(); i++) {
            // mencari di kolom berapa ada elemen!=0 karena bisa jadi elemen pada kolom pertama bernilai 0
            // karena sudah diurutkan berdasarkan jumlah 0, maka indent hanya perlu dispesifikasikan sekali
            while (i + indent <= m.getLastIdxCol() && m.getElmt(i, i + indent) == 0) {
                indent++;
            }

            if (i + indent <= m.getLastIdxCol()) {
                // jadikan angka terdepan (elemen pertama yang tidak 0 pada baris) = 1
                pembagi = m.getElmt(i, i + indent);
                for (j = 0; j <= m.getLastIdxCol(); j++) {
                    m.setElmt(i, j, m.getElmt(i, j)/pembagi);
                }

                // pengurangan
                for (j = i + 1; j <= m.getLastIdxRow(); j++) {
                    if (m.getElmt(j, i + indent) != 0) {
                        pembagi = m.getElmt(j, i + indent);
                        for (k = 0; k <= m.getLastIdxCol(); k++) {
                            m.setElmt(j, k, m.getElmt(j, k)/pembagi);
                        }
                        for (k = 0; k <= m.getLastIdxCol(); k++) {
                            m.setElmt(j, k, m.getElmt(j, k) - m.getElmt(i, k));
                        }
                    }
                }
            }
        }

        // jika hasil matriksnya ada elemen -0.0
        rapihkan(m);
    }

    public static void gaussJordan(Matrix m) {
        int i, j, k, indent;
        float pembagi;
    
        gauss(m);

        indent = 0;

        for (i = 0; i <= m.getLastIdxRow(); ++i) {
            // mencari di kolom berapa ada elemen!=0 karena matriks telah di sederhanakan dengan gauss, dan kemungkinan besar elemen 0 tidak berurutan secara diagonal
            // karena sudah diurutkan berdasarkan jumlah 0, maka indent hanya perlu dispesifikasikan sekali
            while (i + indent <= m.getLastIdxCol() && m.getElmt(i, i + indent) == 0) {
                indent++;
            }

            if (i + indent <= m.getLastIdxCol()) {
                // pengurangan
                
                for (j = i - 1; j >= 0; j--) {
                    if (m.getElmt(j, i + indent) != 0) {
                        pembagi = m.getElmt(j, i + indent);
                        for (k = 0; k <= m.getLastIdxCol(); k++) {
                            m.setElmt(j, k, m.getElmt(j, k) - (m.getElmt(i, k) * pembagi));
                        }
                    }
                }
            }
        }

        // jika hasil matriksnya ada elemen -0.0
        rapihkan(m);
    }
}