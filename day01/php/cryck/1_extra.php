<?php

function getFuel(float $mass) {
  $fuel = floor($mass/3) - 2;

  return $fuel;
}

$lines = file("input.txt");
$arr = array();

foreach ($lines as $mass) {
  $tmp = getFuel(floatval($mass));
  $arr[] = $tmp;
  while($tmp > 0) {
    $tmp = getFuel(floatval($tmp));
    if($tmp < 0) {
      $tmp = 0;
    }
    $arr[] = $tmp;
  }
}

echo array_sum($arr);

?>
