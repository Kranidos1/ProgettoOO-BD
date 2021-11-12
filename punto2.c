#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>
	char tmpt[200];
	FILE* t;
	FILE* h;
	char giornocont[20];
	int giornocon;
	char ito[5];
	int x;
	//BUFFER
	char codicu[16];
	char noma[20];
	char cognoma[20];
	char esito[20];
	char giorno[20];
	char fascia[20];
	float val;
		char tmps[200];
	char bufferfigo[20];
	
	struct utente{
		char codice[17];
		char nome[20];
		char cognome[20];
		char esito[20];
		char giorno[20];
		char fascia[20];
		float val;
	};
	typedef struct utente ut;
	ut* rip;
	
void swap(ut* A,int x,int h);
void stampa(ut* A,int size);
void Ordinamento(int m,ut* a);


int appuntamenti(){
		char giornop[20];
	FILE* g;
	g=fopen("richiestautenti.o","r+");
	char codice[17];
	char nome[20];
	char cognome[20];
	char esito[20];
	char giorno[20];
	char fascia[20];
	float val;
	char tmp[200];
	int cont=0;
	while(fgets(tmp,200,g)!=0){
		cont++;
	}
	rewind(g);
	int i=0;
	rip=(ut*)malloc(cont*sizeof(ut));
	while(fscanf(g,"%s%s%s%s%s%s%f",&codicu,&noma,&cognoma,&esito,&giorno,&fascia,&val)!=EOF){
		strcpy(rip[i].codice,codicu);
		strcpy(rip[i].nome,noma);
		strcpy(rip[i].cognome,cognoma);
		strcpy(rip[i].esito,esito);
		strcpy(rip[i].giorno,giorno);
		strcpy(rip[i].fascia,fascia);
		rip[i].val=val;
		i++;
	}
	if(i==0){
		printf("Non ci sono richieste presenti!");
		return 3;
	}
	//DEBUG
	int k;
	for(k=0;k<cont;k++){
		rip[k].codice[16]='\0';
	}
	

	Ordinamento(cont,rip);
	//qui va stampa
	stampa(rip,cont);
	fclose(g);
	
	FILE* p;
	p=fopen("presincarico.o","r+");
	t=fopen("contagiorni.o","r+");
	fscanf(t,"%s%d",&giornocont,&giornocon);
	fprintf(p,"%s %d\n",giornocont,giornocon);
	fclose(t);
	fclose(p);
	p=fopen("presincarico.o","r+");
	//GESTIONE ERRORE APERTURA FILE
	if(!p){
		printf("error");
	}
	//CONTATORE PER FARE COSE
	int contatore=0;
	int verifica=0;
	int ult=0;
	char pene[20];
	//GESTIONE CASO BASE
	fscanf(p,"%s%d",&giornop,&x);
	
	if(fscanf(p,"%s%s%s%s%s%s%.2f",&codicu,&noma,&cognoma,&esito,&giorno,&fascia,&val)==EOF){
		while(contatore<cont){
			if(verifica==6){
				x=x+1;
				fprintf(p,"%s %d\n",giornop,x);
				verifica=0;
			}
			if(verifica==0 || verifica==1){
				strcpy(rip[contatore].fascia,"Mattina");
			}
			if(verifica==2 || verifica==3 ){
				strcpy(rip[contatore].fascia,"Pomeriggio");
			}
			if(verifica==4 || verifica==5){
				strcpy(rip[contatore].fascia,"Sera");
			}
			
			itoa(x,ito,10);
			strcat(rip[contatore].giorno,ito);
//			rip[contatore].giorno[6]=x;
			fprintf(p,"%s %s %s %s %s %s %.2f\n",rip[contatore].codice,rip[contatore].nome,rip[contatore].cognome,rip[contatore].esito,rip[contatore].giorno,rip[contatore].fascia,rip[contatore].val);
			contatore++;
			verifica++;

		}
		rewind(p);
	}else{
	//CASO IN CUI CI SIANO GIA STRIGNHE
	verifica=0;
	while(fgets(tmps,200,p)!=0){
		if(verifica==7){
			verifica=0;
		}
		ult++;
		verifica++;
	}
	//CASO PERFETTO fixed
	if(verifica==7){
				x=x+(ult/7)+1;
				fprintf(p,"%s %d\n",giornop,x);
				verifica=0;
				
		while(contatore<cont){
			if(verifica==6){
				x=x+1;
				fprintf(p,"%s %d\n",giornop,x);
				verifica=0;
			}
			
			if(verifica==0 || verifica==1){
				strcpy(rip[contatore].fascia,"Mattina");
			}
			if(verifica==2 || verifica==3){
				strcpy(rip[contatore].fascia,"Pomeriggio");
			}
			if(verifica==4 || verifica==5){
				strcpy(rip[contatore].fascia,"Sera");
			}
			
			itoa(x,ito,10);
			strcat(rip[contatore].giorno,ito);
//			rip[contatore].giorno[6]=x;
			fprintf(p,"%s %s %s %s %s %s %.2f\n",rip[contatore].codice,rip[contatore].nome,rip[contatore].cognome,rip[contatore].esito,rip[contatore].giorno,rip[contatore].fascia,rip[contatore].val);
			contatore++;
			verifica++;

		}					
	}	//CASO IMPERFETTO
	else{
		x=x+(ult/7);
		while(contatore<cont){
			if(verifica==6){
				x=x+1;
				fprintf(p,"%s %d\n",giornop,x);
				verifica=0;
			}
			
			if(verifica==0 || verifica==1){
				strcpy(rip[contatore].fascia,"Mattina");
			}
			if(verifica==2 || verifica==3){
				strcpy(rip[contatore].fascia,"Pomeriggio");
			}
			if(verifica==4 || verifica==5){
				strcpy(rip[contatore].fascia,"Sera");
			}
			
			itoa(x,ito,10);
			strcat(rip[contatore].giorno,ito);
			fprintf(p,"%s %s %s %s %s %s %.2f\n",rip[contatore].codice,rip[contatore].nome,rip[contatore].cognome,rip[contatore].esito,rip[contatore].giorno,rip[contatore].fascia,rip[contatore].val);
			contatore++;
			verifica++;
		}		
	}
		}
	
	char risposta[2];
	//PARTE DEL CODICE PER FARE TAMPONI ALLA CHIUSURA
	fclose(p);
	p=fopen("presincarico.o","r+");
	FILE* r;
	char* j;
	char* o;
	char pol[5];
	char copia[200];
	char copy[200];
	char gio[11]="Giorno";
	int var,pippo,gf;
	gf=0;
	r=fopen("storico.o","a+");
	h=fopen("appoggio.o","w+");
	//USARE APPOGGIO E TRRASFORMARE FILE

	while(fgets(tmpt,200,p)!=0){
			if(strncmp(tmpt,"Giorno",6)==0){
				strcpy(copy,tmpt);
				j=strtok(tmpt," ");
				j=strtok(NULL," ");
					var=atoi(j);
					
						if(var==giornocon){
								while(fgets(tmpt,200,p)!=0){
									strcpy(copia,tmpt);
									o=strtok(tmpt," ");
									o=strtok(NULL," ");
									o=strtok(NULL," ");
									if(o==NULL){
										break;
									}
									o=strtok(NULL," ");
									o=strtok(NULL," ");
									if(gf<=6){
										puts(copia);
										fputs(copia,r);
									}
									gf++;
								}

								if(gf<=6){

								fprintf(h,"%s %d\n","Giorno",giornocon+1);
								continue;
								}
						}
	
					fprintf(h,"%s %d\n","Giorno",var);
					continue;
			}
		
			fputs(tmpt,h);
	}
	fclose(h);
	char tmpi[200];
	remove("presincarico.o");
	p=fopen("presincarico.o","w+");
	h=fopen("appoggio.o","r+");
	int ty=0;
	char* tuk;
	int nid;
	while(fgets(tmpi,200,h)!=0){
		puts(tmpi);
		fputs(tmpi
		,p);
	}
	fclose(h);
	
	remove("appoggio.o");
	//PULIZIA RICHIESTE
	remove("richiestautenti.o");
	g=fopen("richiestautenti.o","w+");
	fclose(g);
//AGGIORNAMENTO CONTA GIORNI CORRETTO
	t=fopen("contagiorni.o","r+");
	fprintf(t,"%s %d",giornocont,giornocon+1);
	fclose(t);
	fclose(p);
	fclose(h);
	fclose(r);
	//QUI VENGONO MESSI I VALORI DEI TAMPONI
	struct uten{
		char fiscal[16];
		char noms[20];
		char cognoms[20];
		char esit[20];
		char giorn[20];
		char fasc[20];
		float val;
	};
	typedef struct uten Z;
i=1;
float rest;


	char temper[200];
	FILE* z;
	int contostorico=0;
	z=fopen("testo.o","r+");
	while(fgets(temper,200,z)!=0){
		contostorico++;
	}
	rewind(z);
	Z* vect;
	int ap=0;
	vect=(Z*)malloc(contostorico*sizeof(Z));
	while(fscanf(z,"%s%s%s%s%s%s%f",&vect[ap].fiscal,&vect[ap].noms,&vect[ap].cognoms,&vect[ap].esit,&vect[ap].giorn,&vect[ap].fasc,&vect[ap].val)!=EOF && ap<contostorico){
			if(strcmp(vect[ap].esit,"Esito")==0){
				rest=i%2;
				if(rest==0){
					strcpy(vect[ap].esit,"Positivo");
				}else{
					strcpy(vect[ap].esit,"Negativo");
				}
			}
		i++;
		ap++;
	}
	fclose(z);
	remove("testo.o");
	z=fopen("testo.o","w+");
	gf;
	for(gf=0;gf<contostorico;gf++){
		fprintf(z,"%s %s %s %s %s %s %.2f\n",&vect[gf].fiscal,&vect[gf].noms,&vect[gf].cognoms,&vect[gf].esit,&vect[gf].giorn,&vect[gf].fasc,&vect[gf].val);
	}
	fclose(z);
	free(vect);
	free(rip);
	return 0;

}

//FUNZIONI NECESSARIE

void swap(ut* A,int x,int h){
	ut tmp;
	//COPIA IN TMP
	strcpy(tmp.codice,A[h].codice);
	strcpy(tmp.nome,A[h].nome);
	strcpy(tmp.cognome,A[h].cognome);
	strcpy(tmp.esito,A[h].esito);
	strcpy(tmp.giorno,A[h].giorno);
	strcpy(tmp.fascia,A[h].fascia);
	tmp.val=A[h].val;
	//COPIA DI A[X] IN A[H]
	strcpy(A[h].codice,A[x].codice);
	strcpy(A[h].nome,A[x].nome);
	strcpy(A[h].cognome,A[x].cognome);
	strcpy(A[h].esito,A[x].esito);
	strcpy(A[h].giorno,A[x].giorno);
	strcpy(A[h].fascia,A[x].fascia);
	A[h].val=A[x].val;
	//COPIA DI TMP IN A[x]
	strcpy(A[x].codice,tmp.codice);
	strcpy(A[x].nome,tmp.nome);
	strcpy(A[x].cognome,tmp.cognome);
	strcpy(A[x].esito,tmp.esito);
	strcpy(A[x].giorno,tmp.giorno);
	strcpy(A[x].fascia,tmp.fascia);
	A[x].val=tmp.val;
	
	return;
}

void stampa(ut* A,int size){
	int i;
	for(i=0;i<size;i++){
		printf("%s %s %s %s %s %s %.2f\n",A[i].codice,A[i].nome,A[i].cognome,A[i].esito,A[i].giorno,A[i].fascia,A[i].val);
	}
	return;
}

void Ordinamento(int m,ut* a){
int j,o;
		for(j=0;j<m;j++){
			for(o=j+1;o<m;o++){
				if(a[j].val<a[o].val){
					swap(a,j,o);
				}
			}
		}
}
