import System.Environment
import Data.Set as Set
import Data.List
import Data.Ord

-- Convert input to a set of points
parse :: [String] -> Set.Set (Int, Int)
parse = Set.fromList . Prelude.filter (\i->i /= (-1, -1)) . mkMap
  where
    mkMap m = concatMap idxY $ zip m [0..]
    idxY (r, y) = Prelude.map (idxX y) $ zip r [0..]
    idxX y ('#', x) = (x, y)
    idxX y ('.', x) = (-1,-1)

-- Check if any points along the line between 2 points are an asteroid
isVisible :: (Int, Int) -> (Int, Int) -> Set.Set (Int, Int) -> Bool
isVisible (x1,y1) (x2,y2) input = divisor == 1 || (not $ any (\n -> Set.member (x1 + ((div dx divisor)*n), y1 + ((div dy divisor)*n)) input) [1..(divisor-1)])
  where
    (dx,dy) = (x2-x1, y2-y1)
    divisor = gcd dx dy

-- Filter all points that are visible from a given point
allVisible :: (Int, Int) -> Set.Set (Int, Int) -> Set.Set (Int, Int)
allVisible x input = Set.delete x $ Set.filter (\i -> isVisible x i input) input

-- Find point with the maximum number of visible asteroids
maxVisible :: Set.Set (Int, Int) -> ((Int, Int), Int)
maxVisible input = maximumBy (comparing snd) $ Prelude.map (\i -> (i, Set.size $ allVisible i input)) $ Set.toList input

-- Calculate the angle an asteroid is from straight up using arctangent
angle :: (Int, Int) -> (Int, Int) -> Float
angle (x1, y1) (x2, y2) = (if angle < 0 then 2*pi else 0) + angle
  where angle = atan2 (fromIntegral $ x2-x1) (fromIntegral $ y1-y2)

-- Get all asteroids in order of clockwise sweeps of visible asteroids
vaporizeOrder :: (Int, Int) -> Set.Set (Int, Int) -> [(Int, Int)]
vaporizeOrder x input = if Set.null input then [] else ((sortBy (comparing (angle x))) (Set.toList visible)) ++ (vaporizeOrder x (Set.difference input visible))
  where visible = allVisible x input

main = do
  input <- getArgs
  let x = parse input

  let (maxPos, occ) = maxVisible x
  print occ
  let (foundX, foundY) = (vaporizeOrder maxPos x)!!199
  print $ foundX*100 + foundY
