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


int main(int argc, char * argv[])
{
    if(mkfifo("PIPE02",0777) == -1) //CREAMOS EL FICHERO FIFO1
    {
        if(errno !=  EEXIST)
        {
            printf("No se pudo crear el fichero (PIPE02)\n");
            return 1;
        }
    }
    //WRITE
    srand(time(NULL));
    int randNum = rand() % 11;
    int pipe = open("PIPE02", O_WRONLY);
    char randNumSTR[10];
    sprintf(randNumSTR,"%d",randNum); //CONVERTIMOS EL ENTERO A CADENA DE CARACTERES
    write(pipe,&randNumSTR,sizeof(randNumSTR));
    printf("Writted %s in PIPE02...\n",randNumSTR);
    close(pipe);

    //READ
    int pipeRD = open("PIPE02", O_RDONLY);
    char factNumberSTR[10];
    read(pipeRD,&factNumberSTR,sizeof(factNumberSTR));
    printf("Read factorial %s from PIPE02...\n",factNumberSTR);
    close(pipeRD);

        
    unlink("PIPE02");
    return 0;
}