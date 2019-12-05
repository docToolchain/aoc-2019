<?php
$array = explode("\n", file_get_contents('input.txt'));
$fuel = 0;
foreach ($array as $value) {
	$fuel = $fuel + (floor($value/3))-2;
}
echo $fuel;
