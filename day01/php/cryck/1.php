<?php

function getFuel(float $mass) {
  $fuel = floor($mass/3) - 2;

  return $fuel;
}

$lines = file("input.txt");
$arr = array();

foreach ($lines as $mass) {
  $arr[] = getFuel(floatval($mass));
}

echo array_sum($arr);

?>
