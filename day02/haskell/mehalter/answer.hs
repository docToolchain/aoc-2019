import System.Environment
import Data.Sequence

comp :: Seq Int -> Int -> Seq Int
comp x y = do
  let op = index x y
  let left = index x (index x (y+1))
  let right = index x (index x (y+2))
  let dest = index x (index x (y+3))
  if op == 99
    then x
  else if op /= 1 && op /= 2
    then (fromList [])
  else
    comp (update (index x (y+3)) (if op == 1 then (left + right) else if op == 2 then (left * right) else dest) x) (y+4)

findParams :: Seq Int -> Int -> [Int] -> Int
findParams x y params = do
  let computed = comp (update 1 (params!!0) (update 2 (params!!1) x)) 0
  let ans = index computed 0
  if y == ans
    then 100*(params!!0)+(params!!1)
  else if (mod y 1000) > (mod ans 1000)
    then findParams x y [(params!!0), ((params!!1) + 1)]
  else if (mod y 1000) < (mod ans 1000)
    then findParams x y [(params!!0), ((params!!1) - 1)]
  else if y > ans
    then findParams x y [((params!!0)+1), (params!!1)]
  else
    findParams x y [((params!!0)-1), (params!!1)]


main = do
  let x = fromList [1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,13,1,19,1,19,9,23,1,5,23,27,1,27,9,31,1,6,31,35,2,35,9,39,1,39,6,43,2,9,43,47,1,47,6,51,2,51,9,55,1,5,55,59,2,59,6,63,1,9,63,67,1,67,10,71,1,71,13,75,2,13,75,79,1,6,79,83,2,9,83,87,1,87,6,91,2,10,91,95,2,13,95,99,1,9,99,103,1,5,103,107,2,9,107,111,1,111,5,115,1,115,5,119,1,10,119,123,1,13,123,127,1,2,127,131,1,131,13,0,99,2,14,0,0]
  print (index (comp x 0) 0)
  print (findParams x 19690720 [0,0])
