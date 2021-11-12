#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<ctype.h>
	
int visualizzastorico(){
	
	char tmpcodice[16];
	char tmpnome[20];
	char tmpcognome[20];
	char tmpesito[10];
	char tmpdata[12];
	char tmpfascia[10];
	float tmpval;
	
	char riposa[3];
	int cd;
	FILE* g;
	g=fopen("storico.o","r+");
	while(fscanf(g,"%s%s%s%s%s%s%f",&tmpcodice,&tmpnome,&tmpcognome,&tmpesito,&tmpdata,&tmpfascia,&tmpval)!=EOF){
		printf("%s %s %s %s %s %s %.2f\n",&tmpcodice,&tmpnome,&tmpcognome,&tmpesito,&tmpdata,&tmpfascia,&tmpval);
	}
	fclose(g);
	return 0;
}
