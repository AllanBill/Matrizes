import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class matrizes {

    // Lê matriz quadrada de arquivo .txt
    public static int[][] lerMatrizTxt(Scanner sc, int n, String nome) {
        int[][] m = new int[n][n];
        System.out.println("📄 Lendo matriz " + nome + " do .txt:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                m[i][j] = sc.nextInt();
        return m;
    }

    // Lê matriz quadrada de arquivo .xlsx
    public static int[][] lerMatrizExcel(Sheet sheet, int startRow, int n, String nome) {
        int[][] m = new int[n][n];
        System.out.println("📊 Lendo matriz " + nome + " do .xlsx:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                Cell cell = sheet.getRow(startRow + i).getCell(j);
                if (cell != null && cell.getCellType() == CellType.NUMERIC)
                    m[i][j] = (int) cell.getNumericCellValue();
                else
                    m[i][j] = 0; // padrão se estiver vazio
            }
        return m;
    }

    // Imprime matriz
    public static void imprimirMatriz(int[][] m, String nome) {
        System.out.println("\n🧮 Matriz " + nome + ":");
        for (int[] linha : m) {
            for (int elem : linha)
                System.out.print(elem + "\t");
            System.out.println();
        }
    }

    // Multiplica duas matrizes quadradas
    public static int[][] multiplicar(int[][] a, int[][] b) {
        int n = a.length;
        int[][] r = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < n; k++)
                    r[i][j] += a[i][k] * b[k][j];
        return r;
    }

    public static void main(String[] args) {
        try {
            String caminho = "matrizes.txt"; // ou "matrizes.txt"

            int n;
            int[][] M1, V1;

            if (caminho.endsWith(".txt")) {
                Scanner sc = new Scanner(new File(caminho));
                n = sc.nextInt(); // primeira linha deve conter o valor de n
                M1 = lerMatrizTxt(sc, n, "M1");
                V1 = lerMatrizTxt(sc, n, "V1");
                sc.close();
            } else if (caminho.endsWith(".xlsx")) {
                FileInputStream fis = new FileInputStream(caminho);
                Workbook workbook = new XSSFWorkbook(fis);
                Sheet sheet = workbook.getSheetAt(0);

                // Célula A1 (linha 0, coluna 0) deve conter o valor de n
                Cell cell = sheet.getRow(0).getCell(0);
                if (cell == null || cell.getCellType() != CellType.NUMERIC) {
                    System.out.println("Célula A1 deve conter o valor de n.");
                    workbook.close();
                    return;
                }
                n = (int) cell.getNumericCellValue();

                // M1 ocupa da linha 1 até linha n
                M1 = lerMatrizExcel(sheet, 1, n, "M1");
                // V1 ocupa da linha (1 + n) até (1 + 2n - 1)
                V1 = lerMatrizExcel(sheet, 1 + n, n, "V1");

                workbook.close();
            } else {
                System.out.println("❌ Formato de arquivo não suportado.");
                return;
            }

            // Impressão e multiplicação
            imprimirMatriz(M1, "M1");
            imprimirMatriz(V1, "V1");

            int[][] V2 = multiplicar(M1, V1);
            imprimirMatriz(V2, "V2");

        } catch (FileNotFoundException e) {
            System.out.println("❌ Arquivo não encontrado: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Erro durante a execução:");
            e.printStackTrace();
        }
    }
}
