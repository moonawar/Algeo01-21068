public class SPLGauss {
    static boolean segi3Bawah(Matrix m) {
        int i, j;

        if (m.getLastIdxRow() >= 1) {
            for (i = 1; i <= m.getLastIdxRow(); i++) {
                for (j = 0; j < i; j++) {
                    if (m.getElmt(i, j) != 0) {
                        return false;
                    }
                }
            }
        } else if (m.getLastIdxRow() == 0) {
            return true;
        } else {
            return false;
        }
    
        return true;
    }

    static boolean segi3Atas(Matrix m) {
        int i, j;
    
        if (m.getLastIdxRow() >= 1) {
            for (i = 0; i <= m.getLastIdxRow()-1; i++) {
                for (j = i+1; j <= m.getLastIdxCol(); j++) {
                    if (m.getElmt(i, j) != 0) {
                        return false;
                    }
                }
            }
        } else if (m.getLastIdxRow() == 0) {
            return true;
        } else {
            return false;
        }
    
        return true;
    }

    static void gauss(Matrix m) {
        int i, j, k;
        float ratio;
    
        if (!segi3Bawah(m)) {
            for (i = 0; i <= m.getLastIdxRow(); i++) {
                for (j = 0; j <= m.getLastIdxCol(); j++) {
                    if (j>i) {
                        ratio = (float)m.getElmt(j, i) / m.getElmt(i, i);
                        for (k = 0; k <= m.getLastIdxRow(); k++) {
                            m.setElmt(j, k, m.getElmt(j, k) - (m.getElmt(i, k)) * ratio);
                        }
                    } 
                }
            }
        }
    }

    static void gaussJordan(Matrix m) {
        int i, j, k;
        float ratio;
    
        gauss(m);
    
        if (!segi3Atas(m)) {
            for (i = m.getLastIdxRow(); i >= 0; i--) {
                for (j = m.getLastIdxCol(); j >= 0; j--) {
                    if (i != j) {
                        ratio = (float)m.getElmt(j, i) / m.getElmt(i, i);
                        for (k = m.getLastIdxCol(); k >= 0; k--) {
                            m.setElmt(j, k, m.getElmt(j, k) - (m.getElmt(i, k)) * ratio);
                        }
                    }
                }
            }
        }
    }

}