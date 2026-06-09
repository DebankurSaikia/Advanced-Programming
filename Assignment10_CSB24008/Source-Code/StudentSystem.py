class Address:
    def __init__(self, street: str, city: str, zipCode: str):
        self.street = street
        self.city = city
        self.zipCode = zipCode

    def display(self):
        print(f"{self.street}, {self.city} - {self.zipCode}")


class Student:
    def __init__(self, name: str, age: int, address: Address):
        self.name = name
        self._age = None
        self.age = age
        self.address = address
        self.courses = []

    @property
    def age(self):
        return self._age

    @age.setter
    def age(self, value: int):
        if value <= 0 or value > 120:
            raise ValueError("Age must be between 1 and 120")
        self._age = value

    def add_course(self, course: str):
        self.courses.append(course)

    def display(self):
        print(f"Name: {self.name}")
        print(f"Age: {self.age}")
        print("Address:", end=" ")
        self.address.display()
        print(f"Courses: {self.courses}")



class ScholarshipStudent(Student):
    def __init__(self, name: str, age: int, address: Address, scholarshipAmount: float):
        super().__init__(name, age, address)

        if scholarshipAmount < 0:
            raise ValueError("Scholarship amount cannot be negative")

        self.scholarshipAmount = scholarshipAmount

    
    def display(self):
        super().display()
        print(f"Scholarship Amount: {self.scholarshipAmount}")



if __name__ == "__main__":

    addr = Address("GNB Road", "Guwahati", "781001")

    s1 = Student("Rahul", 20, addr)

    
    print("\n--- Demonstrating Mutable Behavior ---")
    s1.add_course("Math")
    print("After adding Math:", s1.courses)

    s1.add_course("Physics")
    print("After adding Physics:", s1.courses)

    print("\n--- Student Details ---")
    s1.display()

    s2 = ScholarshipStudent("Ankit", 21, addr, 50000)

    s2.add_course("Computer Science")
    s2.add_course("AI")

    print("\n--- Scholarship Student Details ---")
    s2.display()