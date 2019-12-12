import System.Environment
import Text.Regex.PCRE
import Data.List.Split
import Data.List

type PosVel = ([Int],[Int])

-- Convert input to a list of (positions, velocities)
parse :: [String] -> [PosVel]
parse ss = zip (chunksOf 3 $ foldl (\a s -> a ++ map (\i -> read (i!!1) :: Int) (s =~ "=([-\\d]*)" :: [[String]])) [] ss) $ take (length ss) $ repeat [0,0,0]

-- Update velocity of moon 1 based on moon 2
newVelocity :: PosVel -> PosVel -> [Int]
newVelocity ([px1, py1, pz1], [vx1, vy1, vz1]) ([px2, py2, pz2], [vx2, vy2, vz2]) =
  [(velChange px1 px2),(velChange py1 py2),(velChange pz1 pz2)]
  where
    velChange v1 v2 = if v2 > v1 then 1 else if v2 < v1 then (-1) else 0

sumTriple :: [Int] -> [Int] -> [Int]
sumTriple [x1, y1, z1] [x2, y2, z2] = [x1+x2, y1+y2, z1+z2]

-- Update position based on velocity
updatePosition :: PosVel -> PosVel
updatePosition ([px, py, pz], [vx, vy, vz]) = ([px+vx, py+vy, pz+vz], [vx, vy, vz])

-- Get unique pairs of indexes relative to a given index
uniquePairs :: [Int] -> Int -> [(Int, Int)]
uniquePairs l i = [(x,y) | x<-l, y<-l, x /= y && x == i]

-- Calculate a single timestep
timeStep :: [PosVel] -> [PosVel]
timeStep moons = map (\i->updatePosition ((fst $ moons!!i), foldl sumTriple (snd $ moons!!i) $ map (\(i1, i2) -> newVelocity (moons!!i1) (moons!!i2)) (uniquePairs idxs i))) idxs
  where idxs = [0..((length moons)-1)]

-- Loop for a number of steps
timeSteps :: [PosVel] -> Int -> [PosVel]
timeSteps moons steps = foldl (\posvels step -> timeStep posvels) moons [1..steps]

-- Find equilibrium step by finding cycle in each axis and finding the least common multiple between the 3 cycles
equilibrium :: [PosVel] -> [PosVel] -> [Int] -> Int -> Int
equilibrium initial moons [xc, yc, zc] step = if xc /= 0 && yc /= 0 && zc /= 0 then lcm xc $ lcm yc zc
                                              else equilibrium initial newMoons [checkCycle xc 0, checkCycle yc 1, checkCycle zc 2] (step+1)
  where
    newMoons = timeStep moons
    checkCycle val idx = if val == 0 && equalAxis initial newMoons idx then step+1 else val
    equalAxis initial currMoons idx = (getAxis idx initial) == (getAxis idx currMoons)
    getAxis axis = map (\(p, v) -> (p!!axis, v!!axis))

-- Calculate total energy in the system
totalEnergy :: [PosVel] -> Int
totalEnergy = sum . map (\(p, v) -> sum (map abs p) * sum (map abs v))

main = do
  input <- getArgs
  let parsed = parse input

  print $ totalEnergy $ timeSteps parsed 1000
  print $ equilibrium parsed parsed [0,0,0] 0
