import System.Environment
import Data.Sequence
import Data.List
import Data.List.Split
import Data.Ord
import Data.Foldable
import Data.Maybe
import qualified Data.Map.Strict as Map
import Debug.Trace

parse :: String -> Map.Map Int Int
parse str = Map.fromList $ Data.List.zip [0..] $ map (read :: String -> Int) $ splitOn "," str

revDigits :: Int -> [Int]
revDigits = Prelude.reverse . Prelude.map (read . return) . show

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
    digits = revDigits $ x Map.! y
    op = (if Prelude.length digits > 1 then 10 * digits!!1 else 0) + digits!!0
    left = if Prelude.length digits > 2 && digits!!2 == 1 then x Map.! (y+1)
           else if Prelude.length digits > 2 && digits!!2 == 2 then x Map.! (z+(x Map.! (y+1)))
           else indexOfIndex (y+1)
    right = if Prelude.length digits > 3 && digits!!3 == 1 then x Map.! (y+2)
            else if Prelude.length digits > 3 && digits!!3 == 2 then x Map.! (z+(x Map.! (y+2)))
            else indexOfIndex (y+2)
    dest = if op == 3 then (if Prelude.length digits > 2 && digits!!2 == 2 then z+(x Map.! (y+1)) else x Map.! (y+1))
           else (if Prelude.length digits > 4 && digits!!4 == 2 then z else 0)+(x Map.! (y+3))

getPoints :: ((Map.Map Int Int, Int, Int), ([Int], [Int])) -> Map.Map (Int, Int) Int -> Int -> (Int, Int) -> Map.Map (Int, Int) Int
getPoints (prog, (inputs, outputs)) map dir (x, y) = if nextY2 == -1 then newMap else getPoints ((nextProg2, nextY2, nextZ2), (newInput2, (newDir:newOutputs2))) newMap newDir2 (newX, newY)
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
  let parsed = parse $ input!!0

  print $ Map.size $ getPoints ((parsed, 0, 0), ([], [])) (Map.empty) 0 (0, 0)
  putStr $ paint $ getPoints ((parsed, 0, 0), ([], [])) (Map.singleton (0,0) 1) 0 (0, 0)
