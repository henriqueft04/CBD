CREATE KEYSPACE IF NOT EXISTS videos WITH REPLICATION =
{ 'class' : 'SimpleStrategy',
'replication_factor' : '1'
};

USE videos;

DROP TABLE IF EXISTS videos.user;
CREATE TABLE IF NOT EXISTS videos.user (
    username text PRIMARY KEY,
    name text,
    email text,
    register_date timestamp,
);

DROP TABLE IF EXISTS videos.video;
CREATE TABLE IF NOT EXISTS videos.video (
    video_id uuid,
    title text,
    user text,
    upload_date timestamp,
    description text,
    tags set<text>,
    PRIMARY KEY (video_id, upload_date)
) WITH CLUSTERING ORDER BY (upload_date DESC);

DROP INDEX IF EXISTS video_tags_dix;
CREATE INDEX  video_tags_dix ON videos.video(values(tags));

DROP TABLE IF EXISTS videos.video_tags;
CREATE TABLE IF NOT EXISTS videos.video_tags (
    tag text,
    video_id uuid,
    PRIMARY KEY (tag, video_id)
);


DROP TABLE IF EXISTS videos.videos_by_upload_date;
CREATE TABLE IF NOT EXISTS videos.videos_by_upload_date (
    upload_date timestamp,
    video_id uuid,
    title text,
    user text,
    description text,
    tags set<text>,
    PRIMARY KEY ( video_id, upload_date)
) WITH CLUSTERING ORDER BY (upload_date DESC );

DROP TABLE IF EXISTS videos.videos_by_user;
CREATE TABLE IF NOT EXISTS videos.videos_by_user (
    user text,
    video_id uuid,
    title text,
    upload_date timestamp,
    description text,
    tags set<text>,
    PRIMARY KEY (user, upload_date, video_id)
);

DROP INDEX IF EXISTS video_upload_date_idx;
CREATE INDEX IF NOT EXISTS video_upload_date_idx ON videos.videos_by_user(upload_date);

DROP TABLE IF EXISTS videos.comment_by_video;
CREATE TABLE IF NOT EXISTS videos.comment_by_video (
    video_id uuid,
    comment_id uuid,
    comment_date timestamp,
    user text,
    comment text,
    PRIMARY KEY (video_id, comment_date, comment_id)
) WITH CLUSTERING ORDER BY (comment_date DESC);

DROP TABLE IF EXISTS videos.comment_by_user;
CREATE TABLE IF NOT EXISTS videos.comment_by_user (
    user text,
    comment_date timestamp,
    comment_id uuid,
    video_id uuid,
    comment text,
    PRIMARY KEY (user, comment_date, comment_id)
) WITH CLUSTERING ORDER BY (comment_date DESC);

DROP TABLE IF EXISTS videos.followed_videos_comments;
CREATE TABLE IF NOT EXISTS videos.followed_videos_comments (
    user text,
    video_id uuid,
    comment_id uuid,
    comment_date timestamp,
    comment text,
    PRIMARY KEY ((user), video_id, comment_date)
) WITH CLUSTERING ORDER BY (video_id ASC, comment_date DESC);

DROP TABLE IF EXISTS videos.video_follower;
CREATE TABLE IF NOT EXISTS videos.video_follower (
    user text,
    video_id uuid,
    user_timestamp timestamp,
    PRIMARY KEY (video_id, user)
);

DROP TABLE IF EXISTS videos.events_by_user;
CREATE TABLE IF NOT EXISTS videos.events_by_user (
    user text,
    video_id uuid,
    event_date timestamp,
    event_type text,
    PRIMARY KEY ((user, video_id), event_date)
) WITH CLUSTERING ORDER BY (event_date DESC);

DROP INDEX IF EXISTS event_type_idx;
CREATE INDEX event_type_idx ON videos.events_by_user(event_type);

DROP TABLE IF EXISTS videos.video_rating;
CREATE TABLE IF NOT EXISTS videos.video_rating (
    partition_key text,
    video_id uuid,
    rating int,
    PRIMARY KEY (partition_key, rating, video_id)
) WITH CLUSTERING ORDER BY (rating DESC, video_id ASC);

DROP TABLE IF EXISTS videos.average_video_rating;
CREATE TABLE IF NOT EXISTS videos.average_video_rating (
    video_id uuid PRIMARY KEY,
    avg_rating float,
    total_ratings int
);