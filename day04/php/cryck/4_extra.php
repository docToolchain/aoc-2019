<?php

function testPass(int $pass) {
  $arr = array_reverse(str_split($pass));

  foreach($arr as $key => $num) {
    if(isset($arr[$key+1])) {
      if($num < $arr[$key+1]) {
        return false;
      }
    }
  }
  foreach(array_count_values($arr) as $doubles) {
    if($doubles === 2) {
      return true;
    }
  }
}

$i = 0;

foreach(range(353096, 843212) as $pass) {
  if(testPass($pass)) {
    $i++;
  }
}

echo $i;
