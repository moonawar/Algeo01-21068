public class Matrix {
    
    public int rowEff;
    public int colEff;
    public float[][] mem;

    public Matrix(int nRow, int nCol){
        this.rowEff = nRow;
        this.colEff = nCol;
        this.mem = new float[nRow][nCol];
        for (int i = 0; i <= getLastIdxRow(); i++) {
            for (int j = 0; j <= getLastIdxCol(); j++){
                setElmt(i, j, 0); 
            }
        } 
    }

    public int getLastIdxRow() {
        return this.rowEff - 1;
    }
      
    public int getLastIdxCol() {
        return this.colEff - 1;
    }
      
    public float getElmt(int i, int j) {
        return this.mem[i][j];
    }
      
    public void setElmt(int i, int j, float val) {
        this.mem[i][j] = val;
    }

    public int NbElmt() {
        return (this.rowEff * this.colEff);
    }

    public boolean isSquare() {
        return (this.rowEff == this.colEff);
    }

    public Matrix copyMatrix(){
        Matrix mOut = new Matrix(this.rowEff, this.colEff);
        for (int i = 0; i <= this.getLastIdxRow(); i++) {
            for (int j = 0; j <= this.getLastIdxCol(); j++) {
                mOut.setElmt(i, j, this.getElmt(i, j));
            }
        }

        return mOut;
    }
}
