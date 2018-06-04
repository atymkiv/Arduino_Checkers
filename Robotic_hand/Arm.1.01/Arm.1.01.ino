
#include <VarSpeedServo.h>

VarSpeedServo servo1;
VarSpeedServo servo2;
VarSpeedServo servo3;
VarSpeedServo servo4;
VarSpeedServo servo5;
VarSpeedServo servo6;
int OnGame, IsDelete, X1coordinat, Y1coordinat,X2coordinat, Y2coordinat;

void setup() {
    servo1.attach(3);
    servo2.attach(5);
    servo3.attach(6);
    servo4.attach(9);
    servo5.attach(10);
    servo6.attach(11);
    delay(2000);
 Start1();
  servo6.slowmove(45,50);
  
/*   Movetocell(3,6);
 delay(500);
 Get();
 delay(500);
 Start();
 Movetocell(4,5);
 delay(500);
 Put();*/
/* Start();
 Movetocell(6,7);
 delay(500);
 Get();
 delay(500);
 Start();
 delay(500);
 Movetocell(5,8);
 delay(500);
 Put();
 delay(500);
 Start();
*/




 Movetocell(4,7);
 delay(1000);
 
 /*delay(500);
 Get();
 delay(500);
 Start();
 delay(100);
 Movetocell(4,7);
 delay(100);
 Put();
 Start();
*/
 
    /*Movetocell(3,6);
    delay(2000);
    Start();
    Movetocell(4,5);
    delay(2000);
    Start1();
    Movetocell(6,7);
    */
   /*Movetocell(1,1);
   delay(2000);
   Get();
   delay(1000);
   Start();
   delay(1000);
   Movetocell(5,5);
   delay(1000);
   Put(); */
   /* Serial.begin(9600);
      Start();
     OnGame=Serial.read();
    while(OnGame==0){
      IsDelete=Serial.read();
      if(IsDelete==1) {
       X1coordinat=Serial.read();
       Y1coordinat=Serial.read();
       Movetocell(X1coordinat, Y1coordinat);
       Get();
       Start();
       Delete();
       Put();
      } else{
        X1coordinat=Serial.read();
       Y1coordinat=Serial.read();
       X2coordinat=Serial.read();
       Y2coordinat=Serial.read();
       Movetocell(X1coordinat, Y1coordinat);
       Get();
       Start();
       Movetocell(X2coordinat, Y2coordinat);
       Put();
       Start();
      }
    }*/
}
void Start1(){
    servo2.slowmove(40,20);
    delay(2000);
    servo1.slowmove(70,20);
    delay(2000);
    servo3.slowmove(65,20);
    delay(2000);
   servo4.slowmove(100,20); 
    delay(2000);
   servo5.slowmove(50,20);
 }
void Start(){
    servo5.slowmove(50,20);
    delay(2000);
    servo4.slowmove(100,20); 
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
    servo4.slowmove(60,20);
    delay(2000);
    servo5.slowmove(50,20);
    delay(2000);
}

void Move(int target1,int target2,int target3,int target4,int target5){
    servo1.slowmove(target1, 20);
    delay(2000);
    servo2.slowmove(target2,20);
    delay(2000);
    servo3.slowmove(target3,20);
    delay(2000);
    servo4.slowmove(target4,20);
    delay(2000);
    servo5.slowmove(target5,20);
    delay(2000);
}

void Get(){
  servo6.slowmove(95,50);
}

void Put(){
  servo6.slowmove(30,50);
}

void Movetocell(int i,int j){
  if(i==1){
      if (j==8) {           //(1;8)
        Move(110,57,53,145,50);            
      } else if (j==6){     //(1;6)
         Move(90,44,43,150,50);            
      } else if (j==4){     //(1;4)
        Move(63,44,42,150,50);             
      } else if (j==2){     //(1;2)  
        Move(39,57,50,147,50);            
      }
    } else if (i==2) {
      if (j==7) {           //(2;7)
        Move(96,52,50,140,50);             
      } else if (j==5){     //(2;5)
        Move(74,45,43,141,50);            
      } else if (j==3){     //(2;3)
        Move(52,50,45,140,50);             
      } else if (j==1){     //(2;1)
        Move(38,50,42,129,0); 
      }
    } else if (i==3) {      
      if (j==8) {           //(3;8)
        Move(100,40,39,120,0);
      } else if (j==6){     //(3;6)
        Move(82,40,63,139,0);
      } else if (j==4){     //(3;4)
        Move(60,0,0,0,0); 
      } else if (j==2){     //(3;2)
        Move(45,65,46,130,67); 
      }
    } else if (i==4) {
      if (j==7) {           //(4;7)
        Move(88,54,60,131,0);
      } else if (j==5){     //(4;5)
        Move(73,44,60,135,0);
      } else if (j==3){     //(4;3)
        Move(58,60,60,130,50); 
      } else if (j==1){     //(4;1)
        Move(45,62,55,120,67); 
      }
    } else if (i==5) {
      if (j==8) {           //(5;8)
        Move(93,48,67,117,0);
      } else if (j==6){     //(5;6)
        Move(78,0,0,0,0);
      } else if (j==4){     //(5;4)
        Move(65,100,45,135,0); 
      } else if (j==2){     //(5;2)
        Move(50,56,47,38,50); 
      }
    } else if (i==6) {
      if (j==7) {           //(6;7)
        Move(85,57,63,114,0);
      } else if (j==5){     //(6;5)
        Move(70,0,0,0,0);
      } else if (j==3){     //(6;3)
        Move(60,0,0,0,0);
      } else if (j==1){     //(6;1)
        Move(48,40,50,60,65); 
      }
    } else if (i==7) {
      if (j==8) {           //(7;8)
        Move(83,0,0,0,0);
      } else if (j==6){     //(7;6)
        Move(75,0,0,0,0);
      } else if (j==4){     //(7;4)
        Move(65,0,0,0,0);
} else if (j==2){           //(7;2)
        Move(55,0,0,0,0);
      }
    } else if (i==8) {
      if (j==7) {           //(8;7)
        Move(81,0,0,0,0);
      } else if (j==5){     //(8;5)
        Move(69,0,0,0,0);
      } else if (j==3){     //(8;3)
        Move(59,0,0,0,0); 
      } else if (j==1){     //(8;1)
        Move(51,0,0,0,0);
      }
}
}


void loop(){

    }
