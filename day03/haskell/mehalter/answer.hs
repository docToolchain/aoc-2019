import System.Environment
import Data.Set as Set
import Data.List as List
import Data.List.Split
import Data.Maybe as Maybe

parse :: [String] -> [[String]]
parse = List.map (splitOn ",")

-- Calculate given a direction, whether the length should be added or subtracted
getDir :: Char -> Int
getDir dir = if dir == 'L' || dir == 'D' then -1 else 1

-- Calculate an array of points in a single step of the wire
getPoints :: Char -> Int -> [[Int]] -> [[Int]]
getPoints dir len points = do
  let [x,y] = last points
  points ++ [if dir == 'L' || dir == 'R' then [x + point, y] else [x, y + point]
    | point <- Prelude.map ((getDir dir)*) [1..len]]

-- Calculate all the points for all the steps
getAllPoints :: [String] -> [[Int]] -> [[Int]]
getAllPoints [] points = points
getAllPoints (d:ds) points = getAllPoints ds (getPoints (head d) (read (tail d)::Int) points)

-- Convert two wire array of points to sets, and find where they cross
getIntersectionSet :: [[Int]] -> [[Int]] -> Set [Int]
getIntersectionSet a b = (fromList a) `Set.intersection` (fromList b)

-- Calculate Manhattan distance of point to (0,0)
getDis :: [Int] -> Int
getDis [x,y] = (abs x) + (abs y)

-- Calculate Manhattan distance for all the intersection points, and return the smallest that isn't 0
getMinManhattan :: Set [Int] -> Int
getMinManhattan points = elemAt 1 $ Set.map getDis points

-- Calculate number of steps needed to get to a given intersection point
getPointStep :: [[Int]] -> [[Int]] -> [Int] -> Int
getPointStep a b point = (fromMaybe 0 $ List.elemIndex point a) + (fromMaybe 0 $ List.elemIndex point b)

-- Calculate steps for all the intersection points, and return the smallest that isn't 0
getMinSteps :: [[Int]] -> [[Int]] -> Set [Int] -> Int
getMinSteps a b points = elemAt 1 $ Set.map (getPointStep a b) points

main = do
  input <- getArgs

  let x = parse input

  -- Get all the points for wires a and b
  let pointsA = getAllPoints (x!!0) [[0,0]]
  let pointsB = getAllPoints (x!!1) [[0,0]]

  -- Find all the points where the wires cross
  let intersection = pointsA `getIntersectionSet` pointsB

  -- Print the minimum Manhattan distance of an intersection
  print $ getMinManhattan intersection
  -- Print the minimum steps needed to get to an intersection
  print $ getMinSteps pointsA pointsB intersection
