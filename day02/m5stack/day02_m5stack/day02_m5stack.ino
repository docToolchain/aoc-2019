// day02 by RJPlog, 02.12.2019

#include <M5Stack.h>

int i = 10000; // max lines of programm
int x; // counter

int gravity_assist_prog[] = {1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,6,19,2,19,6,23,1,23,5,27,1,9,27,31,1,31,10,35,2,35,9,39,1,5,39,43,2,43,9,47,1,5,47,51,2,51,13,55,1,55,10,59,1,59,10,63,2,9,63,67,1,67,5,71,2,13,71,75,1,75,10,79,1,79,6,83,2,13,83,87,1,87,6,91,1,6,91,95,1,10,95,99,2,99,6,103,1,103,5,107,2,6,107,111,1,10,111,115,1,115,5,119,2,6,119,123,1,123,5,127,2,127,6,131,1,131,5,135,1,2,135,139,1,139,13,0,99,2,0,14,0};

void setup() {
  // put your setup code here, to run once:

  M5.begin();
  M5.Lcd.fillScreen(BLACK);
  M5.Lcd.setCursor(0, 0);
  M5.Lcd.setTextColor(M5.Lcd.color565(255, 131, 250));
  M5.Lcd.setTextSize(2);
  M5.Lcd.println("Hi Santa, before continuing we need your help:");
  M5.Lcd.println("We need the first number of the gravity assist programm.");


  // --- Day 2: 1202 Program Alarm ---

  for (int x = 0; x < i; x = x + 4) {
    if (gravity_assist_prog[x] == 1) {
      gravity_assist_prog[gravity_assist_prog[x + 3]] = gravity_assist_prog[gravity_assist_prog[x + 1]] + gravity_assist_prog[gravity_assist_prog[x + 2]];
    }
    else if (gravity_assist_prog[x] == 2) {
      gravity_assist_prog[gravity_assist_prog[x + 3]] = gravity_assist_prog[gravity_assist_prog[x + 1]] * gravity_assist_prog[gravity_assist_prog[x + 2]];
    }
    else if (gravity_assist_prog[x] == 99) {
      break;
    }
  }
  M5.Lcd.setTextColor(M5.Lcd.color565(255, 100, 100));
  M5.Lcd.println("The first number of the program is:");
  M5.Lcd.println(gravity_assist_prog[0]);
}

void loop() {
  // put your main code here, to run repeatedly:

}

