#!/bin/bash
# run tests with
# ./test.bash &> results.txt

OUTPUT=$(./solution.rb)
if [ "$OUTPUT" == "Hello World" ] 
then
   echo "success"
else
   echo "fail"
   echo "$OUTPUT"
fi
