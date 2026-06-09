import pytest
from score_processor import ScoreProcessor


@pytest.fixture
def processor():
    return ScoreProcessor()


def test_valid_score_file(processor, tmp_path):

    file_path = tmp_path / "score.txt"
    file_path.write_text("8")

    result = processor.process_score_file(str(file_path))

    assert result == 80


def test_missing_file(processor):

    with pytest.raises(FileNotFoundError):
        processor.process_score_file("missing_file.txt")


def test_invalid_data(processor, tmp_path):

    file_path = tmp_path / "invalid.txt"
    file_path.write_text("abc")

    with pytest.raises(ValueError):
        processor.process_score_file(str(file_path))