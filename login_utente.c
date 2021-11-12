#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<ctype.h>
char boh;
int cambiopw(char codic[16]);
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
ut pippo;
//ut pippo=(ut)malloc(1*sizeof(ut));
ut loginutente(){
	FILE* t;
	t=fopen("utentiaccesso.o","r+");
	printf("Inserisci codice fiscale\t");
	char codicefisc[17];
	char password[20];
	gets(codicefisc);
	int x;
	x=strlen(codicefisc);
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
	
	
	while(x>16 || x<=14 || lettere==0 || letter==0 || lette==0 || lett==0 || let==0 || le==0 || l==0 || numeri==0 || numer==0 || nume==0 || num==0 || nu==0 || n==0 || opo==0 || op==0 || o==0){
		printf("Formato errato,inserisci il codice fiscale o inserisci 1 per uscire\t");
		gets(codicefisc);
		if(strcmp("1",codicefisc)==0){
				system("cls");

			printf("Arrivederci!");

			pippo.nome[0]='l';
			return pippo;
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
	char bufdom[25];
	//QUI AVVIENE UN UPPER SE NECESSARIO
	int j;
	for(j=0;j<16;j++){
		codicefisc[j]=toupper(codicefisc[j]);
	}
	char sol[20];
	char bufcod[17];
	char bufpassword[20];
	int compar;
	while(fscanf(t,"%s %s %s",&bufcod,&bufpassword,&bufdom)>0){
		compar=strcmp(bufcod,codicefisc);
		if(compar==0){
			strcpy(sol,bufcod);
			printf("Inserisci password:\t");
			gets(password);
			x=strlen(password);
			//CONTROLLO LUNGHEZZA O USCITA
			while(x>20){
				printf("Formato errato,inserisci di nuovo o inserisci 1 per uscire\t");
				gets(password);
				if(strcmp("1",password)==0){
					system("cls");
				printf("Arrivederci!");
				pippo.nome[0]='l';
				return pippo;
					}
				x=strlen(password);				
			}
			//CONTROLLO CORRISPONDENZA
			int risp;
			risp=strcmp(bufpassword,password);
			if(risp==0){
//				system("cls");
				FILE* g;
				g=fopen("utentiregistrati.o","r+");
	char codic[20];
	char nome[20];
	char cognome[20];
	char esito[20];
	char data[20];
	char fascia[10];
	float val;
				while(fscanf(g,"%s%s%s%s%s%s%f",codic,nome,cognome,esito,data,fascia,&val)!=EOF){
					if(strcmp(codic,bufcod)==0){
						fclose(g);
						strcpy(pippo.codice,bufcod);
						strcpy(pippo.cognome,cognome);
						strcpy(pippo.nome,nome);
						strcpy(pippo.esito,esito);
						strcpy(pippo.giorno,data);
						strcpy(pippo.fascia,fascia);
						pippo.val=val;
						return pippo;
					}
				}

			}else{
				int counter=0;
				while(risp!=0){
					counter++;
					if(counter>4){
						printf("Vuoi resettare la password?");
						char answer[3];
						gets(answer);
						int o;
						for(o=0;o<2;o++){
							answer[o]=tolower(answer[o]);
						}
						if(strcmp(answer,"si")==0){
							fclose(t);
							int asd=cambiopw(bufcod);
								system("cls");
							if(asd==1){
								printf("Il programma si chiude,riaprilo e accedi con le nuove credenziali!");
							pippo.nome[0]='l';
							return pippo;
							}else{
								printf("Arrivederci");
								pippo.nome[0]='l';
								return pippo;
							}
						}
					}
					printf("Password errata,inseriscila di nuovo o premi 1 per uscire\t");
					gets(password);
					risp=strcmp(password,bufpassword);
					if(risp==0){
				system("cls");
				FILE* g;
				g=fopen("utentiregistrati.o","r+");
	char nome[20];
	char cognome[20];
	char esito[20];
	char data[20];
	char codice[20];
	char fascia[10];
	float val;
				while(fscanf(g,"%s%s%s%s%s%f",&codice,&nome,&cognome,&esito,&data,&fascia,&val)!=EOF){
					if(strcmp(codice,sol)==0){
						fclose(g);
						strcpy(pippo.codice,bufcod);
						strcpy(pippo.cognome,cognome);
						strcpy(pippo.nome,nome);
						strcpy(pippo.esito,esito);
						strcpy(pippo.giorno,data);
						strcpy(pippo.fascia,fascia);
						pippo.val=val;
						return pippo;
					}
				}
					}
					
					risp=strcmp(password,"1");
					if(risp==0){
							system("cls");
						printf("Arrivederci");
						pippo.nome[0]='l';
						return pippo;
					}
				}
			}
		}
	}

	if(feof(t)){
			fclose(t);
			system("cls");
			printf("Non sei presente nel database,riaccedi ed effettua una registrazione o verifica i tuoi dati!\nIl programma si chiude.");
			pippo.nome[0]='l';
			return pippo;
	}
}


int cambiopw(char codic[17]){
int val;
char buffer[120];
char buff[20];
int i;
struct utente{
	char id[17];
	char password[20];
	char sicurezza[20];
};
typedef struct utente utenza;
FILE* p;
p=fopen("utentiaccesso.o","r+");
int conta=0;
while(fgets(buffer,120,p)!=0){
	conta++;
}
rewind(p);
utenza* x;
x=(utenza*)malloc(conta*sizeof(utenza));
for(i=0;i<conta;i++){
	fscanf(p,"%s%s%s",&x[i].id,&x[i].password,&x[i].sicurezza);
	
char cit[20];
int leng;
	if(strcmp(x[i].id,codic)==0){
		printf("(Domanda di sicurezza)Nome della tua citta preferita: ");
		gets(cit);
		leng=strlen(cit);
		while(leng>20 || strcmp(x[i].sicurezza,cit)!=0){
			printf("Risposta errata\nInserisci di nuovo una risposta o 1 per uscire:  ");
			gets(cit);
			if(strcmp(cit,"1")==0){
				system("cls");
				int va=2;
				return va;
			}
			leng=strlen(cit);
		}
		printf("Nuova password\t");
		gets(x[i].password);
	}
}
rewind(p);
for(i=0;i<conta;i++){
	fprintf(p,"%s %s %s\n",x[i].id,x[i].password,x[i].sicurezza);
}
free(x);
fclose(p);
val=1;
return val;
}
