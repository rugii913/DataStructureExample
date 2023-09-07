#include <stdio.h>
#include <windows.h>
int main() {
    int i, n, arr[20];
    // clrscr(); // #include <conio.h>는 리눅스 용인듯하다.
    system("cls");
    printf("\n Enter the number of elements in the array: ");
    scanf("%d", &n);
    for (i = 0; i < n; i++) {
        printf("\n arr[%d] = ", i);
        scanf("%d", &arr[i]);
    }
    printf("\n The array elements are ");
    for (i = 0; i < n; i++) {
        printf("\t %d", arr[i]);
    }
    return 0;
}