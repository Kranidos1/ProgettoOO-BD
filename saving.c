#include<stdio.h>
#include<stdlib.h>
#include<string.h>
struct utenza{
	char codic[17];
	char nome[20];
	char cognome[20];
	char esito[20];
	char giorno[20];
	char fascia[20];
	float val;
};
typedef struct utenza K;


void laboratorio(){
	char bufcod[17];
char bufnome[20];
char bufcognome[20];
char bufgiorno[20];
char bufesito[20];
char bufascia[20];
float bufval;
	char tmp[200];
	
	FILE* g;
	FILE* p;
	int contatorpos=0;
	int contatorneg=0;
	int contato=0;
	g=fopen("storico.o","r+");
	p=fopen("piattaforma.o","r+");
	
	while(fscanf(g,"%s%s%s%s%s%s%f",&bufcod,&bufnome,&bufcognome,&bufesito,&bufgiorno,&bufascia,&bufval)!=EOF){
		if(strcmp(bufesito,"Positivo")==0){
			contatorpos++;
			continue;
		}
		if(strcmp(bufesito,"Negativo")==0){
			contatorneg++;
			continue;
		}
	}
	rewind(g);
	fclose(g);
	while(fgets(tmp,200,p)!=0){
		contato++;
	}
	rewind(p);
	fclose(p);
	//STRUCT PER POSITIVI
	K* pos=(K*)malloc(contatorpos*sizeof(K));
	//STRUCT PER NEGATIVI
	K* neg=(K*)malloc(contatorneg*sizeof(K));
	//STRUCT PER GIA SEGNALATI
	K* segnalati=(K*)malloc(contato*sizeof(K));
	//RIEMPIO GIA SEGNALATI
	p=fopen("piattaforma.o","r+");
	int j=0;
	while(fscanf(p,"%s%s%s%s%s%s%f",&segnalati[j].codic,&segnalati[j].nome,&segnalati[j].cognome,&segnalati[j].esito,&segnalati[j].giorno,&segnalati[j].fascia,&segnalati[j].val)!=EOF && j<contato){
		j++;
	}
	rewind(p);
	fclose(p);
	//RIEMPIO NEGATIVI E POSITIVI
	//RIEMPIMENTO COMBINATO STRUTTURA NEGATIVI E POSITIVI
	g=fopen("storico.o","r+");
	j=0;
	int h=0;
	while(j<contatorneg || h<contatorpos){
		fscanf(g,"%s%s%s%s%s%s%f",&bufcod,&bufnome,&bufcognome,&bufesito,&bufgiorno,&bufascia,&bufval);
		if(strcmp(bufesito,"Positivo")==0){
			strcpy(pos[h].codic,bufcod);
			strcpy(pos[h].nome,bufnome);
			strcpy(pos[h].cognome,bufcognome);
			strcpy(pos[h].esito,bufesito);
			strcpy(pos[h].giorno,bufgiorno);
			strcpy(pos[h].fascia,bufascia);
			pos[h].val=bufval;
			h++;
			continue;
		}
		if(strcmp(bufesito,"Negativo")==0){
			strcpy(neg[j].codic,bufcod);
			strcpy(neg[j].nome,bufnome);
			strcpy(neg[j].cognome,bufcognome);
			strcpy(neg[j].esito,bufesito);
			strcpy(neg[j].giorno,bufgiorno);
			strcpy(neg[j].fascia,bufascia);
			neg[j].val=bufval;
			j++;
			continue;
		}
	}	
	fclose(g);
	
	//INSERIMENTO DEI VECCHI(ELIMINANDO I NUOVI NEGATIVI) E NUOVI POSITIVI ALL'INTERNO DELLA SEGNALAZIONE
	int y=0;
	while(y<contato){
		y++;	
	}
	
	y=0;
	int c;
	remove("piattaforma.o");
	int pi=0;
	p=fopen("piattaforma.o","w+");
	while(y<contato){
		c=0;
			while(c<contatorneg){
					if(strcmp(segnalati[y].codic,neg[c].codic)==0){
				if(strcmp(segnalati[y].esito,neg[c].esito)!=0){
					break;
				}
			}
			c++;
			}
			if(strcmp(segnalati[y].codic,neg[c].codic)==0){
				y++;
				continue;
			}
			fprintf(p,"%s %s %s %s %s %s %.2f\n",segnalati[y].codic,segnalati[y].nome,segnalati[y].cognome,segnalati[y].esito,segnalati[y].giorno,segnalati[y].fascia,segnalati[y].val);
			y++;
			pi++;
	}
	K* rimod=(K*)malloc(pi*sizeof(K));
	remove("piattaforma.o");
	p=fopen("piattaforma.o","w+");
	y=0;
	pi=0;
	while(y<contato){
		c=0;
			while(c<contatorneg){
					if(strcmp(segnalati[y].codic,neg[c].codic)==0){
				if(strcmp(segnalati[y].esito,neg[c].esito)!=0){
					break;
				}
			}
			c++;
			}
			if(strcmp(segnalati[y].codic,neg[c].codic)==0){
				y++;
				continue;
			}
			strcpy(rimod[pi].codic,segnalati[y].codic);
			strcpy(rimod[pi].codic,segnalati[y].nome);
			strcpy(rimod[pi].codic,segnalati[y].cognome);
			strcpy(rimod[pi].codic,segnalati[y].esito);
			strcpy(rimod[pi].codic,segnalati[y].giorno);
			strcpy(rimod[pi].codic,segnalati[y].fascia);
			rimod[pi].val=segnalati[y].val;
			fprintf(p,"%s %s %s %s %s %s %.2f         \n",rimod[pi].codic,rimod[pi].nome,rimod[pi].cognome,rimod[pi].esito,rimod[pi].giorno,rimod[pi].fascia,rimod[pi].val);
			y++;
			pi++;
	}	
	int u=0;
	while(u<contatorpos){
	
		fprintf(p,"%s %s %s %s %s %s %.2f  \n",pos[u].codic,pos[u].nome,pos[u].cognome,pos[u].esito,pos[u].giorno,pos[u].fascia,pos[u].val);
		u++;
	}
	fclose(p);

	//ricorda free neg
	free(pos);
	free(neg);
	free(segnalati);
	free(rimod);
	return;
}

