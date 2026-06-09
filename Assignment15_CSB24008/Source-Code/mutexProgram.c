#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define NUM_THREADS 4
#define NUM_INCREMENTS 100000


int counter = 0;


pthread_mutex_t mutex;


void* increment_without_mutex(
        void *arg
) {

    for(int i = 0;
        i < NUM_INCREMENTS;
        i++) {

        counter++;
    }

    return NULL;
}


void* increment_with_mutex(
        void *arg
) {

    for(int i = 0;
        i < NUM_INCREMENTS;
        i++) {

        pthread_mutex_lock(
                &mutex
        );

        counter++;

        pthread_mutex_unlock(
                &mutex
        );
    }

    return NULL;
}


int run_threads(
        void* (*worker)(void*)
) {

    pthread_t threads[
            NUM_THREADS
    ];


    for(int i = 0;
        i < NUM_THREADS;
        i++) {

        if(
            pthread_create(
                &threads[i],
                NULL,
                worker,
                NULL
            ) != 0
        ) {

            printf(
                "Thread creation failed\n"
            );


            for(int j = 0;
                j < i;
                j++) {

                pthread_join(
                    threads[j],
                    NULL
                );
            }

            return 0;
        }
    }



    for(int i = 0;
        i < NUM_THREADS;
        i++) {

        if(
            pthread_join(
                threads[i],
                NULL
            ) != 0
        ) {

            printf(
                "Thread join failed\n"
            );

            return 0;
        }
    }


    return 1;
}



int main() {

    int expected =
        NUM_THREADS *
        NUM_INCREMENTS;



    printf(
        "Expected Counter = %d\n\n",
        expected
    );


    counter = 0;


    printf(
        "Running WITHOUT mutex...\n"
    );


    if(
        !run_threads(
            increment_without_mutex
        )
    ) {

        return 1;
    }


    printf(
        "Counter = %d\n\n",
        counter
    );


    counter = 0;


    if(
        pthread_mutex_init(
            &mutex,
            NULL
        ) != 0
    ) {

        printf(
            "Mutex initialization failed\n"
        );

        return 1;
    }


    printf(
        "Running WITH mutex...\n"
    );


    if(
        !run_threads(
            increment_with_mutex
        )
    ) {

        pthread_mutex_destroy(
                &mutex
        );

        return 1;
    }



    printf(
        "Counter = %d\n",
        counter
    );



    if(
        pthread_mutex_destroy(
            &mutex
        ) != 0
    ) {

        printf(
            "Mutex destruction failed\n"
        );

        return 1;
    }

    return 0;
}