#include <signal.h>
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

//fd(0) - read
//fd(1) - write
int fd[2]; // (pipe)
bool isChild(pid_t pid){
    return pid == 0;
}
bool isParent(pid_t pid){
    return pid > 0;
}

void action_p3(int signum)
{
    if(signum == SIGUSR1)
    {
        int num;
        read(fd[0], &num,sizeof(int));
        printf("Número IMPAR leido por P3 : %d\n", num);
    }
}
void action_p2(int signum)
{
    if(signum == SIGUSR1)
    {
        int num;
        read(fd[0], &num,sizeof(int));
        printf("Número PAR leido por P2 : %d\n", num);
    }
}
int main(int argc, char * argv[])
{

    if(pipe(fd) == -1)
    {
        printf("ERROR ON MAKE PIPE\n");
        return 1;
    }
    pid_t pid1, pid2;

    pid1 =  fork();
    if (isChild(pid1)) //FIRST CHILD PROCESS (P2)
    {
        signal(SIGUSR1,action_p2);
        while (1)
        {
            pause();
        }
        
    }
    else if (isParent(pid1))
    {
        pid2 = fork();
        if (isChild(pid2)) //SECOND CHILD PROCESS (P3)
        {
            signal(SIGUSR1,action_p3);
            while (1)
            {
                pause();
            }
        }
        else if(isParent(pid2))
        {
            int number = -1;
            do
            {
                printf("Escribe un numero: ");
                scanf("%d",&number);

                if (number == 0) {
                pid_t pid_p2 = pid1;
                pid_t pid_p3 = pid2;
                pid_t pid_p1 = getpid();
                printf("Mandando señal de terminación al proceso hijo P2 con PID %d\n", pid1);
                kill(pid1,SIGKILL);
                printf("Mandando señal de terminación al proceso hijo P3 con PID %d\n", pid2);
                kill(pid2,SIGKILL);
                }
                else
                {
                    if(number %2 == 0)
                    {
                        write(fd[1],&number,sizeof(int));
                        kill(pid1,SIGUSR1);
                    }
                    else
                    {
                        write(fd[1],&number,sizeof(int));
                        kill(pid2,SIGUSR1);
                    }
                    usleep(10000);

                }
            }
            while (number != 0);
            printf("Esperando a que los procesos hijos mueran...\n");
            wait(NULL);
            wait(NULL);
            printf("Fin proceso padre PID: %d\n",getpid());

        }









        else
        {
            printf("Error in second fork()\n");
            return 1;
        }

    }
    else
    {
        printf("Error in first fork()\n");
        return 1;
    }


    return 0;
}