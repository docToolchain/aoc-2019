import System.Environment
import Data.Char
import Data.List
import Data.List.Split
import Data.Ord

count :: Char -> [Char] -> Int
count char = length . filter (char ==)

getPixel :: Char -> Char
getPixel input = if input == '0' then ' ' else if input == '1' then '█' else input

mergeAllLayers :: [[Char]] -> [Char]
mergeAllLayers [input] = input
mergeAllLayers (inp:inps) = foldl merge2Layers inp inps

merge2Layers :: [Char] -> [Char] -> [Char]
merge2Layers in1 [] = in1
merge2Layers (in1:in1s) (in2:in2s) = (if in1 == '2' then getPixel in2 else getPixel in1) : merge2Layers in1s in2s

solveA :: [Char] -> (Int, Int) -> Int
solveA input (x, y) = count '1' found * count '2' found where
    found = fst $ minimumBy (comparing snd) $ map (\i -> (i, count '0' i)) $ chunksOf (x*y) input

solveB :: [Char] -> (Int, Int) -> [Char]
solveB input (x, y) = foldl (\i j->i ++ (j ++ ['\n'])) [] $ chunksOf x $ mergeAllLayers $ chunksOf (x*y) input

main = do
  inputs <- getArgs
  let input = inputs!!0
  let x = 25
  let y = 6

  print $ solveA input (x, y)
  putStr $ solveB input (x, y)
