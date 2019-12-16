module Intcode
( parseProg
, compute
, computeTilHalt
, startingState
) where

import Data.List as List
import Data.Maybe
import Data.Ord
import Data.List.Split
import qualified Data.Map.Strict as Map

parseProg :: String -> Map.Map Int Int
parseProg str = Map.fromList $ List.zip [0..] $ map (read :: String -> Int) $ splitOn "," str

compute :: (Map.Map Int Int, Int, Int) -> ([Int], [Int]) -> ((Map.Map Int Int, Int, Int), ([Int], [Int]))
compute (x, y, z) (input_orig, output) =
  if op == 99
    then ((x, -1, z), (input_orig, output))
  else if op == 1 || op == 2
    then compute ((Map.insert dest ((if op == 1 then (+) else (*)) left right) x), (y+4), z) (input_orig, output)
  else if op == 3
    then compute ((Map.insert dest input x), (y+2), z) (inputs, output)
  else if op == 4
    then ((x, y+2, z), (input_orig, (left:output)))
  else if op == 5 || op == 6
    then compute (x, (if (if op == 5 then (/=) else (==)) left 0 then right else (y+3)), z) (input_orig, output)
  else if op == 7 || op == 8
    then compute ((Map.insert dest (if (if op == 7 then (<) else (==)) left right then 1 else 0) x), (y+4), z) (input_orig, output)
  else if op == 9
    then compute (x, y+2, z+left) (input_orig, output)
  else
    ((Map.empty, -1, z), (input_orig, output))
  where
    (input:inputs) = if Prelude.length input_orig > 0 then input_orig else (0:input_orig)
    indexOfIndex y = Map.findWithDefault 0 (x Map.! y) x
    digits = Prelude.reverse $ Prelude.map (read . return) $ show $ x Map.! y
    op = (if Prelude.length digits > 1 then 10 * digits!!1 else 0) + digits!!0
    left = if Prelude.length digits > 2 && digits!!2 == 1 then x Map.! (y+1)
           else if Prelude.length digits > 2 && digits!!2 == 2 then x Map.! (z+(x Map.! (y+1)))
           else indexOfIndex (y+1)
    right = if Prelude.length digits > 3 && digits!!3 == 1 then x Map.! (y+2)
            else if Prelude.length digits > 3 && digits!!3 == 2 then x Map.! (z+(x Map.! (y+2)))
            else indexOfIndex (y+2)
    dest = if op == 3 then (if Prelude.length digits > 2 && digits!!2 == 2 then z+(x Map.! (y+1)) else x Map.! (y+1))
           else (if Prelude.length digits > 4 && digits!!4 == 2 then z else 0)+(x Map.! (y+3))

computeTilHalt :: ((Map.Map Int Int, Int, Int), ([Int], [Int])) -> [Int]
computeTilHalt ((prog, y, z), (inputs, outputs)) =
  if y == -1 then outputs
  else computeTilHalt $ compute (prog, y, z) (inputs, outputs)

startingState :: Map.Map Int Int -> [Int] -> ((Map.Map Int Int, Int, Int), ([Int], [Int]))
startingState prog input = ((prog, 0, 0), (input, []))
