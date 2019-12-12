<?php

function getOutput(int $noun, int $verb) {
  $array = array_map('intval', explode(",", file_get_contents('input.txt')));

  $array[1] = $noun;
  $array[2] = $verb;

  foreach ($array as $key => $val) {
    if($key%4 === 0 || $key === 0) {
      if($val === 1) {
        $pos1 = (int)$array[$key+1];
        $pos2 = (int)$array[$key+2];
        $pos3 = (int)$array[$key+3];
        $array[$pos3] = (int)$array[$pos1] + (int)$array[$pos2];
      }
      if($val === 2) {
        $pos1 = (int)$array[$key+1];
        $pos2 = (int)$array[$key+2];
        $pos3 = (int)$array[$key+3];
        $array[$pos3] = (int)$array[$pos1] * (int)$array[$pos2];
      }
      if($val === 99) {
        break;
      }
    }
  }
  return $array[0];
}

foreach(range(0, 99) as $num_noun) {
    foreach(range(0, 99) as $num_verb) {
        if(getOutput($num_noun, $num_verb) === 19690720) {
          echo 100 * $num_noun + $num_verb;
          break;
        }
    }
}

?>
