import Data.List

-- convert a number to a list of digits
digits :: Int -> [Int]
digits = map (read . return) . show

-- check if list is sorted
isSorted :: Ord a => [a] -> Bool
isSorted [] = True
isSorted [x] = True
isSorted (x:y:xs) = x <= y && isSorted (y:xs)

-- check if the number an item appears satisfies a supplied function
containsN :: Ord a => (Int -> Bool) -> [a] -> Bool
containsN func list = (<) 0 $ length $ filter (\l -> func $ length l) $ group list

-- Check if digits are sorted and if there is a digit who's appearance satisfies a function
valid :: Ord a => (Int -> Bool) -> [a] -> Bool
valid func list = isSorted list && containsN func list

main = do
  let ints = map digits [245318..765747]
  print $ length $ filter (valid (>1)) ints
  print $ length $ filter (valid (==2)) ints
