#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<ctype.h>
		char codes[16];
		char name[20];
		char surname[20];
		char end[10];
		char day[12];
		char fasc[10];
		float value;
		FILE* p;

float richiesta(char codinp[16]){
	float res=0;
	char ri[2];
	int i;
		p=fopen("richiestautenti.o","r+");
		while(fscanf(p,"%s%s%s%s%s%s%f",&codes,&name,&surname,&end,&day,&fasc,&value)!=EOF){
		if(strcmp(codes,codinp)==0){
			system("cls");
			printf("%s non e possibile effettuare una richiesta due volte\nControlla ogni 3 ore la sezione '' Appuntamenti fissati'' per visionare lo stato della tua richiesta!",name);
			return 7;
		}
	}
	rewind(p);
		codinp[16]='\0';
		fclose(p);		
		
		system("cls");
		printf("Compila questo breve questionario(rispondi con si o no)\n");
		printf("Hai febbre maggiore a 38 gradi?  ");
		gets(ri);
		for(i=0;i<2;i++){
			ri[i]=tolower(ri[i]);
		}
		while(strcmp(ri,"si")!=0 && strcmp(ri,"no")!=0){
			printf("Formato errato,inserisci si o no  ");
			gets(ri);
			for(i=0;i<2;i++){
			ri[i]=tolower(ri[i]);
		}
		}
		if(strcmp(ri,"si")==0){
			res=0.5;
		}
		printf("Hai la febbre da piu' di due giorni?\n");
		gets(ri);
		for(i=0;i<2;i++){
			ri[i]=tolower(ri[i]);
		}
		while(strcmp(ri,"si")!=0 && strcmp(ri,"no")!=0){
			printf("Formato errato,inserisci si o no  ");
			gets(ri);
			for(i=0;i<2;i++){
			ri[i]=tolower(ri[i]);
		}
		}
		if(strcmp(ri,"si")==0){
			res=res+0.5;
		}
		printf("Hai tosse secca?\n");
		gets(ri);
		for(i=0;i<2;i++){
			ri[i]=tolower(ri[i]);
		}
		while(strcmp(ri,"si")!=0 && strcmp(ri,"no")!=0){
			printf("Formato errato,inserisci si o no  ");
			gets(ri);
			for(i=0;i<2;i++){
			ri[i]=tolower(ri[i]);
		}
		}
		if(strcmp(ri,"si")==0){
			res=res+0.5;
		}
		printf("Hai diarrea?\n");		
			gets(ri);
		for(i=0;i<2;i++){
			ri[i]=tolower(ri[i]);
		}
		while(strcmp(ri,"si")!=0 && strcmp(ri,"no")!=0){
			printf("Formato errato,inserisci si o no  ");
			gets(ri);
			for(i=0;i<2;i++){
			ri[i]=tolower(ri[i]);
		}
		}
		if(strcmp(ri,"si")==0){
			res=res+1;
		}
		printf("Hai perso gusto o olfatto?\n");
			gets(ri);
		for(i=0;i<2;i++){
			ri[i]=tolower(ri[i]);
		}
		while(strcmp(ri,"si")!=0 && strcmp(ri,"no")!=0){
			printf("Formato errato,inserisci si o no  ");
			gets(ri);
			for(i=0;i<2;i++){
			ri[i]=tolower(ri[i]);
		}
		}
		if(strcmp(ri,"si")==0){
			res=res+1;
		}
		printf("Hai difficolta respiratorie?\n");
			gets(ri);
		for(i=0;i<2;i++){
			ri[i]=tolower(ri[i]);
		}
		while(strcmp(ri,"si")!=0 && strcmp(ri,"no")!=0){
			printf("Formato errato,inserisci si o no  ");
			gets(ri);
			for(i=0;i<2;i++){
			ri[i]=tolower(ri[i]);
		}
		}
		if(strcmp(ri,"si")==0){
			res=res+1;
		}	
		printf("Hai oppressione o dolore al petto?\n");
			gets(ri);
		for(i=0;i<2;i++){
			ri[i]=tolower(ri[i]);
		}
		while(strcmp(ri,"si")!=0 && strcmp(ri,"no")!=0){
			printf("Formato errato,inserisci si o no  ");
			gets(ri);
			for(i=0;i<2;i++){
			ri[i]=tolower(ri[i]);
		}
		}
		if(strcmp(ri,"si")==0){
			res=res+1;
		}
		printf("Sei piu stanco del solito?\n");
			gets(ri);
		for(i=0;i<2;i++){
			ri[i]=tolower(ri[i]);
		}
		while(strcmp(ri,"si")!=0 && strcmp(ri,"no")!=0){
			printf("Formato errato,inserisci si o no  ");
			gets(ri);
			for(i=0;i<2;i++){
			ri[i]=tolower(ri[i]);
		}
		}
		if(strcmp(ri,"si")==0){
			res=res+0.5;
		}	
		
	return res;
}
void inserimentorichiesta(char codinp[16],char nomeinp[20],char cognomeinp[20],char esitoinp[10],char datainp[12],char fascinp[10],float valinp){
	FILE* rich;
	rich=fopen("richiestautenti.o","a+");
	fprintf(rich,"%s %s %s %s %s %s %.2f\n",codinp,nomeinp,cognomeinp,esitoinp,datainp,fascinp,valinp);
	fclose(rich);
}
