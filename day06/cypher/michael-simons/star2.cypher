MATCH (y:Object {name: 'YOU'}) -[:ORBITS] -> (o1:Object) // <1>
MATCH (s:Object {name: 'SAN'}) -[:ORBITS] -> (o2:Object)
WITH o1, o2 // <2>
MATCH p = shortestPath((o1)-[:ORBITS*1..]-(o2))  // <3>
RETURN length(p);
