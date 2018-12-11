package Algorithems;

import java.util.Arrays;

public class MatrixMin {

	double[][] matrix ;
	int[] array_min;
	public MatrixMin(double[][] matrix, int[] array_min) {
		super();
		this.matrix = matrix;
		this.array_min = array_min;
	}
	public double[][] getMatrix() {
		return matrix;
	}
	public void setMatrix(double[][] matrix) {
		this.matrix = matrix;
	}
	public int[] getArray_min() {
		return array_min;
	}
	public void setArray_min(int[] array_min) {
		this.array_min = array_min;
	}
	@Override
	public String toString() {
		return "MatrixMin [matrix=" + Arrays.toString(matrix) + ", array_min=" + Arrays.toString(array_min) + "]";
	}
		

		
	
}