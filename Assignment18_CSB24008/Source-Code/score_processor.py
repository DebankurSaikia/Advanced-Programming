class ScoreProcessor:

    def process_score_file(self, file_path: str) -> int:

        try:
            
            with open(file_path, "r") as file:
                content = file.read().strip()

            score = int(content)

        except FileNotFoundError:
            print("Error: File not found")
            raise

        except ValueError:
            print("Error: Invalid data format in file")
            raise

        else:
            result = score * 10
            print("Data processed successfully")
            return result

        finally:
            print("File cleanup completed")


if __name__ == "__main__":

    processor = ScoreProcessor()

    try:
        result = processor.process_score_file("score.txt")
        print(f"Processed Score: {result}")

    except Exception:
        pass