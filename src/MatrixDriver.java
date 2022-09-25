public class MatrixDriver {
    public static void main(String[] args) {
        MatrixOperations lib = new MatrixOperations();
        Matrix m = lib.readMatrix();
        Matrix n = lib.Inverse(m);
        
        lib.displayMatrix(m);
    }
}
