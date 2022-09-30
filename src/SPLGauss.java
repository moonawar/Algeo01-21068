public class SPLGauss {
    static final float MARK = (float) -99999999999999999999999.9999;

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

    public static boolean solusiAda(Matrix m) {
        int i, j, jumlah0;
        
        for (i = 0; i <= m.getLastIdxRow(); ++i) {
            jumlah0 = 0;
            for (j = 0; j <= m.getLastIdxCol()-1; ++j) {
                if (m.getElmt(i, j) == 0) {
                    ++jumlah0;
                }
            }

            // jika jumlah0 pada suatu baris == banyak elemen di baris - 1
            if (jumlah0 == m.getLastIdxCol()) {
                if (m.getElmt(i, m.getLastIdxCol()) != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean satuSolusi(Matrix m, int i) {
        int count = 0;
        int j;

        for (j = 0; j <= m.getLastIdxCol()-1; ++j) {
            if (m.getElmt(i, j) != 0) {
                ++count;
            }
        }
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean solusiBanyak(Matrix m, int i) {
        int j, jumlah0;
        
        jumlah0 = 0;
        for (j = 0; j <= m.getLastIdxCol()-1; ++j) {
            if (m.getElmt(i, j) == 0) {
                ++jumlah0;
            }
        }

        // jika jumlah0 pada suatu baris == banyak elemen di baris - 1
        if (jumlah0 == m.getLastIdxCol()) {
            if (m.getElmt(i, m.getLastIdxCol()) == 0) {
                return true;
            }
        }

        return false;
    }

    public static int jumlahTidak0(Matrix m, int i) {
        int j, jumlahtidak0;
        
        jumlahtidak0 = 0;
        for (j = 0; j <= m.getLastIdxCol()-1; ++j) {
            if (m.getElmt(i, j) != 0) {
                ++jumlahtidak0;
            }
        }

        return jumlahtidak0;
    }

    public static Solution gaussSPL(Matrix m) {
        int i, j, k, count;
        int temp;
        float temp1;
        String temp2;
        boolean state = true;
        String[] listVarMat = new String[m.getLastIdxCol()];
        float[] hasil = new float[m.getLastIdxCol()];
        Solution sol = new Solution(hasil, listVarMat, state);

        // string untuk parametrik
        String listVar = "abcdefghijklmnopqrstuvwxyz";
        
        gauss(m);

        // cek apakah ada solusi
        if (!solusiAda(m)) {
            sol.setState(false);
            return sol;
        } else {
            // misalkan seluruh hasil adalah parametrik
            for (j = 0; j <= m.getLastIdxCol()-1; ++j) {
                sol.setVar(j, listVar.substring(j, j+1));
            }

            // misalkan awalnya hasil berisikan MARK sebagai penanda kalau kita tidak memiliki nilainya
            for (j = 0; j <= m.getLastIdxCol()-1; ++j) {
                sol.setHasil(j, MARK);
            }

            
            // urutkan baris berdasarkan jumlah 0 sebelum diagonal
            int[] jumlah0 = new int[m.getLastIdxRow() + 1];
            for (i = 0; i <= m.getLastIdxRow(); ++i) {
                jumlah0[i] = 0;
                j = 0;
                while (j <= m.getLastIdxCol()-1 && m.getElmt(i, j) == 0) {
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
            
            for (i = m.getLastIdxRow(); i >= 0; --i) {
                if (satuSolusi(m, i)) {
                    j = 0;
                    while (j <= m.getLastIdxCol()-1 && m.getElmt(i, j) == 0) {
                        ++j;
                    }
                    sol.setHasil(j, m.getElmt(i, m.getLastIdxCol()));
                    sol.setVar(j, String.valueOf(m.getElmt(i, m.getLastIdxCol())));
                } else if (solusiBanyak(m, i)) {
                    continue;
                } else {
                    j = 0;
                    while (m.getElmt(i, j) == 0 && j <= m.getLastIdxCol()-1) {
                        ++j;
                    }

                    count = jumlahTidak0(m, i);

                    temp1 = m.getElmt(i, m.getLastIdxCol());
                    // prioritaskan yang sudah ada nilai terlebih dahulu
                    for (k = j+1; k <= m.getLastIdxCol()-1; ++k) {
                        if (m.getElmt(i, k) != 0) {
                            if (sol.getHasil(k) != MARK) {
                                temp1 -= m.getElmt(i, k) * sol.getHasil(k);
                                --count;
                            }
                        }
                    }
                    if (count == 1) {
                        sol.setHasil(j, temp1);
                        sol.setVar(j, String.valueOf(temp1));
                    } else {
                        if (temp1 == 0) {
                            temp2 = "";
                        } else {
                            temp2 = String.valueOf(temp1);
                        }
                        // untuk yang tidak memiliki nilai
                        for (k = j+1; k <= m.getLastIdxCol()-1; ++k) {
                            if (m.getElmt(i, k) != 0) {
                                if (sol.getHasil(k) == MARK) {
                                    if (m.getElmt(i, j) > 0) {
                                        if (temp2 == "") {
                                            temp2 = temp2.concat("-");
                                        } else {
                                            temp2 = temp2.concat(" -");
                                        }
                                    } else {
                                        if (temp2 == "") {
                                            temp2 = temp2.concat("+");
                                        } else {
                                            temp2 = temp2.concat(" +");
                                        }
                                    }
                                    temp2 = temp2.concat(String.valueOf(Math.abs(m.getElmt(i, k))));
                                    temp2 = temp2.concat(sol.getVar(k));
                                    
                                }
                            }
                        }
                        sol.setVar(j, temp2);
                    } 
                }
            }
            return sol;
        }
    }

    public static Solution gaussJordanSPL(Matrix m) {
        int i, j, k, count;
        int temp;
        float temp1;
        String temp2;
        boolean state = true;
        String[] listVarMat = new String[m.getLastIdxCol()];
        float[] hasil = new float[m.getLastIdxCol()];
        Solution sol = new Solution(hasil, listVarMat, state);

        // string untuk parametrik
        String listVar = "abcdefghijklmnopqrstuvwxyz";
        
        gaussJordan(m);

        // cek apakah ada solusi
        if (!solusiAda(m)) {
            sol.setState(false);
            return sol;
        } else {
            // misalkan seluruh hasil adalah parametrik
            for (j = 0; j <= m.getLastIdxCol()-1; ++j) {
                sol.setVar(j, listVar.substring(j, j+1));
            }

            // misalkan awalnya hasil berisikan MARK sebagai penanda kalau kita tidak memiliki nilainya
            for (j = 0; j <= m.getLastIdxCol()-1; ++j) {
                sol.setHasil(j, MARK);
            }

            
            // urutkan baris berdasarkan jumlah 0 sebelum diagonal
            int[] jumlah0 = new int[m.getLastIdxRow() + 1];
            for (i = 0; i <= m.getLastIdxRow(); ++i) {
                jumlah0[i] = 0;
                j = 0;
                while (j <= m.getLastIdxCol()-1 && m.getElmt(i, j) == 0) {
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
            
            for (i = m.getLastIdxRow(); i >= 0; --i) {
                if (satuSolusi(m, i)) {
                    j = 0;
                    while (j <= m.getLastIdxCol()-1 && m.getElmt(i, j) == 0) {
                        ++j;
                    }
                    sol.setHasil(j, m.getElmt(i, m.getLastIdxCol()));
                    sol.setVar(j, String.valueOf(m.getElmt(i, m.getLastIdxCol())));
                } else if (solusiBanyak(m, i)) {
                    continue;
                } else {
                    j = 0;
                    while (m.getElmt(i, j) == 0 && j <= m.getLastIdxCol()-1) {
                        ++j;
                    }

                    count = jumlahTidak0(m, i);

                    temp1 = m.getElmt(i, m.getLastIdxCol());
                    // prioritaskan yang sudah ada nilai terlebih dahulu
                    for (k = j+1; k <= m.getLastIdxCol()-1; ++k) {
                        if (m.getElmt(i, k) != 0) {
                            if (sol.getHasil(k) != MARK) {
                                temp1 -= m.getElmt(i, k) * sol.getHasil(k);
                                --count;
                            }
                        }
                    }
                    if (count == 1) {
                        sol.setHasil(j, temp1);
                        sol.setVar(j, String.valueOf(temp1));
                    } else {
                        if (temp1 == 0) {
                            temp2 = "";
                        } else {
                            temp2 = String.valueOf(temp1);
                        }
                        // untuk yang tidak memiliki nilai
                        for (k = j+1; k <= m.getLastIdxCol()-1; ++k) {
                            if (m.getElmt(i, k) != 0) {
                                if (sol.getHasil(k) == MARK) {
                                    if (m.getElmt(i, j) > 0) {
                                        if (temp2 == "") {
                                            temp2 = temp2.concat("-");
                                        } else {
                                            temp2 = temp2.concat(" -");
                                        }
                                    } else {
                                        if (temp2 == "") {
                                            temp2 = temp2.concat("+");
                                        } else {
                                            temp2 = temp2.concat(" +");
                                        }
                                    }
                                    temp2 = temp2.concat(String.valueOf(Math.abs(m.getElmt(i, k))));
                                    temp2 = temp2.concat(sol.getVar(k));
                                    
                                }
                            }
                        }
                        sol.setVar(j, temp2);
                    } 
                }
            }
            return sol;
        }
    }
}