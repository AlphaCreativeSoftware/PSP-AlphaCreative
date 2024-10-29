#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h> 
#include <string.h>
#include <ctype.h>
#include <stdbool.h>
#include <time.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <errno.h>
#include <fcntl.h>
//FUNCTIONS
void clear() {system("clear");}
char *GetDate() {
    time_t hora;
    time(&hora);
    char *cadenaFecha = ctime(&hora);

    char *fecha = malloc(26);
    if (fecha != NULL) {
        snprintf(fecha, 26, "%s", cadenaFecha);
    }
    return fecha;
}
//END FUNCTIONS

//SIGNAL MANAGER
void signal_action(int signum)
{
    switch (signum)
    {
    case SIGINT:
        printf("La fecha de fin de %d es: %s\n\n",getpid(),GetDate());
        exit(0);
        break;
    case SIGTSTP:
        system('fg %1'); //IMPOSIBILITAMOS EL BACKGROUNDING
        break;
    default:
        break;
    }
}
//END SIGNAL MANAGER

int main(int argc, char*argv[])
{
    time_t hora;
    char *fecha ;
    time(&hora);
    fecha = ctime(&hora);

    printf("La fecha de inicio de %d es: %s\n",getpid(),GetDate());
    printf("Press Ctrl+C to Exit...\n");

    signal(SIGINT, signal_action);
    signal(SIGTSTP,signal_action);

    while (1)
    {
        sleep(1);
    }
    

    return 0;
}