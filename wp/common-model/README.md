#Starting the database

```sh 
docker-compose up -d finki-db
```

#Populate database with latest schema and data

```sh 
git pull 
```

Then run the common model spring boot application. This should be done regularly in order to have the latest data. 
