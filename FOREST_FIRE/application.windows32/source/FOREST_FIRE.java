import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class FOREST_FIRE extends PApplet {

final int W = 1400;
final int H = 700;
final int SCALE = 2;

CELLS c;

public void settings(){
size(W,H);  
}

public void setup(){
c = new CELLS();  
c.startState();
c.update();
}

public void draw(){
c.update();
}
class CELLS{

int[][] state = new int[W][H];  
int[][] state2 = new int[W][H];  
int[][] timer = new int[W][H];  
int c;

private int populate(){
  int r = PApplet.parseInt(random(2));
  return r;
}

public void startState(){
  for(int i = 10; i < W-10; i++){
    for(int j = 10; j < H-10; j++){
      state[i][j] = populate();
    }  
  }
}

public void update(){
loadPixels();
  for(int i = 10; i < W-10; i++){
    for(int j = 10; j < H-10; j++){ 
      rules(i,j);
      show(i,j);
    }
  } 
updatePixels();
}

public void rules(int x, int y){
 if(state[x][y] == 0 && PApplet.parseInt(random(-100000,100000)) == 0){
  state2[x][y] = 1; 
 }else if(state[x][y] == 1 && PApplet.parseInt(random(-10000000,10000000)) == 0){
  state2[x][y] = 2; 
 }else if(state[x][y] == 2 && PApplet.parseInt(random(0,10)) == 5){
  for(int k = -1; k < 2;k++){
    for(int m = -1; m < 2;m++){
      if(state[x+k][y+m] == 1){
         state2[x+k][y+m] = 2;
      }else if(state[x+k][y+m] == 2){
         timer[x+k][y+m] = 255;
         state2[x+k][y+m] = 3; 
      }else{
         state2[x+k][y+m] = state[x+k][y+m];
      }
    } 
  }
 }else if(state[x][y] == 3 && timer[x][y] < 1){
  state2[x][y] = 0; 
 }else{
  state2 = state; 
 }
 state = state2;
}

private void show(int x, int y){
   if(state[x][y] == 0){
    c = color(0); 
   }else if(state[x][y] == 1){
    c = color(0,150,60); 
   }else if(state[x][y] == 2){
    c = color(255,0,0); 
   }else if(state[x][y] == 3){
    c = color(timer[x][y],0,0); 
   }
   pixels[x+y*W] = c;
   timer[x][y] -= 0.3f;
}
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "FOREST_FIRE" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
