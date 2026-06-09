products = [
    {"name": "Laptop", "stock": 15},
    {"name": "Mouse", "stock": 5},
    {"name": "Keyboard", "stock": 8},
    {"name": "Monitor", "stock": 20},
    {"name": "USB Cable", "stock": 3},
    {"name": "Hard Disk", "stock": 9},
    {"name": "Motherboard", "stock": 12}
]

print("Products with stock less than 10:\n")

for product in products:
    if product["stock"] < 10:
        print(f"Product: {product['name']}, Stock: {product['stock']}")
