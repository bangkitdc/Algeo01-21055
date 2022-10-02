package mtrx.Utility;

import java.io.*;

import mtrx.Matrix.*;
import mtrx.Methods.*;

public class Menu extends Utils{
    static InputStreamReader streamReader = new InputStreamReader(System.in);
    BufferedReader bufferedReader = new BufferedReader(Menu.streamReader);

    public static void menuLoop() throws IOException {
        char Y = '\0';
        do {
            mainMenu();
            println("");
            println("Apakah ingin melanjutkan? [Y/N]: ");
            try {
                Y = inputYN();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (Y == 'Y');
        exit();
    }

    public static void mainMenu() throws IOException {
        println("Selamat Datang di Matrix Calculator");
        println("=========== Menu ===========");
        println("Aplikasi ini akan menyelesaikan persoalan:");
        println("[1] Sistem Persamaan Linear (SPL)");
        println("[2] Determinan");
        println("[3] Matriks Balikan (Inverse)");
        println("[4] Interpolasi Polinom");
        println("[5] Interpolasi Bicubic");
        println("[6] Regresi Linear Berganda");
        println("[7] Image Resizer");
        println("[8] Keluar");
        println("");
        println("Masukkan pilihan: ");
        int input = select(1, 8);
        int selection;

        Matrix m = new Matrix();
        
        /* Input */
        switch (input) {
            case 1: // SPL
                
                break;
            case 2: // Determinan
                inputFileConsole();
                selection = select(1, 2);
                switch (selection) {
                    case 1:
                        m.inputFileSquaredMatrix();
                        break;
                    case 2:
                        m.inputSquaredMatrix();
                        break;
                    default:
                        break;
                }
                break;
            case 3: // Inverse
                
                inputFileConsole();
                selection = select(1, 2);
                switch (selection) {
                    case (1):
                        m.inputSquaredMatrix(1);
                        break;
                    case 2:
                        m.inputSquaredMatrix(2);
                        break;
                    default:
                        break;
                }

                break;
            case 4: // Interpolasi

                inputFileConsole();
                selection = select(1, 2);
                switch (selection) {
                    case (1):
                        
                        break;
                    case 2:
                        Interpolation.problem.InputNewProblem();
                        break;
                    default:
                        break;
                }

                break;
            case 5: // Interpolasi Bicubic

                break;
            case 6:

                break;
            case 7:

                break;
            case 8:
                exit();
                break;
            default:
                break;
        }

        /* Output */
        switch (input) {
            case 1: // SPL

                break;
            case 2: // Determinan
                outputFileConsole();
                selection = select(1, 2);
                switch (selection) {
                    case 1:
                        Determinan.detFile(m);
                        break;
                    case 2:
                        Determinan.detConsole(m);
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                outputFileConsole();
                selection = select(1, 2);

                switch (selection) {
                    case 1:
                    
                        break;
                    case 2:
             
                        break;
                    default:
                        break;
                }

                break;
            case 4:
                outputFileConsole();
                selection = select(1, 2);

                switch (selection) {
                    case 1:
                    
                        break;
                    case 2:
                        Interpolation.problem.displayInterpolation();
                        break;

                    default:
                        break;
                }

                break;
            case 5: // Interpolasi Bicubic

                break;
            case 6:

                break;
            case 7:

                break;
            case 8:
                exit();
                break;
            default:
                break;
        }
    }

    public static void inputFileConsole() {
        println("");
        println("====== Pilih Jenis Input ======");
        println("[1] File");
        println("[2] Console");
        println("Masukkan pilihan: ");
    }

    public static String outputFile() {
        InputStreamReader streamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(streamReader);

        println("");
        println("Masukkan name file output: ");
        print("> ");

        String fileName = new String();

        try {
            fileName = bufferedReader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public static void outputFileConsole() {
        println("");
        println("====== Pilih Jenis Output ======");
        println("[1] File");
        println("[2] Console");
        println("Masukkan pilihan: ");
    }

    public static void showChoice(String title, String[] methods, int length) {
        println("");
        println("====== Pilih Jenis" + title + " ======");
        
        for (int i = 0; i < length; i++)
        {
            println("[" + Integer.toString(i + 1) + "] " + methods[i]);
        }

        println("Masukkan pilihan: ");
    }

    public static void exit() {
        println("\nTerima kasih telat menggunakan aplikasi matrix :D");
        System.exit(0);
    }
}
