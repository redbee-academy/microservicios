
# How to connect to DB

## Build

```
$ docker build --tag socialMediaDb .
```



## Run 

```
$ docker run -p 5432:5432 socialMediaDb
```

## Connect
  - usr: redbee
  - pwd: Password10
  - jdbc:postgresql://localhost:5432/{db}

Las dbs son: **feed** y **users**

# How to change

If you want to change any db/table/clomun, modify the scripts, rebuild the image and rerun
