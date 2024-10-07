Mikael Rodríguez López
2ºDAM
Programación de Servicios y Procesos
## MP04
![Esquema](https://raw.githubusercontent.com/AlphaCreativeSoftware/PSP-AlphaCreative/10014467b3b6ce1405815393deb6a4816d26649e/1_TRIM/M0/MP4.png)
**Código modificado:**
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>

void main()
{
 printf("CCC \n");
 if (fork()!=0)
 {
    waitpid(NULL,0,0);
    printf("AAA \n");
 }
 else
 {
    printf("BBB \n");
 }
 exit(0);
}

**Devuelve:**
CCC
BBB
AAA

