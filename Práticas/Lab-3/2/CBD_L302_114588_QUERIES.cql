use videos;

SELECT JSON * FROM user;

SELECT JSON * FROM video;

SELECT JSON * FROM video_follower;

SELECT JSON * FROM events_by_user;

SELECT JSON * FROM video_rating;

SELECT JSON * FROM events_by_user;

SELECT * FROM videos_by_upload_date;


// 7. Permitir a pesquisa de todos os vídeos de determinado autor;
SELECT JSON * FROM videos_by_user;

// 8. Permitir a pesquisa de comentários por utilizador, ordenado inversamente pela data;
SELECT JSON * FROM comment_by_user;

// 9. Permitir a pesquisa de comentários por vídeos, ordenado inversamente pela data;
SELECT JSON * FROM comment_by_video;

// 10. Permitir a pesquisa do rating médio de um vídeo e quantas vezes foi votado;
SELECT JSON * FROM average_video_rating;


// 1.Os últimos 3 comentários introduzidos para um vídeo;
SELECT JSON * FROM comment_by_video LIMIT 3;

// 2. Lista das tags de determinado vídeo;
SELECT JSON tags FROM video WHERE video_id = 11111111-1111-1111-1111-111111111111;


// 3. Todos os vídeos com a tag Aveiro;
SELECT JSON * FROM video WHERE tags CONTAINS 'Aveiro';

// 4.
// a) Os últimos 5 eventos de determinado vídeo realizados por um utilizador;
SELECT JSON * FROM events_by_user
        WHERE user = 'ana_silva' AND video_id = 11111111-1111-1111-1111-111111111111 LIMIT 5;

// b) Todos os eventos de determinado utilizador;
SELECT JSON * FROM events_by_user WHERE user = 'ana_silva' ;

// c) Todos os eventos de determinado utilizador do tipo "pause"
SELECT JSON * FROM events_by_user WHERE user = 'ana_silva' AND event_type = 'pause';

// 5. Vídeos partilhados por determinado utilizador (maria1987, por exemplo) num determinado período de tempo (Agosto de 2017, por exemplo);
SELECT JSON * FROM videos_by_user
        WHERE user = 'ana_silva' AND upload_date >= '2017-08-01' AND upload_date <= '2017-09-10';

// 6. Os últimos 10 vídeos, ordenado inversamente pela data da partilhada;
SELECT JSON * FROM videos.videos_by_upload_date LIMIT 10;

// 7. Todos os seguidores (followers) de determinado vídeo;
SELECT JSON * FROM video_follower WHERE video_id = 11111111-1111-1111-1111-111111111111;

// 8. Todos os comentários (dos vídeos) que determinado utilizador está a seguir (following);
SELECT JSON * FROM followed_videos_comments WHERE user = 'ana_silva';

// 9. Os 5 vídeos com maior rating;
SELECT JSON * FROM video_rating LIMIT 5;

// 10. Uma query que retorne todos os vídeos e que mostre claramente a forma pela qual estão ordenados;
SELECT JSON title, upload_date, video_id FROM video;

// 11. Lista com as Tags existentes e o número de vídeos catalogados com cada uma delas;
SELECT tag, COUNT(*) AS video_count FROM video_tags GROUP BY tag;

// 12. .. 14. Descreva 3 perguntas adicionais à base dados (alíneas 12 a 14),
-- significativamente distintas das anteriores e que tenham como base nova(s) tabela(s) resultante de novos requisitos (a definir).
-- Apresente igualmente a solução de pesquisa para cada questão.

// 12. Listar todos os comentários de um vídeo específico, ordenados por data (do mais recente para o mais antigo).
SELECT JSON * FROM comment_by_video WHERE video_id = 11111111-1111-1111-1111-111111111111;

// 13. Listar os vídeos enviados por um user em ordem cronológica
SELECT JSON * FROM videos_by_user WHERE user = 'ana_silva';

//14. Obter os detalhes de um vídeo específico pelo seu video_id.
SELECT JSON * FROM video WHERE video_id = 11111111-1111-1111-1111-111111111111;