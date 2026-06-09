import { useEffect, useState } from 'react';

export default function Dashboard(){
    const [toDoName, setToDoName] = useState("");
    const [toDoList, setToDoList] = useState(() => {
        const data = localStorage.getItem("todos");
        return data ? JSON.parse(data) : [];
    });

    useEffect(() => {
        localStorage.setItem("todos", JSON.stringify(toDoList));
    }, [toDoList]);

    const handleSubmit = () => {

        setToDoList((prev) => {
            return [...prev, toDoName];
        });
        setToDoName("");
    }

    const handleDelete = (index) => {
        setToDoList((prev) => {
            return prev.filter((todo,id) => id !== index);
        })
    }

    return (
        <div className='dashboard-wrapper'>
            <h1>Create Your To-Do</h1>
            <div>
                <input
                    type="text"
                    name=""
                    value={toDoName}
                    onChange={(e) => setToDoName(e.target.value)}
                    id=""
                />
                <button
                    onClick={handleSubmit}
                >
                    <i class="fa-solid fa-plus"></i>
                </button>
            </div>
            <div className="display-box">
                {
                    toDoList.map((todo, id) => (
                        <div className="todo-container" key={id}>
                            <p>{todo}</p>
                            <button
                                onClick={() => handleDelete(id)}
                            >
                                Delete
                            </button>
                        </div>
                    ))
                }
            </div>
        </div>
    )
}