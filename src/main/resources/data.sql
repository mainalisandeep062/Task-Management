CREATE TABLE IF NOT EXISTS user
(
    user_id    bigint auto_increment
        primary key,
    active     enum ('ACTIVE', 'INACTIVE') not null,
    created_at datetime(6)                 null,
    created_by varchar(255)                null,
    e_mail     varchar(50)                 not null,
    full_name  varchar(255)                null,
    password   varchar(255)                not null,
    role       enum ('ADMIN', 'EMPLOYEE')  not null,
    updated_at datetime(6)                 null,
    updated_by varchar(255)                null,
    constraint UK5awx5dcb9xdv5m1bl38bmoke0
        unique (e_mail)
);

INSERT INTO user (active,
                  created_at,
                  created_by,
                  e_mail,
                  full_name,
                  password,
                  role,
                  updated_at,
                  updated_by)
VALUES ('ACTIVE',
        NOW(),
        'SystemAdmin',
        'varosa@example.com',
        'Default Admin',
        '{noop}SystemAdmin@123',
        'ADMIN',
        NOW(),
        '')ON DUPLICATE KEY UPDATE full_name='Super Admin';

