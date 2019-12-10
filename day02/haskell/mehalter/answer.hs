import System.Environment
import Data.List.Split
import Data.Sequence

parse :: String -> Seq Int
parse = fromList . map (read :: String -> Int) . splitOn ","

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
findParamsBrute x y [p1,p2] = do
  let computed = compute (update 1 p1 $ update 2 p2 x) 0
  let ans = computed `index` 0
  if y == ans
    then 100*p1 + p2
  else if ans > y
    then -1
  else if y `mod` 1000 > ans `mod` 1000
    then findParamsBrute x y [p1, p2 + 1]
  else
    findParamsBrute x y [p1+1, p2]

-- Use the gradients of each parameter in the goal to calculate the answer in O(1)
findParams :: Int -> Int
findParams goal = do
  let first = (goal-493708) `div` 243000
  let second = goal - 493708 - 243000*first
  100*first + second


main = do
  input <- getArgs
  let x = parse $ input!!0
  print $ (x `compute` 0) `index` 0
  print $ findParams 19690720
