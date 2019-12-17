import System.Environment
import qualified Data.Map.Strict as Map

import Intcode

computeTilHalt2 :: ((Map.Map Int Int, Int, Int), ([Int], [Int])) -> Int
computeTilHalt2 ((prog, y, z), io) =
  if y == -1 then prog Map.! 0
  else computeTilHalt2 $ compute (prog, y, z) io

findParams :: Int -> Int
findParams goal = 100*first + second
  where
    first = (goal-493708) `div` 243000
    second = goal - 493708 - 243000*first

main = do
  input <- getArgs
  let parsed = parseProg $ input!!0

  let map = Map.insert 1 12 parsed
  let newMap = Map.insert 2 2 map
  print $ computeTilHalt2 $ startingState newMap []
  print $ findParams 19690720
