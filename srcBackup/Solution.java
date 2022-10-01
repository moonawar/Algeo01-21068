public class Solution {
    public float[] hasil;
    public String[] listVarMat;
    public Boolean state;
    
    public Solution (float[] hasil, String[] listVarMat, Boolean state) {
        this.hasil = hasil;
        this.listVarMat = listVarMat;
        this.state = state;
    }

    public String getVar(int j) {
        return this.listVarMat[j];
    }

    public Float getHasil(int j) {
        return this.hasil[j];
    }

    public Boolean getState() {
        return this.state;
    }

    public void setVar(int j, String s) {
        this.listVarMat[j] = s;
    }

    public void setHasil(int j, float f) {
        this.hasil[j] = f;
    }

    public void setState(Boolean s) {
        this.state = s;
    }

    public static void displaySolution(Solution sol, Matrix m) {
        if (sol.getState()) {
            for (int j = 0; j <= m.getLastIdxCol()-1; j++) {
                System.out.println("x" + (j + 1) + " = " + sol.getVar(j));
            }

        } else {
            System.out.println("SPL tersebut tidak miliki solusi");
        }
    }
}