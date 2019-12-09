import System.Environment
import Data.Map.Lazy as Map
import Data.List.Split
import Control.Arrow
import Control.Monad (join)
import Data.Tuple (swap)
import Data.List (unfoldr)
import Data.Function (on)

parse :: String -> (String, String)
parse input = (x,y) where
  [x,y] = splitOn ")" input

orbits :: [(String, String)] -> Map String [String]
orbits input = fromListWith (++) $ second (:[]) <$> input

totalOrbits :: Map String [String] -> Int
totalOrbits orbits = sum checksums where
  checksums = checksum <$> orbits
  checksum = sum . Prelude.map (maybe 1 (+ 1) . flip Map.lookup checksums)

solveA :: [String] -> Int
solveA input = totalOrbits $ orbits $ Prelude.map parse input

rorbits :: [(String, String)] -> Map String String
rorbits input = Map.fromList $ swap <$> input

dropCommonPrefix :: [String] -> [String] -> ([String], [String])
dropCommonPrefix (x:xs) (y:ys) | x == y = dropCommonPrefix xs ys
dropCommonPrefix xs ys = (xs, ys)

path :: [(String, String)] -> String -> [String]
path orbits = (reverse .) . unfoldr $ fmap (join (,)) . flip Map.lookup (rorbits orbits)

solveB :: [String] -> Int
solveB input = uncurry ((+) `on` length) $ dropCommonPrefix (path parsed "SAN") (path parsed "YOU") where parsed = Prelude.map parse input

main = do
  input <- getArgs
  print $ solveA input
  print $ solveB input
