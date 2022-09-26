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

    static boolean segi3Atas(Matrix m, boolean augmented) {
        int i, j;
        if (!augmented) {
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
        } else {
            if (m.getLastIdxRow() >= 1) {
                for (i = 0; i <= m.getLastIdxRow()-1; i++) {
                    for (j = i+1; j <= m.getLastIdxCol()-1; j++) {
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
    }

    static boolean onlyHasOneCol(Matrix m, boolean augmented) {
        if (augmented) {
            if (m.getLastIdxCol() == 1) {
                return true;
            }
        } else {
            if (m.getLastIdxCol() == 0) {
                return true;
            }
        }

        return false;
    }

    static void gauss(Matrix m, boolean augmented) {
        int i, j, k;
        float ratio, pembagi;
    
        if (!segi3Bawah(m) && !onlyHasOneCol(m, augmented)) {
            for (i = 0; i <= m.getLastIdxRow(); i++) {
                for (j = 0; j <= m.getLastIdxRow(); j++) {
                    if (j>i) {
                        ratio = (float)m.getElmt(j, i) / m.getElmt(i, i);
                        for (k = 0; k <= m.getLastIdxCol(); k++) {
                            m.setElmt(j, k, m.getElmt(j, k) - (m.getElmt(i, k)) * ratio);
                        }
                    } 
                }
            }

            for (i = 0; i <= m.getLastIdxRow(); i++) {
                for (j = 0; j <= m.getLastIdxCol(); j++) {
                    k = i - 1;
                    do {
                        ++k;
                    }
                    while (m.getElmt(i, k) == 0 && k <= m.getLastIdxCol());

                    if (k > m.getLastIdxCol()) {
                        pembagi = m.getElmt(i, i);
                        for (j = 0; j <= m.getLastIdxCol(); ++j) {
                            m.setElmt(i, j, m.getElmt(i, j)/pembagi);
                        }
                    }
                }
            }
        }
    }

    static void gaussJordan(Matrix m, boolean augmented) {
        int i, j, k;
        float ratio, pembagi;
    
        gauss(m, augmented);
    
        if (!segi3Atas(m, augmented) && !onlyHasOneCol(m, augmented)) {
            for (i = m.getLastIdxRow(); i >= 0; i--) {
                for (j = m.getLastIdxRow(); j >= 0; j--) {
                    if (i != j) {
                        ratio = (float)m.getElmt(j, i) / m.getElmt(i, i);
                        for (k = m.getLastIdxCol(); k >= 0; k--) {
                            m.setElmt(j, k, m.getElmt(j, k) - (m.getElmt(i, k)) * ratio);
                        }
                    }
                }
            }

            for (i = 0; i <= m.getLastIdxRow(); i++) {
                for (j = 0; j <= m.getLastIdxCol(); j++) {
                    k = i - 1;
                    do {
                        ++k;
                    }
                    while (m.getElmt(i, k) == 0 && k <= m.getLastIdxCol());

                    if (k > m.getLastIdxCol()) {
                        pembagi = m.getElmt(i, i);
                        for (j = 0; j <= m.getLastIdxCol(); ++j) {
                            m.setElmt(i, j, m.getElmt(i, j)/pembagi);
                        }
                    }
                }
            }
        }
    }
}