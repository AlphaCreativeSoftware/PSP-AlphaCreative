#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h> 
#include <string.h>
#include <ctype.h>
#include <stdbool.h>

int main(int argc, char* argv[])
{
    pid_t pid;
    int fd[2];
    //fd(0) - read
    //fd(1) - write
    if(pipe(fd) == -1)
    {
        printf("Error opening pipe");
        return 1;
    }
    pid=fork();
    switch (pid)
    {
    case -1: //ERROR


    break;

    case 0: //CHILD
        close(fd[0]); //CERRAMOS EL LECTOR EN EL HIJO

        int x = 0;
        char word[40] = "";
        while (true)
        {
            printf("Input a number: ");
            scanf("%s", &word);
            if (strcmp(word, "+") == 0)
            {
                break;
            }
            int number = atoi(word);
            x += number;
        }
        write(fd[1], &x, sizeof(int));
        close(fd[1]);
    break;

    default: //PARENT
        wait(NULL);
        close(fd[1]); //CERRAMOS EL ESCRITOR EN EL PADRE
        int number = 0;
        read(fd[0], &number, sizeof(int)); //LEEMOS LA TUBERIA
        close(fd[0]);
        printf("Obtenido del proceso hijo: %d\n",number);
        return 1;
    break;
    }

}