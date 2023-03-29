rm data.sql
sudo docker exec c219c72151f4 /usr/bin/mysqldump -u root --password=petstoremaidinh PetStore > data.sql
