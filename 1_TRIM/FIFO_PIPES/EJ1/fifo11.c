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

bool isChild(pid_t pid){
    return pid == 0;
}
bool isParent(pid_t pid){
    return pid > 0;
}

int main(int argc, char * argv[])
{
    if(mkfifo("FIFO1",0777) == -1)
    {
        if(errno !=  EEXIST)
        {
            printf("No se pudo crear el fichero (FIFO1)\n");
            return 1;
        }
    }
    if(mkfifo("FIFO2",0777) == -1)
    {
        if(errno != EEXIST)
        {
            printf("No se pudo crear el fichero (FIFO2)\n");
            return 1;
        }
    }

    //FIFO1
    printf("Opening. FIFO1..\n");
    int fd1 = open("FIFO1",O_WRONLY);
    printf("Opened FIFO1\n");

    srand(time(NULL));
    int randNumber = rand() %11;
    char strandNumber[10];
    sprintf(strandNumber, "%d", randNumber);
    printf("Número a calcular para factorial: %s\n",strandNumber);
    if(write(fd1,  &strandNumber, sizeof(strandNumber)) == -1)
    {
        return 2;
    }
    printf("Written FIFO1...\n");
    close(fd1);
    printf("Closed FIFO1...\n");

    //FIFO2
    printf("Opening. FIFO2..\n");
    int fd2 = open("FIFO2",O_RDONLY);
    printf("Opened FIFO2\n");

    char recibedBuffer[10];
    read(fd2,&recibedBuffer, sizeof(recibedBuffer));
    int factorial = atoi(recibedBuffer);
    printf("Recibido FACTORIAL: %d\n",factorial);
    close(fd2);

    unlink("FIFO1");
    unlink("FIFO2");
    return 0;
}