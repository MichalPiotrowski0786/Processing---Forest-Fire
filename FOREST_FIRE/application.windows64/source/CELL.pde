class CELLS{

int[][] state = new int[W][H];  
int[][] state2 = new int[W][H];  
int[][] timer = new int[W][H];  
color c;

private int populate(){
  int r = int(random(2));
  return r;
}

void startState(){
  for(int i = 10; i < W-10; i++){
    for(int j = 10; j < H-10; j++){
      state[i][j] = populate();
    }  
  }
}

void update(){
loadPixels();
  for(int i = 10; i < W-10; i++){
    for(int j = 10; j < H-10; j++){ 
      rules(i,j);
      show(i,j);
    }
  } 
updatePixels();
}

void rules(int x, int y){
 if(state[x][y] == 0 && int(random(-100000,100000)) == 0){
  state2[x][y] = 1; 
 }else if(state[x][y] == 1 && int(random(-10000000,10000000)) == 0){
  state2[x][y] = 2; 
 }else if(state[x][y] == 2 && int(random(0,10)) == 5){
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
   timer[x][y] -= 0.3;
}
}
