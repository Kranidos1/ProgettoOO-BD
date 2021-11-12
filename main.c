#include <iostream>
#include<stdio.h>
#include<stdlib.h>
#include<math.h>
#include<string.h>
#include"codice_fiscale.h"
#include"login_utente.h"
#include"richiestatamp.h"
#include"cancellatamp.h"
#include"visualizzareappuntamenti.h"
#include"visualizzaesito.h"
#include"visualizzasto.h"
#include"aggiungiel.h"
#include"punto2.h"
#include "letturarichieste.h"
#include"saving.h"

char risposta[3];

int main(){
	//FUNZIONE CONTROLLO PRIMO AVVIO DEL MEDICO
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
		return 0;
	}
	fclose(p);
//	int risp;
	printf("Ciao,sei un richiedente o un medico?\nInserisci 1 se sei un medico,0 se sei un richiedente\t");
//	scanf("%d",&risp);
//	char invio;
//	invio=getchar();
	char risp[200];
	gets(risp);
	while(strcmp(risp,"1")!=0 && strcmp(risp,"0")!=0){
		printf("Formato errato,inserisci una risposta(1 se medico o 0 se richiedente).");
		gets(risp);
	}
	
	if(strcmp(risp,"1")==0){
		//QUI CONTINUEREBBE PER IL MEDICO
		system("cls");
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
				
				printf("Benvenuto %s,di seguito la schermata di scelta.",user);
				system("cls");
				char rip;
				char invio;

				printf("Schermata di scelta:\n");
				printf("Inserisci:\n1 Per visualizzare lo storico \n2 Per visualizzare richieste tamponi e nel caso fissare appuntamenti\n3 Visualizzare appuntamenti gia fissati \n4 Per cancellare/aggiungere un appuntamento");
				scanf("%c",&rip);
				invio=getchar();
				while(rip!='1' && rip!='2' && rip!='3' && rip!='4'){
				printf("\nform err  ");
				scanf("%c",&rip);
				invio=getchar();
				}
				if(rip=='1'){
				visualizzastorico();
				}
				if(rip=='2'){
					int hg;
					hg=appuntamenti();
					if(hg==3){
						return 0;
					}else{
							laboratorio();
					}
				}
				if(rip=='3'){
					letturarichi();
				}
				if(rip=='4'){
					aggiungioelimina();
				}
				

			
			printf("Arrivederci!");
			return 0;
			//qui si chiude medico
			
	}else
		if(strcmp(risp,"0")==0){
			system("cls");
			//QUI CONTINUEREBBE PER L'UTENTE
			printf("Sei un utente registrato?\nScrivi ''si'' se sei registrato,''no'' se non lo sei     ");
			gets(risposta);
			int lunghezza=strlen(risposta);
			for(int cd=0;cd<2;cd++){
				risposta[cd]=tolower(risposta[cd]);
			}
			while(strcmp(risposta,"si")!=0 && strcmp(risposta,"no")!=0 || lunghezza!=2 ){
				printf("Risposta non valida,reinserisci una risposta o inserisci 1 per uscire.     ");
				gets(risposta);
				for(int cd=0;cd<2;cd++){
				risposta[cd]=tolower(risposta[cd]);
			}
			lunghezza=strlen(risposta);
				if(strcmp(risposta,"1")==0){
					printf("\nArrivederci");
					return 0;					
				}
			}
			if(strcmp(risposta,"no")==0){
				printf("Iniziamo la registrazione.\n");
				char ris=codicefiscalepassword();
				if(ris=='x'){
					printf("Registrazione avvenuta con successo!\n");

					printf("Il programma si chiude,riaprilo e effettua il login!");
					return 0;
				}else{
					//USCITA DAL PROGRAMMA DOPO RESET PW tramite reg
					printf("Arrivederci");
					return 0;
				}
			}else
				if(strcmp(risposta,"si")==0){
				system("cls");
				char risp;
			ut mat;
				mat=loginutente();
				
				char tmpnominativo[20];
				strcpy(tmpnominativo,mat.nome);

				//SCELTA EFFETTIVA'\0';
				if(mat.nome[0]!='l'){

							printf("\nQui inizia la scelta %s ",mat.codice);
								char rip;
				printf("Schermata di scelta:\n");
				printf("Inserisci:\n1 per richiedere un appuntamento\n2 per visualizzare appuntamenti gia fissati\n3 per cancellare un appuntamento\n4 per visionare il tuo esito  ");
				scanf("%c",&rip);
				char invio=getchar();
				while(rip!='1' && rip!='2' && rip!='3' && rip!='4'){
				printf("\nform err  ");
				scanf("%c",&rip);
					char invio=getchar();
				}//SERIE DI IF CON VALORI SCELTA
				if(rip=='1'){
//					strcpy(codicino,mat.nome);
					mat.val=richiesta(mat.codice);
					if(mat.val<7){
						//QUI AVVIENE INSERIMENTO NEL FILE APPOSITO
//						mat.codice[17]='\0';
						inserimentorichiesta(mat.codice,tmpnominativo,mat.cognome,mat.esito,mat.giorno,mat.fascia,mat.val);
						printf("Richiesta avvenuta.Attendi 5 ore minimo per poter visualizzare la data dell appuntamento.");
						return 0;
					}else
						//CHIUSURA NEL CASO FOSSE GIA PRESENTE NEL FILE RICHIESTE
						return 0;
				}
				//SCELTA 2
				if(rip=='2'){
					int corri=visualizza(mat.codice);
					if(corri==2){
						printf("\nArrivederci!");
					}
					if(corri==3){
						//utente vuole effettuare richiesta
						mat.val=richiesta(mat.codice);
						inserimentorichiesta(mat.codice,tmpnominativo,mat.cognome,mat.esito,mat.giorno,mat.fascia,mat.val);
						printf("Richiesta avvenuta.Attendi 5 ore minimo per poter visualizzare la data dell appuntamento.");
						return 0;
					}else
						return 0;
				}
				if(rip=='3'){
					//CANCELLAE TAMPONE
					int boing=cancellareg(mat.codice);
						if(boing==2){
							system("cls");
							printf("Richiesta cancellata con successo,il programma si chiude");
							return 0;
						}else
							if(boing==3){
								
								printf("\nVuoi effettuare una richiesta?\t");
											gets(risposta);
											int lunghezza=strlen(risposta);
											for(int cd=0;cd<2;cd++){
												risposta[cd]=tolower(risposta[cd]);
											}
											while(strcmp(risposta,"si")!=0 && strcmp(risposta,"no")!=0 || lunghezza!=2 ){
												printf("Risposta non valida,reinserisci una risposta o inserisci 1 per uscire dalla scelta.     ");
												gets(risposta);
												for(int cd=0;cd<2;cd++){
												risposta[cd]=tolower(risposta[cd]);
											}
											lunghezza=strlen(risposta);
												if(strcmp(risposta,"1")==0){
													printf("\nArrivederci");
													return 0;					
												}
											}
											if(strcmp(risposta,"si")==0){
												//QUI RICHIESTA
												mat.val=richiesta(mat.codice);
												inserimentorichiesta(mat.codice,mat.nome,mat.cognome,mat.esito,mat.giorno,mat.fascia,mat.val);
												printf("Richiesta avvenuta.Attendi 5 ore minimo per poter visualizzare la data dell appuntamento.");
												return 0;												
											}else
												if(strcmp(risposta,"no")==0){
													printf("Arrivederci!");
													return 0;
												}
							}
				}
					if(rip=='4'){
						//scelta 4
						esito(mat.codice);
						return 0;
					}
					return 0;
				}else{
					//uscita dal programma tramite login dopo reset
					return 0;
				}
				}

		}
		return 0;
}

