#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>

int counter = 0; // Contador de alarmas sonadas
int max_count; // Número máximo de veces que debe sonar la alarma

// Función para obtener la hora actual como una cadena
char *GetDate() {
    time_t hora;
    time(&hora);
    char *cadenaFecha = ctime(&hora);
    cadenaFecha[strlen(cadenaFecha) - 1] = '\0'; // Elimina el salto de línea
    return cadenaFecha;
}
void alarm_handler(int signum) {
    if (counter < max_count) {
        printf("Señal de alarma recibida a las %s\n", GetDate());
        counter++;
        alarm(2);
    } else {
        printf("Alarma desactivada\n");
        exit(0);
    }
}

int main() {
    int interval;

    printf("¿Cuántas veces sonará la alarma?: ");
    scanf("%d", &max_count);

    printf("¿Cada cuántos segundos se repetirá la alarma?: ");
    scanf("%d", &interval);

    signal(SIGALRM, alarm_handler);

    printf("Alarma activada\n");
    alarm(interval);
    while (1) {
        pause();
    }
    return 0;
}
