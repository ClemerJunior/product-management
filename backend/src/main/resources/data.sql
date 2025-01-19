create schema IF NOT EXISTS STOCK;

INSERT INTO stock.action (id, name, description) VALUES (1, 'ADD','Action allowed to add products');
INSERT INTO stock.action (id, name, description) VALUES (2, 'UPDATE', 'Action allowed to update products');
INSERT INTO stock.action (id, name, description) VALUES (3, 'DELETE', 'Action allowed to delete products');
INSERT INTO stock.role (id, name, description) VALUES (1, 'MASTER', 'Role able to perform any operation');
INSERT INTO stock.role_action (role_id, action_id) VALUES (1, 1);
INSERT INTO stock.role_action (role_id, action_id) VALUES (1, 2);
INSERT INTO stock.role_action (role_id, action_id) VALUES (1, 3);
INSERT INTO stock.users (id, username, password, last_access, role_id) VALUES (1,'superuser', '$2a$10$1S4XVwI4wE8CN4QIk2cpJeOn/Hg/7vahl5dBfSROrpHFw8U3qNaDK',null, 1);
INSERT INTO stock.category(id, name, parent_id) VALUES (1, 'electronics', null);
INSERT INTO stock.category(id, name, parent_id) VALUES (2, 'cellphone', 1);
INSERT INTO stock.category(id, name, parent_id) VALUES (3, 'apple', 2);

