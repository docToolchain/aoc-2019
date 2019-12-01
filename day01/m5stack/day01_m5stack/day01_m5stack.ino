// day01 by RJPlog, 01.12.2019

#include <M5Stack.h>

int i = 100; // amount of modules
int x; // counter
int fuel_counter_upper = 0;
int temporary_fuel = 0; //needed only for 2nd part
int mass[] = {54172,
              58469,
              92948,
              143402,
              57563,
              54532,
              68042,
              89847,
              70872,
              54069,
              107310,
              146439,
              88851,
              142869,
              71309,
              89613,
              70338,
              87708,
              95305,
              134384,
              128250,
              134991,
              91270,
              127819,
              68650,
              102556,
              129882,
              68688,
              129939,
              137344,
              102624,
              90828,
              86487,
              91712,
              114866,
              75697,
              107599,
              99053,
              87511,
              128128,
              57772,
              69314,
              90771,
              145376,
              100730,
              142675,
              112731,
              83985,
              123565,
              127325,
              86597,
              121772,
              131992,
              148859,
              93348,
              77294,
              119763,
              74636,
              95592,
              79628,
              78861,
              68565,
              88820,
              134291,
              69262,
              128678,
              118216,
              52799,
              92731,
              61600,
              63477,
              64016,
              131872,
              131412,
              146579,
              104400,
              99110,
              63458,
              144393,
              54787,
              148622,
              91323,
              61137,
              106082,
              103644,
              63795,
              126648,
              61489,
              140964,
              110963,
              72696,
              124370,
              110466,
              139317,
              108440,
              148062,
              89992,
              145645,
              70556,
              95739
             }; // mass of each module


void setup() {
  // put your setup code here, to run once:

  M5.begin();
  M5.Lcd.fillScreen(BLACK);
  M5.Lcd.setCursor(0, 0);
  M5.Lcd.setTextColor(M5.Lcd.color565(255, 131, 250));
  M5.Lcd.setTextSize(2);
  M5.Lcd.println("Hi Santa, before going to lauch we need your help:");
  M5.Lcd.println("We need the amount of modules and the mass for each modul.");


  // --- Day 1: The Tyranny of the Rocket Equation ---

  // fuel for each modul
  for (int x = 0; x < i; x++) {
    fuel_counter_upper = fuel_counter_upper + mass[x] / 3 - 2;
  }
 M5.Lcd.setTextColor(M5.Lcd.color565(255, 100, 100));
  M5.Lcd.println("The needed amount of fuel for the modules is:");
  M5.Lcd.println(fuel_counter_upper);

  // fuel for each modul including fuel for fuel
  fuel_counter_upper = 0;
  for (int x = 0; x < i; x++) {
    temporary_fuel = mass[x] / 3 - 2;
    while (temporary_fuel > 0) {
      fuel_counter_upper = fuel_counter_upper + temporary_fuel;
      temporary_fuel = temporary_fuel / 3 - 2;
    }

  }
  M5.Lcd.println("The needed amount of fuel for the modules including fuel for fuel is:");
  M5.Lcd.println(fuel_counter_upper);
}


void loop() {
  // put your main code here, to run repeatedly:


}

