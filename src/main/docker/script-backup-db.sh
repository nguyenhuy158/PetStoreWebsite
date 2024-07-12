docker cp ./src/main/docker/config.cnf docker-pet_store_3_chang_trai-1:/config.cnf ;
rm ./src/main/docker/data.sql
sudo docker exec docker-pet_store_3_chang_trai-1 /usr/bin/mysqldump --defaults-extra-file=config.cnf PetStore > ./src/main/docker/data.sql
