# stackoverflow-test

### Project description
This is a simple application for working with stackoverflow API and retrieves information about 
user's accounts and user's tags according to task.

### Features
The application shows users according to next requirements:
- Are located in Romania or Moldova
- Have a reputation of min 223 points.
- Answered min 1 question
- Have the tags: "java",".net","docker" or "C#"

The application uses API key from site stackoverflow, because free version is limited for requests.


### Technologies used in project
- Java v.11
- Apache Maven v.3.8
- Docker, Retrofit

### For launch project

1. You should have ready to use docker and git programs.

2. Clone this project. Run in terminal:
   > git clone https://github.com/gdpbalt/stackoverflow-test.git

3. Build and run project via docker. Go to the project directory and run in terminal:
   > docker build -t stackoverflow . && docker run -it stackoverflow
