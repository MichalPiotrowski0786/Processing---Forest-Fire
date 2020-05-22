final int W = 1400;
final int H = 700;
final int SCALE = 1;

CELLS c;

void settings(){
size(W,H);  
}

void setup(){
c = new CELLS();  
c.startState();
c.update();
}

void draw(){
c.update();
}
