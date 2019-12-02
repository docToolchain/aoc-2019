// day00 by RJPlog, 29.11.2019

#include <M5Stack.h>

void setup() {
  // put your setup code here, to run once:

  M5.begin();
  M5.Lcd.fillScreen(BLACK);
  M5.Lcd.setCursor(0, 80);
  M5.Lcd.setTextColor(M5.Lcd.color565(255, 131, 250));
  M5.Lcd.setTextSize(3);
  M5.Lcd.println(" hello world ");
}

void loop() {
  // put your main code here, to run repeatedly:

}
