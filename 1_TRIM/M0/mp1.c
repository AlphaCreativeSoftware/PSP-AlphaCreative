#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>


int main()
{

    pid_t pid1;

    pid1 = fork();

    if(pid1 < 0)
    {
        printf("Error interno en la creación del proceso");
    }
    else if(pid1 == 0)
    {
        printf("Soy el hijo y mi PID: %d\n",getpid());
        sleep(1);
    }
    else
    {
        printf("Soy el padre y mi PID: %d\n",getpid());
        waitpid(pid1,0,0);
        printf("Mi hijo ha terminado \nPID(HIJO): %d\nPPID(PADRE): %d\n",pid1,getpid());

    }
    return 0;
}