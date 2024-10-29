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

int factorial(int n) {
    if (n == 0) return 1;
    return n * factorial(n - 1);
}

int main(int argc, char * argv[])
{
    //READ
    int pipe = open("PIPE02", O_RDONLY);
    char buffer[10];
    read(pipe,&buffer,sizeof(buffer));
    int number = atoi(buffer);
    printf("Readed %d from PIPE02...\n",number);
    close(pipe);

    //WRITE
    int factorialNumber = factorial(number);
    char factorialNUmberSTR[10];
    sprintf(factorialNUmberSTR,"%d",factorialNumber);

    int pipeWR = open("PIPE02", O_WRONLY);
    write(pipeWR,factorialNUmberSTR,sizeof(factorialNUmberSTR));
    printf("Writted factorial %s in PIPE02...\n",factorialNUmberSTR);
    close(pipeWR);

    return 0;
}