insert into ROLES values(1,'ROLE_USER');
insert into ROLES values(2,'ROLE_ADMIN');
insert into ROLES values(3,'ROLE_SUPERVISOR');
insert into ROLES values(4,'ROLE_MODERATOR');

insert into USERS values('1a48dc3c-eed8-4f6b-a8c8-cc05825f61d9',sysdate, 'user@test.com',	TRUE,sysdate,sysdate,'user',	'$2a$10$g5JwewCvhp.QewMOBsHXfODgxSiEPK40jTkOc6rYX0Ia8k711Wsy.',	null,	'user@test.com');
insert into USERS values('f215030a-f950-4234-9a04-281457ac2eeb',sysdate, 'admin@test.com',	TRUE,sysdate,sysdate,'admin',	'$2a$10$g5JwewCvhp.QewMOBsHXfODgxSiEPK40jTkOc6rYX0Ia8k711Wsy.',	null,	'admin@test.com');
insert into USERS values('933a2e9f-9af4-4151-b3ea-2cd0fdb3f93c',sysdate, 'supervisor@test.com',	TRUE,sysdate,sysdate,'supervisor',	'$2a$10$g5JwewCvhp.QewMOBsHXfODgxSiEPK40jTkOc6rYX0Ia8k711Wsy.',	null,	'supervisor@test.com');
insert into USERS values('a2245845-c11e-441e-939e-5bc842537693',sysdate, 'moderator@test.com',	TRUE,sysdate,sysdate,'moderator',	'$2a$10$g5JwewCvhp.QewMOBsHXfODgxSiEPK40jTkOc6rYX0Ia8k711Wsy.',	null,	'moderator@test.com');

insert into USERS_ROLES values('1a48dc3c-eed8-4f6b-a8c8-cc05825f61d9',1);
insert into USERS_ROLES values('f215030a-f950-4234-9a04-281457ac2eeb',2);
insert into USERS_ROLES values('933a2e9f-9af4-4151-b3ea-2cd0fdb3f93c',3);
insert into USERS_ROLES values('a2245845-c11e-441e-939e-5bc842537693',4);