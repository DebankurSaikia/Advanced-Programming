import pytest

from registration_service import (
    RegistrationService,
    InvalidEmailError,
    UnderageError
)


@pytest.fixture

def service():
    return RegistrationService()



def test_valid_registration(service):
    assert service.register_user("alice@example.com", 22) is True



def test_empty_email(service):
    with pytest.raises(InvalidEmailError):
        service.register_user("", 25)



def test_invalid_email_format(service):
    with pytest.raises(InvalidEmailError):
        service.register_user("aliceexample.com", 25)



def test_underage_user(service):
    with pytest.raises(UnderageError):
        service.register_user("bob@example.com", 16)



def test_assertion_error(service):
    with pytest.raises(AssertionError):
        service.register_user(None, 20)