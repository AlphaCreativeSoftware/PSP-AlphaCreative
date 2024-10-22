#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h> 
#include <string.h>
#include <ctype.h>

void AnimationMenu()
{
    int frecuency = 70000;

    printf("     ------     \n");
    printf("       AT       \n");
    printf("     ------     \n");
    usleep(frecuency);
    clear();

    printf("    --------    \n");
    printf("      MATO      \n");
    printf("    --------    \n");
    usleep(frecuency);
    clear();

    printf("   ----------   \n");
    printf("     UMATOR     \n");
    printf("   ----------   \n");
    usleep(frecuency);
    clear();

    printf("  ------------  \n");
    printf("    SUMATORY    \n");
    printf("  ------------  \n");
    usleep(frecuency);
    clear();

    printf(" -------------- \n");
    printf("  --SUMATORY--  \n");
    printf(" -------------- \n");
    usleep(frecuency);
    clear();

    printf("----------------\n");
    printf("----SUMATORY----\n");
    printf("----------------\n");
    usleep(frecuency*6);
    clear();

    printf(" -------------- \n");
    printf("  --SUMATORY--  \n");
    printf(" -------------- \n");
    usleep(frecuency);
    clear();

    printf("  ------------  \n");
    printf("    SUMATORY    \n");
    printf("  ------------  \n");
    usleep(frecuency);
    clear();

    printf("   ----------   \n");
    printf("     UMATOR     \n");
    printf("   ----------   \n");
    usleep(frecuency);
    clear();

    printf("    --------    \n");
    printf("      MATO      \n");
    printf("    --------    \n");
    usleep(frecuency);
    clear();

    printf("     ------     \n");
    printf("       AT       \n");
    printf("     ------     \n");
    usleep(frecuency);
    clear();

    printf("      ----      \n");
    printf("                \n");
    printf("      ----      \n");
    usleep(frecuency);
    clear();

    printf("       --       \n");
    printf("                \n");
    printf("       --       \n");
    usleep(frecuency);
    clear();

    printf("                \n");
    printf("                \n");
    printf("                \n");
    usleep(frecuency);
    clear();

}

void clear()
{
    system("clear");
}

int main()
{
    AnimationMenu();
    int fd[2];           // Pipe file descriptors (fd[0] para leer, fd[1] para escribir)
    char buffer[100];    // Buffer para recibir los datos en el proceso hijo
    pid_t pid;

    if (pipe(fd) == -1) {   // Verificar que se pudo crear el pipe
        perror("Error al crear el pipe");
        exit(EXIT_FAILURE);
    }

    pid = fork();  // Crear el proceso hijo

    if (pid < 0) {
        perror("Error al crear el proceso hijo");
        exit(EXIT_FAILURE);
    }

    if (pid == 0) {
        // PROCESO HIJO
        close(fd[1]);  // Cerrar el extremo de escritura del pipe en el proceso hijo
        printf("Soy el hijo PID: %d\n", getpid());

        int suma = 0;

        while (1) {
            read(fd[0], buffer, sizeof(buffer));  // Leer desde el pipe

            if (strcmp(buffer, "+") == 0) {  // Si recibe el carácter '+', termina
                break;
            }

            int numero = atoi(buffer);  // Convertir la cadena leída a número entero
            printf("Número recibido: %d\n", numero);
            suma += numero;  // Sumar el número recibido
        }

        printf("Suma total: %d\n", suma);  // Mostrar la suma total
        close(fd[0]);  // Cerrar el extremo de lectura del pipe
        exit(0);
    } else {
        // PROCESO PADRE
        close(fd[0]);  // Cerrar el extremo de lectura del pipe en el proceso padre
        printf("Soy el padre PID: %d\n", getpid());

        char input[100];
        while (1) {
            printf("Introduce un número (o '+' para finalizar): ");
            fgets(input, 100, stdin);  // Leer la entrada del usuario

            // Eliminar el salto de línea al final de la entrada
            input[strcspn(input, "\n")] = 0;

            // Enviar la cadena al proceso hijo
            write(fd[1], input, strlen(input) + 1);

            if (strcmp(input, "+") == 0) {  // Si el usuario introduce '+', termina
                break;
            }
        }

        close(fd[1]);  // Cerrar el extremo de escritura del pipe
        wait(NULL);    // Esperar a que el proceso hijo termine
    }

    return 0;
}

