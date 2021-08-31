FROM postgres:13.4

ENV POSTGRES_PASSWORD="Password10" \
    POSTGRES_USER="redbee"

COPY user.sql /docker-entrypoint-initdb.d/
COPY feed.sql /docker-entrypoint-initdb.d/
