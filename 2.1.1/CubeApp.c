#include <stdio.h>

typedef struct{
    int x, y;
    int w, h, d;
} Cube;

void print (Cube* c);

void main (void){
    Cube c1 = {10, 10, 30, 30, 30};
    print(&c1);
}

void print(Cube* c){
    printf("Cubo %dx%dx%d esta na posicao (%d, %d).\n", c->w, c->h, c->d, c->x, c->y);
}
