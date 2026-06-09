import sys
import gc


class Node:
    def __init__(self, name: str):
        self.name = name
        self.link = None


    def __del__(self):
        print(f"{self.name} is being garbage collected")



if __name__ == "__main__":

    gc.disable()

    print("Creating objects...\n")

    
    A = Node("Node A")
    B = Node("Node B")

    
    A.link = B
    B.link = A

   
    print("Reference Counts:")
    print(f"A: {sys.getrefcount(A)}")
    print(f"B: {sys.getrefcount(B)}")

    a_id = id(A)
    b_id = id(B)

    print("\nDeleting A and B...")
    del A
    del B

   
    print("\nInvestigating unreachable objects before cleanup...")

    existing_ids = {id(obj) for obj in gc.get_objects()}

    if a_id in existing_ids and b_id in existing_ids:
        print("Both objects still exist in memory due to circular reference.")
    else:
        print("Objects not found.")


    print("\nRunning garbage collector...")
    collected = gc.collect()


    print(f"\nUnreachable objects collected: {collected}")

    existing_ids = {id(obj) for obj in gc.get_objects()}

    if a_id not in existing_ids and b_id not in existing_ids:
        print("Objects successfully removed from memory.")

    gc.enable()