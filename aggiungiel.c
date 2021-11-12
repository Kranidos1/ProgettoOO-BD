#include<stdio.h>
#include<stdlib.h>
#include<math.h>
#include<string.h>
#include<ctype.h>

int aggiungioelimina(){
int presenza=0;
struct bru{
	char tmpe[200];
};
typedef struct bru P;

char stringaric[16];
int contatorep=0;
char tmpu[200];
int lungform;
int maiusc;
	printf("Vuoi aggiungere o cancellare un utente?(Inserisci 1 per aggiungere e 0 per cancellare)\t");
	char risp[200];
	gets(risp);
	//CONTROLLO FORMATO SCELTA
	while(strcmp(risp,"1")!=0 && strcmp(risp,"0")!=0){
		printf("Formato errato,inserisci una risposta(1 se medico o 0 se richiedente).");
		gets(risp);
	}
	if(strcmp(risp,"0")==0){
		system("cls");
	
	printf("Inserisci il codice fiscale dell'utente da cancellare.\n");
	gets(stringaric);
	lungform=strlen(stringaric);
	//CONTROLLO FORMATO CODICE FISCALE
	for(maiusc=0;maiusc<16;maiusc++){
		stringaric[maiusc]=toupper(stringaric[maiusc]);
	}
	
	while(lungform!=16){
		printf("Formato errato,inserisci 1 per uscire o un codice valido\t");
		gets(stringaric);
			if(strcmp(stringaric,"1")==0){
				system("cls");
				printf("Arrivederci!");
				return 0;
			}
			lungform=strlen(stringaric);
				for(maiusc=0;maiusc<16;maiusc++){
				stringaric[maiusc]=toupper(stringaric[maiusc]);
				}
	}
	
	FILE* g;
	g=fopen("presincarico.o","r+");
	while(fgets(tmpu,200,g)!=0){
		if(strncmp(tmpu,stringaric,16)==0){
			continue;
		}
		contatorep++;
	}
	rewind(g);
	P* mart=(P*)malloc(contatorep*sizeof(P));
	int asc=0;
	while(fgets(tmpu,200,g)!=0){		
			if(strncmp(tmpu,stringaric,16)==0){
				presenza++;
				continue;
			}
			
		strcpy(mart[asc].tmpe,tmpu);
		asc++;
	}
	int k;
	
	//riempimento dopo cancella
	remove("presincarico.o.o");
	g=fopen("presincarico.o","w+");
		for(k=0;k<contatorep;k++){
		fprintf(g,"%s",mart[k].tmpe);
	}
	fclose(g);
	free(mart);
	fclose(g);
	if(presenza==1){
		printf("Utente cancellato correttamente!");
	}else{
		printf("Utente non presente nel db,il programma si chiude!");
	}
	
	}else{
		char tmp[200];
int x;
int y=0;
char tmpi[200];
char gio[11];
char in[5];
char* p;
char* t;
char* k;
int d;
	FILE* g;
	g=fopen("presincarico.o","r+");
	while(fgets(tmp,200,g)!=0){
		if(strncmp(tmp,"Giorno",6)==0){

			x=0;
			x++;
			p=strtok(tmp," ");
			p=strtok(NULL," ");
			y=atoi(p);
			itoa(y,in,10);
			strcpy(gio,"Giorno");
			strcat(gio,in);
			continue;
		}
		strcpy(tmpi,tmp);
		t=strtok(tmp," ");
		t=strtok(NULL," ");
		t=strtok(NULL," ");
		t=strtok(NULL," ");
		t=strtok(NULL," ");
		if(strcmp(t,gio)==0){
			//DIM CHE STRINGA GIORNO E' 0

			x++;
		}
	}
	char giorn[11];
	strcpy(giorn,"Giorno");
	//CASO NECESSARIO FPRINTF GIORNO NUOVO
	if(x==7){
		k=strtok(t,"o");
		k=strtok(NULL,"o");
		k=strtok(NULL,"o");
		d=atoi(k);
		d=d+1;
		fprintf(g,"\n%s %d\n","Giorno",d);
		char iut[5];
		itoa(d,iut,10);
		strcat(giorn,iut);
		//AGGIUNTA
		printf("Aggiungi appuntamento\nInserisci codice fiscale\t");
		char giorno[20];
		char codicefisc[20];
		char nome[20];
		char cognome[20];
		//FASCIA PER FORZA MATTINA!
		int val=0;
	gets(codicefisc);
	int f;
	f=strlen(codicefisc);
	int lettere,letter,lette,lett,let,le,l;
	lettere=isdigit(codicefisc[6]);
	letter=isdigit(codicefisc[7]);
	lette=isdigit(codicefisc[9]);
	lett=isdigit(codicefisc[10]);
	let=isdigit(codicefisc[12]);
	le=isdigit(codicefisc[13]);
	l=isdigit(codicefisc[14]);
	
	int numeri,numer,nume,num,nu,n,opo,op,o;
	numeri=isalpha(codicefisc[0]);
	numer=isalpha(codicefisc[1]);
	nume=isalpha(codicefisc[2]);
	num=isalpha(codicefisc[3]);
	nu=isalpha(codicefisc[4]);
	n=isalpha(codicefisc[5]);
	opo=isalpha(codicefisc[8]);
	op=isalpha(codicefisc[11]);
	o=isalpha(codicefisc[15]);
	
	
	while(f>16 || f<=14 || lettere==0 || letter==0 || lette==0 || lett==0 || let==0 || le==0 || l==0 || numeri==0 || numer==0 || nume==0 || num==0 || nu==0 || n==0 || opo==0 || op==0 || o==0){
		printf("Formato errato,inserisci il codice fiscale o inserisci 1 per uscire\t");
		gets(codicefisc);
		if(strcmp("1",codicefisc)==0){
				system("cls");

			printf("Arrivederci!");
			return 0;
		}
	//VERIFICHE RIGUARDO LA LUNGHEZZA,LA POSIZIONE DEI NUMERI E DELLE LETTERE
	x=strlen(codicefisc);
	
	lettere=isdigit(codicefisc[6]);
	letter=isdigit(codicefisc[7]);
	lette=isdigit(codicefisc[9]);
	lett=isdigit(codicefisc[10]);
	let=isdigit(codicefisc[12]);
	le=isdigit(codicefisc[13]);
	l=isdigit(codicefisc[14]);
	
	numeri=isalpha(codicefisc[0]);
	numer=isalpha(codicefisc[1]);
	nume=isalpha(codicefisc[2]);
	num=isalpha(codicefisc[3]);
	nu=isalpha(codicefisc[4]);
	n=isalpha(codicefisc[5]);
	opo=isalpha(codicefisc[8]);
	op=isalpha(codicefisc[11]);
	o=isalpha(codicefisc[15]);
	
	}
	//QUI AVVIENE UN UPPER SE NECESSARIO
	int j;
	for(j=0;j<16;j++){
		codicefisc[j]=toupper(codicefisc[j]);
	}
	
	printf("Inserisci nome\t");
	gets(nome);
	int leng;
	leng=strlen(nome);
	while(leng>20){
		printf("Formato errato,inserisci di nuovo o 1 per uscire");
		gets(nome);
				if(strcmp("1",nome)==0){
				system("cls");

			printf("Arrivederci!");
			return 0;
		}
		leng=strlen(nome);
	}
	printf("Inserisci cognome\t");
	gets(cognome);
	leng=strlen(cognome);
	while(leng>20){
		printf("Formato errato,inserisci di nuovo o 1 per uscire");
		gets(cognome);
				if(strcmp("1",cognome)==0){
				system("cls");

			printf("Arrivederci!");
			return 0;
		}
		leng=strlen(cognome);
	}
	fprintf(g,"%s %s %s %s %s %s %.2f\n",codicefisc,nome,cognome,"Esito",giorn,"Mattina",0);	
	}else
		if(x<7){
		k=strtok(t,"o");
		k=strtok(NULL,"o");
		k=strtok(NULL,"o");
		d=atoi(k);
		
		char iut[5];
		itoa(d,iut,10);
		strcat(giorn,iut);
		//AGGIUNTA
		printf("Aggiungi appuntamento\nInserisci codice fiscale\t");
		char giorno[20];
		char codicefisc[20];
		char nome[20];
		char cognome[20];
		//FASCIA PER FORZA MATTINA!
		int val=0;
	gets(codicefisc);
	int f;
	f=strlen(codicefisc);
	int lettere,letter,lette,lett,let,le,l;
	lettere=isdigit(codicefisc[6]);
	letter=isdigit(codicefisc[7]);
	lette=isdigit(codicefisc[9]);
	lett=isdigit(codicefisc[10]);
	let=isdigit(codicefisc[12]);
	le=isdigit(codicefisc[13]);
	l=isdigit(codicefisc[14]);
	
	int numeri,numer,nume,num,nu,n,opo,op,o;
	numeri=isalpha(codicefisc[0]);
	numer=isalpha(codicefisc[1]);
	nume=isalpha(codicefisc[2]);
	num=isalpha(codicefisc[3]);
	nu=isalpha(codicefisc[4]);
	n=isalpha(codicefisc[5]);
	opo=isalpha(codicefisc[8]);
	op=isalpha(codicefisc[11]);
	o=isalpha(codicefisc[15]);
	
	
	while(f>16 || f<=14 || lettere==0 || letter==0 || lette==0 || lett==0 || let==0 || le==0 || l==0 || numeri==0 || numer==0 || nume==0 || num==0 || nu==0 || n==0 || opo==0 || op==0 || o==0){
		printf("Formato errato,inserisci il codice fiscale o inserisci 1 per uscire\t");
		gets(codicefisc);
		if(strcmp("1",codicefisc)==0){
				system("cls");

			printf("Arrivederci!");
			return 0;
		}
	//VERIFICHE RIGUARDO LA LUNGHEZZA,LA POSIZIONE DEI NUMERI E DELLE LETTERE
	f=strlen(codicefisc);
	
	lettere=isdigit(codicefisc[6]);
	letter=isdigit(codicefisc[7]);
	lette=isdigit(codicefisc[9]);
	lett=isdigit(codicefisc[10]);
	let=isdigit(codicefisc[12]);
	le=isdigit(codicefisc[13]);
	l=isdigit(codicefisc[14]);
	
	numeri=isalpha(codicefisc[0]);
	numer=isalpha(codicefisc[1]);
	nume=isalpha(codicefisc[2]);
	num=isalpha(codicefisc[3]);
	nu=isalpha(codicefisc[4]);
	n=isalpha(codicefisc[5]);
	opo=isalpha(codicefisc[8]);
	op=isalpha(codicefisc[11]);
	o=isalpha(codicefisc[15]);
	
	}
	//QUI AVVIENE UN UPPER SE NECESSARIO
	int j;
	for(j=0;j<16;j++){
		codicefisc[j]=toupper(codicefisc[j]);
	}
	
	printf("Inserisci nome\t");
	gets(nome);
	int leng;
	leng=strlen(nome);
	while(leng>20){
		printf("Formato errato,inserisci di nuovo o 1 per uscire");
		gets(nome);
				if(strcmp("1",nome)==0){
				system("cls");

			printf("Arrivederci!");
			return 0;
		}
		leng=strlen(nome);
	}
	printf("Inserisci cognome\t");
	gets(cognome);
	leng=strlen(cognome);
	while(leng>20){
		printf("Formato errato,inserisci di nuovo o 1 per uscire");
		gets(cognome);
				if(strcmp("1",cognome)==0){
				system("cls");

			printf("Arrivederci!");
			return 0;
		}
		leng=strlen(cognome);
	}
	if(x==1 || x==2){
			fprintf(g,"%s %s %s %s %s %s %.2f\n",codicefisc,nome,cognome,"Esito",giorn,"Mattina",0);	
			return 0;
	}else
		if(x==3 || x==4){
			fprintf(g,"%s %s %s %s %s %s %.2f\n",codicefisc,nome,cognome,"Esito",giorn,"Pomeriggio",0);	
		}else
			if(x==5 || x==6){
				fprintf(g,"%s %s %s %s %s %s %.2f\n",codicefisc,nome,cognome,"Esito",giorn,"Sera",0);	
			}
		}
	fclose(g);
	}
	return 0;
}
