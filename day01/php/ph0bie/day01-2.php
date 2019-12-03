<?php

function calculateFuelFuel($fuel) {
    $total_fuel = 0;
    while ($fuel > 0) {
        $temp_fuel = floor($fuel/3)-2;
        if ($temp_fuel < 0) {break;}
        $total_fuel = $total_fuel + $temp_fuel;
        $fuel = $temp_fuel;
    }
    return $total_fuel;
}

$array = explode("\n", file_get_contents('input.txt'));
$mass_fuel = 0;
foreach ($array as $value) {
    $temp_mass_fuel = (floor($value/3))-2; 
    $mass_fuel = $mass_fuel + $temp_mass_fuel; // total fuel
    $fuel_fuel = calculateFuelFuel($temp_mass_fuel);
	$mass_fuel = $mass_fuel + $fuel_fuel;
}
echo $mass_fuel;
