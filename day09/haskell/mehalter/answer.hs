import System.Environment
import Data.List
import Data.List.Split
import Data.Ord
import qualified Data.Map.Strict as Map

import Intcode

main = do
  input <- getArgs
  let parsed = parseProg $ input!!0

  print $ flip (!!) 0 $ computeTilHalt $ startingState parsed [1]
  print $ flip (!!) 0 $ computeTilHalt $ startingState parsed [2]
