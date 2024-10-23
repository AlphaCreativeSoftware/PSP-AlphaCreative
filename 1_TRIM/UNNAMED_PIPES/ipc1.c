#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h> 
#include <time.h>

void main(){

     int fd[2]; 
     char buffer[80];
     pid_t pid;
    
     // Creamos el pipe
     pipe(fd); 
     
     //Se crea un proceso hijo
     pid = fork();

     if (pid==0)
     
     {
                close(fd[1]); // Cierra el descriptor de escritura
                printf("El hijo se dispone a leee del PIPE \n\n");
                read(fd[0], buffer, 80);
                printf("Mensaje leido del pipe:\n%s \n", buffer);
     
     }
     
     else
     
     {
                close(fd[0]); // Cierra el descriptor de lectura
                printf("El padre escribe en el PIPE...\n");
                
                time_t t = time(NULL);
                struct tm *tm_info = localtime(&t);

                char buffer[80];
                strftime(buffer, 80, "Hoy es %Y-%m-%d y la hora es %H:%M:%S", tm_info);
                write(fd[1], buffer, 80);
                wait(NULL);
     }
     
        
}