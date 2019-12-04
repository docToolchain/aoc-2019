class Point
   @x = 0
   @y = 0

  attr_accessor :x, :y

  def initialize(x, y)
    @x = x
    @y = y
  end

  def self.from_s(string)
    parts = string.split(",")
    x = parts[0][3..-1].to_i
    y = parts[1][2..-2].to_i
    return Point.new(x, y)
  end

  def manhattan(p2)
    x = (@x - p2.x).abs
    y = (@y - p2.y).abs
    x + y
  end

  def +(p2)
    return Point.new(@x + p2.x, @y + p2.y)
  end

  def ==(p2)
    return @x === p2.x && @y === p2.y
  end

  def to_s
    return "(x:%d,y:%d)" % [@x, @y]
  end
end
