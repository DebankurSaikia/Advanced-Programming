import re


class InvalidEmailError(ValueError):
    def __init__(self, email: str):
        super().__init__(f"Invalid email format: {email}")


class UnderageError(Exception):
    def __init__(self, age: int):
        super().__init__(f"User age {age} is below the minimum required age of 18")



class RegistrationService:

    
    EMAIL_PATTERN = r'^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'

    def register_user(self, email: str, age: int) -> bool:

        
        assert email is not None and isinstance(age, int), "Invalid system state"

        
        if not email or email.strip() == "":
            raise InvalidEmailError(email)

        
        if not re.match(self.EMAIL_PATTERN, email):
            raise InvalidEmailError(email)

        
        if age < 18:
            raise UnderageError(age)

        return True



if __name__ == "__main__":

    service = RegistrationService()

    try:
        result = service.register_user("john@example.com", 24)

        if result:
            print("Registration successful")

    except (InvalidEmailError, UnderageError) as e:
        print(e)