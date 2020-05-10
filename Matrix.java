class Matrix {

    private int[][] m;

    public int n; // only square matrices
    public int n2;

    public Matrix(int n, int n2) {

        this.n = n;
        this.n2 = n2;

        m = new int[n][n2];

    }

    // set value at i,j
    public void setV(int i, int j, int val) {

        m[i][j] = val;

    }

    // get value at index i,j
    public int v(int i, int j) {

        return m[i][j];

    }

    // return a square submatrix from this
    public Matrix getSubmatrix(int startRow, int startCol, int dim, int dim2) {
        int acti = dim;
        int actj = dim2;
        if (startRow + dim >= n)
            acti = n - startRow;
        if (startCol + dim2 >= n2)
            actj = n2 - startCol;

        Matrix subM = new Matrix(acti, actj);

        for (int i = 0; i < acti; i++)

            for (int j = 0; j < actj; j++)

                // subM.setV(i, j, m[startRow + i][startCol + j]);
                subM.setV(i, j, v(startRow + i, startCol + j));

        return subM;

    }

    // write this matrix as a submatrix from b (useful for the result of
    // multiplication)
    public void putSubmatrix(int startRow, int startCol, Matrix b) {

        for (int i = 0; i < b.n; i++)

            for (int j = 0; j < b.n2; j++)

                setV(startRow + i, startCol + j, b.v(i, j));

    }

    private int min(int a, int b) {
        return a > b ? b : a;
    }


    // matrix addition
    public Matrix sum(Matrix b) {

        Matrix c = new Matrix(min(n, b.n), min(n2, b.n2));

        for (int i = 0; i < n && i < b.n; i++) {

            for (int j = 0; j < n2 && j < b.n2; j++) {

                c.setV(i, j, m[i][j] + b.v(i, j));

            }

        }

        return c;

    }

    // matrix subtraction
    public Matrix sub(Matrix b) {

        Matrix c = new Matrix(n, n2);

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                c.setV(i, j, m[i][j] - b.v(i, j));

            }

        }

        return c;

    }

    public int sumAll() {
        int fin = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n2; j++) {
                fin += v(i, j);
            }
        }
        return fin;
    }

    public void izpis() {
        System.out.printf("DIMS: %dx%d\n", n, n2);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n2; j++) {
                System.out.print(v(i, j) + " ");
            }
            System.out.println();
        }
    }

    public Matrix osnovno(Matrix mat2) {
        Matrix res = new Matrix(n, mat2.n2);
        int temp = 0;
        for (int i = 0; i < res.n; i++) {
            for (int j = 0; j < res.n2; j++) {
                temp = 0;
                for (int f = 0; f < mat2.n; f++) {
                    temp += v(i, f) * mat2.v(f, j);

                }
                res.setV(i, j, temp);
            }
        }

        return res;
    }

    public Matrix blocno(Matrix mat2, int dim) {
        Matrix res = new Matrix(n, mat2.n2);

        for (int i = 0; i < n; i += dim) {
            for (int j = 0; j < mat2.n2; j += dim) {
                Matrix temp = new Matrix(dim, dim);
                for (int f = 0; f < n2; f += dim) {
                    // System.out.println("----------");
                    Matrix a = this.getSubmatrix(i, f, dim, dim);
                    // a.izpis();
                    Matrix b = mat2.getSubmatrix(f, j, dim, dim);
                    // b.izpis();
                    Matrix multTemp = a.osnovno(b);
                    // multTemp.izpis();
                    System.out.println(multTemp.sumAll());

                    temp = temp.sum(multTemp);

                }
                res.putSubmatrix(i, j, temp);
            }
        }

        return res;
    }

    public Matrix dv(Matrix nova, int param) {
		if (n == 1 || n == param) {
			Matrix res = new Matrix(param, param);
            res = this.osnovno(nova);
			return res;
		}

		Matrix a = getSubmatrix(0, 0, n / 2, n / 2);
		Matrix b = getSubmatrix(0, n / 2, n / 2, n / 2);
		Matrix c = getSubmatrix(n / 2, 0, n / 2, n / 2);
		Matrix d = getSubmatrix(n / 2, n / 2, n / 2, n / 2);

		Matrix e = nova.getSubmatrix(0, 0, nova.n / 2, n / 2);
		Matrix f = nova.getSubmatrix(0, nova.n / 2, nova.n / 2, n / 2);
		Matrix g = nova.getSubmatrix(nova.n / 2, 0, nova.n / 2, n / 2);
        Matrix h = nova.getSubmatrix(nova.n / 2, nova.n / 2, nova.n / 2, n / 2);

        Matrix zmn1 = a.dv(e, param);
        System.out.println(zmn1.sumAll());
        Matrix zmn2 = b.dv(g, param);
        System.out.println(zmn2.sumAll());
        Matrix zmn3 = a.dv(f, param);
        System.out.println(zmn3.sumAll());
        Matrix zmn4 = b.dv(h, param);
        System.out.println(zmn4.sumAll());
        Matrix zmn5 = c.dv(e, param);
        System.out.println(zmn5.sumAll());
        Matrix zmn6 = d.dv(g, param);
        System.out.println(zmn6.sumAll());
        Matrix zmn7 = c.dv(f, param);
        System.out.println(zmn7.sumAll());
        Matrix zmn8 = d.dv(h, param);
        System.out.println(zmn8.sumAll());
        
        
        
        Matrix t1 = zmn1.sum(zmn2);
        
        Matrix t2 = zmn3.sum(zmn4);
        
        Matrix t3 = zmn5.sum(zmn6);
        
        Matrix t4 = zmn7.sum(zmn8);
        
        

		Matrix rez = new Matrix(n, n);
		rez.putSubmatrix(0, 0, t1);
		rez.putSubmatrix(0, n / 2, t2);
		rez.putSubmatrix(n / 2, 0, t3);
		rez.putSubmatrix(n / 2, n / 2, t4);

		return rez;

	}
    
    public Matrix multStrassen(Matrix nova, int cutoff) {
		if (n == cutoff) {
			return osnovno(nova);
		}

		Matrix a11 = getSubmatrix(0, 0, n / 2, n / 2);
		Matrix a12 = getSubmatrix(0, n / 2, n / 2, n / 2);
		Matrix a21 = getSubmatrix(n / 2, 0, n / 2, n / 2);
		Matrix a22 = getSubmatrix(n / 2, n / 2, n / 2, n / 2);

		Matrix b11 = nova.getSubmatrix(0, 0, nova.n / 2, n / 2);
		Matrix b12 = nova.getSubmatrix(0, nova.n / 2, nova.n / 2, n / 2);
		Matrix b21 = nova.getSubmatrix(nova.n / 2, 0, nova.n / 2, n / 2);
        Matrix b22 = nova.getSubmatrix(nova.n / 2, nova.n / 2, nova.n / 2, n / 2);
        
        Matrix m1 = a11.multStrassen(b12.sub(b22), cutoff);
        System.out.printf("%d\n", m1.sumAll());
        Matrix m2 = a11.sum(a12).multStrassen(b22, cutoff);
        System.out.printf("%d\n", m2.sumAll());
        Matrix m3 = a21.sum(a22).multStrassen(b11, cutoff);
        System.out.printf("%d\n", m3.sumAll());
        Matrix m4 = a22.multStrassen(b21.sub(b11), cutoff);
        System.out.printf("%d\n", m4.sumAll());
        Matrix m5 = a11.sum(a22).multStrassen(b11.sum(b22), cutoff);
        System.out.printf("%d\n", m5.sumAll());
        Matrix m6 = a12.sub(a22).multStrassen(b21.sum(b22), cutoff);
        System.out.printf("%d\n", m6.sumAll());
        Matrix m7 = a11.sub(a21).multStrassen(b11.sum(b12), cutoff);
        System.out.printf("%d\n", m7.sumAll());
		

		Matrix rez = new Matrix(n, n);
		rez.putSubmatrix(0, 0, m5.sum(m4).sub(m2).sum(m6));
		rez.putSubmatrix(0, n / 2, m1.sum(m2));
		rez.putSubmatrix(n / 2, 0, m3.sum(m4));
		rez.putSubmatrix(n / 2, n / 2, m1.sum(m5).sub(m3).sub(m7));

		return rez;
	}

}