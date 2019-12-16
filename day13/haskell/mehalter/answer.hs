import System.Environment
import Data.List
import Data.List.Split
import qualified Data.Map.Strict as Map

import Intcode

getElements :: ((Map.Map Int Int, Int, Int), ([Int], [Int])) -> [((Int, Int), Int)]
getElements ((prog, y, z), (inputs, outputs)) = if y == -1 then map (\[x, y, i] -> ((x, y), i)) $ Data.List.Split.chunksOf 3 $ Data.List.reverse outputs else getElements $ compute (prog, y, z) (inputs, outputs)

solveA :: [((Int, Int), Int)] -> Int
solveA = Prelude.length . Data.List.filter (\(pos, i) -> i == 2)

solveB :: [((Int, Int), Int)] -> Int
solveB = snd . last

main = do
  input <- getArgs
  let parsed = parseProg $ input!!0

  let output = getElements $ startingState parsed []
  print $ solveA output
  print $ solveB output
