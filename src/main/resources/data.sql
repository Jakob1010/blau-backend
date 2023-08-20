INSERT INTO drink(id,alcoholic,description,name) VALUES(1,'1','','Beer')
ON CONFLICT (id) DO NOTHING;

INSERT INTO app_user(id,username,password,role) VALUES(1,'jakob','password','ROLE_ADMIN')
ON CONFLICT (id) DO NOTHING;