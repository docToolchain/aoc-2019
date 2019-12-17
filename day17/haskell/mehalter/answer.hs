import System.Environment
import Data.Char
import Data.List.Split
import qualified Data.Map.Strict as Map

import Intcode

getPoints :: [Int] -> ((Int, Int), Map.Map (Int, Int) Int) -> Map.Map (Int, Int) Int
getPoints [] (pos, map) = map
getPoints (input:inputs) ((y, x), map) = getPoints inputs (if input == 10 then ((y+1,0), map) else ((y,x+1), Map.insert (y, x) input map))

drawMap :: Map.Map (Int, Int) Int -> String
drawMap m = foldl (\s l -> s ++ (map chr l) ++ "\n") "" $ chunksOf width $ Map.elems m
  where width = (+) 1 $ snd $ last $ Map.keys m

isIntersection :: (Int, Int) -> Map.Map (Int, Int) Int -> Bool
isIntersection (x, y) m = and (map (\k -> 35 == (Map.findWithDefault (-1) k m)) [(x-1,y), (x+1,y), (x,y-1), (x,y+1)])

calibrate :: Map.Map (Int, Int) Int -> Int
calibrate m = Map.foldlWithKey (\total (x,y) i -> total + (if (isIntersection (x, y) m) && i == 35 then (x*y) else 0)) 0 m

getInput :: [String] -> [Int]
getInput = foldl (\out i -> out ++ i ++ [ord '\n']) [] . map (map ord)

main = do
  input <- getArgs
  let parsed = parseProg $ input!!0

  let points = getPoints (reverse $ computeTilHalt $ startingState parsed []) ((0, 0), Map.empty)
  putStr $ drawMap points

  print $ calibrate points

  let directions = ["A,C,A,C,B,B,C,A,C,B",
                    "L,10,R,10,L,10,L,10",
                    "R,12,L,12,R,6",
                    "R,10,R,12,L,12",
                    "n"]
  print (flip (!!) 0 $ computeTilHalt $ startingState (Map.insert 0 2 parsed) $ getInput directions)


  -- L10R10L10L10R10R12L12L10R10L10L10R10R12L12R12L12R6R12L12R6R10R12L12L10R10L10L10R10R12L12R12L12R6
  --
  -- A L10R10L10
  --   L10
  --
  -- C R10
  --   R12L12
  --
  -- A L10R10L10
  --   L10
  --
  -- C R10
  --   R12L12
  --
  -- B R12L12
  --   R6
  --
  -- B R12L12
  --   R6
  --
  -- C R10
  --   R12L12
  --
  -- A L10R10L10
  --   L10
  --
  -- C R10
  --   R12L12
  --
  -- B R12L12
  --   R6
  --
  -- A,C,A,C,B,B,C,A,C,B
  -- L,10,R,10,L,10,L,10
  -- R,12,L,12,R,6
  -- R,10,R,12,L,12
