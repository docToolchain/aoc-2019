class Asteroid {
  int x;
  int y;
  int numOfVisibleAsteroids = 0;

  Asteroid(this.x, this.y);

  @override
  int get hashCode => x.hashCode * y.hashCode;

  @override
  bool operator ==(a) {
    return (this.x == a.x) && (this.y == a.y);
  }

  @override
  String toString() {
    return '($x, $y) -> Sees $numOfVisibleAsteroids';
  }
}