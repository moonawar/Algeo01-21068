import java.io.FileNotFoundException;

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
                return;
            }


        switch (in) {
            case 1:
                solution = SPLGauss.gaussSPL(mInput);
                Solution.displaySolution(solution, mInput);
                BackToMainMenu();
                break;
            case 2:
                solution = SPLGauss.gaussJordanSPL(mInput);
                Solution.displaySolution(solution, mInput);
                BackToMainMenu();
                break;
            case 3:
                SPLInverse.SPLWithInverse(Converter.augmentedToMatrix(mInput, true), Converter.augmentedToMatrix(mInput, false), true);
                BackToMainMenu();
                break;
            case 4:
                SPLCrammer.cramer(Converter.augmentedToMatrix(mInput, true), Converter.augmentedToMatrix(mInput, false));
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


        switch (in) {
            case 1:
                System.out.println("\nDeterminan dari matriks tersebut dengan metode reduksi baris adalah: " + DetReduction.determinanGauss(mInput));
                BackToMainMenu();
                break;
            case 2:
                System.out.println("\nDeterminan dari matriks tersebut dengan metode ekspansi kofaktor adalah: " + DetReduction.determinanGauss(mInput));
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


        switch (in) {
            case 1:
                System.out.println("\nMatriks balikan dari matriks tersebut dengan metode reduksi baris adalah: ");
                MatrixOperations.displayMatrix(InverseMat.InverseWithRed(mInput));
                BackToMainMenu();
                break;
            case 2:
                System.out.println("\nMatriks balikan dari matriks tersebut dengan metode determinan dan adjoin adalah: ");
                MatrixOperations.displayMatrix(InverseMat.InverseWithAdjoin(mInput));
                BackToMainMenu();
                break;
            case 0:
                BackToMainMenu();
                break;
        }
    }

    public static void PolinomInterMenu(){
        System.out.println("\nInterpolasi polinom.\n");

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
                break;
            case 2:
                System.out.println("\nMasukkan path ke file yang ingin dibaca: ");
                String path = MainScanner.sc.next();
                try {
                    mInput = Converter.readTxt(path);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            case 0:
                BackToMainMenu();
            default:
                throw new IllegalStateException("Unexpected value: " + in);
        }

        Interpolation.PolinomInterpolation(mInput);
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

        Matrix mInput;

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
                    e.printStackTrace();
                }
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

        Interpolation.BicubicInterpolation(mInput, inX, inY);
    }

    public static void RegresiMenu(){
        System.out.println("""
            \nRegresi linear kaya praktikum fisika, tapi yang ini berganda.\n
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
                    break;
                case 2:
                    System.out.println("\nMasukkan path ke file yang ingin dibaca: ");
                    String path = MainScanner.sc.next();
                    try {
                        mInput = Converter.readTxt(path);
                        Matrix mInputX = new Matrix(mInput.rowEff - 1, mInput.colEff);
                        for (int i = 0; i <= mInputX.getLastIdxRow(); i++) {
                            for (int j = 0; j <= mInputX.getLastIdxCol(); j++) {
                                mInputX.setElmt(i, j, mInput.getElmt(i, j));
                            }
                        }

                        Regression.MultipleLinearRegression(mInputX, mInput.rowEff, mInput.colEff);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
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
                    e.printStackTrace();
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
                        e.printStackTrace();
                    }
                case 0:
                    BackToMainMenu();
                    return null;
                default:
                    throw new IllegalStateException("Unexpected value: " + in);
            } 
    }
}
