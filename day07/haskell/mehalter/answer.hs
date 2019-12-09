import System.Environment
import Data.Sequence
import Data.List
import Data.List.Split
import Data.Ord

parse :: String -> Seq Int
parse = fromList . map (read :: String -> Int) . splitOn ","

revDigits :: Int -> [Int]
revDigits = Prelude.reverse . Prelude.map (read . return) . show

compute :: (Seq Int, Int) -> ([Int], [Int]) -> ((Seq Int, Int), ([Int], [Int]))
compute (x, y) (input_orig, output) = do
  let (input:inputs) = if Prelude.length input_orig > 0 then input_orig else (0:input_orig)
  let indexOfIndex y = index x $ index x y
  let digits = revDigits $ x `index` y
  let op = (if Prelude.length digits > 1 then 10 * digits!!1 else 0) + digits!!0
  let left = if Prelude.length digits > 2 && digits!!2 == 1 then x `index` (y+1) else indexOfIndex (y+1)
  let right = if Prelude.length digits > 3 && digits!!3 == 1 then x `index` (y+2) else indexOfIndex (y+2)
  let dest = if op == 3 then x `index` (y+1) else x `index` (y+3)
  if op == 99
    then ((x, -1), (input_orig, output))
  else if op == 1 || op == 2
    then compute ((update dest ((if op == 1 then (+) else (*)) left right) x), (y+4)) (input_orig, output)
  else if op == 3
    then compute ((update dest input x), (y+2)) (inputs, output)
  else if op == 4
    then ((x, y+2), (input_orig, (left:output)))
  else if op == 5 || op == 6
    then compute (x, (if (if op == 5 then (/=) else (==)) left 0 then right else (y+3))) (input_orig, output)
  else if op == 7 || op == 8
    then compute ((update dest (if (if op == 7 then (<) else (==))left right then 1 else 0) x), (y+4)) (input_orig, output)
  else
    ((fromList [], -1), (input_orig, output))

startingState :: Seq Int -> [Int] -> Seq ((Seq Int, Int), ([Int], [Int]))
startingState prog comps = do
  let out = fromList $ map (\i -> ((prog, 0), ([i], []))) comps
  let (prog, (input, output)) = out `index` 0
  update 0 (prog, (input ++ [0], output)) out

runSeries :: Seq ((Seq Int, Int), ([Int], [Int])) -> [Int] -> Int -> Int
runSeries states comps idx = do
  let (prog, (newInput, newOutput:outputs)) = uncurry compute $ index states idx
  let newStates = update idx (prog, (newInput, newOutput:outputs)) states
  let newIdx = if idx+1 == Data.List.length comps then 0 else idx+1
  let ((nextProg, nextY), (nextInput, nextOutput)) = index newStates newIdx
  if nextY == -1
    then newOutput
  else
    runSeries (update newIdx ((nextProg, nextY), (nextInput ++ [newOutput], nextOutput)) newStates) comps newIdx

findMax :: Seq Int -> [[Int]] -> ([Int], Int)
findMax prog comps = maximumBy (comparing snd) $ map (\i -> (i, runSeries (startingState prog i) i 0)) comps

main = do
  input <- getArgs
  let x = parse $ input!!0

  print $ findMax x $ permutations [0..4]
  print $ findMax x $ permutations [5..9]
