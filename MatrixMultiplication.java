import java.util.Scanner;

public class MatrixMultiplication {

    public static int pow(int a, int k) {
        int st = 1;
        for (int i = 0; i < k; i++) {
            st *= a;
        }
        return st;
    }

    public static int max(int a, int b) {
        return a < b ? b : a;
    }

    public static int findClosest2k(int a) {
        int k = 0;
        while (a > pow(2,k)) {
            k++;
        }
        return (int)pow(2,k);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String tip = sc.next();
        int param = 0;
        if (!tip.equals("os")) {
            param = sc.nextInt();
        }
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] matrika1 = new int[n][m];
        Matrix mat1 = new Matrix(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrika1[i][j] = sc.nextInt();
                mat1.setV(i, j, matrika1[i][j]);
            }
        }

        n = sc.nextInt();
        m = sc.nextInt();

        int[][] matrika2 = new int[n][m];
        Matrix mat2 = new Matrix(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrika2[i][j] = sc.nextInt();
                mat2.setV(i, j, matrika2[i][j]);
            }
        }

        if (tip.equals("os")) {
            // os(matrika1, matrika2);
            mat1.osnovno(mat2).izpis();
        } else if (tip.equals("bl")) {
            mat1.blocno(mat2, param).izpis();
        } else if (tip.equals("dv")) {
            int maxn = max(mat1.n, mat2.n);
            int maxm = max(mat1.n2, mat2.n2);
            int max = max(maxn, maxm);
            max = findClosest2k(max);
            Matrix nova1 = new Matrix(max, max);
            nova1.putSubmatrix(0, 0, mat1);
            Matrix nova2 = new Matrix(max, max);
            nova2.putSubmatrix(0, 0, mat2);
            nova1.dv(nova2, param).izpis();
        } else if (tip.equals("st")) {
            int maxn = max(mat1.n, mat2.n);
            int maxm = max(mat1.n2, mat2.n2);
            int max = max(maxn, maxm);
            max = findClosest2k(max);
            Matrix nova1 = new Matrix(max, max);
            nova1.putSubmatrix(0, 0, mat1);
            Matrix nova2 = new Matrix(max, max);
            nova2.putSubmatrix(0, 0, mat2);
            nova1.multStrassen(nova2, param).izpis();
        }

        sc.close();
    }

}

