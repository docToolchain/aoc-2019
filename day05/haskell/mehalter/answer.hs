import System.Environment

import Intcode

main = do
  input <- getArgs
  let parsed = parseProg $ input!!0

  print $ flip (!!) 0 $ computeTilHalt $ startingState parsed [1]
  print $ flip (!!) 0 $ computeTilHalt $ startingState parsed [5]
