public class DetCofactor {
    public static void MatrixKecil(Matrix mat, Matrix cofac, int row, int col, int order){  
               
    int a = 0;  
    int b = 0;  
      
    for (int i = 0; i < order; i++) { 
            for (int j = 0; j < order; j++) {  
                if (j != col && i != row) {  
                    cofac.mem[a][b] = mat.getElmt(i, j);
                    b++; 

                    if (b == order - 1) {
                        b = 0;
                        a++;
                    }
                }  
            }  
          
    }    
}  
            
          
    public static float DeterminanCofaktor(Matrix m,int order){
    //2x2
        if (order == 2){
            return (m.getElmt(0, 0)*m.getElmt(1, 1))-(m.getElmt(0, 1)*m.getElmt(1, 0));
        } else {
            float res = 0;
            Matrix cofac = new Matrix(order, order);
            int sign = 1;
            for (int i = 0; i < order; i++) {
                MatrixKecil(m, cofac, 0, i, order);
                res += sign * m.getElmt(0, i) * DeterminanCofaktor(cofac, order - 1);
                sign = -sign;
            }
         return res;
        }  
    }


    public static float determinanCofactor(Matrix m) {
        return 0;
    }
}
