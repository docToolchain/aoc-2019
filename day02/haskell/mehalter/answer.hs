import System.Environment
import Data.Sequence

compute :: Seq Int -> Int -> Seq Int
compute x y = do
  let op = x `index` y
  let indexOfIndex y = index x $ index x y
  let left = indexOfIndex (y+1)
  let right = indexOfIndex (y+2)
  if op == 99
    then x
  else if op /= 1 && op /= 2
    then fromList []
  else
    compute (update (x `index` (y+3)) ((if op == 1 then (+) else (*)) left right) x) (y+4)

-- Brute force to find answer based on any array input and any desired answer
findParamsBrute :: Seq Int -> Int -> [Int] -> Int
findParamsBrute x y params = do
  let computed = compute (update 1 (params!!0) $ update 2 (params!!1) x) 0
  let ans = computed `index` 0
  if y == ans
    then 100*params!!0 + params!!1
  else if ans > y
    then -1
  else if y `mod` 1000 > ans `mod` 1000
    then findParamsBrute x y [params!!0, params!!1 + 1]
  else
    findParamsBrute x y [params!!0+1, params!!1]

-- Use the gradients of each parameter in the goal to calculate the answer in O(1)
findParams :: Int -> Int
findParams goal = do
  let first = (goal-493708) `div` 243000
  let second = goal - 493708 - 243000*first
  100*first + second


main = do
  let x = fromList [1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,13,1,19,1,19,9,23,1,5,23,27,1,27,9,31,1,6,31,35,2,35,9,39,1,39,6,43,2,9,43,47,1,47,6,51,2,51,9,55,1,5,55,59,2,59,6,63,1,9,63,67,1,67,10,71,1,71,13,75,2,13,75,79,1,6,79,83,2,9,83,87,1,87,6,91,2,10,91,95,2,13,95,99,1,9,99,103,1,5,103,107,2,9,107,111,1,111,5,115,1,115,5,119,1,10,119,123,1,13,123,127,1,2,127,131,1,131,13,0,99,2,14,0,0]
  print $ (x `compute` 0) `index` 0
  print $ findParams 19690720
