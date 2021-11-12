#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>

char lettura[200];
int letturarichi(){
	FILE* g;
	g=fopen("presincarico.o","r+");
	while(fgets(lettura,200,g)!=0){
		puts(lettura);
	}

	fclose(g);
	return 0;
}
