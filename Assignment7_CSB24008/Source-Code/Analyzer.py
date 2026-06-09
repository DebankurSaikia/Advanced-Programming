import random
import time
import tracemalloc
from typing import TypedDict, List, Dict, Set
from collections import defaultdict
from functools import reduce


class Activity(TypedDict):
    user: str
    action: str
    duration: float


def generate_logs(n: int) -> List[Activity]:
    actions = ["YouTube", "Instagram", "Chrome", "WhatsApp", "Twitter", "LinkedIn"]

    return [
        {
            "user": f"CSB{24000 + random.randint(1, n)}",
            "action": random.choice(actions),
            "duration": round(random.uniform(0.5, 3.0), 2)
        }
        for _ in range(n)
    ]


def total_time_per_user(logs: List[Activity]) -> Dict[str, float]:

    def accumulate(acc: Dict[str, float], record: Activity) -> Dict[str, float]:
        acc[record["user"]] += record["duration"]
        return acc

    totals = reduce(accumulate, logs, defaultdict(float))

    return dict(totals)


def most_active_users(logs: List[Activity], k: int) -> List[str]:
    totals = total_time_per_user(logs)

    sorted_users = sorted(
        totals.items(),
        key=lambda x: x[1],
        reverse=True
    )

    return [user for user, _ in sorted_users[:k]]


def unique_actions(logs: List[Activity]) -> Set[str]:
    return {record["action"] for record in logs}


def measure_performance(func, *args):

    tracemalloc.start()

    start_time = time.perf_counter()

    result = func(*args)

    end_time = time.perf_counter()

    current_mem, peak_mem = tracemalloc.get_traced_memory()

    tracemalloc.stop()

    execution_time = (end_time - start_time) * 1000
    peak_memory = peak_mem / 1024

    return result, execution_time, peak_memory


if __name__ == "__main__":

    n = int(input("Enter number of activity records to generate: "))

    logs = generate_logs(n)

    totals, time_ms, mem_kb = measure_performance(total_time_per_user, logs)
    top_users, time_ms2, mem_kb2 = measure_performance(most_active_users, logs, 5)
    actions, time_ms3, mem_kb3 = measure_performance(unique_actions, logs)

    print("\n========== Activity Log Analysis ==========")

    print(f"\nTotal Activity Records : {n}")
    print(f"Unique Users           : {len(totals)}")
    print(f"Unique Actions         : {len(actions)}")

    print("\nTop 5 Most Active Users:")
    for i, user in enumerate(top_users, 1):
        print(f"{i}. {user}")

    print("\nPerformance Statistics")

    print(f"Total Time Calculation -> {time_ms:.4f} ms | {mem_kb:.4f} KB")
    print(f"Top Users Calculation  -> {time_ms2:.4f} ms | {mem_kb2:.4f} KB")
    print(f"Unique Actions         -> {time_ms3:.4f} ms | {mem_kb3:.4f} KB")

    print("\n============================================")


