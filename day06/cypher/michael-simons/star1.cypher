MATCH (c:Object {name: 'COM'}) WITH c
MATCH p=((:Object) -[:ORBITS*1..] -> (c))
RETURN sum(length(p));
