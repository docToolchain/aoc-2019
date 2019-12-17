import System.Environment
import Data.List as List
import Data.Maybe
import Data.Ord
import qualified Data.Map.Strict as Map

import Intcode

getPoints :: ((Map.Map Int Int, Int, Int), ([Int], [Int])) -> Map.Map (Int, Int) Int -> (Int, Int) -> Map.Map (Int, Int) Int
getPoints ((prog, y, z), (inputs, outputs)) map pos = if y == -1 then map else if lastObj == 0 then map else List.foldl Map.union Map.empty $ List.map (\(i, p) -> calcNext ((prog, y, z), (inputs, outputs)) map i p) (unmappedNeighbors map pos)
  where
    lastObj = if length outputs > 0 then outputs!!0 else -1
    unmappedNeighbors m (x, y) = filter (\(i, p) -> not . isJust $ Map.lookup p m) [(1, (x, y+1)), (2, (x, y-1)), (3, (x-1, y)), (4, (x+1, y))]

calcNext :: ((Map.Map Int Int, Int, Int), ([Int], [Int])) -> Map.Map (Int, Int) Int -> Int -> (Int, Int) -> Map.Map (Int, Int) Int
calcNext (state, (inputs, outputs)) map dir newPos = getPoints (nextState, (newInput, newObj:newOutputs)) (Map.insert newPos newObj map) newPos
    where (nextState, (newInput, newObj:newOutputs)) = compute state ((dir:inputs), outputs)

bfs :: Map.Map (Int, Int) Int -> Map.Map (Int, Int) (Int, Int) -> (Int, Int) -> Int -> Map.Map (Int, Int) (Int, Int)
bfs map seen pos dis = if lastObj == 0 then seen else List.foldl Map.union Map.empty $ List.map (\p -> bfs map (Map.insert p ((map Map.! p), dis+1) seen) p (dis+1)) (unmappedNeighbors seen pos)
  where
    lastObj = map Map.! pos
    unmappedNeighbors m (x, y) = filter (\p -> not . isJust $ Map.lookup p m) [(x, y+1), (x, y-1), (x-1, y), (x+1, y)]

getMaxDis :: Map.Map (Int, Int) Int -> (Int, Int) -> Int -> Int
getMaxDis map pos item = snd $ maximumBy (comparing snd) $ Map.elems $ Map.filter ((== item) . fst) $ bfs map (Map.singleton pos (2, 0)) pos 0

main = do
  input <- getArgs
  let parsed = parseProg $ input!!0

  let map = getPoints (startingState parsed []) (Map.singleton (0, 0) 1) (0, 0)
  print $ getMaxDis map (0, 0) 2
  print $ getMaxDis map (-16, -12) 1
