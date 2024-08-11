const baseUrl = 'http://localhost:8080/api';

// Load project details on project detail page
function loadProjectDetails() {
    const urlParams = new URLSearchParams(window.location.search);
    const projectId = urlParams.get('projectId');
    fetch(`${baseUrl}/projects/${projectId}`, {
        headers: {
            'Authorization': 'Basic ' + btoa('user:password')
        }
    })
    .then(response => response.json())
    .then(project => {
        document.getElementById('projectTitle').innerText = project.title;
        const todosDiv = document.getElementById('todos');
        todosDiv.innerHTML = '';
        project.todos.forEach(todo => {
            const todoElement = document.createElement('div');
            todoElement.innerHTML = `<p>${todo.description}</p>
                                     <p>${todo.status ? 'Completed' : 'Pending'}</p>
                                     <button onclick="toggleTodoStatus(${todo.id}, ${!todo.status})">
                                        ${todo.status ? 'Mark as Pending' : 'Mark as Complete'}
                                     </button>
                                     <button onclick="deleteTodo(${todo.id})">Delete</button>`;
            todosDiv.appendChild(todoElement);
        });
    });
}

// Add a new todo to the project
function addTodo() {
    const description = prompt('Enter todo description:');
    const urlParams = new URLSearchParams(window.location.search);
    const projectId = urlParams.get('projectId');
    if (description) {
        fetch(`${baseUrl}/projects/${projectId}/todos`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Basic ' + btoa('user:password')
            },
            body: JSON.stringify({ description: description })
        })
        .then(() => {
            loadProjectDetails();
        });
    }
}

// Toggle the status of a todo
function toggleTodoStatus(todoId, status) {
    fetch(`${baseUrl}/todos/${todoId}?status=${status}`, {
        method: 'PUT',
        headers: {
            'Authorization': 'Basic ' + btoa('user:password')
        }
    })
    .then(() => {
        loadProjectDetails();
    });
}

// Delete a todo
function deleteTodo(todoId) {
    fetch(`${baseUrl}/todos/${todoId}`, {
        method: 'DELETE',
        headers: {
            'Authorization': 'Basic ' + btoa('user:password')
        }
    })
    .then(() => {
        loadProjectDetails();
    });
}

// Export project summary as a GitHub Gist
function exportGist() {
    const urlParams = new URLSearchParams(window.location.search);
    const projectId = urlParams.get('projectId');
    fetch(`${baseUrl}/projects/${projectId}/export-gist`, {
        method: 'POST',
        headers: {
            'Authorization': 'Basic ' + btoa('user:password')
        }
    })
    .then(() => {
        alert('Project summary exported as Gist.');
    });
}

// Initial load of projects
if (window.location.pathname.endsWith('index.html')) {
    loadProjects();
}

// Initial load of project details
if (window.location.pathname.endsWith('project.html')) {
    loadProjectDetails();
}
