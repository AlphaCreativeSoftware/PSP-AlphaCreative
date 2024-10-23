#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h> 
#include <string.h>
#include <ctype.h>
#include <stdbool.h>
#include <time.h>

int factorial(int n) {
    if (n == 0 || n == 1) {
        return 1;
    }
    return n * factorial(n - 1);
}

bool isChild(pid_t pid){
    return pid == 0;
}
bool isParent(pid_t pid){
    return pid > 0;
}

int wFD(int fd[2])
{
    return fd[1];
}
int rFD(int fd[2])
{
    return fd[0];
}
int main(int argc, char* argv[])
{

    pid_t pid;

    //fd(0) - read
    //fd(1) - write
    int fd1[2]; // (pipe1)
    int fd2[2]; // (pipe2)


    if(pipe(fd1) == -1 || pipe(fd2) == -1)
    {
        perror("pipe");
        return 1;
    }

    pid = fork();

    if(isParent(pid)) //PARENT
    {
        printf("Soy el padre\n");
        close(fd1[0]); //CERRAMOS LECTURA FD1
        close(fd2[1]); //CERRAMOS ESCRITURA FD2
        srand(time(NULL));

        int randNum = rand()  % 11;
        write(fd1[1], &randNum, sizeof(int));
        printf("(PADRE) El proceso padre genera el numero %d en el (pipe 1)\n",randNum);
        close(fd1[1]);

        int obtainedNumber;
        read(fd2[0], &obtainedNumber, sizeof(int));
        printf("(PADRE) El factorial calculado por el proceso hijo: %d != %d\n",randNum,obtainedNumber);
        close(fd2[0]);
    }
    else if(isChild(pid)) //CHILD
    {
        printf("Soy el hijo\n");
        close(fd1[1]); //CERRAMOS ESCRITURA FD1
        close(fd2[0]); //CERRAMOS LECTURA FD2

        int obtainedNumber;
        read(fd1[0], &obtainedNumber, sizeof(int));
        printf("(HIJO) Numero obtenido: %d\n",obtainedNumber);

        int factorialNumber = factorial(obtainedNumber);
        write(fd2[1], &factorialNumber, sizeof(int));
        close(fd2[1]);
        close(fd1[0]);
    }

    return 0;
}