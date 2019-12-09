import System.Environment
import Data.List
import Data.List.Split

parse :: String -> [Int]
parse = map (read :: String -> Int) . splitOn "-"

-- convert a number to a list of digits
digits :: Int -> [Int]
digits = map (read . return) . show

-- check if list is sorted
isSorted :: Ord a => [a] -> Bool
isSorted list = all (uncurry (<=)) $ zip list $ tail list

-- check if the number an item appears satisfies a supplied function
containsN :: Ord a => (Int -> Bool) -> [a] -> Bool
containsN func = (<) 0 . length . filter (\l -> func $ length l) . group

-- Check if digits are sorted and if there is a digit who's appearance satisfies a function
valid :: Ord a => (Int -> Bool) -> [a] -> Bool
valid func list = isSorted list && containsN func list

main = do
  input <- getArgs
  let [x, y] = parse  $ input!!0
  let ints = map digits [x..y]
  print $ length $ filter (valid (>1)) ints
  print $ length $ filter (valid (==2)) ints
