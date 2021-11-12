#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<ctype.h>

void esito(char tmpcod[16]){
	
	char tmpcodice[16];
	char tmpnome[20];
	char tmpcognome[20];
	char tmpesito[10];
	char tmpdata[12];
	char tmpfascia[10];
	float tmpval;
	
	FILE* g;
	g=fopen("storico.o","r+");
	while(fscanf(g,"%s%s%s%s%s%s%f",&tmpcodice,&tmpnome,&tmpcognome,&tmpesito,&tmpdata,&tmpfascia,&tmpval)!=EOF){
//		printf("Ciao %s,dovrai venire di %s",tmpcodice,tmpfascia);
		if(strcmp(tmpcodice,tmpcod)==0){
			printf("Ciao %s,sei risultato %s",tmpcodice,tmpesito);
			return;
		}
	}
	if(feof(g)){
		printf("Risultato ancora non disponibile,controlla tra qualche ora!");
		return;
	}
	fclose(g);
}
