import System.Environment
import Data.Sequence
import Data.List
import Data.List.Split
import Data.Ord
import qualified Data.Map.Strict as Map

import Intcode

startingStates :: Map.Map Int Int -> [Int] -> Seq ((Map.Map Int Int, Int, Int), ([Int], [Int]))
startingStates prog comps = update 0 (state, (input ++ [0], output)) out
  where
    out = fromList $ map (\i -> startingState prog [i]) comps
    (state, (input, output)) = out `index` 0

runSeries :: Seq ((Map.Map Int Int, Int, Int), ([Int], [Int])) -> [Int] -> Int -> Int
runSeries states comps idx = do
  let (state, (newInput, newOutput:outputs)) = uncurry compute $ index states idx
  let newStates = update idx (state, (newInput, newOutput:outputs)) states
  let newIdx = if idx+1 == Data.List.length comps then 0 else idx+1
  let ((nextProg, nextY, nextZ), (nextInput, nextOutput)) = index newStates newIdx
  if nextY == -1
    then newOutput
  else
    runSeries (update newIdx ((nextProg, nextY, nextZ), (nextInput ++ [newOutput], nextOutput)) newStates) comps newIdx

findMax :: Map.Map Int Int -> [[Int]] -> Int
findMax prog comps = snd $ maximumBy (comparing snd) $ map (\i -> (i, runSeries (startingStates prog i) i 0)) comps

main = do
  input <- getArgs
  let parsed = parseProg $ input!!0

  print $ findMax parsed $ permutations [0..4]
  print $ findMax parsed $ permutations [5..9]
