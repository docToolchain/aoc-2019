import System.Environment

mass1 :: Int -> Int
mass1 x = (x `div` 3) - 2

mass2 :: Int -> Int
mass2 x = do
  let m = mass1 x
  if m > 0
    then m + mass2 m
  else 0

main = do
  x <- getArgs
  print (sum (map mass1 (map (read::String->Int) x)))
  print (sum (map mass2 (map (read::String->Int) x)))
