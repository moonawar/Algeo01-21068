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

        switch (in) {
            case 1:
                MainMenu();
                break;
            case 2:
                MainMenu();
                break;
            case 3:
                MainMenu();
                break;
            case 4:
                MainMenu();
                break;
            case 0:
                System.out.println("\nKembali ke menu utama...");
                MainMenu();
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

        switch (in) {
            case 1:
                MainMenu();
                break;
            case 2:
                MainMenu();
                break;
            case 0:
            System.out.println("\nKembali ke menu utama...");
                MainMenu();
                break;
        }
    }

    public static void InverseMenu(){
        System.out.println("\nIni adalah hari kebalikan! Sekarang, pilih metode inverse matriks yang kamu inginkan!\n");
        System.out.printf("""
                            PILIHAN METODE INVERSE MATRIKS
        (1) Metode Reduksi Baris (Input | Identitas)
        (2) Metode Determinan Adjoin

        (0) Kembali ke menu utama
        """);
        System.out.printf("Ayo dipilih.. (Pilihan 0-2): ");
        int in = MainScanner.sc.nextInt();

        while (in < 0 || in > 2){
            System.out.printf("Punten, pilihannya masih salah. Ayo coba lagi (Pilihan 0-4): ");
            in = MainScanner.sc.nextInt();
        }

        switch (in) {
            case 1:
                MainMenu();
                break;
            case 2:
                MainMenu();
                break;
            case 0:
                System.out.println("\nKembali ke menu utama...");
                MainMenu();
                break;
        }
    }

    public static void PolinomInterMenu(){
        System.out.println("\nInterpolasi polinom (please don't hurt my family).\n");
    }

    public static void BicubicInterMenu(){
        System.out.println("\nUwoghh! Kamu tahu apa itu bicubic interpolation!??? Kamu adalah orang yang sangat berbudaya ˚‧º·(˃̣̣̥o˂̣̣̥)‧º·\n");
    }

    public static void RegresiMenu(){
        System.out.println("""
            \nRegresi linear kaya praktikum fisika, tapi yang ini berganda.\n
            """);
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
}
