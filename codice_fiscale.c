#include<stdio.h>
#include<string.h>
#include<ctype.h>
#include<stdlib.h>
#include<string.h>
#define MAX 100
char nome[MAX];
char cognome[MAX];
char sesso;
char codice[17];
char prov[MAX];
FILE *fp;
int i,c,n,k,j,nc=0,cont=0;
int cambiopw(char codic[17]);
char codicefiscalepassword(){
    /*Lettura nome e cognome sesso e data di nascita*/
    //cognome
    do{
	printf("Cognome:");
	for(i=0;((cognome[i]=getchar())!='\n') && i<MAX;i++)
	    ;
	cognome[i]='\0';
	c=i;
    }while(i<1);
    //nome
    do{
	printf("Nome:");
	for(i=0;((nome[i]=getchar())!='\n') && i<MAX;i++)
	    ;
	nome[i]='\0';
	n=i;
    }while(i<1);
    //sesso
    do{
	printf("Sesso M/F:");
	scanf("%1s",&sesso);
    }while
	(!((sesso=='m')||(sesso=='M') || (sesso=='f') || (sesso=='F')));
    /*Eliminazione di spazi e , ecc..*/
    do{
	j=0;
	for(i=0;i<c;i++){
	    if(!((cognome[i]>='A' && cognome[i]<='Z') || (cognome[i]>='a' && cognome[i]<='z'))){
		for(k=i;k<=c-1;k++)
		    cognome[k]=cognome[k+1];
		c--;
		j=1;
	    }
	}
    }while
	(j);
    do{
	j=0;
	for(i=0;i<n;i++){
	    if(!((nome[i]>='A' && nome[i]<='Z') || (nome[i]>='a' && nome[i]<='z'))){
		for(k=i;k<=n-1;k++)
		    nome[k]=nome[k+1];
		n--;
		j=1;
	    }
	}
    }while
	(j);
    /*Caratteri to UPPERCASE*/
    for(i=0;i<c;i++){
	if(!(cognome[i]>='A' && cognome[i]<='Z'))
	    cognome[i]=cognome[i]-32;
    }
    for(i=0;i<n;i++){
	if(!(nome[i]>='A' && nome[i]<='Z'))
	    nome[i]=nome[i]-32;
    }
    /*Tre consonanti cognome*/
    //numero consonanti del cognome
    for(i=0;i<c;i++)
	if(!(cognome[i]=='A' || cognome[i]=='E' || cognome[i]=='I' || cognome[i]=='O' || cognome[i]=='U'))
	    nc++;
    //piu di tre consonanti
    if(nc>=3){
	i=0;
	for(k=0;k<3;k++){
	    for(;i<c;i++)
	    if(!(cognome[i]=='A' || cognome[i]=='E' || cognome[i]=='I' || cognome[i]=='O' || cognome[i]=='U')){
		codice[k]=cognome[i];
		break;
	    }
	    i++;
	}
    }
    //meno di tre consonanti
    else if(nc<3 && nc>0 && c>2){
	    i=0;
	    cont=0;
	    do{
		for(;i<c;i++)
		    if(!(cognome[i]=='A' || cognome[i]=='E' || cognome[i]=='I' || cognome[i]=='O' || cognome[i]=='U')){
			codice[cont]=cognome[i];
			cont++;
			i++;
			break;
		    }
	    }while(cont<nc);
	    for(i=0;i<c;i++)
		if((cognome[i]=='A' || cognome[i]=='E' || cognome[i]=='I' || cognome[i]=='O' || cognome[i]=='U')){
		    codice[cont]=cognome[i];
		    cont++;
		    if (cont==4)
			break;
		}
    }
    //0 consonanti e lunghezza>=3
    else if (nc==0 && c>=3){
	for(k=0;k<3;k++)
		codice[k]=cognome[k];
    }
    //0 consonanti e lunghezza<3
    else if(c<3 && nc==0){
	for(k=0;k<3;k++){
	    if(k<c)
		codice[k]=cognome[k];
	    else
		codice[k]='X';
	}
    }
    //piu di 0 consonanti e lunghezza <3
    else if (nc<3 && nc>0 && c<3){
	    i=0;
	    cont=0;
	    do{
		for(;i<c;i++)
		    if(!(cognome[i]=='A' || cognome[i]=='E' || cognome[i]=='I' || cognome[i]=='O' || cognome[i]=='U')){
			codice[cont]=cognome[i];
			cont++;
			i++;
			break;
		    }
	    }while(cont<nc);
	    for(i=0;i<c;i++){
		if((cognome[i]=='A' || cognome[i]=='E' || cognome[i]=='I' || cognome[i]=='O' || cognome[i]=='U')){
		    codice[cont]=cognome[i];
		    cont++;
		    if (cont==4)
			break;
		}
	    }
	    for(k=cont;k<3;k++)
		codice[k]='X';
    }
    /*Tre caratteri consonanti del nome*/
    //Numero consonanti
    nc=0;
    for(i=0;i<n;i++)
	if(!(nome[i]=='A' || nome[i]=='E' || nome[i]=='I' || nome[i]=='O' || nome[i]=='U'))
	    nc++;
    //piu di tre consonanti
    if(nc>3){
	i=0;
	for(k=3;k<7;k++){
	    for(;i<n;i++)
		if(!(nome[i]=='A' || nome[i]=='E' || nome[i]=='I' || nome[i]=='O' || nome[i]=='U')){
		    if(k!=5 && k<5)
			codice[k]=nome[i];
		    if(k>=5)
			codice[k-1]=nome[i];
		    break;
		}
		i++;
	}
    }
    else if(nc==3){
	i=0;
	for(k=3;k<6;k++){
	    for(;i<n;i++)
		if(!(nome[i]=='A' || nome[i]=='E' || nome[i]=='I' || nome[i]=='O' || nome[i]=='U')){
		    codice[k]=nome[i];
		    break;
		}
		i++;
	}
    }
    else if(nc<3 && nc>0 && n>2){
	    i=0;
	    cont=0;
	    do{
		for(;i<n;i++)
		    if(!(nome[i]=='A' || nome[i]=='E' || nome[i]=='I' || nome[i]=='O' || nome[i]=='U')){
			codice[cont+3]=nome[i];
			cont++;
			i++;
			break;
		    }
	    }while(cont<nc);
	    for(i=0;i<n;i++)
		if((nome[i]=='A' || nome[i]=='E' || nome[i]=='I' || nome[i]=='O' || nome[i]=='U')){
		    codice[cont+3]=nome[i];
		    cont++;
		    if (cont==3) 
			break;
		}
    }
    //0 consonanti e lunghezza>=3
    else if (nc==0 && n>=3){
	for(k=3;k<6;k++)
		codice[k]=nome[k-3];
    }
    //0 consonanti e lunghezza<3
    else if(n<3 && nc==0){
	for(k=3;k<6;k++){
	    if(k<n+3)
		codice[k]=nome[k-3];
	    else
		codice[k]='X';
	}
    }
    //piu di 0 consonanti e lunghezza <3
    else if (nc<3 && nc>0 && n<3){
	    i=0;
	    cont=0;
	    do{
		for(;i<c;i++)
		    if(!(nome[i]=='A' || nome[i]=='E' || nome[i]=='I' || nome[i]=='O' || nome[i]=='U')){
			codice[cont+3]=nome[i];
			cont++;
			i++;
			break;
		    }
	    }while(cont<nc);
	    for(i=0;i<c;i++){
		if((nome[i]=='A' || nome[i]=='E' || nome[i]=='I' || nome[i]=='O' || nome[i]=='U')){
		    codice[cont+3]=nome[i];
		    cont++;
		    if (cont==3)
			break;
		}
	    }
	    for(k=cont+3;k<6;k++)
		codice[k]='X';
    }
    /*Anno di nascita*/
    int gg,mm,aa;
    do{
	k=0;
	printf("Inserire data di nascita(gg/mm/aaaa):");
	scanf("%d/%d/%d",&gg,&mm,&aa);
	switch(mm){
	    case 1:if(gg<1 || gg>31)	k=1;	break;
	    case 2:
		if((aa%4)==0){
		    if(gg<1 || gg>29)   k=1;
		}else
		    if(gg<1 || gg>28)	k=1;	break;
	    case 3:if(gg<1 || gg>31)	k=1;	break;
	    case 4:if(gg<1 || gg>30) 	k=1;	break;
	    case 5:if(gg<1 || gg>31)	k=1;	break;
	    case 6:if(gg<1 || gg>30) 	k=1;	break;
	    case 7:if(gg<1 || gg>31)	k=1;	break;
	    case 8:if(gg<1 || gg>31)	k=1;	break;    
	    case 9:if(gg<1 || gg>30) 	k=1;	break;    
	    case 10:if(gg<1 || gg>31)	k=1;	break;
	    case 11:if(gg<1 || gg>30) 	k=1;	break;    
	    case 12:if(gg<1 || gg>31)	k=1;	break;
	    default:
		k=1;
	}
	if(aa<1900 || aa>=2099)
	    k=1;
    }while
	(k);
    if(aa<=1999 && aa>=1900)
	aa=aa-1900;
    else if(aa>=2000)
	aa=aa-2000;
    k=6;

    switch(aa){
	case 0:codice[k]='0'; codice[k+1]='0';	break;
	case 1:codice[k]='0'; codice[k+1]='1';	break;
	case 2:codice[k]='0'; codice[k+1]='2';	break;
	case 3:codice[k]='0'; codice[k+1]='3';	break;
	case 4:codice[k]='0'; codice[k+1]='4';	break;
	case 5:codice[k]='0'; codice[k+1]='5';	break;
	case 6:codice[k]='0'; codice[k+1]='6';	break;
	case 7:codice[k]='0'; codice[k+1]='7';	break;
	case 8:codice[k]='0'; codice[k+1]='8';	break;
	case 9:codice[k]='0'; codice[k+1]='9';	break;
	case 10:codice[k]='1'; codice[k+1]='0';	break;
	case 11:codice[k]='1'; codice[k+1]='1';	break;
	case 12:codice[k]='1'; codice[k+1]='2';	break;
	case 13:codice[k]='1'; codice[k+1]='3';	break;
	case 14:codice[k]='1'; codice[k+1]='4';	break;
	case 15:codice[k]='1'; codice[k+1]='5';	break;
	case 16:codice[k]='1'; codice[k+1]='6';	break;
	case 17:codice[k]='1'; codice[k+1]='7';	break;
	case 18:codice[k]='1'; codice[k+1]='8';	break;
	case 19:codice[k]='1'; codice[k+1]='9';	break;
	case 20:codice[k]='2'; codice[k+1]='0';	break;
	case 21:codice[k]='2'; codice[k+1]='1';	break;
	case 22:codice[k]='2'; codice[k+1]='2';	break;
	case 23:codice[k]='2'; codice[k+1]='3';	break;
	case 24:codice[k]='2'; codice[k+1]='4';	break;
	case 25:codice[k]='2'; codice[k+1]='5';	break;
	case 26:codice[k]='2'; codice[k+1]='6';	break;
	case 27:codice[k]='2'; codice[k+1]='7';	break;
	case 28:codice[k]='2'; codice[k+1]='8';	break;
	case 29:codice[k]='2'; codice[k+1]='9';	break;
	case 30:codice[k]='3'; codice[k+1]='0';	break;
	case 31:codice[k]='3'; codice[k+1]='1';	break;
	case 32:codice[k]='3'; codice[k+1]='2';	break;
	case 33:codice[k]='3'; codice[k+1]='3';	break;
	case 34:codice[k]='3'; codice[k+1]='4';	break;
	case 35:codice[k]='3'; codice[k+1]='5';	break;
	case 36:codice[k]='3'; codice[k+1]='6';	break;
	case 37:codice[k]='3'; codice[k+1]='7';	break;
	case 38:codice[k]='3'; codice[k+1]='8';	break;
	case 39:codice[k]='3'; codice[k+1]='9';	break;
	case 40:codice[k]='4'; codice[k+1]='0';	break;
	case 41:codice[k]='4'; codice[k+1]='1';	break;
	case 42:codice[k]='4'; codice[k+1]='2';	break;
	case 43:codice[k]='4'; codice[k+1]='3';	break;
	case 44:codice[k]='4'; codice[k+1]='4';	break;
	case 45:codice[k]='4'; codice[k+1]='5';	break;
	case 46:codice[k]='4'; codice[k+1]='6';	break;
	case 47:codice[k]='4'; codice[k+1]='7';	break;
	case 48:codice[k]='4'; codice[k+1]='8';	break;
	case 49:codice[k]='4'; codice[k+1]='9';	break;
	case 50:codice[k]='5'; codice[k+1]='0';	break;
	case 51:codice[k]='5'; codice[k+1]='1';	break;
	case 52:codice[k]='5'; codice[k+1]='2';	break;
	case 53:codice[k]='5'; codice[k+1]='3';	break;
	case 54:codice[k]='5'; codice[k+1]='4';	break;
	case 55:codice[k]='5'; codice[k+1]='5';	break;
	case 56:codice[k]='5'; codice[k+1]='6';	break;
	case 57:codice[k]='5'; codice[k+1]='7';	break;
	case 58:codice[k]='5'; codice[k+1]='8';	break;
	case 59:codice[k]='5'; codice[k+1]='9';	break;
	case 60:codice[k]='6'; codice[k+1]='0';	break;
	case 61:codice[k]='6'; codice[k+1]='1';	break;
	case 62:codice[k]='6'; codice[k+1]='2';	break;
	case 63:codice[k]='6'; codice[k+1]='3';	break;
	case 64:codice[k]='6'; codice[k+1]='4';	break;
	case 65:codice[k]='6'; codice[k+1]='5';	break;
	case 66:codice[k]='6'; codice[k+1]='6';	break;
	case 67:codice[k]='6'; codice[k+1]='7';	break;
	case 68:codice[k]='6'; codice[k+1]='8';	break;
	case 69:codice[k]='6'; codice[k+1]='9';	break;
	case 70:codice[k]='7'; codice[k+1]='0';	break;
	case 71:codice[k]='7'; codice[k+1]='1';	break;
	case 72:codice[k]='7'; codice[k+1]='2';	break;
	case 73:codice[k]='7'; codice[k+1]='3';	break;
	case 74:codice[k]='7'; codice[k+1]='4';	break;
	case 75:codice[k]='7'; codice[k+1]='5';	break;
	case 76:codice[k]='7'; codice[k+1]='6';	break;
	case 77:codice[k]='7'; codice[k+1]='7';	break;
	case 78:codice[k]='7'; codice[k+1]='8';	break;
	case 79:codice[k]='7'; codice[k+1]='9';	break;
	case 80:codice[k]='8'; codice[k+1]='0';	break;
	case 81:codice[k]='8'; codice[k+1]='1';	break;
	case 82:codice[k]='8'; codice[k+1]='2';	break;
	case 83:codice[k]='8'; codice[k+1]='3';	break;
	case 84:codice[k]='8'; codice[k+1]='4';	break;
	case 85:codice[k]='8'; codice[k+1]='5';	break;
	case 86:codice[k]='8'; codice[k+1]='6';	break;
	case 87:codice[k]='8'; codice[k+1]='7';	break;
	case 88:codice[k]='8'; codice[k+1]='8';	break;
	case 89:codice[k]='8'; codice[k+1]='9';	break;
	case 90:codice[k]='9'; codice[k+1]='0';	break;
	case 91:codice[k]='9'; codice[k+1]='1';	break;
	case 92:codice[k]='9'; codice[k+1]='2';	break;
	case 93:codice[k]='9'; codice[k+1]='3';	break;
	case 94:codice[k]='9'; codice[k+1]='4';	break;
	case 95:codice[k]='9'; codice[k+1]='5';	break;
	case 96:codice[k]='9'; codice[k+1]='6';	break;
	case 97:codice[k]='9'; codice[k+1]='7';	break;
	case 98:codice[k]='9'; codice[k+1]='8';	break;
	case 99:codice[k]='9'; codice[k+1]='9';	break;
   }
   k=8;
   switch(mm){
    case 1:codice[k]='A';	break;
    case 2:codice[k]='B';	break;
    case 3:codice[k]='C';	break;
    case 4:codice[k]='D';	break;
    case 5:codice[k]='E';	break;
    case 6:codice[k]='H';	break;
    case 7:codice[k]='L';	break;
    case 8:codice[k]='M';	break;
    case 9:codice[k]='P';	break;
    case 10:codice[k]='R';	break;
    case 11:codice[k]='S';	break;
    case 12:codice[k]='T';	break;
   }
   k=9;
   if (sesso=='f' || sesso=='F')
    gg+=40;
    switch(gg){
	case 1:codice[k]='0'; codice[k+1]='1';	break;
	case 2:codice[k]='0'; codice[k+1]='2';	break;
	case 3:codice[k]='0'; codice[k+1]='3';	break;
	case 4:codice[k]='0'; codice[k+1]='4';	break;
	case 5:codice[k]='0'; codice[k+1]='5';	break;
	case 6:codice[k]='0'; codice[k+1]='6';	break;
	case 7:codice[k]='0'; codice[k+1]='7';	break;
	case 8:codice[k]='0'; codice[k+1]='8';	break;
	case 9:codice[k]='0'; codice[k+1]='9';	break;
	case 10:codice[k]='1'; codice[k+1]='0';	break;
	case 11:codice[k]='1'; codice[k+1]='1';	break;
	case 12:codice[k]='1'; codice[k+1]='2';	break;
	case 13:codice[k]='1'; codice[k+1]='3';	break;
	case 14:codice[k]='1'; codice[k+1]='4';	break;
	case 15:codice[k]='1'; codice[k+1]='5';	break;
	case 16:codice[k]='1'; codice[k+1]='6';	break;
	case 17:codice[k]='1'; codice[k+1]='7';	break;
	case 18:codice[k]='1'; codice[k+1]='8';	break;
	case 19:codice[k]='1'; codice[k+1]='9';	break;
	case 20:codice[k]='2'; codice[k+1]='0';	break;
	case 21:codice[k]='2'; codice[k+1]='1';	break;
	case 22:codice[k]='2'; codice[k+1]='2';	break;
	case 23:codice[k]='2'; codice[k+1]='3';	break;
	case 24:codice[k]='2'; codice[k+1]='4';	break;
	case 25:codice[k]='2'; codice[k+1]='5';	break;
	case 26:codice[k]='2'; codice[k+1]='6';	break;
	case 27:codice[k]='2'; codice[k+1]='7';	break;
	case 28:codice[k]='2'; codice[k+1]='8';	break;
	case 29:codice[k]='2'; codice[k+1]='9';	break;
	case 30:codice[k]='3'; codice[k+1]='0';	break;
	case 31:codice[k]='3'; codice[k+1]='1';	break;
	case 41:codice[k]='4'; codice[k+1]='1';	break;
	case 42:codice[k]='4'; codice[k+1]='2';	break;
	case 43:codice[k]='4'; codice[k+1]='3';	break;
	case 44:codice[k]='4'; codice[k+1]='4';	break;
	case 45:codice[k]='4'; codice[k+1]='5';	break;
	case 46:codice[k]='4'; codice[k+1]='6';	break;
	case 47:codice[k]='4'; codice[k+1]='7';	break;
	case 48:codice[k]='4'; codice[k+1]='8';	break;
	case 49:codice[k]='4'; codice[k+1]='9';	break;
	case 50:codice[k]='5'; codice[k+1]='0';	break;
	case 51:codice[k]='5'; codice[k+1]='1';	break;
	case 52:codice[k]='5'; codice[k+1]='2';	break;
	case 53:codice[k]='5'; codice[k+1]='3';	break;
	case 54:codice[k]='5'; codice[k+1]='4';	break;
	case 55:codice[k]='5'; codice[k+1]='5';	break;
	case 56:codice[k]='5'; codice[k+1]='6';	break;
	case 57:codice[k]='5'; codice[k+1]='7';	break;
	case 58:codice[k]='5'; codice[k+1]='8';	break;
	case 59:codice[k]='5'; codice[k+1]='9';	break;
	case 60:codice[k]='6'; codice[k+1]='0';	break;
	case 61:codice[k]='6'; codice[k+1]='1';	break;
	case 62:codice[k]='6'; codice[k+1]='2';	break;
	case 63:codice[k]='6'; codice[k+1]='3';	break;
	case 64:codice[k]='6'; codice[k+1]='4';	break;
	case 65:codice[k]='6'; codice[k+1]='5';	break;
	case 66:codice[k]='6'; codice[k+1]='6';	break;
	case 67:codice[k]='6'; codice[k+1]='7';	break;
	case 68:codice[k]='6'; codice[k+1]='8';	break;
	case 69:codice[k]='6'; codice[k+1]='9';	break;
	case 70:codice[k]='7'; codice[k+1]='0';	break;
	case 71:codice[k]='7'; codice[k+1]='1';	break;
    }
    getchar();
    do{
	printf("Comune di nascita(estero=ee):");
	for(i=0;((prov[i]=getchar())!='\n') && i<MAX;i++);
	prov[i]='\0';
	n=i;
    }while(i<1);
    char stato[MAX];
    if((prov[0]=='e' || prov[0]=='E') && (prov[1]=='e' || prov[1]=='E')){
	do{
	    printf("Inserire paese di nascita:");
	    for(i=0;((stato[i]=getchar())!='\n')&& i<MAX;i++)
		;
	    stato[i]='\0';
	    n=i;
	}while(i<1);
    }
    /*Caratteri to UPPERCASE*/

    if((prov[0]=='e' || prov[0]=='E') && (prov[1]=='e' || prov[1]=='E')){
	fp=fopen("estero.txt","r");
        for(i=0;i<n;i++){
	    if(!(stato[i]>='A' && stato[i]<='Z') && (stato[i]>='a' && stato[i]<='z'))
		stato[i]=stato[i]-32;
	}
    }
    else{
            for(i=0;i<n;i++){
	    if(!(prov[i]>='A' && prov[i]<='Z') && (prov[i]>='a' && prov[i]<='z'))
	        prov[i]=prov[i]-32;
	    stato[i]=prov[i];
	    stato[n]='\0';
	}
	switch(prov[0]){
	    case 'A':fp=fopen("a.txt","r");break;
	    case 'B':fp=fopen("b.txt","r");break;
	    case 'C':fp=fopen("c.txt","r");break;
	    case 'D':fp=fopen("d.txt","r");break;
	    case 'E':fp=fopen("e.txt","r");break;
	    case 'F':fp=fopen("f.txt","r");break;	
	    case 'G':fp=fopen("g.txt","r");break;
	    case 'H':fp=fopen("h.txt","r");break;
	    case 'I':fp=fopen("i.txt","r");break;	
	    case 'J':fp=fopen("j.txt","r");break;
	    //case 'K':fp=fopen("k.txt","r");break;
	    case 'L':fp=fopen("l.txt","r");break;	
	    case 'M':fp=fopen("m.txt","r");break;
	    case 'N':fp=fopen("n.txt","r");break;
	    case 'O':fp=fopen("o.txt","r");break;	
	    case 'P':fp=fopen("p.txt","r");break;
	    case 'Q':fp=fopen("q.txt","r");break;
	    case 'R':fp=fopen("r.txt","r");break;	
	    case 'S':fp=fopen("s.txt","r");break;
	    case 'T':fp=fopen("t.txt","r");break;
	    case 'U':fp=fopen("u.txt","r");break;	
	    case 'V':fp=fopen("v.txt","r");break;
	    case 'W':fp=fopen("w.txt","r");break;
	    case 'X':fp=fopen("x.txt","r");break;	
	    case 'Y':fp=fopen("y.txt","r");break;
	    case 'Z':fp=fopen("z.txt","r");break;	
	}
    }
    char buf[MAX],app[MAX];
    int h=0;
    nc=0;
    for(;;){
	if(fgets(buf,100,fp)==NULL)
	    break;
	else
	    nc++;
    }
    fseek(fp,0L,0);
    for(i=1;i<=nc;i++){
	//for(j=0;j<n;j++)
	fgets(app,80,fp);
	//fscanf(fp,"%s",app);
	/*c='\n';
	do{
	    fscanf(fp,"%1s",c);
	    fseek(fp,1,1);
	}while
	    (c!='\n');*/
	//printf("%s",app);
	for(k=0;k<n;k++)
	    buf[k]=app[k];
	buf[n]='\0';
	//printf("%s\n",buf);
	for(k=0;k<n;k++)
	    if(buf[k]==stato[k])
		h++;
	    else
		h=0;
	if(h==n && app[h]==';')
	    break;
    }
    if(h==n && app[h]==';'){
	k=11;
	for(i=0;k<16;k++,i++){
	    codice[k]=app[n+i+1];
	}
	k=16;
	fclose(fp);
	int r[15];
	for(i=1;i<k;i++){
	    if(i%2==0){
		switch(codice[i-1]){
		    case '0':r[i-1]=0; break;
		    case '1':r[i-1]=1; break;
		    case '2':r[i-1]=2; break;
		    case '3':r[i-1]=3; break;		
		    case '4':r[i-1]=4; break;
		    case '5':r[i-1]=5; break;		
		    case '6':r[i-1]=6; break;
		    case '7':r[i-1]=7; break;		
		    case '8':r[i-1]=8; break;
		    case '9':r[i-1]=9; break;		
		    case 'A':r[i-1]=0; break;
		    case 'B':r[i-1]=1; break;		
		    case 'C':r[i-1]=2; break;
		    case 'D':r[i-1]=3; break;		
		    case 'E':r[i-1]=4; break;
		    case 'F':r[i-1]=5; break;		
		    case 'G':r[i-1]=6; break;
		    case 'H':r[i-1]=7; break;
		    case 'I':r[i-1]=8; break;
		    case 'J':r[i-1]=9; break;
		    case 'K':r[i-1]=10; break;
		    case 'L':r[i-1]=11; break;		
		    case 'M':r[i-1]=12; break;
		    case 'N':r[i-1]=13; break;		
		    case 'O':r[i-1]=14; break;
		    case 'P':r[i-1]=15; break;		
		    case 'Q':r[i-1]=16; break;
		    case 'R':r[i-1]=17; break;		
		    case 'S':r[i-1]=18; break;
		    case 'T':r[i-1]=19; break;		
		    case 'U':r[i-1]=20; break;
		    case 'V':r[i-1]=21; break;		
		    case 'W':r[i-1]=22; break;
		    case 'X':r[i-1]=23; break;		
		    case 'Y':r[i-1]=24; break;
		    case 'Z':r[i-1]=25; break;
		}
	    }else{
		switch(codice[i-1]){
		    case '0':r[i-1]=1; break;
		    case '1':r[i-1]=0; break;
		    case '2':r[i-1]=5; break;
		    case '3':r[i-1]=7; break;		
		    case '4':r[i-1]=9; break;
		    case '5':r[i-1]=13; break;		
		    case '6':r[i-1]=15; break;
		    case '7':r[i-1]=17; break;		
		    case '8':r[i-1]=19; break;
		    case '9':r[i-1]=21; break;		
		    case 'A':r[i-1]=1; break;
		    case 'B':r[i-1]=0; break;		
		    case 'C':r[i-1]=5; break;
		    case 'D':r[i-1]=7; break;		
		    case 'E':r[i-1]=9; break;
		    case 'F':r[i-1]=13; break;		
		    case 'G':r[i-1]=15; break;
		    case 'H':r[i-1]=17; break;
		    case 'I':r[i-1]=19; break;
		    case 'J':r[i-1]=21; break;
		    case 'K':r[i-1]=2; break;
		    case 'L':r[i-1]=4; break;		
		    case 'M':r[i-1]=18; break;
		    case 'N':r[i-1]=20; break;		
		    case 'O':r[i-1]=11; break;
		    case 'P':r[i-1]=3; break;		
		    case 'Q':r[i-1]=6; break;
		    case 'R':r[i-1]=8; break;		
		    case 'S':r[i-1]=12; break;
		    case 'T':r[i-1]=14; break;		
		    case 'U':r[i-1]=16; break;
		    case 'V':r[i-1]=10; break;		
		    case 'W':r[i-1]=22; break;
		    case 'X':r[i-1]=25; break;		
		    case 'Y':r[i-1]=24; break;
		    case 'Z':r[i-1]=23; break;
		}
	    }
	}
	int max;
	max=0;
	for(i=0;i<k-1;i++)
	    max+=r[i];
	max=max%26;
	k=15;
	switch(max){
	    case 0:codice[k]='A';	break;
	    case 1:codice[k]='B';	break;
	    case 2:codice[k]='C';	break;
	    case 3:codice[k]='D';	break;
	    case 4:codice[k]='E';	break;
	    case 5:codice[k]='F';	break;
	    case 6:codice[k]='G';	break;
	    case 7:codice[k]='H';	break;
	    case 8:codice[k]='I';	break;
	    case 9:codice[k]='J';	break;
	    case 10:codice[k]='K';	break;
	    case 11:codice[k]='L';	break;
	    case 12:codice[k]='M';	break;
	    case 13:codice[k]='N';	break;
	    case 14:codice[k]='O';	break;	
	    case 15:codice[k]='P';	break;
	    case 16:codice[k]='Q';	break;
	    case 17:codice[k]='R';	break;
	    case 18:codice[k]='S';	break;
	    case 19:codice[k]='T';	break;
	    case 20:codice[k]='U';	break;
	    case 21:codice[k]='V';	break;
	    case 22:codice[k]='W';	break;
	    case 23:codice[k]='X';	break;
	    case 24:codice[k]='Y';	break;
	    case 25:codice[k]='Z';	break;
	}
	FILE* p;
	p=fopen("utentiaccesso.o","a+");
	FILE* f;
	f=fopen("utentiregistrati.o","a+");


	char bufferconfronto[16];
	strncpy(bufferconfronto,codice,16);
	//CONTROLLO GIA EFFETTUATO,CODICE FISCALE ESATTO (CANCELLA POI)
	char bufferfile[16];
	char bufferpas[20];
	//CODICE FISCALE ADATTO PER IL CONFRONTO,INSERISCI CODICE PER VEDERE SE GIA REGISTRATO
	while(fscanf(p,"%s %s",&bufferfile,&bufferpas)!=EOF){
		if(strncmp(bufferfile,bufferconfronto,16)==0){
			printf("Gia' presente,vuoi resettare la password?(inserisci si o no)\t");
			char ans[3];
			gets(ans);
			int lunghezza=0;
			lunghezza=strlen(ans);
			while(isalpha(ans[0])==0 || isalpha(ans[1])==0 || lunghezza!=2){
				printf("Formato errato,inserisci si o no\t");
				gets(ans);
				lunghezza=strlen(ans);
			}
			//controllo formato da definire
			int sd=0;
			for(sd=0;sd<2;sd++){
				ans[sd]=tolower(ans[sd]);
			}
			if(strcmp(ans,"si")==0){
				int rit=cambiopw(codice);
				system("cls");
				printf("Il programma si chiude,riaprilo e accedi con le nuove credenziali!");
			}else
				printf("Arrivederci!");
			
			return 'l';
		}
	}
	
		for(i=0;i<16;i++){
        fprintf(p,"%c",codice[i]);
        fprintf(f,"%c",codice[i]);
	}
	
	char citta[20];
	printf("Inserisci una risposta alla domanda di sicurezza che verra usata per il reset della password.\nNome della tua citta preferita\t");
	gets(citta);
	int lunghezza;
	lunghezza=strlen(citta);
	while(lunghezza>20){
		printf("Fromato inadatto,inserisci un nome di max 20 caratteri");
		gets(citta);
		lunghezza=strlen(citta);
	}
	printf("Ora scegli una password(MAX 20 CHAR)\t");
	char password[20];
	gets(password);
	fprintf(p," %s %s\n",password,citta);
	fclose(p);
	fprintf(f," %s %s %s %s %s %.2f\n",nome,cognome,"Esito","Giorno","evening","0");
	fclose(f);
	system("cls");
    }
    else{
	printf("Paese non presente nel database\n");
	return 'l';
    }
	return 'x';
}
