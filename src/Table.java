
public class Table {
  public int bottom,left = 0;
  public int top,right = 5;
  public Table(int top,int right,int bottom,int left) {
	  this.top = top;
	  this.bottom = bottom;
	  this.left = left;
	  this.right = right;
  }
  public int getBottom() {
	  return this.bottom;
  }
  public int getTop() {
	  return this.top;
  }
  public int getRight() {
	  return this.right;
  }
  public int getLeft() {
	  return this.left;
  }
  public static Table initTable() {
	  return new Table(5,5,0,0);
  }
}
