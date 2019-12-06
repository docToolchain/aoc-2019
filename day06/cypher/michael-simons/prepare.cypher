LOAD csv FROM 'file:///input.txt' AS orbits FIELDTERMINATOR ')'
MERGE (o0:Object {name: orbits[0]}) // <1>
MERGE (o1:Object {name: orbits[1]})
MERGE (o0) <- [:ORBITS] - (o1) // <2>
RETURN COUNT(*);
