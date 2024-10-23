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

        int numbers[2];
        int count = 0;
        while (true)
        {
            if(count < 2)
            {
                int number;
                printf("Input a number: ");
                scanf("%d", &number);
                numbers[count] = number;
                count ++;
            }
            else
            {
                break;
            }

        }
        write(fd[1], &numbers, count * sizeof(int));
        close(fd[1]);
    break;

    default: //PARENT
        wait(NULL);
        close(fd[1]); //CERRAMOS EL ESCRITOR EN EL PADRE
        int numbers_obt[2];
        read(fd[0], &numbers_obt, 2 * sizeof(int)); //LEEMOS LA TUBERIA
        close(fd[0]);
        int num1 = numbers_obt[0];
        int num2 = numbers_obt[1];
        printf("Suma de %d + %d = %d\n",num1,num2, num1+num2);
        printf("Resta de %d - %d = %d\n",num1,num2, num1-num2);
        printf("Multiplicacion de %d * %d = %d\n",num1,num2,num1*num2);
        printf("Division de %d / %d = %f\n",num1,num2,(float)num1/(float)num2);
        return 0;
    break;
    }

}