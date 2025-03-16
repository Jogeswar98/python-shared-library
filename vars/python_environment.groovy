def call() {
    sh """
        sudo apt-get update -y
        sudo apt-get install -y python3.11 python3.11-venv python3-pip
        python3.11 -m venv venv
        . venv/bin/activate
        pip install --upgrade pip poetry
    """
}
