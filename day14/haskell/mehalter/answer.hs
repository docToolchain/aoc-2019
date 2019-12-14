import System.Environment
import Control.Monad
import Data.Maybe
import Data.List as List
import Data.List.Split
import qualified Data.Map.Strict as Map

type Chemical = (String, Int)
type Recipe = ([Chemical], [Chemical])
type Recipes = Map.Map String Recipe

parseSingleChem :: String -> (String, Int)
parseSingleChem s = (chemical, (read amount :: Int))
  where [amount, chemical] = splitOn " " s

parseString :: String -> (String, Recipe)
parseString s = (goal, (needs, [(goal, yield)]))
  where
    leftright = splitOn " => " s
    (goal, yield) = parseSingleChem (leftright!!1)
    needs = List.map parseSingleChem $ splitOn ", " (leftright!!0)

parse :: [String] -> Recipes
parse = Map.fromList . List.map parseString

reduce :: Recipe -> Recipe
reduce (inputs, output) = ([ (r, -n) | (r, n) <- totals, n < 0 ], [ (r, n) | (r, n) <- totals, n > 0 ])
  where totals = Map.toList $ Map.fromListWith (+) ([ (r, -n) | (r, n) <- inputs ] ++ output)

expand :: Recipes -> Recipe -> Maybe Recipe
expand recipes (inputs, outputs) = listToMaybe $ do
  (i, amount) <- inputs
  guard $ i `Map.member` recipes
  let (newI, [(_, newAmount)]) = recipes Map.! i
  let times = amount `div` newAmount + if amount `mod` newAmount == 0 then 0 else 1
  return $ reduce ((inputs ++ [(j, n * times) | (j, n) <- newI]), (outputs ++ [(i, newAmount * times)]))

findRecipe :: Recipes -> Recipe -> Int
findRecipe recipes = snd . flip (!!) 0 . fst . last . unfoldr (liftM (\x->(x,x)) . (expand recipes))

maxFuel :: Recipes -> Int -> Int
maxFuel recipes ore = uncurry search $ grow 1
  where
    totalOre m = findRecipe recipes ([("FUEL", m)], [("FUEL", m)])
    grow m = if totalOre (2*m) <= ore then grow (2*m) else (m, 2*m)
    search min max = if min >= max - 1 then min else let mid = (min + max) `div` 2 in if totalOre mid <= ore then search mid max else search min mid

main :: IO()
main = do
  input <- readFile "input"
  let recipes = parse $ lines input

  print $ findRecipe recipes (recipes Map.! "FUEL")
  print $ maxFuel recipes 1000000000000
