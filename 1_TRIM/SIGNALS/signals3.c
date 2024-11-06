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
void signal_action(int signum) {
    switch (signum) {
        case SIGINT: {
            // Usar un bloque para declarar variables
            FILE *fichero = fopen("salidas.txt", "a");
            if (fichero == NULL) {
                perror("Error al abrir el archivo");
                exit(EXIT_FAILURE);
            }
            printf("Señal SIGINT recibida a las %s\n", GetDate());
            fprintf(fichero, "Señal SIGINT recibida a las %s\n", GetDate());
            fclose(fichero);
            break;
        }
        default:
            break;
    }
}

int main(int argc, char * argv[])
{
    signal(SIGINT, signal_action);
    printf("Presiona Ctrl+C para intentar detener el programa...\n");
    while (1)
    {
        pause();
    }
    return 0;
}