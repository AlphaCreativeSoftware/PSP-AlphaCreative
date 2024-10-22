//Author: Mikael Rodriguez

#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdbool.h>
bool isChild(pid_t pid){

    if (pid == 0)
    {
        return true;
    }
    else
    {
        return false;
    }
}

int main()
{
    
    pid_t pid1, pid2, pid3;

    pid_t pid2_1, pid2_2;

    pid1 = fork(); //SUBDIVIDIMOS PROCESOS


    if (isChild(pid1))
    {
        printf("Soy el primer hijo PID: %d\n",getpid());

        pid2 = fork();

        if(isChild(pid2))
        {
            printf("Soy el segundo hijo PID: %d\n",getpid());

            pid3 = fork();
            if(isChild(pid3))
            {
                printf("Soy el tercer hijo PID: %d\n",getpid());
            }
            else
            {
                waitpid(NULL,0,0);
            }

        }
        else
        {
            waitpid(NULL,0,0);
            printf("Soy el primer hijo PID: %d, y voy a crear más\n",getpid());

            pid2_1 = fork();

            if(isChild(pid2_1))
            {
                printf("Soy el primer hijo del primero PID: %ds\n",getpid());

                pid2_2 = fork();

                if(isChild(pid2_2))
                {
                    printf("Soy el segundo hijo del primero PID: %ds\n",getpid());
                }
                else
                {
                    waitpid(NULL,0,0);
                }
            }
            else
            {
                waitpid(NULL,0,0);
            }


        }
    }
    else
    {
        waitpid(NULL,0,0);
        printf("Soy el padre principal PID: %d\n",getpid());
    }
    return 0;
}

