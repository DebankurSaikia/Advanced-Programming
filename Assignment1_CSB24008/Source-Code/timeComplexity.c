#include <stdio.h>
#include <time.h>
#include <stdlib.h>

int constantTime(int x)//O(1)
{
    return x * x;
}

int linearSearch(int arr[], int n, int element)//O(n)
{
    for (int i = 0; i < n; i++)
    {
        if (arr[i] == element)
            return i;
    }
    return -1;
}

int binarySearch(int arr[], int n, int element)//O(logn)
{
    int low = 0, high = n - 1;

    while (low <= high)
    {
        int mid = low + (high - low) / 2;

        if (arr[mid] == element)
            return mid;
        else if (arr[mid] > element)
            high = mid - 1;
        else
            low = mid + 1;
    }
    return -1;
}

void bubbleSort(int arr[], int n)//O(n^2)
{
    int temp;
    for (int i = 0; i < n - 1; i++)
    {
        for (int j = 0; j < n - i - 1; j++)
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

void printSpaceComplexity(int n, int arr[])
{
    printf("Space Complexity Analysis:\n");
    printf("Array Space (O(n)): %lu bytes\n", sizeof(int) * n);

    printf("Auxiliary Space Used:\n");
    printf("Constant Time Function: O(1) -> %lu bytes\n", sizeof(int));
    printf("Linear Search: O(1) -> %lu bytes\n", sizeof(int));
    printf("Binary Search: O(1) -> %lu bytes\n", sizeof(int) * 3);
    printf("Bubble Sort: O(1) -> %lu bytes\n", sizeof(int));
}


int main()
{
    int sizes[] = {10000, 50000, 100000};
    int element = -1;

    for (int s = 0; s < 3; s++)
    {
        int n = sizes[s];
        int arr[n];

        
        for (int i = 0; i < n; i++)
            arr[i] = rand() % n;

        printf("\nInput Size: %d\n", n);
        printf("Time Complexity Analysis:\n");


        clock_t start, end;
        double time_taken;


        start = clock();
        constantTime(10);
        end = clock();
        time_taken = ((double)(end - start)) / CLOCKS_PER_SEC;
        printf("Constant Time (O(1)) Function Time: %f seconds\n", time_taken);

        
        start = clock();
        linearSearch(arr, n, element);
        end = clock();
        time_taken = ((double)(end - start)) / CLOCKS_PER_SEC;
        printf("Linear Search Time (O(n)): %f seconds\n", time_taken);

        
        start = clock();
        bubbleSort(arr, n);
        end = clock();
        time_taken = ((double)(end - start)) / CLOCKS_PER_SEC;
        printf("Bubble Sort Time (O(n^2)): %f seconds\n", time_taken);

        
        start = clock();
        binarySearch(arr, n, element);
        end = clock();
        time_taken = ((double)(end - start)) / CLOCKS_PER_SEC;
        printf("Binary Search Time (O(logn)): %f seconds\n", time_taken);


        printSpaceComplexity(n, arr);
    }

    return 0;
}