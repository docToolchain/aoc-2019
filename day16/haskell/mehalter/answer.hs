import System.Environment
import Data.Char
import Data.List

parse :: String -> [Int]
parse = map digitToInt

fft :: Int -> [Int] -> [Int]
fft num input = foldl (\arr i -> step arr) input [1..num]
  where
    pattern i = tail . concat . repeat . concatMap (replicate i) $ [0, 1, 0, -1]
    step input = map (\(i, k) -> (abs . foldl (+) 0 $ zipWith (*) (pattern i) input) `mod` 10) $ zip [1..] input

partfft :: Int -> [Int] -> [Int]
partfft n = head . drop n . iterate (map ((`mod` 10). abs) . reverse . tail . scanl (+) 0 . reverse)

solveA :: [Int] -> String
solveA = concatMap show . take 8 . fft 100

solveB :: [Int] -> String
solveB input = concatMap show $ take 8 $ partfft 100 $ drop offset $ concat $ replicate 10000 input
  where
    offset = (read :: String -> Int) $ concatMap show $ take 7 input

main = do
  input <- getArgs
  let parsed = parse $ input!!0

  print $ solveA parsed
  print $ solveB parsed
