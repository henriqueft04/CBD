USE videos;

-- Inserir dados na tabela user
INSERT INTO user (username, name, email, register_date)
VALUES ('ana_silva', 'Ana Silva', 'ana.silva@gmail.com', '2017-07-15 10:00:00');
INSERT INTO user (username, name, email, register_date)
VALUES ('pedro_gomes', 'Pedro Gomes', 'pedro.gomes@yahoo.com', '2017-07-20 11:30:00');
INSERT INTO user (username, name, email, register_date)
VALUES ('maria_oliveira', 'Maria Oliveira', 'maria.oliveira@outlook.com', '2017-07-25 09:45:00');
INSERT INTO user (username, name, email, register_date)
VALUES ('joao_santos', 'João Santos', 'joao.santos@hotmail.com', '2017-08-05 14:20:00');
INSERT INTO user (username, name, email, register_date)
VALUES ('clara_costa', 'Clara Costa', 'clara.costa@gmail.com', '2017-08-10 16:50:00');
INSERT INTO user (username, name, email, register_date)
VALUES ('lucas_martins', 'Lucas Martins', 'lucas.martins@gmail.com', '2017-08-15 08:30:00');
INSERT INTO user (username, name, email, register_date)
VALUES ('renata_ferreira', 'Renata Ferreira', 'renata.ferreira@yahoo.com', '2017-08-20 12:10:00');
INSERT INTO user (username, name, email, register_date)
VALUES ('carlos_dias', 'Carlos Dias', 'carlos.dias@outlook.com', '2017-08-25 13:40:00');
INSERT INTO user (username, name, email, register_date)
VALUES ('bianca_rosa', 'Bianca Rosa', 'bianca.rosa@hotmail.com', '2017-09-01 10:15:00');
INSERT INTO user (username, name, email, register_date)
VALUES ('marco_pereira', 'Marco Pereira', 'marco.pereira@gmail.com', '2017-09-05 09:00:00');

-- Inserir dados na tabela video
INSERT INTO video (video_id, title, user, upload_date, description, tags)
VALUES (11111111-1111-1111-1111-111111111111, 'Como fazer pão caseiro', 'ana_silva', '2017-08-01 10:00:00', 'Um guia passo a passo para fazer pão em casa.', {'culinária', 'tutorial', 'Aveiro'});
INSERT INTO video (video_id, title, user, upload_date, description, tags)
VALUES (11111111-1111-1111-1111-111111111112, 'Como fazer pão industrial', 'ana_silva', '2017-08-31 11:00:00', 'Um guia passo a passo para fazer pão em fábrica.', {'culinária', 'tutorial', 'Aveiro'});
INSERT INTO video (video_id, title, user, upload_date, description, tags)
VALUES (11111111-1111-1111-1111-111111111113, 'Como fazer panquecas de Albufeira', 'ana_silva', '2017-09-20 09:30:00', 'Um guia passo a passo para fazer panquecas que conquistam qualquer um.', {'culinária', 'tutorial'});
INSERT INTO video (video_id, title, user, upload_date, description, tags)
VALUES (22222222-2222-2222-2222-222222222222, 'A História da Arte Moderna', 'pedro_gomes', '2017-10-01 14:00:00', 'Exploração dos principais movimentos da arte moderna.', {'educação', 'arte'});
INSERT INTO video (video_id, title, user, upload_date, description, tags)
VALUES (33333333-3333-3333-3333-333333333333, '10 Melhores Exercícios para Perder Peso', 'maria_oliveira', '2017-10-05 16:30:00', 'Exercícios rápidos e fáceis para perder peso.', {'fitness', 'saúde'});
INSERT INTO video (video_id, title, user, upload_date, description, tags)
VALUES (44444444-4444-4444-4444-444444444444, 'Como Montar um Computador', 'joao_santos', '2017-10-10 08:45:00', 'Aprenda a montar o seu próprio computador passo a passo.', {'tecnologia', 'tutorial'});
INSERT INTO video (video_id, title, user, upload_date, description, tags)
VALUES (55555555-5555-5555-5555-555555555555, 'Dicas para Viajar Com Crianças', 'clara_costa', '2017-10-15 12:20:00', 'Sugestões práticas para viagens em família.', {'viagem', 'família'});
INSERT INTO video (video_id, title, user, upload_date, description, tags)
VALUES (66666666-6666-6666-6666-666666666666, 'Fotografia para Iniciantes', 'lucas_martins', '2017-10-20 09:10:00', 'Curso básico de fotografia.', {'fotografia', 'tutorial'});
INSERT INTO video (video_id, title, user, upload_date, description, tags)
VALUES (77777777-7777-7777-7777-777777777777, 'Aprendendo Python', 'renata_ferreira', '2017-10-25 11:55:00', 'Introdução à programação com Python.', {'programação', 'tecnologia'});
INSERT INTO video (video_id, title, user, upload_date, description, tags)
VALUES (88888888-8888-8888-8888-888888888888, 'Meditação Guiada', 'carlos_dias', '2017-10-30 14:40:00', 'Sessões de meditação para iniciantes.', {'saúde', 'bem-estar', 'Aveiro'});
INSERT INTO video (video_id, title, user, upload_date, description, tags)
VALUES (99999999-9999-9999-9999-999999999999, 'Desenho Digital Avançado', 'bianca_rosa', '2017-11-05 10:25:00', 'Técnicas avançadas de desenho digital.', {'arte', 'tecnologia'});
INSERT INTO video (video_id, title, user, upload_date, description, tags)
VALUES (aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa, 'Cozinhando com Vegetais', 'marco_pereira', '2017-11-10 09:50:00', 'Receitas deliciosas com vegetais.', {'culinária', 'saúde'});

-- Inserir dados na tabela video_tags
INSERT INTO video_tags (tag, video_id)
VALUES ('culinária', 11111111-1111-1111-1111-111111111111);
INSERT INTO video_tags (tag, video_id)
VALUES ('tutorial', 11111111-1111-1111-1111-111111111111);
INSERT INTO video_tags (tag, video_id)
VALUES ('Aveiro', 11111111-1111-1111-1111-111111111111);
INSERT INTO video_tags (tag, video_id)
VALUES ('culinária', 11111111-1111-1111-1111-111111111112);
INSERT INTO video_tags (tag, video_id)
VALUES ('tutorial', 11111111-1111-1111-1111-111111111112);
INSERT INTO video_tags (tag, video_id)
VALUES ('Aveiro', 11111111-1111-1111-1111-111111111112);
INSERT INTO video_tags (tag, video_id)
VALUES ('culinária', 11111111-1111-1111-1111-111111111113);
INSERT INTO video_tags (tag, video_id)
VALUES ('tutorial', 11111111-1111-1111-1111-111111111113);
INSERT INTO video_tags (tag, video_id)
VALUES ('culinária', 22222222-2222-2222-2222-222222222222);
INSERT INTO video_tags (tag, video_id)
VALUES ('tutorial', 22222222-2222-2222-2222-222222222222);
INSERT INTO video_tags (tag, video_id)
VALUES ('educação', 22222222-2222-2222-2222-222222222222);
INSERT INTO video_tags (tag, video_id)
VALUES ('arte', 22222222-2222-2222-2222-222222222222);
INSERT INTO video_tags (tag, video_id)
VALUES ('fitness', 33333333-3333-3333-3333-333333333333);
INSERT INTO video_tags (tag, video_id)
VALUES ('saúde', 33333333-3333-3333-3333-333333333333);
INSERT INTO video_tags (tag, video_id)
VALUES ('tecnologia', 44444444-4444-4444-4444-444444444444);
INSERT INTO video_tags (tag, video_id)
VALUES ('tutorial', 44444444-4444-4444-4444-444444444444);
INSERT INTO video_tags (tag, video_id)
VALUES ('viagem', 55555555-5555-5555-5555-555555555555);
INSERT INTO video_tags (tag, video_id)
VALUES ('família', 55555555-5555-5555-5555-555555555555);
INSERT INTO video_tags (tag, video_id)
VALUES ('fotografia', 66666666-6666-6666-6666-666666666666);
INSERT INTO video_tags (tag, video_id)
VALUES ('tutorial', 66666666-6666-6666-6666-666666666666);
INSERT INTO video_tags (tag, video_id)
VALUES ('programação', 77777777-7777-7777-7777-777777777777);
INSERT INTO video_tags (tag, video_id)
VALUES ('tecnologia', 77777777-7777-7777-7777-777777777777);
INSERT INTO video_tags (tag, video_id)
VALUES ('saúde', 88888888-8888-8888-8888-888888888888);
INSERT INTO video_tags (tag, video_id)
VALUES ('bem-estar', 88888888-8888-8888-8888-888888888888);
INSERT INTO video_tags (tag, video_id)
VALUES ('arte', 99999999-9999-9999-9999-999999999999);
INSERT INTO video_tags (tag, video_id)
VALUES ('tecnologia', 99999999-9999-9999-9999-999999999999);
INSERT INTO video_tags (tag, video_id)
VALUES ('culinária', aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa);
INSERT INTO video_tags (tag, video_id)
VALUES ('saúde', aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa);


-- Inserir dados na tabela videos_by_upload_date
INSERT INTO videos_by_upload_date (upload_date, video_id, title, user, description, tags)
VALUES ('2017-08-01 10:00:00', 11111111-1111-1111-1111-111111111111, 'Como fazer pão caseiro', 'ana_silva', 'Um guia passo a passo para fazer pão em casa.', {'culinária', 'tutorial', 'Aveiro'});
INSERT INTO videos_by_upload_date (upload_date, video_id, title, user, description, tags)
VALUES ('2017-08-31 11:00:00', 11111111-1111-1111-1111-111111111112, 'Como fazer pão industrial', 'ana_silva', 'Um guia passo a passo para fazer pão em fábrica.', {'culinária', 'tutorial', 'Aveiro'});
INSERT INTO videos_by_upload_date (upload_date, video_id, title, user, description, tags)
VALUES ('2017-09-20 09:30:00', 11111111-1111-1111-1111-111111111113, 'Como fazer panquecas de Albufeira', 'ana_silva', 'Um guia passo a passo para fazer panquecas que conquistam qualquer um.', {'culinária', 'tutorial'});
INSERT INTO videos_by_upload_date (upload_date, video_id, title, user, description, tags)
VALUES ('2017-10-01 14:00:00', 22222222-2222-2222-2222-222222222222, 'A História da Arte Moderna', 'pedro_gomes', 'Exploração dos principais movimentos da arte moderna.', {'educação', 'arte'});
INSERT INTO videos_by_upload_date (upload_date, video_id, title, user, description, tags)
VALUES ('2017-10-05 16:30:00', 33333333-3333-3333-3333-333333333333, '10 Melhores Exercícios para Perder Peso', 'maria_oliveira', 'Exercícios rápidos e fáceis para perder peso.', {'fitness', 'saúde'});
INSERT INTO videos_by_upload_date (upload_date, video_id, title, user, description, tags)
VALUES ('2017-10-10 08:45:00', 44444444-4444-4444-4444-444444444444, 'Como Montar um Computador', 'joao_santos', 'Aprenda a montar o seu próprio computador passo a passo.', {'tecnologia', 'tutorial'});
INSERT INTO videos_by_upload_date (upload_date, video_id, title, user, description, tags)
VALUES ('2017-10-15 12:20:00', 55555555-5555-5555-5555-555555555555, 'Dicas para Viajar Com Crianças', 'clara_costa', 'Sugestões práticas para viagens em família.', {'viagem', 'família'});
INSERT INTO videos_by_upload_date (upload_date, video_id, title, user, description, tags)
VALUES ('2017-10-20 09:10:00', 66666666-6666-6666-6666-666666666666, 'Fotografia para Iniciantes', 'lucas_martins', 'Curso básico de fotografia.', {'fotografia', 'tutorial'});
INSERT INTO videos_by_upload_date (upload_date, video_id, title, user, description, tags)
VALUES ('2017-10-25 11:55:00', 77777777-7777-7777-7777-777777777777, 'Aprendendo Python', 'renata_ferreira', 'Introdução à programação com Python.', {'programação', 'tecnologia'});
INSERT INTO videos_by_upload_date (upload_date, video_id, title, user, description, tags)
VALUES ('2017-10-30 14:40:00', 88888888-8888-8888-8888-888888888888, 'Meditação Guiada', 'carlos_dias', 'Sessões de meditação para iniciantes.', {'saúde', 'bem-estar'});
INSERT INTO videos_by_upload_date (upload_date, video_id, title, user, description, tags)
VALUES ('2017-11-05 10:25:00', 99999999-9999-9999-9999-999999999999, 'Desenho Digital Avançado', 'bianca_rosa', 'Técnicas avançadas de desenho digital.', {'arte', 'tecnologia'});
INSERT INTO videos_by_upload_date (upload_date, video_id, title, user, description, tags)
VALUES ('2017-11-10 09:50:00', aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa, 'Cozinhando com Vegetais', 'marco_pereira', 'Receitas deliciosas com vegetais.', {'culinária', 'saúde'});

-- Inserir dados na tabela videos_by_user
INSERT INTO videos_by_user (user, video_id, title, upload_date, description, tags)
VALUES ('ana_silva', 11111111-1111-1111-1111-111111111111, 'Como fazer pão caseiro', '2017-08-01 10:00:00', 'Um guia passo a passo para fazer pão em casa.', {'culinária', 'tutorial', 'Aveiro'});
INSERT INTO videos_by_user (user, video_id, title, upload_date, description, tags)
VALUES ('ana_silva', 11111111-1111-1111-1111-111111111112, 'Como fazer pão industrial', '2017-08-31 11:00:00', 'Um guia passo a passo para fazer pão em fábrica.', {'culinária', 'tutorial', 'Aveiro'});
INSERT INTO videos_by_user (user, video_id, title, upload_date, description, tags)
VALUES ('ana_silva', 11111111-1111-1111-1111-111111111113, 'Como fazer panquecas de Albufeira','2017-09-20 09:30:00', 'Um guia passo a passo para fazer panquecas que conquistam qualquer um.', {'culinária', 'tutorial'});
INSERT INTO videos_by_user (user, video_id, title, upload_date, description, tags)
VALUES ('pedro_gomes', 22222222-2222-2222-2222-222222222222, 'A História da Arte Moderna', '2017-10-01 14:00:00', 'Exploração dos principais movimentos da arte moderna.', {'educação', 'arte'});
INSERT INTO videos_by_user (user, video_id, title, upload_date, description, tags)
VALUES ('maria_oliveira', 33333333-3333-3333-3333-333333333333, '10 Melhores Exercícios para Perder Peso', '2017-10-05 16:30:00', 'Exercícios rápidos e fáceis para perder peso.', {'fitness', 'saúde'});
INSERT INTO videos_by_user (user, video_id, title, upload_date, description, tags)
VALUES ('joao_santos', 44444444-4444-4444-4444-444444444444, 'Como Montar um Computador', '2017-10-10 08:45:00', 'Aprenda a montar o seu próprio computador passo a passo.', {'tecnologia', 'tutorial'});
INSERT INTO videos_by_user (user, video_id, title, upload_date, description, tags)
VALUES ('clara_costa', 55555555-5555-5555-5555-555555555555, 'Dicas para Viajar Com Crianças', '2017-10-15 12:20:00', 'Sugestões práticas para viagens em família.', {'viagem', 'família'});
INSERT INTO videos_by_user (user, video_id, title, upload_date, description, tags)
VALUES ('lucas_martins', 66666666-6666-6666-6666-666666666666, 'Fotografia para Iniciantes', '2017-10-20 09:10:00', 'Curso básico de fotografia.', {'fotografia', 'tutorial'});
INSERT INTO videos_by_user (user, video_id, title, upload_date, description, tags)
VALUES ('renata_ferreira', 77777777-7777-7777-7777-777777777777, 'Aprendendo Python', '2017-10-25 11:55:00', 'Introdução à programação com Python.', {'programação', 'tecnologia'});
INSERT INTO videos_by_user (user, video_id, title, upload_date, description, tags)
VALUES ('carlos_dias', 88888888-8888-8888-8888-888888888888, 'Meditação Guiada', '2017-10-30 14:40:00', 'Sessões de meditação para iniciantes.', {'saúde', 'bem-estar'});
INSERT INTO videos_by_user (user, video_id, title, upload_date, description, tags)
VALUES ('bianca_rosa', 99999999-9999-9999-9999-999999999999, 'Desenho Digital Avançado', '2017-11-05 10:25:00', 'Técnicas avançadas de desenho digital.', {'arte', 'tecnologia'});
INSERT INTO videos_by_user (user, video_id, title, upload_date, description, tags)
VALUES ('marco_pereira', aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa, 'Cozinhando com Vegetais', '2017-11-10 09:50:00', 'Receitas deliciosas com vegetais.', {'culinária', 'saúde'});

-- Inserir dados na tabela comment_by_video
INSERT INTO comment_by_video (video_id, comment_date, comment_id, user, comment)
VALUES (11111111-1111-1111-1111-111111111111, '2017-08-02 10:15:00', uuid(), 'joao_santos', 'Ótimo vídeo, muito útil!');
INSERT INTO comment_by_video (video_id, comment_date, comment_id, user, comment)
VALUES (11111111-1111-1111-1111-111111111111, '2017-08-03 11:20:00', uuid(), 'clara_costa', 'Adorei as dicas de pão!');
INSERT INTO comment_by_video (video_id, comment_date, comment_id, user, comment)
VALUES (22222222-2222-2222-2222-222222222222, '2017-10-02 14:30:00', uuid(), 'maria_oliveira', 'Muito informativo, obrigado!');
INSERT INTO comment_by_video (video_id, comment_date, comment_id, user, comment)
VALUES (33333333-3333-3333-3333-333333333333, '2017-10-06 16:45:00', uuid(), 'ana_silva', 'Excelentes exercícios!');
INSERT INTO comment_by_video (video_id, comment_date, comment_id, user, comment)
VALUES (44444444-4444-4444-4444-444444444444, '2017-10-11 09:50:00', uuid(), 'pedro_gomes', 'Agora sei montar meu PC!');
INSERT INTO comment_by_video (video_id, comment_date, comment_id, user, comment)
VALUES (55555555-5555-5555-5555-555555555555, '2017-10-16 12:35:00', uuid(), 'lucas_martins', 'Perfeito para viagens com crianças!');
INSERT INTO comment_by_video (video_id, comment_date, comment_id, user, comment)
VALUES (66666666-6666-6666-6666-666666666666, '2017-10-21 09:25:00', uuid(), 'renata_ferreira', 'Aprender Python nunca foi tão fácil.');
INSERT INTO comment_by_video (video_id, comment_date, comment_id, user, comment)
VALUES (77777777-7777-7777-7777-777777777777, '2017-10-26 12:55:00', uuid(), 'carlos_dias', 'Muito relaxante, gostei!');
INSERT INTO comment_by_video (video_id, comment_date, comment_id, user, comment)
VALUES (88888888-8888-8888-8888-888888888888, '2017-10-31 15:10:00', uuid(), 'bianca_rosa', 'Desenho incrível!');
INSERT INTO comment_by_video (video_id, comment_date, comment_id, user, comment)
VALUES (aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa, '2017-11-11 10:40:00', uuid(), 'marco_pereira', 'Receitas deliciosas e saudáveis!');

-- Inserir dados na tabela comment_by_user
INSERT INTO comment_by_user (user, comment_date, comment_id, video_id, comment)
VALUES ('ana_silva', '2017-08-02 10:15:00', uuid(), 11111111-1111-1111-1111-111111111111, 'Ótimo vídeo, muito útil!');
INSERT INTO comment_by_user (user, comment_date, comment_id, video_id, comment)
VALUES ('clara_costa', '2017-08-03 11:20:00', uuid(), 11111111-1111-1111-1111-111111111111, 'Adorei as dicas de pão!');
INSERT INTO comment_by_user (user, comment_date, comment_id, video_id, comment)
VALUES ('maria_oliveira', '2017-10-02 14:30:00', uuid(), 22222222-2222-2222-2222-222222222222, 'Muito informativo, obrigado!');
INSERT INTO comment_by_user (user, comment_date, comment_id, video_id, comment)
VALUES ('joao_santos', '2017-10-06 16:45:00', uuid(), 33333333-3333-3333-3333-333333333333, 'Excelentes exercícios!');
INSERT INTO comment_by_user (user, comment_date, comment_id, video_id, comment)
VALUES ('pedro_gomes', '2017-10-11 09:50:00', uuid(), 44444444-4444-4444-4444-444444444444, 'Agora sei montar meu PC!');
INSERT INTO comment_by_user (user, comment_date, comment_id, video_id, comment)
VALUES ('lucas_martins', '2017-10-16 12:35:00', uuid(), 55555555-5555-5555-5555-555555555555, 'Perfeito para viagens com crianças!');
INSERT INTO comment_by_user (user, comment_date, comment_id, video_id, comment)
VALUES ('renata_ferreira', '2017-10-21 09:25:00', uuid(), 66666666-6666-6666-6666-666666666666, 'Aprender Python nunca foi tão fácil.');
INSERT INTO comment_by_user (user, comment_date, comment_id, video_id, comment)
VALUES ('carlos_dias', '2017-10-26 12:55:00', uuid(), 77777777-7777-7777-7777-777777777777, 'Muito relaxante, gostei!');
INSERT INTO comment_by_user (user, comment_date, comment_id, video_id, comment)
VALUES ('bianca_rosa', '2017-10-31 15:10:00', uuid(), 88888888-8888-8888-8888-888888888888, 'Desenho incrível!');
INSERT INTO comment_by_user (user, comment_date, comment_id, video_id, comment)
VALUES ('marco_pereira', '2017-11-11 10:40:00', uuid(), 99999999-9999-9999-9999-999999999999, 'Receitas deliciosas e saudáveis!');
INSERT INTO comment_by_user (user, comment_date, comment_id, video_id, comment)
VALUES ('ana_silva', '2017-11-12 11:00:00', uuid(), aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa, 'Receitas deliciosas e saudáveis!');

-- Inserir dados na tabela video_follower
INSERT INTO video_follower (user, video_id, user_timestamp)
VALUES ('joao_santos', 11111111-1111-1111-1111-111111111111, '2017-08-02 10:20:00');
INSERT INTO video_follower (user, video_id, user_timestamp)
VALUES ('clara_costa', 22222222-2222-2222-2222-222222222222, '2017-10-03 11:30:00');
INSERT INTO video_follower (user, video_id, user_timestamp)
VALUES ('pedro_gomes', 33333333-3333-3333-3333-333333333333, '2017-10-06 14:45:00');
INSERT INTO video_follower (user, video_id, user_timestamp)
VALUES ('maria_oliveira', 44444444-4444-4444-4444-444444444444, '2017-10-11 16:50:00');
INSERT INTO video_follower (user, video_id, user_timestamp)
VALUES ('ana_silva', 55555555-5555-5555-5555-555555555555, '2017-10-16 12:35:00');
INSERT INTO video_follower (user, video_id, user_timestamp)
VALUES ('lucas_martins', 66666666-6666-6666-6666-666666666666, '2017-10-21 09:25:00');
INSERT INTO video_follower (user, video_id, user_timestamp)
VALUES ('renata_ferreira', 77777777-7777-7777-7777-777777777777, '2017-10-26 12:55:00');
INSERT INTO video_follower (user, video_id, user_timestamp)
VALUES ('carlos_dias', 88888888-8888-8888-8888-888888888888, '2017-10-31 15:10:00');
INSERT INTO video_follower (user, video_id, user_timestamp)
VALUES ('bianca_rosa', 99999999-9999-9999-9999-999999999999, '2017-11-06 10:30:00');
INSERT INTO video_follower (user, video_id, user_timestamp)
VALUES ('marco_pereira', aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa, '2017-11-11 09:50:00');

-- Inserir dados na tabela followed_videos_comments
INSERT INTO followed_videos_comments (user, video_id, comment_id, comment_date, comment)
VALUES ('joao_santos', 11111111-1111-1111-1111-111111111111, uuid(), '2017-08-02 10:15:00', 'Ótimo vídeo, muito útil!');
INSERT INTO followed_videos_comments (user, video_id, comment_id, comment_date, comment)
VALUES ('clara_costa', 22222222-2222-2222-2222-222222222222, uuid(), '2017-10-02 14:30:00', 'Muito informativo, obrigado!');
INSERT INTO followed_videos_comments (user, video_id, comment_id, comment_date, comment)
VALUES ('pedro_gomes', 33333333-3333-3333-3333-333333333333, uuid(), '2017-10-06 16:45:00', 'Excelentes exercícios!');
INSERT INTO followed_videos_comments (user, video_id, comment_id, comment_date, comment)
VALUES ('maria_oliveira', 44444444-4444-4444-4444-444444444444, uuid(), '2017-10-11 09:50:00', 'Agora sei montar meu PC!');
INSERT INTO followed_videos_comments (user, video_id, comment_id, comment_date, comment)
VALUES ('ana_silva', 55555555-5555-5555-5555-555555555555, uuid(), '2017-10-16 12:35:00', 'Perfeito para viagens com crianças!');
INSERT INTO followed_videos_comments (user, video_id, comment_id, comment_date, comment)
VALUES ('lucas_martins', 66666666-6666-6666-6666-666666666666, uuid(), '2017-10-21 09:25:00', 'Aprender Python nunca foi tão fácil.');
INSERT INTO followed_videos_comments (user, video_id, comment_id, comment_date, comment)
VALUES ('renata_ferreira', 77777777-7777-7777-7777-777777777777, uuid(), '2017-10-26 12:55:00', 'Muito relaxante, gostei!');
INSERT INTO followed_videos_comments (user, video_id, comment_id, comment_date, comment)
VALUES ('carlos_dias', 88888888-8888-8888-8888-888888888888, uuid(), '2017-10-31 15:10:00', 'Desenho incrível!');
INSERT INTO followed_videos_comments (user, video_id, comment_id, comment_date, comment)
VALUES ('bianca_rosa', 99999999-9999-9999-9999-999999999999, uuid(), '2017-11-05 10:25:00', 'Receitas deliciosas e saudáveis!');
INSERT INTO followed_videos_comments (user, video_id, comment_id, comment_date, comment)
VALUES ('marco_pereira', aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa, uuid(), '2017-11-11 10:40:00', 'Receitas deliciosas e saudáveis!');

-- Inserir dados na tabela events_by_user
INSERT INTO events_by_user (user, video_id, event_date, event_type)
VALUES ('ana_silva', 11111111-1111-1111-1111-111111111111, '2017-08-02 10:30:00', 'view');
INSERT INTO events_by_user (user, video_id, event_date, event_type)
VALUES ('ana_silva', 22222222-2222-2222-2222-222222222222, '2017-08-31 11:30:00', 'pause');
INSERT INTO events_by_user (user, video_id, event_date, event_type)
VALUES ('ana_silva', 11111111-1111-1111-1111-111111111111, '2017-08-02 10:35:00', 'hate');
INSERT INTO events_by_user (user, video_id, event_date, event_type)
VALUES ('ana_silva', 22222222-2222-2222-2222-222222222222, '2017-08-31 11:45:00', 'like');
INSERT INTO events_by_user (user, video_id, event_date, event_type)
VALUES ('pedro_gomes', 33333333-3333-3333-3333-333333333333, '2017-10-06 14:50:00', 'pause');
INSERT INTO events_by_user (user, video_id, event_date, event_type)
VALUES ('pedro_gomes', 44444444-4444-4444-4444-444444444444, '2017-10-11 16:55:00', 'like');
INSERT INTO events_by_user (user, video_id, event_date, event_type)
VALUES ('maria_oliveira', 55555555-5555-5555-5555-555555555555, '2017-10-16 12:40:00', 'view');
INSERT INTO events_by_user (user, video_id, event_date, event_type)
VALUES ('maria_oliveira', 66666666-6666-6666-6666-666666666666, '2017-10-21 09:30:00', 'like');
INSERT INTO events_by_user (user, video_id, event_date, event_type)
VALUES ('joao_santos', 77777777-7777-7777-7777-777777777777, '2017-10-26 13:00:00', 'pause');
INSERT INTO events_by_user (user, video_id, event_date, event_type)
VALUES ('joao_santos', 88888888-8888-8888-8888-888888888888, '2017-10-31 15:15:00', 'like');
INSERT INTO events_by_user (user, video_id, event_date, event_type)
VALUES ('clara_costa', 99999999-9999-9999-9999-999999999999, '2017-11-06 10:35:00', 'view');
INSERT INTO events_by_user (user, video_id, event_date, event_type)
VALUES ('clara_costa', aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa, '2017-11-11 09:55:00', 'like');

-- Inserir dados na tabela video_rating
INSERT INTO video_rating (partition_key, video_id, rating)
VALUES ('all_ratings', 11111111-1111-1111-1111-111111111111, 5);
INSERT INTO video_rating (partition_key, video_id, rating)
VALUES ('all_ratings', 11111111-1111-1111-1111-111111111111, 4);
INSERT INTO video_rating (partition_key, video_id, rating)
VALUES ('all_ratings', 22222222-2222-2222-2222-222222222222, 3);
INSERT INTO video_rating (partition_key, video_id, rating)
VALUES ('all_ratings', 33333333-3333-3333-3333-333333333333, 4);
INSERT INTO video_rating (partition_key, video_id, rating)
VALUES ('all_ratings', 33333333-3333-3333-3333-333333333333, 5);
INSERT INTO video_rating (partition_key, video_id, rating)
VALUES ('all_ratings', 44444444-4444-4444-4444-444444444444, 3);
INSERT INTO video_rating (partition_key, video_id, rating)
VALUES ('all_ratings', 44444444-4444-4444-4444-444444444444, 4);
INSERT INTO video_rating (partition_key, video_id, rating)
VALUES ('all_ratings', 55555555-5555-5555-5555-555555555555, 5);
INSERT INTO video_rating (partition_key, video_id, rating)
VALUES ('all_ratings', 55555555-5555-5555-5555-555555555555, 4);

-- Inserir dados na tabela average_video_rating
INSERT INTO average_video_rating (video_id, avg_rating, total_ratings)
VALUES (11111111-1111-1111-1111-111111111111, 4.5, 10);
INSERT INTO average_video_rating (video_id, avg_rating, total_ratings)
VALUES (22222222-2222-2222-2222-222222222222, 4.0, 8);
INSERT INTO average_video_rating (video_id, avg_rating, total_ratings)
VALUES (33333333-3333-3333-3333-333333333333, 4.5, 12);
INSERT INTO average_video_rating (video_id, avg_rating, total_ratings)
VALUES (44444444-4444-4444-4444-444444444444, 3.5, 6);
INSERT INTO average_video_rating (video_id, avg_rating, total_ratings)
VALUES (55555555-5555-5555-5555-555555555555, 4.5, 10);
INSERT INTO average_video_rating (video_id, avg_rating, total_ratings)
VALUES (66666666-6666-6666-6666-666666666666, 4.0, 7);
INSERT INTO average_video_rating (video_id, avg_rating, total_ratings)
VALUES (77777777-7777-7777-7777-777777777777, 4.8, 15);
INSERT INTO average_video_rating (video_id, avg_rating, total_ratings)
VALUES (88888888-8888-8888-8888-888888888888, 4.2, 9);
INSERT INTO average_video_rating (video_id, avg_rating, total_ratings)
VALUES (99999999-9999-9999-9999-999999999999, 4.7, 14);
INSERT INTO average_video_rating (video_id, avg_rating, total_ratings)
VALUES (aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa, 4.3, 11);

