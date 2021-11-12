#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<ctype.h>
	struct utente{
	char codice[17];
	char nome[20];
	char cognome[20];
	char esito[10];
	char giorno[12];
	char fascia[10];
	float val;
};
typedef struct utente ut;
ut* pip;
char tmp[200];

int cancellareg(char ric[16]){
int j=0;
int i=0;
	char tmpcodice[17];
	char tmpnome[20];
	char tmpcognome[20];
	char tmpesito[10];
	char tmpgiorno[12];
	char tmpfascia[10];
	float tmpval;
	
	FILE* g;
	g=fopen("richiestautenti.o","r");
	//ricerca presenza utente
	while(fgets(tmp,200,g)!=0){
		printf("%s",tmp);
		if(strncmp(tmp,ric,16)==0){
			break;
		}
	}
	//caso di chiusura in quanto utente non trovato
	if(strncmp(tmp,ric,16)!=0){
		printf("Non presente nel db");
		return 3;
	}
	rewind(g);
	
	while(fgets(tmp,200,g)!=0){
		i++;
	}
	int x;
	i--;
	pip=(ut*)malloc(i*sizeof(ut));
	rewind(g);
	char bu[20];
	while(fscanf(g,"%s%s%s%s%s%s%f",&tmpcodice,&tmpnome,&tmpcognome,&tmpesito,&tmpgiorno,&tmpfascia,&tmpval)!=EOF){

		if(strcmp(tmpcodice,ric)==0){
			continue;
		}
		strcpy(pip[j].codice,tmpcodice);
		strcpy(pip[j].nome,tmpnome);
		strcpy(pip[j].cognome,tmpcognome);
		strcpy(pip[j].esito,tmpesito);
		strcpy(pip[j].giorno,tmpgiorno);
		strcpy(pip[j].fascia,tmpfascia);
		pip[j].val=tmpval;
		
		j++;
	}
	fclose(g);
	//CANCELLA FILE E RICREA
	remove("richiestautenti.o");
	g=fopen("richiestautenti.o","w+");
	for(j=0;j<i;j++){
		printf("%s %s %s %s %s %s %.2f\n",pip[j].codice,pip[j].nome,pip[j].cognome,pip[j].esito,pip[j].giorno,pip[j].fascia,pip[j].val);
		fprintf(g,"%s %s %s %s %s %s %.2f\n",pip[j].codice,pip[j].nome,pip[j].cognome,pip[j].esito,pip[j].giorno,pip[j].fascia,pip[j].val);
	}
	fclose(g);
	free(pip);

	return 2;
}
