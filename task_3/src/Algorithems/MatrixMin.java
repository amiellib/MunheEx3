package Algorithems;

public class MatrixMin {

		double min_value;
		int row;
		int column;
		
		public MatrixMin() {
			super();
		}
		public MatrixMin(double min_value, int row, int column) {
			super();
			this.min_value = min_value;
			this.row = row;
			this.column = column;
		}
		public double getMin_value() {
			return min_value;
		}
		public void setMin_value(double min_value) {
			this.min_value = min_value;
		}
		public int getRow() {
			return row;
		}
		public void setRow(int row) {
			this.row = row;
		}
		public int getColumn() {
			return column;
		}
		public void setColumn(int column) {
			this.column = column;
		}
		@Override
		public String toString() {
			return "MatrixMin [min_value=" + min_value + ", row=" + row + ", column=" + column + "]";
		}
		
	
}
