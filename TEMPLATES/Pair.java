

/**

Minimized version of Pair:
(suitable for copy-paste into solution)

public static class Pair<X, Y> {
	public X x;public Y y;public Pair(X x, Y y) {this.x = x;this.y = y;}
	public static Pair<Integer, Integer> ofInt(int x, int y) {return new Pair<Integer, Integer>(x,y);}
	public static <X,Y> Pair<X,Y> of(X x,Y y){return new Pair<X, Y>(x, y);}
	public X getX() {return x;} public void setX(X x) {this.x = x;}
	public Y getY() {return y;} public void setY(Y y) {this.y = y;}
	@Override public int hashCode() {final int prime = 31;int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());return result;}
	@Override public boolean equals(Object obj) {
		if (this == obj) return true; if (obj == null) return false;
		if (getClass() != obj.getClass()) return false; Pair<?,?> other = (Pair<?,?>) obj;
		if (x == null) { if (other.x != null) return false; } else if (!x.equals(other.x)) return false;
		if (y == null) { if (other.y != null) return false; } else if (!y.equals(other.y)) return false; return true;}
	@Override public String toString() { return "Pair<" + x + ", " + y + ">"; }}


*/


public class Pair<X, Y> {
	public X x;
	public Y y;
	public Pair(X x, Y y) {
		this.x = x;
		this.y = y;
	}
	public static Pair<Integer, Integer> ofInt(int x, int y) {
		return new Pair<Integer, Integer>(x,y);
	}
	public static <X, Y> Pair<X, Y> of(X x, Y y) {
		return new Pair<X, Y>(x, y);
	}
	public X getX() {
		return x;
	}
	public void setX(X x) {
		this.x = x;
	}
	public Y getY() {
		return y;
	}
	public void setY(Y y) {
		this.y = y;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair<?,?> other = (Pair<?,?>) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Pair<" + x + ", " + y + ">";
	}
	
}

