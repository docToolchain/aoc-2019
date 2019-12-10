import System.Environment

mass1 :: Int -> Int
mass1 x = (x `div` 3) - 2

mass2 :: Int -> Int
mass2 x = do
  let m = mass1 x
  if m > 0 then m + mass2 m else 0

main = do
  inputs <- getArgs
  let ints = map (read::String->Int) inputs
  print $ sum $ map mass1 ints
  print $ sum $ map mass2 ints
