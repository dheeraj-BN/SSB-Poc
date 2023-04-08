FROM node:19-alpine

WORKDIR /reactApp

ADD build /reactApp/build

COPY ["package.json", "/reactApp"]

RUN /bin/sh  -c 'npm install -g serve'

EXPOSE 3000

CMD /bin/sh -c 'serve -s build'