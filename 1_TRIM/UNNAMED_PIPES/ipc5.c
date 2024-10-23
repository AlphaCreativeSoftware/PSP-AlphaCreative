#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h> 
#include <string.h>
#include <ctype.h>
#include <stdbool.h>
#include <time.h>

bool isChild(pid_t pid){
    return pid == 0;
}
bool isParent(pid_t pid){
    return pid > 0;
}

int main(int argc, char * argv[])
{

    pid_t pid;

    //pipe(0) - read
    //pipe(1) - write
    int pipe1[2]; // (pipe1)
    int pipe2[2]; // (pipe2)


    if(pipe(pipe1) == -1 || pipe(pipe2) == -1)
    {
        perror("pipe");
        return 1;
    }

    pid = fork();

    if(isChild(pid))
    {
        close(pipe2[1]);
        close(pipe1[0]);
        int dni;
        printf("Escribe tu DNI sin letra ni espacios: ");
        scanf("%d", &dni);
        write(pipe1[1],&dni,sizeof(int));
        close(pipe1[1]);

        char letraDNI;
        read(pipe2[0], &letraDNI, sizeof(char));
        close(pipe2[0]);

        printf("(HIJO) %d%c\n",dni,letraDNI);
    }
    else if(isParent(pid))
    {
        close(pipe2[0]);
        close(pipe1[1]);

        int dni;
        read(pipe1[0],&dni,sizeof(int));
        close(pipe1[0]);

        char letras[] = "TRWAGMYFPDXBNJZSQVHLCKE";
        int index = dni % 23;
        char letraDNI = letras[index];
        write(pipe2[1], &letraDNI,  sizeof(char));
    }

    return 0;
}