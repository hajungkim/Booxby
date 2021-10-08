FROM python:3.8.5

#WORKDIR /flask
COPY . .
RUN pip install --no-cache-dir -r requirements.txt

RUN apt-get update && apt-get install --yes software-properties-common

RUN add-apt-repository ppa:webupd8team/java

RUN apt install default-jdk -y

EXPOSE 5000

CMD ["python", "app.py"]
