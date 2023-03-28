rm data.sql
sudo docker exec d933dc7030cf /usr/bin/mysqldump -u root --password=petstoremaidinh PetStore > data.sql
