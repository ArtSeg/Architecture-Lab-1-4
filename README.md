Сержантов Артем, ИДМ-20-01
Лабораторные работы по "Архитектуре ПО"

(1) Приложение реализующее трехзвенную архитектуру + (4) Выгрузка данных из БД в файлы формата Excel/Word.

Для запуска необходимо создать базу данных stankin в MySQL Server:

  CREATE DATABASE stankin;
  USE stankin;

  CREATE USER 'dbuser'@'%' IDENTIFIED BY 'password';
  GRANT ALL PRIVILEGES ON stankin.* TO 'dbuser'@'%' WITH GRANT OPTION;

  CREATE TABLE IF NOT EXISTS data (
      id SERIAL PRIMARY KEY,
      model VARCHAR(64) NOT NULL,
      speed INT,
      service_date DATE
  );
  INSERT INTO data(model, speed, service_date) VALUES
  ('Picasso 3D Designer', 50, '2021-07-02'),
  ('Picasso 3D Designer X', 60, '2022-07-02'),
  ('Picasso 3D Designer X PRO', 80, '20121-07-02'),
  ('UltiSteel 2', 100, '2022-07-02'),
  ('Ultimaker 2', 100, '2021-07-02'),
  ('Picasso 3D Designer rev.2', 50, '2022-07-02'),
  ('Picasso 3D Designer X rev.2', 60, '2022-07-02');

Запуск:

  java -jar project.jar ru.stankin.project.ProjectApplication

Сервер:

  http://localhost:8090/table
