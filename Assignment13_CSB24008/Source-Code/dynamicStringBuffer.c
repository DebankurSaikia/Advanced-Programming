#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stddef.h>
#include <stdint.h>

typedef struct {
    char *data;
    size_t length;
    size_t capacity;
} StringBuffer;


StringBuffer* sb_init(size_t initial_capacity) {

    if(initial_capacity == 0) {
        initial_capacity = 1;
    }

    StringBuffer *sb =
            (StringBuffer*)malloc(sizeof(StringBuffer));

    if(sb == NULL) {
        return NULL;
    }

    sb->data =
            (char*)malloc(initial_capacity);

    if(sb->data == NULL) {
        free(sb);
        return NULL;
    }

    sb->length = 0;
    sb->capacity = initial_capacity;

    sb->data[0] = '\0';

    return sb;
}



int sb_append(
        StringBuffer *sb,
        const char *str
) {

    if(sb == NULL || str == NULL) {
        return 0;
    }

    size_t str_len = strlen(str);

    size_t required =
            sb->length + str_len + 1;


   
    while(required > sb->capacity) {

        if(sb->capacity > ((size_t)-1) / 2) {
            return 0;
        }

        size_t new_capacity =
                sb->capacity * 2;


        char *temp =
                (char*)realloc(
                        sb->data,
                        new_capacity
                );

        if(temp == NULL) {
            return 0;
        }

        sb->data = temp;
        sb->capacity = new_capacity;

        printf(
            "Buffer grew to %zu bytes\n",
            sb->capacity
        );
    }


    strcpy(
            sb->data + sb->length,
            str
    );

    sb->length += str_len;

    return 1;
}



void sb_free(StringBuffer *sb) {

    if(sb == NULL) {
        return;
    }

    free(sb->data);
    free(sb);
}




int main() {

    StringBuffer *sb =
            sb_init(8);

    if(sb == NULL) {
        printf(
            "Memory allocation failed\n"
        );
        return 1;
    }


    if(!sb_append(sb, "Hello")) {
        printf("Append failed\n");
        sb_free(sb);
        return 1;
    }

    printf("%s\n", sb->data);


    if(!sb_append(sb, " World!")) {
        printf("Append failed\n");
        sb_free(sb);
        return 1;
    }

    printf("%s\n", sb->data);


    if(!sb_append(
            sb,
            " Dynamic String Buffer Example"
    )) {

        printf("Append failed\n");
        sb_free(sb);
        return 1;
    }

    printf("%s\n", sb->data);


    printf(
        "\nFinal Length: %zu\n",
        sb->length
    );

    printf(
        "Final Capacity: %zu\n",
        sb->capacity
    );


    sb_free(sb);

    return 0;
}