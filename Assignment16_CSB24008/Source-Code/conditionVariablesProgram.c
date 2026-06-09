#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>

#define BUFFER_SIZE 5
#define NUM_ITEMS 10


int buffer_count = 0;


pthread_mutex_t mutex;

pthread_cond_t not_full;

pthread_cond_t not_empty;


void* producer(
        void *arg
) {

    for(
        int i = 1;
        i <= NUM_ITEMS;
        i++
    ) {

        pthread_mutex_lock(
                &mutex
        );


        while(
            buffer_count
            ==
            BUFFER_SIZE
        ) {

            printf(
                "Producer waiting "
                "(buffer full)\n"
            );

            pthread_cond_wait(
                    &not_full,
                    &mutex
            );
        }


        buffer_count++;

        printf(
            "Produced item %d "
            "(buffer = %d)\n",
            i,
            buffer_count
        );


        pthread_cond_signal(
                &not_empty
        );


        pthread_mutex_unlock(
                &mutex
        );


        sleep(1);
    }


    return NULL;
}



void* consumer(
        void *arg
) {

    for(
        int i = 1;
        i <= NUM_ITEMS;
        i++
    ) {

        pthread_mutex_lock(
                &mutex
        );



        while(
            buffer_count
            ==
            0
        ) {

            printf(
                "Consumer waiting "
                "(buffer empty)\n"
            );

            pthread_cond_wait(
                    &not_empty,
                    &mutex
            );
        }



        buffer_count--;

        printf(
            "Consumed item %d "
            "(buffer = %d)\n",
            i,
            buffer_count
        );



        pthread_cond_signal(
                &not_full
        );



        pthread_mutex_unlock(
                &mutex
        );


        sleep(2);
    }


    return NULL;
}



int main() {

    pthread_t producer_thread;

    pthread_t consumer_thread;


    pthread_mutex_init(
            &mutex,
            NULL
    );

    pthread_cond_init(
            &not_full,
            NULL
    );

    pthread_cond_init(
            &not_empty,
            NULL
    );


    pthread_create(
            &producer_thread,
            NULL,
            producer,
            NULL
    );

    pthread_create(
            &consumer_thread,
            NULL,
            consumer,
            NULL
    );


    pthread_join(
            producer_thread,
            NULL
    );

    pthread_join(
            consumer_thread,
            NULL
    );


    pthread_mutex_destroy(
            &mutex
    );

    pthread_cond_destroy(
            &not_full
    );

    pthread_cond_destroy(
            &not_empty
    );

    return 0;
}