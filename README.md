# Matrix-Multiplication
Different algorithms for matrix multiplication implemented in Java with Matrix data structure.

## About
In the Matrix class there are implementations of 4 different matrix multiplication algorithms:  
* Classic multiplication ("os")
* Block multiplication ("bl")
* Naive divide and conquer ("dv")
* Strassen algorithm ("st")  
  


  
## Input
In the input directory one can find some input examples that follow the next specifications:  

* First, there is a string that defines which algorithm is to be used (os, bl, dv ali st)
* Then, there is a int parameter (except for "os")
* Then, the two matrices. For each matrix we get the dimensions (n rows, m columns), followed by n*m int numbers

### Disclaimer
Because this was an university project, the outputs do not only display the final matrix,  
but also some results in the middle of the algorithm.  
The Math library was not allowed, so the min, max and pow methods had to be coded.  
Also, the MatrixMultiplication.java in the main methods contains a lot of code in the if statements, because of the rules of the project, defined by the university.