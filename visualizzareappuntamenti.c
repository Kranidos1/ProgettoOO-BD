#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<ctype.h>

	
int visualizza(char tm[16]){
	
	char tmpcodice[16];
	char tmpnome[20];
	char tmpcognome[20];
	char tmpesito[10];
	char tmpdata[12];
	char tmpfascia[10];
	float tmpval;
	
	FILE* g;
	g=fopen("presaincarico.o","r+");
	while(fscanf(g,"%s%s%s%s%s%s%f",&tmpcodice,&tmpnome,&tmpcognome,&tmpesito,&tmpdata,&tmpfascia,&tmpval)!=EOF){

		if(strcmp(tmpcodice,tm)==0){
			printf("Ciao %s,dovrai venire di %s",tmpcodice,tmpfascia);
			fclose(g);
			return 2;
		}
	}
		printf("Non presente nel db\nVuoi effettuare una richiesta?  ");
		char rispo[3];
											gets(rispo);
											int lunghezza=strlen(rispo);
											for(int cd=0;cd<2;cd++){
												rispo[cd]=tolower(rispo[cd]);
											}
											while(strcmp(rispo,"si")!=0 && strcmp(rispo,"no")!=0 || lunghezza!=2 ){
												printf("Risposta non valida,reinserisci una risposta o inserisci 1 per uscire dalla scelta.     ");
												gets(rispo);
												for(int cd=0;cd<2;cd++){
												rispo[cd]=tolower(rispo[cd]);
											}
											lunghezza=strlen(rispo);
												if(strcmp(rispo,"1")==0){
													printf("\nArrivederci");
													return 4;					
												}
											}
											if(strcmp(rispo,"si")==0){
												return 3;												
											}else
												if(strcmp(rispo,"no")==0){
													printf("Arrivederci!");
													return 4;
												
		fclose(g);
		return 3;
	}
}
