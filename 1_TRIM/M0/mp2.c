//Author: Mikael Rodriguez

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
int main()
{
    pid_t pid1, pid2;


    pid1 = fork();

    if(pid1 < 0)
    {

    }
    else if (pid1 == 0)
    {
        //PROCESO HIJO
        //PID = 0

        pid2 = fork();
            if(pid2 < 0)
            {

            }
            else if (pid2 == 0)
            {
                //PROCESO NIETO
                //PID = 0
                printf("P3 NIETO DE P1 Y TAMBIEN HIJO DE P2, PID: %d\n",getpid());
            }
            else
            {
                //PROCESO HIJO
                //PID = PID(NIETO)
                printf("P2 HIJO DE P1 Y PADRE DE P3, PID: %d\n",getpid());
                waitpid(NULL,0,0);
            }

    }
    else
    {
        //PROCESO PADRE
        //PID = PID(HIJO)
        printf("P1 PADRE DE P2 Y ABUELO DE P3, PID: %d\n",getpid());
        waitpid(NULL,0,0);
    }
    



    return 0;   
}
