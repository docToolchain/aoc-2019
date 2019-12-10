class Vector2 {
  double x;
  double y;

  Vector2(this.y, this.x);

  @override
  int get hashCode => x.hashCode * y.hashCode;

  @override
  bool operator ==(a) {
    return (this.x == a.x) && (this.y == a.y);
  }

  @override
  String toString() {
    return '$y, $x';
  }
}