// Author: Mikael Rodriguez

#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdbool.h>
bool isChild(pid_t pid){
    return pid == 0;
}

int main() {
    pid_t pid2, pid3, pid4, pid5;
    int acumulado;

    printf("Soy el proceso P1 (padre principal) PID: %d\n", getpid());
    acumulado = getpid();
    printf("P1: acumulado inicial = %d\n", acumulado);

    pid2 = fork();
    if (isChild(pid2)) {
        printf("Soy el proceso P2 PID: %d\n", getpid());
        if (getpid() % 2 == 0) {
            acumulado += 10;
        } else {
            acumulado -= 100;
        }
        printf("P2: acumulado modificado = %d\n", acumulado);

        pid5 = fork();
        if (isChild(pid5)) {
            printf("Soy el proceso P5 PID: %d\n", getpid());
            if (getpid() % 2 == 0) {
                acumulado += 10;
            } else {
                acumulado -= 100;
            }
            printf("P5: acumulado modificado = %d\n", acumulado);
            exit(0);
        } else {
            wait(NULL);
            exit(0);
        }
    } else {
        pid3 = fork();
        if (isChild(pid3)) {
            printf("Soy el proceso P3 PID: %d\n", getpid());
            if (getpid() % 2 == 0) {
                acumulado += 10;
            } else {
                acumulado -= 100;
            }
            printf("P3: acumulado modificado = %d\n", acumulado);

            pid4 = fork();
            if (isChild(pid4)) {
                // Proceso P4
                printf("Soy el proceso P4 PID: %d\n", getpid());
                if (getpid() % 2 == 0) {
                    acumulado += 10;
                } else {
                    acumulado -= 100;
                }
                printf("P4: acumulado modificado = %d\n", acumulado);
                exit(0);
            } else {
                waitpid(NULL,0,0);
                exit(0);
            }
        } else {
            wait(NULL);
            wait(NULL);
            printf("P1 (PID: %d) ha terminado.\n", getpid());
        }
    }

    return 0;
}
