import Data.Sequence
import System.Environment
import Data.List.Split

parse :: String -> Seq Int
parse = fromList . map (read :: String -> Int) . splitOn ","

revDigits :: Int -> [Int]
revDigits = Prelude.reverse . Prelude.map (read . return) . show

compute :: Seq Int -> Int -> IO (Seq Int)
compute x y = do
  let indexOfIndex y = index x $ index x y
  let digits = revDigits $ x `index` y
  let op = (if Prelude.length digits > 1 then 10 * digits!!1 else 0) + digits!!0
  let left = if Prelude.length digits > 2 && digits!!2 == 1 then x `index` (y+1) else indexOfIndex (y+1)
  let right = if Prelude.length digits > 3 && digits!!3 == 1 then x `index` (y+2) else indexOfIndex (y+2)
  let dest = if op == 3 then x `index` (y+1) else x `index` (y+3)
  if op == 99
    then return (x)
  else if op == 1 || op == 2
    then compute (update dest ((if op == 1 then (+) else (*)) left right) x) (y+4)
  else if op == 3
    then do
      print "Input:"
      input <- getLine
      compute (update dest (read input::Int) x) (y+2)
  else if op == 4
    then do
      print "Output:"
      print left
      compute x (y+2)
  else if op == 5 || op == 6
    then compute x (if (if op == 5 then (/=) else (==)) left 0 then right else (y+3))
  else if op == 7 || op == 8
    then compute (update dest (if (if op == 7 then (<) else (==))left right then 1 else 0) x) (y+4)
  else
    return (fromList [])

main = do
  input <- getArgs
  let x = parse $ input!!0
  x `compute` 0 >>= print
