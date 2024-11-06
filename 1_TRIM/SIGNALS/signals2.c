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

//SIGNAL MANAGER
int sec = 0;
void signal_action(int signum)
{
    switch (signum)
    {
    case SIGALRM:
        sec += 5;
        printf("Han pasado %d segundos\n",sec);
        alarm(5);
        break;
    default:
        break;
    }
}
//END SIGNAL MANAGER
int main(int argc, char*argv[])
{
    signal(SIGALRM, signal_action);
    alarm(5);
    while (1) {
        pause();
    }
    return 0;
}