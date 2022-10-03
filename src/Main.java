import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Welcome();
        MainMenu();
    }

    public static void MainMenu(){
        System.out.printf("""

                                    ADALAH MENU UTAMA
        ==========================================================================
        (1) Sistem Persamaaan Linear
        (2) Determinan
        (3) Matriks Balikan
        (4) Interpolasi Polinom
        (5) Interpolasi Bicubic
        (6) Regresi Linear Berganda
        
        (0) Keluar
        """);

        System.out.printf("Mau kemanakah kamu? (Pilihan 0-6): ");
        int in = MainScanner.sc.nextInt();

        while (in < 0 || in > 6){
            System.out.printf("Punten, pilihannya masih salah. Ayo coba lagi (Pilihan 0-6): ");
            in = MainScanner.sc.nextInt();
        }

        switch (in) {
            case 1:
                SPLMenu();
                break;
            case 2:
                DeterminantMenu();
                break;
            case 3:
                InverseMenu();
                break;
            case 4:
                PolinomInterMenu();
                break;
            case 5:
                BicubicInterMenu();
                break;
            case 6:
                RegresiMenu();
                break;
            case 0:
                System.out.println("Aite man, goodbye!");
                System.exit(0);
        }
    }

    public static void SPLMenu(){
        System.out.println("\nPilihan yang sangat epic! Sekarang, ayo pilih metode yang mau dipakai!\n");
        System.out.printf("""
                                    PILIHAN METODE SPL
        (1) Metode eliminasi Gauss
        (2) Metode eliminasi Gauss-Jordan
        (3) Metode matriks balikan
        (4) Metode kaidah Cramer

        (0) Kembali ke menu utama
        """);
        System.out.printf("Tentukan pilihanmu? (Pilihan 0-4): ");
        int in = MainScanner.sc.nextInt();

        while (in < 0 || in > 4){
            System.out.printf("Punten, pilihannya masih salah. Ayo coba lagi (Pilihan 0-4): ");
            in = MainScanner.sc.nextInt();
        }
            System.out.println();
            Solution solution;
            Matrix mInput;

            if (in != 0) {
                mInput = readSPLMain();
            } else {
                mInput = null;
                BackToMainMenu();
                return;
            }


        switch (in) {
            case 1:
                solution = SPLGauss.gaussSPL(mInput);
                System.out.println("\nSolusi SPL dengan metode eliminasi Gauss:");
                Solution.displaySolution(solution, mInput);
                SimpanSPLKeFile(solution);
                BackToMainMenu();
                break;
            case 2:
                solution = SPLGauss.gaussJordanSPL(mInput);
                System.out.println("\nSolusi SPL dengan metode eliminasi Gauss-Jordan:");
                Solution.displaySolution(solution, mInput);
                SimpanSPLKeFile(solution);
                BackToMainMenu();
                break;
            case 3:
                solution = SPLInverse.SolutionWithInverse(Converter.augmentedToMatrix(mInput, true), Converter.augmentedToMatrix(mInput, false), false);
                Solution.displaySolution(solution, mInput);
                SimpanSPLKeFile(solution);
                BackToMainMenu();
                break;
            case 4:
                solution = SPLCrammer.crammerSPL(mInput);
                Solution.displaySolution(solution, mInput);
                SimpanSPLKeFile(solution);
                BackToMainMenu();
                break;
            case 0:
                BackToMainMenu();
                break;
        }
    }

    public static void DeterminantMenu(){
        System.out.println("\nSheesshh! Kamu memilih determinan. Sekarang, ayo pilih metode yang mau dipakai!\n");
        System.out.printf("""
                                PILIHAN METODE DETERMINAN
        (1) Metode Reduksi Baris
        (2) Metode Ekspansi Kofaktor

        (0) Kembali ke menu utama
        """);
        System.out.printf("Tentukan pilihanmu? (Pilihan 0-2): ");
        int in = MainScanner.sc.nextInt();

        while (in < 0 || in > 2){
            System.out.printf("Punten, pilihannya masih salah. Ayo coba lagi (Pilihan 0-4): ");
            in = MainScanner.sc.nextInt();
        }

        Matrix mInput;

        if (in != 0) {
            mInput = readMatrixMain();
            while (!mInput.isSquare()) {
                System.out.println("Maaf, matriks yang kamu masukkan bukan matriks persegi. Ayo coba lagi!");
                mInput = readMatrixMain();
            }

        } else {
            mInput = null;
        }

        float det;
        switch (in) {
            case 1:
                det = DetReduction.determinanGauss(mInput);
                System.out.println("\nDeterminan dari matriks tersebut dengan metode reduksi baris adalah: " + det);
                BackToMainMenu();
                break;
            case 2:
                det = DetCofactor.determinanCofactor(mInput);
                System.out.println("\nDeterminan dari matriks tersebut dengan metode ekspansi kofaktor adalah: " + det);
                BackToMainMenu();
                break;
            case 0:
                BackToMainMenu();
                break;
        }
    }

    public static void InverseMenu(){
        System.out.println("\nIni adalah hari kebalikan! Sekarang, pilih metode inverse matriks yang kamu inginkan!\n");
        System.out.printf("""
                            PILIHAN METODE INVERSE MATRIKS
        (1) Metode Reduksi Baris (Input | Identitas)
        (2) Metode Determinan dan Adjoin

        (0) Kembali ke menu utama
        """);
        System.out.printf("Ayo dipilih.. (Pilihan 0-2): ");
        int in = MainScanner.sc.nextInt();

        while (in < 0 || in > 2){
            System.out.printf("Punten, pilihannya masih salah. Ayo coba lagi (Pilihan 0-2): ");
            in = MainScanner.sc.nextInt();
        }

        Matrix m;

        if (in != 0) {
            m = readMatrixMain();
            while (!m.isSquare()) {
                System.out.println("Maaf, matriks yang kamu masukkan bukan matriks persegi. Ayo coba lagi!");
                m = readMatrixMain();
            }

        } else {
            m = null;
        }


        Matrix mInverse;
        switch (in) {
            case 1:
                System.out.println("\nMatriks balikan dari matriks tersebut dengan metode reduksi baris adalah: ");
                mInverse = InverseMat.InverseWithRed(m);
                if (mInverse != null) {
                    MatrixOperations.displayMatrix(mInverse);
                }
                BackToMainMenu();
                break;
            case 2:
                System.out.println("\nMatriks balikan dari matriks tersebut dengan metode determinan dan adjoin adalah: ");
                mInverse = InverseMat.InverseWithAdjoin(m);
                if (mInverse != null) {
                    MatrixOperations.displayMatrix(mInverse);
                }
                BackToMainMenu();
                break;
            case 0:
                BackToMainMenu();
                break;
        }
    }

    public static void PolinomInterMenu(){
        System.out.println("\nInterpolasi polinom.");

        System.out.println("\nPilih cara kalian untuk input matrix!\n");
        System.out.printf("""
                            PILIHAN METODE INPUT MATRIX
        (1) Melalui keyboard
        (2) Melalui File

        (0) Kembali ke menu utama
        """);
        System.out.printf("Ayo dipilih.. (Pilihan 0-2): ");
        int in = MainScanner.sc.nextInt();

        while (in < 0 || in > 2){
            System.out.printf("Punten, pilihannya masih salah. Ayo coba lagi (Pilihan 0-2): ");
            in = MainScanner.sc.nextInt();
        }

        Matrix mInput;

        switch (in) {
            case 1:
                mInput = Interpolation.ReadInterpolationData();
                Interpolation.PolinomInterpolation(mInput);
                BackToMainMenu();
                break;
            case 2:
                System.out.println("\nMasukkan path ke file yang ingin dibaca: ");
                String path = MainScanner.sc.next();
                try {
                    mInput = Converter.readTxt(path);
                    Interpolation.PolinomInterpolation(mInput);
                } catch (FileNotFoundException e) {
                    System.out.println("\nFile tidak ditemukan");
                    BackToMainMenu(); break;
                }

                BackToMainMenu();
                break;
            case 0:
                BackToMainMenu();
            default:
                throw new IllegalStateException("Unexpected value: " + in);
        }
    }

    public static void BicubicInterMenu(){
        System.out.println("\nUwoghh! Kamu tahu apa itu bicubic interpolation!??? Kamu adalah orang yang sangat berbudaya ˚‧º·(˃̣̣̥o˂̣̣̥)‧º·\n");
        System.out.println("\nPilih cara kalian untuk input matrix!\n");
        System.out.printf("""
                            PILIHAN METODE INPUT MATRIX
        (1) Melalui keyboard
        (2) Melalui File

        (0) Kembali ke menu utama
        """);
        System.out.printf("Ayo dipilih.. (Pilihan 0-2): ");
        int in = MainScanner.sc.nextInt();

        while (in < 0 || in > 2){
            System.out.printf("Punten, pilihannya masih salah. Ayo coba lagi (Pilihan 0-2): ");
            in = MainScanner.sc.nextInt();
        }

        Matrix mInput = null;

        switch (in) {
            case 1:
                mInput = Interpolation.ReadBicubicMatrix();
                break;
            case 2:
                System.out.println("\nMasukkan path ke file yang ingin dibaca: ");
                String path = MainScanner.sc.next();
                try {
                    mInput = Converter.readTxt(path);
                } catch (FileNotFoundException e) {
                    System.out.println("\nFile tidak ditemukan");
                    BackToMainMenu(); break;
                }
                break;
            case 0:
                BackToMainMenu();
            default:
                throw new IllegalStateException("Unexpected value: " + in);
        }
        
        float inX = mInput.getElmt(4, 0);
        float inY = mInput.getElmt(4, 1);

        Matrix mIn = new Matrix(4, 4);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                mIn.setElmt(i, j, mInput.getElmt(i, j));
            }
        }

        Interpolation.BicubicInterpolation(mIn, inX, inY);
        BackToMainMenu();
    }

    public static void RegresiMenu(){
        System.out.println("""
            \nRegresi linear kaya praktikum fisika, tapi yang ini berganda.
            """);

            System.out.println("\nPilih cara kalian untuk input matrix!\n");
            System.out.printf("""
                                PILIHAN METODE INPUT MATRIX
            (1) Melalui keyboard
            (2) Melalui File
    
            (0) Kembali ke menu utama
            """);
            System.out.printf("Ayo dipilih.. (Pilihan 0-2): ");
            int in = MainScanner.sc.nextInt();
    
            while (in < 0 || in > 2){
                System.out.printf("Punten, pilihannya masih salah. Ayo coba lagi (Pilihan 0-2): ");
                in = MainScanner.sc.nextInt();
            }
    
            Matrix mInput;
    
            switch (in) {
                case 1:
                    Regression.ReadRegressionData();
                    BackToMainMenu();
                    break;
                case 2:
                    System.out.println("\nMasukkan path ke file yang ingin dibaca: ");
                    String path = MainScanner.sc.next();
                    try {
                        mInput = Converter.readTxt(path);
                        Regression.MultipleLinearRegression(mInput, mInput.rowEff, mInput.colEff - 1);
                        BackToMainMenu();
                    } catch (FileNotFoundException e) {
                        System.out.println("\nFile tidak ditemukan");
                        BackToMainMenu(); break;
                    }
                case 0:
                    BackToMainMenu();
                default:
                    throw new IllegalStateException("Unexpected value: " + in);
            }
        
    }

    public static void Welcome(){
        System.out.println("""             
                                         
        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(*//////////////////////*,.        *////////,  ///,. .,.%@@@@@@@@@@@
        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&*///////////////. ./////*,,..,*////.,//  //. /@@@@@@@@@/&@@@@@@@@
        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*///////////* .///* *&@@@@@@@@@@@#    */..@@@@@@/  ,&@@@&%@@@@@@
        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@* ////////////*.  ,@@@@@@@@@@@@@@@@@@(   @@@@@@@@       @@@%@@@@@
        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(.//////////////// @@@@@@@@@@@@@@/   *@@@@@* @@@@@@@@  &@@/  (@@@%@@@
        @@@@@@@@@@@@@@@@@@@@@@@@@@@@/.////////////////////* @@@@@@@@@@@       %@@@@# @@@@@@@*  @@@   @@@@%@@
        @@@@@@@@@@@@@@@@@@@@@@@@/.//////////////////////////.@@@@@@@@@    @@&  @@@@@,/@@@@@@@.       @@@@ @@
        @@@@@@@@@@@@@@@@@@@@*.//////////////////////////////.%@@@@@@@/   /@@@  @@@@@* @@@@@@@@@(*#@@@@* ./*@
        @@@@@@@@@@@@@@@@*.//////////////////////////////,//// @@@@@@@     //  (@@@@%  . (@@@@@&#*  .*//,  &@
        @@@@@@@@@@@@%.//////////////////////////////////*,///, &@@@@@.      ,@@@@@.   //////////*.  .*/* @@@
        @@@@@@@@@(.//////////////////////////////// ./////,,///. ,%@@@@@@@@@@@#.  ////   ////////////////*/@

        .______    _______ .______    _______   _______      ___           _______.
        |   _  \\  |   ____||   _  \\  |   ____| /  _____|    /   \\         /       |            YOUR MATRIX'S
        |  |_)  | |  |__   |  |_)  | |  |__   |  |  __     /  ^  \\       |   (----`            BEST FRIEND
        |   ___/  |   __|  |   ___/  |   __|  |  | |_ |   /  /_\\  \\       \\   \\    
        |  |      |  |____ |  |      |  |____ |  |__| |  /  _____  \\  .----)   |               BY ADDIN, LUIS,
        | _|      |_______|| _|      |_______| \\______| /__/     \\__\\ |_______/                AND ILHAM              
        """);
    }

    public static void BackToMainMenu(){
        System.out.println("\nKembali ke menu utama...");
        MainMenu();
    }

    public static Matrix readSPLMain(){
        System.out.println("\nPilih cara kalian untuk input matrix!\n");
        System.out.printf("""
                            PILIHAN METODE INPUT MATRIX
        (1) Melalui keyboard
        (2) Melalui File

        (0) Kembali ke menu utama
        """);
        System.out.printf("Ayo dipilih.. (Pilihan 0-2): ");
        int in = MainScanner.sc.nextInt();

        while (in < 0 || in > 2){
            System.out.printf("Punten, pilihannya masih salah. Ayo coba lagi (Pilihan 0-2): ");
            in = MainScanner.sc.nextInt();
        }

        Matrix mOut;

        switch (in) {
            case 1:
                mOut = MatrixOperations.readSPL();
                return mOut;
            case 2:
                System.out.println("\nMasukkan path ke file yang ingin dibaca: ");
                String path = MainScanner.sc.next();
                try {
                    mOut = Converter.readTxt(path);
                    return mOut;
                } catch (FileNotFoundException e) {
                    System.out.println("\nFile tidak ditemukan");
                    BackToMainMenu();
                    return null;
                }
            case 0:
                BackToMainMenu();
                return null;
            default:
                throw new IllegalStateException("Unexpected value: " + in);
        }
    }

    public static Matrix readMatrixMain(){
        System.out.println("\nPilih cara kalian untuk input matrix!\n");
        System.out.printf("""
                            PILIHAN METODE INPUT MATRIX
        (1) Melalui keyboard
        (2) Melalui File

        (0) Kembali ke menu utama
        """);
        System.out.printf("Ayo dipilih.. (Pilihan 0-2): ");
        int in = MainScanner.sc.nextInt();

        while (in < 0 || in > 2){
            System.out.printf("Punten, pilihannya masih salah. Ayo coba lagi (Pilihan 0-2): ");
            in = MainScanner.sc.nextInt();
        }

        Matrix mOut;

        switch (in) {
            case 1:
                mOut = MatrixOperations.readMatrix();
                return mOut;
            case 2:
                System.out.println("\nMasukkan path ke file yang ingin dibaca: ");
                String path = MainScanner.sc.next();
                try {
                    mOut = Converter.readTxt(path);
                    return mOut;
                } catch (FileNotFoundException e) {
                    System.out.println("\nFile tidak ditemukan");
                    return null;
                }
            case 0:
                BackToMainMenu();
                return null;
            default:
                throw new IllegalStateException("Unexpected value: " + in);
        } 
    }

    public static void SimpanSPLKeFile(Solution sol){
        System.out.println("\nApakah kamu ingin menyimpan output ke file?\n");

        System.out.printf("Pilih (Pilihan y/n): ");
        String in = MainScanner.sc.next();

        while (!in.equals("y") && !in.equals("n")) {
            System.out.printf("\nInput tidak valid. Masukkan kembali: ");
            in = MainScanner.sc.next();
        }

        if (in.equals("y")) {
            System.out.printf("""

                Masukkan nama file (file akan tersimpan pada folder ../test/output/<namafile>.txt):
                """);
            String path = "../test/output/" + MainScanner.sc.next();

            System.out.println("\nMenyimpan file...");
            int lastIdx = sol.listVarMat.length -1;
            Converter.saveFileSPL(path, sol, lastIdx);
            System.out.println("File berhasil tersimpan (poggers)");
        } 
    }
}
