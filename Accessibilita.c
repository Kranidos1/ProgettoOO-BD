#include<stdio.h>
#include<stdlib.h>
#include<math.h>
#include<string.h>
#include"codice_fiscale.h"

int main(){
	//FUNZIONE CONTROLLO PRIMO AVVIO
	FILE* p;
	p=fopen("medico.o","a+");
	char buf[200];
	if(fscanf(p,"%s",&buf)==EOF){
		rewind(p);
		printf("Primo avvio,registrati come medico");
		char user[25];
		char password[25];
		printf("\nInserisci user     ");
		gets(user);
		printf("Inserisci password     ");
		gets(password);
		fprintf(p,"%s %s",user,password);
		printf("%s\t%s",user,password);
		fclose(p);
		return ;
	}
	fclose(p);
	int risp;
	printf("Ciao,sei un richiedente o un medico?\nInserisci 1 se sei un medico,0 se sei un richiedente");
	printf("\n");
	scanf("%d",&risp);
	char invio;
	invio=getchar();
	int pip;
	if(risp==1){
		//QUI CONTINUEREBBE PER IL MEDICO
		p=fopen("medico.o","r+");
		char bufferuser[25];
		fscanf(p,"%s",&bufferuser);
		char user[25];
		printf("Inserisci username     ");
		gets(user);
		while(strcmp(user,bufferuser)!=0){
			printf("Username inesistente,reinserisci username o premi 1 per uscire.");
			gets(user);
			if(strcmp(user,"1")==0){
				printf("\nArrivederci");
				return 0;
			}
		}	
			char password[20];
			char bufferpassword[20];
			fscanf(p,"%s",&bufferpassword);

			printf("Benvenuto %s,inserisci la password     ",user);
			gets(password);
			while(strcmp(password,bufferpassword)!=0){
				printf("Password errata,inseriscila di nuovo o premi 1 per uscire.     ");
				gets(password);
				if(strcmp(password,"1")==0){
					printf("\nArrivederci");
					return 0;
				}
			}
			//QUI ANDREBBE LA SCELTA
	}else
		if(risp==0){
			//QUI CONTINUEREBBE PER L'UTENTE
			printf("Sei un utente registrato?\nScrivi ''si'' se sei registrato,''no'' se non lo sei     ");
			char risposta[3];
			gets(risposta);
			while(strcmp(risposta,"si")!=0 || strcmp(risposta,"no")!=0){
				printf("Risposta non valida,reinserisci una risposta o inserisci 1 per uscire.     ");
				gets(risposta);
				if(strcmp(risposta,"1")==0){
					printf("\nArrivederci");
					return 0;					
				}
			}
			if(strcmp(risposta,"no")==0){
				printf("Iniziamo la registrazione.");
			}

		}else{
			//QUI NEL CASO DI INSERIMENTO SBAGLIATO TORNEREBBE ALLA SCELTA
			printf("nomefunct");

		}

		return 0;
}

