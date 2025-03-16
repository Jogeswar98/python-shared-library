def call() {
    sh """
        venv/bin/python -m pip install --upgrade pip
        venv/bin/poetry install --no-root
    """
}
