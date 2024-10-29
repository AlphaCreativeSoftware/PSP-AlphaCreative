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
    int fd1, fd2;

    fd1 = open("FIFO1",O_RDONLY);
    char buffer[10];
    read(fd1,&buffer,sizeof(buffer));
    int number = atoi(buffer);
    close(fd1);

    int result = factorial(number); //Calcular el factorial del número leido del FIFO1
    printf("Resultado de factorial: %d",result);
    fd2 = open("FIFO2",O_WRONLY);
    char buffer2[10];
    snprintf(buffer2,sizeof(buffer2),"%d",result);
    write(fd2,buffer2, sizeof(buffer2));
    close(fd2);

    return 0;
}