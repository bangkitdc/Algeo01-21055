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
        } while (Y == 'Y' || Y == 'y');
        exit();
    }

    public static void mainMenu() throws IOException {
        println("Selamat Datang di Matrix Calculator");
        println("=============== Menu ==============");
        println("Aplikasi ini akan menyelesaikan persoalan:");
        println("[1] Sistem Persamaan Linear (SPL)");
        println("[2] Determinan");
        println("[3] Matriks Balikan (Inverse)");
        println("[4] Interpolasi Polinom");
        println("[5] Interpolasi Bicubic");
        println("[6] Regresi Linear Berganda");
        println("[7] Image Resizer");
        println("[8] Keluar");
        println("Masukkan pilihan: ");

        Matrix m = new Matrix();

        int menu = select(1, 8);
        int input, output;

        String[] inputRelativePaths = new String[] {
            "../test/txt/inputs/SPL/",
            "../test/txt/inputs/determinan/",
            "../test/txt/inputs/inverse/",
            "../test/txt/inputs/polinom/",
            "../test/txt/inputs/bikubik/",
            "../test/txt/inputs/regresi/",
            "../test/img/",
        };

        String[] outputRelativePaths = new String[] {
            "../test/txt/outputs/SPL/",
            "../test/txt/outputs/determinan/",
            "../test/txt/outputs/inverse/",
            "../test/txt/outputs/polinom/",
            "../test/txt/outputs/bikubik/",
            "../test/txt/outputs/regresi/",
            "../test/img/",
        };
        
        /* Input */
        switch (menu) {
            case 1: // SPL
                inputFileConsole();
                input = select(1, 2);
                switch (input) {
                    case 1:
                        m.inputFileMatrix(inputRelativePaths[menu - 1]);
                        break;
                    case 2:
                        SolveSPL.inputConsole(m);
                        break;
                    default:
                        break;
                }
                break;  
            case 2: // Determinan
                inputFileConsole();
                input = select(1, 2);
                switch (input) {
                    case 1:
                        m.inputFileMatrix(inputRelativePaths[menu - 1]);
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
                input = select(1, 2);
                switch (input) {
                    case (1):
                        m.inputFileMatrix(inputRelativePaths[menu - 1]);
                        break;
                    case 2:
                        m.inputSquaredMatrix();
                        break;
                    default:
                        break;
                }

                break;
            case 4: // Interpolasi

                inputFileConsole();
                input = select(1, 2);
                switch (input) {
                    case (1):
                        Interpolation.problem.inputNewProblem(inputRelativePaths[menu - 1]);
                        break;
                    case 2:
                        Interpolation.problem.inputNewProblem();
                        break;
                    default:
                        break;
                }

                break;
            case 5: // Interpolasi Bicubic
                inputFileConsole();
                input = select(1, 2);
                switch (input) {
                    case 1:
                        m.inputFileMatrix(inputRelativePaths[menu - 1]);
                        break;
                    case 2:
                        m.input4x4Matrix();
                        break;
                    default:
                        break;
                }
                break;
            case 6: // Regresi
            	inputFileConsole();
                input = select(1, 2);
                switch (input) {
                case 1:
                    m.inputFileMatrix(inputRelativePaths[menu - 1]);
                    break;
                case 2:
                    m = Regression.regressionInput();
                    break;
                default:
                    break;
                }
                break;
            case 7: // Image Resizer
                ImageResize.inputImage(inputRelativePaths[menu - 1]);
                break;
            case 8:
                exit();
                break;
            default:
                break;
        }

        /* Output */
        switch (menu) {
            case 1: // SPL
                outputFileConsole();
                output = select(1, 2);
                switch (output) {
                    case 1:
                        SolveSPL.splFile(m, outputRelativePaths[menu - 1]);
                        break;
                    case 2:
                        SolveSPL.splConsole(m);
                        break;
                    default:
                        break;
                }
                break;
            case 2: // Determinan
                outputFileConsole();

                output = select(1, 2);
                switch (output) {
                    case 1:
                        Determinan.detFile(m, outputRelativePaths[menu - 1]);
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
                output = select(1, 2);

                switch (output) {
                    case 1:
                        Inverse.outputInverse(m, outputRelativePaths[menu - 1]);
                        break;
                    case 2:
                        Inverse.outputInverse(m);
                        break;
                    default:
                        break;
                }

                break;
            case 4:
    
                outputFileConsole();
                output = select(1, 2);
                switch (output) {
                    case 1:
                        Interpolation.problem.createOutputFile(outputRelativePaths[menu - 1]);
                        break;
                    case 2:
                        Interpolation.problem.displayInterpolation();
                        break;
                    default:
                        break;
                }
                break;

            case 5: // Interpolasi Bicubic
                outputFileConsole();
                output = select(1, 2);
                switch (output) {
                    case 1:
                        BicubicInterpolation.bicubicFile(m, outputRelativePaths[menu - 1]);
                        break;
                    case 2:
                        BicubicInterpolation.bicubicConsole(m);
                        break;
                    default:
                        break;
                }
                break;
            case 6: // Regresi
            	outputFileConsole();
                output = select(1, 2);
                switch (output) {
                    case 1:
                        Regression.regFile(m, outputRelativePaths[menu - 1]);
                        break;
                    case 2:
                        Regression.regConsole(m);
                        break;
                    default:
                        break;
                }
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
        println("======== Pilih Jenis Input ========");
        println("[1] File");
        println("[2] Console");
        println("Masukkan pilihan: ");
    }

    public static void outputFileConsole() {
        println("");
        println("======= Pilih Jenis Output ========");
        println("[1] File");
        println("[2] Console");
        println("Masukkan pilihan: ");
    }

    public static void showChoice(String title, String[] methods, int length) {
        println("");
        println("====== Pilih Jenis " + title + " ======");
        
        for (int i = 0; i < length; i++)
        {
            println("[" + Integer.toString(i + 1) + "] " + methods[i]);
        }

        println("Masukkan pilihan: ");
    }

    public static void exit() {
        println("\nTerima kasih telah menggunakan Matrix Calculator :)");
        System.exit(0);
    }
}
