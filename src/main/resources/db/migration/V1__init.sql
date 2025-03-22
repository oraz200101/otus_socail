CREATE SCHEMA IF NOT EXISTS otus_social;

CREATE TABLE otus_social.endpoints
(
  id                         uuid PRIMARY KEY,     -- Уникальный идентификатор
  url                        TEXT        NOT NULL, -- URL эндпоинта
  method_name                TEXT        NOT NULL,
  access_roles               TEXT[]      NOT NULL, -- Массив ролей, которые имеют доступ
  request_type               VARCHAR(50) NOT NULL, -- Тип запроса (например, GET, POST)
  default_access_roles       TEXT[]      NOT NULL, -- Роль по умолчанию для доступа
  active_defaultAccess_roles BOOLEAN     NOT NULL,
  authenticated              BOOLEAN     NOT NULL, -- Требуется ли аутентификация
  permit_all                 BOOLEAN     NOT NULL  -- Доступен ли эндпоинт для всех
);

CREATE TABLE otus_social.users
(
  id          uuid PRIMARY KEY, -- Уникальный идентификатор пользователя
  username    VARCHAR(255) NOT NULL UNIQUE,
  first_name  VARCHAR(255) NOT NULL, -- Имя
  second_name VARCHAR(255) NOT NULL, -- Фамилия
  birth_date  DATE         NOT NULL, -- Дата рождения
  biography   TEXT,                  -- Биография
  city        VARCHAR(255),          -- Город
  password    VARCHAR(255) NOT NULL  -- Пароль
);

CREATE INDEX idx_endpoint_url ON otus_social.endpoints (url);