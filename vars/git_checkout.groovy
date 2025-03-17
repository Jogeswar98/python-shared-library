def call() {
    echo 'Cloning repository...'
    git branch: 'main', url: 'https://github.com/Jogeswar98/attendance-api.git'
}
