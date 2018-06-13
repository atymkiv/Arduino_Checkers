
#include <VarSpeedServo.h>

VarSpeedServo servo1;
VarSpeedServo servo2;
VarSpeedServo servo3;
VarSpeedServo servo4;
VarSpeedServo servo5;
VarSpeedServo servo6;

void setup() {
    servo1.attach(3);
    servo2.attach(5);
    servo3.attach(6);
    servo4.attach(9);
    servo5.attach(10);
    servo6.attach(11);
    delay(1000);
 Start();
  servo6.slowmove(50,50);
  servo1.slowmove(82,20);
  delay(1000);
  servo2.slowmove(40,20);
  delay(1000);
  servo3.slowmove(63,20);
  delay(1000);
  servo4.slowmove(139,20);
  delay(1000);
  servo6.slowmove(100,50); 
  delay(1000);
  servo4.slowmove(100,20);
  delay(1000);
  servo3.slowmove(65,20);
  delay(1000); //взяло на 36
  servo1.slowmove(73,20);
  delay(1000);
  servo4.slowmove(135,20);
  delay(1000); 
  servo3.slowmove(60,20);
  delay(1000);
  servo2.slowmove(44,20);
  delay(1000);
  servo6.slowmove(60,50);
  delay(1000);//поставило на 47
  servo4.slowmove(80,20);
  delay(1000);
  servo3.slowmove(70,20);
  delay(1000);
  servo1.slowmove(85,20);
  delay(1000);
  servo2.slowmove(57,20);
  delay(1000);
  servo3.slowmove(63,20);
  delay(1000);
  servo4.slowmove(114,20);
  delay(1000);
  servo6.slowmove(100,50);
  delay(1000);//взяло на 67
  servo2.slowmove(40,50);
  delay(1000);
  servo3.slowmove(75,20);
  delay(1000);
  servo4.slowmove(80,20);
  delay(1000);
  servo1.slowmove(93,20);
  delay(1000);
  servo2.slowmove(48,20);
  delay(1000);
  servo3.slowmove(67,20);
  delay(1000);
  servo4.slowmove(117,20);
  delay(1000);
  servo6.slowmove(50,50);
  delay(1000); //поклало на 58
  //4!!
  servo3.slowmove(80,20);
  delay(1000);
  servo1.slowmove(100,20);
  delay(1000);
  servo2.slowmove(40,20);
  delay(1000);
  servo3.slowmove(42,20);
  delay(1000);
  servo4.slowmove(125,20);
  delay(1000);
  servo6.slowmove(100,50);
  delay(1000);//взяло на 38
  servo3.slowmove(70,20);
  delay(1000);
  servo4.slowmove(90,20);
  delay(1000);
  servo1.slowmove(88,20);
  delay(1000);
  servo4.slowmove(131,20);
  delay(1000);
  servo3.slowmove(60,20);
  delay(1000);
  servo2.slowmove(54,20);
  delay(1000);
  servo6.slowmove(50,50);
  delay(1000); //поклало на 47
  servo1.slowmove(93,20);
  delay(1000);
  servo4.slowmove(117,20);
  delay(1000);
  servo2.slowmove(48,20);
  delay(1000);
  servo3.slowmove(67,20);
  delay(1000);
  servo6.slowmove(100,50);
  delay(1000);//взяло на 58
  servo4.slowmove(100,20);
  delay(1000);
  servo3.slowmove(70,20);
  delay(1000);
  servo1.slowmove(80
  ,20);
  delay(1000);
  servo2.slowmove(40,20);
  delay(1000);
  servo3.slowmove(63,20);
  delay(1000);
  servo4.slowmove(139,20);
  delay(1000);
  servo6.slowmove(50,50);
  delay(1000);//поклало на 36
  servo4.slowmove(100,20);
  delay(1000);
  servo3.slowmove(70,20);
  delay(1000);
  servo1.slowmove(88,20);
  delay(1000);
  servo2.slowmove(50,20);
  delay(1000);
  servo3.slowmove(63,20);
  delay(1000);
  servo4.slowmove(134,20);
  delay(1000);
   servo6.slowmove(100,50);
  delay(1000);//взяло на 47
  servo4.slowmove(80,20);
  delay(1000);
  servo3.slowmove(70,20);
  delay(1000);
  Delete();
  Start();
}
void Start(){
    servo4.slowmove(80,20); 
    delay(2000);
    servo3.slowmove(65,20);
    delay(2000);
    servo2.slowmove(40,20);
    delay(2000);
    servo1.slowmove(68,20);
 }
  
void Delete(){
    servo1.slowmove(10,20);
    delay(2000);
    servo2.slowmove(30,20);
    delay(2000);
    servo3.slowmove(40,20);
    delay(2000);
    servo4.slowmove(130,20);
    delay(2000);
    servo6.slowmove(50,50);
  delay(1000);
}
void loop(){
  }
