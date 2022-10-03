public class SPLGauss {
    static final float MARK = (float) -99999999999999999999999.9999;

    public static void rapihkan(Matrix m) {
        /* KAMUS */
        // jika ada hasil di matriks yang bernilai -0.0
        int i, j;

        /* ALGORITMA */
        for (i = 0; i <= m.getLastIdxRow(); i++) {
            for (j = 0; j <= m.getLastIdxCol(); j++) {
                if (m.getElmt(i, j) == -0) {
                    m.setElmt(i, j, 0);
                }
            }
        }
    }

    public static void swap(Matrix m ,int i1, int i2) {
        /* KAMUS */
        int j;
        float temp;

        /* ALGORITMA */
        for (j = 0; j <= m.getLastIdxCol(); ++j) {
            temp = m.getElmt(i1, j);
            m.setElmt(i1, j, m.getElmt(i2, j));
            m.setElmt(i2, j, temp);
        }
    }

    public static void gauss(Matrix m) {
        /* KAMUS */
        int i, j, k, temp, indent;
        float pembagi;

        /* ALGORITMA */
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
        /* KAMUS */
        int i, j, k, indent;
        float pembagi;
    
        /* ALGORITMA */
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
        /* KAMUS */
        int i, j, jumlah0;
        
        /* ALGORITMA */
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
        /* KAMUS */
        int count = 0;
        int j;

        /* ALGORITMA */
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
        /* KAMUS */
        int j, jumlah0;
        
        /* ALGORITMA */
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
        /* KAMUS */
        int j, jumlahtidak0;
        
        /* ALGORITMA */
        jumlahtidak0 = 0;
        for (j = 0; j <= m.getLastIdxCol()-1; ++j) {
            if (m.getElmt(i, j) != 0) {
                ++jumlahtidak0;
            }
        }

        return jumlahtidak0;
    }

    public static String pengaliVar(String varString, Float x) {
        /* KAMUS */
        // ASCII a-z : 97-122
        // ASCII 0-9 : 48-57
        // ASCII . : 46
        // ASCII - : 45
        int i, panjang, ctemp;
        String temp, ctempstr;
        float ctempfloat;
        char c;

        /* ALGORITMA */
        panjang = varString.length();
        temp = "";
        if (panjang == 1) {
            temp = temp.concat(String.valueOf(x));
            temp = temp.concat(varString);
        } else {
            i = 0;
            ctempstr = "";
            while (panjang - i >= 0) {
                try {
                    c = varString.charAt(i);
                    ctemp = c;
                    if ((ctemp >= 48 && ctemp <= 57) || ctemp == 46 || ctemp == 45) {
                        ctempstr = ctempstr.concat(String.valueOf(c));
                    } else if (ctemp == 43) {
                        temp = temp.concat(" + ");
                    } else {
                        //jika variabel
                        ctempfloat = Float.parseFloat(ctempstr);
                        ctempfloat *= x;
                        temp = temp.concat(String.valueOf(ctempfloat));
                        ctempstr = String.valueOf(c);
                        temp = temp.concat(ctempstr);
                        ctempstr = ""; // reset ctemp
                    }
                    ++i;
                } catch (Exception e) {
                    //jika blank
                    ++i;
                }
            }
        }

        return temp;
    }

    public static void rapihkanSPL(Solution sol, int j) {
        /* KAMUS */
        String temp, temp2;
        int panjang;
        char tempChar;

        /* ALGORITMA */
        for (int i = 0; i <= j; ++i) {
            temp2 = "";
            temp = sol.getVar(i);
            panjang = temp.length();
            if (panjang == 1) {
                temp2 = temp2.concat(temp);
            } else {
                for (int k = 0; k <= panjang-1; ++k) {
                    try {
                        tempChar = temp.charAt(k);
                        if (tempChar == '-') {
                            if (temp2 == "") {
                                temp2 = temp2.concat(String.valueOf(tempChar));
                                temp2 = temp2.concat(" ");
                            } else {
                                temp2 = temp2.concat(" ");
                                temp2 = temp2.concat(String.valueOf(tempChar));
                                temp2 = temp2.concat(" ");
                            }
                        } else if (tempChar == '+') {
                            if (temp2 == "") {
                                continue;
                            } else {
                                temp2 = temp2.concat(" ");
                                temp2 = temp2.concat(String.valueOf(tempChar));
                                temp2 = temp2.concat(" ");
                            }
                        } else if (tempChar == ' ') {
                            continue;
                        } else if (tempChar == '.') {
                            temp2 = temp2.concat(String.valueOf(tempChar));
                        } else {
                            temp2 = temp2.concat(String.valueOf(tempChar));
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }

                sol.setVar(i, sederhanakanSPL(temp2, 25));
            }
        }
    }

    public static String sederhanakanSPL(String s, int length) {
        /* KAMUS */
        int i = 0;
        int j;
        int tempASCII;
        String currentWord = "";
        String end = "";
        String tempString = "";
        float temp;
        float[] equation;
        String[] variableList;
        equation = new float[length+1];
        variableList = new String[length+1];
        String listVar = "abcdefghijklmnopqrstuvwxyz";

        /* ALGORITMA */
        for (j = 0; j <= length; ++j) {
            variableList[j] = listVar.substring(j, j+1);
            equation[j] = 0;
        }

        while (i <= s.length()-1) {
            if (s.charAt(i) == ' ' || i == s.length()-1) {
                if (currentWord.length() == 0) {
                    ++i;
                    continue;
                }

                if (i == s.length()-1) {
                    currentWord = currentWord.concat(String.valueOf(s.charAt(i)));
                }
                
                if ((currentWord.charAt(0) == '-' || currentWord.charAt(0) == '+') && currentWord.length() == 1) {
                    ++i;
                    continue;
                } else if (currentWord.length() == 2 && ((currentWord.charAt(0) == '-' && currentWord.charAt(1) == '+') || currentWord.charAt(0) == '+' && currentWord.charAt(1) == '-')) {
                    ++i;
                    continue;
                } else {
                    if (currentWord.charAt(currentWord.length()-1) != 'd' && currentWord.charAt(currentWord.length()-1) != 'f') {
                        try {
                            temp = Float.parseFloat(currentWord);
                            equation[length] += temp;
                            currentWord = "";
                            
                        } catch (Exception e) {
                            tempASCII = currentWord.charAt(currentWord.length()-1);
                            if (currentWord.length() == 1) {
                                equation[tempASCII-97] += 1;
                            } else if (currentWord.length() == 2) {
                                if (currentWord.charAt(currentWord.length()-2) == '-') {
                                    equation[tempASCII-97] -= 1;
                                } else {
                                    equation[tempASCII-97] += 1;
                                }
                            } 
                            else {
                                if ((currentWord.charAt(0) == '-' && currentWord.charAt(1) == '+') || (currentWord.charAt(0) == '+' && currentWord.charAt(1) == '-')) {
                                    tempString = "-";
                                    currentWord = currentWord.substring(2, currentWord.length()-1);
                                    tempString = tempString.concat(currentWord);
                                    temp = Float.parseFloat(tempString);
                                    equation[tempASCII-97] += temp;
                                } else {
                                    currentWord = currentWord.substring(0, currentWord.length()-1);
                                    temp = Float.parseFloat(currentWord);
                                    equation[tempASCII-97] += temp;
                                }   
                            }
                            currentWord = "";
                        }
                    } else {
                        tempASCII = currentWord.charAt(currentWord.length()-1);
                        if (currentWord.length() == 1) {
                            equation[tempASCII-97] += 1;
                        } else if (currentWord.length() == 2) {
                            if (currentWord.charAt(currentWord.length()-2) == '-') {
                                equation[tempASCII-97] -= 1;
                            } else {
                                equation[tempASCII-97] += 1;
                            }
                        } 
                        else {
                            if ((currentWord.charAt(0) == '-' && currentWord.charAt(1) == '+') || (currentWord.charAt(0) == '+' && currentWord.charAt(1) == '-')) {
                                tempString = "-";
                                currentWord = currentWord.substring(2, currentWord.length()-1);
                                tempString = tempString.concat(currentWord);
                                temp = Float.parseFloat(tempString);
                                equation[tempASCII-97] += temp;
                            } else {
                                currentWord = currentWord.substring(0, currentWord.length()-1);
                                temp = Float.parseFloat(currentWord);
                                equation[tempASCII-97] += temp;
                            }   
                        }
                        currentWord = "";
                    }
                }
            } else {
                currentWord = currentWord.concat(String.valueOf(s.charAt(i)));
            }
            ++i;   
        }
        if (equation[length] != 0) {
            end = String.valueOf(equation[length]);
        } else {
            end = "";
        }

        for (j = 0; j < length; ++j) {
            if (equation[j] == 0) {
                continue;
            } else if (equation[j] > 0) {
                if (end != "") {
                    end = end.concat(" + ");
                    if (equation[j] == 1) {
                        end = end.concat(variableList[j]);
                    } else {
                        end = end.concat(String.valueOf(equation[j]));
                        end = end.concat(variableList[j]);
                    }
                } else {
                    if (equation[j] == 1) {
                        end = end.concat(variableList[j]);
                    } else {
                        end = end.concat(String.valueOf(equation[j]));
                        end = end.concat(variableList[j]);
                    }
                }
            } else {
                if (end != "") {
                    end = end.concat(" - ");
                    if (equation[j] == -1) {
                        end = end.concat(variableList[j]);
                    } else {
                        end = end.concat(String.valueOf(Math.abs(equation[j])));
                        end = end.concat(variableList[j]);
                    }
                } else {
                    end = end.concat("-");
                    if (equation[j] == -1) {
                        end = end.concat(variableList[j]);
                    } else {
                        end = end.concat(String.valueOf(Math.abs(equation[j])));
                        end = end.concat(variableList[j]);
                    }
                }
            }    
        }
        if (end == "") {
            end = "0";
        }
        return end;
    }

    public static Solution gaussSPL(Matrix m) {
        /* KAMUS */
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
        
        /* ALGORITMA */
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
                                    if (pengaliVar(sol.getVar(k), m.getElmt(i, k) * (-1)).charAt(0) != '-') {
                                        temp2 = temp2.concat(" +");
                                    } else {
                                        temp2 = temp2.concat(" ");
                                    }
                                    temp2 = temp2.concat(pengaliVar(sol.getVar(k), m.getElmt(i, k) * (-1)));
                                }
                            }
                        }
                        sol.setVar(j, temp2);
                    } 
                }
            }
            rapihkanSPL(sol, m.getLastIdxCol() - 1);
            return sol;
        }
    }

    public static Solution gaussJordanSPL(Matrix m) {
        /* KAMUS */
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
        
        /* ALGORITMA */
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
                                    if (pengaliVar(sol.getVar(k), m.getElmt(i, k) * (-1)).charAt(0) != '-') {
                                        temp2 = temp2.concat(" +");
                                    } else {
                                        temp2 = temp2.concat(" ");
                                    }
                                    temp2 = temp2.concat(pengaliVar(sol.getVar(k), m.getElmt(i, k) * (-1)));
                                }
                            }
                        }
                        sol.setVar(j, temp2);
                    } 
                }
            }
            rapihkanSPL(sol, m.getLastIdxCol()-1);
            return sol;
        }
    }
}