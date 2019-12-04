import Data.List

-- convert a number to a list of digits
digits :: Int -> [Int]
digits = map (read . return) . show

-- check if list is sorted
isSorted :: Ord a => [a] -> Bool
isSorted [] = True
isSorted [x] = True
isSorted (x:y:xs) = x <= y && isSorted (y:xs)

-- check if a list contains any duplicates
hasDup :: (Ord a) => [a] -> Bool
hasDup xs = length (nub xs) /= length xs

-- check if a item in a sorted list appears strictly twice
containsTwo :: Ord a => [a] -> Bool
containsTwo list = or $ map (\l -> length l == 2) (group list)

-- Check if digits are sorted and if there are any duplicates
valid1 :: [Int] -> Int
valid1 ints = fromEnum $ (isSorted ints) && (hasDup ints)

-- Check if digits are sorted and if there is a digit with only 2 appearances
valid2 :: [Int] -> Int
valid2 ints = fromEnum $ (isSorted ints) && (containsTwo ints)

main = do
  let ints = map digits [245318..765747]
  print $ sum $ map valid1 ints
  print $ sum $ map valid2 ints
