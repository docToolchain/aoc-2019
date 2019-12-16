import System.Environment
import Data.Sequence
import Data.List
import Data.List.Split
import Data.Ord
import Data.Foldable
import qualified Data.Map.Strict as Map

import Intcode

getPoints :: ((Map.Map Int Int, Int, Int), ([Int], [Int])) -> Map.Map (Int, Int) Int -> Int -> (Int, Int) -> Map.Map (Int, Int) Int
getPoints (prog, (inputs, outputs)) map dir (x, y) = if nextY2 == -1 then newMap
                                                     else getPoints ((nextProg2, nextY2, nextZ2), (newInput2, (newDir:newOutputs2))) newMap newDir2 (newX, newY)
  where
    currColor = Map.findWithDefault 0 (x, y) map
    ((nextProg1, nextY1, nextZ1), (newInput1, (newColor:newOutputs1))) = compute prog ((currColor:inputs), outputs)
    newMap = if nextY1 == -1 || newColor == currColor then map else Map.insert (x, y) newColor map
    ((nextProg2, nextY2, nextZ2), (newInput2, (newDir:newOutputs2))) = if nextY1 == -1 then ((nextProg1, nextY1, nextZ1), (newInput1, (newColor:newOutputs1)))
                                                                       else compute (nextProg1, nextY1, nextZ1) (newInput1, (newColor:newOutputs1))
    newDir2 = mod (dir+(if newDir == 0 then -1 else 1)) 4
    newX = if newDir2 == 1 then x+1 else if newDir2 == 3 then x-1 else x
    newY = if newDir2 == 0 then y+1 else if newDir2 == 2 then y-1 else y

paint :: Map.Map (Int, Int) Int -> [Char]
paint map = foldl (\i j->i ++ (j ++ ['\n'])) [] $ Data.List.Split.chunksOf width $ toList seq
  where
    minX = fst $ minimumBy (comparing fst) $ Map.keys map
    maxX = fst $ maximumBy (comparing fst) $ Map.keys map
    minY = snd $ minimumBy (comparing snd) $ Map.keys map
    maxY = snd $ maximumBy (comparing snd) $ Map.keys map
    width = abs minX + abs maxX + 1
    height = abs minY + abs maxY + 1
    output = fromList $ Prelude.take (width*height) (repeat ' ')
    seq = Map.foldlWithKey (\seq (x, y) color->update ((abs (y - maxY))*width + (x+abs minX)) (if color == 1 then '█' else ' ') seq) output map

main = do
  input <- getArgs
  let parsed = parseProg $ input!!0

  print $ Map.size $ getPoints (startingState parsed []) (Map.empty) 0 (0, 0)
  putStr $ paint $ getPoints (startingState parsed []) (Map.singleton (0,0) 1) 0 (0, 0)
