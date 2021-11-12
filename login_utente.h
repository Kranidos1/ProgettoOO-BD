#ifndef login_utente
#define login_utente
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
ut loginutente();
int cambiopw(char codic[17]);

#endif
