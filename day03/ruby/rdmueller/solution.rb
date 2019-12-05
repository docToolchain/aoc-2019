#!/usr/bin/env ruby

def readInput(filename)
    input = []
    file = File.open(filename).read
    i = 0
    file.each_line { |line|
        input[i] = line.split(",")
        i += 1
    }
    return input 
end

input = readInput("test_input.txt")
puts input[0][3]
panel = []
sx = 11000
sy = 11000
x = sx 
y = sy 
maxx = sx
minx = sx
maxy = sy
miny = sy
panel[x] = []
input[0].each { |inst|
    dx = 0
    dy = 0
    case (inst[0])
    when 'U'    
        dy =  1
    when 'D'
        dy = -1
    when 'L'
        dx = -1
    when 'R'
        dx =  1
    end
    dist = Integer(inst[1..-1])
    (1..dist).each { |step|
        x += dx
        y += dy
        if (panel[x].nil?) then
            panel[x] = []
        end
        panel[x][y] = "#"
        if (x>maxx) then maxx=x end
        if (y>maxy) then maxy=y end
        if (x<minx) then minx=x end
        if (y<miny) then miny=y end
        
    }
    panel[x][y] = "+"
} 
# follow the second wire
x = sx 
y = sy 

input[1].each { |inst|
    dx = 0
    dy = 0
    case (inst[0])
    when 'U'    
        dy =  1
    when 'D'
        dy = -1
    when 'L'
        dx = -1
    when 'R'
        dx =  1
    end
    dist = Integer(inst[1..-1])
    (1..dist).each { |step|
        x += dx
        y += dy
        if (panel[x].nil?) then
            panel[x] = []
        end
        if (panel[x][y] == "#") then
            puts (x-sx).abs+(y-sy).abs
        else
            panel[x][y] = '*'
        end
        if (x>maxx) then maxx=x end
        if (y>maxy) then maxy=y end
        if (x<minx) then minx=x end
        if (y<miny) then miny=y end
    }
} 
panel[sx][sy]="O"
puts minx
puts maxx
puts miny
puts maxy
=begin
(miny..maxy).each { |y| 
    (minx..maxx).each { |x|
        if (panel[x].nil?) then
            print "."
        else
            if (panel[x][y].nil?) then
                print "."
            else
                print panel[x][y]
            end
        end
    }
    puts "-"
}
=end