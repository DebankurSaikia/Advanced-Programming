#include <stdio.h>
#include <stdlib.h>

int constantTime(int x, size_t *auxMem)
{
    *auxMem = sizeof(int);
    return x * x;
}

int linearSearch(int arr[], int n, int element, size_t *auxMem)
{
    int i;
    *auxMem = sizeof(int);

    for (i = 0; i < n; i++)
    {
        if (arr[i] == element)
            return i;
    }
    return -1;
}

int binarySearch(int arr[], int n, int element, size_t *auxMem)
{
    int low = 0, high = n - 1, mid;
    *auxMem = sizeof(int) * 3;

    while (low <= high)
    {
        mid = low + (high - low) / 2;

        if (arr[mid] == element)
            return mid;
        else if (arr[mid] > element)
            high = mid - 1;
        else
            low = mid + 1;
    }
    return -1;
}

void bubbleSort(int arr[], int n, size_t *auxMem)
{
    int i, j, temp;
    *auxMem = sizeof(int) * 3;

    for (i = 0; i < n - 1; i++)
    {
        for (j = 0; j < n - i - 1; j++)
        {
            if (arr[j] > arr[j + 1])
            {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}

int main()
{
    int sizes[] = {10000, 50000, 100000};
    size_t auxMem;

    for (int s = 0; s < 3; s++)
    {
        int n = sizes[s];

        int *arr = (int *)malloc(n * sizeof(int));

        
        for (int i = 0; i < n; i++)
            arr[i] = i;

        printf("\nInput Size: %d\n", n);

        constantTime(5, &auxMem);
        printf("Constant Time Auxiliary Space: %zu bytes (O(1))\n", auxMem);

        linearSearch(arr, n, -1, &auxMem);
        printf("Linear Search Auxiliary Space: %zu bytes (O(1))\n", auxMem);

        binarySearch(arr, n, -1, &auxMem);
        printf("Binary Search Auxiliary Space: %zu bytes (O(1))\n", auxMem);

        bubbleSort(arr, n, &auxMem);
        printf("Bubble Sort Auxiliary Space: %zu bytes (O(1))\n", auxMem);

        free(arr);
    }

    return 0;
}
