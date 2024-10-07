//Author: Mikael Rodriguez

#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>

int main() {
    pid_t pid1, pid2;
    pid1 = fork();

    if (pid1 < 0) {
        perror("Error al crear el primer hijo");
        exit(0);
    }

    if (pid1 == 0) {
        printf("Soy el primer hijo. PID: %d, PPID (Padre): %d\n", getpid(), getppid());
        printf("El primer hijo %d va a dormir\n", getpid());
        sleep(3);
        printf("El primer hijo %d se despertó\n", getpid());
        exit(0);
    } else {
        pid2 = fork();

        if (pid2 < 0) {
            perror("Error al crear el segundo hijo");
            exit(0);
        }

        if (pid2 == 0) {
            printf("Soy el segundo hijo. PID: %d, PPID (Padre): %d\n", getpid(), getppid());
            exit(0);
        } else {
            //El proceso padre espera a que los dos hijos terminen
            waitpid(NULL,0,0);  //Espera al primer hijo
            waitpid(NULL,0,0);  //Espera al segundo hijo

            printf("El padre ha terminado después de esperar a sus hijos.\n");
        }
    }

    return 0;
}